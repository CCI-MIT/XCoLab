package org.xcolab.entity.utils;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.util.TemplateReplacementUtilPlaceholder;
import org.xcolab.client.admin.pojo.ContestType;
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
            if (contestType == null) {
                contestType = ContestTypeClient.getContestType(ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get());
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
        return TemplateReplacementUtilPlaceholder.getAdminFromEmail();
    }
}
