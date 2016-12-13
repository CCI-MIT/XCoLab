package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestDiscussionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestDiscussionLocalService
 * @generated
 */
public class ContestDiscussionLocalServiceWrapper
    implements ContestDiscussionLocalService,
        ServiceWrapper<ContestDiscussionLocalService> {
    private ContestDiscussionLocalService _contestDiscussionLocalService;

    public ContestDiscussionLocalServiceWrapper(
        ContestDiscussionLocalService contestDiscussionLocalService) {
        _contestDiscussionLocalService = contestDiscussionLocalService;
    }

    /**
    * Adds the contest discussion to the database. Also notifies the appropriate model listeners.
    *
    * @param contestDiscussion the contest discussion
    * @return the contest discussion that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestDiscussion addContestDiscussion(
        com.ext.portlet.model.ContestDiscussion contestDiscussion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestDiscussionLocalService.addContestDiscussion(contestDiscussion);
    }

    /**
    * Creates a new contest discussion with the primary key. Does not add the contest discussion to the database.
    *
    * @param DiscussionId the primary key for the new contest discussion
    * @return the new contest discussion
    */
    @Override
    public com.ext.portlet.model.ContestDiscussion createContestDiscussion(
        long DiscussionId) {
        return _contestDiscussionLocalService.createContestDiscussion(DiscussionId);
    }

    /**
    * Deletes the contest discussion with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param DiscussionId the primary key of the contest discussion
    * @return the contest discussion that was removed
    * @throws PortalException if a contest discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestDiscussion deleteContestDiscussion(
        long DiscussionId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestDiscussionLocalService.deleteContestDiscussion(DiscussionId);
    }

    /**
    * Deletes the contest discussion from the database. Also notifies the appropriate model listeners.
    *
    * @param contestDiscussion the contest discussion
    * @return the contest discussion that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestDiscussion deleteContestDiscussion(
        com.ext.portlet.model.ContestDiscussion contestDiscussion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestDiscussionLocalService.deleteContestDiscussion(contestDiscussion);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _contestDiscussionLocalService.dynamicQuery();
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
        return _contestDiscussionLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestDiscussionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _contestDiscussionLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestDiscussionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _contestDiscussionLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
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
        return _contestDiscussionLocalService.dynamicQueryCount(dynamicQuery);
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
        return _contestDiscussionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ContestDiscussion fetchContestDiscussion(
        long DiscussionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestDiscussionLocalService.fetchContestDiscussion(DiscussionId);
    }

    /**
    * Returns the contest discussion with the primary key.
    *
    * @param DiscussionId the primary key of the contest discussion
    * @return the contest discussion
    * @throws PortalException if a contest discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestDiscussion getContestDiscussion(
        long DiscussionId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestDiscussionLocalService.getContestDiscussion(DiscussionId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestDiscussionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contest discussions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestDiscussionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest discussions
    * @param end the upper bound of the range of contest discussions (not inclusive)
    * @return the range of contest discussions
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ContestDiscussion> getContestDiscussions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestDiscussionLocalService.getContestDiscussions(start, end);
    }

    /**
    * Returns the number of contest discussions.
    *
    * @return the number of contest discussions
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getContestDiscussionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestDiscussionLocalService.getContestDiscussionsCount();
    }

    /**
    * Updates the contest discussion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestDiscussion the contest discussion
    * @return the contest discussion that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestDiscussion updateContestDiscussion(
        com.ext.portlet.model.ContestDiscussion contestDiscussion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestDiscussionLocalService.updateContestDiscussion(contestDiscussion);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestDiscussionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestDiscussionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestDiscussionLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestDiscussionLocalService getWrappedContestDiscussionLocalService() {
        return _contestDiscussionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestDiscussionLocalService(
        ContestDiscussionLocalService contestDiscussionLocalService) {
        _contestDiscussionLocalService = contestDiscussionLocalService;
    }

    @Override
    public ContestDiscussionLocalService getWrappedService() {
        return _contestDiscussionLocalService;
    }

    @Override
    public void setWrappedService(
        ContestDiscussionLocalService contestDiscussionLocalService) {
        _contestDiscussionLocalService = contestDiscussionLocalService;
    }
}
