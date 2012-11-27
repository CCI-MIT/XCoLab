package com.ext.portlet.plans.service.impl;

import java.util.Map;

import com.ext.portlet.plans.NoSuchUserSettingsException;
import com.ext.portlet.plans.model.PlanAttributeFilter;
import com.ext.portlet.plans.model.PlanColumnSettings;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanPropertyFilter;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanTypeColumn;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.model.impl.PlansUserSettingsModelImpl;
import com.ext.portlet.plans.service.PlanAttributeFilterLocalServiceUtil;
import com.ext.portlet.plans.service.PlanColumnSettingsLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPropertyFilterLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlansUserSettingsLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

/**
 * The implementation of the plans user settings local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.service.PlansUserSettingsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.plans.service.base.PlansUserSettingsLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.PlansUserSettingsLocalServiceUtil
 */
public class PlansUserSettingsLocalServiceImpl
    extends PlansUserSettingsLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.plans.service.PlansUserSettingsLocalServiceUtil} to access the plans user settings local service.
     */

    private static final String USER_SETTINGS = "USER_SETTINGS_";

    private static Log _log = LogFactoryUtil.getLog(PlansUserSettingsLocalServiceImpl.class);

    public PlansUserSettings getByUserIdPlanTypeId(Long userId, Long planTypeId) throws NoSuchUserSettingsException,
            SystemException {
        return plansUserSettingsPersistence.findByuserIdPlanTypeId(userId, planTypeId);
    }

    private String getUserSettingsParamName(long planTypeId) {
        return USER_SETTINGS + String.valueOf(planTypeId);
    }

    public PlansUserSettings getPlanUserSettings(Map sessionMap, Map requestMap, PlanType planType)
            throws PortalException, SystemException {
        String settingsParamName = getUserSettingsParamName(planType.getPlanTypeId());
        PlansUserSettings userSettings = (PlansUserSettings) sessionMap.get(settingsParamName);
        if (userSettings == null) {
            ThemeDisplay themeDisplay = (ThemeDisplay) requestMap.get(WebKeys.THEME_DISPLAY);
            long userId = themeDisplay.getUserId();
            User user = themeDisplay.getUser();

            if (!user.isDefaultUser()) {
                try {
                    userSettings = getByUserIdPlanTypeId(userId, planType
                            .getPlanTypeId());
                    // get positions from db and store them in the object
                    if (userSettings != null) {
                        /*
                        List<Long> positionsIds = 
                            PlansFilterPositionLocalServiceUtil.getPositionsIds(userId, planType.getPlanTypeId());
                        userSettings.setPositionsIds(positionsIds);
                        */
                    }

                } catch (NoSuchUserSettingsException e) {
                    System.out.print(e);
                }
            }

            if (userSettings == null || user.isDefaultUser()) {
                userSettings = createPlansUserSettings(null);
                userSettings.setPlanTypeId(planType.getPlanTypeId());
                userSettings.setUserId(userId);
                userSettings.setFilterEnabled(false);
                userSettings.setFilterPositionsAll(false);

                for (PlanTypeColumn planTypeColumn : planType.getColumns()) {
                    PlanColumnSettings settings = PlanColumnSettingsLocalServiceUtil.createPlanColumnSettings(null);
                    settings.setColumnName(planTypeColumn.getColumnName());
                    settings.setPlanUserSettingsId(userSettings.getPlanUserSettingsId());
                    settings.setVisible(planTypeColumn.getVisibleByDefault());

                    userSettings.addColumnSettings(settings);
                }

            }
            sessionMap.put(settingsParamName, userSettings);
        }

        return userSettings;

    }
    
    /**
     * Method responsible for storing columns configuration. Configuration is
     * stored in session also, if user is authorized then configuration is
     * persisted.
     *
     * @param requestMap           render request
     * @param plansUserSettings settings that should be stored
     * @throws PortalException passed up in case of framework error
     * @throws SystemException passed up in case of framework error
     */
    public void saveUserSettings(Map sessionMap, Map requestMap, PlansUserSettings plansUserSettings)
            throws PortalException, SystemException {

        ThemeDisplay themeDisplay = (ThemeDisplay) requestMap.get(WebKeys.THEME_DISPLAY);
        long userId = themeDisplay.getUserId();
        User user = UserLocalServiceUtil.getUser(userId);
        boolean add = false;
        if (!user.isDefaultUser()) {
            _log.debug(plansUserSettings.getClass().getName());
            _log.debug(plansUserSettings.getClass().getSuperclass().getName());
            _log.debug(PlansUserSettingsModelImpl.class.getName());
            _log.debug(PlansUserSettingsModelImpl.class + "\t" + plansUserSettings.getClass().getSuperclass() + "\t" + plansUserSettings.getClass().getSuperclass().equals(PlansUserSettingsModelImpl.class));
            _log.debug(PlansUserSettingsModelImpl.class.getClassLoader().hashCode() + " " + plansUserSettings.getClass().getClassLoader().hashCode() + " " + this.getClass().getClassLoader().hashCode());
            PlansUserSettingsModelImpl plansUserSettingsModelImpl = (PlansUserSettingsModelImpl) plansUserSettings;
            
            if (plansUserSettings.getPlanUserSettingsId() == null) {
                long planUserSettingsId = CounterLocalServiceUtil.increment(PlanItem.class.getName());
                plansUserSettings.setPlanUserSettingsId(planUserSettingsId);
                add = true;
            }
            if (add) {
                addPlansUserSettings(plansUserSettings);
            } else {
                updatePlansUserSettings(plansUserSettings);
            }

            for (PlanColumnSettings columnSettings : plansUserSettings.getUpdatedColumnSettings()) {
                add = false;
                if (columnSettings.getPlanColumnSettingsId() == null) {
                    /*long planColumnSettingsId = CounterServiceUtil.increment(PlanColumnSettings.class.getName());
                    columnSettings.setPlanColumnSettingsId(planColumnSettingsId);
                    columnSettings.setPlanUserSettingsId(plansUserSettings.getPlanUserSettingsId());
                    add = true;
                    */
                }
                if (add) {
                    PlanColumnSettingsLocalServiceUtil.addPlanColumnSettings(columnSettings);
                } else {
                    PlanColumnSettingsLocalServiceUtil.updatePlanColumnSettings(columnSettings);
                }
            }

            for (PlanAttributeFilter attributeFilter : plansUserSettings.getUpdatedPlanAttributeFilters()) {
                add = false;
                if (attributeFilter.getPlanAttributeFilterId() == null) {
                    /*
                    long planAttributeFilterId = CounterServiceUtil.increment(PlanColumnSettings.class.getName());
                    attributeFilter.setPlanAttributeFilterId(planAttributeFilterId);
                    attributeFilter.setPlanUserSettingsId(plansUserSettings.getPlanUserSettingsId());
                    add = true;
                    */
                }
                if (add) {
                    PlanAttributeFilterLocalServiceUtil.addPlanAttributeFilter(attributeFilter);
                } else {
                    PlanAttributeFilterLocalServiceUtil.updatePlanAttributeFilter(attributeFilter);
                }
            }

            for (PlanPropertyFilter propertyFilter : plansUserSettings.getUpdatedPlanPropertyFilters()) {
                add = false;
                if (propertyFilter.getPlanPropertyFilterId() == null) {
                    /*long planAttributeFilterId = CounterServiceUtil.increment(PlanColumnSettings.class.getName());
                    propertyFilter.setPlanPropertyFilterId(planAttributeFilterId);
                    propertyFilter.setPlanUserSettingsId(plansUserSettings.getPlanUserSettingsId());
                    add = true;
                    */
                }
                if (add) {
                    PlanPropertyFilterLocalServiceUtil.addPlanPropertyFilter(propertyFilter);
                } else {
                    PlanPropertyFilterLocalServiceUtil.updatePlanPropertyFilter(propertyFilter);
                }
            }
            
            // store positions ids
            //PlansFilterPositionLocalServiceUtil.storeFilterPositionsIds(userId, plansUserSettings.getPlanTypeId(), plansUserSettings.getPositionsIds());

            //storePlansFilterPositionsIds(plansUserSettings);
        }
        String settingsParamName = getUserSettingsParamName(plansUserSettings.getPlanTypeId());
        sessionMap.put(settingsParamName, plansUserSettings);
    }

    public PlansUserSettings getPlanUserSettings(Map sessionMap, Map requestMap, long planTypeId)
            throws PortalException, SystemException {
        return getPlanUserSettings(sessionMap, requestMap, PlanTypeLocalServiceUtil.getPlanType(planTypeId));
    }
}
