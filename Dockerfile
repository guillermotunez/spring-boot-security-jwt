FROM maven:3.8.2-jdk-11
WORKDIR /spring-boot-app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
#COPY . .
#RUN mvn clean package
#CMD mvn spring-boot:run