package org.xcolab.client.proposals.exceptions;

public class PlanTemplateNotFoundException extends RuntimeException {

    public PlanTemplateNotFoundException(long id) {
        super("PlanTemplateNotFoundException with id " + id + " does not exist");
    }
}