#!/bin/bash

cd themes/climatecolab-theme
  mvn clean compile package install
cd ../..

cd themes/solve-theme
  mvn clean compile liferay:build-css package liferay:deploy
cd ../..
