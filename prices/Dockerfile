FROM eclipse-temurin:21-jdk AS build
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml pom.xml

RUN ./mvnw dependency:go-offline

COPY ./src ./src
RUN ./mvnw clean install -Dmaven.test.skip=true

FROM alpine/java:21-jdk
WORKDIR /app
COPY --from=build /app/target/prices-backend-test-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar","--enable-preview" , "app.jar"]