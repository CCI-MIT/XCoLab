package org.xcolab.view.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ResourceMessageResolver {

    private static final Logger _log = LoggerFactory.getLogger(ResourceMessageResolver.class);

    private final MessageSource messageSource;

    @Autowired
    public ResourceMessageResolver(MessageSource messageSource) {
        Assert.notNull(messageSource, "MessageSource is required");
        this.messageSource = messageSource;
    }

    public String getLocalizedMessage(String messageId, Object... args) {
        try {
            return messageSource.getMessage(messageId, args, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            _log.error("Resource message not found: {} for locale {}", messageId,
                    LocaleContextHolder.getLocale());
            return "";
        }
    }
}
