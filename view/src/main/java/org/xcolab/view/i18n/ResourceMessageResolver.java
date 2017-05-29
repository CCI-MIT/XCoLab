package org.xcolab.view.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ResourceMessageResolver {

    private static final Logger _log = LoggerFactory.getLogger(ResourceMessageResolver.class);

    @Autowired
    private MessageSource messageSource;


    public String getLocalizedMessage(String messageId) {
        try {
            return messageSource.getMessage(messageId, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            _log.error("Resource message not found: " + messageId + " for locale "
                    + LocaleContextHolder.getLocale());
            return "";
        }
    }

    public String getLocalizedMessage(String messageId, String[] options) {
        try {
            return messageSource.getMessage(messageId, options, LocaleContextHolder.getLocale());

        } catch (NoSuchMessageException e) {
            _log.error("Resource message not found: " + messageId + " for locale "
                    + LocaleContextHolder.getLocale());
            return "";
        }

    }
}
