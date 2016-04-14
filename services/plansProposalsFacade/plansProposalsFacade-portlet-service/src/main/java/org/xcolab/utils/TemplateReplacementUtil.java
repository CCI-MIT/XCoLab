package org.xcolab.utils;

import com.ext.portlet.model.ContestType;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.enums.ConfigurationAttributeKey;

import java.io.UnsupportedEncodingException;

public final class TemplateReplacementUtil {

    public final static String COLAB_NAME_PLACEHOLDER = "<colab-name/>";
    public final static String COLAB_SHORT_NAME_PLACEHOLDER = "<colab-short-name/>";
    public final static String COLAB_URL = "<colab-url/>";

    public final static String ADMIN_EMAIL_PLACEHOLDER = "<admin-email/>";
    public final static String ADMIN_FROM_EMAIL_PLACEHOLDER = "<admin-from-email/>";

    public final static String PROPOSAL_PLACEHOLDER = "<proposal/>";
    public final static String CONTEST_PLACEHOLDER = "<contest/>";
    public final static String PROPOSALS_PLACEHOLDER = "<proposals/>";
    public final static String CONTESTS_PLACEHOLDER = "<contests/>";

    private TemplateReplacementUtil() {
    }

    public static String replacePlatformConstants(String text) throws SystemException {
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

    public static String replaceContestTypeStrings(String text, ContestType contestType) throws SystemException {
        try {
            if (contestType == null) {
                contestType = ContestTypeLocalServiceUtil.getContestType(
                        ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.getLongValue());

            }
            return text.replaceAll(PROPOSAL_PLACEHOLDER, contestType.getProposalName())
                    .replaceAll(PROPOSALS_PLACEHOLDER, contestType.getProposalNamePlural())
                    .replaceAll(CONTEST_PLACEHOLDER, contestType.getContestName())
                    .replaceAll(CONTESTS_PLACEHOLDER, contestType.getContestNamePlural());
        } catch (PortalException e) {
            throw new SystemException("ConfigurationAttributeKey(s) missing", e);
        }
    }

    public static InternetAddress getAdminFromEmailAddress() throws SystemException, UnsupportedEncodingException {
        final String adminFromEmail = ConfigurationAttributeKey.ADMIN_FROM_EMAIL.getStringValue();
        return new InternetAddress(adminFromEmail,
                TemplateReplacementUtil.replacePlatformConstants("The <colab-name/> Team"));
    }
}
