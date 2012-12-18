package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanSection;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan section service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionPersistenceImpl
 * @see PlanSectionUtil
 * @generated
 */
public interface PlanSectionPersistence extends BasePersistence<PlanSection> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanSectionUtil} to access the plan section persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the plan section in the entity cache if it is enabled.
    *
    * @param planSection the plan section
    */
    public void cacheResult(com.ext.portlet.plans.model.PlanSection planSection);

    /**
    * Caches the plan sections in the entity cache if it is enabled.
    *
    * @param planSections the plan sections
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanSection> planSections);

    /**
    * Creates a new plan section with the primary key. Does not add the plan section to the database.
    *
    * @param id the primary key for the new plan section
    * @return the new plan section
    */
    public com.ext.portlet.plans.model.PlanSection create(long id);

    /**
    * Removes the plan section with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan section
    * @return the plan section that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanSectionException if a plan section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSection remove(long id)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanSection updateImpl(
        com.ext.portlet.plans.model.PlanSection planSection, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan section with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanSectionException} if it could not be found.
    *
    * @param id the primary key of the plan section
    * @return the plan section
    * @throws com.ext.portlet.plans.NoSuchPlanSectionException if a plan section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSection findByPrimaryKey(long id)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan section with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan section
    * @return the plan section, or <code>null</code> if a plan section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSection fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan sections where planId = &#63; and planSectionDefinitionId = &#63;.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @return the matching plan sections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanSection> findByPlanIdSectionDefinitionId(
        long planId, long planSectionDefinitionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan sections where planId = &#63; and planSectionDefinitionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param start the lower bound of the range of plan sections
    * @param end the upper bound of the range of plan sections (not inclusive)
    * @return the range of matching plan sections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanSection> findByPlanIdSectionDefinitionId(
        long planId, long planSectionDefinitionId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan sections where planId = &#63; and planSectionDefinitionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param start the lower bound of the range of plan sections
    * @param end the upper bound of the range of plan sections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan sections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanSection> findByPlanIdSectionDefinitionId(
        long planId, long planSectionDefinitionId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan section in the ordered set where planId = &#63; and planSectionDefinitionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan section
    * @throws com.ext.portlet.plans.NoSuchPlanSectionException if a matching plan section could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSection findByPlanIdSectionDefinitionId_First(
        long planId, long planSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan section in the ordered set where planId = &#63; and planSectionDefinitionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan section
    * @throws com.ext.portlet.plans.NoSuchPlanSectionException if a matching plan section could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSection findByPlanIdSectionDefinitionId_Last(
        long planId, long planSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan sections before and after the current plan section in the ordered set where planId = &#63; and planSectionDefinitionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current plan section
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan section
    * @throws com.ext.portlet.plans.NoSuchPlanSectionException if a plan section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSection[] findByPlanIdSectionDefinitionId_PrevAndNext(
        long id, long planId, long planSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan section where planId = &#63; and planSectionDefinitionId = &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanSectionException} if it could not be found.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @return the matching plan section
    * @throws com.ext.portlet.plans.NoSuchPlanSectionException if a matching plan section could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSection findByCurrentPlanIdSectionDefinitionId(
        long planId, long planSectionDefinitionId)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan section where planId = &#63; and planSectionDefinitionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @return the matching plan section, or <code>null</code> if a matching plan section could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSection fetchByCurrentPlanIdSectionDefinitionId(
        long planId, long planSectionDefinitionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan section where planId = &#63; and planSectionDefinitionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan section, or <code>null</code> if a matching plan section could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSection fetchByCurrentPlanIdSectionDefinitionId(
        long planId, long planSectionDefinitionId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan section where planId = &#63; and planSectionDefinitionId = &#63; and planVersion &le; &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanSectionException} if it could not be found.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param planVersion the plan version
    * @return the matching plan section
    * @throws com.ext.portlet.plans.NoSuchPlanSectionException if a matching plan section could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSection findByPlanIdPlanVersion(
        long planId, long planSectionDefinitionId, long planVersion)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan section where planId = &#63; and planSectionDefinitionId = &#63; and planVersion &le; &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param planVersion the plan version
    * @return the matching plan section, or <code>null</code> if a matching plan section could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSection fetchByPlanIdPlanVersion(
        long planId, long planSectionDefinitionId, long planVersion)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan section where planId = &#63; and planSectionDefinitionId = &#63; and planVersion &le; &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param planVersion the plan version
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan section, or <code>null</code> if a matching plan section could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSection fetchByPlanIdPlanVersion(
        long planId, long planSectionDefinitionId, long planVersion,
        boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan sections.
    *
    * @return the plan sections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanSection> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan sections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan sections
    * @param end the upper bound of the range of plan sections (not inclusive)
    * @return the range of plan sections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanSection> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan sections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan sections
    * @param end the upper bound of the range of plan sections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan sections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanSection> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan sections where planId = &#63; and planSectionDefinitionId = &#63; from the database.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByPlanIdSectionDefinitionId(long planId,
        long planSectionDefinitionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the plan section where planId = &#63; and planSectionDefinitionId = &#63; from the database.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCurrentPlanIdSectionDefinitionId(long planId,
        long planSectionDefinitionId)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the plan section where planId = &#63; and planSectionDefinitionId = &#63; and planVersion &le; &#63; from the database.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param planVersion the plan version
    * @throws SystemException if a system exception occurred
    */
    public void removeByPlanIdPlanVersion(long planId,
        long planSectionDefinitionId, long planVersion)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan sections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan sections where planId = &#63; and planSectionDefinitionId = &#63;.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @return the number of matching plan sections
    * @throws SystemException if a system exception occurred
    */
    public int countByPlanIdSectionDefinitionId(long planId,
        long planSectionDefinitionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan sections where planId = &#63; and planSectionDefinitionId = &#63;.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @return the number of matching plan sections
    * @throws SystemException if a system exception occurred
    */
    public int countByCurrentPlanIdSectionDefinitionId(long planId,
        long planSectionDefinitionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan sections where planId = &#63; and planSectionDefinitionId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param planVersion the plan version
    * @return the number of matching plan sections
    * @throws SystemException if a system exception occurred
    */
    public int countByPlanIdPlanVersion(long planId,
        long planSectionDefinitionId, long planVersion)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan sections.
    *
    * @return the number of plan sections
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
