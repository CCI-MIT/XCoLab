package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class ModelInputItemLocalServiceClp implements ModelInputItemLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addModelInputItemMethodKey0;
    private MethodKey _createModelInputItemMethodKey1;
    private MethodKey _deleteModelInputItemMethodKey2;
    private MethodKey _deleteModelInputItemMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchModelInputItemMethodKey8;
    private MethodKey _getModelInputItemMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getModelInputItemsMethodKey11;
    private MethodKey _getModelInputItemsCountMethodKey12;
    private MethodKey _updateModelInputItemMethodKey13;
    private MethodKey _updateModelInputItemMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _getItemsForModelMethodKey17;
    private MethodKey _getItemForMetaDataMethodKey18;
    private MethodKey _getItemForGroupIdMethodKey19;
    private MethodKey _getMetaDataMethodKey20;
    private MethodKey _getModelMethodKey21;
    private MethodKey _getPropertyMapMethodKey22;
    private MethodKey _savePropertiesMethodKey23;
    private MethodKey _storeMethodKey24;

    public ModelInputItemLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addModelInputItemMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addModelInputItem", com.ext.portlet.model.ModelInputItem.class);

        _createModelInputItemMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createModelInputItem", long.class);

        _deleteModelInputItemMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteModelInputItem", long.class);

        _deleteModelInputItemMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteModelInputItem",
                com.ext.portlet.model.ModelInputItem.class);

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

        _fetchModelInputItemMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchModelInputItem", long.class);

        _getModelInputItemMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getModelInputItem", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getModelInputItemsMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getModelInputItems", int.class, int.class);

        _getModelInputItemsCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getModelInputItemsCount");

        _updateModelInputItemMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateModelInputItem",
                com.ext.portlet.model.ModelInputItem.class);

        _updateModelInputItemMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateModelInputItem",
                com.ext.portlet.model.ModelInputItem.class, boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _getItemsForModelMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "getItemsForModel",
                edu.mit.cci.simulation.client.Simulation.class);

        _getItemForMetaDataMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "getItemForMetaData", java.lang.Long.class,
                edu.mit.cci.simulation.client.MetaData.class);

        _getItemForGroupIdMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "getItemForGroupId", java.lang.Long.class);

        _getMetaDataMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMetaData", com.ext.portlet.model.ModelInputItem.class);

        _getModelMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "getModel", com.ext.portlet.model.ModelInputItem.class);

        _getPropertyMapMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPropertyMap", com.ext.portlet.model.ModelInputItem.class);

        _savePropertiesMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
                "saveProperties", com.ext.portlet.model.ModelInputItem.class,
                java.util.Map.class);

        _storeMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
                "store", com.ext.portlet.model.ModelInputItem.class);
    }

    public com.ext.portlet.model.ModelInputItem addModelInputItem(
        com.ext.portlet.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addModelInputItemMethodKey0,
                ClpSerializer.translateInput(modelInputItem));

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

        return (com.ext.portlet.model.ModelInputItem) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ModelInputItem createModelInputItem(
        long modelInputItemPK) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createModelInputItemMethodKey1,
                modelInputItemPK);

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

        return (com.ext.portlet.model.ModelInputItem) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteModelInputItem(long modelInputItemPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteModelInputItemMethodKey2,
                modelInputItemPK);

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

    public void deleteModelInputItem(
        com.ext.portlet.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteModelInputItemMethodKey3,
                ClpSerializer.translateInput(modelInputItem));

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

    public com.ext.portlet.model.ModelInputItem fetchModelInputItem(
        long modelInputItemPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchModelInputItemMethodKey8,
                modelInputItemPK);

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

        return (com.ext.portlet.model.ModelInputItem) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ModelInputItem getModelInputItem(
        long modelInputItemPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getModelInputItemMethodKey9,
                modelInputItemPK);

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

        return (com.ext.portlet.model.ModelInputItem) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.model.ModelInputItem> getModelInputItems(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getModelInputItemsMethodKey11,
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

        return (java.util.List<com.ext.portlet.model.ModelInputItem>) ClpSerializer.translateOutput(returnObj);
    }

    public int getModelInputItemsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getModelInputItemsCountMethodKey12);

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

    public com.ext.portlet.model.ModelInputItem updateModelInputItem(
        com.ext.portlet.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateModelInputItemMethodKey13,
                ClpSerializer.translateInput(modelInputItem));

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

        return (com.ext.portlet.model.ModelInputItem) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ModelInputItem updateModelInputItem(
        com.ext.portlet.model.ModelInputItem modelInputItem, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateModelInputItemMethodKey14,
                ClpSerializer.translateInput(modelInputItem), merge);

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

        return (com.ext.portlet.model.ModelInputItem) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.model.ModelInputItem> getItemsForModel(
        edu.mit.cci.simulation.client.Simulation sim) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getItemsForModelMethodKey17,
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

        return (java.util.List<com.ext.portlet.model.ModelInputItem>) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ModelInputItem getItemForMetaData(
        java.lang.Long modelId, edu.mit.cci.simulation.client.MetaData md) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getItemForMetaDataMethodKey18,
                ClpSerializer.translateInput(modelId),
                ClpSerializer.translateInput(md));

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

        return (com.ext.portlet.model.ModelInputItem) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.ModelInputItem> getItemForGroupId(
        java.lang.Long groupid) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getItemForGroupIdMethodKey19,
                ClpSerializer.translateInput(groupid));

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

    public edu.mit.cci.simulation.client.MetaData getMetaData(
        com.ext.portlet.model.ModelInputItem item)
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getMetaDataMethodKey20,
                ClpSerializer.translateInput(item));

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

        return (edu.mit.cci.simulation.client.MetaData) ClpSerializer.translateOutput(returnObj);
    }

    public edu.mit.cci.simulation.client.Simulation getModel(
        com.ext.portlet.model.ModelInputItem item)
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getModelMethodKey21,
                ClpSerializer.translateInput(item));

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

        return (edu.mit.cci.simulation.client.Simulation) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.Map<java.lang.String, java.lang.String> getPropertyMap(
        com.ext.portlet.model.ModelInputItem item) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPropertyMapMethodKey22,
                ClpSerializer.translateInput(item));

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

        return (java.util.Map<java.lang.String, java.lang.String>) ClpSerializer.translateOutput(returnObj);
    }

    public void saveProperties(com.ext.portlet.model.ModelInputItem item,
        java.util.Map<java.lang.String, java.lang.String> props)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_savePropertiesMethodKey23,
                ClpSerializer.translateInput(item),
                ClpSerializer.translateInput(props));

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

    public void store(com.ext.portlet.model.ModelInputItem item)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_storeMethodKey24,
                ClpSerializer.translateInput(item));

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
