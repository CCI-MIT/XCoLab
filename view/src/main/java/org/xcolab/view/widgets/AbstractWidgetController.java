package org.xcolab.view.widgets;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;

import org.xcolab.client.user.PermissionsClient;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.view.widgets.WidgetPreference.Supplier;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractWidgetController<WidgetPreferenceT extends WidgetPreference> {

    protected static final String PREFERENCES_URL_PATH = "/preferences";

    private final String baseUrl;
    private final Supplier<WidgetPreferenceT> preferenceSupplier;

    protected AbstractWidgetController(String baseUrl,
            WidgetPreference.Supplier<WidgetPreferenceT> preferenceSupplier) {
        this.baseUrl = baseUrl;
        this.preferenceSupplier = preferenceSupplier;
    }

    protected WidgetPreferenceT getPreferences(String id) {
        Locale locale = LocaleContextHolder.getLocale();
        return getPreferences(id, locale.getLanguage());
    }

    private WidgetPreferenceT getPreferences(String id, String language) {
        return preferenceSupplier.get(id, language);
    }

    protected String showPreferencesInternal(HttpServletResponse response, Model model,
            Member member, String preferenceId, String language, String viewName) {

        if (!PermissionsClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        model.addAttribute("preferences", getPreferences(preferenceId, language));
        return viewName;
    }


    protected String savePreferencesInternal(HttpServletRequest request,
            HttpServletResponse response, Member member, WidgetPreferenceT preferences) {

        if (!PermissionsClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        preferences.savePreferences();

        AlertMessage.success("Preferences have been saved.").flash(request);
        return "redirect:" + baseUrl + PREFERENCES_URL_PATH + "?preferenceId=" + preferences
                .getPreferenceId();
    }

}
