#!/bin/bash
read -p "Enter the host path for the database files (must be empty): " dataPath
read -s -p "Enter root password for MySQL: " mysqlRootPassword
echo
read -s -p "Enter password for database user xcolab: " mysqlPassword
echo

currentPath=$(pwd)

docker run -d -p 3306:3306 --name xcolab_mysql --env-file ${currentPath}/mysql_env.list \
-e MYSQL_PASSWORD=${mysqlPassword} -e MYSQL_ROOT_PASSWORD=${mysqlRootPassword} \
--mount type=bind,src=${currentPath}/mysql_config,dst=/etc/mysql \
--mount type=bind,src=${dataPath},dst=/var/lib/mysql \
--restart=always mysql/mysql-server:5.7
