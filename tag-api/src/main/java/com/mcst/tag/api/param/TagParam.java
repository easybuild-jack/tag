package com.mcst.tag.api.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.mcst.easyfk.core.dto.request.BasicParam;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
* <p>
* 标签数据参数对象
* </p>
*
* @author liu yijun
* @since 2026-01-23
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description="标签数据参数对象")
@SuperBuilder
@NoArgsConstructor
public class TagParam extends BasicParam {

    /**
    * 标签ID
    */
    @Schema(description= "标签ID")
    private String tagId;
    /**
    * 标签名称
    */
    @Schema(description= "标签名称")
    private String tagName;
    /**
    * 副标签名称
    */
    @Schema(description= "副标签名称")
    private String subName;
    /**
    * 标签编码
    */
    @Schema(description= "标签编码")
    private String tagCode;
    /**
    * 标签描述
    */
    @Schema(description= "标签描述")
    private String tagDescribe;
    /**
    * 分类ID
    */
    @Schema(description= "分类ID")
    private String categoryId;
    /**
    * 分类名称
    */
    @Schema(description= "分类名称")
    private String categoryName;
    /**
    * 禁用状态 0可用，1禁用
    */
    @Schema(description= "禁用状态 0可用，1禁用")
    private Integer forbiddenFlag;
    /**
    * 代理商ID
    */
    @Schema(description= "代理商ID")
    private String agentId;
    /**
    * 代理商名称
    */
    @Schema(description= "代理商名称")
    private String agentName;
    /**
    * 商户ID
    */
    @Schema(description= "商户ID")
    private String merchantId;
    /**
    * 商户名称
    */
    @Schema(description= "商户名称")
    private String merchantName;
}