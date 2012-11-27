package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanSectionPlanMap;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan section plan map service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionPlanMapPersistenceImpl
 * @see PlanSectionPlanMapUtil
 * @generated
 */
public interface PlanSectionPlanMapPersistence extends BasePersistence<PlanSectionPlanMap> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanSectionPlanMapUtil} to access the plan section plan map persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the plan section plan map in the entity cache if it is enabled.
    *
    * @param planSectionPlanMap the plan section plan map
    */
    public void cacheResult(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap);

    /**
    * Caches the plan section plan maps in the entity cache if it is enabled.
    *
    * @param planSectionPlanMaps the plan section plan maps
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> planSectionPlanMaps);

    /**
    * Creates a new plan section plan map with the primary key. Does not add the plan section plan map to the database.
    *
    * @param planSectionPlanMapPK the primary key for the new plan section plan map
    * @return the new plan section plan map
    */
    public com.ext.portlet.plans.model.PlanSectionPlanMap create(
        PlanSectionPlanMapPK planSectionPlanMapPK);

    /**
    * Removes the plan section plan map with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planSectionPlanMapPK the primary key of the plan section plan map
    * @return the plan section plan map that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException if a plan section plan map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSectionPlanMap remove(
        PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanSectionPlanMap updateImpl(
        com.ext.portlet.plans.model.PlanSectionPlanMap planSectionPlanMap,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan section plan map with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanSectionPlanMapException} if it could not be found.
    *
    * @param planSectionPlanMapPK the primary key of the plan section plan map
    * @return the plan section plan map
    * @throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException if a plan section plan map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSectionPlanMap findByPrimaryKey(
        PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan section plan map with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planSectionPlanMapPK the primary key of the plan section plan map
    * @return the plan section plan map, or <code>null</code> if a plan section plan map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSectionPlanMap fetchByPrimaryKey(
        PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan section plan maps where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @return the matching plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findByPlanId(
        java.lang.Long relatedPlanId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan section plan maps where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param relatedPlanId the related plan ID
    * @param start the lower bound of the range of plan section plan maps
    * @param end the upper bound of the range of plan section plan maps (not inclusive)
    * @return the range of matching plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findByPlanId(
        java.lang.Long relatedPlanId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan section plan maps where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param relatedPlanId the related plan ID
    * @param start the lower bound of the range of plan section plan maps
    * @param end the upper bound of the range of plan section plan maps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findByPlanId(
        java.lang.Long relatedPlanId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan section plan map in the ordered set where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan section plan map
    * @throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException if a matching plan section plan map could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSectionPlanMap findByPlanId_First(
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan section plan map in the ordered set where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan section plan map
    * @throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException if a matching plan section plan map could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSectionPlanMap findByPlanId_Last(
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan section plan maps before and after the current plan section plan map in the ordered set where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planSectionPlanMapPK the primary key of the current plan section plan map
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan section plan map
    * @throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException if a plan section plan map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSectionPlanMap[] findByPlanId_PrevAndNext(
        PlanSectionPlanMapPK planSectionPlanMapPK,
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan section plan maps where sectionId = &#63;.
    *
    * @param sectionId the section ID
    * @return the matching plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findBySectionId(
        java.lang.Long sectionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan section plan maps where sectionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param sectionId the section ID
    * @param start the lower bound of the range of plan section plan maps
    * @param end the upper bound of the range of plan section plan maps (not inclusive)
    * @return the range of matching plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findBySectionId(
        java.lang.Long sectionId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan section plan maps where sectionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param sectionId the section ID
    * @param start the lower bound of the range of plan section plan maps
    * @param end the upper bound of the range of plan section plan maps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findBySectionId(
        java.lang.Long sectionId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan section plan map in the ordered set where sectionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param sectionId the section ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan section plan map
    * @throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException if a matching plan section plan map could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSectionPlanMap findBySectionId_First(
        java.lang.Long sectionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan section plan map in the ordered set where sectionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param sectionId the section ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan section plan map
    * @throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException if a matching plan section plan map could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSectionPlanMap findBySectionId_Last(
        java.lang.Long sectionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan section plan maps before and after the current plan section plan map in the ordered set where sectionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planSectionPlanMapPK the primary key of the current plan section plan map
    * @param sectionId the section ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan section plan map
    * @throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException if a plan section plan map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSectionPlanMap[] findBySectionId_PrevAndNext(
        PlanSectionPlanMapPK planSectionPlanMapPK, java.lang.Long sectionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan section plan maps.
    *
    * @return the plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan section plan maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan section plan maps
    * @param end the upper bound of the range of plan section plan maps (not inclusive)
    * @return the range of plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan section plan maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan section plan maps
    * @param end the upper bound of the range of plan section plan maps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanSectionPlanMap> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan section plan maps where relatedPlanId = &#63; from the database.
    *
    * @param relatedPlanId the related plan ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByPlanId(java.lang.Long relatedPlanId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan section plan maps where sectionId = &#63; from the database.
    *
    * @param sectionId the section ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBySectionId(java.lang.Long sectionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan section plan maps from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan section plan maps where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @return the number of matching plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public int countByPlanId(java.lang.Long relatedPlanId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan section plan maps where sectionId = &#63;.
    *
    * @param sectionId the section ID
    * @return the number of matching plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public int countBySectionId(java.lang.Long sectionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan section plan maps.
    *
    * @return the number of plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
