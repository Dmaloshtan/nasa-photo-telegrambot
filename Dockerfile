FROM adoptopenjdk/openjdk11:ubi

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Dspring.datasource.password=${JDBC_DATABASE_PASSWORD}", "-Dspring.datasource.username=${JDBC_DATABASE_USERNAME}", "-Dusername=$BOT_USERNAME","-Dtoken=$BOT_TOKEN", "-Dapi_key=$NASA_API_KEY","-jar","/app.jar"]

