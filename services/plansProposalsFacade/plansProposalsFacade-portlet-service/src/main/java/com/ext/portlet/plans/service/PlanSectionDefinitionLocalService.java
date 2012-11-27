package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the plan section definition local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionDefinitionLocalServiceUtil
 * @see com.ext.portlet.plans.service.base.PlanSectionDefinitionLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanSectionDefinitionLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface PlanSectionDefinitionLocalService
    extends PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanSectionDefinitionLocalServiceUtil} to access the plan section definition local service. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanSectionDefinitionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the plan section definition to the database. Also notifies the appropriate model listeners.
    *
    * @param planSectionDefinition the plan section definition
    * @return the plan section definition that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSectionDefinition addPlanSectionDefinition(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new plan section definition with the primary key. Does not add the plan section definition to the database.
    *
    * @param id the primary key for the new plan section definition
    * @return the new plan section definition
    */
    public com.ext.portlet.plans.model.PlanSectionDefinition createPlanSectionDefinition(
        java.lang.Long id);

    /**
    * Deletes the plan section definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan section definition
    * @throws PortalException if a plan section definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanSectionDefinition(java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the plan section definition from the database. Also notifies the appropriate model listeners.
    *
    * @param planSectionDefinition the plan section definition
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanSectionDefinition(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition)
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
    public com.ext.portlet.plans.model.PlanSectionDefinition fetchPlanSectionDefinition(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan section definition with the primary key.
    *
    * @param id the primary key of the plan section definition
    * @return the plan section definition
    * @throws PortalException if a plan section definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.plans.model.PlanSectionDefinition getPlanSectionDefinition(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan section definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan section definitions
    * @param end the upper bound of the range of plan section definitions (not inclusive)
    * @return the range of plan section definitions
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> getPlanSectionDefinitions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan section definitions.
    *
    * @return the number of plan section definitions
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getPlanSectionDefinitionsCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the plan section definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planSectionDefinition the plan section definition
    * @return the plan section definition that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSectionDefinition updatePlanSectionDefinition(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the plan section definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planSectionDefinition the plan section definition
    * @param merge whether to merge the plan section definition with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan section definition that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSectionDefinition updatePlanSectionDefinition(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition,
        boolean merge)
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
