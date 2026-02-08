package com.mcst.tag.controller;

import com.mcst.easyfk.core.builders.RRBuilder;
import com.mcst.easyfk.core.builders.SRPBuilder;
import com.mcst.easyfk.core.dto.request.SearchRequest;
import com.mcst.easyfk.core.dto.response.ResponseResult;
import com.mcst.easyfk.core.utils.common.EmptyUtil;
import com.mcst.tag.api.ITagApi;
import com.mcst.tag.api.request.TagReq;
import com.mcst.tag.api.response.TagResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 标签相关接口
 *
 * @author liu yijun
 * @since 2026-01-23
 */
@Tag(name = "标签相关接口", description = "标签相关接口")
@ResponseBody
@RequestMapping("/api/base/tag")
public class TagController {
    @Resource
    private ITagApi tagApi;

    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/queryPage")
    public ResponseResult<List<TagResp>> queryPage(@Validated TagReq tagReq) {
        if (EmptyUtil.isEmpty(tagReq.getTotalCount())) {
            tagReq.setTotalCount(false);
        }
        SearchRequest<TagReq> requestParam = SRPBuilder.buildRequest(tagReq);
        return RRBuilder.buildBodyByPageResult(this.tagApi.queryByPage(requestParam));
    }


    @Operation(summary = "下拉列表接口")
    @GetMapping("/selectList")
    public ResponseResult<List<TagResp>> selectList(TagReq tagReq) {
        SRPBuilder<TagReq> requestParamBuilder = SRPBuilder.<TagReq>builder().selectFields(TagReq::getTagId, TagReq::getTagName).example(tagReq);
        if (EmptyUtil.isNotEmpty(tagReq.getTagName())) {
            requestParamBuilder.likeFields(TagReq::getTagName);
            tagReq.setTop(20);
        }
        return RRBuilder.buildSuccessBody(this.tagApi.queryList(requestParamBuilder.build()));
    }

}
