#!/bin/bash

echo "Configuring maven options"
echo "MAVEN_OPTS='-Xmx1536m -XX:+TieredCompilation -XX:TieredStopAtLevel=1'" > ~/.mavenrc

echo "Running create database script"
mysql -u root xcolab < scripts/travis/xcolab_Create.sql

echo "Copying application properties file"
cp microservices/services/src/main/resources/application.properties $HOME/.xcolab.application.properties

if [ $TRAVIS_BRANCH == 'homolog' ] || [ $TRAVIS_BRANCH == 'master' ]; then
    BUILD_TYPE=deploy

    echo "Creating binary directories"
    mkdir binaries
    mkdir binaries/view
    mkdir binaries/cloud
    mkdir binaries/services
else
#    BUILD_TYPE=test
    #testing deploy step with new environment
    BUILD_TYPE=deploy
fi