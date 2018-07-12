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
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestPaperTypeTemplate;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.TestPaperTypeQueryMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.TestPaperTypeTemplateQueryMapper;
import com.zqkh.healthy.context.appservice.inter.TestPaperTypeTemplateService;
import com.zqkh.healthy.feign.dto.paper.type.template.SearchTestPaperTypeTemplateListDto;
import com.zqkh.healthy.feign.dto.paper.type.template.TestPaperTypeTemplateInfoDto;
import com.zqkh.healthy.feign.vo.paper.type.template.SaveTestPaperTypeTemplateVo;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 测题类型前端显示模板实现层
 *
 * @author 东来
 * @create 2018/6/29 0029
 */
@AppService
public class TestPaperTypeTemplateServiceImpl implements TestPaperTypeTemplateService {

    @Resource
    private TestPaperTypeTemplateQueryMapper testPaperTypeTemplateQueryMapper;

    @Resource
    private TestPaperTypeQueryMapper testPaperTypeQueryMapper;

    private EntityLoader<TestPaperTypeTemplate> testPaperTypeTemplateEntityLoader=new RepositoryLoader<>(TestPaperTypeTemplate.class);

    private ConstructLoader<TestPaperTypeTemplate> testPaperTypeTemplateConstructLoader=new ConstructLoader<>(TestPaperTypeTemplate.class);

    @Override
    public PageResult<SearchTestPaperTypeTemplateListDto> search(Integer pageIndex, Integer pageSize) {

        PageList<String> list = testPaperTypeTemplateQueryMapper.search(PageParames.create(pageIndex, pageSize));

        PageResult pageResult=new PageResult();
        pageResult.setTotalCount(list.getTotalCount());
        pageResult.setPageSize(list.getPageSize());

        if(ObjectUtils.isEmpty(list.getList())){
            pageResult.setList(Collections.emptyList());
            return pageResult;
        }

        List<SearchTestPaperTypeTemplateListDto> collect = list.getList().stream().map(n -> {
            TestPaperTypeTemplate testPaperTypeTemplate = testPaperTypeTemplateEntityLoader.create(StringIdentifier.valueOf(n));

            if (ObjectUtils.isEmpty(testPaperTypeTemplate)) {
                return null;
            }

            SearchTestPaperTypeTemplateListDto searchTestPaperTypeTemplateListDto = SearchTestPaperTypeTemplateListDto.builder()
                    .id(testPaperTypeTemplate.getId().toValue())
                    .name(testPaperTypeTemplate.getName())
                    .num(testPaperTypeQueryMapper.countByTemplateId(n))
                    .build();
            return searchTestPaperTypeTemplateListDto;

        }).filter(n -> !ObjectUtils.isEmpty(n)).collect(Collectors.toList());

        pageResult.setList(collect);
        return pageResult;
    }

    @Override
    public void save(SaveTestPaperTypeTemplateVo saveTestPaperTypeTemplateVo) {
        if(ObjectUtils.isEmpty(saveTestPaperTypeTemplateVo)){
            throw new BusinessException("参数为空");
        }

        if(ObjectUtils.isEmpty(saveTestPaperTypeTemplateVo.getName())){
            throw new BusinessException("模板名称为空");
        }
        if(ObjectUtils.isEmpty(saveTestPaperTypeTemplateVo.getUserId())){
            throw new BusinessException("操作者为空");
        }

        //验证名称是否可用
        boolean flg = testPaperTypeTemplateQueryMapper.validationName(saveTestPaperTypeTemplateVo.getName(), saveTestPaperTypeTemplateVo.getId());
        if(!flg){
            throw new BusinessException("模板名称已存在");
        }

        if(ObjectUtils.isEmpty(saveTestPaperTypeTemplateVo.getId())){
            TestPaperTypeTemplate testPaperTypeTemplate = testPaperTypeTemplateConstructLoader.create(IdGenerator.getInstance().generate(TestPaperTypeTemplate.class));
            testPaperTypeTemplate.init(saveTestPaperTypeTemplateVo.getName(),saveTestPaperTypeTemplateVo.getUserId());
        }else{
            TestPaperTypeTemplate testPaperTypeTemplate = testPaperTypeTemplateEntityLoader.create(StringIdentifier.valueOf(saveTestPaperTypeTemplateVo.getId()));
            if(ObjectUtils.isEmpty(testPaperTypeTemplate)){
                throw new BusinessException("模板不存在");
            }
            testPaperTypeTemplate.edit(saveTestPaperTypeTemplateVo.getName(),saveTestPaperTypeTemplateVo.getUserId());
        }


    }

    @Override
    public void del(String id) {
        if(ObjectUtils.isEmpty(id)){
            throw new BusinessException("模板编号没有传入");
        }

        TestPaperTypeTemplate testPaperTypeTemplate = testPaperTypeTemplateEntityLoader.create(StringIdentifier.valueOf(id));
        if(ObjectUtils.isEmpty(testPaperTypeTemplate)){
            throw new BusinessException("模板不存在");
        }

        //验证测题类型是否有引入该模板
        long count = testPaperTypeQueryMapper.countByTemplateId(id);
        if(count>0){
            throw new BusinessException("该模板正在被使用,不可删除");
        }
        testPaperTypeTemplate.delete();
    }

    @Override
    public TestPaperTypeTemplateInfoDto info(String id) {
        if(ObjectUtils.isEmpty(id)){
            throw new BusinessException("模板编号没有传入");
        }

        TestPaperTypeTemplate testPaperTypeTemplate = testPaperTypeTemplateEntityLoader.create(StringIdentifier.valueOf(id));
        if(ObjectUtils.isEmpty(testPaperTypeTemplate)){
            throw new BusinessException("模板不存在");
        }

        TestPaperTypeTemplateInfoDto testPaperTypeTemplateInfoDto=TestPaperTypeTemplateInfoDto.builder()
                .id(testPaperTypeTemplate.getId().toValue())
                .name(testPaperTypeTemplate.getName())
                .build();
        return testPaperTypeTemplateInfoDto;
    }

    @Override
    public List<TestPaperTypeTemplateInfoDto> allList() {

        List<String> allList=testPaperTypeTemplateQueryMapper.allList();
        if(ObjectUtils.isEmpty(allList)){
            return Collections.EMPTY_LIST;
        }
        List<TestPaperTypeTemplateInfoDto> testPaperTypeTemplateInfoDtoList = allList.stream().map(n -> {
            TestPaperTypeTemplate testPaperTypeTemplate = testPaperTypeTemplateEntityLoader.create(StringIdentifier.valueOf(n));
            if (ObjectUtils.isEmpty(testPaperTypeTemplate)) {
                return null;
            }

            TestPaperTypeTemplateInfoDto testPaperTypeTemplateInfoDto = TestPaperTypeTemplateInfoDto.builder()
                    .id(testPaperTypeTemplate.getId().toValue())
                    .name(testPaperTypeTemplate.getName())
                    .build();
            return testPaperTypeTemplateInfoDto;
        }).filter(n -> !ObjectUtils.isEmpty(n)).collect(Collectors.toList());

        return testPaperTypeTemplateInfoDtoList;
    }
}
