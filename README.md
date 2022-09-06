## 简介

TANSCI MALL 是基于 SpringBoot + Vue3.2 + Element Plus 微商城 SaaS 系统

## 架构

### 技术栈

#### 1、环境要求

- JDK.version：1.8+  强制要求
- MySql.version：5.7+  数据库
- Node.version：14.16+  前端环境要求
- Nginx.version：1.16+  请求转发、反向代理
- Maven.version：3.8+  项目构建，管理
- Git.version：2.14+  项目版本管理

#### 2、后端
- java.version：1.8
- spring-boot.version：2.7.1
- mybatis-plus.version：3.5.2
- jjwt.version：0.9.1
- knife4j-spring-boot.version：3.0.3
- mysql.version：5.1.46

#### 3、前端技术

- vite.version：2.9.9
- vue.version：3.2.25
- vue-router.version：4.0.15
- element-plus.version：2.2.2
- sass.version：1.51.0
- axios.version：0.27.2
- pinia.version：2.0.13
- echarts.version：5.3.2

---

## 详细文档

**1.启动后端**

创建数据库，执行 docs/tansci_mall.sql SQL初始化数据，测试用户：`admin / admin123`。

> 安全认证登录示例：
> 
> 地址：[http://localhost:8090/tansci/user/login](http://localhost:8090/tansci/user/login)
> 
> - 请求参数：
> ```json
> {"username": "admin", "password": "admin123"}
> ```
> 
> - 返回参数：
> ```json
> {
>    "code": 200,
>    "fail": false,
>    "message": "操作成功",
>    "result": {
>        "loginTime": "2022-02-16 11:58:29",
>        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWJqZWN0IiwiZXhwIjoxNjQ0OTkxMTA4LCJwYXlsb2FkIjoie1wiYWNjb3VudE5vbkV4cGlyZWRcIjp0cnVlLFwiYWNjb3VudE5vbkxvY2tlZFwiOnRydWUsXCJhdXRob3JpdGllc1wiOlt7XCJhdXRob3JpdHlcIjpcIlJPTEVfTUVSQ0hBTlRTXCJ9XSxcImNyZWRlbnRpYWxzTm9uRXhwaXJlZFwiOnRydWUsXCJlbmFibGVkXCI6dHJ1ZSxcImlkXCI6XCJiYzNhYzI2ZTY5NzMxYjYxN2ViODAyNzQ0NTNmNmRiYVwiLFwicGFzc3dvcmRcIjpcIiQyYSQxMCR0bFdXZmpUT2JxTHNDNk9OcmhOTFwvLkdJcEFvRnUyMDVUWFBLNnhVUEhIcjFrQVwvcGFLNGxxXCIsXCJ1c2VybmFtZVwiOlwiYWRtaW5cIn0ifQ.8fXiQqlP9SLJK-_sPxws98VrUbDs5kvFysPmn3-Aqu8",
>        "username": "admin"
>    },
>    "success": true
>  }
> ```
> 
> - 设置Headers:
> ```json
> "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWJqZWN0IiwiZXhwIjoxNjQ0ODk4NjY0LCJwYXlsb2FkIjoie1wiYWNjb3VudE5vbkV4cGlyZWRcIjp0cnVlLFwiYWNjb3VudE5vbkxvY2tlZFwiOnRydWUsXCJhdXRob3JpdGllc1wiOlt7XCJhdXRob3JpdHlcIjpcIlJPTEVfTUVSQ0hBTlRTXCJ9XSxcImNyZWRlbnRpYWxzTm9uRXhwaXJlZFwiOnRydWUsXCJlbmFibGVkXCI6dHJ1ZSxcImlkXCI6XCJiYzNhYzI2ZTY5NzMxYjYxN2ViODAyNzQ0NTNmNmRiYVwiLFwicGFzc3dvcmRcIjpcIiQyYSQxMCR0bFdXZmpUT2JxTHNDNk9OcmhOTFwvLkdJcEFvRnUyMDVUWFBLNnhVUEhIcjFrQVwvcGFLNGxxXCIsXCJ1c2VybmFtZVwiOlwiYWRtaW5cIn0ifQ.54GyxZs77ADpE_KYzdy8EAPOLYWL4AqcJDQId_SYCok"
> ```

**2.启动前端**

```shell
npm install  # 初始化项目

npm run dev  # 启动项目
```

**3.访问**
- 后台管理：[http://localhost:3000/login](http://localhost:3000/login)
- 商城：[http://localhost:3000/app/index](http://localhost:3000/app/index)

## 联系开发者

- QQ：742354529
- QQ群：747200630
- 个人主站：[http://tansci.top](http://tansci.top)、[https://typ1805.gitee.io](https://typ1805.gitee.io)
- 个人公众号：
![欢迎关注](./docs/image/gzh.jpg)

## 预览

**后台**

![后台登录](./docs/image/admin-login.png)

![工作台](./docs/image/admin-main.png)

![商品管理](./docs/image/admin-goods.png)

**商城**
![用户信息](./docs/image/mobil-setting.png)

![商城](./docs/image/mobil-index.png)
