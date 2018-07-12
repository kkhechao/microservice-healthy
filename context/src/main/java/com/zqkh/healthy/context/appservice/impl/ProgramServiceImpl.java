package com.zqkh.healthy.context.appservice.impl;

import com.jovezhao.nest.PageList;
import com.jovezhao.nest.ddd.Identifier;
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
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.ProgramQueryMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.ProgramResultQueryMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.ProgramTaskQueryMapper;
import com.zqkh.healthy.context.appservice.impl.domain.service.ProgramDomainService;
import com.zqkh.healthy.context.appservice.impl.domain.service.ProgramResultDomainService;
import com.zqkh.healthy.context.appservice.impl.domain.service.ProgramTaskDomainService;
import com.zqkh.healthy.context.appservice.inter.ProgramService;
import com.zqkh.healthy.context.domain.program.InitProgramResultVo;
import com.zqkh.healthy.context.domain.program.InitProgramTaskVo;
import com.zqkh.healthy.context.domain.program.InitProgramVo;
import com.zqkh.healthy.context.util.RandomDataUtil;
import com.zqkh.healthy.feign.dto.program.app.ProgramInfoDto;
import com.zqkh.healthy.feign.dto.program.app.ProgramListDto;
import com.zqkh.healthy.feign.dto.program.app.ProgramResultInfoDto;
import com.zqkh.healthy.feign.dto.program.app.ProgramTaskListDto;
import com.zqkh.healthy.feign.vo.program.ProgramFeedbackVo;
import com.zqkh.healthy.feign.vo.program.ProgramSrcTypeEnum;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 解决方案业务实现层
 *
 * @author 东来
 * @create 2018/6/6 0006
 */
@AppService
public class ProgramServiceImpl implements ProgramService {

    private static final String DATE_FORMAT="yyyy-MM-dd";


    @Resource
    private ProgramQueryMapper programQueryMapper;
    @Resource
    private ProgramTaskQueryMapper programTaskQueryMapper;
    @Resource
    private ProgramResultQueryMapper programResultQueryMapper;
    private EntityLoader<Program> programEntityLoader=new RepositoryLoader<>(Program.class);
    private EntityLoader<ProgramTask> programTaskEntityLoader=new RepositoryLoader<>(ProgramTask.class);
    private EntityLoader<TestResult> testResultEntityLoader=new RepositoryLoader<>(TestResult.class);

    @Resource
    private ProgramResultDomainService programResultDomainService;
    @Resource
    private ProgramDomainService programDomainService;
    @Resource
    private ProgramTaskDomainService programTaskDomainService;



    @Override
    public PageResult<ProgramListDto> search(String familyMemberId, String key, Boolean done, Boolean dayDone, ProgramSrcTypeEnum programSrcType, String src, Integer pageIndex, Integer pageSize) {

        PageResult<ProgramListDto> result=new PageResult<ProgramListDto>();

        List<String> programIds=null;
        //查询今日完成情况
        if(!ObjectUtils.isEmpty(dayDone)){
            programIds=programQueryMapper.getIdByDayDoneAndFamilyMemberId(familyMemberId,dayDone);
        }

        //主体查询
        PageList<String> list=programQueryMapper.search(key,familyMemberId,done,
                programIds,ObjectUtils.isEmpty(programSrcType)?null:Program.SrcType.getSrcType(programSrcType.name()),src,
                PageParames.create(pageIndex,pageSize));

        result.setTotalCount(list.getTotalCount());
        result.setPageSize(list.getPageSize());

        if(ObjectUtils.isEmpty(list)){
            result.setList(Collections.emptyList());
            return result;
        }
        List<ProgramListDto> programListDtoList=list.stream().map(n->{
            Program program= programEntityLoader.create(StringIdentifier.valueOf(n));
            if(ObjectUtils.isEmpty(program)){
                return null;
            }
            ProgramListDto programListDto=new ProgramListDto();
            programListDto.setId(program.getId().toValue());
            programListDto.setStatus(ProgramListDto.Status.getStatus(program.getStatus().name()));
            ProgramTemplate programTemplate=program.getProgramTemplate();
            programListDto.setName(programTemplate.getName());

            if(program.getStatus().equals(Program.Status.RECEIVE)){
                return programListDto;
            }

            if(program.getStatus().equals(Program.Status.END)){
                //查询方案结果编号
                programListDto.setProgramResultId(programResultQueryMapper.getNewIdByProgramId(n));
            }
            //查询坚持天数,需要排除当天
            Long insistDay=programTaskQueryMapper.insistDay(n);
            programListDto.setInsistDay(ObjectUtils.isEmpty(insistDay)?0:insistDay);
            List<String> taskIdList=programTaskQueryMapper.getIdByProgramId(n);
            if(ObjectUtils.isEmpty(taskIdList)){
                programListDto.setDayDone(false);
            }else{
                Set<Date> day=new HashSet<>();
                //查询今日完成情况
                taskIdList.forEach(m->{
                    ProgramTask programTask = programTaskEntityLoader.create(StringIdentifier.valueOf(m));
                    if(!ObjectUtils.isEmpty(programTask)){
                        if(DateFormatUtils.format(new Date(),DATE_FORMAT).equals(DateFormatUtils.format(programTask.getTime(),DATE_FORMAT))){
                            programListDto.setDayDone(programTask.getDone());
                        }
                        day.add(programTask.getTime());
                    }
                });
            }
            return programListDto;
        }).filter(n->!ObjectUtils.isEmpty(n)).collect(Collectors.toList());
        result.setList(programListDtoList);
        return result;
    }

