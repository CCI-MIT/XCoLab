#!/bin/bash
if [ "$EUID" -ne 0 ]
  then echo "Please run as root"
  exit
fi

# Update config files
# Make sure you configure these lines if your local setup is different
sed -i 's/ProxyPassMatch/#ProxyPassMatch/g' /etc/httpd/conf.d/xcolab.conf
sed -i 's/ProxyPassReverse/#ProxyPassReverse/g' /etc/httpd/conf.d/xcolab.conf

# Restart server
service httpd graceful
