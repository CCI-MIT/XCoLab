package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BalloonLinkLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see BalloonLinkLocalService
 * @generated
 */
public class BalloonLinkLocalServiceWrapper implements BalloonLinkLocalService,
    ServiceWrapper<BalloonLinkLocalService> {
    private BalloonLinkLocalService _balloonLinkLocalService;

    public BalloonLinkLocalServiceWrapper(
        BalloonLinkLocalService balloonLinkLocalService) {
        _balloonLinkLocalService = balloonLinkLocalService;
    }

    /**
    * Adds the balloon link to the database. Also notifies the appropriate model listeners.
    *
    * @param balloonLink the balloon link
    * @return the balloon link that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.BalloonLink addBalloonLink(
        com.ext.portlet.model.BalloonLink balloonLink)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _balloonLinkLocalService.addBalloonLink(balloonLink);
    }

    /**
    * Creates a new balloon link with the primary key. Does not add the balloon link to the database.
    *
    * @param uuid the primary key for the new balloon link
    * @return the new balloon link
    */
    @Override
    public com.ext.portlet.model.BalloonLink createBalloonLink(
        java.lang.String uuid) {
        return _balloonLinkLocalService.createBalloonLink(uuid);
    }

    /**
    * Deletes the balloon link with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param uuid the primary key of the balloon link
    * @return the balloon link that was removed
    * @throws PortalException if a balloon link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.BalloonLink deleteBalloonLink(
        java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _balloonLinkLocalService.deleteBalloonLink(uuid);
    }

    /**
    * Deletes the balloon link from the database. Also notifies the appropriate model listeners.
    *
    * @param balloonLink the balloon link
    * @return the balloon link that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.BalloonLink deleteBalloonLink(
        com.ext.portlet.model.BalloonLink balloonLink)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _balloonLinkLocalService.deleteBalloonLink(balloonLink);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _balloonLinkLocalService.dynamicQuery();
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
        return _balloonLinkLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _balloonLinkLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _balloonLinkLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
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
        return _balloonLinkLocalService.dynamicQueryCount(dynamicQuery);
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
        return _balloonLinkLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.BalloonLink fetchBalloonLink(
        java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _balloonLinkLocalService.fetchBalloonLink(uuid);
    }

    /**
    * Returns the balloon link with the primary key.
    *
    * @param uuid the primary key of the balloon link
    * @return the balloon link
    * @throws PortalException if a balloon link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.BalloonLink getBalloonLink(
        java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _balloonLinkLocalService.getBalloonLink(uuid);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _balloonLinkLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the balloon links.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of balloon links
    * @param end the upper bound of the range of balloon links (not inclusive)
    * @return the range of balloon links
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.BalloonLink> getBalloonLinks(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _balloonLinkLocalService.getBalloonLinks(start, end);
    }

    /**
    * Returns the number of balloon links.
    *
    * @return the number of balloon links
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getBalloonLinksCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _balloonLinkLocalService.getBalloonLinksCount();
    }

    /**
    * Updates the balloon link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param balloonLink the balloon link
    * @return the balloon link that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.BalloonLink updateBalloonLink(
        com.ext.portlet.model.BalloonLink balloonLink)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _balloonLinkLocalService.updateBalloonLink(balloonLink);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _balloonLinkLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _balloonLinkLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _balloonLinkLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public com.ext.portlet.model.BalloonLink getBalloonLinkForUser(
        java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _balloonLinkLocalService.getBalloonLinkForUser(uuid);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public BalloonLinkLocalService getWrappedBalloonLinkLocalService() {
        return _balloonLinkLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedBalloonLinkLocalService(
        BalloonLinkLocalService balloonLinkLocalService) {
        _balloonLinkLocalService = balloonLinkLocalService;
    }

    @Override
    public BalloonLinkLocalService getWrappedService() {
        return _balloonLinkLocalService;
    }

    @Override
    public void setWrappedService(
        BalloonLinkLocalService balloonLinkLocalService) {
        _balloonLinkLocalService = balloonLinkLocalService;
    }
}
