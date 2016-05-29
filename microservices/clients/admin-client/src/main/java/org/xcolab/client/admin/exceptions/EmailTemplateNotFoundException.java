package org.xcolab.client.admin.exceptions;

public class EmailTemplateNotFoundException extends RuntimeException {
    public EmailTemplateNotFoundException(String templateName) {
        super("EmailTemplate " + templateName + " could not be found");
    }
}
