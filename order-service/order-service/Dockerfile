FROM openjdk:11.0.7-jdk

RUN apt-get update && apt-get install -y unzip
WORKDIR /gradle
RUN curl -L https://services.gradle.org/distributions/gradle-6.5.1-bin.zip -o gradle-6.5.1-bin.zip
RUN unzip gradle-6.5.1-bin.zip

ENV GRADLE_HOME=/gradle/gradle-6.5.1
ENV PATH=$PATH:$GRADLE_HOME/bin

RUN gradle --version
RUN gradle build

EXPOSE 80
EXPOSE 8083
EXPOSE 30301
EXPOSE 9092

ENTRYPOINT ["java","-jar","/order-service-1.0.0.jar"]
