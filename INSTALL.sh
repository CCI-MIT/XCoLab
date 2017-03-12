#!/bin/bash

# stop script if any build fails
set -e

echo "Running Climate CoLab Development Environment Installer"

# Check Java Version and check if Java is in Path
if type -p java; then
    echo "[INFO] Found java executable in PATH"
    _java=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
    echo "[INFO] Found java executable in JAVA_HOME"     
    _java="$JAVA_HOME/bin/java"
else
    echo "[ERROR] No Java installation found. Please make sure Java 1.7 is installed and set up in PATH."
    exit 0
fi

if [[ "$_java" ]]; then
    version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    echo "[INFO] Java Version" "$version"
    if [[ "$version" > "1.7" ]]; then
        echo "[INFO] Java version is sufficient (>1.7)"
    else         
        echo "[ERROR] Java version is below 1.7. Please make sure Java 1.7 is installed and set up in PATH."
	exit 0
    fi
fi

#Check MySQL Version
if type -p mysql; then
    echo "[INFO] Found mysql executable in PATH"
    _mysql=mysql
else
    echo "[ERROR] No MySQL installation found. Please make sure MySQL is installed and set up in PATH."
    exit 0
fi

if [[ "$_mysql" ]]; then
    version=$("$_mysql" --version 2>&1 | awk -F ' ' '{print $5}' | awk -F ',' '{print $1}')
    echo "[INFO] MySQL Version" "$version"
    if [[ "$version" > "5.6" ]]; then
        echo "[INFO] MySQL version is sufficient (>5.6)"
	echo "[WARN] If you experience problems with your database please download the correct JDBC driver for your MySQL version from: http://dev.mysql.com/downloads/connector/j/"
    else         
        echo "[ERROR] MySQL version is below 5.6. Please make sure MySQL 5.6 is installed and set up in PATH."
	exit 0
    fi
fi

# Build binaries
mvn package