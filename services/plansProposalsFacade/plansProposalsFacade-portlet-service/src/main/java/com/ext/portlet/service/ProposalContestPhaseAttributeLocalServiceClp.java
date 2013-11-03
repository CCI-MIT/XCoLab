package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class ProposalContestPhaseAttributeLocalServiceClp
    implements ProposalContestPhaseAttributeLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addProposalContestPhaseAttributeMethodKey0;
    private MethodKey _createProposalContestPhaseAttributeMethodKey1;
    private MethodKey _deleteProposalContestPhaseAttributeMethodKey2;
    private MethodKey _deleteProposalContestPhaseAttributeMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchProposalContestPhaseAttributeMethodKey8;
    private MethodKey _getProposalContestPhaseAttributeMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getProposalContestPhaseAttributesMethodKey11;
    private MethodKey _getProposalContestPhaseAttributesCountMethodKey12;
    private MethodKey _updateProposalContestPhaseAttributeMethodKey13;
    private MethodKey _updateProposalContestPhaseAttributeMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _getProposalContestPhaseAttributesMethodKey17;
    private MethodKey _getProposalContestPhaseAttributeMethodKey18;
    private MethodKey _getProposalContestPhaseAttributeMethodKey19;
    private MethodKey _setProposalContestPhaseAttributeMethodKey20;
    private MethodKey _setProposalContestPhaseAttributeMethodKey21;
    private MethodKey _setProposalContestPhaseAttributeMethodKey22;
    private MethodKey _setProposalContestPhaseAttributeMethodKey23;
    private MethodKey _deleteProposalContestPhaseAttributeMethodKey24;

    public ProposalContestPhaseAttributeLocalServiceClp(
        ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addProposalContestPhaseAttributeMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addProposalContestPhaseAttribute",
                com.ext.portlet.model.ProposalContestPhaseAttribute.class);

        _createProposalContestPhaseAttributeMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createProposalContestPhaseAttribute", long.class);

        _deleteProposalContestPhaseAttributeMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteProposalContestPhaseAttribute", long.class);

        _deleteProposalContestPhaseAttributeMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteProposalContestPhaseAttribute",
                com.ext.portlet.model.ProposalContestPhaseAttribute.class);

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

        _fetchProposalContestPhaseAttributeMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchProposalContestPhaseAttribute", long.class);

        _getProposalContestPhaseAttributeMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getProposalContestPhaseAttribute", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getProposalContestPhaseAttributesMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getProposalContestPhaseAttributes", int.class, int.class);

        _getProposalContestPhaseAttributesCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getProposalContestPhaseAttributesCount");

        _updateProposalContestPhaseAttributeMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateProposalContestPhaseAttribute",
                com.ext.portlet.model.ProposalContestPhaseAttribute.class);

        _updateProposalContestPhaseAttributeMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateProposalContestPhaseAttribute",
                com.ext.portlet.model.ProposalContestPhaseAttribute.class,
                boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _getProposalContestPhaseAttributesMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "getProposalContestPhaseAttributes", long.class, long.class);

        _getProposalContestPhaseAttributeMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "getProposalContestPhaseAttribute", long.class, long.class,
                java.lang.String.class, long.class);

        _getProposalContestPhaseAttributeMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "getProposalContestPhaseAttribute", long.class, long.class,
                java.lang.String.class);

        _setProposalContestPhaseAttributeMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "setProposalContestPhaseAttribute", long.class, long.class,
                java.lang.String.class, long.class);

        _setProposalContestPhaseAttributeMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "setProposalContestPhaseAttribute", long.class, long.class,
                java.lang.String.class, java.lang.String.class);

        _setProposalContestPhaseAttributeMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "setProposalContestPhaseAttribute", long.class, long.class,
                java.lang.String.class, double.class);

        _setProposalContestPhaseAttributeMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
                "setProposalContestPhaseAttribute", long.class, long.class,
                java.lang.String.class, long.class, java.lang.String.class,
                double.class);

        _deleteProposalContestPhaseAttributeMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteProposalContestPhaseAttribute", long.class, long.class,
                java.lang.String.class);
    }

    public com.ext.portlet.model.ProposalContestPhaseAttribute addProposalContestPhaseAttribute(
        com.ext.portlet.model.ProposalContestPhaseAttribute proposalContestPhaseAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addProposalContestPhaseAttributeMethodKey0,
                ClpSerializer.translateInput(proposalContestPhaseAttribute));

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

        return (com.ext.portlet.model.ProposalContestPhaseAttribute) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ProposalContestPhaseAttribute createProposalContestPhaseAttribute(
        long id) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createProposalContestPhaseAttributeMethodKey1,
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

        return (com.ext.portlet.model.ProposalContestPhaseAttribute) ClpSerializer.translateOutput(returnObj);
    }

    public void deleteProposalContestPhaseAttribute(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteProposalContestPhaseAttributeMethodKey2,
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

    public void deleteProposalContestPhaseAttribute(
        com.ext.portlet.model.ProposalContestPhaseAttribute proposalContestPhaseAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteProposalContestPhaseAttributeMethodKey3,
                ClpSerializer.translateInput(proposalContestPhaseAttribute));

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

    public com.ext.portlet.model.ProposalContestPhaseAttribute fetchProposalContestPhaseAttribute(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchProposalContestPhaseAttributeMethodKey8,
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

        return (com.ext.portlet.model.ProposalContestPhaseAttribute) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ProposalContestPhaseAttribute getProposalContestPhaseAttribute(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getProposalContestPhaseAttributeMethodKey9,
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

        return (com.ext.portlet.model.ProposalContestPhaseAttribute) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> getProposalContestPhaseAttributes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getProposalContestPhaseAttributesMethodKey11,
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

        return (java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute>) ClpSerializer.translateOutput(returnObj);
    }

    public int getProposalContestPhaseAttributesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getProposalContestPhaseAttributesCountMethodKey12);

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

    public com.ext.portlet.model.ProposalContestPhaseAttribute updateProposalContestPhaseAttribute(
        com.ext.portlet.model.ProposalContestPhaseAttribute proposalContestPhaseAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateProposalContestPhaseAttributeMethodKey13,
                ClpSerializer.translateInput(proposalContestPhaseAttribute));

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

        return (com.ext.portlet.model.ProposalContestPhaseAttribute) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ProposalContestPhaseAttribute updateProposalContestPhaseAttribute(
        com.ext.portlet.model.ProposalContestPhaseAttribute proposalContestPhaseAttribute,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updateProposalContestPhaseAttributeMethodKey14,
                ClpSerializer.translateInput(proposalContestPhaseAttribute),
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

        return (com.ext.portlet.model.ProposalContestPhaseAttribute) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> getProposalContestPhaseAttributes(
        long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getProposalContestPhaseAttributesMethodKey17,
                proposalId, contestPhaseId);

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

        return (java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute>) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ProposalContestPhaseAttribute getProposalContestPhaseAttribute(
        long proposalId, long contestPhaseId, java.lang.String attributeName,
        long additionalId)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getProposalContestPhaseAttributeMethodKey18,
                proposalId, contestPhaseId,
                ClpSerializer.translateInput(attributeName), additionalId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.NoSuchProposalContestPhaseAttributeException) {
                throw (com.ext.portlet.NoSuchProposalContestPhaseAttributeException) t;
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

        return (com.ext.portlet.model.ProposalContestPhaseAttribute) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ProposalContestPhaseAttribute getProposalContestPhaseAttribute(
        long proposalId, long contestPhaseId, java.lang.String attributeName)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getProposalContestPhaseAttributeMethodKey19,
                proposalId, contestPhaseId,
                ClpSerializer.translateInput(attributeName));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.NoSuchProposalContestPhaseAttributeException) {
                throw (com.ext.portlet.NoSuchProposalContestPhaseAttributeException) t;
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

        return (com.ext.portlet.model.ProposalContestPhaseAttribute) ClpSerializer.translateOutput(returnObj);
    }

    public void setProposalContestPhaseAttribute(long proposalId,
        long contestPhaseId, java.lang.String attributeName, long value)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setProposalContestPhaseAttributeMethodKey20,
                proposalId, contestPhaseId,
                ClpSerializer.translateInput(attributeName), value);

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

    public void setProposalContestPhaseAttribute(long proposalId,
        long contestPhaseId, java.lang.String attributeName,
        java.lang.String value)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setProposalContestPhaseAttributeMethodKey21,
                proposalId, contestPhaseId,
                ClpSerializer.translateInput(attributeName),
                ClpSerializer.translateInput(value));

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

    public void setProposalContestPhaseAttribute(long proposalId,
        long contestPhaseId, java.lang.String attributeName, double value)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setProposalContestPhaseAttributeMethodKey22,
                proposalId, contestPhaseId,
                ClpSerializer.translateInput(attributeName), value);

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

    public void setProposalContestPhaseAttribute(long proposalId,
        long contestPhaseId, java.lang.String attributeName, long longValue,
        java.lang.String stringValue, double realValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setProposalContestPhaseAttributeMethodKey23,
                proposalId, contestPhaseId,
                ClpSerializer.translateInput(attributeName), longValue,
                ClpSerializer.translateInput(stringValue), realValue);

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

    public void deleteProposalContestPhaseAttribute(long proposalId,
        long contestPhaseId, java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteProposalContestPhaseAttributeMethodKey24,
                proposalId, contestPhaseId,
                ClpSerializer.translateInput(attributeName));

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
