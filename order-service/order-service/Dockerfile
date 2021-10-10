FROM openjdk:11.0.7-jdk

EXPOSE 80
EXPOSE 8083
EXPOSE 30301
EXPOSE 9092

WORKDIR /order-service
COPY ./build/libs/. .

ENTRYPOINT ["java","-jar","order-service-1.0.0.jar"]
