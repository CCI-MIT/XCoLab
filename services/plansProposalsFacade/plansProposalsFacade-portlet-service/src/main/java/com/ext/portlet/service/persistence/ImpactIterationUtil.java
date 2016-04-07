package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ImpactIteration;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the impact iteration service. This utility wraps {@link ImpactIterationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactIterationPersistence
 * @see ImpactIterationPersistenceImpl
 * @generated
 */
public class ImpactIterationUtil {
    private static ImpactIterationPersistence _persistence;

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
    public static void clearCache(ImpactIteration impactIteration) {
        getPersistence().clearCache(impactIteration);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<ImpactIteration> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ImpactIteration> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ImpactIteration> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ImpactIteration update(ImpactIteration impactIteration)
        throws SystemException {
        return getPersistence().update(impactIteration);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ImpactIteration update(ImpactIteration impactIteration,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(impactIteration, serviceContext);
    }

    /**
    * Returns all the impact iterations where iterationId = &#63;.
    *
    * @param iterationId the iteration ID
    * @return the matching impact iterations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactIteration> findByIterationId(
        long iterationId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByIterationId(iterationId);
    }

    /**
    * Returns a range of all the impact iterations where iterationId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactIterationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param iterationId the iteration ID
    * @param start the lower bound of the range of impact iterations
    * @param end the upper bound of the range of impact iterations (not inclusive)
    * @return the range of matching impact iterations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactIteration> findByIterationId(
        long iterationId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByIterationId(iterationId, start, end);
    }

    /**
    * Returns an ordered range of all the impact iterations where iterationId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactIterationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param iterationId the iteration ID
    * @param start the lower bound of the range of impact iterations
    * @param end the upper bound of the range of impact iterations (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching impact iterations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactIteration> findByIterationId(
        long iterationId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIterationId(iterationId, start, end, orderByComparator);
    }

    /**
    * Returns the first impact iteration in the ordered set where iterationId = &#63;.
    *
    * @param iterationId the iteration ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching impact iteration
    * @throws com.ext.portlet.NoSuchImpactIterationException if a matching impact iteration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactIteration findByIterationId_First(
        long iterationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactIterationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIterationId_First(iterationId, orderByComparator);
    }

    /**
    * Returns the first impact iteration in the ordered set where iterationId = &#63;.
    *
    * @param iterationId the iteration ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching impact iteration, or <code>null</code> if a matching impact iteration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactIteration fetchByIterationId_First(
        long iterationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByIterationId_First(iterationId, orderByComparator);
    }

    /**
    * Returns the last impact iteration in the ordered set where iterationId = &#63;.
    *
    * @param iterationId the iteration ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching impact iteration
    * @throws com.ext.portlet.NoSuchImpactIterationException if a matching impact iteration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactIteration findByIterationId_Last(
        long iterationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactIterationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIterationId_Last(iterationId, orderByComparator);
    }

    /**
    * Returns the last impact iteration in the ordered set where iterationId = &#63;.
    *
    * @param iterationId the iteration ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching impact iteration, or <code>null</code> if a matching impact iteration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactIteration fetchByIterationId_Last(
        long iterationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByIterationId_Last(iterationId, orderByComparator);
    }

    /**
    * Returns the impact iterations before and after the current impact iteration in the ordered set where iterationId = &#63;.
    *
    * @param impactIterationPK the primary key of the current impact iteration
    * @param iterationId the iteration ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next impact iteration
    * @throws com.ext.portlet.NoSuchImpactIterationException if a impact iteration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactIteration[] findByIterationId_PrevAndNext(
        ImpactIterationPK impactIterationPK, long iterationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactIterationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIterationId_PrevAndNext(impactIterationPK,
            iterationId, orderByComparator);
    }

    /**
    * Removes all the impact iterations where iterationId = &#63; from the database.
    *
    * @param iterationId the iteration ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByIterationId(long iterationId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByIterationId(iterationId);
    }

    /**
    * Returns the number of impact iterations where iterationId = &#63;.
    *
    * @param iterationId the iteration ID
    * @return the number of matching impact iterations
    * @throws SystemException if a system exception occurred
    */
    public static int countByIterationId(long iterationId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByIterationId(iterationId);
    }

    /**
    * Caches the impact iteration in the entity cache if it is enabled.
    *
    * @param impactIteration the impact iteration
    */
    public static void cacheResult(
        com.ext.portlet.model.ImpactIteration impactIteration) {
        getPersistence().cacheResult(impactIteration);
    }

    /**
    * Caches the impact iterations in the entity cache if it is enabled.
    *
    * @param impactIterations the impact iterations
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ImpactIteration> impactIterations) {
        getPersistence().cacheResult(impactIterations);
    }

    /**
    * Creates a new impact iteration with the primary key. Does not add the impact iteration to the database.
    *
    * @param impactIterationPK the primary key for the new impact iteration
    * @return the new impact iteration
    */
    public static com.ext.portlet.model.ImpactIteration create(
        ImpactIterationPK impactIterationPK) {
        return getPersistence().create(impactIterationPK);
    }

    /**
    * Removes the impact iteration with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param impactIterationPK the primary key of the impact iteration
    * @return the impact iteration that was removed
    * @throws com.ext.portlet.NoSuchImpactIterationException if a impact iteration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactIteration remove(
        ImpactIterationPK impactIterationPK)
        throws com.ext.portlet.NoSuchImpactIterationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(impactIterationPK);
    }

    public static com.ext.portlet.model.ImpactIteration updateImpl(
        com.ext.portlet.model.ImpactIteration impactIteration)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(impactIteration);
    }

    /**
    * Returns the impact iteration with the primary key or throws a {@link com.ext.portlet.NoSuchImpactIterationException} if it could not be found.
    *
    * @param impactIterationPK the primary key of the impact iteration
    * @return the impact iteration
    * @throws com.ext.portlet.NoSuchImpactIterationException if a impact iteration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactIteration findByPrimaryKey(
        ImpactIterationPK impactIterationPK)
        throws com.ext.portlet.NoSuchImpactIterationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(impactIterationPK);
    }

    /**
    * Returns the impact iteration with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param impactIterationPK the primary key of the impact iteration
    * @return the impact iteration, or <code>null</code> if a impact iteration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactIteration fetchByPrimaryKey(
        ImpactIterationPK impactIterationPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(impactIterationPK);
    }

    /**
    * Returns all the impact iterations.
    *
    * @return the impact iterations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactIteration> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the impact iterations.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactIterationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact iterations
    * @param end the upper bound of the range of impact iterations (not inclusive)
    * @return the range of impact iterations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactIteration> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the impact iterations.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactIterationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact iterations
    * @param end the upper bound of the range of impact iterations (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of impact iterations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactIteration> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the impact iterations from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of impact iterations.
    *
    * @return the number of impact iterations
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ImpactIterationPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ImpactIterationPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ImpactIterationPersistence.class.getName());

            ReferenceRegistry.registerReference(ImpactIterationUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ImpactIterationPersistence persistence) {
    }
}
