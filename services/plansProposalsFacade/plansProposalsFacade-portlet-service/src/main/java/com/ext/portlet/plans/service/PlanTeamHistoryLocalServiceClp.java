package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class PlanTeamHistoryLocalServiceClp
    implements PlanTeamHistoryLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addPlanTeamHistoryMethodKey0;
    private MethodKey _createPlanTeamHistoryMethodKey1;
    private MethodKey _deletePlanTeamHistoryMethodKey2;
    private MethodKey _deletePlanTeamHistoryMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchPlanTeamHistoryMethodKey8;
    private MethodKey _getPlanTeamHistoryMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getPlanTeamHistoriesMethodKey11;
    private MethodKey _getPlanTeamHistoriesCountMethodKey12;
    private MethodKey _updatePlanTeamHistoryMethodKey13;
    private MethodKey _updatePlanTeamHistoryMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _newHistoryItemMethodKey17;
    private MethodKey _newHistoryItemMethodKey18;
    private MethodKey _getLastUserActionInPlanMethodKey19;

    public PlanTeamHistoryLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addPlanTeamHistoryMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addPlanTeamHistory",
                com.ext.portlet.plans.model.PlanTeamHistory.class);

        _createPlanTeamHistoryMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createPlanTeamHistory", java.lang.Long.class);

        _deletePlanTeamHistoryMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deletePlanTeamHistory", java.lang.Long.class);

        _deletePlanTeamHistoryMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deletePlanTeamHistory",
                com.ext.portlet.plans.model.PlanTeamHistory.class);

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

        _fetchPlanTeamHistoryMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchPlanTeamHistory", java.lang.Long.class);

        _getPlanTeamHistoryMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanTeamHistory", java.lang.Long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getPlanTeamHistoriesMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanTeamHistories", int.class, int.class);

        _getPlanTeamHistoriesCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanTeamHistoriesCount");

        _updatePlanTeamHistoryMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updatePlanTeamHistory",
                com.ext.portlet.plans.model.PlanTeamHistory.class);

        _updatePlanTeamHistoryMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updatePlanTeamHistory",
                com.ext.portlet.plans.model.PlanTeamHistory.class, boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _newHistoryItemMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "newHistoryItem", java.lang.Long.class, java.lang.Long.class,
                java.lang.String.class, java.lang.Long.class);

        _newHistoryItemMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "newHistoryItem", java.lang.Long.class, java.lang.Long.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.Long.class);

        _getLastUserActionInPlanMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "getLastUserActionInPlan", java.lang.Long.class,
                java.lang.Long.class);
    }

    public com.ext.portlet.plans.model.PlanTeamHistory addPlanTeamHistory(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addPlanTeamHistoryMethodKey0,
                ClpSerializer.translateInput(planTeamHistory));

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

        return (com.ext.portlet.plans.model.PlanTeamHistory) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.plans.model.PlanTeamHistory createPlanTeamHistory(
        java.lang.Long id) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createPlanTeamHistoryMethodKey1,
                ClpSerializer.translateInput(id));

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

        return (com.ext.portlet.plans.model.PlanTeamHistory) ClpSerializer.translateOutput(returnObj);
    }

    public void deletePlanTeamHistory(java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deletePlanTeamHistoryMethodKey2,
                ClpSerializer.translateInput(id));

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

    public void deletePlanTeamHistory(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deletePlanTeamHistoryMethodKey3,
                ClpSerializer.translateInput(planTeamHistory));

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

    public com.ext.portlet.plans.model.PlanTeamHistory fetchPlanTeamHistory(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchPlanTeamHistoryMethodKey8,
                ClpSerializer.translateInput(id));

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

        return (com.ext.portlet.plans.model.PlanTeamHistory) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.plans.model.PlanTeamHistory getPlanTeamHistory(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanTeamHistoryMethodKey9,
                ClpSerializer.translateInput(id));

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

        return (com.ext.portlet.plans.model.PlanTeamHistory) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> getPlanTeamHistories(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanTeamHistoriesMethodKey11,
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

        return (java.util.List<com.ext.portlet.plans.model.PlanTeamHistory>) ClpSerializer.translateOutput(returnObj);
    }

    public int getPlanTeamHistoriesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanTeamHistoriesCountMethodKey12);

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

    public com.ext.portlet.plans.model.PlanTeamHistory updatePlanTeamHistory(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updatePlanTeamHistoryMethodKey13,
                ClpSerializer.translateInput(planTeamHistory));

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

        return (com.ext.portlet.plans.model.PlanTeamHistory) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.plans.model.PlanTeamHistory updatePlanTeamHistory(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updatePlanTeamHistoryMethodKey14,
                ClpSerializer.translateInput(planTeamHistory), merge);

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

        return (com.ext.portlet.plans.model.PlanTeamHistory) ClpSerializer.translateOutput(returnObj);
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

    public com.ext.portlet.plans.model.PlanTeamHistory newHistoryItem(
        java.lang.Long planId, java.lang.Long userId, java.lang.String action,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_newHistoryItemMethodKey17,
                ClpSerializer.translateInput(planId),
                ClpSerializer.translateInput(userId),
                ClpSerializer.translateInput(action),
                ClpSerializer.translateInput(updateAuthorId));

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

        return (com.ext.portlet.plans.model.PlanTeamHistory) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.plans.model.PlanTeamHistory newHistoryItem(
        java.lang.Long planId, java.lang.Long userId, java.lang.String action,
        java.lang.String payload, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_newHistoryItemMethodKey18,
                ClpSerializer.translateInput(planId),
                ClpSerializer.translateInput(userId),
                ClpSerializer.translateInput(action),
                ClpSerializer.translateInput(payload),
                ClpSerializer.translateInput(updateAuthorId));

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

        return (com.ext.portlet.plans.model.PlanTeamHistory) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.plans.model.PlanTeamHistory getLastUserActionInPlan(
        java.lang.Long planId, java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getLastUserActionInPlanMethodKey19,
                ClpSerializer.translateInput(planId),
                ClpSerializer.translateInput(userId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.plans.NoSuchPlanTeamHistoryException) {
                throw (com.ext.portlet.plans.NoSuchPlanTeamHistoryException) t;
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

        return (com.ext.portlet.plans.model.PlanTeamHistory) ClpSerializer.translateOutput(returnObj);
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
