package org.xcolab.utils;

import com.ext.portlet.model.ContestType;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.admin.util.TemplateReplacementUtilPlaceholder;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.exceptions.InternalException;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.InternetAddress;

public final class TemplateReplacementUtil {

    private final static String PROPOSAL_PLACEHOLDER = "<proposal/>";
    private final static String CONTEST_PLACEHOLDER = "<contest/>";
    private final static String PROPOSALS_PLACEHOLDER = "<proposals/>";
    private final static String CONTESTS_PLACEHOLDER = "<contests/>";

    private TemplateReplacementUtil() {
    }

    public static String replacePlatformConstants(String text) {
        return TemplateReplacementUtilPlaceholder.replacePlatformConstants(text);
    }

    public static String replaceContestTypeStrings(String text, ContestType contestType) {
        try {
            if (contestType == null) {
                contestType = ContestTypeLocalServiceUtil.getContestType(
                        ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.getLongValue());
            }
            return text.replaceAll(PROPOSAL_PLACEHOLDER, contestType.getProposalName())
                    .replaceAll(PROPOSALS_PLACEHOLDER, contestType.getProposalNamePlural())
                    .replaceAll(CONTEST_PLACEHOLDER, contestType.getContestName())
                    .replaceAll(CONTESTS_PLACEHOLDER, contestType.getContestNamePlural());
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw new IllegalStateException("Default contest type does not exist: "
                    + ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.getLongValue());
        }
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
