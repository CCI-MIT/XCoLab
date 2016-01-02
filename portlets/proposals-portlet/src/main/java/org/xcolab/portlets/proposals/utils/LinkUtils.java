package org.xcolab.portlets.proposals.utils;

import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

/**
 * @author pdeboer
 *         First created on 28/01/14 at 19:40
 */
public final class LinkUtils {

    private LinkUtils() { }

    public static ProposalWrapper getProposalFromLinkUrl(String linkUrl) {
        if (linkUrl.contains("/-/plans/contestId/")) {
            final String planId = "/planId/";
            int beginIndex = linkUrl.indexOf(planId);
            if (beginIndex > -1) {
                String proposalIdSuffix = linkUrl.substring(beginIndex + planId.length(), linkUrl.length());
                int slashIndex = proposalIdSuffix.indexOf("/");
                String idStr = slashIndex > -1 ? proposalIdSuffix.substring(0, slashIndex) : proposalIdSuffix;

                try {
                    Long id = Long.parseLong(idStr);

                    return new ProposalWrapper(ProposalLocalServiceUtil.getProposal(id));
                } catch (SystemException | PortalException | NumberFormatException ignored) { }
            }
        }
        return null;
    }
}
