# Tag 标签管理模块

> 基于 `easyfk` 基础框架的**业务标签**管理模块，提供标签基础信息、分类关联、多租户隔离与下拉选择能力，可用于商品标签、内容标签、营销标记等多种运营场景，同时支持 **Spring Cloud（Feign）** 与 **Apache Dubbo** 两种远程调用方式，方便单体或微服务架构集成。

- **项目版本**：`<latest>`（请以实际发布的最新版本为准）
- **Maven Group**：`com.mcst`
- **构建工具**：Gradle（版本以根 `gradle/wrapper/gradle-wrapper.properties` 为准）
- **JDK**：Java 21+
- **框架**：Spring Boot 3.x、Spring Cloud OpenFeign、Apache Dubbo 3.x
- **辅助工具**：Lombok、MapStruct Plus、Hutool

---

## 一、项目主要功能

1. **标签信息维护**
   - 标签名称、副标题、编码、描述、关联分类等基础信息维护。
   - 启用 / 禁用状态控制。

2. **下拉选择与搜索**
   - 提供按名称模糊搜索的下拉接口，便于商品、内容等模块快速引用。

3. **双端接口分离**
   - C 端提供分页与下拉选择。
   - BMS 端提供完整的增删改查、启用 / 禁用。

4. **多租户与数据隔离**
   - 按代理商、商户维度隔离标签数据，避免跨租户串扰。

5. **国际化与可配置**
   - 多语言错误码（基于 `i18n/tagMessage_*.properties`）。

---

## 二、项目架构设计

### 2.1 模块划分

```
tag
├── tag-api          接口契约层：DTO / Req / Resp / Param / VO / Service API
├── tag-server       服务实现层：Service + ApiServer 实现 + 工具类 + 配置 + 单测
├── tag-prd          Web 表现层：Spring Boot Controller
│   ├── tag-prd-client     C 端接口
│   └── tag-prd-bms        后台管理接口
├── tag-orm          持久化层
│   ├── tag-orm-api         仓储接口定义（IRepository）
│   ├── tag-orm-plus        MyBatis-Plus 实现
│   ├── tag-orm-flex        MyBatis-Flex 实现
│   └── tag-orm-hibernate   Hibernate/JPA 实现
├── tag-remote       远程调用层（可选接入）
│   ├── tag-cloud-api         Spring Cloud 客户端：Feign 接口 + Api 适配
│   ├── tag-cloud-provider    Spring Cloud 服务端：暴露 /remote/tag/** 的 Controller
│   ├── tag-dubbo-api         Dubbo 接口 + Api 适配
│   └── tag-dubbo-provider    Dubbo 服务端实现
└── sql               数据库脚本
    ├── tag_mysql.sql
    └── tag_postgresql.sql
```

### 2.2 层次与调用关系

```
┌──────────────────────────────────────────────────────────────────────┐
│  客户端 / 调用方                                                       │
└──────────────────────────────────────────────────────────────────────┘
            │                           │                        │
            ▼                           ▼                        ▼
     ┌────────────┐               ┌────────────┐           ┌────────────┐
     │  tag-prd   │               │  tag-      │           │  tag-      │
     │ -client/-  │               │ cloud-api  │           │ dubbo-api  │
     │  bms       │               │ (Feign 客  │           │ (Dubbo 消  │
     │(Controller │               │ 户端+Api)  │           │ 费端+Api)  │
     └────────────┘               └────────────┘           └────────────┘
            │                           │                        │
            │                           │ HTTP                   │ Dubbo RPC
            ▼                           ▼                        ▼
                          ┌──────────────────────────────────────┐
                          │ tag-cloud-provider / tag-dubbo-       │
                          │ provider （统一委托给 tag-server）    │
                          └──────────────────────────────────────┘
                                              │
                                              ▼
                              ┌──────────────────────────────┐
                              │          tag-server           │
                              │   Service + ApiServer 实现   │
                              └──────────────────────────────┘
                                              │
                                              ▼
                              ┌──────────────────────────────┐
                              │  tag-orm-*（Plus/Flex/Hib）  │
                              │   仓储接口 + 持久化实现        │
                              └──────────────────────────────┘
                                              │
                                              ▼
                                          数据库
```

### 2.3 关键设计点

