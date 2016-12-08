package org.xcolab.entity.utils;

import org.springframework.ui.Model;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;

public final class ModelAttributeUtil {

    private ModelAttributeUtil() { }

    public static void populateModelWithPlatformConstants(Model model) {
        model.addAttribute("colabName", ConfigurationAttributeKey.COLAB_NAME.get());
        model.addAttribute("colabShortName", ConfigurationAttributeKey.COLAB_SHORT_NAME.get());
    }
}
