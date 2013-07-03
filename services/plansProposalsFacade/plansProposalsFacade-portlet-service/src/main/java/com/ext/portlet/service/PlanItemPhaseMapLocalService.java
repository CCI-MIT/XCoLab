package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the plan item phase map local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemPhaseMapLocalServiceUtil
 * @see com.ext.portlet.service.base.PlanItemPhaseMapLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanItemPhaseMapLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface PlanItemPhaseMapLocalService extends PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanItemPhaseMapLocalServiceUtil} to access the plan item phase map local service. Add custom service methods to {@link com.ext.portlet.service.impl.PlanItemPhaseMapLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the plan item phase map to the database. Also notifies the appropriate model listeners.
    *
    * @param planItemPhaseMap the plan item phase map
    * @return the plan item phase map that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemPhaseMap addPlanItemPhaseMap(
        com.ext.portlet.model.PlanItemPhaseMap planItemPhaseMap)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new plan item phase map with the primary key. Does not add the plan item phase map to the database.
    *
    * @param id the primary key for the new plan item phase map
    * @return the new plan item phase map
    */
    public com.ext.portlet.model.PlanItemPhaseMap createPlanItemPhaseMap(
        long id);

    /**
    * Deletes the plan item phase map with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan item phase map
    * @throws PortalException if a plan item phase map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanItemPhaseMap(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the plan item phase map from the database. Also notifies the appropriate model listeners.
    *
    * @param planItemPhaseMap the plan item phase map
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanItemPhaseMap(
        com.ext.portlet.model.PlanItemPhaseMap planItemPhaseMap)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanItemPhaseMap fetchPlanItemPhaseMap(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan item phase map with the primary key.
    *
    * @param id the primary key of the plan item phase map
    * @return the plan item phase map
    * @throws PortalException if a plan item phase map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanItemPhaseMap getPlanItemPhaseMap(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan item phase maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan item phase maps
    * @param end the upper bound of the range of plan item phase maps (not inclusive)
    * @return the range of plan item phase maps
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanItemPhaseMap> getPlanItemPhaseMaps(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan item phase maps.
    *
    * @return the number of plan item phase maps
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getPlanItemPhaseMapsCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the plan item phase map in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planItemPhaseMap the plan item phase map
    * @return the plan item phase map that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemPhaseMap updatePlanItemPhaseMap(
        com.ext.portlet.model.PlanItemPhaseMap planItemPhaseMap)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the plan item phase map in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planItemPhaseMap the plan item phase map
    * @param merge whether to merge the plan item phase map with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan item phase map that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemPhaseMap updatePlanItemPhaseMap(
        com.ext.portlet.model.PlanItemPhaseMap planItemPhaseMap, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);
}
