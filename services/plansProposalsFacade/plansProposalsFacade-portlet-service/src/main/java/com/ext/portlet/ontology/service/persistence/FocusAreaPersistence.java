package com.ext.portlet.ontology.service.persistence;

import com.ext.portlet.ontology.model.FocusArea;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the focus area service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaPersistenceImpl
 * @see FocusAreaUtil
 * @generated
 */
public interface FocusAreaPersistence extends BasePersistence<FocusArea> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link FocusAreaUtil} to access the focus area persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the focus area in the entity cache if it is enabled.
    *
    * @param focusArea the focus area
    */
    public void cacheResult(com.ext.portlet.ontology.model.FocusArea focusArea);

    /**
    * Caches the focus areas in the entity cache if it is enabled.
    *
    * @param focusAreas the focus areas
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.ontology.model.FocusArea> focusAreas);

    /**
    * Creates a new focus area with the primary key. Does not add the focus area to the database.
    *
    * @param id the primary key for the new focus area
    * @return the new focus area
    */
    public com.ext.portlet.ontology.model.FocusArea create(java.lang.Long id);

    /**
    * Removes the focus area with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the focus area
    * @return the focus area that was removed
    * @throws com.ext.portlet.ontology.NoSuchFocusAreaException if a focus area with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.FocusArea remove(java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchFocusAreaException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.ontology.model.FocusArea updateImpl(
        com.ext.portlet.ontology.model.FocusArea focusArea, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the focus area with the primary key or throws a {@link com.ext.portlet.ontology.NoSuchFocusAreaException} if it could not be found.
    *
    * @param id the primary key of the focus area
    * @return the focus area
    * @throws com.ext.portlet.ontology.NoSuchFocusAreaException if a focus area with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.FocusArea findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchFocusAreaException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the focus area with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the focus area
    * @return the focus area, or <code>null</code> if a focus area with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.FocusArea fetchByPrimaryKey(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the focus area where name = &#63; or throws a {@link com.ext.portlet.ontology.NoSuchFocusAreaException} if it could not be found.
    *
    * @param name the name
    * @return the matching focus area
    * @throws com.ext.portlet.ontology.NoSuchFocusAreaException if a matching focus area could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.FocusArea findByName(
        java.lang.String name)
        throws com.ext.portlet.ontology.NoSuchFocusAreaException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the focus area where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param name the name
    * @return the matching focus area, or <code>null</code> if a matching focus area could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.FocusArea fetchByName(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the focus area where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param name the name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching focus area, or <code>null</code> if a matching focus area could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.FocusArea fetchByName(
        java.lang.String name, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the focus areas.
    *
    * @return the focus areas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.FocusArea> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the focus areas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of focus areas
    * @param end the upper bound of the range of focus areas (not inclusive)
    * @return the range of focus areas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.FocusArea> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the focus areas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of focus areas
    * @param end the upper bound of the range of focus areas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of focus areas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.FocusArea> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the focus area where name = &#63; from the database.
    *
    * @param name the name
    * @throws SystemException if a system exception occurred
    */
    public void removeByName(java.lang.String name)
        throws com.ext.portlet.ontology.NoSuchFocusAreaException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the focus areas from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of focus areas where name = &#63;.
    *
    * @param name the name
    * @return the number of matching focus areas
    * @throws SystemException if a system exception occurred
    */
    public int countByName(java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of focus areas.
    *
    * @return the number of focus areas
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
