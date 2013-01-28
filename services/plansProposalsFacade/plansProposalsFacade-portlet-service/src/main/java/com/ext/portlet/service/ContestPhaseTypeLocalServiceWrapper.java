package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestPhaseTypeLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestPhaseTypeLocalService
 * @generated
 */
public class ContestPhaseTypeLocalServiceWrapper
    implements ContestPhaseTypeLocalService,
        ServiceWrapper<ContestPhaseTypeLocalService> {
    private ContestPhaseTypeLocalService _contestPhaseTypeLocalService;

    public ContestPhaseTypeLocalServiceWrapper(
        ContestPhaseTypeLocalService contestPhaseTypeLocalService) {
        _contestPhaseTypeLocalService = contestPhaseTypeLocalService;
    }

    /**
    * Adds the contest phase type to the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseType the contest phase type
    * @return the contest phase type that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseType addContestPhaseType(
        com.ext.portlet.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseTypeLocalService.addContestPhaseType(contestPhaseType);
    }

    /**
    * Creates a new contest phase type with the primary key. Does not add the contest phase type to the database.
    *
    * @param id the primary key for the new contest phase type
    * @return the new contest phase type
    */
    public com.ext.portlet.model.ContestPhaseType createContestPhaseType(
        long id) {
        return _contestPhaseTypeLocalService.createContestPhaseType(id);
    }

    /**
    * Deletes the contest phase type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest phase type
    * @throws PortalException if a contest phase type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteContestPhaseType(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _contestPhaseTypeLocalService.deleteContestPhaseType(id);
    }

    /**
    * Deletes the contest phase type from the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseType the contest phase type
    * @throws SystemException if a system exception occurred
    */
    public void deleteContestPhaseType(
        com.ext.portlet.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestPhaseTypeLocalService.deleteContestPhaseType(contestPhaseType);
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
        return _contestPhaseTypeLocalService.dynamicQuery(dynamicQuery);
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
        return _contestPhaseTypeLocalService.dynamicQuery(dynamicQuery, start,
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
        return _contestPhaseTypeLocalService.dynamicQuery(dynamicQuery, start,
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
        return _contestPhaseTypeLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.ContestPhaseType fetchContestPhaseType(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseTypeLocalService.fetchContestPhaseType(id);
    }

    /**
    * Returns the contest phase type with the primary key.
    *
    * @param id the primary key of the contest phase type
    * @return the contest phase type
    * @throws PortalException if a contest phase type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseType getContestPhaseType(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseTypeLocalService.getContestPhaseType(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseTypeLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contest phase types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest phase types
    * @param end the upper bound of the range of contest phase types (not inclusive)
    * @return the range of contest phase types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhaseType> getContestPhaseTypes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseTypeLocalService.getContestPhaseTypes(start, end);
    }

    /**
    * Returns the number of contest phase types.
    *
    * @return the number of contest phase types
    * @throws SystemException if a system exception occurred
    */
    public int getContestPhaseTypesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseTypeLocalService.getContestPhaseTypesCount();
    }

    /**
    * Updates the contest phase type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseType the contest phase type
    * @return the contest phase type that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseType updateContestPhaseType(
        com.ext.portlet.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseTypeLocalService.updateContestPhaseType(contestPhaseType);
    }

    /**
    * Updates the contest phase type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseType the contest phase type
    * @param merge whether to merge the contest phase type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the contest phase type that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseType updateContestPhaseType(
        com.ext.portlet.model.ContestPhaseType contestPhaseType, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseTypeLocalService.updateContestPhaseType(contestPhaseType,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _contestPhaseTypeLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestPhaseTypeLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ContestPhaseTypeLocalService getWrappedContestPhaseTypeLocalService() {
        return _contestPhaseTypeLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedContestPhaseTypeLocalService(
        ContestPhaseTypeLocalService contestPhaseTypeLocalService) {
        _contestPhaseTypeLocalService = contestPhaseTypeLocalService;
    }

    public ContestPhaseTypeLocalService getWrappedService() {
        return _contestPhaseTypeLocalService;
    }

    public void setWrappedService(
        ContestPhaseTypeLocalService contestPhaseTypeLocalService) {
        _contestPhaseTypeLocalService = contestPhaseTypeLocalService;
    }
}
