package com.zqkh.healthy.context.appservice.impl;

import com.jovezhao.nest.PageList;
import com.jovezhao.nest.ddd.StringIdentifier;
import com.jovezhao.nest.ddd.builder.ConstructLoader;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.RepositoryLoader;
import com.jovezhao.nest.ddd.identifier.IdGenerator;
import com.jovezhao.nest.mybatis.PageParames;
import com.jovezhao.nest.starter.AppService;
import com.zqkh.common.exception.BusinessException;
import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.context.appservice.impl.domain.repository.*;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.TestPaperQueryMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.TestResultQueryMapper;
import com.zqkh.healthy.context.appservice.inter.TestPaperService;
import com.zqkh.healthy.feign.dto.paper.*;
import com.zqkh.healthy.feign.dto.paper.app.FamilyMemberTestResultDto;
import com.zqkh.healthy.feign.dto.paper.app.TestPaperInfoToAppDto;
import com.zqkh.healthy.feign.dto.paper.app.TestPaperListToAppDto;
import com.zqkh.healthy.feign.dto.paper.app.TestQuestionsToAppDto;
import com.zqkh.healthy.feign.dto.paper.type.TestPaperTypeInfoDto;
import com.zqkh.healthy.feign.dto.program.template.ProgramTemplateInfoDto;
import com.zqkh.healthy.feign.vo.paper.SaveTestPaperVo;
import com.zqkh.healthy.feign.vo.paper.TestPointVo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 试卷业务实现层
 *
 * @author 东来
 * @create 2018/5/7 0007
 */
@AppService
public class TestPaperServiceImpl implements TestPaperService {

    private EntityLoader<TestPaper> testPaperEntityLoader = new RepositoryLoader<>(TestPaper.class);

    private ConstructLoader<TestPaper> testPaperConstructLoader = new ConstructLoader<>(TestPaper.class);

    private EntityLoader<TestResult> testResultEntityLoader = new RepositoryLoader<>(TestResult.class);

    private EntityLoader<TestPaperType> testPaperTypeEntityLoader=new RepositoryLoader<>(TestPaperType.class);

    private EntityLoader<ProgramTemplate> programTemplateEntityLoader=new RepositoryLoader<>(ProgramTemplate.class);


    @Resource
    private TestPaperQueryMapper testPaperQueryMapper;

    @Resource
    private TestResultQueryMapper testResultQueryMapper;


