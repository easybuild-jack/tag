package com.mcst.tag.remote;

import com.mcst.easyfk.remote.sc.IBaseRemote;
import com.mcst.tag.api.request.TagReq;
import com.mcst.tag.api.response.TagResp;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * 标签 Remote接口类
 * </p>
 *
 * @author liu yijun
 * @since 2026-01-23
 */
@FeignClient(value = "${easyfk.config.remote.tag.service-id:server}", path = "${easyfk.config.remote.tag.base-path:/remote}/base/tag")
public interface ITagRemote extends IBaseRemote<TagResp, String, TagReq> {


}