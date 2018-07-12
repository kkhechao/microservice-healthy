package com.zqkh.healthy.context.appservice.impl;

import com.jovezhao.nest.ddd.StringIdentifier;
import com.jovezhao.nest.ddd.builder.ConstructLoader;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.RepositoryLoader;
import com.jovezhao.nest.ddd.identifier.IdGenerator;
import com.jovezhao.nest.starter.AppService;
import com.zqkh.common.exception.BusinessException;
import com.zqkh.healthy.context.appservice.impl.domain.repository.*;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.ProgramQueryMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.TestResultQueryMapper;
import com.zqkh.healthy.context.appservice.impl.domain.service.ProgramDomainService;
import com.zqkh.healthy.context.appservice.impl.domain.service.TestResultDomainService;
import com.zqkh.healthy.context.appservice.inter.TestResultService;
import com.zqkh.healthy.context.domain.program.InitProgramVo;
import com.zqkh.healthy.context.util.RandomDataUtil;
import com.zqkh.healthy.feign.dto.paper.app.TestResultToAppDto;
import com.zqkh.healthy.feign.vo.paper.AnswerVo;
import com.zqkh.healthy.feign.vo.paper.SubmitAnAnswerVo;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 侧题结果业务实现
 *
 * @author 东来
 * @create 2018/5/11 0011
 */
@AppService
public class TestResultServiceImpl implements TestResultService {

    private EntityLoader<TestResult> testResultEntityLoader = new RepositoryLoader<>(TestResult.class);

    private ConstructLoader<TestResult> testResultConstructLoader = new ConstructLoader<>(TestResult.class);

    private EntityLoader<TestPaper> testPaperEntityLoader = new RepositoryLoader<>(TestPaper.class);

    private EntityLoader<Program> programEntityLoader = new RepositoryLoader<>(Program.class);

    private EntityLoader<ProgramTemplate> programTemplateEntityLoader = new RepositoryLoader<>(ProgramTemplate.class);

    private ConstructLoader<Program> programConstructLoader=new ConstructLoader<>(Program.class);

    @Resource
    private ProgramDomainService programDomainService;

    private final static String MAGIC_VARIABLE = "score";

    @Resource
    private TestResultQueryMapper testResultQueryMapper;


    @Resource
    private ProgramQueryMapper programQueryMapper;

    @Resource
    private TestResultDomainService testResultDomainService;

    @Override
    public TestResultToAppDto getTestResultToApp(String id) {
       return testResultDomainService.getTestResultToApp(id,null);
    }

