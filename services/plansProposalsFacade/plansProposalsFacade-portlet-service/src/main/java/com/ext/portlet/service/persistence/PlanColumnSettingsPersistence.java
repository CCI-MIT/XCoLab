package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanColumnSettings;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan column settings service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanColumnSettingsPersistenceImpl
 * @see PlanColumnSettingsUtil
 * @generated
 */
public interface PlanColumnSettingsPersistence extends BasePersistence<PlanColumnSettings> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanColumnSettingsUtil} to access the plan column settings persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns the plan column settings where planUserSettingsId = &#63; and columnName = &#63; or throws a {@link com.ext.portlet.NoSuchPlanColumnSettingsException} if it could not be found.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param columnName the column name
    * @return the matching plan column settings
    * @throws com.ext.portlet.NoSuchPlanColumnSettingsException if a matching plan column settings could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanColumnSettings findByPlanUserSettingsIdColumnName(
        long planUserSettingsId, java.lang.String columnName)
        throws com.ext.portlet.NoSuchPlanColumnSettingsException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan column settings where planUserSettingsId = &#63; and columnName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param columnName the column name
    * @return the matching plan column settings, or <code>null</code> if a matching plan column settings could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanColumnSettings fetchByPlanUserSettingsIdColumnName(
        long planUserSettingsId, java.lang.String columnName)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan column settings where planUserSettingsId = &#63; and columnName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param columnName the column name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan column settings, or <code>null</code> if a matching plan column settings could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanColumnSettings fetchByPlanUserSettingsIdColumnName(
        long planUserSettingsId, java.lang.String columnName,
        boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the plan column settings where planUserSettingsId = &#63; and columnName = &#63; from the database.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param columnName the column name
    * @return the plan column settings that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanColumnSettings removeByPlanUserSettingsIdColumnName(
        long planUserSettingsId, java.lang.String columnName)
        throws com.ext.portlet.NoSuchPlanColumnSettingsException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan column settingses where planUserSettingsId = &#63; and columnName = &#63;.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param columnName the column name
    * @return the number of matching plan column settingses
    * @throws SystemException if a system exception occurred
    */
    public int countByPlanUserSettingsIdColumnName(long planUserSettingsId,
        java.lang.String columnName)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the plan column settings in the entity cache if it is enabled.
    *
    * @param planColumnSettings the plan column settings
    */
    public void cacheResult(
        com.ext.portlet.model.PlanColumnSettings planColumnSettings);

    /**
    * Caches the plan column settingses in the entity cache if it is enabled.
    *
    * @param planColumnSettingses the plan column settingses
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PlanColumnSettings> planColumnSettingses);

    /**
    * Creates a new plan column settings with the primary key. Does not add the plan column settings to the database.
    *
    * @param planColumnSettingsId the primary key for the new plan column settings
    * @return the new plan column settings
    */
    public com.ext.portlet.model.PlanColumnSettings create(
        long planColumnSettingsId);

    /**
    * Removes the plan column settings with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planColumnSettingsId the primary key of the plan column settings
    * @return the plan column settings that was removed
    * @throws com.ext.portlet.NoSuchPlanColumnSettingsException if a plan column settings with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanColumnSettings remove(
        long planColumnSettingsId)
        throws com.ext.portlet.NoSuchPlanColumnSettingsException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanColumnSettings updateImpl(
        com.ext.portlet.model.PlanColumnSettings planColumnSettings)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan column settings with the primary key or throws a {@link com.ext.portlet.NoSuchPlanColumnSettingsException} if it could not be found.
    *
    * @param planColumnSettingsId the primary key of the plan column settings
    * @return the plan column settings
    * @throws com.ext.portlet.NoSuchPlanColumnSettingsException if a plan column settings with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanColumnSettings findByPrimaryKey(
        long planColumnSettingsId)
        throws com.ext.portlet.NoSuchPlanColumnSettingsException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan column settings with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planColumnSettingsId the primary key of the plan column settings
    * @return the plan column settings, or <code>null</code> if a plan column settings with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanColumnSettings fetchByPrimaryKey(
        long planColumnSettingsId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan column settingses.
    *
    * @return the plan column settingses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanColumnSettings> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.PlanColumnSettings> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.PlanColumnSettings> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan column settingses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan column settingses.
    *
    * @return the number of plan column settingses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
