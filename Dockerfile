FROM maven:3.9.5-eclipse-temurin-17-alpine AS build
LABEL authors=" "


WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests -X


FROM quay.io/wildfly/wildfly:26.1.3.Final-jdk17 AS deploy

RUN rm /opt/jboss/wildfly/standalone/configuration/standalone.xml

COPY --from=build /app/target/votify-v2.war /opt/jboss/wildfly/standalone/deployments/
COPY --from=build /app/standalone.xml /opt/jboss/wildfly/standalone/configuration/


RUN mkdir -p /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/
COPY --from=build /app/module.xml /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/
COPY --from=build /app/mysql-connector-j-8.2.0.jar /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/


EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]