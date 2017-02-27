#!/usr/bin/env bash

# stop script if any build fails
set -e
echo "#####################################################################################"
echo "Deploying parent dependencies"
echo "#####################################################################################"
LOG="-q -B -Dsurefire.printSummary=false"
#LOG="-q -B -Dsurefire.printSummary=false"
# USE LOG = "" if you want verbose mode
mvn install -N $LOG
cd microservices/
mvn install -N $LOG
cd util/
mvn install -N $LOG
cd ../clients/
mvn install -N $LOG
echo "#####################################################################################"
echo "Deploying xcolab-utils"
echo "#####################################################################################"
cd ../util/xcolab-utils/
mvn clean compile package install clean $LOG
echo "#####################################################################################"
echo "Deploying entity-utils"
echo "#####################################################################################"
cd ../../../
echo "Deploying emails-client"
cd microservices/clients/emails-client
mvn clean compile package install clean $LOG
echo "Deploying members-client"
cd ../members-client
mvn clean compile package install clean $LOG
echo "Deploying admin-client"
cd ../admin-client/
mvn clean compile package install clean $LOG
echo "Deploying comment-client"
cd ../comment-client/
mvn clean compile package install clean $LOG
echo "Deploying activities-client"
cd ../activities-client/
mvn clean compile package install clean $LOG
echo "Deploying modeling-client"
cd ../modeling-client/
mvn clean compile package install clean $LOG
echo "Deploying contestproposal-client"
cd ../contestproposal-client/
mvn clean compile package install clean $LOG
echo "Deploying entity-utils"
cd ../../util/entity-utils/
mvn clean compile package install clean $LOG
cd ../../..
echo "#####################################################################################"
echo "Deploying clients"
cd microservices/clients
for D in *-client; do
    if [ -d "${D}" ]; then
        cd $D
        echo " - $D"
		pwd
		mvn clean compile package install clean $LOG
		cd ..
    fi
done
echo "#####################################################################################"
cd ../..
