#!/bin/sh

echo "Waiting \`db\` service to be ready..."
while ! nc -z db 3306; do sleep 15; done

java -jar target/${ARTIFACT_NAME}
