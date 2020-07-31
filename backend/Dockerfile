FROM maven:3.6.3-openjdk-14-slim AS builder

WORKDIR /usr/local/api

ARG JAR_NAME=snackbar-0.0.1-SNAPSHOT.jar

ENV ARTIFACT_NAME ${JAR_NAME}
ENV SNACKBAR_DATABASE_HOST=db
ENV SNACKBAR_DATABASE_PORT=3306
ENV SNACKBAR_DATABASE_USER=root
ENV SNACKBAR_DATABASE_PASSWORD=root

RUN apt-get update \
    && apt-get install ncat -y \
    && apt-get autoremove -y \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

COPY . .

RUN mvn clean package

EXPOSE 8080

CMD ["sh", "docker-entrypoint.sh"]
