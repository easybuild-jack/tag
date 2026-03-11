package com.mcst.tag.orm.hibernate.persistence.repository;

import com.mcst.tag.orm.hibernate.persistence.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>
 * 标签 JPA Repository接口
 * </p>
 *
 * @author liu yijun
 * @since 2026-01-23
 */
public interface TagJpaRepository extends JpaRepository<Tag, String>, JpaSpecificationExecutor<Tag> {

}
