FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app

COPY . /app

RUN --mount=type=cache,target=/root/.m2 mvn -f /app/pom.xml clean package

FROM eclipse-temurin:21-alpine
 
COPY --from=builder /app/target/stocks*.jar /stocks.jar
 
CMD ["java", "-Xmx500m", "-jar", "/stocks.jar"]
