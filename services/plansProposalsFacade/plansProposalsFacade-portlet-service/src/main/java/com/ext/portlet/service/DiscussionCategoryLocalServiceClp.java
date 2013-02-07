package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class DiscussionCategoryLocalServiceClp
    implements DiscussionCategoryLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addDiscussionCategoryMethodKey0;
    private MethodKey _createDiscussionCategoryMethodKey1;
    private MethodKey _deleteDiscussionCategoryMethodKey2;
    private MethodKey _deleteDiscussionCategoryMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchDiscussionCategoryMethodKey8;
    private MethodKey _getDiscussionCategoryMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getDiscussionCategoriesMethodKey11;
    private MethodKey _getDiscussionCategoriesCountMethodKey12;
    private MethodKey _updateDiscussionCategoryMethodKey13;
    private MethodKey _updateDiscussionCategoryMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _getCategoriesByCategoryGroupIdMethodKey17;
    private MethodKey _getDiscussionCategoryByIdMethodKey18;
    private MethodKey _createDebateCategoryMethodKey19;
    private MethodKey _getThreadsMethodKey20;
    private MethodKey _addThreadMethodKey21;
    private MethodKey _storeMethodKey22;
    private MethodKey _getAuthorMethodKey23;
    private MethodKey _getLastActivityAuthorMethodKey24;
    private MethodKey _deleteMethodKey25;
    private MethodKey _updateMethodKey26;
    private MethodKey _getCategoryGroupMethodKey27;

    public DiscussionCategoryLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addDiscussionCategoryMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addDiscussionCategory",
                com.ext.portlet.model.DiscussionCategory.class);

        _createDiscussionCategoryMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createDiscussionCategory", long.class);

        _deleteDiscussionCategoryMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteDiscussionCategory", long.class);

        _deleteDiscussionCategoryMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteDiscussionCategory",
                com.ext.portlet.model.DiscussionCategory.class);

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

        _fetchDiscussionCategoryMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchDiscussionCategory", long.class);

        _getDiscussionCategoryMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDiscussionCategory", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getDiscussionCategoriesMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDiscussionCategories", int.class, int.class);

        _getDiscussionCategoriesCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDiscussionCategoriesCount");

        _updateDiscussionCategoryMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateDiscussionCategory",
                com.ext.portlet.model.DiscussionCategory.class);

        _updateDiscussionCategoryMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateDiscussionCategory",
                com.ext.portlet.model.DiscussionCategory.class, boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _getCategoriesByCategoryGroupIdMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCategoriesByCategoryGroupId", long.class);

        _getDiscussionCategoryByIdMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDiscussionCategoryById", long.class);

        _createDebateCategoryMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "createDebateCategory", long.class, java.lang.String.class,
                java.lang.String.class, com.liferay.portal.model.User.class);

        _getThreadsMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "getThreads", com.ext.portlet.model.DiscussionCategory.class);

        _addThreadMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "addThread", com.ext.portlet.model.DiscussionCategory.class,
                java.lang.String.class, java.lang.String.class,
                com.liferay.portal.model.User.class);

        _storeMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "store", com.ext.portlet.model.DiscussionCategory.class);

        _getAuthorMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAuthor", com.ext.portlet.model.DiscussionCategory.class);

        _getLastActivityAuthorMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
                "getLastActivityAuthor",
                com.ext.portlet.model.DiscussionCategory.class);

        _deleteMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
                "delete", com.ext.portlet.model.DiscussionCategory.class);

        _updateMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
                "update", com.ext.portlet.model.DiscussionCategory.class,
                java.lang.String.class, java.lang.String.class);

        _getCategoryGroupMethodKey27 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCategoryGroup",
                com.ext.portlet.model.DiscussionCategory.class);
    }

    public com.ext.portlet.model.DiscussionCategory addDiscussionCategory(
        com.ext.portlet.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addDiscussionCategoryMethodKey0,
                ClpSerializer.translateInput(discussionCategory));

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

    public com.ext.portlet.model.DiscussionCategory createDiscussionCategory(
        long pk) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createDiscussionCategoryMethodKey1,
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

        return (com.ext.portlet.model.DiscussionCategory) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteDiscussionCategory(long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteDiscussionCategoryMethodKey2,
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

    public void deleteDiscussionCategory(
        com.ext.portlet.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteDiscussionCategoryMethodKey3,
                ClpSerializer.translateInput(discussionCategory));

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

    public com.ext.portlet.model.DiscussionCategory fetchDiscussionCategory(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchDiscussionCategoryMethodKey8,
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

        return (com.ext.portlet.model.DiscussionCategory) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.DiscussionCategory getDiscussionCategory(
        long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getDiscussionCategoryMethodKey9,
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

        return (com.ext.portlet.model.DiscussionCategory) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.model.DiscussionCategory> getDiscussionCategories(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getDiscussionCategoriesMethodKey11,
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

        return (java.util.List<com.ext.portlet.model.DiscussionCategory>) ClpSerializer.translateOutput(returnObj);
    }

    public int getDiscussionCategoriesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getDiscussionCategoriesCountMethodKey12);

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

    public com.ext.portlet.model.DiscussionCategory updateDiscussionCategory(
        com.ext.portlet.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateDiscussionCategoryMethodKey13,
                ClpSerializer.translateInput(discussionCategory));

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

    public com.ext.portlet.model.DiscussionCategory updateDiscussionCategory(
        com.ext.portlet.model.DiscussionCategory discussionCategory,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateDiscussionCategoryMethodKey14,
                ClpSerializer.translateInput(discussionCategory), merge);

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

    public java.util.List<com.ext.portlet.model.DiscussionCategory> getCategoriesByCategoryGroupId(
        long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCategoriesByCategoryGroupIdMethodKey17,
                categoryGroupId);

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

    public com.ext.portlet.model.DiscussionCategory getDiscussionCategoryById(
        long categoryId)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getDiscussionCategoryByIdMethodKey18,
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

    public com.ext.portlet.model.DiscussionCategory createDebateCategory(
        long categoryGroupId, java.lang.String name,
        java.lang.String description, com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createDebateCategoryMethodKey19,
                categoryGroupId, ClpSerializer.translateInput(name),
                ClpSerializer.translateInput(description),
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

        return (com.ext.portlet.model.DiscussionCategory) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.DiscussionMessage> getThreads(
        com.ext.portlet.model.DiscussionCategory dCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getThreadsMethodKey20,
                ClpSerializer.translateInput(dCategory));

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

    public com.ext.portlet.model.DiscussionMessage addThread(
        com.ext.portlet.model.DiscussionCategory dCategory,
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addThreadMethodKey21,
                ClpSerializer.translateInput(dCategory),
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

    public void store(com.ext.portlet.model.DiscussionCategory dCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_storeMethodKey22,
                ClpSerializer.translateInput(dCategory));

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

    public com.liferay.portal.model.User getAuthor(
        com.ext.portlet.model.DiscussionCategory dCategory)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getAuthorMethodKey23,
                ClpSerializer.translateInput(dCategory));

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
        com.ext.portlet.model.DiscussionCategory dCategory)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getLastActivityAuthorMethodKey24,
                ClpSerializer.translateInput(dCategory));

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

    public void delete(com.ext.portlet.model.DiscussionCategory dCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteMethodKey25,
                ClpSerializer.translateInput(dCategory));

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

    public void update(com.ext.portlet.model.DiscussionCategory dCategory,
        java.lang.String name, java.lang.String description)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_updateMethodKey26,
                ClpSerializer.translateInput(dCategory),
                ClpSerializer.translateInput(name),
                ClpSerializer.translateInput(description));

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

    public com.ext.portlet.model.DiscussionCategoryGroup getCategoryGroup(
        com.ext.portlet.model.DiscussionCategory dCategory)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCategoryGroupMethodKey27,
                ClpSerializer.translateInput(dCategory));

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

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
