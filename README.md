# 智易API开放平台

<div align="center">

![Java](https://img.shields.io/badge/Java-8+-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.2-green.svg)
![MyBatis Plus](https://img.shields.io/badge/MyBatis%20Plus-3.5.1-blue.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)
![Redis](https://img.shields.io/badge/Redis-6.0+-red.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

**一个基于Spring Boot的API开放平台，提供接口管理、用户管理、智能分析等功能**

[项目介绍](#项目介绍) • [技术栈](#技术栈) • [功能特性](#功能特性) • [快速开始](#快速开始) • [API文档](#api文档) • [项目结构](#项目结构)

</div>

---

## 📖 项目介绍

智易API开放平台是一个功能完整的API管理系统，旨在为开发者提供便捷的接口发布、管理和调用服务。平台支持用户注册登录、接口信息管理、调用统计、智能数据分析等核心功能，并集成了AI智能分析能力。

### 🎯 核心特性

- 🔐 **用户认证与授权** - 基于JWT的用户认证系统
- 📊 **接口管理** - 完整的接口CRUD操作和状态管理
- 📈 **调用统计** - 实时监控接口调用次数和成功率
- 🤖 **AI智能分析** - 集成AI能力，支持数据可视化分析
- 🚀 **SDK支持** - 提供客户端SDK，简化接口调用
- 📝 **API文档** - 基于Knife4j的在线API文档
- 🔒 **安全防护** - 接口签名验证和访问控制

## 🛠 技术栈

| 技术领域 | 技术选型 | 版本 | 说明 |
|---------|---------|------|------|
| **后端框架** | Spring Boot | 2.7.2 | 主框架 |
| **数据库** | MySQL | 8.0+ | 主数据库 |
| **缓存** | Redis | 6.0+ | 缓存和限流 |
| **ORM框架** | MyBatis Plus | 3.5.1 | 数据库操作 |
| **API文档** | Knife4j | 3.0.3 | 接口文档生成 |
| **认证授权** | JWT | 3.4.1 | 用户认证 |
| **工具库** | Hutool | 5.8.8 | 工具类库 |
| **Excel处理** | EasyExcel | 3.1.1 | Excel导入导出 |
| **分布式** | Dubbo | 3.0.9 | 微服务通信 |
| **AI集成** | 鱼聪明SDK | 0.0.2 | AI能力集成 |
| **限流** | Redisson | 3.21.3 | 分布式限流 |

## ✨ 功能特性

### 🔐 用户管理
- 用户注册、登录、注销
- JWT令牌认证
- 用户角色权限控制
- 用户信息管理

### 📊 接口管理
- 接口信息CRUD操作
- 接口状态管理（开启/关闭）
- 接口参数配置
- 接口调用测试

### 📈 调用统计
- 用户接口调用次数统计
- 剩余调用次数管理
- 调用历史记录
- 统计分析报表

### 🤖 AI智能分析
- 数据可视化分析
- 智能图表生成
- Excel数据导入分析
- 分析结果导出

### 🔒 安全特性
- 接口签名验证
- 访问频率限制
- 用户权限控制
- 数据安全保护

## 🚀 快速开始

### 环境要求

- JDK 8+
- MySQL 8.0+
- Redis 6.0+
- Maven 3.6+

### 安装部署

1. **克隆项目**
```bash
git clone https://github.com/your-username/zhiyi-api-backend.git
cd zhiyi-api-backend
```

2. **数据库初始化**
```bash
# 创建数据库
mysql -u root -p
CREATE DATABASE api CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# 执行SQL脚本
mysql -u root -p api < sql/create_table.sql
```

3. **配置文件修改**
```yaml
# application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/api?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2b8
    username: your_username
    password: your_password
  
  redis:
    host: localhost
    port: 6379
    database: 1

# AI配置
yuapi:
  client:
    access-key: your_access_key
    secret-key: your_secret_key
```

4. **启动项目**
```bash
# 编译项目
mvn clean compile

# 启动应用
mvn spring-boot:run
```

5. **访问应用**
- 应用地址: http://localhost:9090
- API文档: http://localhost:9090/doc.html

## 📚 API文档

### 用户相关接口

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 用户注册 | POST | `/user/register` | 新用户注册 |
| 用户登录 | POST | `/user/login` | 用户登录认证 |
| 用户注销 | POST | `/user/logout` | 用户退出登录 |
| 获取当前用户 | GET | `/user/get/login` | 获取登录用户信息 |
| 用户列表 | GET | `/user/list/page` | 分页获取用户列表 |

### 接口管理

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 创建接口 | POST | `/interfaceInfo/add` | 新增接口信息 |
| 更新接口 | POST | `/interfaceInfo/update` | 更新接口信息 |
| 删除接口 | POST | `/interfaceInfo/delete` | 删除接口信息 |
| 获取接口 | GET | `/interfaceInfo/get` | 根据ID获取接口 |
| 接口列表 | GET | `/interfaceInfo/list/page` | 分页获取接口列表 |
| 发布接口 | POST | `/interfaceInfo/online` | 发布接口 |
| 下线接口 | POST | `/interfaceInfo/offline` | 下线接口 |
| 调用接口 | POST | `/interfaceInfo/invoke` | 测试调用接口 |

### 智能分析

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 创建图表 | POST | `/chart/add` | 新增图表信息 |
| 智能分析 | POST | `/chart/gen` | AI智能数据分析 |
| 图表列表 | POST | `/chart/list/page` | 分页获取图表列表 |
| 我的图表 | POST | `/chart/my/list/page` | 获取我的图表列表 |

### 文件管理

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 文件上传 | POST | `/file/upload` | 上传文件 |
| 文件下载 | GET | `/file/download` | 下载文件 |

## 📁 项目结构

```
zhiyi-api-backend/
├── src/main/java/com/yunjin/api_backed/
│   ├── algorithm/           # 算法相关
│   │   ├── py/             # Python算法脚本
│   │   └── TextSimilarityUtil.java
│   ├── annotation/         # 自定义注解
│   ├── aop/               # 切面编程
│   ├── common/            # 公共组件
│   │   ├── BaseResponse.java
│   │   ├── ErrorCode.java
│   │   └── utils/
│   ├── config/            # 配置类
│   ├── constant/          # 常量定义
│   ├── context/           # 上下文
│   ├── controller/        # 控制器层
│   ├── exception/         # 异常处理
│   ├── manager/           # 管理器
│   ├── mapper/            # 数据访问层
│   ├── model/             # 数据模型
│   │   ├── dto/          # 数据传输对象
│   │   ├── entity/       # 实体类
│   │   ├── enums/        # 枚举类
│   │   └── vo/           # 视图对象
│   ├── properties/        # 配置属性
│   ├── service/          # 业务逻辑层
│   │   └── impl/         # 实现类
│   └── utils/            # 工具类
├── src/main/resources/
│   ├── application.yml    # 配置文件
│   ├── mapper/           # MyBatis映射文件
│   └── static/           # 静态资源
├── sql/                  # 数据库脚本
├── files/               # 文件存储目录
└── pom.xml             # Maven配置
```

## 🔧 配置说明

### 数据库配置
```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/api?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2b8
    username: root
    password: your_password
```

### Redis配置
```yaml
spring:
  redis:
    database: 1
    host: localhost
    port: 6379
    timeout: 5000
```

### 文件上传配置
```yaml
spring:
  servlet:
    multipart:
      max-file-size: 20MB
      max-request-size: 20MB
```

## 🤝 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 📞 联系我们

- 项目维护者: 韵锦
- 邮箱: your-email@example.com
- 项目地址: https://github.com/your-username/zhiyi-api-backend

## 🙏 致谢

感谢以下开源项目的支持：

- [Spring Boot](https://spring.io/projects/spring-boot)
- [MyBatis Plus](https://baomidou.com/)
- [Knife4j](https://doc.xiaominfo.com/)
- [Hutool](https://hutool.cn/)
- [EasyExcel](https://github.com/alibaba/easyexcel)

---

<div align="center">

**如果这个项目对你有帮助，请给它一个 ⭐️**

</div> 
