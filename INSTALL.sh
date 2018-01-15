#!/bin/bash

# stop script if any build fails
set -e

echo "[INFO] Checking your environment..."

# Check Java Version and check if Java is in Path
if type -p java; then
    echo "[INFO] Found java executable in PATH"
    _java=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
    echo "[INFO] Found java executable in JAVA_HOME"     
    _java="$JAVA_HOME/bin/java"
else
    echo "[ERROR] No Java installation found. Please make sure Java 1.8 is installed and set up in PATH."
    exit 0
fi

if [[ "$_java" ]]; then
    version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    echo "[INFO] Java Version" "$version"
    if [[ "$version" > "1.8" ]]; then
        echo "[INFO] Java version is sufficient (>=1.8)"
    else         
        echo "[ERROR] Java version is below 1.8. Please make sure Java 1.8 is installed and set up in PATH."
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
        echo "[INFO] MySQL version is sufficient (>=5.6)"
	    echo "[INFO] If you experience problems with your database please download the correct JDBC driver for your MySQL version from: http://dev.mysql.com/downloads/connector/j/"
    else         
        echo "[ERROR] MySQL version is below 5.6. Please make sure MySQL 5.6 is installed and set up in PATH."
	exit 0
    fi
fi

echo "[INFO] Environment check complete"

echo "[INFO] Installing..."

if [ -f /tmp/foo.txt ]; then
    echo "[INFO] Configuration file found in $HOME/.xcolab.application.properties ..."
else
    echo "[INFO] Copying configuration file to $HOME/.xcolab.application.properties ..."
    cp conf/application.properties ~/.xcolab.application.properties
    echo "[INFO] Make sure you update the configuration with your database credentials"
fi

echo "[INFO] Setup complete"
echo "[INFO] If you haven't, make sure to initialize the database by running the following scripts:"
echo "       sql/starter/xcolab-schema.sql"
echo "       sql/starter/xcolab-data.sql"
