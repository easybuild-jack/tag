package com.mcst.tag.api.response;

import com.mcst.tag.api.dto.TagDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 标签数据响应对象
 * </p>
 *
 * @author liu yijun
 * @since 2026-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "标签数据响应对象")
@SuperBuilder
@NoArgsConstructor
public class TagResp extends TagDto {


}
