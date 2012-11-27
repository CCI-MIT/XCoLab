package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanRelated;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan related service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanRelatedPersistenceImpl
 * @see PlanRelatedUtil
 * @generated
 */
public interface PlanRelatedPersistence extends BasePersistence<PlanRelated> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanRelatedUtil} to access the plan related persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the plan related in the entity cache if it is enabled.
    *
    * @param planRelated the plan related
    */
    public void cacheResult(com.ext.portlet.plans.model.PlanRelated planRelated);

    /**
    * Caches the plan relateds in the entity cache if it is enabled.
    *
    * @param planRelateds the plan relateds
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanRelated> planRelateds);

    /**
    * Creates a new plan related with the primary key. Does not add the plan related to the database.
    *
    * @param planRelatedPK the primary key for the new plan related
    * @return the new plan related
    */
    public com.ext.portlet.plans.model.PlanRelated create(
        PlanRelatedPK planRelatedPK);

    /**
    * Removes the plan related with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planRelatedPK the primary key of the plan related
    * @return the plan related that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanRelatedException if a plan related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanRelated remove(
        PlanRelatedPK planRelatedPK)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanRelated updateImpl(
        com.ext.portlet.plans.model.PlanRelated planRelated, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan related with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanRelatedException} if it could not be found.
    *
    * @param planRelatedPK the primary key of the plan related
    * @return the plan related
    * @throws com.ext.portlet.plans.NoSuchPlanRelatedException if a plan related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanRelated findByPrimaryKey(
        PlanRelatedPK planRelatedPK)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan related with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planRelatedPK the primary key of the plan related
    * @return the plan related, or <code>null</code> if a plan related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanRelated fetchByPrimaryKey(
        PlanRelatedPK planRelatedPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan relateds where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @return the matching plan relateds
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanRelated> findByPlanId(
        java.lang.Long relatedPlanId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan relateds where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param relatedPlanId the related plan ID
    * @param start the lower bound of the range of plan relateds
    * @param end the upper bound of the range of plan relateds (not inclusive)
    * @return the range of matching plan relateds
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanRelated> findByPlanId(
        java.lang.Long relatedPlanId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan relateds where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param relatedPlanId the related plan ID
    * @param start the lower bound of the range of plan relateds
    * @param end the upper bound of the range of plan relateds (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan relateds
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanRelated> findByPlanId(
        java.lang.Long relatedPlanId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan related in the ordered set where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan related
    * @throws com.ext.portlet.plans.NoSuchPlanRelatedException if a matching plan related could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanRelated findByPlanId_First(
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan related in the ordered set where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan related
    * @throws com.ext.portlet.plans.NoSuchPlanRelatedException if a matching plan related could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanRelated findByPlanId_Last(
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan relateds before and after the current plan related in the ordered set where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planRelatedPK the primary key of the current plan related
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan related
    * @throws com.ext.portlet.plans.NoSuchPlanRelatedException if a plan related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanRelated[] findByPlanId_PrevAndNext(
        PlanRelatedPK planRelatedPK, java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan relateds.
    *
    * @return the plan relateds
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanRelated> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan relateds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan relateds
    * @param end the upper bound of the range of plan relateds (not inclusive)
    * @return the range of plan relateds
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanRelated> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan relateds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan relateds
    * @param end the upper bound of the range of plan relateds (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan relateds
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanRelated> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan relateds where relatedPlanId = &#63; from the database.
    *
    * @param relatedPlanId the related plan ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByPlanId(java.lang.Long relatedPlanId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan relateds from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan relateds where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @return the number of matching plan relateds
    * @throws SystemException if a system exception occurred
    */
    public int countByPlanId(java.lang.Long relatedPlanId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan relateds.
    *
    * @return the number of plan relateds
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
