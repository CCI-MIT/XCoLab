#!/bin/bash

# stop script if any build fails
set -e

cd themes/climatecolab-theme
  mvn clean compile package install
cd ../..

cd themes/rd-theme
  mvn clean compile liferay:build-css package liferay:deploy clean
cd ../..
