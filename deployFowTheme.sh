#!/bin/bash

cd themes/climatecolab-theme
  mvn clean compile package install
cd ../..

cd themes/fow-theme
  mvn clean compile liferay:build-css package liferay:deploy
cd ../..
