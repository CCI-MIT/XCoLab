package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DiscussionMessageFlagLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessageFlagLocalService
 * @generated
 */
public class DiscussionMessageFlagLocalServiceWrapper
    implements DiscussionMessageFlagLocalService,
        ServiceWrapper<DiscussionMessageFlagLocalService> {
    private DiscussionMessageFlagLocalService _discussionMessageFlagLocalService;

    public DiscussionMessageFlagLocalServiceWrapper(
        DiscussionMessageFlagLocalService discussionMessageFlagLocalService) {
        _discussionMessageFlagLocalService = discussionMessageFlagLocalService;
    }

    /**
    * Adds the discussion message flag to the database. Also notifies the appropriate model listeners.
    *
    * @param discussionMessageFlag the discussion message flag
    * @return the discussion message flag that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionMessageFlag addDiscussionMessageFlag(
        com.ext.portlet.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlagLocalService.addDiscussionMessageFlag(discussionMessageFlag);
    }

    /**
    * Creates a new discussion message flag with the primary key. Does not add the discussion message flag to the database.
    *
    * @param pk the primary key for the new discussion message flag
    * @return the new discussion message flag
    */
    @Override
    public com.ext.portlet.model.DiscussionMessageFlag createDiscussionMessageFlag(
        long pk) {
        return _discussionMessageFlagLocalService.createDiscussionMessageFlag(pk);
    }

    /**
    * Deletes the discussion message flag with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the discussion message flag
    * @return the discussion message flag that was removed
    * @throws PortalException if a discussion message flag with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionMessageFlag deleteDiscussionMessageFlag(
        long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlagLocalService.deleteDiscussionMessageFlag(pk);
    }

    /**
    * Deletes the discussion message flag from the database. Also notifies the appropriate model listeners.
    *
    * @param discussionMessageFlag the discussion message flag
    * @return the discussion message flag that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionMessageFlag deleteDiscussionMessageFlag(
        com.ext.portlet.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlagLocalService.deleteDiscussionMessageFlag(discussionMessageFlag);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _discussionMessageFlagLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlagLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlagLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlagLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlagLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlagLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.DiscussionMessageFlag fetchDiscussionMessageFlag(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlagLocalService.fetchDiscussionMessageFlag(pk);
    }

    /**
    * Returns the discussion message flag with the primary key.
    *
    * @param pk the primary key of the discussion message flag
    * @return the discussion message flag
    * @throws PortalException if a discussion message flag with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionMessageFlag getDiscussionMessageFlag(
        long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlagLocalService.getDiscussionMessageFlag(pk);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlagLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the discussion message flags.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of discussion message flags
    * @param end the upper bound of the range of discussion message flags (not inclusive)
    * @return the range of discussion message flags
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.DiscussionMessageFlag> getDiscussionMessageFlags(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlagLocalService.getDiscussionMessageFlags(start,
            end);
    }

    /**
    * Returns the number of discussion message flags.
    *
    * @return the number of discussion message flags
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getDiscussionMessageFlagsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlagLocalService.getDiscussionMessageFlagsCount();
    }

    /**
    * Updates the discussion message flag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param discussionMessageFlag the discussion message flag
    * @return the discussion message flag that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionMessageFlag updateDiscussionMessageFlag(
        com.ext.portlet.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlagLocalService.updateDiscussionMessageFlag(discussionMessageFlag);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _discussionMessageFlagLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _discussionMessageFlagLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _discussionMessageFlagLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List<com.ext.portlet.model.DiscussionMessageFlag> findMessageFlags(
        java.lang.Long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlagLocalService.findMessageFlags(messageId);
    }

    @Override
    public com.ext.portlet.model.DiscussionMessageFlag createFlag(
        java.lang.Long messageId, java.lang.String flagType,
        java.lang.String data, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlagLocalService.createFlag(messageId,
            flagType, data, userId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public DiscussionMessageFlagLocalService getWrappedDiscussionMessageFlagLocalService() {
        return _discussionMessageFlagLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedDiscussionMessageFlagLocalService(
        DiscussionMessageFlagLocalService discussionMessageFlagLocalService) {
        _discussionMessageFlagLocalService = discussionMessageFlagLocalService;
    }

    @Override
    public DiscussionMessageFlagLocalService getWrappedService() {
        return _discussionMessageFlagLocalService;
    }

    @Override
    public void setWrappedService(
        DiscussionMessageFlagLocalService discussionMessageFlagLocalService) {
        _discussionMessageFlagLocalService = discussionMessageFlagLocalService;
    }
}