    @Override
    public ProgramInfoDto info(String id) {
        if(ObjectUtils.isEmpty(id)){
            throw new BusinessException("方案编号为空");
        }

        Program program = programEntityLoader.create(StringIdentifier.valueOf(id));
        if(ObjectUtils.isEmpty(program)){
            throw new BusinessException("方案不存在");
        }

        ProgramInfoDto programInfoDto = new ProgramInfoDto();
        programInfoDto.setId(program.getId().toValue());
        programInfoDto.setName(program.getProgramTemplate().getName());
        programInfoDto.setStatus(ProgramInfoDto.Status.getStatus(program.getStatus().name()));
        if(program.getSrcType()==Program.SrcType.TEST_PAPER_RESULT) {
            TestResult testResult = testResultEntityLoader.create(StringIdentifier.valueOf(program.getSrc()));
            programInfoDto.setTestPaperTitle(testResult.getTestPaper().getTitle());

        }



        //总共天数
        Set<String> totalDaySet=new HashSet<>();
        //如果方案为接收状态
        if(program.getStatus().equals(Program.Status.RECEIVE)){
            //总天数
         program.getProgramTemplate().getTask().forEach(m -> {
                     if (!ObjectUtils.isEmpty(m.getDay())) {
                         m.getDay().forEach(n->{
                             if(!ObjectUtils.isEmpty(n)){
                                 totalDaySet.add(n.toString());
                             }
                         });
                     }
                 });
            programInfoDto.setTotalDay(totalDaySet.size());
            return programInfoDto;
        }

        //已坚持天数
        Set<String> insistDaySet=new HashSet<>();


        List<String> taskIds=programTaskQueryMapper.getIdByProgramId(program.getId().toValue());

        List<ProgramTaskListDto> task=taskIds.stream().map(n->{
            ProgramTask programTask = programTaskEntityLoader.create(StringIdentifier.valueOf(n));
            ProgramTaskListDto programTaskListDto=new ProgramTaskListDto();
            programTaskListDto.setId(programTask.getId().toValue());
            programTaskListDto.setDesc(programTask.getDesc());
            programTaskListDto.setTime(programTask.getTime());
            programTaskListDto.setDone(programTask.getDone());
            programTaskListDto.setName(programTask.getName());
            totalDaySet.add(DateFormatUtils.format(programTask.getTime(),DATE_FORMAT));
            if(programTask.getDone()){
                if(!DateFormatUtils.format(new Date(),DATE_FORMAT).equals(DateFormatUtils.format(programTask.getTime(),DATE_FORMAT))){
                    insistDaySet.add(DateFormatUtils.format(programTask.getTime(),DATE_FORMAT));
                }
            }
            return programTaskListDto;
        }).collect(Collectors.toList());
        programInfoDto.setTask(task);
        programInfoDto.setTotalDay(totalDaySet.size());
        programInfoDto.setInsistDay(insistDaySet.size());

        //查询方案结果编号
        programInfoDto.setProgramResultId( programResultQueryMapper.getNewIdByProgramId(id));
        return programInfoDto;
    }

