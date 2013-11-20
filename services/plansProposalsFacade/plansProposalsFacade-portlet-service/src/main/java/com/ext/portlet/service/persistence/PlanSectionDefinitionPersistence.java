package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanSectionDefinition;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan section definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionDefinitionPersistenceImpl
 * @see PlanSectionDefinitionUtil
 * @generated
 */
public interface PlanSectionDefinitionPersistence extends BasePersistence<PlanSectionDefinition> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanSectionDefinitionUtil} to access the plan section definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the plan section definition in the entity cache if it is enabled.
    *
    * @param planSectionDefinition the plan section definition
    */
    public void cacheResult(
        com.ext.portlet.model.PlanSectionDefinition planSectionDefinition);

    /**
    * Caches the plan section definitions in the entity cache if it is enabled.
    *
    * @param planSectionDefinitions the plan section definitions
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PlanSectionDefinition> planSectionDefinitions);

    /**
    * Creates a new plan section definition with the primary key. Does not add the plan section definition to the database.
    *
    * @param id the primary key for the new plan section definition
    * @return the new plan section definition
    */
    public com.ext.portlet.model.PlanSectionDefinition create(long id);

    /**
    * Removes the plan section definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan section definition
    * @return the plan section definition that was removed
    * @throws com.ext.portlet.NoSuchPlanSectionDefinitionException if a plan section definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanSectionDefinition remove(long id)
        throws com.ext.portlet.NoSuchPlanSectionDefinitionException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanSectionDefinition updateImpl(
        com.ext.portlet.model.PlanSectionDefinition planSectionDefinition)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan section definition with the primary key or throws a {@link com.ext.portlet.NoSuchPlanSectionDefinitionException} if it could not be found.
    *
    * @param id the primary key of the plan section definition
    * @return the plan section definition
    * @throws com.ext.portlet.NoSuchPlanSectionDefinitionException if a plan section definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanSectionDefinition findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchPlanSectionDefinitionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan section definition with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan section definition
    * @return the plan section definition, or <code>null</code> if a plan section definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanSectionDefinition fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan section definitions.
    *
    * @return the plan section definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanSectionDefinition> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan section definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanSectionDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan section definitions
    * @param end the upper bound of the range of plan section definitions (not inclusive)
    * @return the range of plan section definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanSectionDefinition> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan section definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanSectionDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan section definitions
    * @param end the upper bound of the range of plan section definitions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan section definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanSectionDefinition> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan section definitions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan section definitions.
    *
    * @return the number of plan section definitions
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
