package com.mcst.tag.controller;

import com.mcst.easyfk.authority.annotation.AuthResource;
import com.mcst.easyfk.authority.annotation.ResourceController;
import com.mcst.easyfk.authority.enums.ResourceCategory;
import com.mcst.easyfk.core.builders.MRPBuilder;
import com.mcst.easyfk.core.builders.RRBuilder;
import com.mcst.easyfk.core.builders.SRPBuilder;
import com.mcst.easyfk.core.dto.request.BatchBasicReq;
import com.mcst.easyfk.core.dto.request.DisableActionParam;
import com.mcst.easyfk.core.dto.request.DisableFieldReq;
import com.mcst.easyfk.core.dto.request.ModifyRequest;
import com.mcst.easyfk.core.dto.response.ResponseResult;
import com.mcst.easyfk.core.utils.common.DisableUtil;
import com.mcst.easyfk.core.utils.common.EmptyUtil;
import com.mcst.easyfk.core.utils.common.SplitUtil;
import com.mcst.tag.api.ITagApi;
import com.mcst.tag.api.request.TagEditReq;
import com.mcst.tag.api.request.TagReq;
import com.mcst.tag.api.response.TagResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 标签相关接口
 *
 * @author liu yijun
 * @since 2026-01-23
 */
@Tag(name = "标签相关接口", description = "标签相关接口")
@ResponseBody
@RequestMapping("/api/bms/base/tag")
@ResourceController(group = "baseSetting", id = "baseSetting", name = "基础设置", sort = 98000, path = "/base")
public class TagBmsController {
    @Resource
    private ITagApi tagApi;

    @Operation(summary = "详情")
    @GetMapping("/detail")
    public ResponseResult<TagResp> detail(String id) {
        return RRBuilder.buildSuccessBody(this.tagApi.queryById(id));
    }

    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/queryPage")
    @AuthResource(id = "tagManage", name = "标签管理", pId = "baseSetting", pName = "基础设置", sort = 98500, path = "/base/tag", category = ResourceCategory.menu)
    public ResponseResult<List<TagResp>> queryPage(@Validated TagReq tagReq) {
        SRPBuilder<TagReq> builder = SRPBuilder.<TagReq>builder().example(tagReq);
        return RRBuilder.buildBodyByPageResult(this.tagApi.queryByPage(builder.build()));
    }

    @Operation(summary = "新增/编辑", description = "新增/编辑")
    @PostMapping("/addOrEdit")
    @AuthResource(id = "tagAddOrEdit", name = "新增/编辑", title = "新增/编辑", pId = "tagManage", pName = "标签管理", sort = 98501, category = ResourceCategory.action,
            actionCode = "addOrEdit")
    public ResponseResult<?> addOrEdit(@RequestBody TagEditReq tagEditReq) {
        ModifyRequest<TagReq> requestParam = MRPBuilder.buildRequest(tagEditReq, TagReq.class);
        return RRBuilder.buildBodyByBaseResult(this.tagApi.save(requestParam));
    }

    @Operation(summary = "批量删除", description = "批量删除")
    @PostMapping("/delete")
    @AuthResource(id = "tagDelete", name = "批量删除", title = "批量删除", pId = "tagManage", pName = "标签管理", sort = 98502, category = ResourceCategory.action,
            actionCode = "delete")
    public ResponseResult<?> delete(@Validated @RequestBody BatchBasicReq<String> batchBase) {
        List<String> idArray = SplitUtil.split(batchBase.getIds());
        ModifyRequest<TagReq> requestParam = MRPBuilder.<TagReq>builder().ids(idArray).build();
        return RRBuilder.buildBodyByBaseResult(this.tagApi.delete(requestParam));
    }

    @Operation(summary = "启用/禁用")
    @PostMapping("/disable")
    @AuthResource(id = "tagDisable", name = "启用/禁用", title = "启用/禁用", pId = "tagManage", pName = "标签管理", sort = 98503, category = ResourceCategory.action,
            actionCode = "disable")
    public ResponseResult<?> disable(@RequestBody @Validated DisableFieldReq disableFieldVO) {
        DisableActionParam<TagReq> disableActionParam =
                new DisableActionParam<TagReq>(disableFieldVO).setIdField(TagReq::getTagId).setValueField(TagReq::getForbiddenFlag).setTClass(TagReq.class);
        List<TagReq> list = DisableUtil.createObjListByDisableAction(disableActionParam);
        if (EmptyUtil.isEmpty(list)) {
            return RRBuilder.buildFailedBody("参数错误");
        }
        ModifyRequest<TagReq> requestParam = MRPBuilder.<TagReq>builder().paramList(list).build();
        return RRBuilder.buildBodyByBaseResult(this.tagApi.save(requestParam));
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
