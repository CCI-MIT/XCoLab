package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the email list local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailListLocalServiceUtil
 * @see com.ext.portlet.service.base.EmailListLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.EmailListLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface EmailListLocalService extends PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link EmailListLocalServiceUtil} to access the email list local service. Add custom service methods to {@link com.ext.portlet.service.impl.EmailListLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the email list to the database. Also notifies the appropriate model listeners.
    *
    * @param emailList the email list
    * @return the email list that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.EmailList addEmailList(
        com.ext.portlet.model.EmailList emailList)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new email list with the primary key. Does not add the email list to the database.
    *
    * @param id the primary key for the new email list
    * @return the new email list
    */
    public com.ext.portlet.model.EmailList createEmailList(long id);

    /**
    * Deletes the email list with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the email list
    * @throws PortalException if a email list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteEmailList(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the email list from the database. Also notifies the appropriate model listeners.
    *
    * @param emailList the email list
    * @throws SystemException if a system exception occurred
    */
    public void deleteEmailList(com.ext.portlet.model.EmailList emailList)
        throws com.liferay.portal.kernel.exception.SystemException;

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
        throws com.liferay.portal.kernel.exception.SystemException;

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
        int end) throws com.liferay.portal.kernel.exception.SystemException;

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
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.EmailList fetchEmailList(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the email list with the primary key.
    *
    * @param id the primary key of the email list
    * @return the email list
    * @throws PortalException if a email list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.EmailList getEmailList(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.EmailList> getEmailLists(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of email lists.
    *
    * @return the number of email lists
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getEmailListsCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the email list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param emailList the email list
    * @return the email list that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.EmailList updateEmailList(
        com.ext.portlet.model.EmailList emailList)
        throws com.liferay.portal.kernel.exception.SystemException;

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
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);

    public com.ext.portlet.model.EmailList findByListNameEmailAddress(
        java.lang.String listName, java.lang.String emailAddress)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.model.EmailList> findByListName(
        java.lang.String listName)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException;
}
