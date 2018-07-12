package com.zqkh.healthy.context.appservice.impl.domain.service;

import com.jovezhao.nest.ddd.StringIdentifier;
import com.jovezhao.nest.ddd.builder.ConstructLoader;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.RepositoryLoader;
import com.jovezhao.nest.ddd.identifier.IdGenerator;
import com.zqkh.common.exception.BusinessException;
import com.zqkh.healthy.context.appservice.impl.domain.repository.Program;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramTemplate;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.ProgramQueryMapper;
import com.zqkh.healthy.context.domain.program.InitProgramTaskVo;
import com.zqkh.healthy.context.domain.program.InitProgramVo;
import com.zqkh.healthy.context.util.RandomDataUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 东来
 * @create 2018/6/5 0005
 */
@Service
public class ProgramDomainService {


    private ConstructLoader<Program> programConstructLoader=new ConstructLoader<>(Program.class);

    private EntityLoader<Program> programEntityLoader=new RepositoryLoader<>(Program.class);

    private EntityLoader<ProgramTemplate> programTemplateEntityLoader=new RepositoryLoader<>(ProgramTemplate.class);


    @Resource
    private ProgramTaskDomainService programTaskDomainService;

    @Resource
    private ProgramQueryMapper programQueryMapper;

    /**
     * 初始化方案
     * @param initProgramVo
     */
    public Program init(InitProgramVo initProgramVo){
        if(ObjectUtils.isEmpty(initProgramVo)){
            throw new BusinessException("初始化方案参数为空");
        }
        if(ObjectUtils.isEmpty(initProgramVo.getFamilyMember())){
            throw new BusinessException("初始化方案家庭成员为空");
        }
        if(ObjectUtils.isEmpty(initProgramVo.getProgramTemplate())){
            throw new BusinessException("初始化方案方案模板为空");
        }
        Program program = programConstructLoader.create(ObjectUtils.isEmpty(initProgramVo.getIdentifier())?IdGenerator.getInstance().generate(Program.class):initProgramVo.getIdentifier());
        int index=RandomDataUtil.interceptPosition(initProgramVo.getProgramTemplate().getSharingImg().size(),1);

        program.init(initProgramVo.getFamilyMember(),initProgramVo.getSrcType(),initProgramVo.getSrc(),initProgramVo.getSrcDesc(),initProgramVo.getProgramTemplate(),initProgramVo.getProgramTemplate().getSharingImg().get(index));

        return program;
    };

    /**
     * 删除
     * @param srcType
     * @param src
     */
    public void remove( Program.SrcType srcType,String src,String srcDesc,Program.Status... status){
        if(ObjectUtils.isEmpty(srcType)){
            throw new BusinessException("来源类型为空");
        }
        if(ObjectUtils.isEmpty(src)){
            throw new BusinessException("来源为空");
        }

        List<String> id=programQueryMapper.getIdBySrcTypeAndSrc(srcType,src,srcDesc,ObjectUtils.isEmpty(status)?null: Arrays.stream(status).collect(Collectors.toList()));
        if(ObjectUtils.isEmpty(id)){
            return;
        }

        id.forEach(n->{
            Program program=programEntityLoader.create(StringIdentifier.valueOf(n));
            if(!ObjectUtils.isEmpty(program)){
                program.delete();
                programTaskDomainService.removeByProgramId(program.getId().toValue());

            }
        });





    }
}
