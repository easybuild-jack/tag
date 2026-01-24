package com.mcst.tag.server.service.impl;

import com.mcst.tag.api.response.TagResp;
import com.mcst.tag.api.request.TagReq;
import com.mcst.tag.api.dto.TagDto;
import com.mcst.tag.server.service.ITagService;
import com.mcst.tag.server.repository.ITagRepository;
import com.mcst.easyfk.core.dto.page.PageResult;
import com.mcst.easyfk.core.dto.request.SearchRequest;
import com.mcst.easyfk.core.dto.request.ModifyRequest;
import com.mcst.easyfk.core.dto.response.BaseResult;
import com.mcst.easyfk.repository.util.ConditionUtil;
import java.util.List;
import jakarta.annotation.Resource;
import com.mcst.easyfk.service.util.ServiceUtil;
import com.mcst.easyfk.core.utils.common.TransformUtil;
/**
* <p>
* 标签 服务实现类
* </p>
*
* @author liu yijun
* @since 2026-01-23
*/

public class TagServiceImpl  implements ITagService {
    @Resource
    private ITagRepository tagRepository;

    @Override
    public TagResp queryById(String id, String... selectColumns) {
        return TransformUtil.transformObj(this.tagRepository.queryById(id, selectColumns), TagResp.class);
    }

    @Override
    public TagResp queryOneByField(String field, Object value, String... selectColumns) {
        return TransformUtil.transformObj(this.tagRepository.queryOneByField(field, value, selectColumns), TagResp.class);
    }

    @Override
    public List<TagResp> queryList(SearchRequest<TagReq> param) {
        return TransformUtil.transformList(this.tagRepository.queryByCondition(ConditionUtil.conditionByRequest(param)), TagResp.class);
    }

    @Override
    public TagResp queryOne(SearchRequest<TagReq> param) {
        return TransformUtil.transformObj(this.tagRepository.queryOneByCondition(ConditionUtil.conditionByRequest(param)), TagResp.class);
    }

    @Override
    public long count(SearchRequest<TagReq> param) {
        return this.tagRepository.countByCondition(ConditionUtil.conditionByRequest(param));
    }

    @Override
    public boolean exists(SearchRequest<TagReq> param) {
        return this.tagRepository.exists(ConditionUtil.conditionByRequest(param));
    }

    @Override
    public PageResult<TagResp> queryByPage(SearchRequest<TagReq> param) {
        return TransformUtil.transformPageResult(this.tagRepository.queryByPage(ConditionUtil.conditionByRequest(param)),TagResp.class);
    }

    @Override
    public BaseResult<?> save(ModifyRequest<TagReq> param) {
        return ServiceUtil.save(param, tagRepository, TagDto.class);
    }

    @Override
    public BaseResult<?> delete(ModifyRequest<TagReq> param) {
        return ServiceUtil.delete(param, tagRepository, TagDto.class);
    }

    @Override
    public String insertAndReturnId(TagReq param) {
        return ServiceUtil.insertAndReturnId(param, tagRepository, TagDto.class);
    }

}
