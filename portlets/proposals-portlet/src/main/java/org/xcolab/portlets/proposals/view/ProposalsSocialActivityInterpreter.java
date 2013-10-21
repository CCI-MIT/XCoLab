package org.xcolab.portlets.proposals.view;

import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import com.ext.portlet.ProposalActivityKeys;
import com.ext.portlet.Activity.ICollabActivityInterpreter;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

public class ProposalsSocialActivityInterpreter extends BaseSocialActivityInterpreter 
implements ICollabActivityInterpreter {
    private final static String[] CLASS_NAMES = new String[] { Proposal.class.getName() };

    @Override
    public String[] getClassNames() {
        return CLASS_NAMES;
    }

    @Override
    protected SocialActivityFeedEntry doInterpret(SocialActivity activity, ThemeDisplay themeDisplay) throws Exception {
        ProposalActivityKeys activityType = ProposalActivityKeys.values()[activity.getType()];
        String title = activityType.name();
        String body = "";
        /*
        if (msgMap.containsKey(activityType)) {
            body = String.format(msgMap.get(activityType),getUser(activity),getPlan(activity));
        }
        */

        return new SocialActivityFeedEntry("", title, body);
    }

    @Override
    public String getName(Long classNameId, Long classPK, Integer type, String extraData) {
        // name of activity "stream" for given parameters is name of a plan that this activity relates to
        try {
            Proposal rawProposal = ProposalLocalServiceUtil.getProposal(classPK);
            ProposalWrapper proposal = new ProposalWrapper(rawProposal);
            return "Proposal: " + String.format(hyperlink, getProposalURL(proposal), proposal.getName());
        } catch (SystemException e) {
            _log.error("Can't find plan for id: " + classPK, e);
        } catch (PortalException e) {
            _log.error("Can't find plan for id: " + classPK, e);
        }
        
        return "";
    }
    
    private static String getProposalURL(ProposalWrapper p) throws SystemException, PortalException {
        return String.format("/web/guest/plans/-/plans/contestId/" + 
                Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(p.getProposalId()).getContestPK() + "/planId/" + p.getProposalId());
    }

    public static String hyperlink = "<a href=\"%s\">%s</a>";
    
    private final static Log _log = LogFactoryUtil.getLog(ProposalsSocialActivityInterpreter.class);

}
