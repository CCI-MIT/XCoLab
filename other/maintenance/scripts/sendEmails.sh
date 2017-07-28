#!/bin/bash
if [ "$EUID" -ne 0 ]
  then echo "Please run as root"
  exit
fi

SAVED_DIR=`pwd`

# Make sure you change the directory if your local setup is different
cd /var/www/html/scripts
php sendEmails.php

cd ${SAVED_DIR}
