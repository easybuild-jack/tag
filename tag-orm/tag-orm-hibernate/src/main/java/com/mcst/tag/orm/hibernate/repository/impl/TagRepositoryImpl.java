package com.mcst.tag.orm.hibernate.repository.impl;

import com.mcst.easyfk.service.hibernate.impl.BaseHibernateRepositoryImpl;
import com.mcst.tag.api.dto.TagDto;
import com.mcst.tag.orm.hibernate.persistence.model.Tag;
import com.mcst.tag.orm.hibernate.persistence.repository.TagJpaRepository;
import com.mcst.tag.orm.repository.ITagRepository;

/**
 * <p>
 * 标签 Repository实现类（Hibernate/JPA）
 * </p>
 *
 * @author liu yijun
 * @since 2026-01-23
 */
public class TagRepositoryImpl extends BaseHibernateRepositoryImpl<TagJpaRepository, TagDto, Tag, String> implements ITagRepository {


}
