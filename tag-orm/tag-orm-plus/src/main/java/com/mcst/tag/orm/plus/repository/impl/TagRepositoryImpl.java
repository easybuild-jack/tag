package com.mcst.tag.orm.plus.repository.impl;

import com.mcst.easyfk.service.mybatisplus.impl.BaseMyBatisRepositoryImpl;
import com.mcst.tag.api.dto.TagDto;
import com.mcst.tag.orm.plus.persistence.mapper.TagMapper;
import com.mcst.tag.orm.plus.persistence.model.Tag;
import com.mcst.tag.orm.repository.ITagRepository;

/**
 * <p>
 * 标签 Repository实现类
 * </p>
 *
 * @author liu yijun
 * @since 2026-01-23
 */
public class TagRepositoryImpl extends BaseMyBatisRepositoryImpl<TagMapper, TagDto, Tag, String> implements ITagRepository {


}