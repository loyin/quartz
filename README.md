## 

## 技术选型

* 主框架：Spring Boot 2.1.1、Spring Framework 5.1.3
* 持久层：Spring Data Jpa 2.1.3
* 视图层：Spring MVC 5.1.3、thymeleaf 替换JSP
* 前端组件：jQuery 3.3.1、layui 2.4.5
* 工具组件：Quartz 2.2.3
* 全文搜索：Elasticsearch 6.5.4
* 数据库：Mongodb 4.0.5、MySQL 5.7或Mariadb 10.3.12 
### 本地运行

1. 环境准备：`JDK 1.8`、`Maven 3.6`、`MySQL 5.7`
2. 下载源码：<https://github.com/duchao19931220/quartz>
3. 执行初始化数据库脚本，路径/src/resources/db, 
   ```
   执行顺序：
   tables_init_database.sql, 
   tables_mysql.sql,
   job_info.sql, 
   user_info.sql
   ```
4. 在idea中导入源码，执行QuartzApplication.main();

# 技术交流方式

* QQ 群号：`901257529`
