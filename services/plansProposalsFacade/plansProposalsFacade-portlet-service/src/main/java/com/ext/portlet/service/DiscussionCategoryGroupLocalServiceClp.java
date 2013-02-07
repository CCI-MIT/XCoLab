package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class DiscussionCategoryGroupLocalServiceClp
    implements DiscussionCategoryGroupLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addDiscussionCategoryGroupMethodKey0;
    private MethodKey _createDiscussionCategoryGroupMethodKey1;
    private MethodKey _deleteDiscussionCategoryGroupMethodKey2;
    private MethodKey _deleteDiscussionCategoryGroupMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchDiscussionCategoryGroupMethodKey8;
    private MethodKey _getDiscussionCategoryGroupMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getDiscussionCategoryGroupsMethodKey11;
    private MethodKey _getDiscussionCategoryGroupsCountMethodKey12;
    private MethodKey _updateDiscussionCategoryGroupMethodKey13;
    private MethodKey _updateDiscussionCategoryGroupMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _createDiscussionCategoryGroupMethodKey17;
    private MethodKey _getCategoryByIdMethodKey18;
    private MethodKey _getThreadByIdMethodKey19;
    private MethodKey _getCategoriesMethodKey20;
    private MethodKey _addCategoryMethodKey21;
    private MethodKey _storeMethodKey22;
    private MethodKey _getCommentThreadMethodKey23;
    private MethodKey _addCommentMethodKey24;
    private MethodKey _getCommentsCountMethodKey25;
    private MethodKey _copyEverythingMethodKey26;

    public DiscussionCategoryGroupLocalServiceClp(
        ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addDiscussionCategoryGroupMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addDiscussionCategoryGroup",
                com.ext.portlet.model.DiscussionCategoryGroup.class);

        _createDiscussionCategoryGroupMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createDiscussionCategoryGroup", long.class);

        _deleteDiscussionCategoryGroupMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteDiscussionCategoryGroup", long.class);

        _deleteDiscussionCategoryGroupMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteDiscussionCategoryGroup",
                com.ext.portlet.model.DiscussionCategoryGroup.class);

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

        _fetchDiscussionCategoryGroupMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchDiscussionCategoryGroup", long.class);

        _getDiscussionCategoryGroupMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDiscussionCategoryGroup", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getDiscussionCategoryGroupsMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDiscussionCategoryGroups", int.class, int.class);

        _getDiscussionCategoryGroupsCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDiscussionCategoryGroupsCount");

        _updateDiscussionCategoryGroupMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateDiscussionCategoryGroup",
                com.ext.portlet.model.DiscussionCategoryGroup.class);

        _updateDiscussionCategoryGroupMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateDiscussionCategoryGroup",
                com.ext.portlet.model.DiscussionCategoryGroup.class,
                boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _createDiscussionCategoryGroupMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "createDiscussionCategoryGroup", java.lang.String.class);

        _getCategoryByIdMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCategoryById", long.class);

        _getThreadByIdMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "getThreadById", long.class);

        _getCategoriesMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCategories",
                com.ext.portlet.model.DiscussionCategoryGroup.class);

        _addCategoryMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "addCategory",
                com.ext.portlet.model.DiscussionCategoryGroup.class,
                java.lang.String.class, java.lang.String.class,
                com.liferay.portal.model.User.class);

        _storeMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "store", com.ext.portlet.model.DiscussionCategoryGroup.class);

        _getCommentThreadMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCommentThread",
                com.ext.portlet.model.DiscussionCategoryGroup.class);

        _addCommentMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
                "addComment",
                com.ext.portlet.model.DiscussionCategoryGroup.class,
                java.lang.String.class, java.lang.String.class,
                com.liferay.portal.model.User.class);

        _getCommentsCountMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCommentsCount",
                com.ext.portlet.model.DiscussionCategoryGroup.class);

        _copyEverythingMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
                "copyEverything",
                com.ext.portlet.model.DiscussionCategoryGroup.class,
                com.ext.portlet.model.DiscussionCategoryGroup.class);
    }

    public com.ext.portlet.model.DiscussionCategoryGroup addDiscussionCategoryGroup(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addDiscussionCategoryGroupMethodKey0,
                ClpSerializer.translateInput(discussionCategoryGroup));

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

        return (com.ext.portlet.model.DiscussionCategoryGroup) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.DiscussionCategoryGroup createDiscussionCategoryGroup(
        long id) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createDiscussionCategoryGroupMethodKey1,
                id);

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

        return (com.ext.portlet.model.DiscussionCategoryGroup) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteDiscussionCategoryGroup(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteDiscussionCategoryGroupMethodKey2,
                id);

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

    public void deleteDiscussionCategoryGroup(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteDiscussionCategoryGroupMethodKey3,
                ClpSerializer.translateInput(discussionCategoryGroup));

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

    public com.ext.portlet.model.DiscussionCategoryGroup fetchDiscussionCategoryGroup(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchDiscussionCategoryGroupMethodKey8,
                id);

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

        return (com.ext.portlet.model.DiscussionCategoryGroup) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.DiscussionCategoryGroup getDiscussionCategoryGroup(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getDiscussionCategoryGroupMethodKey9,
                id);

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

    public java.util.List<com.ext.portlet.model.DiscussionCategoryGroup> getDiscussionCategoryGroups(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getDiscussionCategoryGroupsMethodKey11,
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

        return (java.util.List<com.ext.portlet.model.DiscussionCategoryGroup>) ClpSerializer.translateOutput(returnObj);
    }

    public int getDiscussionCategoryGroupsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getDiscussionCategoryGroupsCountMethodKey12);

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

    public com.ext.portlet.model.DiscussionCategoryGroup updateDiscussionCategoryGroup(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateDiscussionCategoryGroupMethodKey13,
                ClpSerializer.translateInput(discussionCategoryGroup));

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

        return (com.ext.portlet.model.DiscussionCategoryGroup) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.DiscussionCategoryGroup updateDiscussionCategoryGroup(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateDiscussionCategoryGroupMethodKey14,
                ClpSerializer.translateInput(discussionCategoryGroup), merge);

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

        return (com.ext.portlet.model.DiscussionCategoryGroup) ClpSerializer.translateOutput(returnObj);
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

    public com.ext.portlet.model.DiscussionCategoryGroup createDiscussionCategoryGroup(
        java.lang.String description)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createDiscussionCategoryGroupMethodKey17,
                ClpSerializer.translateInput(description));

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

        return (com.ext.portlet.model.DiscussionCategoryGroup) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.DiscussionCategory getCategoryById(
        long categoryId)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCategoryByIdMethodKey18,
                categoryId);

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

    public com.ext.portlet.model.DiscussionMessage getThreadById(long threadId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getThreadByIdMethodKey19,
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

    public java.util.List<com.ext.portlet.model.DiscussionCategory> getCategories(
        com.ext.portlet.model.DiscussionCategoryGroup dcg)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCategoriesMethodKey20,
                ClpSerializer.translateInput(dcg));

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

        return (java.util.List<com.ext.portlet.model.DiscussionCategory>) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.DiscussionCategory addCategory(
        com.ext.portlet.model.DiscussionCategoryGroup dcg,
        java.lang.String name, java.lang.String description,
        com.liferay.portal.model.User creator)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addCategoryMethodKey21,
                ClpSerializer.translateInput(dcg),
                ClpSerializer.translateInput(name),
                ClpSerializer.translateInput(description),
                ClpSerializer.translateInput(creator));

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

        return (com.ext.portlet.model.DiscussionCategory) ClpSerializer.translateOutput(returnObj);
    }

    public void store(com.ext.portlet.model.DiscussionCategoryGroup dcg)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_storeMethodKey22,
                ClpSerializer.translateInput(dcg));

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

    public com.ext.portlet.model.DiscussionMessage getCommentThread(
        com.ext.portlet.model.DiscussionCategoryGroup dcg)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCommentThreadMethodKey23,
                ClpSerializer.translateInput(dcg));

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

    public com.ext.portlet.model.DiscussionMessage addComment(
        com.ext.portlet.model.DiscussionCategoryGroup dcg,
        java.lang.String title, java.lang.String description,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addCommentMethodKey24,
                ClpSerializer.translateInput(dcg),
                ClpSerializer.translateInput(title),
                ClpSerializer.translateInput(description),
                ClpSerializer.translateInput(author));

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

    public int getCommentsCount(
        com.ext.portlet.model.DiscussionCategoryGroup dcg)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCommentsCountMethodKey25,
                ClpSerializer.translateInput(dcg));

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

        return ((Integer) returnObj).intValue();
    }

    public void copyEverything(
        com.ext.portlet.model.DiscussionCategoryGroup dcg,
        com.ext.portlet.model.DiscussionCategoryGroup source)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_copyEverythingMethodKey26,
                ClpSerializer.translateInput(dcg),
                ClpSerializer.translateInput(source));

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

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
