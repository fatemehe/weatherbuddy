#FROM openjdk:17-alpine
#
#WORKDIR /app
#
#COPY target/weatherbuddy-0.0.1-SNAPSHOT.jar /app/app.jar
#
#EXPOSE 8090
#
#CMD ["java", "-jar", "app.jar"]
FROM maven:3.8.3-openjdk-17 AS builder

# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/

# package jar
RUN mvn clean package

# Second stage: minimal runtime environment
From openjdk:17-alpine

WORKDIR /app

# copy jar from the first stage
COPY --from=builder target/weatherbuddy-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8090

CMD ["java", "-jar", "app.jar"]