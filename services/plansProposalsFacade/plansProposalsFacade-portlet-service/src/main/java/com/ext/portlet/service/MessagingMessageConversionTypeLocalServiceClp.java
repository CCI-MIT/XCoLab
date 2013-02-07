package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class MessagingMessageConversionTypeLocalServiceClp
    implements MessagingMessageConversionTypeLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addMessagingMessageConversionTypeMethodKey0;
    private MethodKey _createMessagingMessageConversionTypeMethodKey1;
    private MethodKey _deleteMessagingMessageConversionTypeMethodKey2;
    private MethodKey _deleteMessagingMessageConversionTypeMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchMessagingMessageConversionTypeMethodKey8;
    private MethodKey _getMessagingMessageConversionTypeMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getMessagingMessageConversionTypesMethodKey11;
    private MethodKey _getMessagingMessageConversionTypesCountMethodKey12;
    private MethodKey _updateMessagingMessageConversionTypeMethodKey13;
    private MethodKey _updateMessagingMessageConversionTypeMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _getByNameMethodKey17;

    public MessagingMessageConversionTypeLocalServiceClp(
        ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addMessagingMessageConversionTypeMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addMessagingMessageConversionType",
                com.ext.portlet.model.MessagingMessageConversionType.class);

        _createMessagingMessageConversionTypeMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createMessagingMessageConversionType", long.class);

        _deleteMessagingMessageConversionTypeMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteMessagingMessageConversionType", long.class);

        _deleteMessagingMessageConversionTypeMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteMessagingMessageConversionType",
                com.ext.portlet.model.MessagingMessageConversionType.class);

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

        _fetchMessagingMessageConversionTypeMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchMessagingMessageConversionType", long.class);

        _getMessagingMessageConversionTypeMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMessagingMessageConversionType", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getMessagingMessageConversionTypesMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMessagingMessageConversionTypes", int.class, int.class);

        _getMessagingMessageConversionTypesCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMessagingMessageConversionTypesCount");

        _updateMessagingMessageConversionTypeMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateMessagingMessageConversionType",
                com.ext.portlet.model.MessagingMessageConversionType.class);

        _updateMessagingMessageConversionTypeMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateMessagingMessageConversionType",
                com.ext.portlet.model.MessagingMessageConversionType.class,
                boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _getByNameMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "getByName", java.lang.String.class);
    }

    public com.ext.portlet.model.MessagingMessageConversionType addMessagingMessageConversionType(
        com.ext.portlet.model.MessagingMessageConversionType messagingMessageConversionType)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addMessagingMessageConversionTypeMethodKey0,
                ClpSerializer.translateInput(messagingMessageConversionType));

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

        return (com.ext.portlet.model.MessagingMessageConversionType) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.MessagingMessageConversionType createMessagingMessageConversionType(
        long typeId) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createMessagingMessageConversionTypeMethodKey1,
                typeId);

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

        return (com.ext.portlet.model.MessagingMessageConversionType) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteMessagingMessageConversionType(long typeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteMessagingMessageConversionTypeMethodKey2,
                typeId);

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

    public void deleteMessagingMessageConversionType(
        com.ext.portlet.model.MessagingMessageConversionType messagingMessageConversionType)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteMessagingMessageConversionTypeMethodKey3,
                ClpSerializer.translateInput(messagingMessageConversionType));

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

    public com.ext.portlet.model.MessagingMessageConversionType fetchMessagingMessageConversionType(
        long typeId) throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchMessagingMessageConversionTypeMethodKey8,
                typeId);

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

        return (com.ext.portlet.model.MessagingMessageConversionType) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.MessagingMessageConversionType getMessagingMessageConversionType(
        long typeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getMessagingMessageConversionTypeMethodKey9,
                typeId);

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

        return (com.ext.portlet.model.MessagingMessageConversionType) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.model.MessagingMessageConversionType> getMessagingMessageConversionTypes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getMessagingMessageConversionTypesMethodKey11,
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

        return (java.util.List<com.ext.portlet.model.MessagingMessageConversionType>) ClpSerializer.translateOutput(returnObj);
    }

    public int getMessagingMessageConversionTypesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getMessagingMessageConversionTypesCountMethodKey12);

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

    public com.ext.portlet.model.MessagingMessageConversionType updateMessagingMessageConversionType(
        com.ext.portlet.model.MessagingMessageConversionType messagingMessageConversionType)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateMessagingMessageConversionTypeMethodKey13,
                ClpSerializer.translateInput(messagingMessageConversionType));

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

        return (com.ext.portlet.model.MessagingMessageConversionType) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.MessagingMessageConversionType updateMessagingMessageConversionType(
        com.ext.portlet.model.MessagingMessageConversionType messagingMessageConversionType,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateMessagingMessageConversionTypeMethodKey14,
                ClpSerializer.translateInput(messagingMessageConversionType),
                merge);

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

        return (com.ext.portlet.model.MessagingMessageConversionType) ClpSerializer.translateOutput(returnObj);
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

    public com.ext.portlet.model.MessagingMessageConversionType getByName(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getByNameMethodKey17,
                ClpSerializer.translateInput(name));

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

        return (com.ext.portlet.model.MessagingMessageConversionType) ClpSerializer.translateOutput(returnObj);
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
