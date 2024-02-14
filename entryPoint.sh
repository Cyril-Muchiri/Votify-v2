#!/bin/bash

# Start WildFly server in the background
/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 &

# Wait for WildFly to be ready
until `curl --output /dev/null --silent --head --fail http://localhost:8080`; do
    echo "Waiting for WildFly..."
    sleep 5
done

# Execute the SQL dump to populate the database
/opt/jboss/wildfly/bin/mysql -h"$MYSQL_HOST" -uroot -p"$MYSQL_ROOT_PASSWORD" "$MYSQL_DATABASE" < /opt/jboss/wildfly/bin/dumpvotify.sql

# Keep the script running
tail -f /dev/null