package com.mcst.tag.remote.provider.impl;

import com.mcst.easyfk.remote.sc.BaseRemoteImpl;
import com.mcst.tag.api.request.TagReq;
import com.mcst.tag.api.response.TagResp;
import com.mcst.tag.remote.ITagRemote;
import com.mcst.tag.server.service.ITagService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 标签 RemoteImpl
 * </p>
 *
 * @author liu yijun
 */
@RestController
@RequestMapping("${easyfk.config.remote.tag.base-path:/remote}/base/tag")
public class TagRemoteImpl extends BaseRemoteImpl<ITagService, TagResp, String, TagReq> implements ITagRemote {


}
