FROM openjdk:11-jre-slim
VOLUME /tmp
EXPOSE 8083
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} command_app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /command_app.jar" ]