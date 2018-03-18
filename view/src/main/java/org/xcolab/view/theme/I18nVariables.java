package org.xcolab.view.theme;

import org.springframework.context.i18n.LocaleContextHolder;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.commons.html.LabelStringValue;
import org.xcolab.util.i18n.I18nUtils;

import java.util.List;
import java.util.Locale;

public class I18nVariables {
    private final String language;
    //private final String locale;
    private final String defaultTimeZone;

    private final boolean isI18NActive;
    //private final String currentLocale;
    private final List<LabelStringValue> languageSelectItems;

    public I18nVariables() {
        final Locale locale = LocaleContextHolder.getLocale();
        this.language = locale.getLanguage();
        //this.locale = locale.toLanguageTag();
        this.defaultTimeZone = ConfigurationAttributeKey.DEFAULT_TIME_ZONE_ID.get();

        this.isI18NActive = ConfigurationAttributeKey.IS_I18N_ACTIVE.get();
        //this.currentLocale = locale.getLanguage();
        this.languageSelectItems = I18nUtils.getSelectList();
    }

    public String getLanguage() {
        return language;
    }

/*    public String getLocale() {
        return locale;
    }*/

    public String getDefaultTimeZone() {
        return defaultTimeZone;
    }

    public boolean getIsI18NActive() {
        return isI18NActive;
    }

/*    public String getCurrentLocale() {
        return currentLocale;
    }*/

    public List<LabelStringValue> getLanguageSelectItems() {
        return languageSelectItems;
    }
}
