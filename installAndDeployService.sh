#!/bin/sh
cd services/plansProposalsFacade/plansProposalsFacade-portlet-service
mvn install
cd ../plansProposalsFacade-portlet
mvn clean initialize compile package liferay:deploy -DskipTests=true
