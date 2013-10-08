package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class DiscussionMessageLocalServiceClp
    implements DiscussionMessageLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addDiscussionMessageMethodKey0;
    private MethodKey _createDiscussionMessageMethodKey1;
    private MethodKey _deleteDiscussionMessageMethodKey2;
    private MethodKey _deleteDiscussionMessageMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchDiscussionMessageMethodKey8;
    private MethodKey _getDiscussionMessageMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getDiscussionMessagesMethodKey11;
    private MethodKey _getDiscussionMessagesCountMethodKey12;
    private MethodKey _updateDiscussionMessageMethodKey13;
    private MethodKey _updateDiscussionMessageMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _getThreadsByCategoryMethodKey17;
    private MethodKey _getThreadMessagesMethodKey18;
    private MethodKey _getThreadMessagesCountMethodKey19;
    private MethodKey _getThreadByThreadIdMethodKey20;
    private MethodKey _addThreadMethodKey21;
    private MethodKey _addMessageMethodKey22;
    private MethodKey _searchMethodKey23;
    private MethodKey _getMessageByMessageIdMethodKey24;
    private MethodKey _reIndexMethodKey25;
    private MethodKey _reIndexMethodKey26;
    private MethodKey _getThreadMessagesMethodKey27;
    private MethodKey _getThreadMessagesCountMethodKey28;
    private MethodKey _storeMethodKey29;
    private MethodKey _addThreadMessageMethodKey30;
    private MethodKey _getAuthorMethodKey31;
    private MethodKey _getLastActivityAuthorMethodKey32;
    private MethodKey _deleteMethodKey33;
    private MethodKey _updateMethodKey34;
    private MethodKey _getCategoryMethodKey35;
    private MethodKey _getCategoryGroupMethodKey36;
    private MethodKey _getThreadMethodKey37;
    private MethodKey _getFlagsMethodKey38;
    private MethodKey _addFlagMethodKey39;
    private MethodKey _removeFlagMethodKey40;
    private MethodKey _hasFlagMethodKey41;

    public DiscussionMessageLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addDiscussionMessageMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addDiscussionMessage",
                com.ext.portlet.model.DiscussionMessage.class);

        _createDiscussionMessageMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createDiscussionMessage", long.class);

        _deleteDiscussionMessageMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteDiscussionMessage", long.class);

        _deleteDiscussionMessageMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteDiscussionMessage",
                com.ext.portlet.model.DiscussionMessage.class);

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

        _fetchDiscussionMessageMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchDiscussionMessage", long.class);

        _getDiscussionMessageMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDiscussionMessage", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getDiscussionMessagesMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDiscussionMessages", int.class, int.class);

        _getDiscussionMessagesCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDiscussionMessagesCount");

        _updateDiscussionMessageMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateDiscussionMessage",
                com.ext.portlet.model.DiscussionMessage.class);

        _updateDiscussionMessageMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateDiscussionMessage",
                com.ext.portlet.model.DiscussionMessage.class, boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _getThreadsByCategoryMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "getThreadsByCategory", long.class);

        _getThreadMessagesMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "getThreadMessages", long.class);

        _getThreadMessagesCountMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "getThreadMessagesCount", long.class);

        _getThreadByThreadIdMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "getThreadByThreadId", long.class);

        _addThreadMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "addThread", long.class, long.class, java.lang.String.class,
                java.lang.String.class, com.liferay.portal.model.User.class);

        _addMessageMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "addMessage", long.class, long.class, long.class,
                java.lang.String.class, java.lang.String.class,
                com.liferay.portal.model.User.class);

        _searchMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
                "search", java.lang.String.class, long.class);

        _getMessageByMessageIdMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMessageByMessageId", long.class);

        _reIndexMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
                "reIndex");

        _reIndexMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
                "reIndex", long.class);

        _getThreadMessagesMethodKey27 = new MethodKey(_classLoaderProxy.getClassName(),
                "getThreadMessages",
                com.ext.portlet.model.DiscussionMessage.class);

        _getThreadMessagesCountMethodKey28 = new MethodKey(_classLoaderProxy.getClassName(),
                "getThreadMessagesCount",
                com.ext.portlet.model.DiscussionMessage.class);

        _storeMethodKey29 = new MethodKey(_classLoaderProxy.getClassName(),
                "store", com.ext.portlet.model.DiscussionMessage.class);

        _addThreadMessageMethodKey30 = new MethodKey(_classLoaderProxy.getClassName(),
                "addThreadMessage",
                com.ext.portlet.model.DiscussionMessage.class,
                java.lang.String.class, java.lang.String.class,
                com.liferay.portal.model.User.class);

        _getAuthorMethodKey31 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAuthor", com.ext.portlet.model.DiscussionMessage.class);

        _getLastActivityAuthorMethodKey32 = new MethodKey(_classLoaderProxy.getClassName(),
                "getLastActivityAuthor",
                com.ext.portlet.model.DiscussionMessage.class);

        _deleteMethodKey33 = new MethodKey(_classLoaderProxy.getClassName(),
                "delete", com.ext.portlet.model.DiscussionMessage.class);

        _updateMethodKey34 = new MethodKey(_classLoaderProxy.getClassName(),
                "update", com.ext.portlet.model.DiscussionMessage.class,
                java.lang.String.class, java.lang.String.class);

        _getCategoryMethodKey35 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCategory", com.ext.portlet.model.DiscussionMessage.class);

        _getCategoryGroupMethodKey36 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCategoryGroup",
                com.ext.portlet.model.DiscussionMessage.class);

        _getThreadMethodKey37 = new MethodKey(_classLoaderProxy.getClassName(),
                "getThread", com.ext.portlet.model.DiscussionMessage.class);

        _getFlagsMethodKey38 = new MethodKey(_classLoaderProxy.getClassName(),
                "getFlags", com.ext.portlet.model.DiscussionMessage.class);

        _addFlagMethodKey39 = new MethodKey(_classLoaderProxy.getClassName(),
                "addFlag", com.ext.portlet.model.DiscussionMessage.class,
                java.lang.String.class, java.lang.String.class,
                com.liferay.portal.model.User.class);

        _removeFlagMethodKey40 = new MethodKey(_classLoaderProxy.getClassName(),
                "removeFlag", com.ext.portlet.model.DiscussionMessage.class,
                java.lang.String.class);

        _hasFlagMethodKey41 = new MethodKey(_classLoaderProxy.getClassName(),
                "hasFlag", long.class, java.lang.String.class);
    }

    public com.ext.portlet.model.DiscussionMessage addDiscussionMessage(
        com.ext.portlet.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addDiscussionMessageMethodKey0,
                ClpSerializer.translateInput(discussionMessage));

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

        return (com.ext.portlet.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.DiscussionMessage createDiscussionMessage(
        long pk) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createDiscussionMessageMethodKey1,
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

        return (com.ext.portlet.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteDiscussionMessage(long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteDiscussionMessageMethodKey2,
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

    public void deleteDiscussionMessage(
        com.ext.portlet.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteDiscussionMessageMethodKey3,
                ClpSerializer.translateInput(discussionMessage));

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

    public com.ext.portlet.model.DiscussionMessage fetchDiscussionMessage(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchDiscussionMessageMethodKey8,
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

        return (com.ext.portlet.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.DiscussionMessage getDiscussionMessage(long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getDiscussionMessageMethodKey9,
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

        return (com.ext.portlet.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.model.DiscussionMessage> getDiscussionMessages(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getDiscussionMessagesMethodKey11,
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

        return (java.util.List<com.ext.portlet.model.DiscussionMessage>) ClpSerializer.translateOutput(returnObj);
    }

    public int getDiscussionMessagesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getDiscussionMessagesCountMethodKey12);

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

    public com.ext.portlet.model.DiscussionMessage updateDiscussionMessage(
        com.ext.portlet.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateDiscussionMessageMethodKey13,
                ClpSerializer.translateInput(discussionMessage));

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

        return (com.ext.portlet.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.DiscussionMessage updateDiscussionMessage(
        com.ext.portlet.model.DiscussionMessage discussionMessage, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateDiscussionMessageMethodKey14,
                ClpSerializer.translateInput(discussionMessage), merge);

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

        return (com.ext.portlet.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.model.DiscussionMessage> getThreadsByCategory(
        long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getThreadsByCategoryMethodKey17,
                categoryId);

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

        return (java.util.List<com.ext.portlet.model.DiscussionMessage>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.DiscussionMessage> getThreadMessages(
        long threadId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getThreadMessagesMethodKey18,
                threadId);

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

        return (java.util.List<com.ext.portlet.model.DiscussionMessage>) ClpSerializer.translateOutput(returnObj);
    }

    public int getThreadMessagesCount(long threadId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getThreadMessagesCountMethodKey19,
                threadId);

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

    public com.ext.portlet.model.DiscussionMessage getThreadByThreadId(
        long threadId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getThreadByThreadIdMethodKey20,
                threadId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.NoSuchDiscussionMessageException) {
                throw (com.ext.portlet.NoSuchDiscussionMessageException) t;
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

        return (com.ext.portlet.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.DiscussionMessage addThread(
        long categoryGroupId, long categoryId, java.lang.String subject,
        java.lang.String body, com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addThreadMethodKey21,
                categoryGroupId, categoryId,
                ClpSerializer.translateInput(subject),
                ClpSerializer.translateInput(body),
                ClpSerializer.translateInput(author));

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

        return (com.ext.portlet.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.DiscussionMessage addMessage(
        long categoryGroupId, long categoryId, long threadId,
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addMessageMethodKey22,
                categoryGroupId, categoryId, threadId,
                ClpSerializer.translateInput(subject),
                ClpSerializer.translateInput(body),
                ClpSerializer.translateInput(author));

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

        return (com.ext.portlet.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.DiscussionMessage> search(
        java.lang.String query, long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_searchMethodKey23,
                ClpSerializer.translateInput(query), categoryGroupId);

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

        return (java.util.List<com.ext.portlet.model.DiscussionMessage>) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.DiscussionMessage getMessageByMessageId(
        long messageId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getMessageByMessageIdMethodKey24,
                messageId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.NoSuchDiscussionMessageException) {
                throw (com.ext.portlet.NoSuchDiscussionMessageException) t;
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

        return (com.ext.portlet.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public void reIndex()
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_reIndexMethodKey25);

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

    public void reIndex(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_reIndexMethodKey26,
                messageId);

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

    public java.util.List<com.ext.portlet.model.DiscussionMessage> getThreadMessages(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getThreadMessagesMethodKey27,
                ClpSerializer.translateInput(dMessage));

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

        return (java.util.List<com.ext.portlet.model.DiscussionMessage>) ClpSerializer.translateOutput(returnObj);
    }

    public int getThreadMessagesCount(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getThreadMessagesCountMethodKey28,
                ClpSerializer.translateInput(dMessage));

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

    public void store(com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_storeMethodKey29,
                ClpSerializer.translateInput(dMessage));

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

    public com.ext.portlet.model.DiscussionMessage addThreadMessage(
        com.ext.portlet.model.DiscussionMessage dMessage,
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addThreadMessageMethodKey30,
                ClpSerializer.translateInput(dMessage),
                ClpSerializer.translateInput(subject),
                ClpSerializer.translateInput(body),
                ClpSerializer.translateInput(author));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.NoSuchDiscussionCategoryException) {
                throw (com.ext.portlet.NoSuchDiscussionCategoryException) t;
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

        return (com.ext.portlet.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portal.model.User getAuthor(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getAuthorMethodKey31,
                ClpSerializer.translateInput(dMessage));

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

        return (com.liferay.portal.model.User) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portal.model.User getLastActivityAuthor(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getLastActivityAuthorMethodKey32,
                ClpSerializer.translateInput(dMessage));

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

        return (com.liferay.portal.model.User) ClpSerializer.translateOutput(returnObj);
    }

    public void delete(com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteMethodKey33,
                ClpSerializer.translateInput(dMessage));

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

    public void update(com.ext.portlet.model.DiscussionMessage dMessage,
        java.lang.String subject, java.lang.String body)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_updateMethodKey34,
                ClpSerializer.translateInput(dMessage),
                ClpSerializer.translateInput(subject),
                ClpSerializer.translateInput(body));

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

    public com.ext.portlet.model.DiscussionCategory getCategory(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCategoryMethodKey35,
                ClpSerializer.translateInput(dMessage));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.NoSuchDiscussionCategoryException) {
                throw (com.ext.portlet.NoSuchDiscussionCategoryException) t;
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

        return (com.ext.portlet.model.DiscussionCategory) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.DiscussionCategoryGroup getCategoryGroup(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCategoryGroupMethodKey36,
                ClpSerializer.translateInput(dMessage));

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

        return (com.ext.portlet.model.DiscussionCategoryGroup) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.DiscussionMessage getThread(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getThreadMethodKey37,
                ClpSerializer.translateInput(dMessage));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.NoSuchDiscussionMessageException) {
                throw (com.ext.portlet.NoSuchDiscussionMessageException) t;
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

        return (com.ext.portlet.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.DiscussionMessageFlag> getFlags(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getFlagsMethodKey38,
                ClpSerializer.translateInput(dMessage));

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

        return (java.util.List<com.ext.portlet.model.DiscussionMessageFlag>) ClpSerializer.translateOutput(returnObj);
    }

    public void addFlag(com.ext.portlet.model.DiscussionMessage dMessage,
        java.lang.String flagType, java.lang.String data,
        com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_addFlagMethodKey39,
                ClpSerializer.translateInput(dMessage),
                ClpSerializer.translateInput(flagType),
                ClpSerializer.translateInput(data),
                ClpSerializer.translateInput(user));

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

    public void removeFlag(com.ext.portlet.model.DiscussionMessage dMessage,
        java.lang.String flagType)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_removeFlagMethodKey40,
                ClpSerializer.translateInput(dMessage),
                ClpSerializer.translateInput(flagType));

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

    public boolean hasFlag(long messageId, java.lang.String flag)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_hasFlagMethodKey41,
                messageId, ClpSerializer.translateInput(flag));

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

        return ((Boolean) returnObj).booleanValue();
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
