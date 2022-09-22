FROM adoptopenjdk/openjdk11:alpine-jre
RUN mkdir -p /app/
WORKDIR /app
COPY "./build/libs/discoveryservice-0.0.1-SNAPSHOT.jar" "discoveryservice-0.0.1-SNAPSHOT.jar"
COPY "./build/resources/main/application.prod.properties" "application.properties"
EXPOSE 9091
ENTRYPOINT ["java","-jar","discoveryservice-0.0.1-SNAPSHOT.jar"]