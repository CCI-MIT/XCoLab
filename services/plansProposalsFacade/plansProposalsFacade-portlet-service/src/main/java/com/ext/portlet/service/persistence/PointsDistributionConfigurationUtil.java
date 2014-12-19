package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PointsDistributionConfiguration;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the points distribution configuration service. This utility wraps {@link PointsDistributionConfigurationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PointsDistributionConfigurationPersistence
 * @see PointsDistributionConfigurationPersistenceImpl
 * @generated
 */
public class PointsDistributionConfigurationUtil {
    private static PointsDistributionConfigurationPersistence _persistence;

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
    public static void clearCache(
        PointsDistributionConfiguration pointsDistributionConfiguration) {
        getPersistence().clearCache(pointsDistributionConfiguration);
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
    public static List<PointsDistributionConfiguration> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PointsDistributionConfiguration> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PointsDistributionConfiguration> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static PointsDistributionConfiguration update(
        PointsDistributionConfiguration pointsDistributionConfiguration)
        throws SystemException {
        return getPersistence().update(pointsDistributionConfiguration);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static PointsDistributionConfiguration update(
        PointsDistributionConfiguration pointsDistributionConfiguration,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(pointsDistributionConfiguration, serviceContext);
    }

    /**
    * Returns all the points distribution configurations where targetUserId = &#63;.
    *
    * @param targetUserId the target user ID
    * @return the matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByTargetUserId(
        long targetUserId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTargetUserId(targetUserId);
    }

    /**
    * Returns a range of all the points distribution configurations where targetUserId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetUserId the target user ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @return the range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByTargetUserId(
        long targetUserId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTargetUserId(targetUserId, start, end);
    }

    /**
    * Returns an ordered range of all the points distribution configurations where targetUserId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetUserId the target user ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByTargetUserId(
        long targetUserId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetUserId(targetUserId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first points distribution configuration in the ordered set where targetUserId = &#63;.
    *
    * @param targetUserId the target user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration findByTargetUserId_First(
        long targetUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetUserId_First(targetUserId, orderByComparator);
    }

    /**
    * Returns the first points distribution configuration in the ordered set where targetUserId = &#63;.
    *
    * @param targetUserId the target user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration fetchByTargetUserId_First(
        long targetUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTargetUserId_First(targetUserId, orderByComparator);
    }

    /**
    * Returns the last points distribution configuration in the ordered set where targetUserId = &#63;.
    *
    * @param targetUserId the target user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration findByTargetUserId_Last(
        long targetUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetUserId_Last(targetUserId, orderByComparator);
    }

    /**
    * Returns the last points distribution configuration in the ordered set where targetUserId = &#63;.
    *
    * @param targetUserId the target user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration fetchByTargetUserId_Last(
        long targetUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTargetUserId_Last(targetUserId, orderByComparator);
    }

    /**
    * Returns the points distribution configurations before and after the current points distribution configuration in the ordered set where targetUserId = &#63;.
    *
    * @param id the primary key of the current points distribution configuration
    * @param targetUserId the target user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration[] findByTargetUserId_PrevAndNext(
        long id, long targetUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetUserId_PrevAndNext(id, targetUserId,
            orderByComparator);
    }

    /**
    * Removes all the points distribution configurations where targetUserId = &#63; from the database.
    *
    * @param targetUserId the target user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByTargetUserId(long targetUserId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByTargetUserId(targetUserId);
    }

    /**
    * Returns the number of points distribution configurations where targetUserId = &#63;.
    *
    * @param targetUserId the target user ID
    * @return the number of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static int countByTargetUserId(long targetUserId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByTargetUserId(targetUserId);
    }

    /**
    * Returns all the points distribution configurations where targetSubProposalId = &#63;.
    *
    * @param targetSubProposalId the target sub proposal ID
    * @return the matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByTargetSubProposalId(
        long targetSubProposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTargetSubProposalId(targetSubProposalId);
    }

    /**
    * Returns a range of all the points distribution configurations where targetSubProposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetSubProposalId the target sub proposal ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @return the range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByTargetSubProposalId(
        long targetSubProposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetSubProposalId(targetSubProposalId, start, end);
    }

    /**
    * Returns an ordered range of all the points distribution configurations where targetSubProposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetSubProposalId the target sub proposal ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByTargetSubProposalId(
        long targetSubProposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetSubProposalId(targetSubProposalId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first points distribution configuration in the ordered set where targetSubProposalId = &#63;.
    *
    * @param targetSubProposalId the target sub proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration findByTargetSubProposalId_First(
        long targetSubProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetSubProposalId_First(targetSubProposalId,
            orderByComparator);
    }

    /**
    * Returns the first points distribution configuration in the ordered set where targetSubProposalId = &#63;.
    *
    * @param targetSubProposalId the target sub proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration fetchByTargetSubProposalId_First(
        long targetSubProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTargetSubProposalId_First(targetSubProposalId,
            orderByComparator);
    }

    /**
    * Returns the last points distribution configuration in the ordered set where targetSubProposalId = &#63;.
    *
    * @param targetSubProposalId the target sub proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration findByTargetSubProposalId_Last(
        long targetSubProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetSubProposalId_Last(targetSubProposalId,
            orderByComparator);
    }

    /**
    * Returns the last points distribution configuration in the ordered set where targetSubProposalId = &#63;.
    *
    * @param targetSubProposalId the target sub proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration fetchByTargetSubProposalId_Last(
        long targetSubProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTargetSubProposalId_Last(targetSubProposalId,
            orderByComparator);
    }

    /**
    * Returns the points distribution configurations before and after the current points distribution configuration in the ordered set where targetSubProposalId = &#63;.
    *
    * @param id the primary key of the current points distribution configuration
    * @param targetSubProposalId the target sub proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration[] findByTargetSubProposalId_PrevAndNext(
        long id, long targetSubProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetSubProposalId_PrevAndNext(id,
            targetSubProposalId, orderByComparator);
    }

    /**
    * Removes all the points distribution configurations where targetSubProposalId = &#63; from the database.
    *
    * @param targetSubProposalId the target sub proposal ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByTargetSubProposalId(long targetSubProposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByTargetSubProposalId(targetSubProposalId);
    }

    /**
    * Returns the number of points distribution configurations where targetSubProposalId = &#63;.
    *
    * @param targetSubProposalId the target sub proposal ID
    * @return the number of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static int countByTargetSubProposalId(long targetSubProposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByTargetSubProposalId(targetSubProposalId);
    }

    /**
    * Returns all the points distribution configurations where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByProposalId(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByProposalId(proposalId);
    }

    /**
    * Returns a range of all the points distribution configurations where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @return the range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByProposalId(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByProposalId(proposalId, start, end);
    }

    /**
    * Returns an ordered range of all the points distribution configurations where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByProposalId(
        long proposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId(proposalId, start, end, orderByComparator);
    }

    /**
    * Returns the first points distribution configuration in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration findByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_First(proposalId, orderByComparator);
    }

    /**
    * Returns the first points distribution configuration in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration fetchByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_First(proposalId, orderByComparator);
    }

    /**
    * Returns the last points distribution configuration in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration findByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_Last(proposalId, orderByComparator);
    }

    /**
    * Returns the last points distribution configuration in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration fetchByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_Last(proposalId, orderByComparator);
    }

    /**
    * Returns the points distribution configurations before and after the current points distribution configuration in the ordered set where proposalId = &#63;.
    *
    * @param id the primary key of the current points distribution configuration
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration[] findByProposalId_PrevAndNext(
        long id, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_PrevAndNext(id, proposalId,
            orderByComparator);
    }

    /**
    * Removes all the points distribution configurations where proposalId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByProposalId(proposalId);
    }

    /**
    * Returns the number of points distribution configurations where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the number of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static int countByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByProposalId(proposalId);
    }

    /**
    * Returns all the points distribution configurations where proposalId = &#63; and pointTypeId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @return the matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByProposalIdPointTypeId(
        long proposalId, long pointTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdPointTypeId(proposalId, pointTypeId);
    }

    /**
    * Returns a range of all the points distribution configurations where proposalId = &#63; and pointTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @return the range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByProposalIdPointTypeId(
        long proposalId, long pointTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdPointTypeId(proposalId, pointTypeId, start,
            end);
    }

    /**
    * Returns an ordered range of all the points distribution configurations where proposalId = &#63; and pointTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findByProposalIdPointTypeId(
        long proposalId, long pointTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdPointTypeId(proposalId, pointTypeId, start,
            end, orderByComparator);
    }

    /**
    * Returns the first points distribution configuration in the ordered set where proposalId = &#63; and pointTypeId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration findByProposalIdPointTypeId_First(
        long proposalId, long pointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdPointTypeId_First(proposalId, pointTypeId,
            orderByComparator);
    }

    /**
    * Returns the first points distribution configuration in the ordered set where proposalId = &#63; and pointTypeId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration fetchByProposalIdPointTypeId_First(
        long proposalId, long pointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalIdPointTypeId_First(proposalId, pointTypeId,
            orderByComparator);
    }

    /**
    * Returns the last points distribution configuration in the ordered set where proposalId = &#63; and pointTypeId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration findByProposalIdPointTypeId_Last(
        long proposalId, long pointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdPointTypeId_Last(proposalId, pointTypeId,
            orderByComparator);
    }

    /**
    * Returns the last points distribution configuration in the ordered set where proposalId = &#63; and pointTypeId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration fetchByProposalIdPointTypeId_Last(
        long proposalId, long pointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalIdPointTypeId_Last(proposalId, pointTypeId,
            orderByComparator);
    }

    /**
    * Returns the points distribution configurations before and after the current points distribution configuration in the ordered set where proposalId = &#63; and pointTypeId = &#63;.
    *
    * @param id the primary key of the current points distribution configuration
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration[] findByProposalIdPointTypeId_PrevAndNext(
        long id, long proposalId, long pointTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdPointTypeId_PrevAndNext(id, proposalId,
            pointTypeId, orderByComparator);
    }

    /**
    * Removes all the points distribution configurations where proposalId = &#63; and pointTypeId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByProposalIdPointTypeId(long proposalId,
        long pointTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByProposalIdPointTypeId(proposalId, pointTypeId);
    }

    /**
    * Returns the number of points distribution configurations where proposalId = &#63; and pointTypeId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param pointTypeId the point type ID
    * @return the number of matching points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static int countByProposalIdPointTypeId(long proposalId,
        long pointTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByProposalIdPointTypeId(proposalId, pointTypeId);
    }

    /**
    * Caches the points distribution configuration in the entity cache if it is enabled.
    *
    * @param pointsDistributionConfiguration the points distribution configuration
    */
    public static void cacheResult(
        com.ext.portlet.model.PointsDistributionConfiguration pointsDistributionConfiguration) {
        getPersistence().cacheResult(pointsDistributionConfiguration);
    }

    /**
    * Caches the points distribution configurations in the entity cache if it is enabled.
    *
    * @param pointsDistributionConfigurations the points distribution configurations
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> pointsDistributionConfigurations) {
        getPersistence().cacheResult(pointsDistributionConfigurations);
    }

    /**
    * Creates a new points distribution configuration with the primary key. Does not add the points distribution configuration to the database.
    *
    * @param id the primary key for the new points distribution configuration
    * @return the new points distribution configuration
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration create(
        long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the points distribution configuration with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the points distribution configuration
    * @return the points distribution configuration that was removed
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration remove(
        long id)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.PointsDistributionConfiguration updateImpl(
        com.ext.portlet.model.PointsDistributionConfiguration pointsDistributionConfiguration)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(pointsDistributionConfiguration);
    }

    /**
    * Returns the points distribution configuration with the primary key or throws a {@link com.ext.portlet.NoSuchPointsDistributionConfigurationException} if it could not be found.
    *
    * @param id the primary key of the points distribution configuration
    * @return the points distribution configuration
    * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchPointsDistributionConfigurationException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the points distribution configuration with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the points distribution configuration
    * @return the points distribution configuration, or <code>null</code> if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the points distribution configurations.
    *
    * @return the points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the points distribution configurations.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @return the range of points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the points distribution configurations.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the points distribution configurations from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of points distribution configurations.
    *
    * @return the number of points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PointsDistributionConfigurationPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PointsDistributionConfigurationPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PointsDistributionConfigurationPersistence.class.getName());

            ReferenceRegistry.registerReference(PointsDistributionConfigurationUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(
        PointsDistributionConfigurationPersistence persistence) {
    }
}
