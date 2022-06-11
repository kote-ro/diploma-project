FROM maven:3.8-openjdk-11-slim as builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:11
WORKDIR /app
ADD data/* /app/
COPY --from=builder /app/target/diploma-project-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar","/app/app.jar"]




