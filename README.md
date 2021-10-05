# ice_break

## 界面丑

## 实现功能

- [x] Mysql存储学生信息（名字，部门，人脸图片路径）
- [x] 每次返回若干（4）个学生名字，并展示一人的人脸图片
- [x] 用户通过鼠标点击选择名字
- [x] 页面中显示结果是否正确，若错误则显示正确答案。页面显示JSON信息
- [x] 登录注销机制，登陆者可以设置是否显示自己的相关信息



## 不足
1. 没写注册等功能，数据库建的比较乱（一开始思路比较乱）
2. 没有用SpringBoot等，主要用Mybatis，因为刚开始写的时候还在学Mybatis，后面学到已经写了不少了，就不魔改了
3. 代码思路一开始比较乱，因为做的时候还在一边学，之后思路慢慢清晰了


mysql：

```mysql
CREATE DATABASE `face_db`;

USE `face_db`;

CREATE TABLE `user`(
	`id` INT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(30) DEFAULT NULL,
	`pwd` VARCHAR(39) DEFAULT NULL,
	`show` INT(10) DEFAULT 1
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `data`(
	`id` INT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`number` VARCHAR(30) DEFAULT NULL,
	`position` VARCHAR(10) DEFAULT NULL,
	`name` VARCHAR(30) DEFAULT NULL,
	`pic` VARCHAR(30) DEFAULT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8;


INSERT INTO `user`(`id`, `name`, `pwd`) VALUES
(1, 'awei', '123'),
(2, 'jiege', '123'),
(3, 'binbin', '123'),
(4, 'zhangmeiyu', '123'),
(5, 'jinlun', '123')

INSERT INTO `data`(`id`, `number`, `position`, `name`, `pic`) VALUES
(1, 'awei', '前端', '阿伟', 'awei.png'),
(2, 'jiege', '后端', '杰哥', 'jiege.png'),
(3, 'binbin', '设计', '彬彬', 'binbin.png'),
(4, 'zhangmeiyu', '生活女工部部长', '张美玉', 'zhangmeiyu.png'),
(5, 'jinlun', '主播', '金轮', 'jinlun.png')


```





