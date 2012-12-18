package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanPositionItem;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan position item service. This utility wraps {@link PlanPositionItemPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionItemPersistence
 * @see PlanPositionItemPersistenceImpl
 * @generated
 */
public class PlanPositionItemUtil {
    private static PlanPositionItemPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(PlanPositionItem planPositionItem) {
        getPersistence().clearCache(planPositionItem);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<PlanPositionItem> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanPositionItem> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanPositionItem> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static PlanPositionItem update(PlanPositionItem planPositionItem,
        boolean merge) throws SystemException {
        return getPersistence().update(planPositionItem, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static PlanPositionItem update(PlanPositionItem planPositionItem,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planPositionItem, merge, serviceContext);
    }

    /**
    * Caches the plan position item in the entity cache if it is enabled.
    *
    * @param planPositionItem the plan position item
    */
    public static void cacheResult(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem) {
        getPersistence().cacheResult(planPositionItem);
    }

    /**
    * Caches the plan position items in the entity cache if it is enabled.
    *
    * @param planPositionItems the plan position items
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanPositionItem> planPositionItems) {
        getPersistence().cacheResult(planPositionItems);
    }

    /**
    * Creates a new plan position item with the primary key. Does not add the plan position item to the database.
    *
    * @param planPositionItemPK the primary key for the new plan position item
    * @return the new plan position item
    */
    public static com.ext.portlet.plans.model.PlanPositionItem create(
        PlanPositionItemPK planPositionItemPK) {
        return getPersistence().create(planPositionItemPK);
    }

    /**
    * Removes the plan position item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planPositionItemPK the primary key of the plan position item
    * @return the plan position item that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanPositionItemException if a plan position item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPositionItem remove(
        PlanPositionItemPK planPositionItemPK)
        throws com.ext.portlet.plans.NoSuchPlanPositionItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(planPositionItemPK);
    }

    public static com.ext.portlet.plans.model.PlanPositionItem updateImpl(
        com.ext.portlet.plans.model.PlanPositionItem planPositionItem,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planPositionItem, merge);
    }

    /**
    * Returns the plan position item with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanPositionItemException} if it could not be found.
    *
    * @param planPositionItemPK the primary key of the plan position item
    * @return the plan position item
    * @throws com.ext.portlet.plans.NoSuchPlanPositionItemException if a plan position item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPositionItem findByPrimaryKey(
        PlanPositionItemPK planPositionItemPK)
        throws com.ext.portlet.plans.NoSuchPlanPositionItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(planPositionItemPK);
    }

    /**
    * Returns the plan position item with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planPositionItemPK the primary key of the plan position item
    * @return the plan position item, or <code>null</code> if a plan position item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPositionItem fetchByPrimaryKey(
        PlanPositionItemPK planPositionItemPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(planPositionItemPK);
    }

    /**
    * Returns all the plan position items where planPositionsId = &#63;.
    *
    * @param planPositionsId the plan positions ID
    * @return the matching plan position items
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findByAllByPlanPositionsId(
        long planPositionsId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanPositionsId(planPositionsId);
    }

    /**
    * Returns a range of all the plan position items where planPositionsId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planPositionsId the plan positions ID
    * @param start the lower bound of the range of plan position items
    * @param end the upper bound of the range of plan position items (not inclusive)
    * @return the range of matching plan position items
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findByAllByPlanPositionsId(
        long planPositionsId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanPositionsId(planPositionsId, start, end);
    }

    /**
    * Returns an ordered range of all the plan position items where planPositionsId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planPositionsId the plan positions ID
    * @param start the lower bound of the range of plan position items
    * @param end the upper bound of the range of plan position items (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan position items
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findByAllByPlanPositionsId(
        long planPositionsId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanPositionsId(planPositionsId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first plan position item in the ordered set where planPositionsId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planPositionsId the plan positions ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan position item
    * @throws com.ext.portlet.plans.NoSuchPlanPositionItemException if a matching plan position item could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPositionItem findByAllByPlanPositionsId_First(
        long planPositionsId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanPositionItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanPositionsId_First(planPositionsId,
            orderByComparator);
    }

    /**
    * Returns the last plan position item in the ordered set where planPositionsId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planPositionsId the plan positions ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan position item
    * @throws com.ext.portlet.plans.NoSuchPlanPositionItemException if a matching plan position item could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPositionItem findByAllByPlanPositionsId_Last(
        long planPositionsId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanPositionItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanPositionsId_Last(planPositionsId,
            orderByComparator);
    }

    /**
    * Returns the plan position items before and after the current plan position item in the ordered set where planPositionsId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planPositionItemPK the primary key of the current plan position item
    * @param planPositionsId the plan positions ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan position item
    * @throws com.ext.portlet.plans.NoSuchPlanPositionItemException if a plan position item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPositionItem[] findByAllByPlanPositionsId_PrevAndNext(
        PlanPositionItemPK planPositionItemPK, long planPositionsId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanPositionItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanPositionsId_PrevAndNext(planPositionItemPK,
            planPositionsId, orderByComparator);
    }

    /**
    * Returns all the plan position items.
    *
    * @return the plan position items
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan position items.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan position items
    * @param end the upper bound of the range of plan position items (not inclusive)
    * @return the range of plan position items
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan position items.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan position items
    * @param end the upper bound of the range of plan position items (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan position items
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanPositionItem> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the plan position items where planPositionsId = &#63; from the database.
    *
    * @param planPositionsId the plan positions ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByAllByPlanPositionsId(long planPositionsId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByAllByPlanPositionsId(planPositionsId);
    }

    /**
    * Removes all the plan position items from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan position items where planPositionsId = &#63;.
    *
    * @param planPositionsId the plan positions ID
    * @return the number of matching plan position items
    * @throws SystemException if a system exception occurred
    */
    public static int countByAllByPlanPositionsId(long planPositionsId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByAllByPlanPositionsId(planPositionsId);
    }

    /**
    * Returns the number of plan position items.
    *
    * @return the number of plan position items
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanPositionItemPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanPositionItemPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.plans.service.ClpSerializer.getServletContextName(),
                    PlanPositionItemPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanPositionItemUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(PlanPositionItemPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(PlanPositionItemUtil.class,
            "_persistence");
    }
}
