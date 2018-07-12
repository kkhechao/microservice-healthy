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
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestPaperType;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestPaperTypeTemplate;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.TestPaperQueryMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.TestPaperTypeQueryMapper;
import com.zqkh.healthy.context.appservice.inter.TestPaperService;
import com.zqkh.healthy.context.appservice.inter.TestPaperTypeService;
import com.zqkh.healthy.feign.dto.paper.app.TestPaperListToAppDto;
import com.zqkh.healthy.feign.dto.paper.app.type.TestPaperTypeListToAppDto;
import com.zqkh.healthy.feign.dto.paper.type.TestPaperTypeInfoDto;
import com.zqkh.healthy.feign.dto.paper.type.TestPaperTypeListDto;
import com.zqkh.healthy.feign.vo.paper.TestPaperTypeVo;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 试卷类型业务实现层
 *
 * @author 东来
 * @create 2018/5/28 0028
 */
@AppService
public class TestPaperTypeServiceImpl implements TestPaperTypeService {

    @Resource
    private TestPaperTypeQueryMapper testPaperTypeQueryMapper;

    @Resource
    private TestPaperQueryMapper testPaperQueryMapper;

    @Resource
    private TestPaperService testPaperService;


    private EntityLoader<TestPaperType> testPaperTypeEntityLoader = new RepositoryLoader<>(TestPaperType.class);

    private ConstructLoader<TestPaperType> testPaperTypeConstructLoader = new ConstructLoader<>(TestPaperType.class);

    private EntityLoader<TestPaperTypeTemplate> testPaperTypeTemplateEntityLoader = new RepositoryLoader<>(TestPaperTypeTemplate.class);


    @Override
    public void save(TestPaperTypeVo testPaperTypeVo) {
        if (ObjectUtils.isEmpty(testPaperTypeVo)) {
            throw new BusinessException("试卷类型参数为空");
        }
        if (ObjectUtils.isEmpty(testPaperTypeVo.getName())) {
            throw new BusinessException("试卷类型名称为空");
        }
        if (ObjectUtils.isEmpty(testPaperTypeVo.getName())) {
            throw new BusinessException("试卷那类型描述为空");
        }
        if (ObjectUtils.isEmpty(testPaperTypeVo.getUserId())) {
            throw new BusinessException("操作用户为空");
        }
        //验证名称是否重复
        boolean flg = testPaperTypeQueryMapper.nameValidation(testPaperTypeVo.getName(), testPaperTypeVo.getId());
        if (!flg) {
            throw new BusinessException("名称:" + testPaperTypeVo.getName() + "重复");
        }

        if (!ObjectUtils.isEmpty(testPaperTypeVo.getId())) {
            TestPaperType testPaperType = testPaperTypeEntityLoader.create(StringIdentifier.valueOf(testPaperTypeVo.getId()));
            if (ObjectUtils.isEmpty(testPaperType)) {
                throw new BusinessException("修改的试题不存在");
            }
            testPaperType.edit(testPaperTypeVo.getName(), testPaperTypeVo.getDesc(), testPaperTypeVo.getUserId(), testPaperTypeVo.getTemplate());
        } else {
            TestPaperType testPaperType = testPaperTypeConstructLoader.create(IdGenerator.getInstance().generate(TestPaperType.class));
            testPaperType.init(testPaperTypeVo.getName(), testPaperTypeVo.getDesc(), testPaperTypeVo.getUserId(), testPaperTypeVo.getTemplate());
        }
    }

    @Override
    public TestPaperTypeInfoDto getInfo(String id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new BusinessException("试卷类型编号为空");
        }

        TestPaperType testPaperType = testPaperTypeEntityLoader.create(StringIdentifier.valueOf(id));

        if (ObjectUtils.isEmpty(testPaperType)) {
            throw new BusinessException("试卷类型不存在");
        }

        TestPaperTypeInfoDto testPaperTypeInfoDto = new TestPaperTypeInfoDto();
        testPaperTypeInfoDto.setId(testPaperType.getId().toValue());
        testPaperTypeInfoDto.setName(testPaperType.getName());
        testPaperTypeInfoDto.setDesc(testPaperType.getDesc());
        testPaperTypeInfoDto.setTemplateId(testPaperType.getTemplateId());

        testPaperTypeInfoDto.setTemplateName(this.getTypePaperTypeTemplateName(testPaperType.getTemplateId()));

