FROM eclipse-temurin:17-jdk-focal
ARG JAR_FILE=../build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]