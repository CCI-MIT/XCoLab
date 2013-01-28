package com.ext.portlet.service.impl;

import java.util.List;

import com.ext.portlet.model.PlanPositionItem;
import com.ext.portlet.model.PlanPositions;
import com.ext.portlet.service.base.PlanPositionItemLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the plan position item local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PlanPositionItemLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PlanPositionItemLocalServiceBaseImpl
 * @see com.ext.portlet.service.PlanPositionItemLocalServiceUtil
 */
public class PlanPositionItemLocalServiceImpl
    extends PlanPositionItemLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PlanPositionItemLocalServiceUtil} to access the plan position item local service.
     */

    public List<PlanPositionItem> getAllIdsForPlanPositions(PlanPositions planPositions) throws SystemException {
        return planPositionItemPersistence.findByAllByPlanPositionsId(planPositions.getId());
    }
}