    @Override
    public void saveTestPaper(SaveTestPaperVo saveTestPaperVo) {
        if (ObjectUtils.isEmpty(saveTestPaperVo)) {
            throw new BusinessException("保存试卷参数为空");
        }
        if (ObjectUtils.isEmpty(saveTestPaperVo.getTitle())) {
            throw new BusinessException("试卷标题为空");
        }
        if (ObjectUtils.isEmpty(saveTestPaperVo.getTestPaperTypeId())) {
            throw new BusinessException("试卷类型为空");
        }

        TestPaperType testPaperType=testPaperTypeEntityLoader.create(StringIdentifier.valueOf(saveTestPaperVo.getTestPaperTypeId()));
        if(ObjectUtils.isEmpty(testPaperType)){
            throw new BusinessException("试卷类型不存在");
        }

        if (ObjectUtils.isEmpty(saveTestPaperVo.getCoverId())) {
            throw new BusinessException("试卷封面图为空");
        }
        if (ObjectUtils.isEmpty(saveTestPaperVo.getRemark())) {
            throw new BusinessException("试卷备注为空");
        }
        if (ObjectUtils.isEmpty(saveTestPaperVo.getPoint())) {
            throw new BusinessException("试卷指标为空");
        }
        if (ObjectUtils.isEmpty(saveTestPaperVo.getQuestions())) {
            throw new BusinessException("试卷试题为空");
        }


        List<TestPoint> testPoints = new ArrayList<>();

        saveTestPaperVo.getPoint().forEach(n -> {
            if (ObjectUtils.isEmpty(n.getName())) {
                throw new BusinessException("指标名称为空");
            }
            if (ObjectUtils.isEmpty(n.getStandard())) {
                throw new BusinessException("指标" + n.getName() + "的评分标准为空");
            }

            TestPoint testPoint = new TestPoint();
            testPoint.setName(n.getName());
            List<GradingStandard> standards = new ArrayList<>();

            n.getStandard().forEach(n1 -> {
                if (ObjectUtils.isEmpty(n1.getTitle())) {
                    throw new BusinessException("指标" + n.getName() + "的评分标准标题为空");
                }
                if (ObjectUtils.isEmpty(n1.getContent())) {
                    throw new BusinessException("指标" + n.getName() + "的评分标准内容为空");
                }
                if (ObjectUtils.isEmpty(n1.getFractionalRange())) {
                    throw new BusinessException("指标" + n.getName() + "的评分标准分数范围为空");
                }
                if (ObjectUtils.isEmpty(n1.getCoverId())) {
                    throw new BusinessException("指标" + n.getName() + "的评分标准背景图为空");
                }
                GradingStandard gradingStandard = new GradingStandard();
                gradingStandard.setContent(n1.getContent());
                gradingStandard.setCoverId(n1.getCoverId());
                gradingStandard.setTitle(n1.getTitle());
                gradingStandard.setFractionalRange(n1.getFractionalRange());
                gradingStandard.setProgramTemplate(n1.getProgramTemplate());
                standards.add(gradingStandard);
            });
            testPoint.setStandard(standards);
            testPoints.add(testPoint);
        });


        List<TestQuestions> testQuestions = new ArrayList<>();

        saveTestPaperVo.getQuestions().forEach(n -> {
            if (ObjectUtils.isEmpty(n.getIndex())) {
                throw new BusinessException("试题编号为空");
            }
            if (ObjectUtils.isEmpty(n.getTitle())) {
                throw new BusinessException("试题" + n.getIndex() + "标题为空");
            }
            if (ObjectUtils.isEmpty(n.getType())) {
                throw new BusinessException("试题" + n.getIndex() + "类型为空");
            }
            if (ObjectUtils.isEmpty(n.getRequired())) {
                n.setRequired(false);
            }

            if (ObjectUtils.isEmpty(n.getPoint())) {
                throw new BusinessException("试题" + n.getIndex() + "指标为空");
            }


            n.getPoint().forEach(m->{
                boolean flg=false;
                for (TestPointVo testPointVo : saveTestPaperVo.getPoint()) {
                    if(testPointVo.getName().equals(m)){
                        flg=true;
                    }
                }
                if(!flg){
                    throw new BusinessException("试题:"+n.getIndex()+"的指标:"+m+"不存在,请选择正确的指标");
                }

            });

            if (ObjectUtils.isEmpty(n.getOption())) {
                throw new BusinessException("试题" + n.getIndex() + "选项为空");
            }

            TestQuestions testQuestions1 = new TestQuestions();
            testQuestions1.setIndex(n.getIndex());
            testQuestions1.setTitle(n.getTitle());
            testQuestions1.setType(TestQuestionsTypeEnum.getTestQuestionsTypeEnum(n.getType().name()));
            testQuestions1.setPoint(n.getPoint());
            testQuestions1.setRequired(n.getRequired());
            testQuestions1.setRemark(n.getRemark());


            List<TestQuestionOption> testQuestionOptions = new ArrayList<>();

            n.getOption().forEach(n1 -> {

                if (ObjectUtils.isEmpty(n1.getName())) {
                    throw new BusinessException("试题" + n.getIndex() + "选项名为空");
                }
                if (ObjectUtils.isEmpty(n1.getValue())) {
                    throw new BusinessException("试题" + n.getIndex() + "选项" + n1.getName() + "值为空");
                }
                if (ObjectUtils.isEmpty(n1.getFraction())) {
                    throw new BusinessException("试题" + n.getIndex() + "选项" + n1.getName() + "的选项分数为空");
                }

                TestQuestionOption testQuestionOption = new TestQuestionOption();
                testQuestionOption.setName(n1.getName());
                testQuestionOption.setValue(n1.getValue());
                testQuestionOption.setEndIndex(n1.getEndIndex());
                testQuestionOption.setStartIndex(n1.getStartIndex());

                List<TestQuestionOptionFraction> testQuestionOptionFractions = new ArrayList<>();
                n1.getFraction().forEach(n2 -> {
                    if (ObjectUtils.isEmpty(n2.getPoint())) {
                        throw new BusinessException("试题" + n.getIndex() + "选项" + n1.getName() + "的选项分数指标为空");
                    }

                    boolean pointFlg=false;
                    for (TestPointVo testPointVo : saveTestPaperVo.getPoint()) {
                        if(testPointVo.getName().equals(n2.getPoint())){
                            pointFlg=true;
                        }
                    }
                    if(!pointFlg){
                        throw new BusinessException("试题" + n.getIndex() + "选项" + n1.getName() + "的选项分数指标不存在,请选择正确的指标");
                    }
                    if (ObjectUtils.isEmpty(n2.getFraction())) {
                        throw new BusinessException("试题" + n.getIndex() + "选项" + n1.getName() + "的选项指标" + n2.getPoint() + "的分数为空");
                    }
                    TestQuestionOptionFraction testQuestionOptionFraction = new TestQuestionOptionFraction();
                    BeanUtils.copyProperties(n2, testQuestionOptionFraction);
                    testQuestionOptionFractions.add(testQuestionOptionFraction);
                });
                testQuestionOption.setFraction(testQuestionOptionFractions);
                testQuestionOptions.add(testQuestionOption);
            });
            testQuestions1.setOption(testQuestionOptions);
            testQuestions.add(testQuestions1);
        });

        TestPaper testPaper;
        if (!ObjectUtils.isEmpty(saveTestPaperVo.getId())) {
            testPaper = testPaperEntityLoader.create(StringIdentifier.valueOf(saveTestPaperVo.getId()));
            if (ObjectUtils.isEmpty(testPaper)) {
                throw new BusinessException("试卷编号异常");
            }
            testPaper.edit(saveTestPaperVo.getTitle(),
                    saveTestPaperVo.getTestPaperTypeId(),
                    saveTestPaperVo.getRemark(), saveTestPaperVo.getCoverId(), testPoints, testQuestions, saveTestPaperVo.getUserId());
        } else {
            testPaper = testPaperConstructLoader.create(IdGenerator.getInstance().generate(TestPaper.class));
            testPaper.init(saveTestPaperVo.getTitle(),
                    saveTestPaperVo.getTestPaperTypeId(),
                    saveTestPaperVo.getRemark(), saveTestPaperVo.getCoverId(), testPoints, testQuestions, saveTestPaperVo.getUserId());
        }
    }

