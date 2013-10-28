package org.xcolab.portlets.admintasks.migration.persistence;

import java.util.List;

import com.liferay.portal.model.Group;
import com.ext.portlet.model.ContestPhaseRibbonType;
import com.ext.portlet.model.Plan2Proposal;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalSupporter;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.model.ProposalVote;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.ext.portlet.service.Plan2ProposalLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalSupporterLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.ext.portlet.service.ProposalVoteLocalServiceUtil;
import com.icesoft.faces.async.render.SessionRenderer;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 10/1/13
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class NewPersistenceCleaner {


    private static final String ENTITY_CLASS_LOADER_CONTEXT = "plansProposalsFacade-portlet";
    private static ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(
            ENTITY_CLASS_LOADER_CONTEXT, "portletClassLoader");
    
    List<String> reference;

    public NewPersistenceCleaner(List<String> reference){
        this.reference = reference;
    }

    public boolean deleteAllRecordsForNewEntities(){
        pushAjaxUpdate("Cleaning DB");
        boolean dbSuccess = true;
        dbSuccess &= deleteAllProposals() &
                deleteAllProposalVersions() &
                deleteAllProposalAttributes() &
                deleteAllPlan2Proposal() &
                deleteAllProposal2Phase() &
                deleteAllProposalVotes() &
                deleteAllProposalSupporters() &
                deleteAllContestPhaseRibbonTypes() &
                deleteAllSocialActivitiesRelatedToNewEntities() &
                deleteNewGroups();
        if (dbSuccess) pushAjaxUpdate("Successfully cleaned DB");
        else pushAjaxUpdate("Error while cleaning DB");

        return dbSuccess;
    }

    private boolean deleteAllProposals(){
        int numberOfProposals = 0;
        try{
            numberOfProposals = ProposalLocalServiceUtil.getProposalsCount();
            pushAjaxUpdate("Deleting " + numberOfProposals + " Proposal(s)");
            List<Proposal> proposals = ProposalLocalServiceUtil.getProposals(0, Integer.MAX_VALUE);
            for(Proposal p : proposals) {
                ProposalLocalServiceUtil.deleteProposal(p.getProposalId());
            }
            numberOfProposals = ProposalLocalServiceUtil.getProposalsCount();

            if (numberOfProposals != 0) {
                pushAjaxUpdate("Error while cleaning DB - Could not clean Proposals");
                return false;
            }
        } catch (Exception e){
            pushAjaxUpdate("Error while cleaning DB: " + e);
            return false;
        }
        return true;
    }

    private boolean deleteNewGroups(){
        pushAjaxUpdate("Deleting groups");
        try{
            for (Group g : GroupLocalServiceUtil.getGroups(0,Integer.MAX_VALUE)){
                if (g.getName().indexOf("Proposal_") == 0) {
                    GroupLocalServiceUtil.class.getMethod("deleteGroup",Group.class).invoke(null,g);
                    //GroupLocalServiceUtil.deleteGroup(g);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            pushAjaxUpdate("Error: " + e.toString());
            return false;
        }
        pushAjaxUpdate("Done deleting groups");
        return true;
    }


    private boolean deleteAllProposalVersions(){
        int numberOfProposalVersions = 0;
        try{
            numberOfProposalVersions = ProposalVersionLocalServiceUtil.getProposalVersionsCount();
            pushAjaxUpdate("Deleting " + numberOfProposalVersions + " Versions");
            List<ProposalVersion> versions = ProposalVersionLocalServiceUtil.getProposalVersions(0, numberOfProposalVersions);
            for(ProposalVersion p : versions) {
                ProposalVersionLocalServiceUtil.deleteProposalVersion(p);
            }
            numberOfProposalVersions = ProposalVersionLocalServiceUtil.getProposalVersionsCount();
            if ((numberOfProposalVersions) != 0) {
                pushAjaxUpdate("Error while cleaning DB - Could not clean Versions");
                return false;
            }
        } catch (Exception e){
            pushAjaxUpdate("Error while cleaning DB: " + e);
            return false;
        }
        return true;
    }

    private boolean deleteAllProposalAttributes(){
        int numberOfProposalAttributes = 0;
        try{
            numberOfProposalAttributes = ProposalAttributeLocalServiceUtil.getProposalAttributesCount();
            pushAjaxUpdate("Deleting " + numberOfProposalAttributes + " Attributes");
            List<ProposalAttribute> attributes = ProposalAttributeLocalServiceUtil.getProposalAttributes(0,numberOfProposalAttributes);
            for(ProposalAttribute p : attributes) {
                ProposalAttributeLocalServiceUtil.deleteProposalAttribute(p);
            }
            numberOfProposalAttributes = ProposalAttributeLocalServiceUtil.getProposalAttributesCount();

            if (numberOfProposalAttributes != 0) {
                pushAjaxUpdate("Error while cleaning DB - Could not clean Attributes");
                return false;
            }
        } catch (Exception e){
            pushAjaxUpdate("Error while cleaning DB: " + e);
            return false;
        }
        return true;
    }

    private boolean deleteAllPlan2Proposal() {
        try {
            pushAjaxUpdate("Deleting " + Plan2ProposalLocalServiceUtil.getPlan2ProposalsCount() + " plans2proposal mappings");
            for (Plan2Proposal p2p: Plan2ProposalLocalServiceUtil.getPlan2Proposals(0, Integer.MAX_VALUE)) {
                Plan2ProposalLocalServiceUtil.deletePlan2Proposal(p2p.getPlanId());
            }
        }
        catch (Exception e) {
            pushAjaxUpdate("Error while cleaning DB: " + e);
            return false;
        }
        return true;
    }

    private boolean deleteAllProposal2Phase(){
        try {
            pushAjaxUpdate("Deleting " + Proposal2PhaseLocalServiceUtil.getProposal2PhasesCount() + " Proposal2Phase mappings");
            for (Proposal2Phase p2p : Proposal2PhaseLocalServiceUtil.getProposal2Phases(0, Integer.MAX_VALUE)) {
                Proposal2PhaseLocalServiceUtil.deleteProposal2Phase(p2p);
            }
        }
        catch (Exception e) {
            pushAjaxUpdate("Error while cleaning DB: " + e);
            return false;
        }
        return true;
    }

    private boolean deleteAllProposalVotes(){
        try {
            pushAjaxUpdate("Deleting " + ProposalVoteLocalServiceUtil.getProposalVotesCount() + " ProposalVotes");
            for (ProposalVote p : ProposalVoteLocalServiceUtil.getProposalVotes(0, Integer.MAX_VALUE)) {
                ProposalVoteLocalServiceUtil.deleteProposalVote(p);
            }
        }
        catch (Exception e) {
            pushAjaxUpdate("Error while cleaning DB: " + e);
            return false;
        }
        return true;
    }

    private boolean deleteAllProposalSupporters(){
        try {
            pushAjaxUpdate("Deleting " + ProposalSupporterLocalServiceUtil.getProposalSupportersCount() + " ProposalSupporters");
            for (ProposalSupporter p : ProposalSupporterLocalServiceUtil.getProposalSupporters(0, Integer.MAX_VALUE)) {
                ProposalSupporterLocalServiceUtil.deleteProposalSupporter(p);
            }
        }
        catch (Exception e) {
            pushAjaxUpdate("Error while cleaning DB: " + e);
            return false;
        }
        return true;
    }

    private boolean deleteAllSocialActivitiesRelatedToNewEntities(){
        try {
            pushAjaxUpdate("Deleting " + SocialActivityLocalServiceUtil.getActivitiesCount(ClassNameLocalServiceUtil.getClassNameId(Proposal.class)) + " Social Activities");
            for (SocialActivity a : SocialActivityLocalServiceUtil.getActivities(ClassNameLocalServiceUtil.getClassNameId(Proposal.class),0,Integer.MAX_VALUE)) {
                SocialActivityLocalServiceUtil.deleteActivity(a);
            }
        }
        catch (Exception e) {
            pushAjaxUpdate("Error while cleaning DB: " + e);
            return false;
        }
        return true;
    }

    private boolean deleteAllContestPhaseRibbonTypes(){
        try {
            pushAjaxUpdate("Deleting " + ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonTypesCount() + " ContestPhaseRibbonTypes");
            
            for (ContestPhaseRibbonType p : ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonTypes(0, Integer.MAX_VALUE)) {
                ContestPhaseRibbonTypeLocalServiceUtil.deleteContestPhaseRibbonType(p);
            }
        }
        catch (Exception e) {
            pushAjaxUpdate("Error while cleaning DB: " + e);
            return false;
        }
        return true;
    }

    private void pushAjaxUpdate(String message){
        reference.add(message);
        SessionRenderer.render("migration");
    }


}
