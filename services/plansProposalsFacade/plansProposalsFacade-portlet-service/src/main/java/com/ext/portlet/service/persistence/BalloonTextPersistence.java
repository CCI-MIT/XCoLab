package com.ext.portlet.service.persistence;

import com.ext.portlet.model.BalloonText;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the balloon text service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BalloonTextPersistenceImpl
 * @see BalloonTextUtil
 * @generated
 */
public interface BalloonTextPersistence extends BasePersistence<BalloonText> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link BalloonTextUtil} to access the balloon text persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the balloon texts where enabled = &#63;.
    *
    * @param enabled the enabled
    * @return the matching balloon texts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.BalloonText> findByEnabled(
        boolean enabled)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the balloon texts where enabled = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonTextModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param enabled the enabled
    * @param start the lower bound of the range of balloon texts
    * @param end the upper bound of the range of balloon texts (not inclusive)
    * @return the range of matching balloon texts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.BalloonText> findByEnabled(
        boolean enabled, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the balloon texts where enabled = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonTextModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param enabled the enabled
    * @param start the lower bound of the range of balloon texts
    * @param end the upper bound of the range of balloon texts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching balloon texts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.BalloonText> findByEnabled(
        boolean enabled, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first balloon text in the ordered set where enabled = &#63;.
    *
    * @param enabled the enabled
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching balloon text
    * @throws com.ext.portlet.NoSuchBalloonTextException if a matching balloon text could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.BalloonText findByEnabled_First(
        boolean enabled,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchBalloonTextException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first balloon text in the ordered set where enabled = &#63;.
    *
    * @param enabled the enabled
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching balloon text, or <code>null</code> if a matching balloon text could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.BalloonText fetchByEnabled_First(
        boolean enabled,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last balloon text in the ordered set where enabled = &#63;.
    *
    * @param enabled the enabled
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching balloon text
    * @throws com.ext.portlet.NoSuchBalloonTextException if a matching balloon text could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.BalloonText findByEnabled_Last(
        boolean enabled,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchBalloonTextException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last balloon text in the ordered set where enabled = &#63;.
    *
    * @param enabled the enabled
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching balloon text, or <code>null</code> if a matching balloon text could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.BalloonText fetchByEnabled_Last(
        boolean enabled,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the balloon texts before and after the current balloon text in the ordered set where enabled = &#63;.
    *
    * @param id the primary key of the current balloon text
    * @param enabled the enabled
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next balloon text
    * @throws com.ext.portlet.NoSuchBalloonTextException if a balloon text with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.BalloonText[] findByEnabled_PrevAndNext(
        long id, boolean enabled,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchBalloonTextException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the balloon texts where enabled = &#63; from the database.
    *
    * @param enabled the enabled
    * @throws SystemException if a system exception occurred
    */
    public void removeByEnabled(boolean enabled)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of balloon texts where enabled = &#63;.
    *
    * @param enabled the enabled
    * @return the number of matching balloon texts
    * @throws SystemException if a system exception occurred
    */
    public int countByEnabled(boolean enabled)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the balloon text in the entity cache if it is enabled.
    *
    * @param balloonText the balloon text
    */
    public void cacheResult(com.ext.portlet.model.BalloonText balloonText);

    /**
    * Caches the balloon texts in the entity cache if it is enabled.
    *
    * @param balloonTexts the balloon texts
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.BalloonText> balloonTexts);

    /**
    * Creates a new balloon text with the primary key. Does not add the balloon text to the database.
    *
    * @param id the primary key for the new balloon text
    * @return the new balloon text
    */
    public com.ext.portlet.model.BalloonText create(long id);

    /**
    * Removes the balloon text with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the balloon text
    * @return the balloon text that was removed
    * @throws com.ext.portlet.NoSuchBalloonTextException if a balloon text with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.BalloonText remove(long id)
        throws com.ext.portlet.NoSuchBalloonTextException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.BalloonText updateImpl(
        com.ext.portlet.model.BalloonText balloonText)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the balloon text with the primary key or throws a {@link com.ext.portlet.NoSuchBalloonTextException} if it could not be found.
    *
    * @param id the primary key of the balloon text
    * @return the balloon text
    * @throws com.ext.portlet.NoSuchBalloonTextException if a balloon text with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.BalloonText findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchBalloonTextException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the balloon text with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the balloon text
    * @return the balloon text, or <code>null</code> if a balloon text with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.BalloonText fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the balloon texts.
    *
    * @return the balloon texts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.BalloonText> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.BalloonText> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the balloon texts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonTextModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of balloon texts
    * @param end the upper bound of the range of balloon texts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of balloon texts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.BalloonText> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the balloon texts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of balloon texts.
    *
    * @return the number of balloon texts
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
