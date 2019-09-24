package org.xcolab.entity.utils;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.admin.util.TemplateReplacementUtilPlaceholder;
import org.xcolab.commons.exceptions.InternalException;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.InternetAddress;

public final class TemplateReplacementUtil {

    private TemplateReplacementUtil() {
    }

    public static String replacePlatformConstants(String text) {
        return TemplateReplacementUtilPlaceholder.replacePlatformConstants(text);
    }

    public static String replaceContestTypeStrings(String text, ContestType contestType) {
        if (contestType == null) {
            contestType = StaticAdminContext.getContestTypeClient()
                    .getContestType(ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get());
        }
        return contestType.format(text);
    }

    public static InternetAddress getAdminFromEmailAddress() {
        try {
            return new InternetAddress(getAdminFromEmail(),
                    TemplateReplacementUtil.replacePlatformConstants("The <colab-name/> Team"));
        } catch (UnsupportedEncodingException e) {
            throw new InternalException(e);
        }
    }

    public static String getAdminFromEmail() {
        return TemplateReplacementUtilPlaceholder.getAdminFromEmail();
    }
}
