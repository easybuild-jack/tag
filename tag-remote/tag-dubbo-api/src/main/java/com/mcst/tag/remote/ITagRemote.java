package com.mcst.tag.remote;

import com.mcst.tag.api.response.TagResp;
import com.mcst.tag.api.request.TagReq;
import com.mcst.easyfk.remote.dubbo.IDubboBaseRemote;

/**
* <p>
* 标签 Remote接口类
* </p>
*
* @author liu yijun
* @since 2026-01-23
*/
public interface ITagRemote extends IDubboBaseRemote<TagResp,String,TagReq>{


}