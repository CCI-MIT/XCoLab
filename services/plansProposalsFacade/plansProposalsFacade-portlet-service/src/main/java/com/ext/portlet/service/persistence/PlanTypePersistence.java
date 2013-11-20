package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanType;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypePersistenceImpl
 * @see PlanTypeUtil
 * @generated
 */
public interface PlanTypePersistence extends BasePersistence<PlanType> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanTypeUtil} to access the plan type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns the plan type where isDefault = &#63; or throws a {@link com.ext.portlet.NoSuchPlanTypeException} if it could not be found.
    *
    * @param isDefault the is default
    * @return the matching plan type
    * @throws com.ext.portlet.NoSuchPlanTypeException if a matching plan type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanType findBydefault(boolean isDefault)
        throws com.ext.portlet.NoSuchPlanTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan type where isDefault = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param isDefault the is default
    * @return the matching plan type, or <code>null</code> if a matching plan type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanType fetchBydefault(boolean isDefault)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan type where isDefault = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param isDefault the is default
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan type, or <code>null</code> if a matching plan type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanType fetchBydefault(boolean isDefault,
        boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the plan type where isDefault = &#63; from the database.
    *
    * @param isDefault the is default
    * @return the plan type that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanType removeBydefault(boolean isDefault)
        throws com.ext.portlet.NoSuchPlanTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan types where isDefault = &#63;.
    *
    * @param isDefault the is default
    * @return the number of matching plan types
    * @throws SystemException if a system exception occurred
    */
    public int countBydefault(boolean isDefault)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the plan type in the entity cache if it is enabled.
    *
    * @param planType the plan type
    */
    public void cacheResult(com.ext.portlet.model.PlanType planType);

    /**
    * Caches the plan types in the entity cache if it is enabled.
    *
    * @param planTypes the plan types
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PlanType> planTypes);

    /**
    * Creates a new plan type with the primary key. Does not add the plan type to the database.
    *
    * @param planTypeId the primary key for the new plan type
    * @return the new plan type
    */
    public com.ext.portlet.model.PlanType create(long planTypeId);

    /**
    * Removes the plan type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planTypeId the primary key of the plan type
    * @return the plan type that was removed
    * @throws com.ext.portlet.NoSuchPlanTypeException if a plan type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanType remove(long planTypeId)
        throws com.ext.portlet.NoSuchPlanTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanType updateImpl(
        com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan type with the primary key or throws a {@link com.ext.portlet.NoSuchPlanTypeException} if it could not be found.
    *
    * @param planTypeId the primary key of the plan type
    * @return the plan type
    * @throws com.ext.portlet.NoSuchPlanTypeException if a plan type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanType findByPrimaryKey(long planTypeId)
        throws com.ext.portlet.NoSuchPlanTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planTypeId the primary key of the plan type
    * @return the plan type, or <code>null</code> if a plan type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanType fetchByPrimaryKey(long planTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan types.
    *
    * @return the plan types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan types
    * @param end the upper bound of the range of plan types (not inclusive)
    * @return the range of plan types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanType> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan types
    * @param end the upper bound of the range of plan types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanType> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan types.
    *
    * @return the number of plan types
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