    @Override
    public String feedback(ProgramFeedbackVo feedback) {
        if(ObjectUtils.isEmpty(feedback)){
            throw new BusinessException("参数为空");
        }
        if(ObjectUtils.isEmpty(feedback.getFamilyMemberId())){
            throw new BusinessException("家庭成员编号为空");
        }
        if(ObjectUtils.isEmpty(feedback.getProgramId())){
            throw new BusinessException("方案编号为空");
        }
        if(ObjectUtils.isEmpty(feedback.getType())){
            throw new BusinessException("反馈类型为空");
        }
        if(ObjectUtils.isEmpty(feedback.getTime())){
            throw new BusinessException("反馈时间为空");
        }

       Program program= programEntityLoader.create(StringIdentifier.valueOf(feedback.getProgramId()));
       if(ObjectUtils.isEmpty(program)){
           throw new BusinessException("方案不存在");
       }
       if(!feedback.getFamilyMemberId().equals(program.getFamilyMember())){
           throw new BusinessException("不可代他人提交");
       }

        ProgramFeedback programFeedback=new ProgramFeedback();
        programFeedback.setTime(feedback.getTime());
        programFeedback.setTye(ProgramFeedbackType.getType(feedback.getType().name()));
        program.feedbackMessage(programFeedback);
        return ProgramFeedbackButtonServiceImpl.getFeedbackMessage(ProgramFeedbackType.getType(feedback.getType().name()));
    }

    @Override
    public ProgramResultInfoDto endProgram(String id, String familyMemberId) {
        if(ObjectUtils.isEmpty(id)){
            throw new BusinessException("方案编号为空");
        }
        if(ObjectUtils.isEmpty(familyMemberId)){
            throw new BusinessException("家庭成员编号为空");
        }

        Program program= programEntityLoader.create(StringIdentifier.valueOf(id));
        if(ObjectUtils.isEmpty(program)){
            throw new BusinessException("方案不存在");
        }
        if(!familyMemberId.equals(program.getFamilyMember())){
            throw new BusinessException("不可代他人提交");
        }
        program.end();



        //放弃次数
        List<ProgramFeedbackType> giveUpNum= new ArrayList<>();

        //徘徊次数
        List<ProgramFeedbackType> wanderNum= new ArrayList<>();
        //
        if(!ObjectUtils.isEmpty(program.getFeedback())){
            program.getFeedback().forEach(n->{

                switch (n.getTye()){
                    case WANDER:
                        wanderNum.add(n.getTye());
                        break;
                    case GIVE_UP:
                        giveUpNum.add(n.getTye());
                        break;
                    case BETTER_STATE:
                        break;
                    default:
                        break;
                }
            });
        }
        InitProgramResultVo initProgramResultVo=new InitProgramResultVo();
        initProgramResultVo.setFamilyMember(familyMemberId);
        initProgramResultVo.setProgram(program);
        initProgramResultVo.setEndTime(new Date());
        initProgramResultVo.setStartTime(program.getOpenTime());
        //完成任务次数
        Long finishTaskNum=programTaskQueryMapper.finishTaskNum(id);
        initProgramResultVo.setFinishNum(ObjectUtils.isEmpty(finishTaskNum)?0:finishTaskNum);
        //放弃次数
        initProgramResultVo.setGiveUpNum(giveUpNum.size());
        //完成持续天数
        Long insistDay=programTaskQueryMapper.insistDay(id);
        initProgramResultVo.setInsistDay(ObjectUtils.isEmpty(insistDay)?0:insistDay);
        //徘徊次数
        initProgramResultVo.setWanderNum(wanderNum.size());

        Long total=programQueryMapper.countByProgramTemplateId(program.getProgramTemplateId());
        initProgramResultVo.setTotal(ObjectUtils.isEmpty(total)?0:total);


        Long transcend=programQueryMapper.countNotEnd(program.getProgramTemplateId());
        initProgramResultVo.setTranscend(ObjectUtils.isEmpty(transcend)?0:transcend);

        //生成方案结果
        ProgramResult programResult = programResultDomainService.init(initProgramResultVo);
        ProgramResultInfoDto programResultInfoDto=new ProgramResultInfoDto();
        programResultInfoDto.setId(programResult.getId().toValue());
        programResultInfoDto.setEndTime(programResult.getEndTime());
        programResultInfoDto.setStartTime(programResult.getStartTime());
        programResultInfoDto.setFinishNum(programResult.getFinishNum());
        programResultInfoDto.setGiveUpNum(programResult.getGiveUpNum());
        programResultInfoDto.setWanderNum(programResult.getWanderNum());
        programResultInfoDto.setInsistDay(programResult.getInsistDay());
        programResultInfoDto.setTotal(programResult.getTotal());
        programResultInfoDto.setTranscend(programResult.getTranscend());


        switch (program.getSrcType()){
            case TEST_PAPER_RESULT:
                TestResult testResult = testResultEntityLoader.create(StringIdentifier.valueOf(program.getSrc()));
                programResultInfoDto.setTestPaperId(testResult.getTestPaperId());
                programResultInfoDto.setTestPaperTitle(testResult.getTestPaper().getTitle());
                break;
            default:
                break;
        }
        return programResultInfoDto;
    }

