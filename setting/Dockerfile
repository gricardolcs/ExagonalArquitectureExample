FROM adoptopenjdk/openjdk11:alpine-jre
RUN mkdir -p /app/
WORKDIR /app
COPY "./build/libs/setting-0.0.1-SNAPSHOT.jar" "setting-0.0.1-SNAPSHOT.jar"
COPY "./build/resources/main/application.prod.properties" "application.properties"
EXPOSE 9096
ENTRYPOINT ["java","-jar","setting-0.0.1-SNAPSHOT.jar"]