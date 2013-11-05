package org.xcolab.portlets.admintasks.migration.persistence;

import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import org.apache.commons.lang3.StringUtils;
import org.xcolab.portlets.admintasks.migration.Pair;
import com.liferay.portlet.social.model.SocialActivity;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 10/1/13
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class OldPersistenceQueries {
    private static final String ENTITY_CLASS_LOADER_CONTEXT = "plansProposalsFacade-portlet";
    private static ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(
            ENTITY_CLASS_LOADER_CONTEXT, "portletClassLoader");

    public static List<PlanAttribute> getRibbonsForPlan(PlanItem plan){
        // Get Ribbons For a plan
        DynamicQuery planRibbonQuery = DynamicQueryFactoryUtil.forClass(PlanAttribute.class, portletClassLoader);
        planRibbonQuery.add(PropertyFactoryUtil.forName("attributeName").like("PLAN_RIBBON%"));
        planRibbonQuery.add(PropertyFactoryUtil.forName("planId").eq(plan.getPlanId()));

        List<PlanAttribute> ribbons = null;
        try{
            ribbons = PlanAttributeLocalServiceUtil.dynamicQuery(planRibbonQuery);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return ribbons;
    }

    public static Pair<Long,String> getRibbonAndHoverTextForPlan(PlanItem plan){
        List<PlanAttribute> ribbons = OldPersistenceQueries.getRibbonsForPlan(plan);
        if (ribbons == null) return null;

        long planRibbon = -1;
        String planRibbonText = "";

        for (PlanAttribute pa : ribbons){
            if (pa.getAttributeName().equalsIgnoreCase("PLAN_RIBBON")){
                try{
                    planRibbon = Long.parseLong(pa.getAttributeValue());
                } catch (NumberFormatException e){
                    planRibbon = -1;
                }
            } else if (pa.getAttributeName().equalsIgnoreCase("PLAN_RIBBON_TEXT")){
                planRibbonText = pa.getAttributeValue();
            }
        }

        if (planRibbon == -1) return null;
        return new Pair<Long, String>(planRibbon,planRibbonText);
    }

    public static List<PlanSection> getPlanSectionsForPlan(PlanItem plan){
        DynamicQuery sectionQuery = DynamicQueryFactoryUtil.forClass(PlanSection.class, portletClassLoader);
        sectionQuery.add(PropertyFactoryUtil.forName("planVersion").eq(plan.getVersion()));
        sectionQuery.add(PropertyFactoryUtil.forName("planId").eq(plan.getPlanId()));
        sectionQuery.addOrder(OrderFactoryUtil.asc("version"));

        List<PlanSection> planSections = null;
        try{
            planSections = PlanSectionLocalServiceUtil.dynamicQuery(sectionQuery);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return planSections;
    }

    public static List<PlanItem> getAllVersionsForPlanASC(long planId){
        DynamicQuery planQuery = DynamicQueryFactoryUtil.forClass(PlanItem.class, portletClassLoader);
        planQuery.add(PropertyFactoryUtil.forName("planId").eq(planId));
        planQuery.addOrder(OrderFactoryUtil.asc("version"));

        List<PlanItem> planItems = null;
        try{
            planItems = PlanItemLocalServiceUtil.dynamicQuery(planQuery);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return planItems;
    }

    public static List<Long> getPlanItemsWithoutGroups(){
        DynamicQuery plansInGroups = DynamicQueryFactoryUtil.forClass(PlanItemGroup.class, portletClassLoader);
        plansInGroups.setProjection(ProjectionFactoryUtil.property("planId"));
        DynamicQuery planItems = DynamicQueryFactoryUtil.forClass(PlanItem.class, portletClassLoader);
        planItems.add(PropertyFactoryUtil.forName("planId").notIn(plansInGroups));
        planItems.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("planId")));

        try{
            return PlanItemLocalServiceUtil.dynamicQuery(planItems);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static void updateSocialActivityClassPKToProposalId (long groupID, List<PlanItem> plans){
        for (PlanItem pi : plans){
            try{
                List<SocialActivity> sa =  SocialActivityLocalServiceUtil.getActivities(0,ClassNameLocalServiceUtil.getClassNameId(PlanItem.class),pi.getPlanId(),0,Integer.MAX_VALUE);
                for (SocialActivity s : sa){
                    s.setClassPK(plans.get(plans.size()-1).getPlanId());
                    SocialActivityLocalServiceUtil.updateSocialActivity(s);
                }

                // discussions
                sa =  SocialActivityLocalServiceUtil.getActivities(0,39202,pi.getPlanId(),0,Integer.MAX_VALUE);
                String extraData = "";
                long planId = plans.get(plans.size()-1).getPlanId();
                Proposal proposal = ProposalLocalServiceUtil.getProposal(planId);
                for (SocialActivity s : sa){
                    s.setClassPK(proposal.getDiscussionId());
                    if (StringUtils.isNotBlank(s.getExtraData())){
                        extraData = proposal.getDiscussionId() + "," + s.getExtraData().split(",")[1] + "," + s.getExtraData().split(",")[2];
                        s.setExtraData(extraData);
                    }
                    SocialActivityLocalServiceUtil.updateSocialActivity(s);
                }
                if (sa.size() > 0){ // only if there are social activities meaning there was a discussion
                    DiscussionCategoryGroup group = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(pi.getPlanId());
                    group.setUrl(group.getUrl().replaceAll("[0-9]*#plans=tab:comments",planId + "/tab/COMMENTS"));
                    DiscussionCategoryGroupLocalServiceUtil.updateDiscussionCategoryGroup(group);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void fixSocialActivitiesErrors(){
        int errorCounter = 0;
        try {
            List<SocialActivity> sa = SocialActivityLocalServiceUtil.getActivities(39202L,0,Integer.MAX_VALUE);
            for (SocialActivity s : sa){
                try{
                    if (StringUtils.isNotBlank(s.getExtraData())){
                        Proposal p = ProposalLocalServiceUtil.getProposal(s.getClassPK());
                        long threadId = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(p.getDiscussionId()).getCommentsThread();
                        List<DiscussionMessage> messages = DiscussionMessageLocalServiceUtil.getThreadMessages(threadId);
                        long messageId = threadId;
                        for (DiscussionMessage dm : messages)
                            if (dm.getAuthorId() == s.getUserId()) messageId = dm.getMessageId();

                        s.setExtraData(s.getExtraData().split(",")[0] + "," + threadId + "," + messageId);
                        SocialActivityLocalServiceUtil.updateSocialActivity(s);
                    }
                } catch (Exception e){
                    //SocialActivityLocalServiceUtil.deleteActivity(s.getActivityId());
                    errorCounter++;
                }

            }
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Errors: " + errorCounter);


    }

}
