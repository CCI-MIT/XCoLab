package com.ext.portlet.Activity.nameGenerators;

import com.ext.portlet.NoSuchPlanItemException;
import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.plans.PlanUtils;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class PlanSubscriptionNameGenerator extends BaseSubscriptionNameGenerator {

    public static String hyperlink = "<a href=\"%s\">%s</a>";
    private final static Log _log = LogFactoryUtil.getLog(PlanSubscriptionNameGenerator.class);
    
    
    @Override
    public String getName(ActivitySubscription subscription) {
        // name of activity "stream" for given parameters is name of a plan that this activity relates to
        Long classPK = subscription.getClassPK();
        try {
            PlanItem plan = PlanItemLocalServiceUtil.getPlan(classPK);
            return "Proposal: " + String.format(hyperlink, PlanUtils.getPlanURL(plan),PlanItemLocalServiceUtil.getName(plan));
        }
        catch (NoSuchPlanItemException e) {
            _log.error("Can't find plan for id: " + classPK, e);
        } catch (SystemException e) {
            _log.error("Can't find plan for id: " + classPK, e);
        } catch (PortalException e) {
            _log.error("Can't find plan for id: " + classPK, e);
        }
        
        return "";
        
    }


    @Override
    protected Class<?> getSupportedClass() {
        return PlanItem.class;
    }

}
