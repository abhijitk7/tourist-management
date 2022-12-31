FROM openjdk:11-jre-slim
VOLUME /tmp
EXPOSE 8085
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} user_service_app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /user_service_app.jar" ]