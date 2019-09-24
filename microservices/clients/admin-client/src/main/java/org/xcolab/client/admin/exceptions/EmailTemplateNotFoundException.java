package org.xcolab.client.admin.exceptions;

import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

public class EmailTemplateNotFoundException extends RuntimeEntityNotFoundException {

    public EmailTemplateNotFoundException(String templateName) {
        super("EmailTemplate " + templateName + " could not be found",
                EmailTemplateNotFoundException.class);
    }
}