    @Override
    public TestResultToAppDto submitAnAnswer(SubmitAnAnswerVo submitAnAnswerVo) {
        if (ObjectUtils.isEmpty(submitAnAnswerVo)) {
            throw new BusinessException("答题参数为空");
        }

        if (ObjectUtils.isEmpty(submitAnAnswerVo.getId())) {
            throw new BusinessException("请选择一个测试试卷");
        }

        if (ObjectUtils.isEmpty(submitAnAnswerVo.getUserId())) {
            throw new BusinessException("用户编号为空");
        }

        if (ObjectUtils.isEmpty(submitAnAnswerVo.getFamilyMemberId())) {
            throw new BusinessException("请选择一个家庭成员");
        }

        if (ObjectUtils.isEmpty(submitAnAnswerVo.getAnswer())) {
            throw new BusinessException("答案为空");
        }

        submitAnAnswerVo.getAnswer().forEach(n -> {
            if (ObjectUtils.isEmpty(n.getIndex())) {
                throw new BusinessException("试题编号为空");
            }
        });

        //验证试卷是否存在
        TestPaper testPaper = testPaperEntityLoader.create(StringIdentifier.valueOf(submitAnAnswerVo.getId()));

        if (ObjectUtils.isEmpty(testPaper)) {
            throw new BusinessException("试卷不存在");
        }


        //过滤试卷必做题
        List<TestQuestions> testQuestionOptions = testPaper.getQuestion().stream().filter(n -> n.getRequired()).filter(n -> !ObjectUtils.isEmpty(n)).collect(Collectors.toList());
        if (!ObjectUtils.isEmpty(testQuestionOptions)) {
            testQuestionOptions.forEach(n -> {
                //判断必做题是否做完
                boolean flg = false;
                for (AnswerVo answerVo : submitAnAnswerVo.getAnswer()) {
                    if (n.getIndex().equals(answerVo.getIndex())) {
                        flg = true;
                    }
                }
                if (!flg) {
                    throw new BusinessException("试题:" + n.getIndex() + "为必做题");
                }
            });
        }

        List<AnswerResult> answer = submitAnAnswerVo.getAnswer().stream().filter(n -> !ObjectUtils.isEmpty(n.getIndex())).map(n -> {
            AnswerResult answerResult = new AnswerResult();
            answerResult.setAnswer(n.getAnswer());
            answerResult.setQuestionId(n.getIndex());
            answerResult.setPreviousIndex(n.getPreviousIndex());
            return answerResult;
        }).collect(Collectors.toList());


        /**
         * 方案模板,用于存放命中分数评分标准下的方案模板编号
         */
        Map<String, String> programTemplateMap = new HashMap<>();

        /**
         * 统计分数
         */
        Map<String, Integer> pointFractionMap = new HashMap<>();
        testPaper.getQuestion().forEach(n -> {
            submitAnAnswerVo.getAnswer().forEach(m -> {
                if (m.getIndex().equals(n.getIndex())) {
                    //计算该题分数
                    TestQuestionOption testQuestionOption = n.getOption().stream().filter(z -> z.getName().equals(m.getAnswer())).findFirst().orElse(null);
                    if (!ObjectUtils.isEmpty(testQuestionOption)) {
                        testQuestionOption.getFraction().forEach(z -> {
                            //分数
                            Integer fraction = pointFractionMap.get(z.getPoint());
                            pointFractionMap.put(z.getPoint(), ObjectUtils.isEmpty(fraction) ? z.getFraction() : fraction + z.getFraction());
                        });
                    }
                }
            });
        });
        List<TestResultPoint> point = new ArrayList<>();
        testPaper.getPoint().forEach(n -> {
            if (!ObjectUtils.isEmpty(pointFractionMap.get(n.getName()))) {
                TestResultPoint testResultPoint = new TestResultPoint();
                testResultPoint.setName(n.getName());
                n.getStandard().forEach(m -> {
                    String el = this.ElExpressionConversion(m.getFractionalRange(), MAGIC_VARIABLE);
                    //判断分值属于哪个区间
                    boolean flg = this.isInclude(pointFractionMap.get(n.getName()).toString(), el, MAGIC_VARIABLE);
                    if (flg) {
                        testResultPoint.setActionContent(m.getContent());
                        testResultPoint.setContent(m.getTitle());
                        if (!ObjectUtils.isEmpty(m.getCoverId())) {
                            testResultPoint.setCoverId(m.getCoverId().get(RandomDataUtil.interceptPosition(m.getCoverId().size(), 1)));
                        }
                        //命中的评分标准,存放方案模板
                        programTemplateMap.put(n.getName(), m.getProgramTemplate());
                    }
                });
                point.add(testResultPoint);

            }
        });

        String testResultId = testResultQueryMapper.getTestResultIdByTestPaperId(submitAnAnswerVo.getId(), submitAnAnswerVo.getFamilyMemberId());
        TestResult testResult = null;
        if (!ObjectUtils.isEmpty(testResultId)) {
            testResult = testResultEntityLoader.create(StringIdentifier.valueOf(testResultId));
            if (ObjectUtils.isEmpty(testResult)) {
                //如果答题单不存在则创建一个
                testResult = testResultConstructLoader.create(IdGenerator.getInstance().generate(TestResult.class));
                testResult.init(submitAnAnswerVo.getUserId(), submitAnAnswerVo.getFamilyMemberId(), submitAnAnswerVo.getId(), null, answer, point, testPaper);
            } else {
                //如果答题单存在则对原有答题单进行修改
                testResult.edit(testResult.getFeedback(), answer, point,testPaper);
            }
        } else {
            testResult = testResultConstructLoader.create(IdGenerator.getInstance().generate(TestResult.class));
            testResult.init(submitAnAnswerVo.getUserId(), submitAnAnswerVo.getFamilyMemberId(), submitAnAnswerVo.getId(), null, answer, point, testPaper);
        }

        testResultId = testResult.getId().toValue();

        List<Program> programs=new ArrayList<>();
        for (int i = 0; i < point.size(); i++) {
            //移除原有方案
            //programDomainService.remove(Program.SrcType.TEST_PAPER_RESULT,testResultId,point.get(i).getName(),Program.Status.REJECT,Program.Status.RECEIVE,Program.Status.OPEN);
            programDomainService.remove(Program.SrcType.TEST_PAPER_RESULT,testResultId,point.get(i).getName(),null);
            if (!ObjectUtils.isEmpty(programTemplateMap.get(point.get(i).getName()))) {
                InitProgramVo initProgramVo = new InitProgramVo();
                initProgramVo.setFamilyMember(submitAnAnswerVo.getFamilyMemberId());
                initProgramVo.setSrcType(Program.SrcType.TEST_PAPER_RESULT);
                initProgramVo.setSrc(testResultId);
                initProgramVo.setSrcDesc(point.get(i).getName());
                //加载方案模板
                ProgramTemplate programTemplate = programTemplateEntityLoader.create(StringIdentifier.valueOf(programTemplateMap.get(point.get(i).getName())));
                initProgramVo.setProgramTemplate(programTemplate);
                //生成新方案
                Program program=programDomainService.init(initProgramVo);
                programs.add(program);
            }
        }

        TestResultToAppDto testResultToApp = testResultDomainService.getTestResultToApp(testResultId,programs);
        return testResultToApp;
    }


    /**
     * EL表达式转换
     *
     * @param src:EL原值,例如    1000<money<=2000, money>1 这类
     * @param magicVariable： 魔法变量,例如 money,risk这类
     * @return ${000<money<=2000}
     */
    private String ElExpressionConversion(String src, String magicVariable) {
        if (ObjectUtils.isEmpty(src) || ObjectUtils.isEmpty(magicVariable)) {
            throw new NullPointerException("指标评分标准el表达式为空");
        }
        src = src.replace("=<", "<=");
        List<String> temp = Arrays.stream(src.split(magicVariable)).filter(n -> !ObjectUtils.isEmpty(n)).collect(Collectors.toList());

        StringBuffer sb = new StringBuffer("${");
        if (temp.size() > 1) {
            sb.append(src.replace(magicVariable, magicVariable + "&&" + magicVariable));
        } else {
            sb.append(src);
        }

        sb.append("}");

        return sb.toString();
    }

    /**
     * 判断el表达式值是否满足el表达式
     *
     * @param elValue:el表达式值
     * @param elString:el表达式,格式如 将${money>=2000&&money<=4000}
     * @param magicVariable      :魔法变量 money
     * @return
     */
    private Boolean isInclude(String elValue, String elString, String magicVariable) {
        String el = elString.substring(elString.indexOf("{") + 1, elString.indexOf("}"));
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        engine.put(magicVariable, elValue);
        boolean eval = false;
        try {
            eval = (boolean) engine.eval(el);
        } catch (ScriptException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return eval;
    }
}
