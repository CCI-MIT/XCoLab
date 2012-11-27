package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class PlansUserSettingsLocalServiceClp
    implements PlansUserSettingsLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addPlansUserSettingsMethodKey0;
    private MethodKey _createPlansUserSettingsMethodKey1;
    private MethodKey _deletePlansUserSettingsMethodKey2;
    private MethodKey _deletePlansUserSettingsMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchPlansUserSettingsMethodKey8;
    private MethodKey _getPlansUserSettingsMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getPlansUserSettingsesMethodKey11;
    private MethodKey _getPlansUserSettingsesCountMethodKey12;
    private MethodKey _updatePlansUserSettingsMethodKey13;
    private MethodKey _updatePlansUserSettingsMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _getByUserIdPlanTypeIdMethodKey17;
    private MethodKey _getPlanUserSettingsMethodKey18;
    private MethodKey _saveUserSettingsMethodKey19;
    private MethodKey _getPlanUserSettingsMethodKey20;

    public PlansUserSettingsLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addPlansUserSettingsMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addPlansUserSettings",
                com.ext.portlet.plans.model.PlansUserSettings.class);

        _createPlansUserSettingsMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createPlansUserSettings", java.lang.Long.class);

        _deletePlansUserSettingsMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deletePlansUserSettings", java.lang.Long.class);

        _deletePlansUserSettingsMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deletePlansUserSettings",
                com.ext.portlet.plans.model.PlansUserSettings.class);

        _dynamicQueryMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
                "dynamicQuery",
                com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

        _dynamicQueryMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
                "dynamicQuery",
                com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
                int.class, int.class);

        _dynamicQueryMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
                "dynamicQuery",
                com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
                int.class, int.class,
                com.liferay.portal.kernel.util.OrderByComparator.class);

        _dynamicQueryCountMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
                "dynamicQueryCount",
                com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

        _fetchPlansUserSettingsMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchPlansUserSettings", java.lang.Long.class);

        _getPlansUserSettingsMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlansUserSettings", java.lang.Long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getPlansUserSettingsesMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlansUserSettingses", int.class, int.class);

        _getPlansUserSettingsesCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlansUserSettingsesCount");

        _updatePlansUserSettingsMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updatePlansUserSettings",
                com.ext.portlet.plans.model.PlansUserSettings.class);

        _updatePlansUserSettingsMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updatePlansUserSettings",
                com.ext.portlet.plans.model.PlansUserSettings.class,
                boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _getByUserIdPlanTypeIdMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "getByUserIdPlanTypeId", java.lang.Long.class,
                java.lang.Long.class);

        _getPlanUserSettingsMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanUserSettings", java.util.Map.class,
                java.util.Map.class, com.ext.portlet.plans.model.PlanType.class);

        _saveUserSettingsMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "saveUserSettings", java.util.Map.class, java.util.Map.class,
                com.ext.portlet.plans.model.PlansUserSettings.class);

        _getPlanUserSettingsMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanUserSettings", java.util.Map.class,
                java.util.Map.class, long.class);
    }

    public com.ext.portlet.plans.model.PlansUserSettings addPlansUserSettings(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addPlansUserSettingsMethodKey0,
                ClpSerializer.translateInput(plansUserSettings));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.plans.model.PlansUserSettings) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.plans.model.PlansUserSettings createPlansUserSettings(
        java.lang.Long planUserSettingsId) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createPlansUserSettingsMethodKey1,
                ClpSerializer.translateInput(planUserSettingsId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.plans.model.PlansUserSettings) ClpSerializer.translateOutput(returnObj);
    }

    public void deletePlansUserSettings(java.lang.Long planUserSettingsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deletePlansUserSettingsMethodKey2,
                ClpSerializer.translateInput(planUserSettingsId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void deletePlansUserSettings(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deletePlansUserSettingsMethodKey3,
                ClpSerializer.translateInput(plansUserSettings));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey4,
                ClpSerializer.translateInput(dynamicQuery));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey5,
                ClpSerializer.translateInput(dynamicQuery), start, end);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey6,
                ClpSerializer.translateInput(dynamicQuery), start, end,
                ClpSerializer.translateInput(orderByComparator));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_dynamicQueryCountMethodKey7,
                ClpSerializer.translateInput(dynamicQuery));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Long) returnObj).longValue();
    }

    public com.ext.portlet.plans.model.PlansUserSettings fetchPlansUserSettings(
        java.lang.Long planUserSettingsId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchPlansUserSettingsMethodKey8,
                ClpSerializer.translateInput(planUserSettingsId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.plans.model.PlansUserSettings) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.plans.model.PlansUserSettings getPlansUserSettings(
        java.lang.Long planUserSettingsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansUserSettingsMethodKey9,
                ClpSerializer.translateInput(planUserSettingsId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.plans.model.PlansUserSettings) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPersistedModelMethodKey10,
                ClpSerializer.translateInput(primaryKeyObj));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portal.model.PersistedModel) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.plans.model.PlansUserSettings> getPlansUserSettingses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansUserSettingsesMethodKey11,
                start, end);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.plans.model.PlansUserSettings>) ClpSerializer.translateOutput(returnObj);
    }

    public int getPlansUserSettingsesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansUserSettingsesCountMethodKey12);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Integer) returnObj).intValue();
    }

    public com.ext.portlet.plans.model.PlansUserSettings updatePlansUserSettings(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updatePlansUserSettingsMethodKey13,
                ClpSerializer.translateInput(plansUserSettings));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.plans.model.PlansUserSettings) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.plans.model.PlansUserSettings updatePlansUserSettings(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updatePlansUserSettingsMethodKey14,
                ClpSerializer.translateInput(plansUserSettings), merge);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.plans.model.PlansUserSettings) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.String getBeanIdentifier() {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getBeanIdentifierMethodKey15);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        MethodHandler methodHandler = new MethodHandler(_setBeanIdentifierMethodKey16,
                ClpSerializer.translateInput(beanIdentifier));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public com.ext.portlet.plans.model.PlansUserSettings getByUserIdPlanTypeId(
        java.lang.Long userId, java.lang.Long planTypeId)
        throws com.ext.portlet.plans.NoSuchUserSettingsException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getByUserIdPlanTypeIdMethodKey17,
                ClpSerializer.translateInput(userId),
                ClpSerializer.translateInput(planTypeId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.plans.NoSuchUserSettingsException) {
                throw (com.ext.portlet.plans.NoSuchUserSettingsException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.plans.model.PlansUserSettings) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.plans.model.PlansUserSettings getPlanUserSettings(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanUserSettingsMethodKey18,
                ClpSerializer.translateInput(sessionMap),
                ClpSerializer.translateInput(requestMap),
                ClpSerializer.translateInput(planType));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.plans.model.PlansUserSettings) ClpSerializer.translateOutput(returnObj);
    }

    public void saveUserSettings(java.util.Map sessionMap,
        java.util.Map requestMap,
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_saveUserSettingsMethodKey19,
                ClpSerializer.translateInput(sessionMap),
                ClpSerializer.translateInput(requestMap),
                ClpSerializer.translateInput(plansUserSettings));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public com.ext.portlet.plans.model.PlansUserSettings getPlanUserSettings(
        java.util.Map sessionMap, java.util.Map requestMap, long planTypeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanUserSettingsMethodKey20,
                ClpSerializer.translateInput(sessionMap),
                ClpSerializer.translateInput(requestMap), planTypeId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.plans.model.PlansUserSettings) ClpSerializer.translateOutput(returnObj);
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
