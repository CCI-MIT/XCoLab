package org.xcolab.client.admin.exceptions;

//TODO: change this to RuntimeEntityNotFoundException
public class EmailTemplateNotFoundException extends RuntimeException {
    public EmailTemplateNotFoundException(String templateName) {
        super("EmailTemplate " + templateName + " could not be found");
    }
}
