package com.mcst.tag.server.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mcst.easyfk.core.annotation.ForbiddenField;
import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.service.mybatisplus.annotation.Column;
import com.mcst.easyfk.service.mybatisplus.persistence.BaseMyBatisPlusEntity;
import com.mcst.tag.api.dto.TagDto;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 作者: liu yijun
 * 日期: 2026-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tag")
@AutoMapper(target = TagDto.class)
public class Tag extends BaseMyBatisPlusEntity<Tag> {

    /**
     * 标签ID
     */
    @PrimaryKey
    @TableId(type = IdType.ASSIGN_UUID)
    @Column(comment = "标签ID")
    private String tagId;

    /**
     * 标签名称
     */
    @Column(comment = "标签名称")
    private String tagName;

    /**
     * 副标签名称
     */
    @Column(comment = "副标签名称")
    private String subName;

    /**
     * 标签编码
     */
    @Column(comment = "标签编码")
    private String tagCode;

    /**
     * 标签描述
     */
    @Column(comment = "标签描述")
    private String tagDescribe;

    /**
     * 分类ID
     */
    @Column(comment = "分类ID")
    private String categoryId;

    /**
     * 分类名称
     */
    @Column(comment = "分类名称")
    private String categoryName;


    /**
     * 禁用状态 0可用，1禁用
     */
    @ForbiddenField(value = "forbiddenFlag")
    @Column(comment = "禁用状态 0可用，1禁用")
    private Integer forbiddenFlag;


    /**
     * 代理商ID
     */
    @Column(comment = "代理商ID")
    private String agentId;

    /**
     * 代理商名称
     */
    @Column(comment = "代理商名称")
    private String agentName;

    /**
     * 商户ID
     */
    @Column(comment = "商户ID")
    private String merchantId;
    /**
     * 商户名称
     */
    @Column(comment = "商户名称")
    private String merchantName;
}