package com.ext.portlet.Activity.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class ActivitySubscriptionLocalServiceClp
    implements ActivitySubscriptionLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addActivitySubscriptionMethodKey0;
    private MethodKey _createActivitySubscriptionMethodKey1;
    private MethodKey _deleteActivitySubscriptionMethodKey2;
    private MethodKey _deleteActivitySubscriptionMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchActivitySubscriptionMethodKey8;
    private MethodKey _getActivitySubscriptionMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getActivitySubscriptionsMethodKey11;
    private MethodKey _getActivitySubscriptionsCountMethodKey12;
    private MethodKey _updateActivitySubscriptionMethodKey13;
    private MethodKey _updateActivitySubscriptionMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _getActivitySubscriptionsMethodKey17;
    private MethodKey _findByUserMethodKey18;
    private MethodKey _isSubscribedMethodKey19;
    private MethodKey _isSubscribedMethodKey20;
    private MethodKey _deleteSubscriptionMethodKey21;
    private MethodKey _deleteSubscriptionMethodKey22;
    private MethodKey _addSubscriptionMethodKey23;
    private MethodKey _addSubscriptionMethodKey24;
    private MethodKey _addActivityInterpreterMethodKey25;
    private MethodKey _getInterpreterForClassMethodKey26;
    private MethodKey _getInterpreterForClassMethodKey27;
    private MethodKey _getActivitiesMethodKey28;
    private MethodKey _storeMethodKey29;
    private MethodKey _getInterpreterMethodKey30;
    private MethodKey _getNameMethodKey31;
    private MethodKey _getSubscriptionTypeMethodKey32;
    private MethodKey _deleteMethodKey33;

    public ActivitySubscriptionLocalServiceClp(
        ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addActivitySubscriptionMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addActivitySubscription",
                com.ext.portlet.Activity.model.ActivitySubscription.class);

        _createActivitySubscriptionMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createActivitySubscription", long.class);

        _deleteActivitySubscriptionMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteActivitySubscription", long.class);

        _deleteActivitySubscriptionMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteActivitySubscription",
                com.ext.portlet.Activity.model.ActivitySubscription.class);

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

        _fetchActivitySubscriptionMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchActivitySubscription", long.class);

        _getActivitySubscriptionMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getActivitySubscription", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getActivitySubscriptionsMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getActivitySubscriptions", int.class, int.class);

        _getActivitySubscriptionsCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getActivitySubscriptionsCount");

        _updateActivitySubscriptionMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateActivitySubscription",
                com.ext.portlet.Activity.model.ActivitySubscription.class);

        _updateActivitySubscriptionMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateActivitySubscription",
                com.ext.portlet.Activity.model.ActivitySubscription.class,
                boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _getActivitySubscriptionsMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "getActivitySubscriptions", java.lang.Class.class,
                java.lang.Long.class, java.lang.Integer.class,
                java.lang.String.class);

        _findByUserMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByUser", java.lang.Long.class);

        _isSubscribedMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "isSubscribed", java.lang.Long.class, java.lang.Long.class,
                java.lang.Long.class, java.lang.Integer.class,
                java.lang.String.class);

        _isSubscribedMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "isSubscribed", java.lang.Long.class, java.lang.Class.class,
                java.lang.Long.class, java.lang.Integer.class,
                java.lang.String.class);

        _deleteSubscriptionMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteSubscription", java.lang.Long.class,
                java.lang.Long.class, java.lang.Long.class,
                java.lang.Integer.class, java.lang.String.class);

        _deleteSubscriptionMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteSubscription", java.lang.Long.class,
                java.lang.Class.class, java.lang.Long.class,
                java.lang.Integer.class, java.lang.String.class);

        _addSubscriptionMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
                "addSubscription", java.lang.Long.class, java.lang.Long.class,
                java.lang.Integer.class, java.lang.String.class,
                java.lang.Long.class);

        _addSubscriptionMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
                "addSubscription", java.lang.Class.class, java.lang.Long.class,
                java.lang.Integer.class, java.lang.String.class,
                java.lang.Long.class);

        _addActivityInterpreterMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
                "addActivityInterpreter",
                com.ext.portlet.Activity.ICollabActivityInterpreter.class);

        _getInterpreterForClassMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
                "getInterpreterForClass", java.lang.String.class);

        _getInterpreterForClassMethodKey27 = new MethodKey(_classLoaderProxy.getClassName(),
                "getInterpreterForClass", java.lang.Long.class);

        _getActivitiesMethodKey28 = new MethodKey(_classLoaderProxy.getClassName(),
                "getActivities", java.lang.Long.class, int.class, int.class);

        _storeMethodKey29 = new MethodKey(_classLoaderProxy.getClassName(),
                "store",
                com.ext.portlet.Activity.model.ActivitySubscription.class);

        _getInterpreterMethodKey30 = new MethodKey(_classLoaderProxy.getClassName(),
                "getInterpreter",
                com.ext.portlet.Activity.model.ActivitySubscription.class);

        _getNameMethodKey31 = new MethodKey(_classLoaderProxy.getClassName(),
                "getName",
                com.ext.portlet.Activity.model.ActivitySubscription.class);

        _getSubscriptionTypeMethodKey32 = new MethodKey(_classLoaderProxy.getClassName(),
                "getSubscriptionType",
                com.ext.portlet.Activity.model.ActivitySubscription.class);

        _deleteMethodKey33 = new MethodKey(_classLoaderProxy.getClassName(),
                "delete",
                com.ext.portlet.Activity.model.ActivitySubscription.class);
    }

    public com.ext.portlet.Activity.model.ActivitySubscription addActivitySubscription(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addActivitySubscriptionMethodKey0,
                ClpSerializer.translateInput(activitySubscription));

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

        return (com.ext.portlet.Activity.model.ActivitySubscription) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.Activity.model.ActivitySubscription createActivitySubscription(
        long pk) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createActivitySubscriptionMethodKey1,
                pk);

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

        return (com.ext.portlet.Activity.model.ActivitySubscription) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteActivitySubscription(long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteActivitySubscriptionMethodKey2,
                pk);

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

    public void deleteActivitySubscription(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteActivitySubscriptionMethodKey3,
                ClpSerializer.translateInput(activitySubscription));

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

    public com.ext.portlet.Activity.model.ActivitySubscription fetchActivitySubscription(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchActivitySubscriptionMethodKey8,
                pk);

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

        return (com.ext.portlet.Activity.model.ActivitySubscription) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.Activity.model.ActivitySubscription getActivitySubscription(
        long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getActivitySubscriptionMethodKey9,
                pk);

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

        return (com.ext.portlet.Activity.model.ActivitySubscription) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> getActivitySubscriptions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getActivitySubscriptionsMethodKey11,
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

        return (java.util.List<com.ext.portlet.Activity.model.ActivitySubscription>) ClpSerializer.translateOutput(returnObj);
    }

    public int getActivitySubscriptionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getActivitySubscriptionsCountMethodKey12);

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

    public com.ext.portlet.Activity.model.ActivitySubscription updateActivitySubscription(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateActivitySubscriptionMethodKey13,
                ClpSerializer.translateInput(activitySubscription));

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

        return (com.ext.portlet.Activity.model.ActivitySubscription) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.Activity.model.ActivitySubscription updateActivitySubscription(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateActivitySubscriptionMethodKey14,
                ClpSerializer.translateInput(activitySubscription), merge);

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

        return (com.ext.portlet.Activity.model.ActivitySubscription) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> getActivitySubscriptions(
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getActivitySubscriptionsMethodKey17,
                ClpSerializer.translateInput(clasz),
                ClpSerializer.translateInput(classPK),
                ClpSerializer.translateInput(type),
                ClpSerializer.translateInput(extraData));

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

        return (java.util.List<com.ext.portlet.Activity.model.ActivitySubscription>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByUser(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByUserMethodKey18,
                ClpSerializer.translateInput(userId));

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

        return (java.util.List<com.ext.portlet.Activity.model.ActivitySubscription>) ClpSerializer.translateOutput(returnObj);
    }

    public boolean isSubscribed(java.lang.Long userId,
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_isSubscribedMethodKey19,
                ClpSerializer.translateInput(userId),
                ClpSerializer.translateInput(classNameId),
                ClpSerializer.translateInput(classPK),
                ClpSerializer.translateInput(type),
                ClpSerializer.translateInput(extraData));

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

        return ((Boolean) returnObj).booleanValue();
    }

    public boolean isSubscribed(java.lang.Long userId, java.lang.Class clasz,
        java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_isSubscribedMethodKey20,
                ClpSerializer.translateInput(userId),
                ClpSerializer.translateInput(clasz),
                ClpSerializer.translateInput(classPK),
                ClpSerializer.translateInput(type),
                ClpSerializer.translateInput(extraData));

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

        return ((Boolean) returnObj).booleanValue();
    }

    public void deleteSubscription(java.lang.Long userId,
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteSubscriptionMethodKey21,
                ClpSerializer.translateInput(userId),
                ClpSerializer.translateInput(classNameId),
                ClpSerializer.translateInput(classPK),
                ClpSerializer.translateInput(type),
                ClpSerializer.translateInput(extraData));

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

    public void deleteSubscription(java.lang.Long userId,
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteSubscriptionMethodKey22,
                ClpSerializer.translateInput(userId),
                ClpSerializer.translateInput(clasz),
                ClpSerializer.translateInput(classPK),
                ClpSerializer.translateInput(type),
                ClpSerializer.translateInput(extraData));

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

    public void addSubscription(java.lang.Long classNameId,
        java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_addSubscriptionMethodKey23,
                ClpSerializer.translateInput(classNameId),
                ClpSerializer.translateInput(classPK),
                ClpSerializer.translateInput(type),
                ClpSerializer.translateInput(extraData),
                ClpSerializer.translateInput(userId));

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

    public void addSubscription(java.lang.Class clasz, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_addSubscriptionMethodKey24,
                ClpSerializer.translateInput(clasz),
                ClpSerializer.translateInput(classPK),
                ClpSerializer.translateInput(type),
                ClpSerializer.translateInput(extraData),
                ClpSerializer.translateInput(userId));

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

    public void addActivityInterpreter(
        com.ext.portlet.Activity.ICollabActivityInterpreter interpreter) {
        MethodHandler methodHandler = new MethodHandler(_addActivityInterpreterMethodKey25,
                ClpSerializer.translateInput(interpreter));

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

    public com.ext.portlet.Activity.ICollabActivityInterpreter getInterpreterForClass(
        java.lang.String className) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getInterpreterForClassMethodKey26,
                ClpSerializer.translateInput(className));

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

        return (com.ext.portlet.Activity.ICollabActivityInterpreter) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.Activity.ICollabActivityInterpreter getInterpreterForClass(
        java.lang.Long classNameId) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getInterpreterForClassMethodKey27,
                ClpSerializer.translateInput(classNameId));

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

        return (com.ext.portlet.Activity.ICollabActivityInterpreter) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.liferay.portlet.social.model.SocialActivity> getActivities(
        java.lang.Long userId, int start, int count)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getActivitiesMethodKey28,
                ClpSerializer.translateInput(userId), start, count);

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

        return (java.util.List<com.liferay.portlet.social.model.SocialActivity>) ClpSerializer.translateOutput(returnObj);
    }

    public void store(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_storeMethodKey29,
                ClpSerializer.translateInput(activitySubscription));

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

    public com.ext.portlet.Activity.ICollabActivityInterpreter getInterpreter(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getInterpreterMethodKey30,
                ClpSerializer.translateInput(activitySubscription));

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

        return (com.ext.portlet.Activity.ICollabActivityInterpreter) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.String getName(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getNameMethodKey31,
                ClpSerializer.translateInput(activitySubscription));

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

    public com.ext.portlet.Activity.SubscriptionType getSubscriptionType(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getSubscriptionTypeMethodKey32,
                ClpSerializer.translateInput(activitySubscription));

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

        return (com.ext.portlet.Activity.SubscriptionType) ClpSerializer.translateOutput(returnObj);
    }

    public void delete(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteMethodKey33,
                ClpSerializer.translateInput(activitySubscription));

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

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
