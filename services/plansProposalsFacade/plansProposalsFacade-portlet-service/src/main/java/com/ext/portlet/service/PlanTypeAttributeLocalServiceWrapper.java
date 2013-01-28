package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanTypeAttributeLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanTypeAttributeLocalService
 * @generated
 */
public class PlanTypeAttributeLocalServiceWrapper
    implements PlanTypeAttributeLocalService,
        ServiceWrapper<PlanTypeAttributeLocalService> {
    private PlanTypeAttributeLocalService _planTypeAttributeLocalService;

    public PlanTypeAttributeLocalServiceWrapper(
        PlanTypeAttributeLocalService planTypeAttributeLocalService) {
        _planTypeAttributeLocalService = planTypeAttributeLocalService;
    }

    /**
    * Adds the plan type attribute to the database. Also notifies the appropriate model listeners.
    *
    * @param planTypeAttribute the plan type attribute
    * @return the plan type attribute that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTypeAttribute addPlanTypeAttribute(
        com.ext.portlet.model.PlanTypeAttribute planTypeAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeAttributeLocalService.addPlanTypeAttribute(planTypeAttribute);
    }

    /**
    * Creates a new plan type attribute with the primary key. Does not add the plan type attribute to the database.
    *
    * @param planTypeAttributeId the primary key for the new plan type attribute
    * @return the new plan type attribute
    */
    public com.ext.portlet.model.PlanTypeAttribute createPlanTypeAttribute(
        long planTypeAttributeId) {
        return _planTypeAttributeLocalService.createPlanTypeAttribute(planTypeAttributeId);
    }

    /**
    * Deletes the plan type attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planTypeAttributeId the primary key of the plan type attribute
    * @throws PortalException if a plan type attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanTypeAttribute(long planTypeAttributeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planTypeAttributeLocalService.deletePlanTypeAttribute(planTypeAttributeId);
    }

    /**
    * Deletes the plan type attribute from the database. Also notifies the appropriate model listeners.
    *
    * @param planTypeAttribute the plan type attribute
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanTypeAttribute(
        com.ext.portlet.model.PlanTypeAttribute planTypeAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTypeAttributeLocalService.deletePlanTypeAttribute(planTypeAttribute);
    }

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
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeAttributeLocalService.dynamicQuery(dynamicQuery);
    }

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
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeAttributeLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

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
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeAttributeLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeAttributeLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.PlanTypeAttribute fetchPlanTypeAttribute(
        long planTypeAttributeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeAttributeLocalService.fetchPlanTypeAttribute(planTypeAttributeId);
    }

    /**
    * Returns the plan type attribute with the primary key.
    *
    * @param planTypeAttributeId the primary key of the plan type attribute
    * @return the plan type attribute
    * @throws PortalException if a plan type attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTypeAttribute getPlanTypeAttribute(
        long planTypeAttributeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTypeAttributeLocalService.getPlanTypeAttribute(planTypeAttributeId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTypeAttributeLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan type attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan type attributes
    * @param end the upper bound of the range of plan type attributes (not inclusive)
    * @return the range of plan type attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanTypeAttribute> getPlanTypeAttributes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeAttributeLocalService.getPlanTypeAttributes(start, end);
    }

    /**
    * Returns the number of plan type attributes.
    *
    * @return the number of plan type attributes
    * @throws SystemException if a system exception occurred
    */
    public int getPlanTypeAttributesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeAttributeLocalService.getPlanTypeAttributesCount();
    }

    /**
    * Updates the plan type attribute in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planTypeAttribute the plan type attribute
    * @return the plan type attribute that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTypeAttribute updatePlanTypeAttribute(
        com.ext.portlet.model.PlanTypeAttribute planTypeAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeAttributeLocalService.updatePlanTypeAttribute(planTypeAttribute);
    }

    /**
    * Updates the plan type attribute in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planTypeAttribute the plan type attribute
    * @param merge whether to merge the plan type attribute with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan type attribute that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTypeAttribute updatePlanTypeAttribute(
        com.ext.portlet.model.PlanTypeAttribute planTypeAttribute, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeAttributeLocalService.updatePlanTypeAttribute(planTypeAttribute,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planTypeAttributeLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planTypeAttributeLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanTypeAttributeLocalService getWrappedPlanTypeAttributeLocalService() {
        return _planTypeAttributeLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanTypeAttributeLocalService(
        PlanTypeAttributeLocalService planTypeAttributeLocalService) {
        _planTypeAttributeLocalService = planTypeAttributeLocalService;
    }

    public PlanTypeAttributeLocalService getWrappedService() {
        return _planTypeAttributeLocalService;
    }

    public void setWrappedService(
        PlanTypeAttributeLocalService planTypeAttributeLocalService) {
        _planTypeAttributeLocalService = planTypeAttributeLocalService;
    }
}
