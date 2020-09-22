#!/usr/bin/env bash

DELIVERYSECRETS=$(aws secretsmanager get-secret-value --secret-id deliveryDatabaseCredentials | python3 -c "import sys, json; print(json.load(sys.stdin)['SecretString'])")
DATABASE_URL=$(echo $DELIVERYSECRETS | python3 -c "import sys, json; print(json.load(sys.stdin)['url'])")
DATABASE_USERNAME=$(echo $DELIVERYSECRETS | python3 -c "import sys, json; print(json.load(sys.stdin)['username'])")
DATABASE_PASSWORD=$(echo $DELIVERYSECRETS | python3 -c "import sys, json; print(json.load(sys.stdin)['password'])")

./delivery-db-migrate/bin/delivery-db-migrate --driver=org.postgresql.Driver --url=${DATABASE_URL} --username=${DATABASE_USERNAME} --password=${DATABASE_PASSWORD} --changeLogFile=changelog.xml update
