package org.xcolab.portlets.admintasks;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.PlanFan;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

public class SyncProposalSupportersBetweenPhasesTask implements MessageListener, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log _log = LogFactoryUtil.getLog(SyncProposalSupportersBetweenPhasesTask.class);
	private static Boolean working = new Boolean(false);

    public void receive(Message message) throws MessageListenerException {
        if (working) return;
        synchronized(working) {
            try {
                syncSupporters();
            } catch (SystemException e) {
                _log.error(e);
                e.printStackTrace();
            } catch (PortalException e) {
                _log.error(e);
            }
            
        }
        
    }

    
    public String syncSupporters() throws SystemException, PortalException {
        _log.info("Syncing supporters");
        
        Map<String, Set<Long>> plansToMap = new HashMap<String, Set<Long>>();
        
        _log.info("fetching plans and their fans");
        for (PlanItem plan: PlanItemLocalServiceUtil.getPlans()) {
            Contest contest = PlanItemLocalServiceUtil.getContest(plan);
            
            String key = contest.getContestPK() + "_" + PlanItemLocalServiceUtil.getName(plan).trim(); 
            
            Set<Long> plans = plansToMap.get(key);
            if (plans == null) {
                plans = new HashSet<Long>();
                plansToMap.put(key, plans);
            }
            plans.add(plan.getPlanId());
        }
        _log.info("merging fans");
        for (String key: plansToMap.keySet()) {
            Set<Long> planIds = plansToMap.get(key);
            if (planIds.size() > 1) {
                Set<Long> fanIds = new HashSet<Long>();
                
                for (Long planId: planIds) {
                    PlanItem plan = PlanItemLocalServiceUtil.getPlan(planId);
                    
                    for (PlanFan fan: PlanItemLocalServiceUtil.getFans(plan)) {
                        fanIds.add(fan.getUserId());
                    }
                }
                for (Long fanId: fanIds) {
                    for (Long planId: planIds) {
                        PlanItem plan = PlanItemLocalServiceUtil.getPlan(planId);
                        PlanItemLocalServiceUtil.addFan(plan, fanId);
                    }
                }
            //System.out.println(key + "\t" + plansToMap.get(key));
            }
        
        }
        
        
        
        return null;
    }
    
}
