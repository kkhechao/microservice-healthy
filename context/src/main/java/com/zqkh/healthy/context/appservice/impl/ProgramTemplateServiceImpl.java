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
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramTaskTemplate;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramTemplate;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.ProgramQueryMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.ProgramTaskQueryMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.ProgramTemplateQueryMapper;
import com.zqkh.healthy.context.appservice.inter.ProgramTemplateService;
import com.zqkh.healthy.feign.dto.program.template.ProgramTaskDto;
import com.zqkh.healthy.feign.dto.program.template.ProgramTemplateInfoDto;
import com.zqkh.healthy.feign.dto.program.template.ProgramTemplateListDto;
import com.zqkh.healthy.feign.vo.program.template.SaveProgramTemplateVo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 方案模板实现接口
 *
 * @author 东来
 * @create 2018/6/4 0004
 */
@AppService
public class ProgramTemplateServiceImpl implements ProgramTemplateService {

    @Resource
    private ProgramTemplateQueryMapper programTemplateQueryMapper;

    @Resource
    private ProgramQueryMapper programQueryMapper;

    @Resource
    private ProgramTaskQueryMapper programTaskQueryMapper;

    private EntityLoader<ProgramTemplate> programTemplateEntityLoader=new RepositoryLoader<>(ProgramTemplate.class);

    private ConstructLoader<ProgramTemplate> programTemplateConstructLoader = new ConstructLoader<>(ProgramTemplate.class);

    @Override
    public PageResult<ProgramTemplateListDto> search(String key,Boolean enable, Integer pageIndex, Integer pageSize) {

        PageList<String> ids=programTemplateQueryMapper.search(key,enable, PageParames.create(pageIndex,pageSize));

        PageResult<ProgramTemplateListDto> result=new PageResult<>();
        result.setTotalCount(ids.getTotalCount());
        result.setPageSize(ids.getPageSize());
        if(ObjectUtils.isEmpty(ids)){
            result.setList(Collections.EMPTY_LIST);
            return result;
        }

        List<ProgramTemplateListDto> list= ids.stream().map(n->{
            ProgramTemplate programTemplate=programTemplateEntityLoader.create(StringIdentifier.valueOf(n));
            if(ObjectUtils.isEmpty(programTemplate)){
                return null;
            }
            ProgramTemplateListDto programTemplateListDto=new ProgramTemplateListDto();
            programTemplateListDto.setEnable(programTemplate.getEnable());
            programTemplateListDto.setId(programTemplate.getId().toValue());
            programTemplateListDto.setName(programTemplate.getName());
            Long use=programQueryMapper.countByProgramTemplateId(n);
            programTemplateListDto.setUse(ObjectUtils.isEmpty(use)||use<=0?false:true);
            return programTemplateListDto;
        }).filter(n->!ObjectUtils.isEmpty(n)).collect(Collectors.toList());
        result.setList(list);
        return result;
    }

    @Override
    public void save(SaveProgramTemplateVo saveProgramTemplateVo) {
        if(ObjectUtils.isEmpty(saveProgramTemplateVo)){
            throw new BusinessException("参数为空");
        }
        if(ObjectUtils.isEmpty(saveProgramTemplateVo.getName())){
            throw new BusinessException("方案名称为空");
        }
        if(ObjectUtils.isEmpty(saveProgramTemplateVo.getSharingImg())){
            throw new BusinessException("分享图片为空");
        }
        if(ObjectUtils.isEmpty(saveProgramTemplateVo.getUserId())){
            throw new BusinessException("操作者为空");
        }
        if(ObjectUtils.isEmpty(saveProgramTemplateVo.getTask())){
            throw new BusinessException("任务为空");
        }

        //处理任务
        List<ProgramTaskTemplate> task=saveProgramTemplateVo.getTask().stream().map(n->{
            ProgramTaskTemplate programTaskTemplate=new ProgramTaskTemplate();
            programTaskTemplate.setDay(n.getDay().stream().filter(m->!ObjectUtils.isEmpty(m)).collect(Collectors.toList()));
            programTaskTemplate.setDesc(n.getDesc());
            programTaskTemplate.setExplain(n.getExplain());
            return programTaskTemplate;
        }).collect(Collectors.toList());

        if(!ObjectUtils.isEmpty(saveProgramTemplateVo.getId())){
            ProgramTemplate programTemplate=programTemplateEntityLoader.create(StringIdentifier.valueOf(saveProgramTemplateVo.getId()));
            if(ObjectUtils.isEmpty(programTemplate)){
                throw new BusinessException("方案不存在");
            }
            programTemplate.edit(saveProgramTemplateVo.getName(),saveProgramTemplateVo.getRemind(),saveProgramTemplateVo.getSharingImg(),task,saveProgramTemplateVo.getUserId());
        }else{
            ProgramTemplate programTemplate=programTemplateConstructLoader.create(IdGenerator.getInstance().generate(ProgramTemplate.class));
            programTemplate.init(saveProgramTemplateVo.getName(),saveProgramTemplateVo.getRemind(),saveProgramTemplateVo.getSharingImg(),task,saveProgramTemplateVo.getUserId());
        }
    }

    @Override
    public void editEnable(String id, boolean enable) {
        if(ObjectUtils.isEmpty(id)){
            throw new BusinessException("方案编号为空");
        }

        ProgramTemplate programTemplate=programTemplateEntityLoader.create(StringIdentifier.valueOf(id));
        if(ObjectUtils.isEmpty(programTemplate)){
            throw new BusinessException("方案不存在");
        }
        programTemplate.editEnable(enable);
    }

    @Override
    public ProgramTemplateInfoDto info(String id) {
        if(ObjectUtils.isEmpty(id)){
            throw new BusinessException("方案编号为空");
        }
        ProgramTemplate programTemplate=programTemplateEntityLoader.create(StringIdentifier.valueOf(id));
        if(ObjectUtils.isEmpty(programTemplate)){
            throw new BusinessException("方案不存在");
        }

        ProgramTemplateInfoDto programTemplateInfoDto=new ProgramTemplateInfoDto();
        programTemplateInfoDto.setId(programTemplate.getId().toValue());
        programTemplateInfoDto.setName(programTemplate.getName());
        programTemplateInfoDto.setRemind(programTemplate.getRemind());
        programTemplateInfoDto.setSharingImg(programTemplate.getSharingImg());
        if(!ObjectUtils.isEmpty(programTemplate.getTask())){
           List<ProgramTaskDto> task= programTemplate.getTask().stream().map(n->{
                ProgramTaskDto programTaskDto=new ProgramTaskDto();
                BeanUtils.copyProperties(n,programTaskDto);
                return programTaskDto;
            }).collect(Collectors.toList());
           programTemplateInfoDto.setTask(task);
        }
        return programTemplateInfoDto;
    }
}
