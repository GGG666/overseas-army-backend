# 留学申请小程序重构设计文档

**日期**：2026-05-07
**版本**：v1.0

---

## 1. 项目概述

- **项目名称**：overseas-army（留学申请小程序）
- **后端**：overseas-army-backend（Spring Boot 3.2）
- **前端**：overseas-army-miniapp（微信小程序）
- **目标**：综合重构，规范化代码结构，引入用户体系和申请管理功能

---

## 2. 技术栈

| 层级 | 技术 |
|------|------|
| 后端框架 | Spring Boot 3.2.0 + Java 17 |
| 安全认证 | Spring Security + JWT |
| ORM | Spring Data JPA |
| 缓存 | Redis |
| 数据库 | MySQL 8 |
| 前端 | 微信小程序 |

---

## 3. 后端模块结构

```
src/main/java/com/overseas/army/
├── config/
│   ├── SecurityConfig.java      # Security 配置
│   ├── RedisConfig.java         # Redis 配置
│   └── WebConfig.java           # Web 配置（CORS、拦截器）
├── entity/                       # 数据实体
│   ├── University.java
│   ├── Major.java
│   ├── User.java               # 新增
│   ├── Application.java         # 新增
│   └── ApplicationLog.java      # 新增
├── repository/                   # 数据访问层
├── service/                      # 业务逻辑层
├── controller/                  # API 接口层
├── dto/
│   ├── MatchRequest.java
│   ├── MatchResult.java
│   ├── LoginRequest.java        # 新增
│   ├── RegisterRequest.java     # 新增
│   ├── ApplicationRequest.java  # 新增
│   └── ApplicationVO.java       # 新增
├── common/
│   ├── ResultVO.java            # 统一响应格式
│   ├── BizException.java        # 业务异常
│   └── GlobalExceptionHandler.java
└── security/
    ├── JwtTokenUtils.java       # JWT 工具类
    ├── JwtAuthFilter.java        # JWT 认证过滤器
    ├── UserDetailsServiceImpl.java
    └── JwtAuthenticationEntryPoint.java
```

### 3.1 各层职责

- **controller**：接收请求、参数校验、调用 service、返回 ResultVO
- **service**：业务逻辑、事务管理
- **repository**：数据访问（JPA）
- **common**：跨层共享的响应格式、异常定义

---

## 4. API 设计

### 4.1 认证模块

| 方法 | 接口 | 说明 | 鉴权 |
|------|------|------|------|
| POST | /api/auth/register | 用户注册 | 否 |
| POST | /api/auth/login | 用户登录，返回 JWT | 否 |
| POST | /api/auth/refresh | 刷新 Token | 否 |

### 4.2 院校模块

| 方法 | 接口 | 说明 | 鉴权 |
|------|------|------|------|
| GET | /api/universities | 院校列表，可按国家筛选 | 是 |
| GET | /api/universities/{id} | 院校详情 | 是 |

### 4.3 专业模块

| 方法 | 接口 | 说明 | 鉴权 |
|------|------|------|------|
| GET | /api/majors | 专业列表，可按院校/类型筛选 | 是 |
| GET | /api/majors/{id} | 专业详情 | 是 |

### 4.4 匹配模块

| 方法 | 接口 | 说明 | 鉴权 |
|------|------|------|------|
| POST | /api/match | 智能选校匹配 | 是 |

### 4.5 申请模块

| 方法 | 接口 | 说明 | 鉴权 |
|------|------|------|------|
| GET | /api/applications | 我的申请列表 | 是 |
| POST | /api/applications | 创建申请 | 是 |
| PUT | /api/applications/{id} | 更新申请状态 | 是 |
| DELETE | /api/applications/{id} | 删除申请 | 是 |

### 4.6 用户模块

| 方法 | 接口 | 说明 | 鉴权 |
|------|------|------|------|
| GET | /api/users/me | 获取个人信息 | 是 |
| PUT | /api/users/me | 更新个人信息 | 是 |
| PUT | /api/users/me/password | 修改密码 | 是 |

---

## 5. 数据库设计

### 5.1 现有表

**university 表**（已有）
```sql
id, name_cn, name_en, country, rank, logo_url, location, website
```

**major 表**（已有）
```sql
id, university_id, name_cn, name_en, degree_type, duration, tuition, language_req, academic_req, deadline
```

### 5.2 新增表

