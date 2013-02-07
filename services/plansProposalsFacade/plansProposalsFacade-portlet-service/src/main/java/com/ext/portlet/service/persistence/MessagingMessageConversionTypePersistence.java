package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingMessageConversionType;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the messaging message conversion type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageConversionTypePersistenceImpl
 * @see MessagingMessageConversionTypeUtil
 * @generated
 */
public interface MessagingMessageConversionTypePersistence
    extends BasePersistence<MessagingMessageConversionType> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MessagingMessageConversionTypeUtil} to access the messaging message conversion type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the messaging message conversion type in the entity cache if it is enabled.
    *
    * @param messagingMessageConversionType the messaging message conversion type
    */
    public void cacheResult(
        com.ext.portlet.model.MessagingMessageConversionType messagingMessageConversionType);

    /**
    * Caches the messaging message conversion types in the entity cache if it is enabled.
    *
    * @param messagingMessageConversionTypes the messaging message conversion types
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.MessagingMessageConversionType> messagingMessageConversionTypes);

    /**
    * Creates a new messaging message conversion type with the primary key. Does not add the messaging message conversion type to the database.
    *
    * @param typeId the primary key for the new messaging message conversion type
    * @return the new messaging message conversion type
    */
    public com.ext.portlet.model.MessagingMessageConversionType create(
        long typeId);

    /**
    * Removes the messaging message conversion type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param typeId the primary key of the messaging message conversion type
    * @return the messaging message conversion type that was removed
    * @throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException if a messaging message conversion type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversionType remove(
        long typeId)
        throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.MessagingMessageConversionType updateImpl(
        com.ext.portlet.model.MessagingMessageConversionType messagingMessageConversionType,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging message conversion type with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingMessageConversionTypeException} if it could not be found.
    *
    * @param typeId the primary key of the messaging message conversion type
    * @return the messaging message conversion type
    * @throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException if a messaging message conversion type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversionType findByPrimaryKey(
        long typeId)
        throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging message conversion type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param typeId the primary key of the messaging message conversion type
    * @return the messaging message conversion type, or <code>null</code> if a messaging message conversion type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversionType fetchByPrimaryKey(
        long typeId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging message conversion type where name = &#63; or throws a {@link com.ext.portlet.NoSuchMessagingMessageConversionTypeException} if it could not be found.
    *
    * @param name the name
    * @return the matching messaging message conversion type
    * @throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException if a matching messaging message conversion type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversionType findByfindByName(
        java.lang.String name)
        throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging message conversion type where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param name the name
    * @return the matching messaging message conversion type, or <code>null</code> if a matching messaging message conversion type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversionType fetchByfindByName(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging message conversion type where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param name the name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching messaging message conversion type, or <code>null</code> if a matching messaging message conversion type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversionType fetchByfindByName(
        java.lang.String name, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the messaging message conversion types.
    *
    * @return the messaging message conversion types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessagingMessageConversionType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the messaging message conversion types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging message conversion types
    * @param end the upper bound of the range of messaging message conversion types (not inclusive)
    * @return the range of messaging message conversion types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessagingMessageConversionType> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the messaging message conversion types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging message conversion types
    * @param end the upper bound of the range of messaging message conversion types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of messaging message conversion types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessagingMessageConversionType> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the messaging message conversion type where name = &#63; from the database.
    *
    * @param name the name
    * @throws SystemException if a system exception occurred
    */
    public void removeByfindByName(java.lang.String name)
        throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the messaging message conversion types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of messaging message conversion types where name = &#63;.
    *
    * @param name the name
    * @return the number of matching messaging message conversion types
    * @throws SystemException if a system exception occurred
    */
    public int countByfindByName(java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of messaging message conversion types.
    *
    * @return the number of messaging message conversion types
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
