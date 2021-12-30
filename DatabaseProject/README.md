
---

![lisense](https://img.shields.io/github/license/Antabot/White-Jotter)
![release](https://img.shields.io/github/v/release/Antabot/White-Jotter)

参考框架：白卷
https://github.com/Antabot/White-Jotter

# 主要技术栈

## 前端

1.Vue.js  
2.ElementUI  
3.axios   

## 后端

1.Spring Boot  
2.Apache Shiro
3.Apache Log4j2
4.Spring Data JPA
5.Spring Data Redis

## 数据库
1.MySQL
2.Redis

# 部署方法

1.clone 项目到本地

`git clone https://github.com/chenduowen233/Database-Myblog.git`

2.数据库配置在后端项目的 `src\main\resources` 目录下的`application.properties` 文件中，mysql 版本为 8.0.15   。

3.在IntelliJ IDEA中运行后端项目，为了保证项目成功运行，可以右键点击 `pom.xml` 选择 maven -> reimport ，并重启项目

至此，服务端就启动成功了，同时，运行前端项目，访问 `http://localhost:8080` ，即可进入登录页面，默认账号是 `admin`，密码是 `123`

