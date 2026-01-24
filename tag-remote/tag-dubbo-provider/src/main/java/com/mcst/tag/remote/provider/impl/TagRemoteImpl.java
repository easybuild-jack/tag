package com.mcst.tag.remote.provider.impl;

import com.mcst.tag.api.response.TagResp;
import com.mcst.tag.api.request.TagReq;
import com.mcst.tag.server.service.ITagService;
import com.mcst.tag.remote.ITagRemote;
import com.mcst.easyfk.remote.dubbo.DubboBaseRemoteImpl;
import org.apache.dubbo.config.annotation.DubboService;

/**
* <p>
* 标签 RemoteImpl
* </p>
*
* @author liu yijun
*/
@DubboService
public class TagRemoteImpl extends DubboBaseRemoteImpl<ITagService, TagResp,String,TagReq> implements ITagRemote {


}
