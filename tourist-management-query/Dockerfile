FROM openjdk:11-jre-slim
VOLUME /tmp
EXPOSE 8084
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} query_app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /query_app.jar" ]