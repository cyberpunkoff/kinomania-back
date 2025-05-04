FROM gradle:8-jdk AS build
WORKDIR /opt/app

COPY build.gradle settings.gradle ./
COPY ./src ./src

RUN gradle clean bootJar

FROM eclipse-temurin:21.0.2_13-jre-jammy AS run
WORKDIR /opt/app

EXPOSE 8080

COPY --from=builder /opt/app/build/libs/*.jar /opt/app/*.jar

ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]
