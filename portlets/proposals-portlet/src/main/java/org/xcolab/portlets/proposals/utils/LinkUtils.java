package org.xcolab.portlets.proposals.utils;

import com.ext.portlet.service.ProposalLocalServiceUtil;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import java.util.List;

/**
 * @author pdeboer
 *         First created on 28/01/14 at 19:40
 */
public class LinkUtils {
    public static ProposalWrapper getProposalLinks(String href) {
        if (href.contains("/web/guest/plans/-/plans/contestId/")) {
            final String planId = "/planId/";
            int beginIndex = href.indexOf(planId);
            if (beginIndex > -1) {
                String proposalIdSuffix = href.substring(beginIndex+planId.length(), href.length());
                int slashIndex = proposalIdSuffix.indexOf("/");
                String idStr = slashIndex > -1 ? proposalIdSuffix.substring(0, slashIndex) : proposalIdSuffix;

                try {
                    Long id = Long.parseLong(idStr);

                    return new ProposalWrapper(ProposalLocalServiceUtil.getProposal(id));
                } catch (Throwable ex) {

                }
            }
        }
        return null;
    }
}
