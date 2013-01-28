package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class ModelGlobalPreferenceLocalServiceClp
    implements ModelGlobalPreferenceLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addModelGlobalPreferenceMethodKey0;
    private MethodKey _createModelGlobalPreferenceMethodKey1;
    private MethodKey _deleteModelGlobalPreferenceMethodKey2;
    private MethodKey _deleteModelGlobalPreferenceMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchModelGlobalPreferenceMethodKey8;
    private MethodKey _getModelGlobalPreferenceMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getModelGlobalPreferencesMethodKey11;
    private MethodKey _getModelGlobalPreferencesCountMethodKey12;
    private MethodKey _updateModelGlobalPreferenceMethodKey13;
    private MethodKey _updateModelGlobalPreferenceMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _isVisibleMethodKey17;
    private MethodKey _setVisibleMethodKey18;
    private MethodKey _getWeightMethodKey19;
    private MethodKey _setWeightMethodKey20;
    private MethodKey _getExpertEvaluationPageIdMethodKey21;
    private MethodKey _setExpertEvaluationPageIdMethodKey22;
    private MethodKey _findByCategoryMethodKey23;
    private MethodKey _getCategoryMethodKey24;
    private MethodKey _updateModelCategoryMethodKey25;

    public ModelGlobalPreferenceLocalServiceClp(
        ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addModelGlobalPreferenceMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addModelGlobalPreference",
                com.ext.portlet.model.ModelGlobalPreference.class);

        _createModelGlobalPreferenceMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createModelGlobalPreference", long.class);

        _deleteModelGlobalPreferenceMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteModelGlobalPreference", long.class);

        _deleteModelGlobalPreferenceMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteModelGlobalPreference",
                com.ext.portlet.model.ModelGlobalPreference.class);

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

        _fetchModelGlobalPreferenceMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchModelGlobalPreference", long.class);

        _getModelGlobalPreferenceMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getModelGlobalPreference", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getModelGlobalPreferencesMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getModelGlobalPreferences", int.class, int.class);

        _getModelGlobalPreferencesCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getModelGlobalPreferencesCount");

        _updateModelGlobalPreferenceMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateModelGlobalPreference",
                com.ext.portlet.model.ModelGlobalPreference.class);

        _updateModelGlobalPreferenceMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateModelGlobalPreference",
                com.ext.portlet.model.ModelGlobalPreference.class, boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _isVisibleMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "isVisible", edu.mit.cci.simulation.client.Simulation.class);

        _setVisibleMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "setVisible", edu.mit.cci.simulation.client.Simulation.class,
                boolean.class);

        _getWeightMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "getWeight", edu.mit.cci.simulation.client.Simulation.class);

        _setWeightMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "setWeight", edu.mit.cci.simulation.client.Simulation.class,
                int.class);

        _getExpertEvaluationPageIdMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "getExpertEvaluationPageId",
                edu.mit.cci.simulation.client.Simulation.class);

        _setExpertEvaluationPageIdMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "setExpertEvaluationPageId",
                edu.mit.cci.simulation.client.Simulation.class,
                java.lang.Long.class);

        _findByCategoryMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
                "findByCategory", com.ext.portlet.model.ModelCategory.class);

        _getCategoryMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCategory", edu.mit.cci.simulation.client.Simulation.class);

        _updateModelCategoryMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateModelCategory",
                com.ext.portlet.model.ModelCategory.class,
                edu.mit.cci.simulation.client.Simulation.class);
    }

    public com.ext.portlet.model.ModelGlobalPreference addModelGlobalPreference(
        com.ext.portlet.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addModelGlobalPreferenceMethodKey0,
                ClpSerializer.translateInput(modelGlobalPreference));

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

        return (com.ext.portlet.model.ModelGlobalPreference) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ModelGlobalPreference createModelGlobalPreference(
        long modelGlobalPreferencePK) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createModelGlobalPreferenceMethodKey1,
                modelGlobalPreferencePK);

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

        return (com.ext.portlet.model.ModelGlobalPreference) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteModelGlobalPreference(long modelGlobalPreferencePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteModelGlobalPreferenceMethodKey2,
                modelGlobalPreferencePK);

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

    public void deleteModelGlobalPreference(
        com.ext.portlet.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteModelGlobalPreferenceMethodKey3,
                ClpSerializer.translateInput(modelGlobalPreference));

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

    public com.ext.portlet.model.ModelGlobalPreference fetchModelGlobalPreference(
        long modelGlobalPreferencePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchModelGlobalPreferenceMethodKey8,
                modelGlobalPreferencePK);

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

        return (com.ext.portlet.model.ModelGlobalPreference) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ModelGlobalPreference getModelGlobalPreference(
        long modelGlobalPreferencePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getModelGlobalPreferenceMethodKey9,
                modelGlobalPreferencePK);

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

        return (com.ext.portlet.model.ModelGlobalPreference) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.model.ModelGlobalPreference> getModelGlobalPreferences(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getModelGlobalPreferencesMethodKey11,
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

        return (java.util.List<com.ext.portlet.model.ModelGlobalPreference>) ClpSerializer.translateOutput(returnObj);
    }

    public int getModelGlobalPreferencesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getModelGlobalPreferencesCountMethodKey12);

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

    public com.ext.portlet.model.ModelGlobalPreference updateModelGlobalPreference(
        com.ext.portlet.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateModelGlobalPreferenceMethodKey13,
                ClpSerializer.translateInput(modelGlobalPreference));

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

        return (com.ext.portlet.model.ModelGlobalPreference) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ModelGlobalPreference updateModelGlobalPreference(
        com.ext.portlet.model.ModelGlobalPreference modelGlobalPreference,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateModelGlobalPreferenceMethodKey14,
                ClpSerializer.translateInput(modelGlobalPreference), merge);

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

        return (com.ext.portlet.model.ModelGlobalPreference) ClpSerializer.translateOutput(returnObj);
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

    public boolean isVisible(edu.mit.cci.simulation.client.Simulation s)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_isVisibleMethodKey17,
                ClpSerializer.translateInput(s));

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

    public void setVisible(edu.mit.cci.simulation.client.Simulation s,
        boolean visible)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setVisibleMethodKey18,
                ClpSerializer.translateInput(s), visible);

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

    public int getWeight(edu.mit.cci.simulation.client.Simulation s)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getWeightMethodKey19,
                ClpSerializer.translateInput(s));

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

    public void setWeight(edu.mit.cci.simulation.client.Simulation s, int weight)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setWeightMethodKey20,
                ClpSerializer.translateInput(s), weight);

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

    public java.lang.Long getExpertEvaluationPageId(
        edu.mit.cci.simulation.client.Simulation s)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getExpertEvaluationPageIdMethodKey21,
                ClpSerializer.translateInput(s));

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

        return (java.lang.Long) ClpSerializer.translateOutput(returnObj);
    }

    public void setExpertEvaluationPageId(
        edu.mit.cci.simulation.client.Simulation s, java.lang.Long pageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setExpertEvaluationPageIdMethodKey22,
                ClpSerializer.translateInput(s),
                ClpSerializer.translateInput(pageId));

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

    public java.util.List<com.ext.portlet.model.ModelGlobalPreference> findByCategory(
        com.ext.portlet.model.ModelCategory category)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findByCategoryMethodKey23,
                ClpSerializer.translateInput(category));

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

        return (java.util.List<com.ext.portlet.model.ModelGlobalPreference>) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ModelCategory getCategory(
        edu.mit.cci.simulation.client.Simulation sim)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCategoryMethodKey24,
                ClpSerializer.translateInput(sim));

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

        return (com.ext.portlet.model.ModelCategory) ClpSerializer.translateOutput(returnObj);
    }

    public void updateModelCategory(com.ext.portlet.model.ModelCategory cat,
        edu.mit.cci.simulation.client.Simulation sim)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_updateModelCategoryMethodKey25,
                ClpSerializer.translateInput(cat),
                ClpSerializer.translateInput(sim));

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
