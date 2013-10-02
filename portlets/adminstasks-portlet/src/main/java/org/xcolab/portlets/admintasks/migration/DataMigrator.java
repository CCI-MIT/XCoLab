package org.xcolab.portlets.admintasks.migration;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import com.icesoft.faces.async.render.SessionRenderer;

import java.util.*;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.portlets.admintasks.migration.persistence.NewPersistenceCleaner;
import org.xcolab.portlets.admintasks.migration.persistence.NewPersistenceQueries;
import org.xcolab.portlets.admintasks.migration.persistence.OldPersistenceQueries;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 9/17/13
 * Time: 12:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataMigrator implements Runnable {
    List<String> reference;
    private boolean TESTING = true;
    public boolean STOP = false;

    public DataMigrator(List<String> reference){
        this.reference = reference;
    }

    public void run() {
        setPermissions();

        if (!(new NewPersistenceCleaner(reference).deleteAllRecordsForNewEntities())) return;

        List<Pair<Long,List<PlanItem>>> groupedPlans = getAllDistinctPlanGroupIds();
        pushAjaxUpdate("Creating new Proposals");
        pushAjaxUpdate("0%");
        int counter = 0;

        boolean skippedFirstRecords = false;
        for(Iterator<Pair<Long,List<PlanItem>>> i = groupedPlans.iterator(); i.hasNext(); ) {
            if (STOP) break;

            // for testing skip first 100 plans
            if (TESTING && !skippedFirstRecords) {
                for (int z=0; z<100;z++) i.next();
                skippedFirstRecords = true;
            }

            if (++counter > 0 && (counter % (groupedPlans.size() / 33)) == 0) updateLastAjaxUpdate((100 * counter / groupedPlans.size()) + "%");
            Pair<Long,List<PlanItem>> pair = i.next();
            createNewPlan(pair.getLeft(),pair.getRight());
            if (counter > (groupedPlans.size()/10) && TESTING) break;
        }

        pushAjaxUpdate("-- MIGRATION FINISHED --");
    }

    private void setPermissions(){
        PrincipalThreadLocal.setName(10144L);
        PermissionChecker permissionChecker;
        try {
            permissionChecker = PermissionCheckerFactoryUtil.create(UserLocalServiceUtil.getUser(10144L), true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        PermissionThreadLocal.setPermissionChecker(permissionChecker);
    }

    private List<Pair<Long,List<PlanItem>>> getAllDistinctPlanGroupIds(){
        pushAjaxUpdate("Getting Distinct PlanGroupIDs");
        List<PlanItem> allPlans;
        List<Pair<Long,List<PlanItem>>> results = new LinkedList<Pair<Long,List<PlanItem>>>();
        try {
            allPlans = PlanItemLocalServiceUtil.getPlans();
        } catch (Exception e){
            pushAjaxUpdate("Error: " + e);
            return null;
        }
        pushAjaxUpdate("Got " + allPlans.size() + " Plans");
        pushAjaxUpdate("Grouping Plans");
        pushAjaxUpdate("0%");
        int counter=0;
        for(PlanItem plan :  allPlans) {
            if (++counter > 0 && (counter % (allPlans.size() / 3)) == 0)
                updateLastAjaxUpdate((100 * counter / allPlans.size() + 1) + "%");
            try{
                long groupID = PlanItemGroupLocalServiceUtil.getPlanItemGroup(plan.getPlanId()).getGroupId();
                boolean didFindGroupIdInSet = false;

                    for(Pair<Long,List<PlanItem>> pair : results) {
                        if (pair.getLeft().longValue() == groupID){
                            pair.getRight().add(0,plan);
                            didFindGroupIdInSet = true;
                            break;
                        }
                    }
                if (!didFindGroupIdInSet){
                    Pair<Long,List<PlanItem>> newPair = new Pair<Long,List<PlanItem>>(groupID,new LinkedList<PlanItem>());
                    newPair.getRight().add(plan);
                    results.add(newPair);
                }
            } catch (Exception e){
                System.out.println("No Group Found for ID " + plan.getPlanId());
            }
        }
        pushAjaxUpdate("Done Getting Distinct PlanGroupIDs (count: " + results.size() + ")");
        return results;
    }

    private void createNewPlan(long groupID, List<PlanItem> plans){
        
        // sort plans by create date
        Collections.sort(plans, new Comparator<PlanItem>() {

            public int compare(PlanItem o1, PlanItem o2) {
                return o1.getUpdated().compareTo(o2.getUpdated());
            }
            
        });
        PlanMeta currentPlanMeta = null;
        Proposal2Phase latestP2P = null;
        long currentContestPhase = 0;

        // get current plan meta for setting proposal entity attributes
        try{
            currentPlanMeta = PlanMetaLocalServiceUtil.getCurrentForPlan(plans.get(0));
        } catch (Exception e){
            pushAjaxUpdate("Error while getting PlanMetas " + plans.get(0).getId() + ": " + e);
            return;
        }

        long authorID = currentPlanMeta.getAuthorId();
        
        Proposal proposal = null;
        try {   
            proposal = ProposalLocalServiceUtil.create(authorID);
        } catch (Exception e){
            pushAjaxUpdate("Error while creating Proposal " + groupID + ": " + e);
        }

        // set create date
        proposal.setCreateDate(plans.get(0).getUpdated());

        // set user group
        proposal.setGroupId(currentPlanMeta.getPlanGroupId());
        proposal.setDiscussionId(currentPlanMeta.getCategoryGroupId());

        // update proposal
        try {
            ProposalLocalServiceUtil.updateProposal(proposal);
        } catch (Exception e){
            pushAjaxUpdate("Error while updating Proposal " + groupID + ": " + e);
        }

        List<Long> planFans = new ArrayList<Long>();

        // Loop through all plans - each representing one contest phase
        for(PlanItem plan :  plans) {
            // get updated proposal
            try {
                proposal = ProposalLocalServiceUtil.getProposal(proposal.getProposalId());
            } catch (Exception e){
                pushAjaxUpdate("Error while updating Proposal " + groupID + ": " + e);
            }

            // add mapping to old DB schema
            createPlan2ProposalMapping(plan,proposal);

            // map proposal to contest phases
            latestP2P = createProposal2PhaseMapping(plan,proposal, latestP2P, currentContestPhase);
            currentContestPhase++;

            // plan entity represents a last version of a plan (for given planId) so we need to iterate over all of it's versions
            // to create respective attributes of a proposal
            createProposalAttributesFromPlan(plan,proposal);
            createVotesFromPlan(plan,proposal);
            // copy supporters (fans)
            createSupporters(plan,proposal,planFans);

            // create ribbons for this plan
            createRibbons(plan, proposal);
        }
    }

    private void createVotesFromPlan(PlanItem plan, Proposal proposal){
        List<PlanVote> planVotes = null;
        long contestPhase = 0;
        try{
            planVotes = PlanVoteLocalServiceUtil.getPlanVotes(plan.getPlanId());
            contestPhase = PlanItemLocalServiceUtil.getContestPhase(plan).getContestPhasePK();
        } catch (Exception e){
            pushAjaxUpdate("Error while getting Votes " + plan.getPlanId() + ": " + e);
            return;
        }

        for (PlanVote planVote : planVotes){
            ProposalVote vote = ProposalVoteLocalServiceUtil.create(contestPhase, planVote.getUserId());
            vote.setCreateDate(planVote.getCreateDate());
            vote.setProposalId(proposal.getProposalId());
            try{
                ProposalVoteLocalServiceUtil.addProposalVote(vote);
            } catch (Exception e){
                pushAjaxUpdate("Error while persisting Votes " + plan.getPlanId() + ": " + e);
                return;
            }
        }


    }

    private void createSupporters(PlanItem plan, Proposal proposal, List<Long> proposalSupporter){
        List<PlanFan> planFans = null;
        long contestPhase = 0;
        try{
            planFans = PlanFanLocalServiceUtil.getPlanFansForPlan(plan.getPlanId());
        } catch (Exception e){
            pushAjaxUpdate("Error while getting Fans " + plan.getPlanId() + ": " + e);
            return;
        }

        for (PlanFan planFan : planFans){
            // add supporter if not already added
            if (!proposalSupporter.contains(planFan.getUserId())){
                ProposalSupporter supporter = ProposalSupporterLocalServiceUtil.create(proposal.getProposalId(),planFan.getUserId());
                supporter.setCreateDate(planFan.getCreated());
                try{
                    ProposalSupporterLocalServiceUtil.addProposalSupporter(supporter);
                } catch (Exception e){
                    pushAjaxUpdate("Error while persisting Supporters " + plan.getPlanId() + ": " + e);
                    return;
                }
                proposalSupporter.add(planFan.getUserId());
            }
        }


    }

    private void createPlan2ProposalMapping(PlanItem plan, Proposal proposal){
        Plan2Proposal plan2Proposal = Plan2ProposalLocalServiceUtil.createPlan2Proposal(plan.getPlanId());
        plan2Proposal.setProposalId(proposal.getProposalId());

        try {
            Plan2ProposalLocalServiceUtil.addPlan2Proposal(plan2Proposal);
        } catch (SystemException e1) {
            pushAjaxUpdate("Error while creating mapping between plan " + plan.getPlanId() + " and proposal: " + proposal.getProposalId() + "\n" + e1);
        }
    }

    private Proposal2Phase createProposal2PhaseMapping(PlanItem plan, Proposal proposal, Proposal2Phase latestP2P, long currentContestPhase){
        // handle new Proposal2Contest Phase Mapping

        if (currentContestPhase > 0){
            // set VersionTo for old p2p mapping
            latestP2P.setVersionTo(proposal.getCurrentVersion());
            try{
                Proposal2PhaseLocalServiceUtil.updateProposal2Phase(latestP2P);
            }catch (SystemException e1) {
                pushAjaxUpdate("Error while storing Proposal2Phase mapping" + e1);
            }
        }
        // add new p2p record
        System.out.println("Current Version:" + proposal.getCurrentVersion() + " ID: " + proposal.getProposalId() + " ContestPhase: " + currentContestPhase);
        PlanMeta matchingMeta = null;
        try {
            for (PlanMeta pm : PlanMetaLocalServiceUtil.getAllForPlan(plan)){
                if (pm.getPlanVersion() == 0){
                    matchingMeta = pm;
                }
            }
        } catch (SystemException e1) {
            pushAjaxUpdate("Error while creating mapping between plan " + plan.getPlanId() + " and proposal: " + proposal.getProposalId() + "\n" + e1);
        }

        if(matchingMeta != null){
            Proposal2Phase p2p = Proposal2PhaseLocalServiceUtil.create(proposal.getProposalId(),matchingMeta.getContestPhase());
            p2p.setSortWeight(1);
            p2p.setAutopromoteCandidate(false);
            p2p.setVersionFrom(proposal.getCurrentVersion());  /* TODO how should we increment the version set/get attr?*/
            p2p.setVersionTo(-1);

            try{
                Proposal2PhaseLocalServiceUtil.addProposal2Phase(p2p);
            }catch (SystemException e1) {
                pushAjaxUpdate("Error while storing Proposal2Phase mapping" + e1);
            }
            return p2p;
        } else{
            System.out.println("Not matching meta found: " + plan.getPlanId());
            return null;
        }
    }

    private void createProposalAttributesFromPlan(PlanItem plan, Proposal proposal){
        List<PlanItem> planVersions = null;
        try {
            planVersions = PlanItemLocalServiceUtil.getAllVersions(plan);
        } catch (SystemException e1) {
            pushAjaxUpdate("Error while fetching plan versions: " + plan.getPlanId() + "\n" + e1);
        }

        for (PlanItem planVersion : planVersions) {
            if (planVersion.getUpdateType().equalsIgnoreCase("CREATED")){
                // ignore use just for transfering from one phase to another
            } else if (planVersion.getUpdateType().equalsIgnoreCase("MODEL_UPDATED")){
                // IGNORE because modelID is deprecated
            } else if (planVersion.getUpdateType().equalsIgnoreCase("SCENARIO_UPDATED")){
                // Get scenarioId from xcolab_PlanModelRun and use it to create attribute
                setAttributeRelatedToScenario(planVersion,proposal,"SCENARIO_UPDATED");
            } else if (planVersion.getUpdateType().equalsIgnoreCase("PLAN_POSITIONS_UPDATED")){
                // IGNORE
            } else if (planVersion.getUpdateType().equalsIgnoreCase("PLAN_DELETED")){
                // SET visibility = false
                proposal.setVisible(false);
            } else if (planVersion.getUpdateType().equalsIgnoreCase("DESCRIPTION_UPDATED")){
                // Get description from xcolab_PlanDescription and use setAttribute
                setAttributeRelatedToPlanDescription(planVersion,proposal,"DESCRIPTION");
            } else if (planVersion.getUpdateType().equalsIgnoreCase("NAME_UPDATED")){
                // Get name from xcolab_PlanDescription and use setAttribute
                setAttributeRelatedToPlanDescription(planVersion,proposal,"NAME");
            } else if (planVersion.getUpdateType().equalsIgnoreCase("PLAN_STATUS_UPDATED")){
                // IGNORE (connected to PlanMeta (col: Status)
            } else if (planVersion.getUpdateType().equalsIgnoreCase("PLAN_CLOSED")){
                // Get open from PlanMeta - anyone can edit (open), only team members can edit
                setAttributeRelatedToPlanMeta(planVersion,proposal,"PLAN_CLOSED");
            } else if (planVersion.getUpdateType().equalsIgnoreCase("PLAN_OPENED")){
                // Get open from PlanMeta - anyone can edit (open), only team members can edit
                setAttributeRelatedToPlanMeta(planVersion,proposal,"PLAN_OPENED");
            } else if (planVersion.getUpdateType().equalsIgnoreCase("PLAN_SECTION_UPDATED")){
                // Get PlanSection -> .setAttr. (col: additionalId = planSectionDef. and String = content (date from PlanITem)
                setAttributeRelatedToPlanSection(planVersion,proposal,"PLAN_SECTION_UPDATED");
            } else if (planVersion.getUpdateType().equalsIgnoreCase("PITCH_UPDATED")){
                // Get pitch from xcolab_PlanDescription and use setAttribute
                setAttributeRelatedToPlanDescription(planVersion,proposal,"PITCH");
            } else if (planVersion.getUpdateType().equalsIgnoreCase("IMAGE_UPDATED")){
                // Get image_id from xcolab_PlanDescription and use setAttribute
                setAttributeRelatedToPlanDescription(planVersion,proposal,"IMAGE_ID");
            } else if (planVersion.getUpdateType().equalsIgnoreCase("PLAN_REVERTED")){
                // IGNORE
            }
        }
    }

    private void updateLatestVersionDate(Proposal proposal, Date date){
        try{
            ProposalVersion latestVersion = NewPersistenceQueries.getLatestVersionForProposal(proposal);
            if (latestVersion != null){
                latestVersion.setCreateDate(date);
                ProposalVersionLocalServiceUtil.updateProposalVersion(latestVersion);
            } else {
                System.out.println("Could not update Version at proposalId: " + proposal.getProposalId());
            }
        } catch (Exception e){
            e.printStackTrace();
            pushAjaxUpdate("Could not find latest version for proposal: " + proposal.getProposalId());
            return;
        }
    }

    private void setAttributeRelatedToScenario(PlanItem plan, Proposal p, String attribute){
        List<PlanModelRun> pmrs = null;
        try{
            pmrs = PlanModelRunLocalServiceUtil.getAllForPlan(plan);
        } catch(Exception e){
            pushAjaxUpdate("Error while getting ScenarioID " + plan.getPlanId() + ": " + e);
        }

        for(PlanModelRun pmr : pmrs) {
            if (pmr.getPlanVersion() == plan.getVersion()){
                try{
                    PlanType planType = PlanItemLocalServiceUtil.getPlanType(plan);
                    ProposalLocalServiceUtil.setAttribute(plan.getUpdateAuthorId(),p.getProposalId(),ProposalAttributeKeys.SCENARIO_ID,planType.getModelId(),null,pmr.getScenarioId(),0);
                    updateLatestVersionDate(p,plan.getUpdated());
                } catch(Exception e){
                    pushAjaxUpdate("Error while setting ScenarioID " + plan.getPlanId() + ": " + e);
                }
                break;
            }
        }
    }

    private void setAttributeRelatedToPlanMeta(PlanItem plan, Proposal p, String attribute){
        List<PlanMeta> planMetas = null;
        try{
            planMetas = PlanMetaLocalServiceUtil.getAllForPlan(plan);
        } catch(Exception e){
            pushAjaxUpdate("Error while getting description record " + plan.getPlanId() + ": " + e);
        }

        for(PlanMeta planMeta : planMetas) {
            if (planMeta.getPlanVersion() == plan.getVersion()){
                try{
                    if(attribute.equalsIgnoreCase("PLAN_OPENED") || attribute.equalsIgnoreCase("PLAN_CLOSED"))
                        ProposalLocalServiceUtil.setAttribute(plan.getUpdateAuthorId(),p.getProposalId(),ProposalAttributeKeys.OPEN,0,null,planMeta.getOpen() ? 1 : 0,0);
                        updateLatestVersionDate(p,plan.getUpdated());
                } catch(Exception e){
                    pushAjaxUpdate("Error while setting Attribute " + plan.getPlanId() + ": " + e);
                }
                break;
            }
        }
    }

    private void setAttributeRelatedToPlanSection(PlanItem plan, Proposal p, String attribute){

        List<PlanSection> planSections = OldPersistenceQueries.getPlanSectionsForPlan(plan);

        if (planSections == null) {
            pushAjaxUpdate("Error while getting PlanSections");
            return;
        }

        for(PlanSection planSection :  planSections) {
            try{
                ProposalLocalServiceUtil.setAttribute(plan.getUpdateAuthorId(),p.getProposalId(),ProposalAttributeKeys.SECTION,
                        planSection.getPlanSectionDefinitionId(),planSection.getContent(),0,0);
                updateLatestVersionDate(p,plan.getUpdated());
            } catch (Exception e){
                pushAjaxUpdate("Error while setting Section " + plan.getPlanId() + ": " + e);
            }
        }
    }

    private void setAttributeRelatedToPlanDescription(PlanItem plan, Proposal p, String attribute){
        List<PlanDescription> planDescriptions = null;
        try{
            planDescriptions = PlanDescriptionLocalServiceUtil.getAllForPlan(plan);
        } catch(Exception e){
            pushAjaxUpdate("Error while getting description record " + plan.getPlanId() + ": " + e);
        }
        for(PlanDescription planDescription : planDescriptions) {
            if (planDescription.getPlanVersion() == plan.getVersion()){
                try{
                    if(attribute.equalsIgnoreCase(ProposalAttributeKeys.NAME))
                        ProposalLocalServiceUtil.setAttribute(plan.getUpdateAuthorId(),p.getProposalId(),attribute,0,planDescription.getName(),0,0);
                    else if(attribute.equalsIgnoreCase(ProposalAttributeKeys.DESCRIPTION))
                        ProposalLocalServiceUtil.setAttribute(plan.getUpdateAuthorId(),p.getProposalId(),attribute,0,planDescription.getDescription(),0,0);
                    else if(attribute.equalsIgnoreCase(ProposalAttributeKeys.PITCH))
                        ProposalLocalServiceUtil.setAttribute(plan.getUpdateAuthorId(),p.getProposalId(),attribute,0,planDescription.getPitch(),0,0);
                    else if(attribute.equalsIgnoreCase(ProposalAttributeKeys.IMAGE_ID))
                        ProposalLocalServiceUtil.setAttribute(plan.getUpdateAuthorId(),p.getProposalId(),attribute,0,null,planDescription.getImage(),0);
                    updateLatestVersionDate(p,plan.getUpdated());
                } catch(Exception e){
                    pushAjaxUpdate("Error while setting Attribute " + plan.getPlanId() + ": " + e);
                }
                break;
            }
        }
    }

    private void createRibbons(PlanItem plan, Proposal p){
        Pair<Long,String> ribbon = OldPersistenceQueries.getRibbonAndHoverTextForPlan(plan);

        if (ribbon == null) return;

        PlanMeta currentPlanMeta = null;
        // Get current plan meta for setting proposal entity attributes
        try{
            currentPlanMeta = PlanMetaLocalServiceUtil.getCurrentForPlan(plan);
        } catch (Exception e){
            pushAjaxUpdate("Error while getting PlanMetas " + plan.getId() + ": " + e);
            return;
        }

        long ribbonContestPhaseAttributeTypeId = NewPersistenceQueries
                .getProposalContestPhaseAttributeTypeIdForRibbon(ribbon.getLeft() + "", ribbon.getRight());

        if (ribbonContestPhaseAttributeTypeId > 0){
            // Create new ContestPhaseAttribute
            NewPersistenceQueries.createNewProposalContestPhaseAttribute(
                    p.getProposalId(), ribbonContestPhaseAttributeTypeId, currentPlanMeta.getContestPhase());
        } else {
            // Set up new ContestPhaseAttributeType
            ProposalContestPhaseAttributeType attributeType = NewPersistenceQueries
                    .createNewProposalContestPhaseAttributeType(ribbon.getLeft() + "", ribbon.getRight());
            if (attributeType == null){
                pushAjaxUpdate("Error while creating AttributeType");
                return;
            }
            // Create new ContestPhaseAttribute
            NewPersistenceQueries.createNewProposalContestPhaseAttribute(
                    p.getProposalId(), attributeType.getId(), currentPlanMeta.getContestPhase());
        }

    }

    private void pushAjaxUpdate(String message){
        reference.add(message);
        SessionRenderer.render("migration");
    }

    private void updateLastAjaxUpdate(String message){
        reference.remove(reference.size()-1);
        reference.add((message));
        SessionRenderer.render("migration");
    }
}