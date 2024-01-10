FROM sapmachine:21-jdk-ubuntu-22.04
LABEL authors="Ale"
ADD target/quiz-service.jar quiz-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "quiz-service.jar"]