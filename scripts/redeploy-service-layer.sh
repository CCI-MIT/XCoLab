XCOLAB_HOME=$(pwd)"/../"

cd $XCOLAB_HOME/services/plansProposalsFacade/plansProposalsFacade-portlet-service
mvn clean compile package install

cd $XCOLAB_HOME/services/plansProposalsFacade/plansProposalsFacade-portlet
mvn clean compile package liferay:deploy -DskipTests=true