- **接口与实现分离**：`tag-api` 只暴露接口与数据模型；`tag-server` 提供本地默认实现，通过 Spring Boot 自动装配以 `@ConditionalOnMissingBean` 形式暴露为 Bean，便于上层覆盖。
- **多 ORM 适配**：`tag-orm` 把仓储接口抽到 `tag-orm-api`，底层分别在 `tag-orm-plus / tag-orm-flex / tag-orm-hibernate` 中提供实现，业务侧按需引入对应依赖即可切换 ORM。
- **远程调用可插拔**：`tag-remote` 同时提供 Feign 和 Dubbo 两套客户端，每套又拆出 `-api`（消费端）与 `-provider`（服务端）。消费端把 `ITagApi` 适配到远程 `Remote`，再通过 `@AutoConfiguration` 注册到容器；服务端把 `tag-server` 的 `Service` 透出成 HTTP / Dubbo 接口。
- **统一参数模型**：遵循 `doc/实体对象映射关系调整指南.md` 中约定的流向（`Sub → Req → Dto → Entity` / `Entity → Dto → Resp`），结合 MapStruct Plus 的 `@AutoMapper` 自动转换。
- **多租户隔离**：内置 `agentId` / `merchantId` 字段与数据过滤工具，按租户维度自动隔离标签数据。
- **声明式权限控制**：BMS 端通过 `@AuthResource` / `@ResourceController` 注解自动注册菜单与按钮权限，与 Auth 模块联动。

### 2.4 数据模型

| 表名 | 说明 |
| --- | --- |
| `tag` | 标签主表，含名称、副标题、编码、描述、关联分类、租户等 |

完整建表脚本见 [sql/tag_mysql.sql](sql/tag_mysql.sql)、[sql/tag_postgresql.sql](sql/tag_postgresql.sql)。

---

## 三、模块详细说明

### 3.1 `tag-api`
- 定义业务接口：`ITagApi`，并继承 `IBaseApi` 提供通用 CRUD。
- 维护所有数据传输对象：`dto/`、`request/`、`response/`、`param/`/`vo/`。
- 业务接口方法示例：`queryByPage`、`queryById`、`save`、`delete`、`disable`、`selectList`、`queryByCategory`、`queryByName` 等。

### 3.2 `tag-server`
- `service/`：业务 Service 接口与实现（`TagServiceImpl`）。
- `impl/`：`ApiServer` 实现，继承 `BaseApiServiceImpl`，把 Service 暴露为 `ITagApi`。
- `util/`：标签校验、租户过滤等工具类。
- `config/TagServerConfig`：Spring Boot 自动装配入口。
- `src/test/`：基于 JUnit 5 的单元测试。

### 3.3 `tag-prd`
- 基于 `easyfk-web-prd` 的 Spring Boot Web 控制器层。
- 控制器清单：
  - `TagBmsController`：`/api/bms/operation/tag/**`（详情、分页、新增/编辑、删除、启用/禁用）
  - `TagClientController`：`/api/client/tag/**`（分页、下拉）
- 通过 `@AuthResource` / `@ResourceController` 注解自动注册权限元数据。

### 3.4 `tag-orm`
- `tag-orm-api`：仓储接口（`ITagRepository`）。
- `tag-orm-plus`：基于 MyBatis-Plus 的实现 + 自动装配。
- `tag-orm-flex`：基于 MyBatis-Flex 的实现 + 自动装配。
- `tag-orm-hibernate`：基于 Hibernate/JPA 的实现 + 自动装配。

> 三个 ORM 实现互斥，按需在业务工程中只引入其中一个，避免冲突。

### 3.5 `tag-remote`
- `tag-cloud-api`：`FeignClient` 接口 + 继承 `BaseApiRemoteImpl` 的 `Api` 实现；通过 `TagApiProperties`（`easyfk.config.remote.tag.service-id/base-path`）读取服务地址。
- `tag-cloud-provider`：`@RestController` 把 `tag-server` 的 `Service` 暴露为 `/remote/tag/**`。
- `tag-dubbo-api`：Dubbo 接口 + `Api` 适配实现。
- `tag-dubbo-provider`：Dubbo 服务实现。

---

## 四、使用方式

### 4.1 构建与发布

```bash
# 清理并编译全部模块
./gradlew clean build

# 发布到本地 Maven 仓库
./gradlew publishToMavenLocal

# 发布到远程 Nexus（在 build.gradle 中已配置 NexusRepository）
./gradlew publish
```

> Windows 环境可直接使用 `gradlew.bat`。模块统一版本：`<latest>`。

### 4.2 单体应用集成（最简方式）

