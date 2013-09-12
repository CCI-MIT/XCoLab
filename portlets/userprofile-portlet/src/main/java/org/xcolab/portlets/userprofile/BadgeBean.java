package org.xcolab.portlets.userprofile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import org.xcolab.portlets.userprofile.entity.Badge;
import com.ext.portlet.model.PlanItem;


/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 9/6/13
 * Time: 5:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class BadgeBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private long userID; // USER the Badges belong to
    private java.util.List<Badge> badges;
    private java.util.List<PlanItem> currentUsersPlans;


    public BadgeBean(long userID){
        this.userID = userID;
        badges = new ArrayList<Badge>();
        fetchBadges();
    }



    private void fetchBadges(){
        List<PlanItem> plans;
        try{
            plans = PlanItemLocalServiceUtil.getPlansForUser(this.userID);
            if (plans == null) return;
        } catch(Exception e) { return; };

        // Iterate over all plans
        for(Iterator<PlanItem> i = plans.iterator(); i.hasNext(); ) {
            PlanItem plan = i.next();

            int planRibbon = -1;
            try {
                planRibbon = PlanItemLocalServiceUtil.getRibbon(plan);
            } catch (Exception e) { }

            if (planRibbon > 0) {
                // Plan won a contest
                try {
                    String badgeText = PlanItemLocalServiceUtil.getPlanAttribute(plan,
                            PlanConstants.Attribute.PLAN_RIBBON_TEXT.toString()).getAttributeValue();
                    long contestId = PlanItemLocalServiceUtil.getContest(plan).getContestPK();
                    String planTitle = PlanItemLocalServiceUtil.getPlanAttribute(plan,
                            PlanConstants.Attribute.NAME.toString()).getAttributeValue();

                    badges.add(new Badge(planRibbon, badgeText, plan.getPlanId(), planTitle, contestId));
                } catch (Exception e) { }
            }
        }
    }

    public java.util.List<Badge> getBadges(){
        return badges;
    }

    @Override
    public String toString(){
        return badges.toString();
    }
}