    @Override
    public PageResult<TestPaperListDto> search(String title, Boolean release, String typeId, int pageIndex, int pageSize) {
        PageList<String> result = testPaperQueryMapper.search(title, release, typeId, PageParames.create(pageIndex, pageSize));
        List<TestPaperListDto> testPaperListDtoList = new ArrayList<>();

        if (!ObjectUtils.isEmpty(result.getList())) {
            testPaperListDtoList = result.stream().map(n -> {
                TestPaper testPaper = testPaperEntityLoader.create(StringIdentifier.valueOf(n));
                if (ObjectUtils.isEmpty(testPaper)) {
                    return null;
                }
                TestPaperListDto testPaperListDto = new TestPaperListDto();
                testPaperListDto.setId(testPaper.getId().toValue());
                testPaperListDto.setRelease(testPaper.getRelease());
                testPaperListDto.setTitle(testPaper.getTitle());
                if(!ObjectUtils.isEmpty(testPaper.getTestPaperType())){
                    TestPaperType testPaperType=testPaperTypeEntityLoader.create(StringIdentifier.valueOf(testPaper.getTestPaperType()));
                    if(!ObjectUtils.isEmpty(testPaperType)){
                        testPaperListDto.setType(this.getTestPaperTypeInfoDto(testPaperType));
                    }
                }
                Set<String> count=new HashSet<>();
                testPaper.getPoint().forEach(m->{
                    m.getStandard().forEach(z->{
                        if(!ObjectUtils.isEmpty(z.getProgramTemplate())){
                            count.add(z.getProgramTemplate());
                        }
                    });
                });
                testPaperListDto.setProgram(count.size()<=0?false:true);
                testPaperListDto.setQuestionNum(testPaper.getQuestion().size());
                testPaperListDto.setCoverId(testPaper.getCoverId());
                return testPaperListDto;
            }).filter(n -> !ObjectUtils.isEmpty(n)).collect(Collectors.toList());
        }
        PageResult<TestPaperListDto> testPaperListDtoPageResult = new PageResult<>();
        testPaperListDtoPageResult.setList(testPaperListDtoList);
        testPaperListDtoPageResult.setPageSize(result.getPageSize());
        testPaperListDtoPageResult.setTotalCount(result.getTotalCount());
        return testPaperListDtoPageResult;
    }


