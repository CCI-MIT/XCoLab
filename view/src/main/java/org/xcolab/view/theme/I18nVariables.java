package org.xcolab.view.theme;

import org.springframework.context.i18n.LocaleContextHolder;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.util.html.LabelStringValue;
import org.xcolab.util.i18n.I18nUtils;

import java.util.List;
import java.util.Locale;

public class I18nVariables {
    private String language;
    private String locale;
    private String defaultTimeZone;

    private boolean isI18NActive;
    private String currentLocale;
    private List<LabelStringValue> languageSelectItems;

    public I18nVariables() {
        final Locale locale = LocaleContextHolder.getLocale();
        this.language = locale.getLanguage();
        this.locale = locale.toLanguageTag(); // TODO: remove locale and use language instead
        this.defaultTimeZone = ConfigurationAttributeKey.DEFAULT_TIME_ZONE_ID.get();

        this.isI18NActive = ConfigurationAttributeKey.IS_I18N_ACTIVE.get();
        this.currentLocale = locale.getLanguage(); // TODO: remove and use language instead
        this.languageSelectItems = I18nUtils.getSelectList();
    }

    public String getLanguage() {
        return language;
    }

    public String getLocale() {
        return locale;
    }

    public String getDefaultTimeZone() {
        return defaultTimeZone;
    }

    public boolean isI18NActive() {
        return isI18NActive;
    }

    public String getCurrentLocale() {
        return currentLocale;
    }

    public List<LabelStringValue> getLanguageSelectItems() {
        return languageSelectItems;
    }
}
