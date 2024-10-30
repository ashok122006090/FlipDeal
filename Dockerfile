FROM openjdk:23
WORKDIR /app
COPY ./target/flipdeal-0.0.1-SNAPSHOT.jar flipdeal.jar
EXPOSE 8080
CMD ["java", "-jar", "flipdeal.jar"]
