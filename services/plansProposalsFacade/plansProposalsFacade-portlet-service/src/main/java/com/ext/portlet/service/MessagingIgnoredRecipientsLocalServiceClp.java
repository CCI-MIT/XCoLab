package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class MessagingIgnoredRecipientsLocalServiceClp
    implements MessagingIgnoredRecipientsLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addMessagingIgnoredRecipientsMethodKey0;
    private MethodKey _createMessagingIgnoredRecipientsMethodKey1;
    private MethodKey _deleteMessagingIgnoredRecipientsMethodKey2;
    private MethodKey _deleteMessagingIgnoredRecipientsMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchMessagingIgnoredRecipientsMethodKey8;
    private MethodKey _getMessagingIgnoredRecipientsMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getMessagingIgnoredRecipientsesMethodKey11;
    private MethodKey _getMessagingIgnoredRecipientsesCountMethodKey12;
    private MethodKey _updateMessagingIgnoredRecipientsMethodKey13;
    private MethodKey _updateMessagingIgnoredRecipientsMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _findByUserIdMethodKey17;
    private MethodKey _findByEmailMethodKey18;

    public MessagingIgnoredRecipientsLocalServiceClp(
        ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addMessagingIgnoredRecipientsMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addMessagingIgnoredRecipients",
                com.ext.portlet.model.MessagingIgnoredRecipients.class);

        _createMessagingIgnoredRecipientsMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createMessagingIgnoredRecipients", long.class);

        _deleteMessagingIgnoredRecipientsMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteMessagingIgnoredRecipients", long.class);

        _deleteMessagingIgnoredRecipientsMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteMessagingIgnoredRecipients",
                com.ext.portlet.model.MessagingIgnoredRecipients.class);

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

        _fetchMessagingIgnoredRecipientsMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchMessagingIgnoredRecipients", long.class);

        _getMessagingIgnoredRecipientsMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMessagingIgnoredRecipients", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getMessagingIgnoredRecipientsesMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMessagingIgnoredRecipientses", int.class, int.class);

        _getMessagingIgnoredRecipientsesCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMessagingIgnoredRecipientsesCount");

        _updateMessagingIgnoredRecipientsMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateMessagingIgnoredRecipients",
                com.ext.portlet.model.MessagingIgnoredRecipients.class);

        _updateMessagingIgnoredRecipientsMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateMessagingIgnoredRecipients",
                com.ext.portlet.model.MessagingIgnoredRecipients.class,
                boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _findByUserIdMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByUserId", java.lang.Long.class);

        _findByEmailMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByEmail", java.lang.String.class);
    }

    public com.ext.portlet.model.MessagingIgnoredRecipients addMessagingIgnoredRecipients(
        com.ext.portlet.model.MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addMessagingIgnoredRecipientsMethodKey0,
                ClpSerializer.translateInput(messagingIgnoredRecipients));

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

        return (com.ext.portlet.model.MessagingIgnoredRecipients) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.MessagingIgnoredRecipients createMessagingIgnoredRecipients(
        long ignoredRecipientId) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createMessagingIgnoredRecipientsMethodKey1,
                ignoredRecipientId);

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

        return (com.ext.portlet.model.MessagingIgnoredRecipients) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteMessagingIgnoredRecipients(long ignoredRecipientId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteMessagingIgnoredRecipientsMethodKey2,
                ignoredRecipientId);

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

    public void deleteMessagingIgnoredRecipients(
        com.ext.portlet.model.MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteMessagingIgnoredRecipientsMethodKey3,
                ClpSerializer.translateInput(messagingIgnoredRecipients));

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

    public com.ext.portlet.model.MessagingIgnoredRecipients fetchMessagingIgnoredRecipients(
        long ignoredRecipientId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchMessagingIgnoredRecipientsMethodKey8,
                ignoredRecipientId);

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

        return (com.ext.portlet.model.MessagingIgnoredRecipients) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.MessagingIgnoredRecipients getMessagingIgnoredRecipients(
        long ignoredRecipientId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getMessagingIgnoredRecipientsMethodKey9,
                ignoredRecipientId);

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

        return (com.ext.portlet.model.MessagingIgnoredRecipients) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.model.MessagingIgnoredRecipients> getMessagingIgnoredRecipientses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getMessagingIgnoredRecipientsesMethodKey11,
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

        return (java.util.List<com.ext.portlet.model.MessagingIgnoredRecipients>) ClpSerializer.translateOutput(returnObj);
    }

    public int getMessagingIgnoredRecipientsesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getMessagingIgnoredRecipientsesCountMethodKey12);

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

    public com.ext.portlet.model.MessagingIgnoredRecipients updateMessagingIgnoredRecipients(
        com.ext.portlet.model.MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateMessagingIgnoredRecipientsMethodKey13,
                ClpSerializer.translateInput(messagingIgnoredRecipients));

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

        return (com.ext.portlet.model.MessagingIgnoredRecipients) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.MessagingIgnoredRecipients updateMessagingIgnoredRecipients(
        com.ext.portlet.model.MessagingIgnoredRecipients messagingIgnoredRecipients,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateMessagingIgnoredRecipientsMethodKey14,
                ClpSerializer.translateInput(messagingIgnoredRecipients), merge);

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

        return (com.ext.portlet.model.MessagingIgnoredRecipients) ClpSerializer.translateOutput(returnObj);
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

    public com.ext.portlet.model.MessagingIgnoredRecipients findByUserId(
        java.lang.Long userId)
        throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByUserIdMethodKey17,
                ClpSerializer.translateInput(userId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.NoSuchMessagingIgnoredRecipientsException) {
                throw (com.ext.portlet.NoSuchMessagingIgnoredRecipientsException) t;
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

        return (com.ext.portlet.model.MessagingIgnoredRecipients) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.MessagingIgnoredRecipients findByEmail(
        java.lang.String email)
        throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByEmailMethodKey18,
                ClpSerializer.translateInput(email));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.NoSuchMessagingIgnoredRecipientsException) {
                throw (com.ext.portlet.NoSuchMessagingIgnoredRecipientsException) t;
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

        return (com.ext.portlet.model.MessagingIgnoredRecipients) ClpSerializer.translateOutput(returnObj);
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
