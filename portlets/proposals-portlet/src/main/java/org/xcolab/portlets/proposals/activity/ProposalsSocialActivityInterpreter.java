package org.xcolab.portlets.proposals.activity;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.xcolab.portlets.proposals.activity.generators.DefaultFeedEntryGenerator;
import org.xcolab.portlets.proposals.utils.ProposalsURLGenerator;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import com.ext.portlet.Activity.ProposalActivityKeys;
import com.ext.portlet.Activity.ICollabActivityInterpreter;
import com.ext.portlet.model.Proposal;
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
    
    private Map<ProposalActivityKeys, ProposalActivityFeedEntryGenerator> feedGenerators = 
            new HashMap<ProposalActivityKeys, ProposalActivityFeedEntryGenerator>();

    public static final String hyperlink = "<a href=\"%s\">%s</a>";
    
    public ProposalsSocialActivityInterpreter() {
        feedGenerators.put(ProposalActivityKeys.ATTRIBUTE_UPDATE, new DefaultFeedEntryGenerator("Proposal updated", "updated proposal"));
        feedGenerators.put(ProposalActivityKeys.ATTRIBUTE_REMOVE, new DefaultFeedEntryGenerator("Proposal updated", "updated proposal"));
        feedGenerators.put(ProposalActivityKeys.PROPOSAL_CREATE, new DefaultFeedEntryGenerator("Created proposal", "created proposal"));
        feedGenerators.put(ProposalActivityKeys.SUPPORTER_ADD, new DefaultFeedEntryGenerator("New proposal supporter", "is supporting proposal"));
        feedGenerators.put(ProposalActivityKeys.SUPPORTER_REMOVE, new DefaultFeedEntryGenerator("New proposal supporter", "retracted support for proposal"));
        feedGenerators.put(ProposalActivityKeys.USER_ADD, new DefaultFeedEntryGenerator("New proposal member", "became a team member of proposal"));
        feedGenerators.put(ProposalActivityKeys.USER_REMOVE, new DefaultFeedEntryGenerator("Proposal member removed", "is no longer a team member of proposal"));
        feedGenerators.put(ProposalActivityKeys.VOTE, new DefaultFeedEntryGenerator("Proposal vote", "voted for proposal"));
        feedGenerators.put(ProposalActivityKeys.VOTE_RETRACT, new DefaultFeedEntryGenerator("Vote retracted", "retracted vote for proposal"));
        feedGenerators.put(ProposalActivityKeys.VOTE_SWITCH, new DefaultFeedEntryGenerator("Vote switched", "voted for proposal"));
        
    }

    @Override
    protected SocialActivityFeedEntry doInterpret(SocialActivity activity, ThemeDisplay themeDisplay) throws Exception {
        
        if (ProposalActivityKeys.values().length <= activity.getType()) {
            String message = "Unknown proposal activity type " + activity.getType();
            _log.error(message);
            throw new ProposalActivityException(message);
        }
        
        ProposalActivityKeys activityKey = ProposalActivityKeys.values()[activity.getType()];
        
        ProposalActivityFeedEntryGenerator generator = feedGenerators.get(activityKey);
        if (generator == null) {
            String message = "Can't find a feed entry generator for activity " + activityKey.name();
            _log.error(message);
            throw new ProposalActivityException(message);
        }
        
        return generator.generateFeedEntry(activity);
    }

    @Override
    public String getName(Long classNameId, Long classPK, Integer type, String extraData) {
        // name of activity "stream" for given parameters is name of a plan that this activity relates to
        try {
            Proposal rawProposal = ProposalLocalServiceUtil.getProposal(classPK);
            ProposalWrapper proposal = new ProposalWrapper(rawProposal);
            return "Proposal: " + String.format(hyperlink, StringEscapeUtils.escapeHtml(ProposalsURLGenerator.getProposalURL(rawProposal)), proposal.getName());
            
        } catch (SystemException e) {
            _log.error("Can't find plan for id: " + classPK, e);
        } catch (PortalException e) {
            _log.error("Can't find plan for id: " + classPK, e);
        }
        
        return "";
    }
    
    private final static String[] CLASS_NAMES = new String[] { Proposal.class.getName() };

    @Override
    public String[] getClassNames() {
        return CLASS_NAMES;
    }

    
    private final static Log _log = LogFactoryUtil.getLog(ProposalsSocialActivityInterpreter.class);
    

}