    @Override
    public void open(String id, String familyMemberId) {
        if(ObjectUtils.isEmpty(id)){
            throw new BusinessException("方案编号为空");
        }
        if(ObjectUtils.isEmpty(familyMemberId)){
            throw new BusinessException("家庭成员编号为空");
        }

        Program program= programEntityLoader.create(StringIdentifier.valueOf(id));
        if(ObjectUtils.isEmpty(program)){
            throw new BusinessException("方案不存在");
        }
        if(!familyMemberId.equals(program.getFamilyMember())){
            throw new BusinessException("不可代他人开启");
        }
        program.open();

        //对原有方案进行删除
        List<String> taskId = programTaskQueryMapper.getIdByProgramId(program.getId().toValue());
        if(!ObjectUtils.isEmpty(taskId)){
            taskId.forEach(n->{
                ProgramTask programTask = programTaskEntityLoader.create(StringIdentifier.valueOf(n));
                programTask.delete();
            });
        }
        program.getProgramTemplate().getTask().forEach(n->{
            InitProgramTaskVo initProgramTaskVo=new InitProgramTaskVo();
            initProgramTaskVo.setDesc(n.getDesc());
            initProgramTaskVo.setName(n.getExplain());
            initProgramTaskVo.setProgram(program.getId().toValue());
            initProgramTaskVo.setFamilyMember(familyMemberId);
            n.getDay().forEach(m->{
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(new Date());
                rightNow.add(Calendar.DAY_OF_YEAR,(m-1));
                initProgramTaskVo.setTime(rightNow.getTime());
                programTaskDomainService.initProgramTask(initProgramTaskVo);
            });
        });
    }


    @Override
    public String copy(String id,String familyMemberId) {
        if(ObjectUtils.isEmpty(id)){
            throw new BusinessException("方案编号为空");
        }
        if(ObjectUtils.isEmpty(familyMemberId)){
            throw new BusinessException("家庭成员编号为空");
        }
        Program program= programEntityLoader.create(StringIdentifier.valueOf(id));
        if(ObjectUtils.isEmpty(program)){
            throw new BusinessException("方案不存在");
        }
        if(!familyMemberId.equals(program.getFamilyMember())){
            throw new BusinessException("不可拷贝他人方案");
        }

        Identifier generate = IdGenerator.getInstance().generate(Program.class);

        //进行方案拷贝
        InitProgramVo initProgramVo=InitProgramVo.builder().familyMember(program.getFamilyMember())
                .identifier(generate)
                .programTemplate(program.getProgramTemplate())
                .srcType(program.getSrcType())
                .srcDesc(program.getSrcDesc())
                .src(program.getSrc())
                .build();
        programDomainService.init(initProgramVo);
        return generate.toValue();
    }


}
