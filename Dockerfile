FROM eclipse-temurin:21-jdk

WORKDIR /enterpriseauthstarter

COPY target/*.jar enterpriseauthstarter-0.0.1.SNAPSHOT.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "enterpriseauthstarter-0.0.1.SNAPSHOT.jar"]