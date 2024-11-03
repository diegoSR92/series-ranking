# Usa una imagen base de OpenJDK
FROM maven:3.8-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "series-ranking.jar"]