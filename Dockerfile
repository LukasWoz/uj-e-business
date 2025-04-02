FROM ubuntu:22.04
RUN apt-get update
RUN apt-get install -y software-properties-common
RUN add-apt-repository ppa:deadsnakes/ppa


RUN apt-get install -y python3.10 

RUN apt-get install -y openjdk-8-jdk
RUN apt-get install -y kotlin

RUN apt-get install -y gradle

WORKDIR /app

COPY build.gradle .
RUN gradle build --refresh-dependencies

CMD /bin/bash
