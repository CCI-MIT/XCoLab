package com.ext.portlet.service.persistence;

import com.ext.portlet.model.BalloonLink;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the balloon link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BalloonLinkPersistenceImpl
 * @see BalloonLinkUtil
 * @generated
 */
public interface BalloonLinkPersistence extends BasePersistence<BalloonLink> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link BalloonLinkUtil} to access the balloon link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the balloon links where balloonUserUuid = &#63;.
    *
    * @param balloonUserUuid the balloon user uuid
    * @return the matching balloon links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.BalloonLink> findByBalloonUserUuid(
        java.lang.String balloonUserUuid)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the balloon links where balloonUserUuid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param balloonUserUuid the balloon user uuid
    * @param start the lower bound of the range of balloon links
    * @param end the upper bound of the range of balloon links (not inclusive)
    * @return the range of matching balloon links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.BalloonLink> findByBalloonUserUuid(
        java.lang.String balloonUserUuid, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the balloon links where balloonUserUuid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param balloonUserUuid the balloon user uuid
    * @param start the lower bound of the range of balloon links
    * @param end the upper bound of the range of balloon links (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching balloon links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.BalloonLink> findByBalloonUserUuid(
        java.lang.String balloonUserUuid, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first balloon link in the ordered set where balloonUserUuid = &#63;.
    *
    * @param balloonUserUuid the balloon user uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching balloon link
    * @throws com.ext.portlet.NoSuchBalloonLinkException if a matching balloon link could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.BalloonLink findByBalloonUserUuid_First(
        java.lang.String balloonUserUuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchBalloonLinkException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first balloon link in the ordered set where balloonUserUuid = &#63;.
    *
    * @param balloonUserUuid the balloon user uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching balloon link, or <code>null</code> if a matching balloon link could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.BalloonLink fetchByBalloonUserUuid_First(
        java.lang.String balloonUserUuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last balloon link in the ordered set where balloonUserUuid = &#63;.
    *
    * @param balloonUserUuid the balloon user uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching balloon link
    * @throws com.ext.portlet.NoSuchBalloonLinkException if a matching balloon link could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.BalloonLink findByBalloonUserUuid_Last(
        java.lang.String balloonUserUuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchBalloonLinkException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last balloon link in the ordered set where balloonUserUuid = &#63;.
    *
    * @param balloonUserUuid the balloon user uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching balloon link, or <code>null</code> if a matching balloon link could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.BalloonLink fetchByBalloonUserUuid_Last(
        java.lang.String balloonUserUuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the balloon links before and after the current balloon link in the ordered set where balloonUserUuid = &#63;.
    *
    * @param uuid the primary key of the current balloon link
    * @param balloonUserUuid the balloon user uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next balloon link
    * @throws com.ext.portlet.NoSuchBalloonLinkException if a balloon link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.BalloonLink[] findByBalloonUserUuid_PrevAndNext(
        java.lang.String uuid, java.lang.String balloonUserUuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchBalloonLinkException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the balloon links where balloonUserUuid = &#63; from the database.
    *
    * @param balloonUserUuid the balloon user uuid
    * @throws SystemException if a system exception occurred
    */
    public void removeByBalloonUserUuid(java.lang.String balloonUserUuid)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of balloon links where balloonUserUuid = &#63;.
    *
    * @param balloonUserUuid the balloon user uuid
    * @return the number of matching balloon links
    * @throws SystemException if a system exception occurred
    */
    public int countByBalloonUserUuid(java.lang.String balloonUserUuid)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the balloon link in the entity cache if it is enabled.
    *
    * @param balloonLink the balloon link
    */
    public void cacheResult(com.ext.portlet.model.BalloonLink balloonLink);

    /**
    * Caches the balloon links in the entity cache if it is enabled.
    *
    * @param balloonLinks the balloon links
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.BalloonLink> balloonLinks);

    /**
    * Creates a new balloon link with the primary key. Does not add the balloon link to the database.
    *
    * @param uuid the primary key for the new balloon link
    * @return the new balloon link
    */
    public com.ext.portlet.model.BalloonLink create(java.lang.String uuid);

    /**
    * Removes the balloon link with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param uuid the primary key of the balloon link
    * @return the balloon link that was removed
    * @throws com.ext.portlet.NoSuchBalloonLinkException if a balloon link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.BalloonLink remove(java.lang.String uuid)
        throws com.ext.portlet.NoSuchBalloonLinkException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.BalloonLink updateImpl(
        com.ext.portlet.model.BalloonLink balloonLink)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the balloon link with the primary key or throws a {@link com.ext.portlet.NoSuchBalloonLinkException} if it could not be found.
    *
    * @param uuid the primary key of the balloon link
    * @return the balloon link
    * @throws com.ext.portlet.NoSuchBalloonLinkException if a balloon link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.BalloonLink findByPrimaryKey(
        java.lang.String uuid)
        throws com.ext.portlet.NoSuchBalloonLinkException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the balloon link with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param uuid the primary key of the balloon link
    * @return the balloon link, or <code>null</code> if a balloon link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.BalloonLink fetchByPrimaryKey(
        java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the balloon links.
    *
    * @return the balloon links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.BalloonLink> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.BalloonLink> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the balloon links.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of balloon links
    * @param end the upper bound of the range of balloon links (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of balloon links
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.BalloonLink> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the balloon links from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of balloon links.
    *
    * @return the number of balloon links
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
