#!/bin/bash         
 
#cronjob:
# 0 2 1 * * /path/to/export-tracked-visits.sh

HOST=127.0.0.1
USER=root
PASSWORD=
DATABASE=xcolab_prod2

#make sure that the user executing the cronjob has access to this file
PRIVATE_KEY_FILE=/Users/manu/.ssh/id_rsa
SSH_SERVER_AND_USER="manuel@cognosis.mit.edu:~/visits-backup/"

VISITFILE=/Users/manu/tracked-visit-$(date +%Y%m%d).csv 
VISITORFILE=/Users/manu/tracked-visitor-to-user-$(date +%Y%m%d).csv 

#export and if successful, empty table
if mysql -h $HOST -u $USER --password=$PASSWORD $DATABASE -e "SELECT * FROM xcolab_TrackedVisit INTO OUTFILE '$VISITFILE' FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n'"; then
	mysql -h $HOST -u $USER --password=$PASSWORD $DATABASE -e "TRUNCATE xcolab_TrackedVisit"
	gzip $VISITFILE
	scp -i $PRIVATE_KEY_FILE $VISITFILE.gz $SSH_SERVER_AND_USER
	rm $VISITFILE.gz
else
	echo "TrackedVisit export error"
fi

if mysql -h $HOST -u $USER --password=$PASSWORD $DATABASE -e "SELECT * FROM xcolab_TrackedVisitor2User INTO OUTFILE '$VISITORFILE' FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n'"; then
	mysql -h $HOST -u $USER --password=$PASSWORD $DATABASE -e "TRUNCATE xcolab_TrackedVisitor2User"
	gzip $VISITORFILE
	scp -i $PRIVATE_KEY_FILE $VISITORFILE.gz $SSH_SERVER_AND_USER
	rm $VISITORFILE.gz
else
	echo "TrackedVisitor2User export error"
fi

 