    private TestPaperTypeInfoDto getTestPaperTypeInfoDto( TestPaperType testPaperType){
        if(!ObjectUtils.isEmpty(testPaperType)){
            TestPaperTypeInfoDto testPaperTypeInfoDto=new TestPaperTypeInfoDto();
            testPaperTypeInfoDto.setId(testPaperType.getId().toValue());
            testPaperTypeInfoDto.setName(testPaperType.getName());
            return testPaperTypeInfoDto;
        }

        return null;
    }

    @Override
    public TestPaperInfoDto info(String id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new BusinessException("试卷编号为空");
        }
        TestPaper testPaper = testPaperEntityLoader.create(StringIdentifier.valueOf(id));
        if (ObjectUtils.isEmpty(testPaper)) {
            return null;
        }
        TestPaperInfoDto testPaperInfoDto = new TestPaperInfoDto();
        testPaperInfoDto.setId(testPaper.getId().toValue());
        testPaperInfoDto.setCoverId(testPaper.getCoverId());
        testPaperInfoDto.setTitle(testPaper.getTitle());

        TestPaperType testPaperType=testPaperTypeEntityLoader.create(StringIdentifier.valueOf(testPaper.getTestPaperType()));
        if(!ObjectUtils.isEmpty(testPaperType)){
            testPaperInfoDto.setType(this.getTestPaperTypeInfoDto(testPaperType));
        }

        testPaperInfoDto.setRemark(testPaper.getRemark());

        //指标
        List<TestPointDto> point = testPaper.getPoint().stream().map(n -> {
            TestPointDto testPointDto = new TestPointDto();
            testPointDto.setName(n.getName());
            List<GradingStandardDto> gradingStandardDtoList = n.getStandard().stream().map(m -> {
                GradingStandardDto gradingStandardDto = new GradingStandardDto();
                gradingStandardDto.setContent(m.getContent());
                gradingStandardDto.setCoverId(m.getCoverId());
                gradingStandardDto.setTitle(m.getTitle());

                //TODO 方案
                if(!ObjectUtils.isEmpty(m.getProgramTemplate())){
                    ProgramTemplate programTemplate=programTemplateEntityLoader.create(StringIdentifier.valueOf(m.getProgramTemplate()));
                    if(!ObjectUtils.isEmpty(programTemplate)){
                        ProgramTemplateInfoDto programTemplateInfoDto=new ProgramTemplateInfoDto();
                        programTemplateInfoDto.setId(programTemplate.getId().toValue());
                        programTemplateInfoDto.setName(programTemplate.getName());
                        gradingStandardDto.setProgramTemplate(programTemplateInfoDto);
                    }
                }
                gradingStandardDto.setFractionalRange(m.getFractionalRange());
                return gradingStandardDto;
            }).collect(Collectors.toList());
            testPointDto.setStandard(gradingStandardDtoList);
            return testPointDto;
        }).collect(Collectors.toList());
        testPaperInfoDto.setPoint(point);

