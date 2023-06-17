FROM maven:3.8.2-eclipse-temurin-17 AS maven_build
WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn clean install
RUN mv target/*.jar target/app.jar  

# Using Java 17: 
FROM eclipse-temurin:17_35-jdk-alpine
COPY --from=maven_build /usr/src/app/target/app.jar ./
ENTRYPOINT ["java","-jar","app.jar"]
