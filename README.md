# Getting Started

使用SpringBoot和Spring-Cloud-Alibaba生态系统开发的微服务项目

### Guides
The following guides illustrate how to use some features concretely:

#### Start Nacos

> git clone https://github.com/alibaba/nacos.git
> cd nacos/
> mvn -Prelease-nacos -Dmaven.test.skip=true clean install -U
> ls -al distribution/target/
> cd distribution/target/nacos-server-$version/nacos/bin

Or
> cd nacos/bin
> bash startup.sh -m standalone

Open [nacos local page](127.0.0.1/nacos),watch services online.

#### Start Sentinel dashboard

> java -Dserver.port=8089 -Dcsp.sentinel.dashboard.server=localhost:8089 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar

Open 127.0.0.1:8089, look sentinel dashboard

### Reference Documentation
For further reference, please consider the following sections:

* [Spring Cloud Alibaba](https://github.com/alibaba/spring-cloud-alibaba)
* [Spring doc Open API](https://github.com/springdoc/springdoc-openapi)
* [Nacos Document](https://nacos.io/)
* [Sentinel Wiki](https://github.com/alibaba/Sentinel/wiki/)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#using-boot-devtools)

