package com.ext.portlet.Activity.nameGenerators;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class ProposalSubscriptionNameGenerator extends BaseSubscriptionNameGenerator {

    private final static String HYPERLINK = "<a href=\"%s\">%s</a>";
    private final static String PROPOSAL_URL = "/web/guest/-/plans/contestId/%d/planId/%d";
    private final static Log _log = LogFactoryUtil.getLog(ProposalSubscriptionNameGenerator.class);
    
    
    @Override
    public String getName(ActivitySubscription subscription) {
        // name of activity "stream" for given parameters is name of a plan that this activity relates to
        Long classPK = subscription.getClassPK();
        try {
            Proposal proposal = ProposalLocalServiceUtil.getProposal(classPK);
            
            return "Proposal: " + String.format(HYPERLINK,
            		String.format(PROPOSAL_URL,
            				Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(classPK).getContestPK(),
            				classPK),
            		ProposalAttributeLocalServiceUtil.getAttribute(classPK, ProposalAttributeKeys.NAME, 0).getStringValue());
        }
        catch (SystemException | PortalException e) {
            _log.error("Can't find proposal for id: " + classPK, e);
        }

        return "";
        
    }


    @Override
    protected Class<?> getSupportedClass() {
        return Proposal.class;
    }

}
