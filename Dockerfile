# Build Stage
FROM maven:3.6.3 AS build
WORKDIR /app
COPY . .
RUN mvn clean compile package

# Deployment Stage
FROM jboss/wildfly:latest AS deploy
COPY --from=build /app/target/Votify.war /opt/jboss/wildfly/standalone/deployments/
RUN rm /opt/jboss/wildfly/standalone/configuration/standalone.xml
COPY --from=build app/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml
COPY --from=build /app/mysql  /opt/jboss/wildfly/modules/system/layers/base/com


EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
