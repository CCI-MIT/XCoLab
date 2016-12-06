package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestEmailTemplateLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestEmailTemplateLocalService
 * @generated
 */
public class ContestEmailTemplateLocalServiceWrapper
    implements ContestEmailTemplateLocalService,
        ServiceWrapper<ContestEmailTemplateLocalService> {
    private ContestEmailTemplateLocalService _contestEmailTemplateLocalService;

    public ContestEmailTemplateLocalServiceWrapper(
        ContestEmailTemplateLocalService contestEmailTemplateLocalService) {
        _contestEmailTemplateLocalService = contestEmailTemplateLocalService;
    }

    /**
    * Adds the contest email template to the database. Also notifies the appropriate model listeners.
    *
    * @param contestEmailTemplate the contest email template
    * @return the contest email template that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestEmailTemplate addContestEmailTemplate(
        com.ext.portlet.model.ContestEmailTemplate contestEmailTemplate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestEmailTemplateLocalService.addContestEmailTemplate(contestEmailTemplate);
    }

    /**
    * Creates a new contest email template with the primary key. Does not add the contest email template to the database.
    *
    * @param type the primary key for the new contest email template
    * @return the new contest email template
    */
    @Override
    public com.ext.portlet.model.ContestEmailTemplate createContestEmailTemplate(
        java.lang.String type) {
        return _contestEmailTemplateLocalService.createContestEmailTemplate(type);
    }

    /**
    * Deletes the contest email template with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param type the primary key of the contest email template
    * @return the contest email template that was removed
    * @throws PortalException if a contest email template with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestEmailTemplate deleteContestEmailTemplate(
        java.lang.String type)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestEmailTemplateLocalService.deleteContestEmailTemplate(type);
    }

    /**
    * Deletes the contest email template from the database. Also notifies the appropriate model listeners.
    *
    * @param contestEmailTemplate the contest email template
    * @return the contest email template that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestEmailTemplate deleteContestEmailTemplate(
        com.ext.portlet.model.ContestEmailTemplate contestEmailTemplate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestEmailTemplateLocalService.deleteContestEmailTemplate(contestEmailTemplate);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _contestEmailTemplateLocalService.dynamicQuery();
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
        return _contestEmailTemplateLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestEmailTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _contestEmailTemplateLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestEmailTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _contestEmailTemplateLocalService.dynamicQuery(dynamicQuery,
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
        return _contestEmailTemplateLocalService.dynamicQueryCount(dynamicQuery);
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
        return _contestEmailTemplateLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ContestEmailTemplate fetchContestEmailTemplate(
        java.lang.String type)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestEmailTemplateLocalService.fetchContestEmailTemplate(type);
    }

    /**
    * Returns the contest email template with the primary key.
    *
    * @param type the primary key of the contest email template
    * @return the contest email template
    * @throws PortalException if a contest email template with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestEmailTemplate getContestEmailTemplate(
        java.lang.String type)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestEmailTemplateLocalService.getContestEmailTemplate(type);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestEmailTemplateLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contest email templates.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestEmailTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest email templates
    * @param end the upper bound of the range of contest email templates (not inclusive)
    * @return the range of contest email templates
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ContestEmailTemplate> getContestEmailTemplates(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestEmailTemplateLocalService.getContestEmailTemplates(start,
            end);
    }

    /**
    * Returns the number of contest email templates.
    *
    * @return the number of contest email templates
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getContestEmailTemplatesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestEmailTemplateLocalService.getContestEmailTemplatesCount();
    }

    /**
    * Updates the contest email template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestEmailTemplate the contest email template
    * @return the contest email template that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestEmailTemplate updateContestEmailTemplate(
        com.ext.portlet.model.ContestEmailTemplate contestEmailTemplate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestEmailTemplateLocalService.updateContestEmailTemplate(contestEmailTemplate);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestEmailTemplateLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestEmailTemplateLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestEmailTemplateLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestEmailTemplateLocalService getWrappedContestEmailTemplateLocalService() {
        return _contestEmailTemplateLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestEmailTemplateLocalService(
        ContestEmailTemplateLocalService contestEmailTemplateLocalService) {
        _contestEmailTemplateLocalService = contestEmailTemplateLocalService;
    }

    @Override
    public ContestEmailTemplateLocalService getWrappedService() {
        return _contestEmailTemplateLocalService;
    }

    @Override
    public void setWrappedService(
        ContestEmailTemplateLocalService contestEmailTemplateLocalService) {
        _contestEmailTemplateLocalService = contestEmailTemplateLocalService;
    }
}
