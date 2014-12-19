package com.ext.portlet.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ext.portlet.model.PlanItemGroup;
import com.ext.portlet.service.base.PlanItemGroupLocalServiceBaseImpl;
import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the plan item group local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.ext.portlet.service.PlanItemGroupLocalService} interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 * 
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PlanItemGroupLocalServiceBaseImpl
 * @see com.ext.portlet.service.PlanItemGroupLocalServiceUtil
 */
public class PlanItemGroupLocalServiceImpl extends PlanItemGroupLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     * 
     * Never reference this interface directly. Always use {@link
     * com.ext.portlet.service.PlanItemGroupLocalServiceUtil} to access the plan
     * item group local service.
     */

    /**
     * Adds given plans to a group, creates one if none exists.
     * 
     * @param fromPlanId
     *            source plan id
     * @param toPlanId
     *            destination plan id
     * @return created PlanItemPhaseMap
     * @throws SystemException
     * @throws NoSuchModelException
     */
    public void addToGroup(Long fromPlanId, Long toPlanId) throws NoSuchModelException, SystemException {
        if (fromPlanId > toPlanId) {
            Long tmp = fromPlanId;
            fromPlanId = toPlanId;
            toPlanId = tmp;
        }

        // check if there is a mapping for provided id's
        PlanItemGroup fromPlanGroup = null;
        try {
            fromPlanGroup = getPlanItemGroup(fromPlanId);
        } catch (PortalException pige) {
            //
        }
        PlanItemGroup toPlanGroup = null;
        try {
            toPlanGroup = getPlanItemGroup(toPlanId);
        } catch (PortalException pige) {
            //
        }

        if (fromPlanGroup == null && toPlanGroup == null) {
            // create new group for plan ids with "parent id", use lower planId
            // as a groupId
            fromPlanGroup = createPlanItemGroup(fromPlanId);
            fromPlanGroup.setGroupId(fromPlanId);
            addPlanItemGroup(fromPlanGroup);
            if (fromPlanId != toPlanId) {
                toPlanGroup = createPlanItemGroup(toPlanId);
                toPlanGroup.setGroupId(fromPlanId);
                addPlanItemGroup(toPlanGroup);
            }
        } else if (fromPlanGroup == null) {
            // toPlanId already belongs to a group, take group id from
            // toPlanGroup 
            fromPlanGroup = createPlanItemGroup(fromPlanId);
            fromPlanGroup.setGroupId(toPlanGroup.getGroupId());
            addPlanItemGroup(fromPlanGroup);
        } else if (toPlanGroup == null ){
            // fromPlanId already belongs to a group, take group id from
            // toPlanGroup
            toPlanGroup = createPlanItemGroup(toPlanId);
            toPlanGroup.setGroupId(fromPlanGroup.getGroupId());
            addPlanItemGroup(toPlanGroup);
        }
    }

    /**
     * Returns list of planIds that belong to the same group as given planId.
     * 
     * @param planId
     *            plan id for which other group members should be returned
     * @return list of plan ids that belong to the same group (can be a single
     *         element array if plan doesn't belong to a group)
     * @throws NoSuchModelException
     * @throws SystemException
     */
    public List<Long> getPlansInGroup(Long planId) throws NoSuchModelException, SystemException {
        // check if there is a group for given planid
        PlanItemGroup group = null;
        try {
            group = planItemGroupPersistence.findByPrimaryKey(planId);
        }
        catch (PortalException e) {
            // ignore
        }
        if (group == null) {
            // plan doesn't belong to any group, return list with only given
            // plan id
            return Arrays.asList(new Long[] { planId });
        }

        // plan does belong to a group, fetch all plan ids in it
        List<Long> planIds = new ArrayList<>();
        for (PlanItemGroup groupElement : planItemGroupPersistence.findByGroupId(group.getGroupId())) {
            planIds.add(groupElement.getPlanId());
        }

        return planIds;
    }
}