        //试题
        List<TestQuestionsDto> question = testPaper.getQuestion().stream().map(n -> {
            TestQuestionsDto testQuestionsDto = new TestQuestionsDto();
            testQuestionsDto.setIndex(n.getIndex());
            testQuestionsDto.setRemark(n.getRemark());
            testQuestionsDto.setRequired(n.getRequired());
            testQuestionsDto.setTitle(n.getTitle());
            testQuestionsDto.setType(ObjectUtils.isEmpty(n.getType()) ? null : com.zqkh.healthy.feign.vo.paper.TestQuestionsTypeEnum.getTestQuestionsTypeEnum(n.getType().name()));
            testQuestionsDto.setPoint(n.getPoint());

            //试题选项
            List<TestQuestionOptionDto> testQuestionOptionDtoList = n.getOption().stream().map(m -> {
                TestQuestionOptionDto testQuestionOptionDto = new TestQuestionOptionDto();
                testQuestionOptionDto.setEndIndex(m.getEndIndex());
                testQuestionOptionDto.setStartIndex(m.getStartIndex());
                testQuestionOptionDto.setName(m.getName());
                testQuestionOptionDto.setValue(m.getValue());
                List<TestQuestionOptionFractionDto> testQuestionOptionFractionDtoList = m.getFraction().stream().map(z -> {
                    TestQuestionOptionFractionDto testQuestionOptionFractionDto = new TestQuestionOptionFractionDto();
                    BeanUtils.copyProperties(z, testQuestionOptionFractionDto);
                    return testQuestionOptionFractionDto;
                }).collect(Collectors.toList());
                testQuestionOptionDto.setFraction(testQuestionOptionFractionDtoList);
                return testQuestionOptionDto;
            }).collect(Collectors.toList());
            testQuestionOptionDtoList.sort((n1, n2) -> n1.getName().compareTo(n2.getName()));
            testQuestionsDto.setOption(testQuestionOptionDtoList);
            return testQuestionsDto;
        }).collect(Collectors.toList());
        question.sort((TestQuestionsDto n1, TestQuestionsDto n2) -> n1.getIndex().compareTo(n2.getIndex()));
        testPaperInfoDto.setQuestions(question);

