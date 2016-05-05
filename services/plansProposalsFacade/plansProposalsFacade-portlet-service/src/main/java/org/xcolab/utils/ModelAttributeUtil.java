package org.xcolab.utils;

import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.ui.Model;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;

public final class ModelAttributeUtil {

    private ModelAttributeUtil() { }

    public static void populateModelWithPlatformConstants(Model model) throws SystemException {
        model.addAttribute("colabName", ConfigurationAttributeKey.COLAB_NAME.getStringValue());
        model.addAttribute("colabShortName", ConfigurationAttributeKey.COLAB_SHORT_NAME.getStringValue());
    }
}
