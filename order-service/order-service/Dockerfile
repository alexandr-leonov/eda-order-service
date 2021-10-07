FROM alpine:latest as packager

RUN apk --no-cache add openjdk11-jdk openjdk11-jmods

ENV JAVA_MINIMAL="/opt/java-minimal"

# build minimal JRE
RUN /usr/lib/jvm/java-11-openjdk/bin/jlink \
    --verbose \
    --add-modules \
        java.base,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument \
    --compress 2 --strip-debug --no-header-files --no-man-pages \
    --release-info="add:IMPLEMENTOR=radistao:IMPLEMENTOR_VERSION=radistao_JRE" \
    --output "$JAVA_MINIMAL"

FROM alpine:latest

ENV JAVA_HOME=/opt/java-minimal
ENV PATH="$PATH:$JAVA_HOME/bin"

EXPOSE 80
EXPOSE 8083
EXPOSE 30301
EXPOSE 9092

RUN ls && cd build/libs && ls
COPY --from=packager "$JAVA_HOME" "$JAVA_HOME"
COPY build/libs/order-service-1.0.0.jar order-service-1.0.0.jar

ENTRYPOINT ["java","-jar","/order-service-1.0.0.jar"]
