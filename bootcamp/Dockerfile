FROM adoptopenjdk/openjdk11:alpine-jre
RUN mkdir -p /app/
WORKDIR /app
COPY "./build/libs/bootcamp-0.0.1-SNAPSHOT.jar" "bootcamp-0.0.1-SNAPSHOT.jar"
COPY "./build/resources/main/application.prod.properties" "application.properties"
EXPOSE 9093
ENTRYPOINT ["java", "-jar", "bootcamp-0.0.1-SNAPSHOT.jar"]