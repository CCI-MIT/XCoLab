XCOLAB_HOME=$(pwd)"/../"

cd $XCOLAB_HOME/services/plansProposalsFacade/plansProposalsFacade-portlet
mvn liferay:build-service

cd $XCOLAB_HOME/services/plansProposalsFacade
mvn install -N

cd $XCOLAB_HOME/services/plansProposalsFacade/plansProposalsFacade-portlet-service
mvn compile package install

cd $XCOLAB_HOME/services/plansProposalsFacade/plansProposalsFacade-portlet
mvn compile package liferay:deploy -DskipTests=true