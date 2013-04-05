package com.ext.portlet.service.persistence;

import com.ext.portlet.model.EmailList;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the email list service. This utility wraps {@link EmailListPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailListPersistence
 * @see EmailListPersistenceImpl
 * @generated
 */
public class EmailListUtil {
    private static EmailListPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(EmailList emailList) {
        getPersistence().clearCache(emailList);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<EmailList> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<EmailList> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<EmailList> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static EmailList update(EmailList emailList, boolean merge)
        throws SystemException {
        return getPersistence().update(emailList, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static EmailList update(EmailList emailList, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(emailList, merge, serviceContext);
    }

    /**
    * Caches the email list in the entity cache if it is enabled.
    *
    * @param emailList the email list
    */
    public static void cacheResult(com.ext.portlet.model.EmailList emailList) {
        getPersistence().cacheResult(emailList);
    }

    /**
    * Caches the email lists in the entity cache if it is enabled.
    *
    * @param emailLists the email lists
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.EmailList> emailLists) {
        getPersistence().cacheResult(emailLists);
    }

    /**
    * Creates a new email list with the primary key. Does not add the email list to the database.
    *
    * @param id the primary key for the new email list
    * @return the new email list
    */
    public static com.ext.portlet.model.EmailList create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the email list with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the email list
    * @return the email list that was removed
    * @throws com.ext.portlet.NoSuchEmailListException if a email list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.EmailList remove(long id)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.EmailList updateImpl(
        com.ext.portlet.model.EmailList emailList, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(emailList, merge);
    }

    /**
    * Returns the email list with the primary key or throws a {@link com.ext.portlet.NoSuchEmailListException} if it could not be found.
    *
    * @param id the primary key of the email list
    * @return the email list
    * @throws com.ext.portlet.NoSuchEmailListException if a email list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.EmailList findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the email list with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the email list
    * @return the email list, or <code>null</code> if a email list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.EmailList fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the email lists where name = &#63;.
    *
    * @param name the name
    * @return the matching email lists
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.EmailList> findByfindByName(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByfindByName(name);
    }

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
    public static java.util.List<com.ext.portlet.model.EmailList> findByfindByName(
        java.lang.String name, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByfindByName(name, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.EmailList> findByfindByName(
        java.lang.String name, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByfindByName(name, start, end, orderByComparator);
    }

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
    public static com.ext.portlet.model.EmailList findByfindByName_First(
        java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByfindByName_First(name, orderByComparator);
    }

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
    public static com.ext.portlet.model.EmailList findByfindByName_Last(
        java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByfindByName_Last(name, orderByComparator);
    }

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
    public static com.ext.portlet.model.EmailList[] findByfindByName_PrevAndNext(
        long id, java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByfindByName_PrevAndNext(id, name, orderByComparator);
    }

    /**
    * Returns the email list where name = &#63; and email = &#63; or throws a {@link com.ext.portlet.NoSuchEmailListException} if it could not be found.
    *
    * @param name the name
    * @param email the email
    * @return the matching email list
    * @throws com.ext.portlet.NoSuchEmailListException if a matching email list could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.EmailList findByfindByNameEmail(
        java.lang.String name, java.lang.String email)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByfindByNameEmail(name, email);
    }

    /**
    * Returns the email list where name = &#63; and email = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param name the name
    * @param email the email
    * @return the matching email list, or <code>null</code> if a matching email list could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.EmailList fetchByfindByNameEmail(
        java.lang.String name, java.lang.String email)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByfindByNameEmail(name, email);
    }

    /**
    * Returns the email list where name = &#63; and email = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param name the name
    * @param email the email
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching email list, or <code>null</code> if a matching email list could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.EmailList fetchByfindByNameEmail(
        java.lang.String name, java.lang.String email, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByfindByNameEmail(name, email, retrieveFromCache);
    }

    /**
    * Returns all the email lists.
    *
    * @return the email lists
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.EmailList> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
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
    public static java.util.List<com.ext.portlet.model.EmailList> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.EmailList> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the email lists where name = &#63; from the database.
    *
    * @param name the name
    * @throws SystemException if a system exception occurred
    */
    public static void removeByfindByName(java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByfindByName(name);
    }

    /**
    * Removes the email list where name = &#63; and email = &#63; from the database.
    *
    * @param name the name
    * @param email the email
    * @throws SystemException if a system exception occurred
    */
    public static void removeByfindByNameEmail(java.lang.String name,
        java.lang.String email)
        throws com.ext.portlet.NoSuchEmailListException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByfindByNameEmail(name, email);
    }

    /**
    * Removes all the email lists from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of email lists where name = &#63;.
    *
    * @param name the name
    * @return the number of matching email lists
    * @throws SystemException if a system exception occurred
    */
    public static int countByfindByName(java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByfindByName(name);
    }

    /**
    * Returns the number of email lists where name = &#63; and email = &#63;.
    *
    * @param name the name
    * @param email the email
    * @return the number of matching email lists
    * @throws SystemException if a system exception occurred
    */
    public static int countByfindByNameEmail(java.lang.String name,
        java.lang.String email)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByfindByNameEmail(name, email);
    }

    /**
    * Returns the number of email lists.
    *
    * @return the number of email lists
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static EmailListPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (EmailListPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    EmailListPersistence.class.getName());

            ReferenceRegistry.registerReference(EmailListUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(EmailListPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(EmailListUtil.class, "_persistence");
    }
}
