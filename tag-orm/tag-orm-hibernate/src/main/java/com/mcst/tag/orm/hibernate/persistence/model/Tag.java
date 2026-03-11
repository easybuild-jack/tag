package com.mcst.tag.orm.hibernate.persistence.model;

import com.mcst.easyfk.core.annotation.ForbiddenField;
import com.mcst.easyfk.core.annotation.PrimaryKey;
import com.mcst.easyfk.service.hibernate.persistence.BaseHibernateEntity;
import com.mcst.tag.api.dto.TagDto;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.UuidGenerator;

/**
 * 作者: liu yijun
 * 日期: 2026-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "base_tag")
@AutoMapper(target = TagDto.class)
public class Tag extends BaseHibernateEntity {


    @PrimaryKey
    @Id
    @UuidGenerator
    @Column(name = "tag_id")
    private String tagId;

    /**
     * 标签名称
     */
    @Column(name = "tag_name")
    private String tagName;

    /**
     * 副标签名称
     */
    @Column(name = "sub_name")
    private String subName;

    /**
     * 标签编码
     */
    @Column(name = "tag_code")
    private String tagCode;

    /**
     * 标签描述
     */
    @Column(name = "tag_describe")
    private String tagDescribe;

    /**
     * 分类ID
     */
    @Column(name = "category_id")
    private String categoryId;

    /**
     * 分类名称
     */
    @Column(name = "category_name")
    private String categoryName;


    /**
     * 禁用状态 0可用，1禁用
     */
    @ForbiddenField(value = "forbiddenFlag")
    @Column(name = "forbidden_flag")
    private Integer forbiddenFlag;


    /**
     * 代理商ID
     */
    @Column(name = "agent_id")
    private String agentId;

    /**
     * 代理商名称
     */
    @Column(name = "agent_name")
    private String agentName;

    /**
     * 商户ID
     */
    @Column(name = "merchant_id")
    private String merchantId;
    /**
     * 商户名称
     */
    @Column(name = "merchant_name")
    private String merchantName;
}
