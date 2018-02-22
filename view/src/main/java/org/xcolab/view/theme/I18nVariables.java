package org.xcolab.view.theme;

import org.springframework.context.i18n.LocaleContextHolder;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.util.html.LabelStringValue;
import org.xcolab.util.i18n.I18nUtils;

import java.util.List;
import java.util.Locale;

public class I18nVariables {
    public String language;
    public String locale;
    public String defaultTimeZone;

    public boolean isI18NActive;
    public String currentLocale;
    public List<LabelStringValue> languageSelectItems;

    public I18nVariables() {
        final Locale locale = LocaleContextHolder.getLocale();
        this.language = locale.getLanguage();
        this.locale = locale.toLanguageTag(); // TODO: remove locale and use language instead
        this.defaultTimeZone = ConfigurationAttributeKey.DEFAULT_TIME_ZONE_ID.get();

        this.isI18NActive = ConfigurationAttributeKey.IS_I18N_ACTIVE.get();
        this.currentLocale = locale.getLanguage(); // TODO: remove and use language instead
        this.languageSelectItems = I18nUtils.getSelectList();
    }
}
