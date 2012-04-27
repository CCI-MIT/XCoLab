package org.xcolab.services.proposalsService.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FooLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FooLocalService
 * @generated
 */
public class FooLocalServiceWrapper implements FooLocalService,
    ServiceWrapper<FooLocalService> {
    private FooLocalService _fooLocalService;

    public FooLocalServiceWrapper(FooLocalService fooLocalService) {
        _fooLocalService = fooLocalService;
    }

    /**
    * Adds the foo to the database. Also notifies the appropriate model listeners.
    *
    * @param foo the foo
    * @return the foo that was added
    * @throws SystemException if a system exception occurred
    */
    public org.xcolab.services.proposalsService.model.Foo addFoo(
        org.xcolab.services.proposalsService.model.Foo foo)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _fooLocalService.addFoo(foo);
    }

    /**
    * Creates a new foo with the primary key. Does not add the foo to the database.
    *
    * @param fooId the primary key for the new foo
    * @return the new foo
    */
    public org.xcolab.services.proposalsService.model.Foo createFoo(long fooId) {
        return _fooLocalService.createFoo(fooId);
    }

    /**
    * Deletes the foo with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param fooId the primary key of the foo
    * @throws PortalException if a foo with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteFoo(long fooId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _fooLocalService.deleteFoo(fooId);
    }

    /**
    * Deletes the foo from the database. Also notifies the appropriate model listeners.
    *
    * @param foo the foo
    * @throws SystemException if a system exception occurred
    */
    public void deleteFoo(org.xcolab.services.proposalsService.model.Foo foo)
        throws com.liferay.portal.kernel.exception.SystemException {
        _fooLocalService.deleteFoo(foo);
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
        return _fooLocalService.dynamicQuery(dynamicQuery);
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
        return _fooLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _fooLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
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
        return _fooLocalService.dynamicQueryCount(dynamicQuery);
    }

    public org.xcolab.services.proposalsService.model.Foo fetchFoo(long fooId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _fooLocalService.fetchFoo(fooId);
    }

    /**
    * Returns the foo with the primary key.
    *
    * @param fooId the primary key of the foo
    * @return the foo
    * @throws PortalException if a foo with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.xcolab.services.proposalsService.model.Foo getFoo(long fooId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _fooLocalService.getFoo(fooId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _fooLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns the foo with the UUID in the group.
    *
    * @param uuid the UUID of foo
    * @param groupId the group id of the foo
    * @return the foo
    * @throws PortalException if a foo with the UUID in the group could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.xcolab.services.proposalsService.model.Foo getFooByUuidAndGroupId(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _fooLocalService.getFooByUuidAndGroupId(uuid, groupId);
    }

    /**
    * Returns a range of all the foos.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of foos
    * @param end the upper bound of the range of foos (not inclusive)
    * @return the range of foos
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.xcolab.services.proposalsService.model.Foo> getFoos(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _fooLocalService.getFoos(start, end);
    }

    /**
    * Returns the number of foos.
    *
    * @return the number of foos
    * @throws SystemException if a system exception occurred
    */
    public int getFoosCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _fooLocalService.getFoosCount();
    }

    /**
    * Updates the foo in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param foo the foo
    * @return the foo that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.xcolab.services.proposalsService.model.Foo updateFoo(
        org.xcolab.services.proposalsService.model.Foo foo)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _fooLocalService.updateFoo(foo);
    }

    /**
    * Updates the foo in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param foo the foo
    * @param merge whether to merge the foo with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the foo that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.xcolab.services.proposalsService.model.Foo updateFoo(
        org.xcolab.services.proposalsService.model.Foo foo, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _fooLocalService.updateFoo(foo, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _fooLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _fooLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public FooLocalService getWrappedFooLocalService() {
        return _fooLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedFooLocalService(FooLocalService fooLocalService) {
        _fooLocalService = fooLocalService;
    }

    public FooLocalService getWrappedService() {
        return _fooLocalService;
    }

    public void setWrappedService(FooLocalService fooLocalService) {
        _fooLocalService = fooLocalService;
    }
}