```
.
├── face
│   ├── face.iml
│   ├── out
│   │   └── artifacts
│   │       └── face_war_exploded
│   │           ├── img
│   │           │   ├── 1.png
│   │           │   ├── 2.png
│   │           │   ├── 3.png
│   │           │   ├── awei.png
│   │           │   ├── binbin.png
│   │           │   ├── fss.png
│   │           │   ├── fxy.png
│   │           │   ├── jiege.png
│   │           │   ├── jinlun.png
│   │           │   ├── jyp.png
│   │           │   ├── ljj.png
│   │           │   ├── swj.png
│   │           │   ├── zhangmeiyu.png
│   │           │   └── zmy.png
│   │           ├── jsp
│   │           │   ├── choose.jsp
│   │           │   ├── error.jsp
│   │           │   ├── index.jsp
│   │           │   ├── login.jsp
│   │           │   ├── uploadFile.jsp
│   │           │   └── welcome.jsp
│   │           ├── META-INF
│   │           │   └── MANIFEST.MF
│   │           └── WEB-INF
│   │               ├── classes
│   │               │   ├── com
│   │               │   │   └── coshe
│   │               │   │       ├── dao
│   │               │   │       │   ├── DataMapper.class
│   │               │   │       │   ├── DataMapper.xml
│   │               │   │       │   ├── UserMapper.class
│   │               │   │       │   └── UserMapper.xml
│   │               │   │       ├── filter
│   │               │   │       │   ├── CharacterEncodingFilter.class
│   │               │   │       │   └── LoginFilter.class
│   │               │   │       ├── pojo
│   │               │   │       │   ├── Data.class
│   │               │   │       │   └── User.class
│   │               │   │       ├── servlet
│   │               │   │       │   ├── ChangeShowServlet.class
│   │               │   │       │   ├── InitServlet.class
│   │               │   │       │   ├── JudgeServlet.class
│   │               │   │       │   ├── LoginServlet.class
│   │               │   │       │   ├── LogoutServlet.class
│   │               │   │       │   ├── StartServlet.class
│   │               │   │       │   └── UploadFileServlet.class
│   │               │   │       └── utils
│   │               │   │           ├── Constants.class
│   │               │   │           └── MybatisUtils.class
│   │               │   ├── db.properties
│   │               │   └── mybatis-config.xml
│   │               ├── img
│   │               ├── lib
│   │               │   ├── commons-fileupload-1.4.jar
│   │               │   ├── commons-io-2.11.0.jar
│   │               │   ├── fastjson-1.2.78.jar
│   │               │   ├── hamcrest-core-1.3.jar
│   │               │   ├── jsp-api-2.1.jar
│   │               │   ├── jstl-api-1.2.jar
│   │               │   ├── junit-4.13.jar
│   │               │   ├── lombok-1.18.20.jar
│   │               │   ├── mybatis-3.5.7.jar
│   │               │   ├── mysql-connector-java-8.0.26.jar
│   │               │   ├── protobuf-java-3.11.4.jar
│   │               │   ├── servlet-api-2.5.jar
│   │               │   └── standard-1.1.2.jar
│   │               └── web.xml
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── coshe
│   │   │   │           ├── dao
│   │   │   │           │   ├── DataMapper.java
│   │   │   │           │   ├── DataMapper.xml
│   │   │   │           │   ├── UserMapper.java
│   │   │   │           │   └── UserMapper.xml
│   │   │   │           ├── filter
│   │   │   │           │   ├── CharacterEncodingFilter.java
│   │   │   │           │   └── LoginFilter.java
│   │   │   │           ├── pojo
│   │   │   │           │   ├── Data.java
│   │   │   │           │   └── User.java
│   │   │   │           ├── servlet
│   │   │   │           │   ├── ChangeShowServlet.java
│   │   │   │           │   ├── InitServlet.java
│   │   │   │           │   ├── JudgeServlet.java
│   │   │   │           │   ├── LoginServlet.java
│   │   │   │           │   ├── LogoutServlet.java
│   │   │   │           │   ├── StartServlet.java
│   │   │   │           │   └── UploadFileServlet.java
│   │   │   │           └── utils
│   │   │   │               ├── Constants.java
│   │   │   │               └── MybatisUtils.java
│   │   │   └── resources
│   │   │       ├── db.properties
│   │   │       └── mybatis-config.xml
│   │   └── test
│   │       └── java
│   │           └── test
│   │               └── testDemo.java
│   ├── target
│   │   ├── classes
│   │   │   ├── com
│   │   │   │   └── coshe
│   │   │   │       ├── dao
│   │   │   │       │   ├── DataMapper.class
│   │   │   │       │   ├── DataMapper.xml
│   │   │   │       │   ├── UserMapper.class
│   │   │   │       │   └── UserMapper.xml
│   │   │   │       ├── filter
│   │   │   │       │   ├── CharacterEncodingFilter.class
│   │   │   │       │   └── LoginFilter.class
│   │   │   │       ├── pojo
│   │   │   │       │   ├── Data.class
│   │   │   │       │   └── User.class
│   │   │   │       ├── servlet
│   │   │   │       │   ├── ChangeShowServlet.class
│   │   │   │       │   ├── InitServlet.class
│   │   │   │       │   ├── JudgeServlet.class
│   │   │   │       │   ├── LoginServlet.class
│   │   │   │       │   ├── LogoutServlet.class
│   │   │   │       │   ├── StartServlet.class
│   │   │   │       │   └── UploadFileServlet.class
│   │   │   │       └── utils
│   │   │   │           ├── Constants.class
│   │   │   │           └── MybatisUtils.class
│   │   │   ├── db.properties
│   │   │   └── mybatis-config.xml
│   │   ├── generated-sources
│   │   │   └── annotations
│   │   ├── generated-test-sources
│   │   │   └── test-annotations
│   │   └── test-classes
│   │       └── test
│   │           └── testDemo.class
│   └── web
│       ├── img
│       │   ├── awei.png
│       │   ├── binbin.png
│       │   ├── jiege.png
│       │   ├── jinlun.png
│       │   └── zhangmeiyu.png
│       ├── jsp
│       │   ├── choose.jsp
│       │   ├── error.jsp
│       │   ├── index.jsp
│       │   ├── login.jsp
│       │   ├── uploadFile.jsp
│       │   └── welcome.jsp
│       └── WEB-INF
│           └── web.xml
└── README.md





```









