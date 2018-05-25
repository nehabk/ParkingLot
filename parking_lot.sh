#!/usr/bin/env bash
mvn clean compile assembly:single
mvn test
java -jar target/parking-lot-1.0-SNAPSHOT-jar-with-dependencies.jar