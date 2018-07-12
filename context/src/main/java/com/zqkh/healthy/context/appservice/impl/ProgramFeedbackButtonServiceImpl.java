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
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramFeedbackButton;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramFeedbackType;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.ProgramFeedbackButtonQueryMapper;
import com.zqkh.healthy.context.appservice.inter.ProgramFeedbackButtonService;
import com.zqkh.healthy.context.util.RandomDataUtil;
import com.zqkh.healthy.feign.dto.program.app.ProgramFeedbackButtonListToAppDto;
import com.zqkh.healthy.feign.dto.program.feedback.ProgramFeedbackButtonInfoDto;
import com.zqkh.healthy.feign.dto.program.feedback.ProgramFeedbackButtonListDto;
import com.zqkh.healthy.feign.vo.program.ProgramFeedbackTypeEnum;
import com.zqkh.healthy.feign.vo.program.feedback.SaveProgramFeedbackVo;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 方案反馈按钮业务实现层
 *
 * @author 东来
 * @create 2018/7/4 0004
 */
@AppService
public class ProgramFeedbackButtonServiceImpl implements ProgramFeedbackButtonService {

    static Map<ProgramFeedbackType,List<String>> feedbackMessageMAP=new HashMap<>();

    static {
        //更有精神
        List<String> list=new ArrayList<>();
        list.add("再接再厉，创造奇迹！！");
        list.add("只有想不到，没有做不到！");
        list.add("不要让懒惰限制了你的健康！");
        list.add("念念不忘，必有回响！");
        list.add("开动脑子，迈开步子，耐住性子！");
        feedbackMessageMAP.put(ProgramFeedbackType.BETTER_STATE,list);

        //难以坚持
        List<String> list1=new ArrayList<>();
        list1.add("撸起袖子加油干！");
        list1.add("说得好不如唱得好，唱得好不如做得好！");
        list1.add("喊破嗓子不如甩开膀子！");
        list1.add("不试试怎么知道是否有效果呢？试一下又不收费，对吧，鬼脸状");
        list1.add("万里之行，始于足下，千万不要前功尽弃哦！");
        feedbackMessageMAP.put(ProgramFeedbackType.GIVE_UP,list1);

        //变化不大，但会坚持
        List<String> list2=new ArrayList<>();
        list2.add("有点想放弃，克服就胜利！");
        list2.add("21天就可以养成一个新习惯啦，再坚持下嘛！");
        list2.add("你的健康你做主，坚持就是胜利！");
        list2.add("为了理想生活，坚持就是胜利！");
        list2.add("为了健康生活，坚持就是胜利！");

        feedbackMessageMAP.put(ProgramFeedbackType.WANDER,list2);
    }
    @Resource
    private ProgramFeedbackButtonQueryMapper programFeedbackButtonQueryMapper;

    private EntityLoader<ProgramFeedbackButton> programFeedbackButtonEntityLoader=new RepositoryLoader<>(ProgramFeedbackButton.class);

    private ConstructLoader<ProgramFeedbackButton> programFeedbackButtonConstructLoader=new ConstructLoader<>(ProgramFeedbackButton.class);

    @Override
    public PageResult<ProgramFeedbackButtonListDto> search(ProgramFeedbackTypeEnum type,Integer pageIndex, Integer pageSize) {
        PageList<String> allList = programFeedbackButtonQueryMapper.search(type,PageParames.create(pageIndex, pageSize));
        PageResult<ProgramFeedbackButtonListDto> result=new PageResult<>();
        result.setTotalCount(allList.getTotalCount());
        result.setPageSize(allList.getPageSize());
        if(ObjectUtils.isEmpty(allList)){
            result.setList(Collections.emptyList());
        }else{
            List<ProgramFeedbackButtonListDto> collect = allList.stream().map(n -> {

                ProgramFeedbackButton programFeedbackButton = programFeedbackButtonEntityLoader.create(StringIdentifier.valueOf(n));
                if (ObjectUtils.isEmpty(programFeedbackButton)) {
                    return null;
                }
                ProgramFeedbackButtonListDto programFeedbackButtonListDto = ProgramFeedbackButtonListDto.builder()
                        .id(programFeedbackButton.getId().toValue())
                        .name(programFeedbackButton.getName())
                        .feedback(programFeedbackButton.getFeedback())
                        .build();

                return programFeedbackButtonListDto;
            }).filter(n -> !ObjectUtils.isEmpty(n)).collect(Collectors.toList());

            result.setList(collect);
        }
        return result;
    }

