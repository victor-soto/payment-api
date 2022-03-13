# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Health Endpoint
* Open http://localhost:8080/actuator/health in browser

### H2 Database Console
* http://localhost:8080/h2-console

### Run api in docker
Build docker image
1. Generate JAR
```
.\mvnw clean package
```
2. Run Postgresql docker image
```
docker build -t payments-api .
```
2. Run api
```
docker run -e "SPRING_PROFILES_ACTIVE=local" -p 8080:9000 -t payments-api
```
