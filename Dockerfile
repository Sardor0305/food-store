FROM openjdk:17-jdk-slim
RUN mkdir -p /usr/local/food-store
ADD target/food-store-0.0.1-SNAPSHOT.jar /usr/local/food-store/
EXPOSE 8080
ENTRYPOINT ["java", \
           "-Dserver.port=8080", \
           "-Dspringdoc.swagger-ui.enabled=true", \
           "-jar", \
           "/usr/local/food-store/food-store-0.0.1-SNAPSHOT.jar"]