    @Override
    public void save(SaveProgramFeedbackVo saveProgramFeedbackVo) {
        if(ObjectUtils.isEmpty(saveProgramFeedbackVo)){
            throw new BusinessException("参数为空");
        }

        if(ObjectUtils.isEmpty(saveProgramFeedbackVo.getName())){
            throw new BusinessException("按钮名称为空");
        }

        if(ObjectUtils.isEmpty(saveProgramFeedbackVo.getTitle())){
            throw new BusinessException("副标题为空");
        }

        if(ObjectUtils.isEmpty(saveProgramFeedbackVo.getFeedback())){
            throw new BusinessException("方案反馈内容为空");
        }

        if(ObjectUtils.isEmpty(saveProgramFeedbackVo.getUserId())){
            throw new BusinessException("操作人为空");
        }

        if(ObjectUtils.isEmpty(saveProgramFeedbackVo.getType())){
            throw new BusinessException("类型为空");
        }

        //验证名称是否可用
        boolean flg=programFeedbackButtonQueryMapper.validateName(saveProgramFeedbackVo.getName(),saveProgramFeedbackVo.getId());
        if(!flg){
            throw new BusinessException("按钮名称已存在");
        }
        if(ObjectUtils.isEmpty(saveProgramFeedbackVo.getId())){
            ProgramFeedbackButton programFeedbackButton = programFeedbackButtonConstructLoader.create(IdGenerator.getInstance().generate(ProgramFeedbackButton.class));
            programFeedbackButton.init(saveProgramFeedbackVo.getName(),saveProgramFeedbackVo.getFeedback(), ProgramFeedbackType.getType(saveProgramFeedbackVo.getType().name()),saveProgramFeedbackVo.getTitle(),saveProgramFeedbackVo.getUserId());
        }else{
            ProgramFeedbackButton programFeedbackButton = programFeedbackButtonEntityLoader.create(StringIdentifier.valueOf(saveProgramFeedbackVo.getId()));
            if(ObjectUtils.isEmpty(programFeedbackButton)){
                throw new BusinessException("方案反馈按钮不存在");
            }
            programFeedbackButton.edit(saveProgramFeedbackVo.getName(),saveProgramFeedbackVo.getFeedback(), ProgramFeedbackType.getType(saveProgramFeedbackVo.getType().name()),saveProgramFeedbackVo.getTitle(),saveProgramFeedbackVo.getUserId());
        }

    }

    @Override
    public ProgramFeedbackButtonInfoDto info(String id) {
        if(ObjectUtils.isEmpty(id)){
            throw new BusinessException("编号为空");
        }

        ProgramFeedbackButton programFeedbackButton = programFeedbackButtonEntityLoader.create(StringIdentifier.valueOf(id));
        if(ObjectUtils.isEmpty(programFeedbackButton)){
            throw new BusinessException("方案反馈按钮不存在");
        }

        ProgramFeedbackButtonInfoDto info=ProgramFeedbackButtonInfoDto.builder()
                .id(id)
                .name(programFeedbackButton.getName())
                .feedback(programFeedbackButton.getFeedback())
                .type(ObjectUtils.isEmpty(programFeedbackButton.getType())?null:ProgramFeedbackTypeEnum.getType(programFeedbackButton.getType().name()))
                .title(programFeedbackButton.getTitle())
                .build();
        return info;
    }

    @Override
    public void del(String id) {
        if(ObjectUtils.isEmpty(id)){
            throw new BusinessException("编号为空");
        }

        ProgramFeedbackButton programFeedbackButton = programFeedbackButtonEntityLoader.create(StringIdentifier.valueOf(id));
        if(ObjectUtils.isEmpty(programFeedbackButton)){
            throw new BusinessException("方案反馈按钮不存在");
        }

        programFeedbackButton.delete();
    }

    /**
     * 获取反馈文案
     * @param type
     * @return
     */
    public static String getFeedbackMessage(ProgramFeedbackType type){
        List<String> message=feedbackMessageMAP.get(type);
        int index= RandomDataUtil.interceptPosition(message.size(),1);
        return message.get(index);
    }

    @Override
    public List<ProgramFeedbackButtonListToAppDto> appButtonList() {

        List<String> list = programFeedbackButtonQueryMapper.appButtonList();

        if(ObjectUtils.isEmpty(list)){
            List<ProgramFeedbackButtonListToAppDto> result=new ArrayList<>();
            ProgramFeedbackButtonListToAppDto buttonListToAppDto1 = ProgramFeedbackButtonListToAppDto.builder()
                    .name("难以坚持,需要鼓励")
                    .title("感觉不能坚持下去")
                    .feedback(getFeedbackMessage(ProgramFeedbackType.GIVE_UP))
                    .type(ProgramFeedbackTypeEnum.GIVE_UP)
                    .build();
            result.add(buttonListToAppDto1);

            ProgramFeedbackButtonListToAppDto buttonListToAppDto2 = ProgramFeedbackButtonListToAppDto.builder()
                    .name("变化不大,但会坚持")
                    .title("有一点变化,坚持会更好")
                    .feedback(getFeedbackMessage(ProgramFeedbackType.WANDER))
                    .type(ProgramFeedbackTypeEnum.WANDER)
                    .build();
            result.add(buttonListToAppDto2);

            ProgramFeedbackButtonListToAppDto buttonListToAppDto3 = ProgramFeedbackButtonListToAppDto.builder()
                    .name("状态更好了")
                    .title("身体感觉能量满满")
                    .feedback(getFeedbackMessage(ProgramFeedbackType.BETTER_STATE))
                    .type(ProgramFeedbackTypeEnum.BETTER_STATE)
                    .build();
            result.add(buttonListToAppDto3);

            return result;
        }

        List<ProgramFeedbackButtonListToAppDto> collect = list.stream().map(n -> {
            ProgramFeedbackButton programFeedbackButton = programFeedbackButtonEntityLoader.create(StringIdentifier.valueOf(n));
            if (ObjectUtils.isEmpty(programFeedbackButton)) {
                return null;
            }
            int index = RandomDataUtil.interceptPosition(programFeedbackButton.getFeedback().size(), 1);

            ProgramFeedbackButtonListToAppDto dto = ProgramFeedbackButtonListToAppDto.builder()
                    .name(programFeedbackButton.getName())
                    .feedback(programFeedbackButton.getFeedback().get(index))
                    .type(ProgramFeedbackTypeEnum.getType(programFeedbackButton.getType().name()))
                    .title(programFeedbackButton.getTitle())
                    .build();
            return dto;
        }).filter(n -> !ObjectUtils.isEmpty(n)).collect(Collectors.toList());
        return collect;
    }
}
