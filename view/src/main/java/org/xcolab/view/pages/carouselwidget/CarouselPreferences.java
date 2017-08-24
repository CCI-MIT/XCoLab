package org.xcolab.view.pages.carouselwidget;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.entity.utils.WidgetPreference;
import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.i18n.I18nUtils;

public class CarouselPreferences extends WidgetPreference {
    private String title;

    public String getTitle() {
        return title;
    }

    @Override
    public AttributeGetter<String> getConfigurationAttribute() {
        return ConfigurationAttributeKey.PORTLET_CONTESTS_PREFERENCES;
    }

    public CarouselPreferences() {
        this(null, I18nUtils.DEFAULT_LANGUAGE);
    }
    public CarouselPreferences(String preferenceId, String locale) {
        this.title = "LOLOLOL";
    }
}
