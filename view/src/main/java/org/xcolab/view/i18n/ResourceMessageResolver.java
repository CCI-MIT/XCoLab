package org.xcolab.view.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ResourceMessageResolver {


    @Autowired
    private MessageSource messageSource;


    public String getLocalizedMessage(String messageId) {

        Locale loc = LocaleContextHolder.getLocale();

        return messageSource.getMessage(messageId,null,loc);

    }
}
