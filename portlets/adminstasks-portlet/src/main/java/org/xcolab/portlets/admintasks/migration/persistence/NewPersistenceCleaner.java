package org.xcolab.portlets.admintasks.migration.persistence;

import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import com.icesoft.faces.async.render.SessionRenderer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 10/1/13
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class NewPersistenceCleaner {

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
                deleteAllProposalContestPhaseAttributes() &
                deleteAllProposalContestPhaseAttributeTypes();
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
                ProposalLocalServiceUtil.deleteProposal(p);
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

    private boolean deleteAllProposalContestPhaseAttributeTypes(){
        try {
            pushAjaxUpdate("Deleting " + ProposalContestPhaseAttributeTypeLocalServiceUtil.getProposalContestPhaseAttributeTypesCount() + " ProposalAttributeTypes");
            for (ProposalContestPhaseAttributeType p : ProposalContestPhaseAttributeTypeLocalServiceUtil.getProposalContestPhaseAttributeTypes(0, Integer.MAX_VALUE)) {
                ProposalContestPhaseAttributeTypeLocalServiceUtil.deleteProposalContestPhaseAttributeType(p);
            }
        }
        catch (Exception e) {
            pushAjaxUpdate("Error while cleaning DB: " + e);
            return false;
        }
        return true;
    }

    private boolean deleteAllProposalContestPhaseAttributes(){
        try {
            pushAjaxUpdate("Deleting " + ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttributesCount() + " ProposalAttributes");
            for (ProposalContestPhaseAttribute p : ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttributes(0, Integer.MAX_VALUE)) {
                ProposalContestPhaseAttributeLocalServiceUtil.deleteProposalContestPhaseAttribute(p);
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
