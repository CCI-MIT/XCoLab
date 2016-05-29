#!/bin/bash

cd themes/climatecolab-theme
  mvn clean compile liferay:build-css package liferay:deploy clean
cd ../..
