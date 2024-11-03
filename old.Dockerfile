# Dockerfile para la aplicación Spring Boot
FROM maven:3.8-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa para ejecutar la aplicación
WORKDIR /app
COPY --from=build /app/target/series-ranking.jar series-ranking.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "series-ranking.jar"]