        return testPaperInfoDto;
    }

    @Override
    public List<TestPaperCompletionDto> getTestPaperCompletionByTile(List<String> title, String type, String familyMemberId) {
        if (ObjectUtils.isEmpty(title)) {
            throw new BusinessException("标题没有传入");
        }
        if (ObjectUtils.isEmpty(familyMemberId)) {
            throw new BusinessException("家庭成员编号为空");
        }

        List<TestPaperCompletionDto> result = title.stream().map(n -> {
            //查询包含标题的试题编号
            List<String> ids = testPaperQueryMapper.searchContainsTitle(n, true,type);
            TestPaperCompletionDto testPaperCompletionDto = new TestPaperCompletionDto();
            testPaperCompletionDto.setName(n);
            if (ObjectUtils.isEmpty(ids)) {
                testPaperCompletionDto.setDone(false);
            } else {
                //查询做题结果编号
                List<String> resultIds = testResultQueryMapper.getTestResultIdByTestPaperIdList(ids, familyMemberId);
                //如果已做试题数量为空
                if (ObjectUtils.isEmpty(resultIds)) {
                    testPaperCompletionDto.setDone(false);
                } else {
                    //如果包含标题的试卷数量和已做试卷数量相同
                    if (ids.size() == resultIds.size()) {
                        testPaperCompletionDto.setDone(true);
                    } else {
                        testPaperCompletionDto.setDone(false);
                    }
                }
            }
            return testPaperCompletionDto;
        }).collect(Collectors.toList());
        return result;
    }


    @Override
    public List<TestPaperListToAppDto> allTestPaperList(String familyMemberId, String type) {
        List<String> ids = testPaperQueryMapper.allTestPaperByType(type, true);

        if (ObjectUtils.isEmpty(ids)) {
            return null;
        }

        List<TestPaperListToAppDto> result = ids.stream().map(n -> {
            TestPaper testPaper = testPaperEntityLoader.create(StringIdentifier.valueOf(n));
            if (ObjectUtils.isEmpty(testPaper)) {
                return null;
            }
            TestPaperListToAppDto testPaperListToAppDto = new TestPaperListToAppDto();
            testPaperListToAppDto.setTitle(testPaper.getTitle());
            testPaperListToAppDto.setCoverId(testPaper.getCoverId());
            testPaperListToAppDto.setId(testPaper.getId().toValue());
            //查询试卷完成情况
            if (ObjectUtils.isEmpty(familyMemberId)) {
                testPaperListToAppDto.setDone(false);
            } else {
                String testResultId = testResultQueryMapper.getTestResultIdByTestPaperId(n, familyMemberId);
                if (ObjectUtils.isEmpty(testResultId)) {
                    testPaperListToAppDto.setDone(false);
                } else {
                    testPaperListToAppDto.setDone(true);
                    testPaperListToAppDto.setTestResultId(testResultId);
                }
            }
            return testPaperListToAppDto;
        }).filter(n -> !ObjectUtils.isEmpty(n)).collect(Collectors.toList());
        return result;
    }

    @Override
    public TestPaperInfoToAppDto getTestPaperInfoToAppById(String id, String familyMemberId) {
        if (ObjectUtils.isEmpty(id)) {
            throw new BusinessException("试卷编号为空");
        }

        TestPaper testPaper = testPaperEntityLoader.create(StringIdentifier.valueOf(id));

        if (ObjectUtils.isEmpty(testPaper)) {
            throw new BusinessException("试卷不存在");
        }

        if (!testPaper.getRelease()) {
            throw new BusinessException("试卷未发布");
        }


        TestPaperInfoToAppDto result = new TestPaperInfoToAppDto();
        result.setId(id);
        result.setRemark(testPaper.getRemark());
        result.setTitle(testPaper.getTitle());

        //处理试题
        List<TestQuestionsToAppDto> question = testPaper.getQuestion().stream().map(n -> {
            TestQuestionsToAppDto testQuestionsDto = new TestQuestionsToAppDto();
            testQuestionsDto.setIndex(n.getIndex());
            testQuestionsDto.setTitle(n.getTitle());
            testQuestionsDto.setRequired(n.getRequired());
            testQuestionsDto.setRequired(n.getRequired());
            List<TestQuestionOptionDto> option = n.getOption().stream().map(m -> {
                TestQuestionOptionDto testQuestionOptionDto = new TestQuestionOptionDto();
                testQuestionOptionDto.setName(m.getName());
                testQuestionOptionDto.setValue(m.getValue());
                testQuestionOptionDto.setStartIndex(m.getStartIndex());
                testQuestionOptionDto.setEndIndex(m.getEndIndex());
                return testQuestionOptionDto;
            }).collect(Collectors.toList());
            testQuestionsDto.setOption(option);
            return testQuestionsDto;
        }).collect(Collectors.toList());

        result.setQuestion(question);

        //处理家庭成员答题结果
        if (ObjectUtils.isEmpty(familyMemberId)) {
            result.setTestResult(null);
        } else {
            String testResultId = testResultQueryMapper.getTestResultIdByTestPaperId(id, familyMemberId);
            if (ObjectUtils.isEmpty(testResultId)) {
                result.setTestResult(null);
            } else {
                TestResult testResult = testResultEntityLoader.create(StringIdentifier.valueOf(testResultId));
                if (ObjectUtils.isEmpty(testResult)) {
                    result.setTestResult(null);
                } else {
                    if (!ObjectUtils.isEmpty(testResult.getAnswer())) {
                        List<FamilyMemberTestResultDto> familyMemberTestResultList = testResult.getAnswer().stream().map(n -> {
                            FamilyMemberTestResultDto familyMemberTestResultDto = new FamilyMemberTestResultDto();
                            familyMemberTestResultDto.setIndex(n.getQuestionId());
                            familyMemberTestResultDto.setOptionName(n.getAnswer());
                            familyMemberTestResultDto.setPreviousIndex(n.getPreviousIndex());
                            return familyMemberTestResultDto;
                        }).collect(Collectors.toList());
                        result.setTestResult(familyMemberTestResultList);
                    }
                }
                result.setTestResultId(testResultId);
            }

        }
        return result;
    }


    @Override
    public void delTestPaper(String id) {
        if(ObjectUtils.isEmpty(id)){
            throw new BusinessException("试题编号为空");
        }

        TestPaper testPaper=testPaperEntityLoader.create(StringIdentifier.valueOf(id));
        if(ObjectUtils.isEmpty(testPaper)){
            throw new BusinessException("试题不存在");
        }
        //删除试题
        testPaper.delete();
    }

    @Override
    public void release(String id, String userId) {
        if(ObjectUtils.isEmpty(id)){
            throw new BusinessException("试题编号为空");
        }
        if(ObjectUtils.isEmpty(userId)){
            throw new BusinessException("操作员编号为空");
        }

        TestPaper testPaper=testPaperEntityLoader.create(StringIdentifier.valueOf(id));
        if(ObjectUtils.isEmpty(testPaper)){
            throw new BusinessException("试题不存在");
        }

        testPaper.release(userId);
    }

    @Override
    public void unReLease(String id, String userId) {
        if(ObjectUtils.isEmpty(id)){
            throw new BusinessException("试题编号为空");
        }
        if(ObjectUtils.isEmpty(userId)){
            throw new BusinessException("操作员编号为空");
        }

        TestPaper testPaper=testPaperEntityLoader.create(StringIdentifier.valueOf(id));
        if(ObjectUtils.isEmpty(testPaper)){
            throw new BusinessException("试题不存在");
        }
        testPaper.unRelease(userId);
    }

    @Override
    public List<TestPaperListToAppDto> listToApp(String familyMemberId, String typeId) {

        List<String> testPaperIdList=testPaperQueryMapper.allTestPaperByType(typeId,true);
        if(ObjectUtils.isEmpty(testPaperIdList)){
            return Collections.EMPTY_LIST;
        }
        return this.getTestPaperListToAppDto(testPaperIdList,familyMemberId);
    }

    @Override
    public List<TestPaperListToAppDto> getTestPaperListToAppDto(List<String> testPaperId, String familyMemberId) {
        if(ObjectUtils.isEmpty(testPaperId)){
            return Collections.EMPTY_LIST;
        }

        List<TestPaperListToAppDto> testPaperListToAppDtoList= testPaperId.stream().map(m->{
            TestPaper testPaper = testPaperEntityLoader.create(StringIdentifier.valueOf(m));
            if(ObjectUtils.isEmpty(testPaper)){
                return null;
            }
            TestPaperListToAppDto testPaperListToAppDto=new TestPaperListToAppDto();
            testPaperListToAppDto.setId(testPaper.getId().toValue());
            testPaperListToAppDto.setCoverId(testPaper.getCoverId());
            testPaperListToAppDto.setTitle(testPaper.getTitle());
            //处理用户答题情况
            if(ObjectUtils.isEmpty(familyMemberId)){
                testPaperListToAppDto.setDone(false);
                testPaperListToAppDto.setTestResultId(null);
            }else{
                //查询用户答题单
                String resultId=testResultQueryMapper.getTestResultIdByTestPaperId(m,familyMemberId);
                if(ObjectUtils.isEmpty(resultId)){
                    //如果没有答题单
                    testPaperListToAppDto.setDone(false);
                    testPaperListToAppDto.setTestResultId(null);
                }else{
                    //如果有答题单
                    TestResult testResult = testResultEntityLoader.create(StringIdentifier.valueOf(resultId));
                    if(ObjectUtils.isEmpty(testResult)){
                        testPaperListToAppDto.setDone(false);
                        testPaperListToAppDto.setTestResultId(null);
                    }else{
                        testPaperListToAppDto.setDone(true);
                        testPaperListToAppDto.setTestResultId(resultId);
                    }
                }
            }
            return testPaperListToAppDto;
        }).filter(m->!ObjectUtils.isEmpty(m)).collect(Collectors.toList());
        return testPaperListToAppDtoList;
    }
}
