package com.ext.portlet.service.persistence;

import com.ext.portlet.model.LandingPage;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the landing page service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LandingPagePersistenceImpl
 * @see LandingPageUtil
 * @generated
 */
public interface LandingPagePersistence extends BasePersistence<LandingPage> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link LandingPageUtil} to access the landing page persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the landing page in the entity cache if it is enabled.
    *
    * @param landingPage the landing page
    */
    public void cacheResult(com.ext.portlet.model.LandingPage landingPage);

    /**
    * Caches the landing pages in the entity cache if it is enabled.
    *
    * @param landingPages the landing pages
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.LandingPage> landingPages);

    /**
    * Creates a new landing page with the primary key. Does not add the landing page to the database.
    *
    * @param id the primary key for the new landing page
    * @return the new landing page
    */
    public com.ext.portlet.model.LandingPage create(long id);

    /**
    * Removes the landing page with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the landing page
    * @return the landing page that was removed
    * @throws com.ext.portlet.NoSuchLandingPageException if a landing page with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.LandingPage remove(long id)
        throws com.ext.portlet.NoSuchLandingPageException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.LandingPage updateImpl(
        com.ext.portlet.model.LandingPage landingPage, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the landing page with the primary key or throws a {@link com.ext.portlet.NoSuchLandingPageException} if it could not be found.
    *
    * @param id the primary key of the landing page
    * @return the landing page
    * @throws com.ext.portlet.NoSuchLandingPageException if a landing page with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.LandingPage findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchLandingPageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the landing page with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the landing page
    * @return the landing page, or <code>null</code> if a landing page with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.LandingPage fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the landing pages.
    *
    * @return the landing pages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.LandingPage> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the landing pages.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of landing pages
    * @param end the upper bound of the range of landing pages (not inclusive)
    * @return the range of landing pages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.LandingPage> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the landing pages.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of landing pages
    * @param end the upper bound of the range of landing pages (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of landing pages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.LandingPage> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the landing pages from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of landing pages.
    *
    * @return the number of landing pages
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
