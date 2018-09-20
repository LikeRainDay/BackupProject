[TOC]

# Swagger2

> 作为Controller接口说明描述，便于开发对接，加快开发效率。


# 使用方法

* 首先需要进行再`pom.xml`中增加 封装好的 **Swagger2** 的模块 
```xml
        <dependency>
            <groupId>com.andy.doc</groupId>
            <artifactId>andy-swagger</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
```

* 在SpringBoot的`main` 进行开启使用. 例如如下。


```java

@ComponentScan(value = ["com.andy"])
@EnableSwagger2
class ServierUserCenterApplication

```

* 完成以上步骤后


* 更改为对应的IP:端口 eg: [localhost:8080/swagger-ui.html](localhost:8080/swagger-ui.html) 

# 参考文档
* [Swagger2的官方文档](https://swagger.io/solutions/api-documentation/)
* [Gitbook 讲解的方式](https://gumutianqi1.gitbooks.io/specification-doc/content/tools-doc/spring-boot-swagger2-guide.html)
