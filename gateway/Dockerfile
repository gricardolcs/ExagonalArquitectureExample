FROM adoptopenjdk/openjdk11:alpine-jre
RUN mkdir -p /app/
WORKDIR /app
COPY "./build/libs/gateway-0.0.1-SNAPSHOT.jar" "gateway-0.0.1-SNAPSHOT.jar"
COPY "./build/resources/main/application.prod.properties" "application.properties"
EXPOSE 9094
ENTRYPOINT ["java","-jar","gateway-0.0.1-SNAPSHOT.jar"]