package com.ext.portlet.discussions.service;

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

    public DiscussionMessageLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addDiscussionMessageMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addDiscussionMessage",
                com.ext.portlet.discussions.model.DiscussionMessage.class);

        _createDiscussionMessageMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createDiscussionMessage", java.lang.Long.class);

        _deleteDiscussionMessageMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteDiscussionMessage", java.lang.Long.class);

        _deleteDiscussionMessageMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteDiscussionMessage",
                com.ext.portlet.discussions.model.DiscussionMessage.class);

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
                "fetchDiscussionMessage", java.lang.Long.class);

        _getDiscussionMessageMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDiscussionMessage", java.lang.Long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getDiscussionMessagesMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDiscussionMessages", int.class, int.class);

        _getDiscussionMessagesCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDiscussionMessagesCount");

        _updateDiscussionMessageMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateDiscussionMessage",
                com.ext.portlet.discussions.model.DiscussionMessage.class);

        _updateDiscussionMessageMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateDiscussionMessage",
                com.ext.portlet.discussions.model.DiscussionMessage.class,
                boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _getThreadsByCategoryMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "getThreadsByCategory", java.lang.Long.class);

        _getThreadMessagesMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "getThreadMessages", java.lang.Long.class);

        _getThreadMessagesCountMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "getThreadMessagesCount", java.lang.Long.class);

        _getThreadByThreadIdMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "getThreadByThreadId", java.lang.Long.class);

        _addThreadMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "addThread", java.lang.Long.class, java.lang.Long.class,
                java.lang.String.class, java.lang.String.class,
                com.liferay.portal.model.User.class);

        _addMessageMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "addMessage", java.lang.Long.class, java.lang.Long.class,
                java.lang.Long.class, java.lang.String.class,
                java.lang.String.class, com.liferay.portal.model.User.class);

        _searchMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
                "search", java.lang.String.class, java.lang.Long.class);

        _getMessageByMessageIdMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMessageByMessageId", java.lang.Long.class);

        _reIndexMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
                "reIndex");

        _reIndexMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
                "reIndex", long.class);
    }

    public com.ext.portlet.discussions.model.DiscussionMessage addDiscussionMessage(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage)
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

        return (com.ext.portlet.discussions.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.discussions.model.DiscussionMessage createDiscussionMessage(
        java.lang.Long pk) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createDiscussionMessageMethodKey1,
                ClpSerializer.translateInput(pk));

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

        return (com.ext.portlet.discussions.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteDiscussionMessage(java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteDiscussionMessageMethodKey2,
                ClpSerializer.translateInput(pk));

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
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage)
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

    public com.ext.portlet.discussions.model.DiscussionMessage fetchDiscussionMessage(
        java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchDiscussionMessageMethodKey8,
                ClpSerializer.translateInput(pk));

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

        return (com.ext.portlet.discussions.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.discussions.model.DiscussionMessage getDiscussionMessage(
        java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getDiscussionMessageMethodKey9,
                ClpSerializer.translateInput(pk));

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

        return (com.ext.portlet.discussions.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getDiscussionMessages(
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

        return (java.util.List<com.ext.portlet.discussions.model.DiscussionMessage>) ClpSerializer.translateOutput(returnObj);
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

    public com.ext.portlet.discussions.model.DiscussionMessage updateDiscussionMessage(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage)
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

        return (com.ext.portlet.discussions.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.discussions.model.DiscussionMessage updateDiscussionMessage(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage,
        boolean merge)
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

        return (com.ext.portlet.discussions.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getThreadsByCategory(
        java.lang.Long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getThreadsByCategoryMethodKey17,
                ClpSerializer.translateInput(categoryId));

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

        return (java.util.List<com.ext.portlet.discussions.model.DiscussionMessage>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getThreadMessages(
        java.lang.Long threadId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getThreadMessagesMethodKey18,
                ClpSerializer.translateInput(threadId));

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

        return (java.util.List<com.ext.portlet.discussions.model.DiscussionMessage>) ClpSerializer.translateOutput(returnObj);
    }

    public int getThreadMessagesCount(java.lang.Long threadId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getThreadMessagesCountMethodKey19,
                ClpSerializer.translateInput(threadId));

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

    public com.ext.portlet.discussions.model.DiscussionMessage getThreadByThreadId(
        java.lang.Long threadId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getThreadByThreadIdMethodKey20,
                ClpSerializer.translateInput(threadId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.discussions.NoSuchDiscussionMessageException) {
                throw (com.ext.portlet.discussions.NoSuchDiscussionMessageException) t;
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

        return (com.ext.portlet.discussions.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.discussions.model.DiscussionMessage addThread(
        java.lang.Long categoryGroupId, java.lang.Long categoryId,
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addThreadMethodKey21,
                ClpSerializer.translateInput(categoryGroupId),
                ClpSerializer.translateInput(categoryId),
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

        return (com.ext.portlet.discussions.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.discussions.model.DiscussionMessage addMessage(
        java.lang.Long categoryGroupId, java.lang.Long categoryId,
        java.lang.Long threadId, java.lang.String subject,
        java.lang.String body, com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addMessageMethodKey22,
                ClpSerializer.translateInput(categoryGroupId),
                ClpSerializer.translateInput(categoryId),
                ClpSerializer.translateInput(threadId),
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

        return (com.ext.portlet.discussions.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> search(
        java.lang.String query, java.lang.Long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_searchMethodKey23,
                ClpSerializer.translateInput(query),
                ClpSerializer.translateInput(categoryGroupId));

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

        return (java.util.List<com.ext.portlet.discussions.model.DiscussionMessage>) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.discussions.model.DiscussionMessage getMessageByMessageId(
        java.lang.Long messageId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getMessageByMessageIdMethodKey24,
                ClpSerializer.translateInput(messageId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.discussions.NoSuchDiscussionMessageException) {
                throw (com.ext.portlet.discussions.NoSuchDiscussionMessageException) t;
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

        return (com.ext.portlet.discussions.model.DiscussionMessage) ClpSerializer.translateOutput(returnObj);
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

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
