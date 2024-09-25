FROM openjdk:21
LABEL authors="Sakawijaya"

RUN microdnf update \
 && microdnf install --nodocs wget unzip \
 && microdnf clean all \
 && rm -rf /var/cache/yum

#ARG JAR_FILE=target/*.jar
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} report-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","report-0.0.1-SNAPSHOT.jar"]