package com.ext.portlet.service.impl;

import java.util.Date;

import com.ext.portlet.NoSuchPlanItemException;
import com.ext.portlet.NoSuchPlanTeamHistoryException;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanTeamHistory;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.PlanTeamHistoryLocalServiceUtil;
import com.ext.portlet.service.base.PlanTeamHistoryLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the plan team history local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PlanTeamHistoryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PlanTeamHistoryLocalServiceBaseImpl
 * @see com.ext.portlet.service.PlanTeamHistoryLocalServiceUtil
 */
public class PlanTeamHistoryLocalServiceImpl
    extends PlanTeamHistoryLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PlanTeamHistoryLocalServiceUtil} to access the plan team history local service.
     */

    public PlanTeamHistory newHistoryItem(Long planId, Long userId, String action, Long updateAuthorId) throws SystemException {
        return newHistoryItem(planId, userId, action, null, updateAuthorId);
    }
        
    public PlanTeamHistory newHistoryItem(Long planId, Long userId, String action, String payload, Long updateAuthorId) throws SystemException {
        long id = CounterLocalServiceUtil.increment(PlanTeamHistory.class.getName());
        
        PlanTeamHistory planTeamHistory = createPlanTeamHistory(id);
        
        planTeamHistory.setPlanId(planId);
        planTeamHistory.setUserId(userId);
        planTeamHistory.setAction(action);
        planTeamHistory.setPayload(payload);
        planTeamHistory.setUpdateAuthorId(updateAuthorId);
        planTeamHistory.setCreated(new Date());
        
        store(planTeamHistory);
        
        return planTeamHistory;
    }
    
    public PlanTeamHistory getLastUserActionInPlan(Long planId, Long userId) throws NoSuchPlanTeamHistoryException, SystemException {
        return planTeamHistoryPersistence.findByLastUserActionInPlan(planId, userId);
    }
    
    
    public void store(PlanTeamHistory pth) throws SystemException {
        if (pth.isNew()) {
            PlanTeamHistoryLocalServiceUtil.addPlanTeamHistory(pth);
        }
        else {
            PlanTeamHistoryLocalServiceUtil.updatePlanTeamHistory(pth);
        }
    }
    
    public User getUser(PlanTeamHistory pth) throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(pth.getUserId());
    }
    
    public PlanItem getPlan(PlanTeamHistory pth) throws NoSuchPlanItemException, SystemException {
        return PlanItemLocalServiceUtil.getPlan(pth.getPlanId());
    }
}
