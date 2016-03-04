package org.xcolab.utils;

import com.ext.portlet.NoSuchConfigurationAttributeException;
import com.ext.portlet.service.ConfigurationAttributeLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.ui.Model;
import org.xcolab.enums.ConfigurationAttributeKey;

/**
 * Created by johannes on 2/23/16.
 */
public final class ModelAttributeUtil {

    private ModelAttributeUtil() { }

    public static void populateModelWithPlatformConstants(Model model) throws SystemException {
        try {
            model.addAttribute("colabName", ConfigurationAttributeLocalServiceUtil
                    .getAttributeStringValue(ConfigurationAttributeKey.COLAB_NAME.name(), 0L));
            model.addAttribute("colabShortName", ConfigurationAttributeLocalServiceUtil
                    .getAttributeStringValue(ConfigurationAttributeKey.COLAB_SHORT_NAME.name(), 0L));
        } catch (NoSuchConfigurationAttributeException e) {
            throw new SystemException("Required ConfigurationAttributes not set", e);
        }
    }
}