```groovy
dependencies {
    implementation 'com.mcst:tag-api:<latest>'
    implementation 'com.mcst:tag-server:<latest>'
    // 选择一种 ORM：
    implementation 'com.mcst:tag-orm:tag-orm-plus:<latest>'
    // implementation 'com.mcst:tag-orm:tag-orm-flex:<latest>'
    // implementation 'com.mcst:tag-orm:tag-orm-hibernate:<latest>'

    // 暴露 HTTP 接口（按端选择）
    implementation 'com.mcst:tag-prd:tag-prd-bms:<latest>'
    implementation 'com.mcst:tag-prd:tag-prd-client:<latest>'
}
```

`tag-server` 会通过 Spring Boot 自动装配把 `ITagApi` 等 Bean 注册到容器，Controller 可直接 `@Resource` 注入使用。

### 4.3 微服务集成（Spring Cloud）

**消费端：**

```groovy
dependencies {
    implementation 'com.mcst:tag-api:<latest>'
    implementation 'com.mcst:tag-remote:tag-cloud-api:<latest>'
}
```

```yaml
easyfk:
  config:
    remote:
      tag:
        service-id: tag-server          # 对应服务在注册中心的服务名
        base-path: /remote              # 可选，默认 /remote
```

**服务端：** 启动 `tag-server` 并加入 `tag-remote:tag-cloud-provider`，即可对外提供 `/remote/tag/**` 接口。

### 4.4 微服务集成（Apache Dubbo）

```groovy
// 消费端
dependencies {
    implementation 'com.mcst:tag-api:<latest>'
    implementation 'com.mcst:tag-remote:tag-dubbo-api:<latest>'
}

// 服务端
dependencies {
    implementation 'com.mcst:tag-server:<latest>'
    implementation 'com.mcst:tag-remote:tag-dubbo-provider:<latest>'
}
```

### 4.5 关键配置项

| 配置项 | 含义 | 默认值 |
| --- | --- | --- |
| `easyfk.config.remote.tag.service-id` | 远程调用目标服务名 | `tag-server` |
| `easyfk.config.remote.tag.base-path` | 远程调用基础路径 | `/remote` |

### 4.6 初始化数据库

```bash
# MySQL
mysql -u <user> -p <database> < sql/tag_mysql.sql

# PostgreSQL
psql -U <user> -d <database> -f sql/tag_postgresql.sql
```

### 4.7 主要 REST 接口一览

| 端 | 路径 | 方法 | 说明 |
| --- | --- | --- | --- |
| BMS | `/api/bms/operation/tag/detail` | GET | 详情 |
| BMS | `/api/bms/operation/tag/queryPage` | GET | 分页查询 |
| BMS | `/api/bms/operation/tag/addOrEdit` | POST | 新增 / 编辑 |
| BMS | `/api/bms/operation/tag/delete` | POST | 批量删除 |
| BMS | `/api/bms/operation/tag/disable` | POST | 启用 / 禁用 |
| Client | `/api/client/tag/queryPage` | GET | C 端分页查询 |
| Client | `/api/client/tag/selectList` | GET | C 端下拉数据 |

> 接口均通过 Swagger OpenAPI 注解描述，可在启动后访问 `/swagger-ui.html` 查看完整文档。

---

## 五、扩展指引

- **新增业务字段**：在 `tag-api` 的 `Dto` 中新增字段 → 在 `Entity` 中同步 → 选择对应 ORM 模块更新映射 → 视情况更新 `Resp`、`Req`、`VO`。
- **新增业务接口**：在 `tag-api` 暴露方法 → 在 `tag-server` 的 `Service` 与 `ApiServer` 中实现 → 选择在 `tag-prd` 增加 Controller 或在 `tag-remote` 暴露远程能力。
- **切换 ORM**：只保留目标 ORM 模块的依赖（如只想用 MyBatis-Plus，则移除 `tag-orm-flex`、`tag-orm-hibernate` 依赖）。
- **国际化**：在 `tag-server/src/main/resources/i18n/` 下追加语言键值即可。

---

## 六、目录速览

```
tag
├── build.gradle                根构建脚本（统一版本、Java 21、Lombok、MapStruct Plus）
├── settings.gradle             多模块声明
├── gradle/, gradlew, gradlew.bat
├── tag-api/                    接口契约
├── tag-server/                 服务实现 + 单测
├── tag-prd/
│   ├── tag-prd-client/
│   └── tag-prd-bms/
├── tag-orm/
│   ├── tag-orm-api/
│   ├── tag-orm-plus/
│   ├── tag-orm-flex/
│   └── tag-orm-hibernate/
├── tag-remote/
│   ├── tag-cloud-api/
│   ├── tag-cloud-provider/
│   ├── tag-dubbo-api/
│   └── tag-dubbo-provider/
└── sql/
```
