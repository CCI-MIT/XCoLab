package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanColumnSettings;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan column settings service. This utility wraps {@link PlanColumnSettingsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanColumnSettingsPersistence
 * @see PlanColumnSettingsPersistenceImpl
 * @generated
 */
public class PlanColumnSettingsUtil {
    private static PlanColumnSettingsPersistence _persistence;

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
    public static void clearCache(PlanColumnSettings planColumnSettings) {
        getPersistence().clearCache(planColumnSettings);
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
    public static List<PlanColumnSettings> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanColumnSettings> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanColumnSettings> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static PlanColumnSettings update(
        PlanColumnSettings planColumnSettings) throws SystemException {
        return getPersistence().update(planColumnSettings);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static PlanColumnSettings update(
        PlanColumnSettings planColumnSettings, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(planColumnSettings, serviceContext);
    }

    /**
    * Returns the plan column settings where planUserSettingsId = &#63; and columnName = &#63; or throws a {@link com.ext.portlet.NoSuchPlanColumnSettingsException} if it could not be found.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param columnName the column name
    * @return the matching plan column settings
    * @throws com.ext.portlet.NoSuchPlanColumnSettingsException if a matching plan column settings could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanColumnSettings findByPlanUserSettingsIdColumnName(
        long planUserSettingsId, java.lang.String columnName)
        throws com.ext.portlet.NoSuchPlanColumnSettingsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanUserSettingsIdColumnName(planUserSettingsId,
            columnName);
    }

    /**
    * Returns the plan column settings where planUserSettingsId = &#63; and columnName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param columnName the column name
    * @return the matching plan column settings, or <code>null</code> if a matching plan column settings could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanColumnSettings fetchByPlanUserSettingsIdColumnName(
        long planUserSettingsId, java.lang.String columnName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanUserSettingsIdColumnName(planUserSettingsId,
            columnName);
    }

    /**
    * Returns the plan column settings where planUserSettingsId = &#63; and columnName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param columnName the column name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan column settings, or <code>null</code> if a matching plan column settings could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanColumnSettings fetchByPlanUserSettingsIdColumnName(
        long planUserSettingsId, java.lang.String columnName,
        boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanUserSettingsIdColumnName(planUserSettingsId,
            columnName, retrieveFromCache);
    }

    /**
    * Removes the plan column settings where planUserSettingsId = &#63; and columnName = &#63; from the database.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param columnName the column name
    * @return the plan column settings that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanColumnSettings removeByPlanUserSettingsIdColumnName(
        long planUserSettingsId, java.lang.String columnName)
        throws com.ext.portlet.NoSuchPlanColumnSettingsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .removeByPlanUserSettingsIdColumnName(planUserSettingsId,
            columnName);
    }

    /**
    * Returns the number of plan column settingses where planUserSettingsId = &#63; and columnName = &#63;.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param columnName the column name
    * @return the number of matching plan column settingses
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanUserSettingsIdColumnName(
        long planUserSettingsId, java.lang.String columnName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByPlanUserSettingsIdColumnName(planUserSettingsId,
            columnName);
    }

    /**
    * Caches the plan column settings in the entity cache if it is enabled.
    *
    * @param planColumnSettings the plan column settings
    */
    public static void cacheResult(
        com.ext.portlet.model.PlanColumnSettings planColumnSettings) {
        getPersistence().cacheResult(planColumnSettings);
    }

    /**
    * Caches the plan column settingses in the entity cache if it is enabled.
    *
    * @param planColumnSettingses the plan column settingses
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PlanColumnSettings> planColumnSettingses) {
        getPersistence().cacheResult(planColumnSettingses);
    }

    /**
    * Creates a new plan column settings with the primary key. Does not add the plan column settings to the database.
    *
    * @param planColumnSettingsId the primary key for the new plan column settings
    * @return the new plan column settings
    */
    public static com.ext.portlet.model.PlanColumnSettings create(
        long planColumnSettingsId) {
        return getPersistence().create(planColumnSettingsId);
    }

    /**
    * Removes the plan column settings with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planColumnSettingsId the primary key of the plan column settings
    * @return the plan column settings that was removed
    * @throws com.ext.portlet.NoSuchPlanColumnSettingsException if a plan column settings with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanColumnSettings remove(
        long planColumnSettingsId)
        throws com.ext.portlet.NoSuchPlanColumnSettingsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(planColumnSettingsId);
    }

    public static com.ext.portlet.model.PlanColumnSettings updateImpl(
        com.ext.portlet.model.PlanColumnSettings planColumnSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planColumnSettings);
    }

    /**
    * Returns the plan column settings with the primary key or throws a {@link com.ext.portlet.NoSuchPlanColumnSettingsException} if it could not be found.
    *
    * @param planColumnSettingsId the primary key of the plan column settings
    * @return the plan column settings
    * @throws com.ext.portlet.NoSuchPlanColumnSettingsException if a plan column settings with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanColumnSettings findByPrimaryKey(
        long planColumnSettingsId)
        throws com.ext.portlet.NoSuchPlanColumnSettingsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(planColumnSettingsId);
    }

    /**
    * Returns the plan column settings with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planColumnSettingsId the primary key of the plan column settings
    * @return the plan column settings, or <code>null</code> if a plan column settings with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanColumnSettings fetchByPrimaryKey(
        long planColumnSettingsId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(planColumnSettingsId);
    }

    /**
    * Returns all the plan column settingses.
    *
    * @return the plan column settingses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanColumnSettings> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan column settingses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanColumnSettingsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan column settingses
    * @param end the upper bound of the range of plan column settingses (not inclusive)
    * @return the range of plan column settingses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanColumnSettings> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan column settingses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanColumnSettingsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan column settingses
    * @param end the upper bound of the range of plan column settingses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan column settingses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanColumnSettings> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the plan column settingses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan column settingses.
    *
    * @return the number of plan column settingses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanColumnSettingsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanColumnSettingsPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PlanColumnSettingsPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanColumnSettingsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PlanColumnSettingsPersistence persistence) {
    }
}
