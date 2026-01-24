package com.mcst.tag.server.repository.impl;

import com.mcst.tag.api.dto.TagDto;
import com.mcst.tag.server.repository.ITagRepository;
import com.mcst.easyfk.service.mybatisplus.impl.BaseMyBatisRepositoryImpl;
import com.mcst.tag.server.persistence.mapper.TagMapper;
import com.mcst.tag.server.persistence.model.Tag;
/**
* <p>
* 标签 Repository实现类
* </p>
*
* @author liu yijun
* @since 2026-01-23
*/
public class TagRepositoryImpl extends BaseMyBatisRepositoryImpl<TagMapper,TagDto, Tag,String> implements ITagRepository {


}