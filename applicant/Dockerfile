FROM adoptopenjdk/openjdk11:alpine-jre
RUN mkdir -p /app/
WORKDIR /app
COPY "./build/libs/applicant-0.0.1-SNAPSHOT.jar" "applicant-0.0.1-SNAPSHOT.jar"
COPY "./build/resources/main/application.prod.properties" "application.properties"
EXPOSE 9092
ENTRYPOINT ["java","-jar","applicant-0.0.1-SNAPSHOT.jar"]