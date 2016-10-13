package org.xcolab.client.proposals.exceptions;

public class PlanTemplateNotFoundException extends Exception {
    public PlanTemplateNotFoundException(long proposalAttributeId) {
        this("PlanTemplateNotFoundException with id " + proposalAttributeId + " does not exist");
    }

    public PlanTemplateNotFoundException(String msg) {
        super(msg);
    }
}