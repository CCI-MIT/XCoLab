package org.xcolab.view.i18n;

import org.xcolab.util.html.LabelStringValue;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class I18nUtils {
    public static final String MEMBER_LOCALE_SESSION_IDENTIFIER = "memberLocale";
    public static final Locale DEFAULT_LOCALE = Locale.US;
    private static final Map<String, String> codeToLocale = new HashMap<>();
    private static final List<LabelStringValue> selectOptions;

    static {
        codeToLocale.put("en", "colab.languages.en");
        codeToLocale.put("pt", "colab.languages.pt");
        codeToLocale.put("es", "colab.languages.es");

        selectOptions = codeToLocale.entrySet().stream()
                .map(entry -> new LabelStringValue(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        selectOptions.sort(Comparator.comparing(LabelStringValue::getLable));
    }


    public static List<LabelStringValue> getSelectList(){
        return selectOptions;
    }
}
