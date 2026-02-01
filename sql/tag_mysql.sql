-- ============================================================
-- MySQL 建表脚本
-- 生成时间: 2026-01-24
-- 说明: 标签模块相关表结构
-- ============================================================
-- 设置字符集，确保中文注释正常显示
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

-- 1. 标签表
DROP TABLE IF EXISTS `base_tag`;
CREATE TABLE `base_tag` (
    `tag_id` VARCHAR(50) NOT NULL COMMENT '标签ID',
    `tag_name` VARCHAR(100) DEFAULT NULL COMMENT '标签名称',
    `sub_name` VARCHAR(100) DEFAULT NULL COMMENT '副标签名称',
    `tag_code` VARCHAR(50) DEFAULT NULL COMMENT '标签编码',
    `tag_describe` TEXT DEFAULT NULL COMMENT '标签描述',
    `category_id` VARCHAR(50) DEFAULT NULL COMMENT '分类ID',
    `category_name` VARCHAR(100) DEFAULT NULL COMMENT '分类名称',
    `forbidden_flag` INT(2) NOT NULL DEFAULT 0 COMMENT '禁用状态 0可用，1禁用',
    `agent_id` VARCHAR(50) DEFAULT NULL COMMENT '代理商ID',
    `agent_name` VARCHAR(200) DEFAULT NULL COMMENT '代理商名称',
    `merchant_id` VARCHAR(50) DEFAULT NULL COMMENT '商户ID',
    `merchant_name` VARCHAR(200) DEFAULT NULL COMMENT '商户名称',
    `insert_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
    `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted` INT(2) NOT NULL DEFAULT 0 COMMENT '删除标记，0否 1是',
    PRIMARY KEY (`tag_id`),
    UNIQUE KEY `uk_tag_code` (`tag_code`),
    KEY `idx_category_id` (`category_id`),
    KEY `idx_agent_id` (`agent_id`),
    KEY `idx_merchant_id` (`merchant_id`),
    KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签';
