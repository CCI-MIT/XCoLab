package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the plan section local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionLocalServiceUtil
 * @see com.ext.portlet.service.base.PlanSectionLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanSectionLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface PlanSectionLocalService extends PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanSectionLocalServiceUtil} to access the plan section local service. Add custom service methods to {@link com.ext.portlet.service.impl.PlanSectionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the plan section to the database. Also notifies the appropriate model listeners.
    *
    * @param planSection the plan section
    * @return the plan section that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanSection addPlanSection(
        com.ext.portlet.model.PlanSection planSection)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new plan section with the primary key. Does not add the plan section to the database.
    *
    * @param id the primary key for the new plan section
    * @return the new plan section
    */
    public com.ext.portlet.model.PlanSection createPlanSection(long id);

    /**
    * Deletes the plan section with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan section
    * @throws PortalException if a plan section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanSection(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the plan section from the database. Also notifies the appropriate model listeners.
    *
    * @param planSection the plan section
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanSection(com.ext.portlet.model.PlanSection planSection)
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
    public com.ext.portlet.model.PlanSection fetchPlanSection(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan section with the primary key.
    *
    * @param id the primary key of the plan section
    * @return the plan section
    * @throws PortalException if a plan section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanSection getPlanSection(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan sections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan sections
    * @param end the upper bound of the range of plan sections (not inclusive)
    * @return the range of plan sections
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanSection> getPlanSections(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan sections.
    *
    * @return the number of plan sections
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getPlanSectionsCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the plan section in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planSection the plan section
    * @return the plan section that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanSection updatePlanSection(
        com.ext.portlet.model.PlanSection planSection)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the plan section in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planSection the plan section
    * @param merge whether to merge the plan section with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan section that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanSection updatePlanSection(
        com.ext.portlet.model.PlanSection planSection, boolean merge)
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

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanSection getCurrentForPlanSectionDef(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanSection getCurrentForPlanSectionDef(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def, boolean createOnEmpty)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanSection getForPlanSectionDef(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanSection getForPlanSectionDef(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def, boolean current,
        boolean createOnEmpty)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanSection createForPlanFrom(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSection from, boolean store)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanSection createNewVersionForPlanSectionDefinition(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanSection createNewVersionForPlanSectionDefinition(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def, boolean store)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanSection> getAllForPlanDefinition(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void store(com.ext.portlet.model.PlanSection ps)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanSectionDefinition getDefinition(
        com.ext.portlet.model.PlanSection ps)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void addPlanReference(com.ext.portlet.model.PlanSection ps,
        java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanItem> getReferencedPlans(
        com.ext.portlet.model.PlanSection ps)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException;
}
