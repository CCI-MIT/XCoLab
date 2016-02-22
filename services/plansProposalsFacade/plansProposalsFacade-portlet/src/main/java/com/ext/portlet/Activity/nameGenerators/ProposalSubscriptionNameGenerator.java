package com.ext.portlet.Activity.nameGenerators;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class ProposalSubscriptionNameGenerator extends BaseSubscriptionNameGenerator {

    private final static String HYPERLINK = "<a href=\"%s\">%s</a>";
    private final static Log _log = LogFactoryUtil.getLog(ProposalSubscriptionNameGenerator.class);
    
    @Override
    public String getName(ActivitySubscription subscription) {
        // name of activity "stream" for given parameters is name of a plan that this activity relates to
        Long proposalId = subscription.getClassPK();
        try {
            return "Proposal: " + String.format(HYPERLINK,
            		ProposalLocalServiceUtil.getProposalLinkUrl(proposalId),
            		ProposalAttributeLocalServiceUtil.getAttribute(proposalId, ProposalAttributeKeys.NAME, 0).getStringValue());
        }
        catch (SystemException | PortalException e) {
            _log.error("Can't find proposal for id: " + proposalId, e);
        }

        return "";
    }

    @Override
    protected Class<?> getSupportedClass() {
        return Proposal.class;
    }

}
