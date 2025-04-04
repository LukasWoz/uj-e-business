FROM ubuntu:22.04

RUN apt-get update
RUN apt-get install -y software-properties-common
RUN add-apt-repository ppa:deadsnakes/ppa

RUN apt-get install -y python3.10 

RUN apt-get install -y openjdk-8-jdk
RUN apt-get install -y kotlin

RUN apt-get install -y gradle

RUN pwd

ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-arm64
ENV PATH="${PATH}:${JAVA_HOME}/bin"

WORKDIR /app

COPY build.gradle .
COPY settings.gradle .
COPY src/ src/

RUN gradle build --refresh-dependencies --no-daemon


CMD ["gradle", "run", "--no-daemon"]

