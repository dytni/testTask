FROM openjdk:21

ARG JAR_FILE=target/*.jargi
COPY ./target/test-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://172.31.48.1:5432/test
ENV SPRING_DATASOURCE_USERNAME=dytni
ENV SPRING_DATASOURCE_PASSWORD=1331

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jаr"]

# docker run -p 8080:8080 test
# docker build -t test .