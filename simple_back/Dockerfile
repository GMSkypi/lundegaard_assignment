FROM maven:3.8.4-openjdk-17-slim AS builder  
COPY . /app  
WORKDIR /app 
RUN mvn clean package 
WORKDIR /app/target

RUN java -Djarmode=layertools -jar simple_back-0.0.1-SNAPSHOT.jar extract

FROM openjdk:17-jdk-alpine
WORKDIR /app/build

COPY --from=builder /app/target/dependencies/ /app/build/
COPY --from=builder /app/target/snapshot-dependencies/ /app/build/
COPY --from=builder /app/target/spring-boot-loader/ /app/build/
COPY --from=builder /app/target/application/ /app/build/
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]