**user 表**
```sql
CREATE TABLE user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(100),
  phone VARCHAR(20),
  gpa DECIMAL(3,2) COMMENT 'GPA 成绩',
  language_score VARCHAR(50) COMMENT '语言成绩如 IELTS 7.0',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

**application 表**
```sql
CREATE TABLE application (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  major_id BIGINT NOT NULL,
  status VARCHAR(20) DEFAULT 'draft' COMMENT 'draft/submitted/admitted/rejected',
  notes TEXT COMMENT '备注/文书链接',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (major_id) REFERENCES major(id)
);
```

**application_log 表**
```sql
CREATE TABLE application_log (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  application_id BIGINT NOT NULL,
  action VARCHAR(50) COMMENT 'create/update_status/cancel',
  before_status VARCHAR(20),
  after_status VARCHAR(20),
  operator VARCHAR(100) COMMENT '操作人',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (application_id) REFERENCES application(id)
);
```

---

## 6. 统一响应格式

所有 API 统一返回以下格式：

```json
{
  "code": 200,
  "message": "success",
  "data": { ... }
}
```

| code | 含义 |
|------|------|
| 200 | 成功 |
| 400 | 参数错误 |
| 401 | 未认证 |
| 403 | 无权限 |
| 404 | 资源不存在 |
| 500 | 服务器错误 |

---

## 7. 安全设计

### 7.1 JWT 配置

- **算法**：HS256
- **Token 有效期**：7 天
- **刷新 Token 有效期**：14 天
- **Payload**：`userId`, `username`, `exp`, `iat`

### 7.2 密码安全

- 存储：BCrypt 加密
- 校验强度：至少 6 位

### 7.3 接口鉴权规则

- `/api/auth/**` 公开访问
- 其他接口需携带 `Authorization: Bearer <token>`

---

## 8. Redis 缓存策略

| 缓存 Key | 数据 | TTL |
|---------|------|-----|
| `univ:all` | 全部院校列表 | 10 分钟 |
| `univ:country:{country}` | 按国家筛选的院校 | 10 分钟 |
| `univ:{id}` | 院校详情 | 30 分钟 |
| `majors:univ:{univId}` | 某院校的专业列表 | 10 分钟 |
| `majors:all` | 全部专业列表 | 10 分钟 |

---

## 9. 前端小程序页面结构

```
pages/
├── university/
│   ├── list         # 院校列表（国家筛选）
│   └── detail       # 院校详情 + 关联专业列表
├── major/
│   ├── list         # 专业列表（院校/类型筛选）
│   └── detail       # 专业详情
├── match/
│   └── index        # 智能匹配（填写条件 → 展示结果）
├── user/
│   ├── login        # 登录注册页
│   └── profile      # 个人中心（个人信息、语言成绩等）
├── application/
│   ├── list        # 我的申请列表
│   ├── create      # 创建申请（选专业 + 填备注）
│   └── edit        # 编辑申请状态
└── tabBar:
    ├── 院校         # pages/university/list
    └── 匹配         # pages/match/index
```

### 9.1 API 层规范

```javascript
// utils/api.js 统一封装
const request = (url, options = {}) => {
  // 1. 自动附加 Token
  // 2. 统一处理 401 → 跳转登录页
  // 3. 统一 Loading/Error 提示
  return wx.request({ ... });
};
```

---

## 10. 实现优先级

### 第一阶段：基础设施
1. 统一响应格式（ResultVO、GlobalExceptionHandler）
2. JWT 安全认证（注册/登录/Token 校验）
3. Redis 配置

### 第二阶段：核心业务规范化
4. 各模块 Service 层规范化（统一异常处理）
5. API 层统一规范（参数校验）

### 第三阶段：新功能
6. 用户个人信息管理
7. 申请管理功能（CRUD + 状态变更日志）
8. 小程序端登录、个人中心、申请管理页面

### 第四阶段：优化
9. Redis 缓存集成
10. API 层优化

---

## 11. 验收标准

- [ ] 后端所有接口统一返回 ResultVO 格式
- [ ] 未登录用户无法访问受保护接口（返回 401）
- [ ] 注册/登录功能正常，JWT 可用于后续请求鉴权
- [ ] 用户可管理个人信息（GPA、语言成绩）
- [ ] 用户可创建、查看、更新申请记录
- [ ] 申请状态变更自动记录到 application_log
- [ ] 小程序端登录/注册流程完整
- [ ] 小程序端可查看和管理申请
- [ ] Redis 缓存院校/专业数据正常
- [ ] API 文档完整（swagger 或 knife4j）
