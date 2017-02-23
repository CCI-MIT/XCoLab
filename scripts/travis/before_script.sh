#!/bin/bash
# Run Mysql Create Script
echo "MAVEN_OPTS='-Xmx1536m'" > ~/.mavenrc
echo "Running create database script"
mysql -u root xcolab < $TRAVIS_BUILD_DIR/scripts/travis/xcolab_Create.sql
echo "Copying application properties file"
cp $TRAVIS_BUILD_DIR/microservices/services/src/main/resources/application.properties $HOME/.xcolab.application.properties
echo "Copying application properties file"
mkdir $TRAVIS_BUILD_DIR/deploy
mkdir $TRAVIS_BUILD_DIR/deploy/apache-tomcat-8.0.33/webapps
mkdir $TRAVIS_BUILD_DIR/deploy/view
mkdir $TRAVIS_BUILD_DIR/deploy/webapps
touch $HOME/.xcolab.deploy.properties
echo "liferay.auto.deploy.dir=$TRAVIS_BUILD_DIR/deploy/webapps" > $HOME/.xcolab.deploy.properties
tail $HOME/.xcolab.deploy.properties