package com.mcst.tag.orm.flex.persistence.model;

import com.mcst.easyfk.core.annotation.ForbiddenField;
import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.service.flex.persistence.BaseFlexEntity;
import com.mcst.tag.api.dto.TagDto;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
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
@Table("base_tag")
@AutoMapper(target = TagDto.class)
public class Tag extends BaseFlexEntity<Tag> {

    /**
     * 标签ID
     */
    @PrimaryKey
    @Id(keyType = KeyType.Generator, value = "uuid")
    private String tagId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 副标签名称
     */
    private String subName;

    /**
     * 标签编码
     */
    private String tagCode;

    /**
     * 标签描述
     */
    private String tagDescribe;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 分类名称
     */
    private String categoryName;


    /**
     * 禁用状态 0可用，1禁用
     */
    @ForbiddenField(value = "forbiddenFlag")
    private Integer forbiddenFlag;


    /**
     * 代理商ID
     */
    private String agentId;

    /**
     * 代理商名称
     */
    private String agentName;

    /**
     * 商户ID
     */
    private String merchantId;
    /**
     * 商户名称
     */
    private String merchantName;
}
