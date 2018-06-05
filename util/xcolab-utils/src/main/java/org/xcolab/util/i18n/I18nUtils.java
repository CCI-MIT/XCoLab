package org.xcolab.util.i18n;

import org.xcolab.commons.html.LabelStringValue;

import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public final class I18nUtils {

    public static final String MEMBER_LOCALE_SESSION_IDENTIFIER = "memberLocale";
    public static final Locale DEFAULT_LOCALE = Locale.US;
    public static final String DEFAULT_LANGUAGE = DEFAULT_LOCALE.getLanguage();

    private static final Map<String, String> codeToLocale = new HashMap<>();

    private static final List<LabelStringValue> selectOptions;

    static {
        codeToLocale.put("en", "colab.languages.en");
        codeToLocale.put("de", "colab.languages.de");
        codeToLocale.put("pt", "colab.languages.pt");
        codeToLocale.put("es", "colab.languages.es");

        selectOptions = codeToLocale.entrySet().stream()
                .map(entry -> new LabelStringValue(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        selectOptions.sort(Comparator.comparing(LabelStringValue::getLable));
    }

    private I18nUtils() {
    }

    public static List<LabelStringValue> getSelectList() {
        return selectOptions;
    }

    public static List<String> getAllLanguages() {
        return new ArrayList<>(codeToLocale.keySet());
    }

    public static boolean hasCapitalNouns(Locale locale) {
        return hasCapitalNouns(locale.getLanguage());
    }

    public static boolean hasCapitalNouns(String language) {
        if (language != null) {
            return "de".equalsIgnoreCase(language);
        }
        return false;
    }

    public static String formatNumberDefaultLocale(Locale locale, Integer values){
        NumberFormat formatter = NumberFormat.getInstance(locale);
        return formatter.format(values);

    }

    public static String getSupportedLanguage(Locale locale) {
        final String language = locale.getLanguage();
        if (getAllLanguages().contains(language)) {
            return language;
        }
        return DEFAULT_LANGUAGE;
    }
}
