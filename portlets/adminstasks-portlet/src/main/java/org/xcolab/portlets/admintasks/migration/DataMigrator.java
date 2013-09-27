package org.xcolab.portlets.admintasks.migration;

import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import com.ext.portlet.service.persistence.Proposal2PhasePK;
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
import org.xcolab.portlets.admintasks.migration.Pair;


/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 9/17/13
 * Time: 12:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataMigrator implements Runnable {
    List<String> reference;

    private static final String ENTITY_CLASS_LOADER_CONTEXT = "plansProposalsFacade-portlet";

    private boolean TESTING = false;

    public boolean STOP = false;

    public DataMigrator(List<String> reference){
        this.reference = reference;

    }

    public void run() {

        setPermissions();

        pushAjaxUpdate("Cleaning DB");
        boolean dbSuccess = true;
        dbSuccess &= deleteAllProposals() &
                deleteAllProposalVersions() &
                deleteAllProposalAttributes() &
                deleteAllPlan2Proposal() &
                deleteAllProposal2Phase();
        if (!dbSuccess) return;
        pushAjaxUpdate("Successfully cleaned DB");

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

            System.out.println(counter + "\t" + groupedPlans.size() + "\t" + (counter % (groupedPlans.size() / 33)));
            if (++counter > 0 && (counter % (groupedPlans.size() / 33)) == 0) updateLastAjaxUpdate((100 * counter / groupedPlans.size()) + "%");
            Pair<Long,List<PlanItem>> pair = i.next();
            createNewPlan(pair.getLeft(),pair.getRight());
            if (counter > (groupedPlans.size()/10) && TESTING) break;
        }

        pushAjaxUpdate("-- MIGRATION FINISHED WITHOUT ERRORS --");
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


    private List<Pair<Long,List<PlanItem>>> getAllDistinctPlanGroupIds(){
        pushAjaxUpdate("Getting Distinct PlanGroupIDs");
        List<PlanItem> allPlans;
        List<Pair<Long,List<PlanItem>>> results = new LinkedList<Pair<Long,List<PlanItem>>>();
        try {
            //allPlans = PlanItemLocalServiceUtil.getPlanItems(0,PlanItemLocalServiceUtil.getPlanItemsCount());
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
            if (++counter > 0 && (counter % (allPlans.size() / 3)) == 0) updateLastAjaxUpdate((100 * counter / allPlans.size() + 1) + "%");
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
                //return null;
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
                    ProposalLocalServiceUtil.setAttribute(plan.getUpdateAuthorId(),p.getProposalId(),"SCENARIO_ID",0,null,pmr.getScenarioId(),0);
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
                        ProposalLocalServiceUtil.setAttribute(plan.getUpdateAuthorId(),p.getProposalId(),"OPEN",0,null,planMeta.getOpen() ? 1 : 0,0);
                } catch(Exception e){
                    pushAjaxUpdate("Error while setting Attribute " + plan.getPlanId() + ": " + e);
                }
                break;
            }
        }
    }

    private void setAttributeRelatedToPlanSection(PlanItem plan, Proposal p, String attribute){
        // get all sections related to this plan and version
        ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(
                ENTITY_CLASS_LOADER_CONTEXT, "portletClassLoader");

        DynamicQuery sectionQuery = DynamicQueryFactoryUtil.forClass(PlanSection.class, portletClassLoader);
        sectionQuery.add(PropertyFactoryUtil.forName("planVersion").eq(plan.getVersion()));
        sectionQuery.add(PropertyFactoryUtil.forName("planId").eq(plan.getPlanId()));
        sectionQuery.addOrder(OrderFactoryUtil.asc("version"));

        List<PlanSection> planSections = null;
        try{
            planSections = PlanSectionLocalServiceUtil.dynamicQuery(sectionQuery);
        } catch (Exception e){
            pushAjaxUpdate("Error while getting Section " + plan.getPlanId() + ": " + e);
        }
        //if (planSections != null && planSections.size() > 1) pushAjaxUpdate("Found more then one PlanSection: (id) " + plan.getPlanId());

        for(PlanSection planSection :  planSections) {
            try{
                ProposalLocalServiceUtil.setAttribute(plan.getUpdateAuthorId(),p.getProposalId(),"SECTION",
                        planSection.getPlanSectionDefinitionId(),planSection.getContent(),0,0);
            } catch (Exception e){
                pushAjaxUpdate("Error while setting Section " + plan.getPlanId() + ": " + e);
            }
        }
    }

    private void setAttributeRelatedToPlanDescription(PlanItem plan, Proposal p, String attribute){
        // name descr pitch  image ID
        List<PlanDescription> planDescriptions = null;
        try{
            planDescriptions = PlanDescriptionLocalServiceUtil.getAllForPlan(plan);
        } catch(Exception e){
            pushAjaxUpdate("Error while getting description record " + plan.getPlanId() + ": " + e);
        }
        for(PlanDescription planDescription : planDescriptions) {
            if (planDescription.getPlanVersion() == plan.getVersion()){
                try{
                    if(attribute.equalsIgnoreCase("NAME"))
                        ProposalLocalServiceUtil.setAttribute(plan.getUpdateAuthorId(),p.getProposalId(),attribute,0,planDescription.getName(),0,0);
                    else if(attribute.equalsIgnoreCase("DESCRIPTION"))
                        ProposalLocalServiceUtil.setAttribute(plan.getUpdateAuthorId(),p.getProposalId(),attribute,0,planDescription.getDescription(),0,0);
                    else if(attribute.equalsIgnoreCase("PITCH"))
                        ProposalLocalServiceUtil.setAttribute(plan.getUpdateAuthorId(),p.getProposalId(),attribute,0,planDescription.getPitch(),0,0);
                    else if(attribute.equalsIgnoreCase("IMAGE_ID"))
                        ProposalLocalServiceUtil.setAttribute(plan.getUpdateAuthorId(),p.getProposalId(),attribute,0,null,planDescription.getImage(),0);
                } catch(Exception e){
                    pushAjaxUpdate("Error while setting Attribute " + plan.getPlanId() + ": " + e);
                }
                break;
            }
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