        return testPaperTypeInfoDto;
    }


    /**
     * 获取模板名称
     *
     * @param templateId
     * @return
     */
    private String getTypePaperTypeTemplateName(String templateId) {
        TestPaperTypeTemplate testPaperTypeTemplate = null;
        if (!ObjectUtils.isEmpty(templateId)) {
            testPaperTypeTemplate = testPaperTypeTemplateEntityLoader.create(StringIdentifier.valueOf(templateId));
            if (!ObjectUtils.isEmpty(testPaperTypeTemplate)) {
                return testPaperTypeTemplate.getName();
            }
        }

        return null;
    }

    @Override
    public PageResult<TestPaperTypeListDto> getTestPaperTypeListDto(Integer pageIndex, Integer pageSize) {

        PageList<String> ids = testPaperTypeQueryMapper.list(PageParames.create(pageIndex, pageSize));

        PageResult pageResult = new PageResult();
        pageResult.setPageSize(ids.getPageSize());
        pageResult.setTotalCount(ids.getTotalCount());
        if (ObjectUtils.isEmpty(ids)) {
            pageResult.setList(Collections.EMPTY_LIST);
        } else {
            List<TestPaperTypeListDto> list = ids.stream().map(n -> {
                TestPaperType testPaperType = testPaperTypeEntityLoader.create(StringIdentifier.valueOf(n));
                if (ObjectUtils.isEmpty(testPaperType)) {
                    return null;
                }
                TestPaperTypeListDto testPaperTypeListDto = new TestPaperTypeListDto();
                testPaperTypeListDto.setId(testPaperType.getId().toValue());
                testPaperTypeListDto.setName(testPaperType.getName());
                testPaperTypeListDto.setTemplateName(this.getTypePaperTypeTemplateName(testPaperType.getTemplateId()));
                testPaperTypeListDto.setNum(testPaperTypeQueryMapper.count(n));
                testPaperTypeListDto.setSeq(testPaperType.getSeq());
                testPaperTypeListDto.setDesc(testPaperType.getDesc());
                return testPaperTypeListDto;
            }).filter(n -> !ObjectUtils.isEmpty(n)).collect(Collectors.toList());
            pageResult.setList(list);
        }
        return pageResult;
    }

    @Override
    public void del(String id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new BusinessException("试卷类型编号为空");
        }

        TestPaperType testPaperType = testPaperTypeEntityLoader.create(StringIdentifier.valueOf(id));
        if (ObjectUtils.isEmpty(testPaperType)) {
            throw new BusinessException("试卷类型不存在");
        }
        //验证是否有关联试卷
        long count = testPaperTypeQueryMapper.count(id);
        if (0 < count) {
            throw new BusinessException("请把关联试卷修改后在进行删除");
        }
        testPaperType.delete();
    }

    @Override
    public List<TestPaperTypeListDto> all() {
        List<String> ids = testPaperTypeQueryMapper.all();
        if (ObjectUtils.isEmpty(ids)) {
            return Collections.EMPTY_LIST;
        }

        List<TestPaperTypeListDto> list = ids.stream().map(n -> {
            TestPaperType testPaperType = testPaperTypeEntityLoader.create(StringIdentifier.valueOf(n));
            if (ObjectUtils.isEmpty(testPaperType)) {
                return null;
            }
            TestPaperTypeListDto testPaperTypeListDto = new TestPaperTypeListDto();
            testPaperTypeListDto.setId(n);
            testPaperTypeListDto.setName(testPaperType.getName());
            return testPaperTypeListDto;

        }).filter(n -> !ObjectUtils.isEmpty(n)).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<TestPaperTypeListToAppDto> getTaperTypeListToApp(String familyMemberId) {
        //查询试题类型列表
        List<String> ids = testPaperTypeQueryMapper.all();
        if (ObjectUtils.isEmpty(ids)) {
            return Collections.EMPTY_LIST;
        }
        List<TestPaperTypeListToAppDto> testPaperTypeListToAppDtoList = ids.stream().map(n -> {
            TestPaperType testPaperType = testPaperTypeEntityLoader.create(StringIdentifier.valueOf(n));
            if (ObjectUtils.isEmpty(testPaperType)) {
                return null;
            }

            TestPaperTypeListToAppDto testPaperTypeListToAppDto = new TestPaperTypeListToAppDto();
            testPaperTypeListToAppDto.setId(testPaperType.getId().toValue());
            testPaperTypeListToAppDto.setName(testPaperType.getName());
            testPaperTypeListToAppDto.setDesc(testPaperType.getDesc());
            TestPaperTypeTemplate testPaperTypeTemplate = testPaperTypeTemplateEntityLoader.create(StringIdentifier.valueOf(testPaperType.getTemplateId()));
            if (!ObjectUtils.isEmpty(testPaperTypeTemplate)) {
                testPaperTypeListToAppDto.setTemplate(testPaperTypeTemplate.getName());
            }

            PageList<String> testPaperId = testPaperQueryMapper.search(null, true, testPaperType.getId().toString(), PageParames.create(1, 3));
            if (ObjectUtils.isEmpty(testPaperId)) {
                return null;
            }
            List<TestPaperListToAppDto> testPaperListToAppDtoList = testPaperService.getTestPaperListToAppDto(testPaperId, familyMemberId);
            testPaperTypeListToAppDto.setTestPaper(testPaperListToAppDtoList);
            return testPaperTypeListToAppDto;
        }).filter(n -> !ObjectUtils.isEmpty(n)).collect(Collectors.toList());
        return testPaperTypeListToAppDtoList;
    }

    @Override
    public void asc(String id, String userId) {
        if (ObjectUtils.isEmpty(id)) {
            throw new BusinessException("试题类型编号为空");
        }
        if (ObjectUtils.isEmpty(userId)) {
            throw new BusinessException("操作者为空");
        }

        TestPaperType testPaperType = testPaperTypeEntityLoader.create(StringIdentifier.valueOf(id));
        if (ObjectUtils.isEmpty(testPaperType)) {
            throw new BusinessException("试题类型不存在");
        }
        testPaperType.asc(userId);

    }

    @Override
    public void descend(String id, String userId) {
        if (ObjectUtils.isEmpty(id)) {
            throw new BusinessException("试题类型编号为空");
        }
        if (ObjectUtils.isEmpty(userId)) {
            throw new BusinessException("操作者为空");
        }

        TestPaperType testPaperType = testPaperTypeEntityLoader.create(StringIdentifier.valueOf(id));
        if (ObjectUtils.isEmpty(testPaperType)) {
            throw new BusinessException("试题类型不存在");
        }
        testPaperType.descend(userId);
    }
}
