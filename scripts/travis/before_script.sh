#!/bin/bash

echo "Configuring maven options"
echo "MAVEN_OPTS='-Xmx1536m -XX:+TieredCompilation -XX:TieredStopAtLevel=1'" > ~/.mavenrc

echo "Running create database script"
mysql -e 'CREATE DATABASE IF NOT EXISTS xcolab;'
mysql -u root xcolab < sql/starter/xcolab-schema.sql

echo "Copying application properties file"
cp microservices/services/src/main/resources/application.properties $HOME/.xcolab.application.properties
