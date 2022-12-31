FROM openjdk:11-jre-slim
VOLUME /tmp
EXPOSE 8082
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} gateway_app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /gateway_app.jar" ]