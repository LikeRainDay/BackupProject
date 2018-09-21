[TOC]


# 数据库模块
> 目的：主要简化集成方式，并对其相关数据库的内容进行封装。

# 数据库

## datasource_arango

> 此数据库进行存储图关系

1. [ArangoDB官方首页](https://www.arangodb.com/)
2. [Java 使用方式导航](https://www.arangodb.com/tutorials/tutorial-sync-java-driver/)


## datasource_jpa

> 此模块作为对Mysql的ORM框架，进行快速开发操作Mysql数据库

## datasource_redis

> 此模块作为高度读取数据（ 注意 由于Redis为单线程模型，所有耗时操作都不适用，只是用来进行的数据的get 和 set ）

[Redis官网](https://redis.io/)

## datasource_mongodb

> 此模块作为 Gson的一些复杂实体的存储。 


## datasource_elasticsearch

> 此模块作为 全文检索使用，一些需要进行大数据量的查询数据存放在此数据库中。