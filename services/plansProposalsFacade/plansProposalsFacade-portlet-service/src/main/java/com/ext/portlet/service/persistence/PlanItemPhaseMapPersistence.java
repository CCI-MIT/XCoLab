package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanItemPhaseMap;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan item phase map service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemPhaseMapPersistenceImpl
 * @see PlanItemPhaseMapUtil
 * @generated
 */
public interface PlanItemPhaseMapPersistence extends BasePersistence<PlanItemPhaseMap> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanItemPhaseMapUtil} to access the plan item phase map persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the plan item phase map in the entity cache if it is enabled.
    *
    * @param planItemPhaseMap the plan item phase map
    */
    public void cacheResult(
        com.ext.portlet.model.PlanItemPhaseMap planItemPhaseMap);

    /**
    * Caches the plan item phase maps in the entity cache if it is enabled.
    *
    * @param planItemPhaseMaps the plan item phase maps
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PlanItemPhaseMap> planItemPhaseMaps);

    /**
    * Creates a new plan item phase map with the primary key. Does not add the plan item phase map to the database.
    *
    * @param id the primary key for the new plan item phase map
    * @return the new plan item phase map
    */
    public com.ext.portlet.model.PlanItemPhaseMap create(long id);

    /**
    * Removes the plan item phase map with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan item phase map
    * @return the plan item phase map that was removed
    * @throws com.ext.portlet.NoSuchPlanItemPhaseMapException if a plan item phase map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemPhaseMap remove(long id)
        throws com.ext.portlet.NoSuchPlanItemPhaseMapException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanItemPhaseMap updateImpl(
        com.ext.portlet.model.PlanItemPhaseMap planItemPhaseMap, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan item phase map with the primary key or throws a {@link com.ext.portlet.NoSuchPlanItemPhaseMapException} if it could not be found.
    *
    * @param id the primary key of the plan item phase map
    * @return the plan item phase map
    * @throws com.ext.portlet.NoSuchPlanItemPhaseMapException if a plan item phase map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemPhaseMap findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchPlanItemPhaseMapException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan item phase map with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan item phase map
    * @return the plan item phase map, or <code>null</code> if a plan item phase map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemPhaseMap fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan item phase maps.
    *
    * @return the plan item phase maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanItemPhaseMap> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan item phase maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan item phase maps
    * @param end the upper bound of the range of plan item phase maps (not inclusive)
    * @return the range of plan item phase maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanItemPhaseMap> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan item phase maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan item phase maps
    * @param end the upper bound of the range of plan item phase maps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan item phase maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanItemPhaseMap> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan item phase maps from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan item phase maps.
    *
    * @return the number of plan item phase maps
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
