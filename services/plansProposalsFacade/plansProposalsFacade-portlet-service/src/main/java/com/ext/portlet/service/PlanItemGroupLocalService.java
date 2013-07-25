package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the plan item group local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemGroupLocalServiceUtil
 * @see com.ext.portlet.service.base.PlanItemGroupLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanItemGroupLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface PlanItemGroupLocalService extends PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanItemGroupLocalServiceUtil} to access the plan item group local service. Add custom service methods to {@link com.ext.portlet.service.impl.PlanItemGroupLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the plan item group to the database. Also notifies the appropriate model listeners.
    *
    * @param planItemGroup the plan item group
    * @return the plan item group that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemGroup addPlanItemGroup(
        com.ext.portlet.model.PlanItemGroup planItemGroup)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new plan item group with the primary key. Does not add the plan item group to the database.
    *
    * @param planId the primary key for the new plan item group
    * @return the new plan item group
    */
    public com.ext.portlet.model.PlanItemGroup createPlanItemGroup(long planId);

    /**
    * Deletes the plan item group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planId the primary key of the plan item group
    * @throws PortalException if a plan item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanItemGroup(long planId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the plan item group from the database. Also notifies the appropriate model listeners.
    *
    * @param planItemGroup the plan item group
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanItemGroup(
        com.ext.portlet.model.PlanItemGroup planItemGroup)
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
    public com.ext.portlet.model.PlanItemGroup fetchPlanItemGroup(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan item group with the primary key.
    *
    * @param planId the primary key of the plan item group
    * @return the plan item group
    * @throws PortalException if a plan item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanItemGroup getPlanItemGroup(long planId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan item groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan item groups
    * @param end the upper bound of the range of plan item groups (not inclusive)
    * @return the range of plan item groups
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanItemGroup> getPlanItemGroups(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan item groups.
    *
    * @return the number of plan item groups
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getPlanItemGroupsCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the plan item group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planItemGroup the plan item group
    * @return the plan item group that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemGroup updatePlanItemGroup(
        com.ext.portlet.model.PlanItemGroup planItemGroup)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the plan item group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planItemGroup the plan item group
    * @param merge whether to merge the plan item group with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan item group that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemGroup updatePlanItemGroup(
        com.ext.portlet.model.PlanItemGroup planItemGroup, boolean merge)
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

    /**
    * Adds given plans to a group, creates one if none exists.
    *
    * @param fromPlanId
    source plan id
    * @param toPlanId
    destination plan id
    * @return created PlanItemPhaseMap
    * @throws SystemException
    * @throws NoSuchModelException
    */
    public void addToGroup(java.lang.Long fromPlanId, java.lang.Long toPlanId)
        throws com.liferay.portal.NoSuchModelException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns list of planIds that belong to the same group as given planId.
    *
    * @param planId
    plan id for which other group members should be returned
    * @return list of plan ids that belong to the same group (can be a single
    element array if plan doesn't belong to a group)
    * @throws NoSuchModelException
    * @throws SystemException
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<java.lang.Long> getPlansInGroup(java.lang.Long planId)
        throws com.liferay.portal.NoSuchModelException,
            com.liferay.portal.kernel.exception.SystemException;
}
