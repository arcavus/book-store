FROM adoptopenjdk/openjdk11:ubi

VOLUME /tmp
EXPOSE 9090
ARG JAR_FILE=getir-bookstore.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=dev","-DskipTests","/app.jar"]