FROM openjdk:17

ARG JAR_FILE=target/*.jar
COPY ./target/test-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5433/testDB
ENV SPRING_DATASOURCE_USERNAME=dytni
ENV SPRING_DATASOURCE_PASSWORD=1331

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

# docker run -p 8080:8080 test
# docker build -t test .