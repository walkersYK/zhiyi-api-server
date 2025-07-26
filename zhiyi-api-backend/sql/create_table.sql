-- 创建库
create database if not exists api;

-- 切换库
use api;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userName     varchar(256)                           null comment '用户昵称',
    userAccount  varchar(256)                           not null comment '账号',
    userAvatar   varchar(1024)                          null comment '用户头像',
    gender       tinyint                                null comment '性别',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user / admin',
    userPassword varchar(512)                           not null comment '密码',
    `accessKey` varchar(512) not null comment 'accessKey',
    `secretKey` varchar(512) not null comment 'secretKey',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    constraint uni_userAccount
        unique (userAccount)
) comment '用户';

-- 接口信息
create table if not exists api.`interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '名称',
    `description` varchar(256) null comment '描述',
    `url` varchar(512) not null comment '接口地址',
    `requestParams` text not null comment '请求参数',
    `requestHeader` text null comment '请求头',
    `responseHeader` text null comment '响应头',
    `status` int default 0 not null comment '接口状态（0-关闭，1-开启）',
    `method` varchar(256) not null comment '请求类型',
    `userId` bigint not null comment '创建人',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `descriptionImg` varchar(256) null comment '描述图片',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';

-- 用户调用接口关系表
create table if not exists api.`user_interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `userId` bigint not null comment '调用用户 id',
    `interfaceInfoId` bigint not null comment '接口 id',
    `totalNum` int default 0 not null comment '总调用次数',
    `leftNum` int default 0 not null comment '剩余调用次数',
    `status` int default 0 not null comment '0-正常，1-禁用',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '用户调用接口关系';


-- 用户调用接口关系表
create table if not exists api.`user_interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `userId` bigint not null comment '调用用户 id',
    `interfaceInfoId` bigint not null comment '接口 id',
    `totalNum` int default 0 not null comment '总调用次数',
    `leftNum` int default 0 not null comment '剩余调用次数',
    `status` int default 0 not null comment '0-正常，1-禁用',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '用户调用接口关系';
-- 图表表
create table if not exists chart
(
    id           bigint auto_increment comment 'id' primary key,
    goal				 text  null comment '分析目标',
    `name`               varchar(128) null comment '图表名称',
    chartData    text  null comment '图表数据',
    chartType	   varchar(128) null comment '图表类型',
    genChart		 text	 null comment '生成的图表数据',
    genResult		 text	 null comment '生成的分析结论',
    userId       bigint null comment '创建用户 id',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除'
) comment '图表信息表' collate = utf8mb4_unicode_ci;

-- 模拟数据：用户ID为1001的开发者创建的接口
INSERT INTO api.interface_info (name, description, url, requestParams, requestHeader, responseHeader, status, method, userId, descriptionImg)
VALUES
    -- 1. 公开用户信息查询接口（开启状态）
    (
        '用户信息查询',
        '根据用户ID获取公开资料，支持分页和字段筛选',
        'https://api.example.com/v1/users',
        '{"userId": "number", "page": "number", "size": "number", "fields": ["string"]}',
        '{"Content-Type": "application/json", "Authorization": "Bearer {token}"}',
        '{"Content-Type": "application/json", "X-Request-ID": "string"}',
        1,
        'GET',
        1001,
        'https://img.example.com/docs/user-api.png'
    ),

    -- 2. 订单创建接口（关闭状态，维护中）
    (
        '创建支付订单',
        '生成商品支付订单，返回支付链接',
        'https://api.example.com/v1/orders',
        '{"productId": "string", "quantity": "number", "paymentType": "string"}',
        '{"Content-Type": "application/json", "App-Key": "string"}',
        '{"Content-Type": "application/json", "Order-ID": "string"}',
        0,
        'POST',
        1001,
        NULL
    ),

    -- 3. 天气数据接口（开启状态）
    (
        '实时天气查询',
        '根据城市ID获取实时温度、湿度等气象数据',
        'https://api.example.com/v2/weather',
        '{"cityId": "string", "lang": "string(en/zh)"}',
        '{"Content-Type": "application/x-www-form-urlencoded"}',
        '{"Content-Type": "application/json", "Cache-Control": "max-age=300"}',
        1,
        'GET',
        1002,
        'https://img.example.com/docs/weather-api.jpg'
    ),

    -- 4. 数据统计接口（开启状态）
    (
        '接口调用统计',
        '获取指定日期范围内的接口调用次数和成功率',
        'https://api.example.com/v1/statistics',
        '{"startDate": "string(yyyy-MM-dd)", "endDate": "string(yyyy-MM-dd)", "interfaceId": "number"}',
        '{"Content-Type": "application/json", "Authorization": "Basic {credentials}"}',
        '{"Content-Type": "application/json"}',
        1,
        'POST',
        1003,
        NULL
    ),

    -- 5. 已删除的测试接口（标记为删除）
    (
        '测试删除接口',
        '仅用于测试删除功能的临时接口',
        'https://api.example.com/test/delete',
        '{"testId": "number"}',
        NULL,
        NULL,
        1,
        'DELETE',
        1001,
        NULL
    );