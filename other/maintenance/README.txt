This readme document will describe the necessary steps to install and use the Climate CoLab's Maintenance dialog web page.

Installation:
1.) Install a recent version of PHP on the Apache web server of the CoLab instance.

2.) Copy all the files in this folder except the folder "scripts" (and this readme file) to the document root folder of the CoLab's apache web server. The files should be copied so that the index file (index.php) should be accessible via http://<ccolab_url/>/index.php.

Usage:

1.) Enable the maintenance dialog as described in the section "Activate maintenance mode" in the wiki article hosted at https://github.com/CCI-MIT/XCoLab/wiki/Deployments-on-Production-Server.

2.) After you committed all changes at the CoLab don't forget to trigger the delivery of email notifications to users that subscribed to the status of the CoLab maintenance, as described in section "Send emails to people who have subscribed for updates" in the wiki article.