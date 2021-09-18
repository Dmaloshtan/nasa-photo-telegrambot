FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.datasource.password=$BOT_DB_PASSWORD", "-Dspring.datasource.username=$BOT_DB_USERNAME", "-Dusername=$BOT_USERNAME","-Dtoken=$BOT_TOKEN","-jar","/app.jar"]