package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BalloonTextLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see BalloonTextLocalService
 * @generated
 */
public class BalloonTextLocalServiceWrapper implements BalloonTextLocalService,
    ServiceWrapper<BalloonTextLocalService> {
    private BalloonTextLocalService _balloonTextLocalService;

    public BalloonTextLocalServiceWrapper(
        BalloonTextLocalService balloonTextLocalService) {
        _balloonTextLocalService = balloonTextLocalService;
    }

    /**
    * Adds the balloon text to the database. Also notifies the appropriate model listeners.
    *
    * @param balloonText the balloon text
    * @return the balloon text that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.BalloonText addBalloonText(
        com.ext.portlet.model.BalloonText balloonText)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _balloonTextLocalService.addBalloonText(balloonText);
    }

    /**
    * Creates a new balloon text with the primary key. Does not add the balloon text to the database.
    *
    * @param id the primary key for the new balloon text
    * @return the new balloon text
    */
    @Override
    public com.ext.portlet.model.BalloonText createBalloonText(long id) {
        return _balloonTextLocalService.createBalloonText(id);
    }

    /**
    * Deletes the balloon text with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the balloon text
    * @return the balloon text that was removed
    * @throws PortalException if a balloon text with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.BalloonText deleteBalloonText(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _balloonTextLocalService.deleteBalloonText(id);
    }

    /**
    * Deletes the balloon text from the database. Also notifies the appropriate model listeners.
    *
    * @param balloonText the balloon text
    * @return the balloon text that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.BalloonText deleteBalloonText(
        com.ext.portlet.model.BalloonText balloonText)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _balloonTextLocalService.deleteBalloonText(balloonText);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _balloonTextLocalService.dynamicQuery();
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
        return _balloonTextLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonTextModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _balloonTextLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonTextModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _balloonTextLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _balloonTextLocalService.dynamicQueryCount(dynamicQuery);
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
        return _balloonTextLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.BalloonText fetchBalloonText(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _balloonTextLocalService.fetchBalloonText(id);
    }

    /**
    * Returns the balloon text with the primary key.
    *
    * @param id the primary key of the balloon text
    * @return the balloon text
    * @throws PortalException if a balloon text with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.BalloonText getBalloonText(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _balloonTextLocalService.getBalloonText(id);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _balloonTextLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the balloon texts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonTextModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of balloon texts
    * @param end the upper bound of the range of balloon texts (not inclusive)
    * @return the range of balloon texts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.BalloonText> getBalloonTexts(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _balloonTextLocalService.getBalloonTexts(start, end);
    }

    /**
    * Returns the number of balloon texts.
    *
    * @return the number of balloon texts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getBalloonTextsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _balloonTextLocalService.getBalloonTextsCount();
    }

    /**
    * Updates the balloon text in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param balloonText the balloon text
    * @return the balloon text that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.BalloonText updateBalloonText(
        com.ext.portlet.model.BalloonText balloonText)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _balloonTextLocalService.updateBalloonText(balloonText);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _balloonTextLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _balloonTextLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _balloonTextLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public BalloonTextLocalService getWrappedBalloonTextLocalService() {
        return _balloonTextLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedBalloonTextLocalService(
        BalloonTextLocalService balloonTextLocalService) {
        _balloonTextLocalService = balloonTextLocalService;
    }

    @Override
    public BalloonTextLocalService getWrappedService() {
        return _balloonTextLocalService;
    }

    @Override
    public void setWrappedService(
        BalloonTextLocalService balloonTextLocalService) {
        _balloonTextLocalService = balloonTextLocalService;
    }
}
