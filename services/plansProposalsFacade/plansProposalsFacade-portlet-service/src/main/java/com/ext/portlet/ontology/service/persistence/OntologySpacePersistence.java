package com.ext.portlet.ontology.service.persistence;

import com.ext.portlet.ontology.model.OntologySpace;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ontology space service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OntologySpacePersistenceImpl
 * @see OntologySpaceUtil
 * @generated
 */
public interface OntologySpacePersistence extends BasePersistence<OntologySpace> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link OntologySpaceUtil} to access the ontology space persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ontology space in the entity cache if it is enabled.
    *
    * @param ontologySpace the ontology space
    */
    public void cacheResult(
        com.ext.portlet.ontology.model.OntologySpace ontologySpace);

    /**
    * Caches the ontology spaces in the entity cache if it is enabled.
    *
    * @param ontologySpaces the ontology spaces
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.ontology.model.OntologySpace> ontologySpaces);

    /**
    * Creates a new ontology space with the primary key. Does not add the ontology space to the database.
    *
    * @param id the primary key for the new ontology space
    * @return the new ontology space
    */
    public com.ext.portlet.ontology.model.OntologySpace create(long id);

    /**
    * Removes the ontology space with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the ontology space
    * @return the ontology space that was removed
    * @throws com.ext.portlet.ontology.NoSuchOntologySpaceException if a ontology space with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologySpace remove(long id)
        throws com.ext.portlet.ontology.NoSuchOntologySpaceException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.ontology.model.OntologySpace updateImpl(
        com.ext.portlet.ontology.model.OntologySpace ontologySpace,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology space with the primary key or throws a {@link com.ext.portlet.ontology.NoSuchOntologySpaceException} if it could not be found.
    *
    * @param id the primary key of the ontology space
    * @return the ontology space
    * @throws com.ext.portlet.ontology.NoSuchOntologySpaceException if a ontology space with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologySpace findByPrimaryKey(
        long id)
        throws com.ext.portlet.ontology.NoSuchOntologySpaceException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology space with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the ontology space
    * @return the ontology space, or <code>null</code> if a ontology space with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologySpace fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology space where name = &#63; or throws a {@link com.ext.portlet.ontology.NoSuchOntologySpaceException} if it could not be found.
    *
    * @param name the name
    * @return the matching ontology space
    * @throws com.ext.portlet.ontology.NoSuchOntologySpaceException if a matching ontology space could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologySpace findByName(
        java.lang.String name)
        throws com.ext.portlet.ontology.NoSuchOntologySpaceException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology space where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param name the name
    * @return the matching ontology space, or <code>null</code> if a matching ontology space could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologySpace fetchByName(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ontology space where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param name the name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching ontology space, or <code>null</code> if a matching ontology space could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologySpace fetchByName(
        java.lang.String name, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ontology spaces.
    *
    * @return the ontology spaces
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologySpace> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ontology spaces.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of ontology spaces
    * @param end the upper bound of the range of ontology spaces (not inclusive)
    * @return the range of ontology spaces
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologySpace> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ontology spaces.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of ontology spaces
    * @param end the upper bound of the range of ontology spaces (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ontology spaces
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologySpace> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the ontology space where name = &#63; from the database.
    *
    * @param name the name
    * @throws SystemException if a system exception occurred
    */
    public void removeByName(java.lang.String name)
        throws com.ext.portlet.ontology.NoSuchOntologySpaceException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ontology spaces from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ontology spaces where name = &#63;.
    *
    * @param name the name
    * @return the number of matching ontology spaces
    * @throws SystemException if a system exception occurred
    */
    public int countByName(java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ontology spaces.
    *
    * @return the number of ontology spaces
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
