package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanDescription;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan description service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanDescriptionPersistenceImpl
 * @see PlanDescriptionUtil
 * @generated
 */
public interface PlanDescriptionPersistence extends BasePersistence<PlanDescription> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanDescriptionUtil} to access the plan description persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns the plan description where planId = &#63; or throws a {@link com.ext.portlet.NoSuchPlanDescriptionException} if it could not be found.
    *
    * @param planId the plan ID
    * @return the matching plan description
    * @throws com.ext.portlet.NoSuchPlanDescriptionException if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription findByCurrentByPlanId(
        long planId)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan description where planId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @return the matching plan description, or <code>null</code> if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription fetchByCurrentByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan description where planId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan description, or <code>null</code> if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription fetchByCurrentByPlanId(
        long planId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the plan description where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @return the plan description that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription removeByCurrentByPlanId(
        long planId)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan descriptions where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public int countByCurrentByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan descriptions where planId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @return the matching plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanDescription> findByPlanIdPlanVersion(
        long planId, long planVersion)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan descriptions where planId = &#63; and planVersion &le; &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanDescriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param start the lower bound of the range of plan descriptions
    * @param end the upper bound of the range of plan descriptions (not inclusive)
    * @return the range of matching plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanDescription> findByPlanIdPlanVersion(
        long planId, long planVersion, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan descriptions where planId = &#63; and planVersion &le; &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanDescriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param start the lower bound of the range of plan descriptions
    * @param end the upper bound of the range of plan descriptions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanDescription> findByPlanIdPlanVersion(
        long planId, long planVersion, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan description in the ordered set where planId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan description
    * @throws com.ext.portlet.NoSuchPlanDescriptionException if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription findByPlanIdPlanVersion_First(
        long planId, long planVersion,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan description in the ordered set where planId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan description, or <code>null</code> if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription fetchByPlanIdPlanVersion_First(
        long planId, long planVersion,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan description in the ordered set where planId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan description
    * @throws com.ext.portlet.NoSuchPlanDescriptionException if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription findByPlanIdPlanVersion_Last(
        long planId, long planVersion,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan description in the ordered set where planId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan description, or <code>null</code> if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription fetchByPlanIdPlanVersion_Last(
        long planId, long planVersion,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan descriptions before and after the current plan description in the ordered set where planId = &#63; and planVersion &le; &#63;.
    *
    * @param id the primary key of the current plan description
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan description
    * @throws com.ext.portlet.NoSuchPlanDescriptionException if a plan description with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription[] findByPlanIdPlanVersion_PrevAndNext(
        long id, long planId, long planVersion,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan descriptions where planId = &#63; and planVersion &le; &#63; from the database.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @throws SystemException if a system exception occurred
    */
    public void removeByPlanIdPlanVersion(long planId, long planVersion)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan descriptions where planId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @return the number of matching plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public int countByPlanIdPlanVersion(long planId, long planVersion)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan descriptions where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanDescription> findByAllByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan descriptions where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanDescriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan descriptions
    * @param end the upper bound of the range of plan descriptions (not inclusive)
    * @return the range of matching plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanDescription> findByAllByPlanId(
        long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan descriptions where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanDescriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan descriptions
    * @param end the upper bound of the range of plan descriptions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanDescription> findByAllByPlanId(
        long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan description in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan description
    * @throws com.ext.portlet.NoSuchPlanDescriptionException if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription findByAllByPlanId_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan description in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan description, or <code>null</code> if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription fetchByAllByPlanId_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan description in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan description
    * @throws com.ext.portlet.NoSuchPlanDescriptionException if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription findByAllByPlanId_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan description in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan description, or <code>null</code> if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription fetchByAllByPlanId_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan descriptions before and after the current plan description in the ordered set where planId = &#63;.
    *
    * @param id the primary key of the current plan description
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan description
    * @throws com.ext.portlet.NoSuchPlanDescriptionException if a plan description with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription[] findByAllByPlanId_PrevAndNext(
        long id, long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan descriptions where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByAllByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan descriptions where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public int countByAllByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the plan description in the entity cache if it is enabled.
    *
    * @param planDescription the plan description
    */
    public void cacheResult(
        com.ext.portlet.model.PlanDescription planDescription);

    /**
    * Caches the plan descriptions in the entity cache if it is enabled.
    *
    * @param planDescriptions the plan descriptions
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PlanDescription> planDescriptions);

    /**
    * Creates a new plan description with the primary key. Does not add the plan description to the database.
    *
    * @param id the primary key for the new plan description
    * @return the new plan description
    */
    public com.ext.portlet.model.PlanDescription create(long id);

    /**
    * Removes the plan description with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan description
    * @return the plan description that was removed
    * @throws com.ext.portlet.NoSuchPlanDescriptionException if a plan description with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription remove(long id)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanDescription updateImpl(
        com.ext.portlet.model.PlanDescription planDescription)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan description with the primary key or throws a {@link com.ext.portlet.NoSuchPlanDescriptionException} if it could not be found.
    *
    * @param id the primary key of the plan description
    * @return the plan description
    * @throws com.ext.portlet.NoSuchPlanDescriptionException if a plan description with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan description with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan description
    * @return the plan description, or <code>null</code> if a plan description with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanDescription fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan descriptions.
    *
    * @return the plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanDescription> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan descriptions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanDescriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan descriptions
    * @param end the upper bound of the range of plan descriptions (not inclusive)
    * @return the range of plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanDescription> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan descriptions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanDescriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan descriptions
    * @param end the upper bound of the range of plan descriptions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanDescription> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan descriptions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan descriptions.
    *
    * @return the number of plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
