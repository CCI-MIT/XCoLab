package com.ext.portlet.service.impl;

import com.ext.portlet.model.PlanItemPhaseMap;
import com.ext.portlet.service.base.PlanItemPhaseMapLocalServiceBaseImpl;
import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the plan item phase map local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PlanItemPhaseMapLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PlanItemPhaseMapLocalServiceBaseImpl
 * @see com.ext.portlet.service.PlanItemPhaseMapLocalServiceUtil
 */
public class PlanItemPhaseMapLocalServiceImpl
    extends PlanItemPhaseMapLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PlanItemPhaseMapLocalServiceUtil} to access the plan item phase map local service.
     */
    
    /**
     *  add a mapping from plan id to plan id
     * @param fromPlanId source plan id
     * @param toPlanId destination plan id
     * @return created PlanItemPhaseMap
     * @throws SystemException 
     * @throws NoSuchModelException 
     */
    
}
