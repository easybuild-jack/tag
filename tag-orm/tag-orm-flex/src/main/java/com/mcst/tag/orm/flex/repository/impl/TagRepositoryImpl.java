package com.mcst.tag.orm.flex.repository.impl;

import com.mcst.easyfk.service.flex.impl.BaseFlexRepositoryImpl;
import com.mcst.tag.api.dto.TagDto;
import com.mcst.tag.orm.flex.persistence.mapper.TagMapper;
import com.mcst.tag.orm.flex.persistence.model.Tag;
import com.mcst.tag.orm.repository.ITagRepository;

/**
 * <p>
 * 标签 Repository实现类（MyBatis-Flex）
 * </p>
 *
 * @author liu yijun
 * @since 2026-01-23
 */
public class TagRepositoryImpl extends BaseFlexRepositoryImpl<TagMapper, TagDto, Tag, String> implements ITagRepository {


}
