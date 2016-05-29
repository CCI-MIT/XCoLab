package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestType;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the contest type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestTypePersistenceImpl
 * @see ContestTypeUtil
 * @generated
 */
public interface ContestTypePersistence extends BasePersistence<ContestType> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ContestTypeUtil} to access the contest type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the contest type in the entity cache if it is enabled.
    *
    * @param contestType the contest type
    */
    public void cacheResult(com.ext.portlet.model.ContestType contestType);

    /**
    * Caches the contest types in the entity cache if it is enabled.
    *
    * @param contestTypes the contest types
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ContestType> contestTypes);

    /**
    * Creates a new contest type with the primary key. Does not add the contest type to the database.
    *
    * @param id the primary key for the new contest type
    * @return the new contest type
    */
    public com.ext.portlet.model.ContestType create(long id);

    /**
    * Removes the contest type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest type
    * @return the contest type that was removed
    * @throws com.ext.portlet.NoSuchContestTypeException if a contest type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestType remove(long id)
        throws com.ext.portlet.NoSuchContestTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ContestType updateImpl(
        com.ext.portlet.model.ContestType contestType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest type with the primary key or throws a {@link com.ext.portlet.NoSuchContestTypeException} if it could not be found.
    *
    * @param id the primary key of the contest type
    * @return the contest type
    * @throws com.ext.portlet.NoSuchContestTypeException if a contest type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestType findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchContestTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the contest type
    * @return the contest type, or <code>null</code> if a contest type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestType fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest types.
    *
    * @return the contest types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest types
    * @param end the upper bound of the range of contest types (not inclusive)
    * @return the range of contest types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestType> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest types
    * @param end the upper bound of the range of contest types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contest types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestType> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest types.
    *
    * @return the number of contest types
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
