FROM openjdk:11-jre-slim
VOLUME /tmp
EXPOSE 9011
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} config_server_app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /config_server_app.jar" ]