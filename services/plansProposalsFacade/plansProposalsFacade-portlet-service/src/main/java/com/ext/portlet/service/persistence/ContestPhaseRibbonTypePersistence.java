package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestPhaseRibbonType;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the contest phase ribbon type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseRibbonTypePersistenceImpl
 * @see ContestPhaseRibbonTypeUtil
 * @generated
 */
public interface ContestPhaseRibbonTypePersistence extends BasePersistence<ContestPhaseRibbonType> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ContestPhaseRibbonTypeUtil} to access the contest phase ribbon type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the contest phase ribbon type in the entity cache if it is enabled.
    *
    * @param contestPhaseRibbonType the contest phase ribbon type
    */
    public void cacheResult(
        com.ext.portlet.model.ContestPhaseRibbonType contestPhaseRibbonType);

    /**
    * Caches the contest phase ribbon types in the entity cache if it is enabled.
    *
    * @param contestPhaseRibbonTypes the contest phase ribbon types
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ContestPhaseRibbonType> contestPhaseRibbonTypes);

    /**
    * Creates a new contest phase ribbon type with the primary key. Does not add the contest phase ribbon type to the database.
    *
    * @param id the primary key for the new contest phase ribbon type
    * @return the new contest phase ribbon type
    */
    public com.ext.portlet.model.ContestPhaseRibbonType create(long id);

    /**
    * Removes the contest phase ribbon type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest phase ribbon type
    * @return the contest phase ribbon type that was removed
    * @throws com.ext.portlet.NoSuchContestPhaseRibbonTypeException if a contest phase ribbon type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseRibbonType remove(long id)
        throws com.ext.portlet.NoSuchContestPhaseRibbonTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ContestPhaseRibbonType updateImpl(
        com.ext.portlet.model.ContestPhaseRibbonType contestPhaseRibbonType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest phase ribbon type with the primary key or throws a {@link com.ext.portlet.NoSuchContestPhaseRibbonTypeException} if it could not be found.
    *
    * @param id the primary key of the contest phase ribbon type
    * @return the contest phase ribbon type
    * @throws com.ext.portlet.NoSuchContestPhaseRibbonTypeException if a contest phase ribbon type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseRibbonType findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchContestPhaseRibbonTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest phase ribbon type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the contest phase ribbon type
    * @return the contest phase ribbon type, or <code>null</code> if a contest phase ribbon type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseRibbonType fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest phase ribbon types.
    *
    * @return the contest phase ribbon types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhaseRibbonType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest phase ribbon types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseRibbonTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest phase ribbon types
    * @param end the upper bound of the range of contest phase ribbon types (not inclusive)
    * @return the range of contest phase ribbon types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhaseRibbonType> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest phase ribbon types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseRibbonTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest phase ribbon types
    * @param end the upper bound of the range of contest phase ribbon types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contest phase ribbon types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhaseRibbonType> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest phase ribbon types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest phase ribbon types.
    *
    * @return the number of contest phase ribbon types
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
