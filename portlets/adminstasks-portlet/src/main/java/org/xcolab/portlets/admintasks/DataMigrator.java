package org.xcolab.portlets.admintasks;

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

    private boolean TESTING = true;

    public DataMigrator(List<String> reference){
        this.reference = reference;

    }

    public void run() {

        setPermissions();

        pushAjaxUpdate("Cleaning DB");
        boolean dbSuccess = true;
        dbSuccess &= deleteAllProposals();
        dbSuccess &= deleteAllProposalVersions();
        dbSuccess &= deleteAllProposalAttributes();
        dbSuccess &= deleteAllPlan2Proposal();
        if (!dbSuccess) return;
        pushAjaxUpdate("Successfully cleaned DB");

        List<Pair<Long,List<PlanItem>>> groupedPlans = getAllDistinctPlanGroupIds();
        pushAjaxUpdate("Creating new Proposals");
        pushAjaxUpdate("0%");
        int counter = 0;
        
        for(Iterator<Pair<Long,List<PlanItem>>> i = groupedPlans.iterator(); i.hasNext(); ) {
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
            List<Proposal> proposals = ProposalLocalServiceUtil.getProposals(0, numberOfProposals);
            for(Iterator<Proposal> i = proposals.iterator(); i.hasNext(); ) {
                ProposalLocalServiceUtil.deleteProposal(i.next());
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
            for(Iterator<ProposalVersion> i = versions.iterator(); i.hasNext(); ) {
                ProposalVersionLocalServiceUtil.deleteProposalVersion(i.next());
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
            for(Iterator<ProposalAttribute> i = attributes.iterator(); i.hasNext(); ) {
                ProposalAttributeLocalServiceUtil.deleteProposalAttribute(i.next());
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
        for(Iterator<PlanItem> i = allPlans.iterator(); i.hasNext(); ) {
            PlanItem plan = i.next();
            if (++counter > 0 && (counter % (allPlans.size() / 3)) == 0) updateLastAjaxUpdate((100 * counter / allPlans.size() + 1) + "%");
            try{
                long groupID = PlanItemLocalServiceUtil.getPlanGroupId(plan);
                boolean didFindGroupIdInSet = false;

                    for(Iterator<Pair<Long,List<PlanItem>>> j = results.iterator(); j.hasNext(); ) {
                        Pair<Long,List<PlanItem>> pair = j.next();
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
                pushAjaxUpdate("Error: " + e);
                return null;
            }
        }
        pushAjaxUpdate("Done Getting Distinct PlanGroupIDs (count: " + results.size() + ")");
        return results;
    }

    private void createNewPlan(long groupID, List<PlanItem> plans){
        PlanMeta currentPlanMeta = null;
        try{
            currentPlanMeta = PlanMetaLocalServiceUtil.getCurrentForPlan(plans.get(0));
        } catch (Exception e){
            pushAjaxUpdate("Error while getting PlanMetas " + plans.get(0).getId() + ": " + e);
            return;
        }

        long authorID = currentPlanMeta.getAuthorId();
        
        Proposal p = null;
        try {   
            p = ProposalLocalServiceUtil.create(authorID);
        } catch (Exception e){
            pushAjaxUpdate("Error while creating Proposal " + groupID + ": " + e);
        }
        // set create date
        p.setCreateDate(plans.get(0).getUpdated());
        // set user group

        p.setGroupId(currentPlanMeta.getPlanGroupId());
        p.setDiscussionId(currentPlanMeta.getCategoryGroupId());




        for(Iterator<PlanItem> i = plans.iterator(); i.hasNext(); ) {
            PlanItem plan = i.next();
            
             
            Plan2Proposal plan2Proposal = Plan2ProposalLocalServiceUtil.createPlan2Proposal(plan.getPlanId());
            plan2Proposal.setProposalId(p.getProposalId());
            
            try {
                Plan2ProposalLocalServiceUtil.addPlan2Proposal(plan2Proposal);
            } catch (SystemException e1) {
                pushAjaxUpdate("Error while creating mapping between plan " + plan.getPlanId() + " and proposal: " + p.getProposalId() + "\n" + e1);
            }

            // plan entity represents a last version of a plan (for given planId) so we need to iterate over all of it's versions
            
            List<PlanItem> planVersions = null;
            try {
                planVersions = PlanItemLocalServiceUtil.getAllVersions(plan);
            } catch (SystemException e1) {
                pushAjaxUpdate("Error while fetching plan versions: " + plan.getPlanId() + "\n" + e1);
            }
            
            for (PlanItem planVersion: planVersions) {
                if (planVersion.getUpdateType().equalsIgnoreCase("CREATED")){
                    // ignore use just for transfering from one phase to another

                } else if (planVersion.getUpdateType().equalsIgnoreCase("MODEL_UPDATED")){
                    // IGNORE because modelID is deprecated
                } else if (planVersion.getUpdateType().equalsIgnoreCase("SCENARIO_UPDATED")){
                    // Get scenarioId from xcolab_PlanModelRun and use it to create attribute
                    List<PlanModelRun> pmrs = null;
                    try{
                        pmrs = PlanModelRunLocalServiceUtil.getAllForPlan(planVersion);
                    } catch(Exception e){
                        pushAjaxUpdate("Error while getting ScenarioID " + planVersion.getPlanId() + ": " + e);
                    }
                
                    for(Iterator<PlanModelRun> j = pmrs.iterator(); j.hasNext(); ) {
                        PlanModelRun pmr = j.next();
                        if (pmr.getPlanVersion() == planVersion.getVersion()){
                            try{
                                ProposalLocalServiceUtil.setAttribute(planVersion.getUpdateAuthorId(),p.getProposalId(),"SCENARIO_ID",0,null,pmr.getScenarioId(),0);
                            } catch(Exception e){
                                pushAjaxUpdate("Error while setting ScenarioID " + planVersion.getPlanId() + ": " + e);
                            }
                            break;
                        }
                    }
                } else if (planVersion.getUpdateType().equalsIgnoreCase("PLAN_POSITIONS_UPDATED")){
                    // IGNORE
                } else if (planVersion.getUpdateType().equalsIgnoreCase("PLAN_DELETED")){
                    // SET visibility = false
                    p.setVisible(false);
                } else if (planVersion.getUpdateType().equalsIgnoreCase("DESCRIPTION_UPDATED")){
                    // Get description from xcolab_PlanDescription and use setAttribute
                    setAttributeRelatedToPlanDescription(planVersion,p,"DESCRIPTION");
                } else if (planVersion.getUpdateType().equalsIgnoreCase("NAME_UPDATED")){
                    // Get name from xcolab_PlanDescription and use setAttribute
                    setAttributeRelatedToPlanDescription(planVersion,p,"NAME");
                } else if (planVersion.getUpdateType().equalsIgnoreCase("PLAN_STATUS_UPDATED")){
                    // IGNORE (connected to PlanMeta (col: Status)
                } else if (planVersion.getUpdateType().equalsIgnoreCase("PLAN_CLOSED")){
                    // Get open from PlanMeta - anyone can edit (open), only team members can edit
                    setAttributeRelatedToPlanMeta(planVersion,p,"PLAN_CLOSED");
                } else if (planVersion.getUpdateType().equalsIgnoreCase("PLAN_OPENED")){
                    // Get open from PlanMeta - anyone can edit (open), only team members can edit
                    setAttributeRelatedToPlanMeta(planVersion,p,"PLAN_OPENED");
                } else if (planVersion.getUpdateType().equalsIgnoreCase("PLAN_SECTION_UPDATED")){
                    // Get PlanSection -> .setAttr. (col: additionalId = planSectionDef. and String = content (date from PlanITem)
                    setAttributeRelatedToPlanSection(planVersion,p,"PLAN_SECTION_UPDATED");
                } else if (planVersion.getUpdateType().equalsIgnoreCase("PITCH_UPDATED")){
                    // Get pitch from xcolab_PlanDescription and use setAttribute
                    setAttributeRelatedToPlanDescription(planVersion,p,"PITCH");
                } else if (planVersion.getUpdateType().equalsIgnoreCase("IMAGE_UPDATED")){
                    // Get image_id from xcolab_PlanDescription and use setAttribute
                    setAttributeRelatedToPlanDescription(planVersion,p,"IMAGE_ID");
                } else if (planVersion.getUpdateType().equalsIgnoreCase("PLAN_REVERTED")){
                    // IGNORE
                }
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
        for(Iterator<PlanMeta> j = planMetas.iterator(); j.hasNext(); ) {
            PlanMeta planMeta = j.next();
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
        /* TODO */
    }




    private void setAttributeRelatedToPlanDescription(PlanItem plan, Proposal p, String attribute){
        // name descr pitch  image ID
        List<PlanDescription> planDescriptions = null;
        try{
            planDescriptions = PlanDescriptionLocalServiceUtil.getAllForPlan(plan);
        } catch(Exception e){
            pushAjaxUpdate("Error while getting description record " + plan.getPlanId() + ": " + e);
        }
        for(Iterator<PlanDescription> j = planDescriptions.iterator(); j.hasNext(); ) {
            PlanDescription planDescription = j.next();
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


