package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class MessagingMessageConversionLocalServiceClp
    implements MessagingMessageConversionLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addMessagingMessageConversionMethodKey0;
    private MethodKey _createMessagingMessageConversionMethodKey1;
    private MethodKey _deleteMessagingMessageConversionMethodKey2;
    private MethodKey _deleteMessagingMessageConversionMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchMessagingMessageConversionMethodKey8;
    private MethodKey _getMessagingMessageConversionMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getMessagingMessageConversionsMethodKey11;
    private MethodKey _getMessagingMessageConversionsCountMethodKey12;
    private MethodKey _updateMessagingMessageConversionMethodKey13;
    private MethodKey _updateMessagingMessageConversionMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _countByTypeMethodKey17;
    private MethodKey _countByTypeMethodKey18;
    private MethodKey _addConversionMethodKey19;
    private MethodKey _countConversionsByIPMethodKey20;
    private MethodKey _countConversionsByRecipientMethodKey21;

    public MessagingMessageConversionLocalServiceClp(
        ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addMessagingMessageConversionMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addMessagingMessageConversion",
                com.ext.portlet.model.MessagingMessageConversion.class);

        _createMessagingMessageConversionMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createMessagingMessageConversion", long.class);

        _deleteMessagingMessageConversionMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteMessagingMessageConversion", long.class);

        _deleteMessagingMessageConversionMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteMessagingMessageConversion",
                com.ext.portlet.model.MessagingMessageConversion.class);

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

        _fetchMessagingMessageConversionMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchMessagingMessageConversion", long.class);

        _getMessagingMessageConversionMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMessagingMessageConversion", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getMessagingMessageConversionsMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMessagingMessageConversions", int.class, int.class);

        _getMessagingMessageConversionsCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMessagingMessageConversionsCount");

        _updateMessagingMessageConversionMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateMessagingMessageConversion",
                com.ext.portlet.model.MessagingMessageConversion.class);

        _updateMessagingMessageConversionMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateMessagingMessageConversion",
                com.ext.portlet.model.MessagingMessageConversion.class,
                boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _countByTypeMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "countByType", java.lang.Long.class,
                com.ext.portlet.model.MessagingMessageConversionType.class);

        _countByTypeMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "countByType", java.lang.Long.class, java.lang.String.class);

        _addConversionMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "addConversion", java.lang.Long.class, java.lang.String.class,
                java.lang.String.class, java.lang.Object.class,
                java.lang.Object.class);

        _countConversionsByIPMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "countConversionsByIP", java.lang.Long.class,
                java.lang.String.class);

        _countConversionsByRecipientMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "countConversionsByRecipient", java.lang.Long.class,
                java.lang.String.class);
    }

    public com.ext.portlet.model.MessagingMessageConversion addMessagingMessageConversion(
        com.ext.portlet.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addMessagingMessageConversionMethodKey0,
                ClpSerializer.translateInput(messagingMessageConversion));

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

        return (com.ext.portlet.model.MessagingMessageConversion) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.MessagingMessageConversion createMessagingMessageConversion(
        long conversionId) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createMessagingMessageConversionMethodKey1,
                conversionId);

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

        return (com.ext.portlet.model.MessagingMessageConversion) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteMessagingMessageConversion(long conversionId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteMessagingMessageConversionMethodKey2,
                conversionId);

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

    public void deleteMessagingMessageConversion(
        com.ext.portlet.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteMessagingMessageConversionMethodKey3,
                ClpSerializer.translateInput(messagingMessageConversion));

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

    public com.ext.portlet.model.MessagingMessageConversion fetchMessagingMessageConversion(
        long conversionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchMessagingMessageConversionMethodKey8,
                conversionId);

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

        return (com.ext.portlet.model.MessagingMessageConversion) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.MessagingMessageConversion getMessagingMessageConversion(
        long conversionId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getMessagingMessageConversionMethodKey9,
                conversionId);

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

        return (com.ext.portlet.model.MessagingMessageConversion) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.model.MessagingMessageConversion> getMessagingMessageConversions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getMessagingMessageConversionsMethodKey11,
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

        return (java.util.List<com.ext.portlet.model.MessagingMessageConversion>) ClpSerializer.translateOutput(returnObj);
    }

    public int getMessagingMessageConversionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getMessagingMessageConversionsCountMethodKey12);

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

    public com.ext.portlet.model.MessagingMessageConversion updateMessagingMessageConversion(
        com.ext.portlet.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateMessagingMessageConversionMethodKey13,
                ClpSerializer.translateInput(messagingMessageConversion));

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

        return (com.ext.portlet.model.MessagingMessageConversion) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.MessagingMessageConversion updateMessagingMessageConversion(
        com.ext.portlet.model.MessagingMessageConversion messagingMessageConversion,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateMessagingMessageConversionMethodKey14,
                ClpSerializer.translateInput(messagingMessageConversion), merge);

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

        return (com.ext.portlet.model.MessagingMessageConversion) ClpSerializer.translateOutput(returnObj);
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

    public int countByType(java.lang.Long messageId,
        com.ext.portlet.model.MessagingMessageConversionType type)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_countByTypeMethodKey17,
                ClpSerializer.translateInput(messageId),
                ClpSerializer.translateInput(type));

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

    public int countByType(java.lang.Long messageId, java.lang.String typeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_countByTypeMethodKey18,
                ClpSerializer.translateInput(messageId),
                ClpSerializer.translateInput(typeName));

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

    public void addConversion(java.lang.Long messageId,
        java.lang.String typeName, java.lang.String clientIP,
        java.lang.Object extraData, java.lang.Object extraData2)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_addConversionMethodKey19,
                ClpSerializer.translateInput(messageId),
                ClpSerializer.translateInput(typeName),
                ClpSerializer.translateInput(clientIP),
                ClpSerializer.translateInput(extraData),
                ClpSerializer.translateInput(extraData2));

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

    public int countConversionsByIP(java.lang.Long messageId,
        java.lang.String typeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_countConversionsByIPMethodKey20,
                ClpSerializer.translateInput(messageId),
                ClpSerializer.translateInput(typeName));

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

    public int countConversionsByRecipient(java.lang.Long messageId,
        java.lang.String typeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_countConversionsByRecipientMethodKey21,
                ClpSerializer.translateInput(messageId),
                ClpSerializer.translateInput(typeName));

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

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
