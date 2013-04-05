package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link EmailListLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       EmailListLocalService
 * @generated
 */
public class EmailListLocalServiceWrapper implements EmailListLocalService,
    ServiceWrapper<EmailListLocalService> {
    private EmailListLocalService _emailListLocalService;

    public EmailListLocalServiceWrapper(
        EmailListLocalService emailListLocalService) {
        _emailListLocalService = emailListLocalService;
    }

    /**
    * Adds the email list to the database. Also notifies the appropriate model listeners.
    *
    * @param emailList the email list
    * @return the email list that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.EmailList addEmailList(
        com.ext.portlet.model.EmailList emailList)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _emailListLocalService.addEmailList(emailList);
    }

    /**
    * Creates a new email list with the primary key. Does not add the email list to the database.
    *
    * @param id the primary key for the new email list
    * @return the new email list
    */
    public com.ext.portlet.model.EmailList createEmailList(long id) {
        return _emailListLocalService.createEmailList(id);
    }

    /**
    * Deletes the email list with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the email list
    * @throws PortalException if a email list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteEmailList(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _emailListLocalService.deleteEmailList(id);
    }

    /**
    * Deletes the email list from the database. Also notifies the appropriate model listeners.
    *
    * @param emailList the email list
    * @throws SystemException if a system exception occurred
    */
    public void deleteEmailList(com.ext.portlet.model.EmailList emailList)
        throws com.liferay.portal.kernel.exception.SystemException {
        _emailListLocalService.deleteEmailList(emailList);
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
        return _emailListLocalService.dynamicQuery(dynamicQuery);
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
        return _emailListLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _emailListLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _emailListLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.EmailList fetchEmailList(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _emailListLocalService.fetchEmailList(id);
    }

    /**
    * Returns the email list with the primary key.
    *
    * @param id the primary key of the email list
    * @return the email list
    * @throws PortalException if a email list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.EmailList getEmailList(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _emailListLocalService.getEmailList(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _emailListLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the email lists.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of email lists
    * @param end the upper bound of the range of email lists (not inclusive)
    * @return the range of email lists
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.EmailList> getEmailLists(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _emailListLocalService.getEmailLists(start, end);
    }

    /**
    * Returns the number of email lists.
    *
    * @return the number of email lists
    * @throws SystemException if a system exception occurred
    */
    public int getEmailListsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _emailListLocalService.getEmailListsCount();
    }

    /**
    * Updates the email list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param emailList the email list
    * @return the email list that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.EmailList updateEmailList(
        com.ext.portlet.model.EmailList emailList)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _emailListLocalService.updateEmailList(emailList);
    }

    /**
    * Updates the email list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param emailList the email list
    * @param merge whether to merge the email list with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the email list that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.EmailList updateEmailList(
        com.ext.portlet.model.EmailList emailList, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _emailListLocalService.updateEmailList(emailList, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _emailListLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _emailListLocalService.setBeanIdentifier(beanIdentifier);
    }

    public com.ext.portlet.model.EmailList findByListNameEmailAddress(
        java.lang.String listName, java.lang.String emailAddress)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException {
        return _emailListLocalService.findByListNameEmailAddress(listName,
            emailAddress);
    }

    public java.util.List<com.ext.portlet.model.EmailList> findByListName(
        java.lang.String listName)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException {
        return _emailListLocalService.findByListName(listName);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public EmailListLocalService getWrappedEmailListLocalService() {
        return _emailListLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedEmailListLocalService(
        EmailListLocalService emailListLocalService) {
        _emailListLocalService = emailListLocalService;
    }

    public EmailListLocalService getWrappedService() {
        return _emailListLocalService;
    }

    public void setWrappedService(EmailListLocalService emailListLocalService) {
        _emailListLocalService = emailListLocalService;
    }
}
