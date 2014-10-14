#!/bin/bash         
 
#cronjob:
# 0 2 1 * * /path/to/export-tracked-visits.sh

HOST=127.0.0.1
USER=root
PASSWORD=
DATABASE=xcolab_prod

VISITFILE=/Users/manu/tracked-visit-$(date +%Y%m%d).csv 
VISITORFILE=/Users/manu/tracked-visitor-to-user-$(date +%Y%m%d).csv 

#export and if successful, empty table
if mysql -h $HOST -u $USER --password=$PASSWORD $DATABASE -e "SELECT * FROM xcolab_TrackedVisit INTO OUTFILE '$VISITFILE' FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n'"; then
	mysql -h $HOST -u $USER --password=$PASSWORD $DATABASE -e "TRUNCATE xcolab_TrackedVisit"
else
	echo "TrackedVisit export error"
fi

if mysql -h $HOST -u $USER --password=$PASSWORD $DATABASE -e "SELECT * FROM xcolab_TrackedVisitor2User INTO OUTFILE '$VISITORFILE' FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n'"; then
	mysql -h $HOST -u $USER --password=$PASSWORD $DATABASE -e "TRUNCATE xcolab_TrackedVisitor2User"
else
	echo "TrackedVisitor2User export error"
fi

 
