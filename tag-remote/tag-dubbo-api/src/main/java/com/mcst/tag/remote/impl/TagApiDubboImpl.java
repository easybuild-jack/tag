package com.mcst.tag.remote.impl;

import com.mcst.tag.api.response.TagResp;
import com.mcst.tag.api.request.TagReq;
import com.mcst.tag.api.ITagApi;
import com.mcst.easyfk.remote.dubbo.DubboBaseApiRemoteImpl;
import com.mcst.tag.remote.ITagRemote;
import jakarta.annotation.Resource;
/**
* <p>
* 标签 Api接口Dubbo层实现
* </p>
*
* @author liu yijun
* @since 2026-01-23
*/
public class TagApiDubboImpl extends DubboBaseApiRemoteImpl<ITagRemote, TagResp,String,TagReq> implements ITagApi {

}
