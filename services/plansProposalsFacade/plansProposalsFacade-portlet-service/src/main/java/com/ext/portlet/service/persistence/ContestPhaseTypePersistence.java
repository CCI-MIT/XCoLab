package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestPhaseType;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the contest phase type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseTypePersistenceImpl
 * @see ContestPhaseTypeUtil
 * @generated
 */
public interface ContestPhaseTypePersistence extends BasePersistence<ContestPhaseType> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ContestPhaseTypeUtil} to access the contest phase type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the contest phase type in the entity cache if it is enabled.
    *
    * @param contestPhaseType the contest phase type
    */
    public void cacheResult(
        com.ext.portlet.model.ContestPhaseType contestPhaseType);

    /**
    * Caches the contest phase types in the entity cache if it is enabled.
    *
    * @param contestPhaseTypes the contest phase types
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ContestPhaseType> contestPhaseTypes);

    /**
    * Creates a new contest phase type with the primary key. Does not add the contest phase type to the database.
    *
    * @param id the primary key for the new contest phase type
    * @return the new contest phase type
    */
    public com.ext.portlet.model.ContestPhaseType create(long id);

    /**
    * Removes the contest phase type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest phase type
    * @return the contest phase type that was removed
    * @throws com.ext.portlet.NoSuchContestPhaseTypeException if a contest phase type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseType remove(long id)
        throws com.ext.portlet.NoSuchContestPhaseTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ContestPhaseType updateImpl(
        com.ext.portlet.model.ContestPhaseType contestPhaseType, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest phase type with the primary key or throws a {@link com.ext.portlet.NoSuchContestPhaseTypeException} if it could not be found.
    *
    * @param id the primary key of the contest phase type
    * @return the contest phase type
    * @throws com.ext.portlet.NoSuchContestPhaseTypeException if a contest phase type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseType findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchContestPhaseTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest phase type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the contest phase type
    * @return the contest phase type, or <code>null</code> if a contest phase type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseType fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest phase types.
    *
    * @return the contest phase types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhaseType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest phase types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest phase types
    * @param end the upper bound of the range of contest phase types (not inclusive)
    * @return the range of contest phase types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhaseType> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest phase types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest phase types
    * @param end the upper bound of the range of contest phase types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contest phase types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhaseType> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest phase types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest phase types.
    *
    * @return the number of contest phase types
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
