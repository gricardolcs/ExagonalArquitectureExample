FROM adoptopenjdk/openjdk11:alpine-jre
RUN mkdir -p /app/
WORKDIR /app
COPY "./build/libs/authentication-0.0.1-SNAPSHOT.jar" "authentication-0.0.1-SNAPSHOT.jar"
COPY "./build/resources/main/application.prod.properties" "application.properties"
EXPOSE 9095
ENTRYPOINT ["java","-jar","authentication-0.0.1-SNAPSHOT.jar"]