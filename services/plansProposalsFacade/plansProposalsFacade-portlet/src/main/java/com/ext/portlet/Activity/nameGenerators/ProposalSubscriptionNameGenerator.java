package com.ext.portlet.Activity.nameGenerators;

import com.ext.portlet.NoSuchProposalException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class ProposalSubscriptionNameGenerator extends BaseSubscriptionNameGenerator {

    public static String hyperlink = "<a href=\"%s\">%s</a>";
    private static String proposalUrl = "/web/guest/-/plans/contestId/%d/planId/%d";
    private final static Log _log = LogFactoryUtil.getLog(ProposalSubscriptionNameGenerator.class);
    
    
    @Override
    public String getName(ActivitySubscription subscription) {
        // name of activity "stream" for given parameters is name of a plan that this activity relates to
        Long classPK = subscription.getClassPK();
        try {
            Proposal proposal = ProposalLocalServiceUtil.getProposal(classPK);
            
            return "Proposal: " + String.format(hyperlink, 
            		String.format(proposalUrl, 
            				Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(classPK).getContestPK(),
            				classPK),
            		ProposalLocalServiceUtil.getAttribute(classPK, ProposalAttributeKeys.NAME.toString(), 0).getStringValue());
        }
        catch (NoSuchProposalException e) {
            _log.error("Can't find proposal for id: " + classPK, e);
        } catch (SystemException e) {
            _log.error("Can't find proposal for id: " + classPK, e);
        } catch (PortalException e) {
            _log.error("Can't find proposal for id: " + classPK, e);
        }
        
        return "";
        
    }


    @Override
    protected Class<?> getSupportedClass() {
        return Proposal.class;
    }

}
