package org.xcolab.utils;


import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.exceptions.InternalException;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.InternetAddress;

public final class TemplateReplacementUtil {

    private final static String COLAB_NAME_PLACEHOLDER = "<colab-name/>";
    private final static String COLAB_SHORT_NAME_PLACEHOLDER = "<colab-short-name/>";
    private final static String COLAB_URL = "<colab-url/>";

    private final static String ADMIN_EMAIL_PLACEHOLDER = "<admin-email/>";
    private final static String ADMIN_FROM_EMAIL_PLACEHOLDER = "<admin-from-email/>";

    private final static String PROPOSAL_PLACEHOLDER = "<proposal/>";
    private final static String CONTEST_PLACEHOLDER = "<contest/>";
    private final static String PROPOSALS_PLACEHOLDER = "<proposals/>";
    private final static String CONTESTS_PLACEHOLDER = "<contests/>";

    private TemplateReplacementUtil() {
    }

    public static String replacePlatformConstants(String text) {
        final String colabName = ConfigurationAttributeKey.COLAB_NAME.getStringValue();
        final String colabShortName = ConfigurationAttributeKey.COLAB_SHORT_NAME.getStringValue();
        final String colabUrl = ConfigurationAttributeKey.COLAB_URL.getStringValue();
        final String adminEmail = ConfigurationAttributeKey.ADMIN_EMAIL.getStringValue();
        final String adminFromEmail = ConfigurationAttributeKey.ADMIN_FROM_EMAIL.getStringValue();

        return text.replaceAll(COLAB_NAME_PLACEHOLDER, colabName)
                .replaceAll(COLAB_SHORT_NAME_PLACEHOLDER, colabShortName)
                .replaceAll(COLAB_URL, colabUrl)
                .replaceAll(ADMIN_EMAIL_PLACEHOLDER, adminEmail)
                .replaceAll(ADMIN_FROM_EMAIL_PLACEHOLDER, adminFromEmail);
    }

    public static String replaceContestTypeStrings(String text, ContestType contestType) {
            if (contestType == null) {
                contestType = ContestClient.getContestType(ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.getLongValue());
            }
            return text.replaceAll(PROPOSAL_PLACEHOLDER, contestType.getProposalName())
                    .replaceAll(PROPOSALS_PLACEHOLDER, contestType.getProposalNamePlural())
                    .replaceAll(CONTEST_PLACEHOLDER, contestType.getContestName())
                    .replaceAll(CONTESTS_PLACEHOLDER, contestType.getContestNamePlural());
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
        return ConfigurationAttributeKey.ADMIN_FROM_EMAIL.getStringValue();
    }
}
