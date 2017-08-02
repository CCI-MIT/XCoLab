# Maintenance dialogue

This readme document describes the necessary steps to install and use the XCoLab's Maintenance dialog web page.

The maintenance dialogue is meant to be used with a httpd instance that is used as a reverse proxy (e.g. for a tomcat server).
It works by sitting in the standard web root and by disabling the reverse proxy during maintenance. 

## Requirements
1. httpd2 (for another web server instructions may vary)
2. php >= 5.4

## Installation:
1. Make the contents of the src folder accessible at the root of your web server.
    `index.php` should be accessible via `http(s)://example.com/index.php`.

## Usage:

1. Enable and disable the maintenance dialogue by using the `enableMaintenance.sh` and `disableMaintenance.sh` script in the `scrips` folder.
2. If applicable, send maintenance emails using the `sendEmail.sh` script in the `scripts` folder.
