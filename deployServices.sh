#!/bin/sh

cd microservices/util/jooq-config
mvn clean compile package install clean
cd ../../..

cd microservices/services
for D in *-service; do
    if [ -d "${D}" ]; then
        cd $D
        pwd
        mvn compile package spring-boot:repackage
        cd ..
    fi
done
cd ../..