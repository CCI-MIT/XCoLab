#!/bin/bash

XCOLAB_HOME=$(pwd)"/../"

cd $XCOLAB_HOME
git pull origin master


#deploy service layer
cd $XCOLAB_HOME/services/plansProposalsFacade/plansProposalsFacade-portlet-service
mvn clean compile package install

cd $XCOLAB_HOME/services/plansProposalsFacade/plansProposalsFacade-portlet
mvn clean compile package -DskipTests=true

#deploy all other portlets
cd $XCOLAB_HOME/portlets
for D in *; do
	if [ -d "${D}" ]; then
		cd $D
        mvn clean compile package
        cd ..
	fi;
done


#run tests
cd $XCOLAB_HOME/scripts
./jenkins-run-junit-tests.sh
