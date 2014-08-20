#!/bin/sh
cd services/plansProposalsFacade/plansProposalsFacade-portlet-service
mvn clean install
cd ../plansProposalsFacade-portlet
mvn clean initialize compile package liferay:deploy -DskipTests=true
