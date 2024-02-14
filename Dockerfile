# FROM maven:3.9.5-eclipse-temurin-17-alpine AS build
# LABEL authors="Chief"

# # Set the working directory inside the container to /app
# WORKDIR /app

# # Copy the entire current directory (.) into the container at /app
# COPY . .

# # Download MySQL Connector/J JAR file
# RUN wget -O mysql-connector-java.jar https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.28/mysql-connector-java-8.0.28.jar

# # Run Maven to clean, build, and install the project, skipping tests
# RUN mvn clean install -DskipTests -X

# # Use the WildFly image with JDK 17 as the deployment environment
# FROM quay.io/wildfly/wildfly:26.1.3.Final-jdk17 AS deploy

# # Remove the default WildFly standalone.xml configuration file
# RUN rm /opt/jboss/wildfly/standalone/configuration/standalone.xml

# # Copy the compiled WAR file from the build stage to WildFly deployments directory
# COPY --from=build /app/target/votify-v2.war /opt/jboss/wildfly/standalone/deployments/

# # Copy a custom standalone.xml configuration file to WildFly configuration directory
# COPY --from=build /app/standalone.xml /opt/jboss/wildfly/standalone/configuration/

# # Create directories and copy MySQL module configuration and JDBC driver to WildFly modules directory
# RUN mkdir -p /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/
# COPY --from=build /app/module.xml /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/
# COPY --from=build /app/mysql-connector-java.jar /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/

# # Expose port 8080 for the application
# EXPOSE 8081

# # Set the default command to start WildFly and bind it to all network interfaces
# CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]

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

# Copy the entrypoint script
# COPY dumpvotify.sql /opt/jboss/wildfly/bin/
# COPY entryPoint.sh /opt/jboss/wildfly/bin/
# USER root
# RUN chown root:root /opt/jboss/wildfly/bin/entryPoint.sh

# RUN chmod +x /opt/jboss/wildfly/bin/entryPoint.sh


EXPOSE 8080

# ENTRYPOINT ["/opt/jboss/wildfly/bin/entryPoint.sh"]



CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]