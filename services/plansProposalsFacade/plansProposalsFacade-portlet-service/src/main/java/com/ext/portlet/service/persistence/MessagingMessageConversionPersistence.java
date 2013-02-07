package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingMessageConversion;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the messaging message conversion service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageConversionPersistenceImpl
 * @see MessagingMessageConversionUtil
 * @generated
 */
public interface MessagingMessageConversionPersistence extends BasePersistence<MessagingMessageConversion> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MessagingMessageConversionUtil} to access the messaging message conversion persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the messaging message conversion in the entity cache if it is enabled.
    *
    * @param messagingMessageConversion the messaging message conversion
    */
    public void cacheResult(
        com.ext.portlet.model.MessagingMessageConversion messagingMessageConversion);

    /**
    * Caches the messaging message conversions in the entity cache if it is enabled.
    *
    * @param messagingMessageConversions the messaging message conversions
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.MessagingMessageConversion> messagingMessageConversions);

    /**
    * Creates a new messaging message conversion with the primary key. Does not add the messaging message conversion to the database.
    *
    * @param conversionId the primary key for the new messaging message conversion
    * @return the new messaging message conversion
    */
    public com.ext.portlet.model.MessagingMessageConversion create(
        long conversionId);

    /**
    * Removes the messaging message conversion with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param conversionId the primary key of the messaging message conversion
    * @return the messaging message conversion that was removed
    * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a messaging message conversion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversion remove(
        long conversionId)
        throws com.ext.portlet.NoSuchMessagingMessageConversionException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.MessagingMessageConversion updateImpl(
        com.ext.portlet.model.MessagingMessageConversion messagingMessageConversion,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging message conversion with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingMessageConversionException} if it could not be found.
    *
    * @param conversionId the primary key of the messaging message conversion
    * @return the messaging message conversion
    * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a messaging message conversion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversion findByPrimaryKey(
        long conversionId)
        throws com.ext.portlet.NoSuchMessagingMessageConversionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging message conversion with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param conversionId the primary key of the messaging message conversion
    * @return the messaging message conversion, or <code>null</code> if a messaging message conversion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversion fetchByPrimaryKey(
        long conversionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the messaging message conversions where messageId = &#63; and conversionTypeId = &#63;.
    *
    * @param messageId the message ID
    * @param conversionTypeId the conversion type ID
    * @return the matching messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessagingMessageConversion> findByfindByType(
        long messageId, long conversionTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the messaging message conversions where messageId = &#63; and conversionTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the message ID
    * @param conversionTypeId the conversion type ID
    * @param start the lower bound of the range of messaging message conversions
    * @param end the upper bound of the range of messaging message conversions (not inclusive)
    * @return the range of matching messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessagingMessageConversion> findByfindByType(
        long messageId, long conversionTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the messaging message conversions where messageId = &#63; and conversionTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the message ID
    * @param conversionTypeId the conversion type ID
    * @param start the lower bound of the range of messaging message conversions
    * @param end the upper bound of the range of messaging message conversions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessagingMessageConversion> findByfindByType(
        long messageId, long conversionTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first messaging message conversion in the ordered set where messageId = &#63; and conversionTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the message ID
    * @param conversionTypeId the conversion type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching messaging message conversion
    * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a matching messaging message conversion could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversion findByfindByType_First(
        long messageId, long conversionTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessagingMessageConversionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last messaging message conversion in the ordered set where messageId = &#63; and conversionTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the message ID
    * @param conversionTypeId the conversion type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching messaging message conversion
    * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a matching messaging message conversion could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversion findByfindByType_Last(
        long messageId, long conversionTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessagingMessageConversionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging message conversions before and after the current messaging message conversion in the ordered set where messageId = &#63; and conversionTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param conversionId the primary key of the current messaging message conversion
    * @param messageId the message ID
    * @param conversionTypeId the conversion type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next messaging message conversion
    * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a messaging message conversion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversion[] findByfindByType_PrevAndNext(
        long conversionId, long messageId, long conversionTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessagingMessageConversionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the messaging message conversions.
    *
    * @return the messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessagingMessageConversion> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the messaging message conversions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging message conversions
    * @param end the upper bound of the range of messaging message conversions (not inclusive)
    * @return the range of messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessagingMessageConversion> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the messaging message conversions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging message conversions
    * @param end the upper bound of the range of messaging message conversions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessagingMessageConversion> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the messaging message conversions where messageId = &#63; and conversionTypeId = &#63; from the database.
    *
    * @param messageId the message ID
    * @param conversionTypeId the conversion type ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByfindByType(long messageId, long conversionTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the messaging message conversions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of messaging message conversions where messageId = &#63; and conversionTypeId = &#63;.
    *
    * @param messageId the message ID
    * @param conversionTypeId the conversion type ID
    * @return the number of matching messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public int countByfindByType(long messageId, long conversionTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of messaging message conversions.
    *
    * @return the number of messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
