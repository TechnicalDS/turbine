FROM maven:3.8.4-openjdk-17 as maven

WORKDIR /turbine

COPY . /turbine

RUN mvn package


FROM openjdk:17.0

ARG JAR_FILE=turbine-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

EXPOSE 8080

COPY --from=maven /turbine/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java", "-jar", "turbine-0.0.1-SNAPSHOT.jar"]