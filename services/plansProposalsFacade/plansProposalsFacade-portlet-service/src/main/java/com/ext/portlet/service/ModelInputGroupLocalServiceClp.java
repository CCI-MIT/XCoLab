package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class ModelInputGroupLocalServiceClp
    implements ModelInputGroupLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addModelInputGroupMethodKey0;
    private MethodKey _createModelInputGroupMethodKey1;
    private MethodKey _deleteModelInputGroupMethodKey2;
    private MethodKey _deleteModelInputGroupMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchModelInputGroupMethodKey8;
    private MethodKey _getModelInputGroupMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getModelInputGroupsMethodKey11;
    private MethodKey _getModelInputGroupsCountMethodKey12;
    private MethodKey _updateModelInputGroupMethodKey13;
    private MethodKey _updateModelInputGroupMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _getInputGroupsMethodKey17;
    private MethodKey _getChildGroupsMethodKey18;
    private MethodKey _getInputItemsMethodKey19;
    private MethodKey _getParentMethodKey20;
    private MethodKey _getModelMethodKey21;
    private MethodKey _getMetaDataMethodKey22;

    public ModelInputGroupLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addModelInputGroupMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addModelInputGroup",
                com.ext.portlet.model.ModelInputGroup.class);

        _createModelInputGroupMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createModelInputGroup", long.class);

        _deleteModelInputGroupMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteModelInputGroup", long.class);

        _deleteModelInputGroupMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteModelInputGroup",
                com.ext.portlet.model.ModelInputGroup.class);

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

        _fetchModelInputGroupMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchModelInputGroup", long.class);

        _getModelInputGroupMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getModelInputGroup", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getModelInputGroupsMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getModelInputGroups", int.class, int.class);

        _getModelInputGroupsCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getModelInputGroupsCount");

        _updateModelInputGroupMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateModelInputGroup",
                com.ext.portlet.model.ModelInputGroup.class);

        _updateModelInputGroupMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateModelInputGroup",
                com.ext.portlet.model.ModelInputGroup.class, boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _getInputGroupsMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "getInputGroups", edu.mit.cci.roma.client.Simulation.class);

        _getChildGroupsMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "getChildGroups", com.ext.portlet.model.ModelInputGroup.class);

        _getInputItemsMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "getInputItems", com.ext.portlet.model.ModelInputGroup.class);

        _getParentMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "getParent", com.ext.portlet.model.ModelInputGroup.class);

        _getModelMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "getModel", com.ext.portlet.model.ModelInputGroup.class);

        _getMetaDataMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMetaData", com.ext.portlet.model.ModelInputGroup.class);
    }

    public com.ext.portlet.model.ModelInputGroup addModelInputGroup(
        com.ext.portlet.model.ModelInputGroup modelInputGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addModelInputGroupMethodKey0,
                ClpSerializer.translateInput(modelInputGroup));

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

        return (com.ext.portlet.model.ModelInputGroup) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ModelInputGroup createModelInputGroup(
        long modelInputGroupPK) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createModelInputGroupMethodKey1,
                modelInputGroupPK);

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

        return (com.ext.portlet.model.ModelInputGroup) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteModelInputGroup(long modelInputGroupPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteModelInputGroupMethodKey2,
                modelInputGroupPK);

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

    public void deleteModelInputGroup(
        com.ext.portlet.model.ModelInputGroup modelInputGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteModelInputGroupMethodKey3,
                ClpSerializer.translateInput(modelInputGroup));

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

    public com.ext.portlet.model.ModelInputGroup fetchModelInputGroup(
        long modelInputGroupPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchModelInputGroupMethodKey8,
                modelInputGroupPK);

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

        return (com.ext.portlet.model.ModelInputGroup) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ModelInputGroup getModelInputGroup(
        long modelInputGroupPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getModelInputGroupMethodKey9,
                modelInputGroupPK);

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

        return (com.ext.portlet.model.ModelInputGroup) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.model.ModelInputGroup> getModelInputGroups(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getModelInputGroupsMethodKey11,
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

        return (java.util.List<com.ext.portlet.model.ModelInputGroup>) ClpSerializer.translateOutput(returnObj);
    }

    public int getModelInputGroupsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getModelInputGroupsCountMethodKey12);

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

    public com.ext.portlet.model.ModelInputGroup updateModelInputGroup(
        com.ext.portlet.model.ModelInputGroup modelInputGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateModelInputGroupMethodKey13,
                ClpSerializer.translateInput(modelInputGroup));

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

        return (com.ext.portlet.model.ModelInputGroup) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ModelInputGroup updateModelInputGroup(
        com.ext.portlet.model.ModelInputGroup modelInputGroup, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateModelInputGroupMethodKey14,
                ClpSerializer.translateInput(modelInputGroup), merge);

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

        return (com.ext.portlet.model.ModelInputGroup) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.model.ModelInputGroup> getInputGroups(
        edu.mit.cci.roma.client.Simulation sim) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getInputGroupsMethodKey17,
                ClpSerializer.translateInput(sim));

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

        return (java.util.List<com.ext.portlet.model.ModelInputGroup>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.ModelInputGroup> getChildGroups(
        com.ext.portlet.model.ModelInputGroup group) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getChildGroupsMethodKey18,
                ClpSerializer.translateInput(group));

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

        return (java.util.List<com.ext.portlet.model.ModelInputGroup>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.ModelInputItem> getInputItems(
        com.ext.portlet.model.ModelInputGroup group) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getInputItemsMethodKey19,
                ClpSerializer.translateInput(group));

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

        return (java.util.List<com.ext.portlet.model.ModelInputItem>) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ModelInputGroup getParent(
        com.ext.portlet.model.ModelInputGroup group) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getParentMethodKey20,
                ClpSerializer.translateInput(group));

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

        return (com.ext.portlet.model.ModelInputGroup) ClpSerializer.translateOutput(returnObj);
    }

    public edu.mit.cci.roma.client.Simulation getModel(
        com.ext.portlet.model.ModelInputGroup group)
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getModelMethodKey21,
                ClpSerializer.translateInput(group));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof java.io.IOException) {
                throw (java.io.IOException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (edu.mit.cci.roma.client.Simulation) ClpSerializer.translateOutput(returnObj);
    }

    public edu.mit.cci.roma.client.MetaData getMetaData(
        com.ext.portlet.model.ModelInputGroup group)
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getMetaDataMethodKey22,
                ClpSerializer.translateInput(group));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof java.io.IOException) {
                throw (java.io.IOException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (edu.mit.cci.roma.client.MetaData) ClpSerializer.translateOutput(returnObj);
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
