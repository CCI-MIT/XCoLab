package com.ext.portlet.service.persistence;

import com.ext.portlet.model.EmailList;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the email list service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailListPersistenceImpl
 * @see EmailListUtil
 * @generated
 */
public interface EmailListPersistence extends BasePersistence<EmailList> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link EmailListUtil} to access the email list persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the email list in the entity cache if it is enabled.
    *
    * @param emailList the email list
    */
    public void cacheResult(com.ext.portlet.model.EmailList emailList);

    /**
    * Caches the email lists in the entity cache if it is enabled.
    *
    * @param emailLists the email lists
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.EmailList> emailLists);

    /**
    * Creates a new email list with the primary key. Does not add the email list to the database.
    *
    * @param id the primary key for the new email list
    * @return the new email list
    */
    public com.ext.portlet.model.EmailList create(long id);

    /**
    * Removes the email list with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the email list
    * @return the email list that was removed
    * @throws com.ext.portlet.NoSuchEmailListException if a email list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.EmailList remove(long id)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.EmailList updateImpl(
        com.ext.portlet.model.EmailList emailList, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the email list with the primary key or throws a {@link com.ext.portlet.NoSuchEmailListException} if it could not be found.
    *
    * @param id the primary key of the email list
    * @return the email list
    * @throws com.ext.portlet.NoSuchEmailListException if a email list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.EmailList findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the email list with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the email list
    * @return the email list, or <code>null</code> if a email list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.EmailList fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the email lists where name = &#63;.
    *
    * @param name the name
    * @return the matching email lists
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.EmailList> findByfindByName(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the email lists where name = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param name the name
    * @param start the lower bound of the range of email lists
    * @param end the upper bound of the range of email lists (not inclusive)
    * @return the range of matching email lists
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.EmailList> findByfindByName(
        java.lang.String name, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the email lists where name = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param name the name
    * @param start the lower bound of the range of email lists
    * @param end the upper bound of the range of email lists (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching email lists
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.EmailList> findByfindByName(
        java.lang.String name, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first email list in the ordered set where name = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param name the name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching email list
    * @throws com.ext.portlet.NoSuchEmailListException if a matching email list could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.EmailList findByfindByName_First(
        java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last email list in the ordered set where name = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param name the name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching email list
    * @throws com.ext.portlet.NoSuchEmailListException if a matching email list could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.EmailList findByfindByName_Last(
        java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the email lists before and after the current email list in the ordered set where name = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current email list
    * @param name the name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next email list
    * @throws com.ext.portlet.NoSuchEmailListException if a email list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.EmailList[] findByfindByName_PrevAndNext(
        long id, java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the email list where name = &#63; and email = &#63; or throws a {@link com.ext.portlet.NoSuchEmailListException} if it could not be found.
    *
    * @param name the name
    * @param email the email
    * @return the matching email list
    * @throws com.ext.portlet.NoSuchEmailListException if a matching email list could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.EmailList findByfindByNameEmail(
        java.lang.String name, java.lang.String email)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the email list where name = &#63; and email = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param name the name
    * @param email the email
    * @return the matching email list, or <code>null</code> if a matching email list could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.EmailList fetchByfindByNameEmail(
        java.lang.String name, java.lang.String email)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the email list where name = &#63; and email = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param name the name
    * @param email the email
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching email list, or <code>null</code> if a matching email list could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.EmailList fetchByfindByNameEmail(
        java.lang.String name, java.lang.String email, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the email lists.
    *
    * @return the email lists
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.EmailList> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.EmailList> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the email lists.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of email lists
    * @param end the upper bound of the range of email lists (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of email lists
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.EmailList> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the email lists where name = &#63; from the database.
    *
    * @param name the name
    * @throws SystemException if a system exception occurred
    */
    public void removeByfindByName(java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the email list where name = &#63; and email = &#63; from the database.
    *
    * @param name the name
    * @param email the email
    * @throws SystemException if a system exception occurred
    */
    public void removeByfindByNameEmail(java.lang.String name,
        java.lang.String email)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the email lists from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of email lists where name = &#63;.
    *
    * @param name the name
    * @return the number of matching email lists
    * @throws SystemException if a system exception occurred
    */
    public int countByfindByName(java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of email lists where name = &#63; and email = &#63;.
    *
    * @param name the name
    * @param email the email
    * @return the number of matching email lists
    * @throws SystemException if a system exception occurred
    */
    public int countByfindByNameEmail(java.lang.String name,
        java.lang.String email)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of email lists.
    *
    * @return the number of email lists
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
