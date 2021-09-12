FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=
ENV BOT_TOKEN=
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}","-Dbot.token=${BOT_TOKEN}","-jar","/app.jar"]