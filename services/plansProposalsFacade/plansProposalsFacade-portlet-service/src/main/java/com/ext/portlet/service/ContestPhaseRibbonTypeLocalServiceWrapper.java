package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestPhaseRibbonTypeLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestPhaseRibbonTypeLocalService
 * @generated
 */
public class ContestPhaseRibbonTypeLocalServiceWrapper
    implements ContestPhaseRibbonTypeLocalService,
        ServiceWrapper<ContestPhaseRibbonTypeLocalService> {
    private ContestPhaseRibbonTypeLocalService _contestPhaseRibbonTypeLocalService;

    public ContestPhaseRibbonTypeLocalServiceWrapper(
        ContestPhaseRibbonTypeLocalService contestPhaseRibbonTypeLocalService) {
        _contestPhaseRibbonTypeLocalService = contestPhaseRibbonTypeLocalService;
    }

    /**
    * Adds the contest phase ribbon type to the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseRibbonType the contest phase ribbon type
    * @return the contest phase ribbon type that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseRibbonType addContestPhaseRibbonType(
        com.ext.portlet.model.ContestPhaseRibbonType contestPhaseRibbonType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseRibbonTypeLocalService.addContestPhaseRibbonType(contestPhaseRibbonType);
    }

    /**
    * Creates a new contest phase ribbon type with the primary key. Does not add the contest phase ribbon type to the database.
    *
    * @param id the primary key for the new contest phase ribbon type
    * @return the new contest phase ribbon type
    */
    public com.ext.portlet.model.ContestPhaseRibbonType createContestPhaseRibbonType(
        long id) {
        return _contestPhaseRibbonTypeLocalService.createContestPhaseRibbonType(id);
    }

    /**
    * Deletes the contest phase ribbon type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest phase ribbon type
    * @throws PortalException if a contest phase ribbon type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteContestPhaseRibbonType(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _contestPhaseRibbonTypeLocalService.deleteContestPhaseRibbonType(id);
    }

    /**
    * Deletes the contest phase ribbon type from the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseRibbonType the contest phase ribbon type
    * @throws SystemException if a system exception occurred
    */
    public void deleteContestPhaseRibbonType(
        com.ext.portlet.model.ContestPhaseRibbonType contestPhaseRibbonType)
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestPhaseRibbonTypeLocalService.deleteContestPhaseRibbonType(contestPhaseRibbonType);
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
        return _contestPhaseRibbonTypeLocalService.dynamicQuery(dynamicQuery);
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
        return _contestPhaseRibbonTypeLocalService.dynamicQuery(dynamicQuery,
            start, end);
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
        return _contestPhaseRibbonTypeLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
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
        return _contestPhaseRibbonTypeLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.ContestPhaseRibbonType fetchContestPhaseRibbonType(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseRibbonTypeLocalService.fetchContestPhaseRibbonType(id);
    }

    /**
    * Returns the contest phase ribbon type with the primary key.
    *
    * @param id the primary key of the contest phase ribbon type
    * @return the contest phase ribbon type
    * @throws PortalException if a contest phase ribbon type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseRibbonType getContestPhaseRibbonType(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseRibbonTypeLocalService.getContestPhaseRibbonType(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseRibbonTypeLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contest phase ribbon types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest phase ribbon types
    * @param end the upper bound of the range of contest phase ribbon types (not inclusive)
    * @return the range of contest phase ribbon types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhaseRibbonType> getContestPhaseRibbonTypes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseRibbonTypeLocalService.getContestPhaseRibbonTypes(start,
            end);
    }

    /**
    * Returns the number of contest phase ribbon types.
    *
    * @return the number of contest phase ribbon types
    * @throws SystemException if a system exception occurred
    */
    public int getContestPhaseRibbonTypesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseRibbonTypeLocalService.getContestPhaseRibbonTypesCount();
    }

    /**
    * Updates the contest phase ribbon type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseRibbonType the contest phase ribbon type
    * @return the contest phase ribbon type that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseRibbonType updateContestPhaseRibbonType(
        com.ext.portlet.model.ContestPhaseRibbonType contestPhaseRibbonType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseRibbonTypeLocalService.updateContestPhaseRibbonType(contestPhaseRibbonType);
    }

    /**
    * Updates the contest phase ribbon type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseRibbonType the contest phase ribbon type
    * @param merge whether to merge the contest phase ribbon type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the contest phase ribbon type that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseRibbonType updateContestPhaseRibbonType(
        com.ext.portlet.model.ContestPhaseRibbonType contestPhaseRibbonType,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseRibbonTypeLocalService.updateContestPhaseRibbonType(contestPhaseRibbonType,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _contestPhaseRibbonTypeLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestPhaseRibbonTypeLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ContestPhaseRibbonTypeLocalService getWrappedContestPhaseRibbonTypeLocalService() {
        return _contestPhaseRibbonTypeLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedContestPhaseRibbonTypeLocalService(
        ContestPhaseRibbonTypeLocalService contestPhaseRibbonTypeLocalService) {
        _contestPhaseRibbonTypeLocalService = contestPhaseRibbonTypeLocalService;
    }

    public ContestPhaseRibbonTypeLocalService getWrappedService() {
        return _contestPhaseRibbonTypeLocalService;
    }

    public void setWrappedService(
        ContestPhaseRibbonTypeLocalService contestPhaseRibbonTypeLocalService) {
        _contestPhaseRibbonTypeLocalService = contestPhaseRibbonTypeLocalService;
    }
}
