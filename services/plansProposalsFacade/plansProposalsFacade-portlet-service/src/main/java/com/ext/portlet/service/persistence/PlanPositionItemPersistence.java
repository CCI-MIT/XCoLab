package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanPositionItem;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan position item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionItemPersistenceImpl
 * @see PlanPositionItemUtil
 * @generated
 */
public interface PlanPositionItemPersistence extends BasePersistence<PlanPositionItem> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanPositionItemUtil} to access the plan position item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the plan position items where planPositionsId = &#63;.
    *
    * @param planPositionsId the plan positions ID
    * @return the matching plan position items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanPositionItem> findByAllByPlanPositionsId(
        long planPositionsId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan position items where planPositionsId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planPositionsId the plan positions ID
    * @param start the lower bound of the range of plan position items
    * @param end the upper bound of the range of plan position items (not inclusive)
    * @return the range of matching plan position items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanPositionItem> findByAllByPlanPositionsId(
        long planPositionsId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan position items where planPositionsId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planPositionsId the plan positions ID
    * @param start the lower bound of the range of plan position items
    * @param end the upper bound of the range of plan position items (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan position items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanPositionItem> findByAllByPlanPositionsId(
        long planPositionsId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan position item in the ordered set where planPositionsId = &#63;.
    *
    * @param planPositionsId the plan positions ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan position item
    * @throws com.ext.portlet.NoSuchPlanPositionItemException if a matching plan position item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPositionItem findByAllByPlanPositionsId_First(
        long planPositionsId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanPositionItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan position item in the ordered set where planPositionsId = &#63;.
    *
    * @param planPositionsId the plan positions ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan position item, or <code>null</code> if a matching plan position item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPositionItem fetchByAllByPlanPositionsId_First(
        long planPositionsId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan position item in the ordered set where planPositionsId = &#63;.
    *
    * @param planPositionsId the plan positions ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan position item
    * @throws com.ext.portlet.NoSuchPlanPositionItemException if a matching plan position item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPositionItem findByAllByPlanPositionsId_Last(
        long planPositionsId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanPositionItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan position item in the ordered set where planPositionsId = &#63;.
    *
    * @param planPositionsId the plan positions ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan position item, or <code>null</code> if a matching plan position item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPositionItem fetchByAllByPlanPositionsId_Last(
        long planPositionsId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan position items before and after the current plan position item in the ordered set where planPositionsId = &#63;.
    *
    * @param planPositionItemPK the primary key of the current plan position item
    * @param planPositionsId the plan positions ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan position item
    * @throws com.ext.portlet.NoSuchPlanPositionItemException if a plan position item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPositionItem[] findByAllByPlanPositionsId_PrevAndNext(
        com.ext.portlet.service.persistence.PlanPositionItemPK planPositionItemPK,
        long planPositionsId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanPositionItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan position items where planPositionsId = &#63; from the database.
    *
    * @param planPositionsId the plan positions ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByAllByPlanPositionsId(long planPositionsId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan position items where planPositionsId = &#63;.
    *
    * @param planPositionsId the plan positions ID
    * @return the number of matching plan position items
    * @throws SystemException if a system exception occurred
    */
    public int countByAllByPlanPositionsId(long planPositionsId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the plan position item in the entity cache if it is enabled.
    *
    * @param planPositionItem the plan position item
    */
    public void cacheResult(
        com.ext.portlet.model.PlanPositionItem planPositionItem);

    /**
    * Caches the plan position items in the entity cache if it is enabled.
    *
    * @param planPositionItems the plan position items
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PlanPositionItem> planPositionItems);

    /**
    * Creates a new plan position item with the primary key. Does not add the plan position item to the database.
    *
    * @param planPositionItemPK the primary key for the new plan position item
    * @return the new plan position item
    */
    public com.ext.portlet.model.PlanPositionItem create(
        com.ext.portlet.service.persistence.PlanPositionItemPK planPositionItemPK);

    /**
    * Removes the plan position item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planPositionItemPK the primary key of the plan position item
    * @return the plan position item that was removed
    * @throws com.ext.portlet.NoSuchPlanPositionItemException if a plan position item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPositionItem remove(
        com.ext.portlet.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.ext.portlet.NoSuchPlanPositionItemException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanPositionItem updateImpl(
        com.ext.portlet.model.PlanPositionItem planPositionItem)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan position item with the primary key or throws a {@link com.ext.portlet.NoSuchPlanPositionItemException} if it could not be found.
    *
    * @param planPositionItemPK the primary key of the plan position item
    * @return the plan position item
    * @throws com.ext.portlet.NoSuchPlanPositionItemException if a plan position item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPositionItem findByPrimaryKey(
        com.ext.portlet.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.ext.portlet.NoSuchPlanPositionItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan position item with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planPositionItemPK the primary key of the plan position item
    * @return the plan position item, or <code>null</code> if a plan position item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPositionItem fetchByPrimaryKey(
        com.ext.portlet.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan position items.
    *
    * @return the plan position items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanPositionItem> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan position items.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan position items
    * @param end the upper bound of the range of plan position items (not inclusive)
    * @return the range of plan position items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanPositionItem> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan position items.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan position items
    * @param end the upper bound of the range of plan position items (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan position items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanPositionItem> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan position items from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan position items.
    *
    * @return the number of plan position items
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
