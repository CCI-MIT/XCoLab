package org.xcolab.utils;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import javax.portlet.PortletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pdeboer
 *         First created on 28/01/14 at 19:40
 */
public final class LinkUtils {

    private LinkUtils() { }

    public static Proposal getProposalFromLinkUrl(String linkUrl) {
        List<Long> proposalIds = getProposalIdsFromLinksInText(linkUrl);
        if (!proposalIds.isEmpty()) {
            try {
                return ProposalLocalServiceUtil.getProposal(proposalIds.get(0));
            } catch (SystemException | PortalException ignored) { }
        }

        return null;
    }

    public static List<Long> getProposalIdsFromLinksInText(String text) {
        List<Long> proposalIds = new ArrayList<>();
        Pattern proposalLinkPattern = Pattern.compile(
                "(href=|https?://).*?/.+?/\\d{4}/[a-z0-9-]+?/(?:c|phase/\\d*)/.+?/(\\d*)");
        Matcher m = proposalLinkPattern.matcher(text);
        while (m.find()) {
            try {
                final long proposalId = Long.parseLong(m.group(2));
                proposalIds.add(proposalId);
            } catch (NumberFormatException ignored) { }
        }

        proposalIds.addAll(getProposalIdsFromLegacyLinksInText(text));
        return proposalIds;
    }

    public static String getBaseUri(PortletRequest request) {
        String baseUri = String.format("%s://%s", request.getScheme(), request.getServerName());
        int port = request.getServerPort();
        if (port != 80 && port != 443) {
            baseUri += ":" + port;
        }
        return baseUri;
    }

    private static List<Long> getProposalIdsFromLegacyLinksInText(String text) {
        List<Long> proposalIds = new ArrayList<>();
        Pattern proposalLinkPattern = Pattern.compile(
                "(href=|https?://).*?/-/plans/contestId/(\\d*)/(?:phaseId/\\d*/)?planId/(\\d*)");
        Matcher m = proposalLinkPattern.matcher(text);
        while (m.find()) {
            try {
                final long proposalId = Long.parseLong(m.group(3));
                proposalIds.add(proposalId);
            } catch (NumberFormatException ignored) { }
        }
        return proposalIds;
    }
}
