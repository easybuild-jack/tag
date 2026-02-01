-- ============================================================
-- PostgreSQL 建表脚本
-- 生成时间: 2026-01-24
-- 说明: 标签模块相关表结构
-- ============================================================

-- 1. 标签表
DROP TABLE IF EXISTS base_tag CASCADE;
CREATE TABLE base_tag (
    tag_id VARCHAR(50) NOT NULL,
    tag_name VARCHAR(100) DEFAULT NULL,
    sub_name VARCHAR(100) DEFAULT NULL,
    tag_code VARCHAR(50) DEFAULT NULL,
    tag_describe TEXT DEFAULT NULL,
    category_id VARCHAR(50) DEFAULT NULL,
    category_name VARCHAR(100) DEFAULT NULL,
    forbidden_flag SMALLINT NOT NULL DEFAULT 0,
    agent_id VARCHAR(50) DEFAULT NULL,
    agent_name VARCHAR(200) DEFAULT NULL,
    merchant_id VARCHAR(50) DEFAULT NULL,
    merchant_name VARCHAR(200) DEFAULT NULL,
    insert_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted SMALLINT NOT NULL DEFAULT 0,
    CONSTRAINT pk_base_tag PRIMARY KEY (tag_id),
    CONSTRAINT uk_base_tag_tag_code UNIQUE (tag_code)
);

-- 创建索引
CREATE INDEX idx_base_tag_category_id ON base_tag(category_id);
CREATE INDEX idx_base_tag_agent_id ON base_tag(agent_id);
CREATE INDEX idx_base_tag_merchant_id ON base_tag(merchant_id);
CREATE INDEX idx_base_tag_deleted ON base_tag(deleted);

-- 创建触发器函数
CREATE OR REPLACE FUNCTION update_base_tag_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.last_update_time = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- 创建触发器
CREATE TRIGGER trigger_update_base_tag_timestamp
    BEFORE UPDATE ON base_tag
    FOR EACH ROW
    EXECUTE FUNCTION update_base_tag_timestamp();

-- 表注释
COMMENT ON TABLE base_tag IS '标签';
COMMENT ON COLUMN base_tag.tag_id IS '标签ID';
COMMENT ON COLUMN base_tag.tag_name IS '标签名称';
COMMENT ON COLUMN base_tag.sub_name IS '副标签名称';
COMMENT ON COLUMN base_tag.tag_code IS '标签编码';
COMMENT ON COLUMN base_tag.tag_describe IS '标签描述';
COMMENT ON COLUMN base_tag.category_id IS '分类ID';
COMMENT ON COLUMN base_tag.category_name IS '分类名称';
COMMENT ON COLUMN base_tag.forbidden_flag IS '禁用状态 0可用，1禁用';
COMMENT ON COLUMN base_tag.agent_id IS '代理商ID';
COMMENT ON COLUMN base_tag.agent_name IS '代理商名称';
COMMENT ON COLUMN base_tag.merchant_id IS '商户ID';
COMMENT ON COLUMN base_tag.merchant_name IS '商户名称';
COMMENT ON COLUMN base_tag.insert_time IS '插入时间';
COMMENT ON COLUMN base_tag.last_update_time IS '最后更新时间';
COMMENT ON COLUMN base_tag.deleted IS '删除标记，0否 1是';
