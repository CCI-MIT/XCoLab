package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class PlanItemLocalServiceClp implements PlanItemLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addPlanItemMethodKey0;
    private MethodKey _createPlanItemMethodKey1;
    private MethodKey _deletePlanItemMethodKey2;
    private MethodKey _deletePlanItemMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchPlanItemMethodKey8;
    private MethodKey _getPlanItemMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getPlanItemsMethodKey11;
    private MethodKey _getPlanItemsCountMethodKey12;
    private MethodKey _updatePlanItemMethodKey13;
    private MethodKey _updatePlanItemMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _createPlanMethodKey17;
    private MethodKey _createPlanMethodKey18;
    private MethodKey _createPlanMethodKey19;
    private MethodKey _getPlansMethodKey20;
    private MethodKey _getPlansInContestPhaseMethodKey21;
    private MethodKey _getPlanMethodKey22;
    private MethodKey _getPlansMethodKey23;
    private MethodKey _getPlansMethodKey24;
    private MethodKey _getPlansMethodKey25;
    private MethodKey _getPlansMethodKey26;
    private MethodKey _isNameAvailableMethodKey27;
    private MethodKey _applyFiltersMethodKey28;
    private MethodKey _removePlanWithEntireHistoryMethodKey29;
    private MethodKey _getAllVersionsMethodKey30;
    private MethodKey _getPlanAttributesMethodKey31;
    private MethodKey _getPlanAttributeMethodKey32;
    private MethodKey _reIndexMethodKey33;
    private MethodKey _reIndexMethodKey34;
    private MethodKey _findPlansForFocusAreaMethodKey35;
    private MethodKey _findPlansForOntologyTermsMethodKey36;
    private MethodKey _findPlansForOntologyTermsMethodKey37;
    private MethodKey _countPlansByContestMethodKey38;
    private MethodKey _getPlansByContestMethodKey39;
    private MethodKey _planDeletedMethodKey40;

    public PlanItemLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addPlanItemMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addPlanItem", com.ext.portlet.plans.model.PlanItem.class);

        _createPlanItemMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createPlanItem", java.lang.Long.class);

        _deletePlanItemMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deletePlanItem", java.lang.Long.class);

        _deletePlanItemMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deletePlanItem", com.ext.portlet.plans.model.PlanItem.class);

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

        _fetchPlanItemMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchPlanItem", java.lang.Long.class);

        _getPlanItemMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanItem", java.lang.Long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getPlanItemsMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanItems", int.class, int.class);

        _getPlanItemsCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanItemsCount");

        _updatePlanItemMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updatePlanItem", com.ext.portlet.plans.model.PlanItem.class);

        _updatePlanItemMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updatePlanItem", com.ext.portlet.plans.model.PlanItem.class,
                boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _createPlanMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "createPlan",
                com.ext.portlet.contests.model.ContestPhase.class,
                java.lang.Long.class);

        _createPlanMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "createPlan", com.ext.portlet.plans.model.PlanItem.class,
                java.lang.Long.class);

        _createPlanMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "createPlan", com.ext.portlet.plans.model.PlanItem.class,
                com.ext.portlet.contests.model.ContestPhase.class,
                java.lang.Long.class);

        _getPlansMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlans");

        _getPlansInContestPhaseMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlansInContestPhase",
                com.ext.portlet.contests.model.ContestPhase.class);

        _getPlanMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlan", java.lang.Long.class);

        _getPlansMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlans", java.util.Map.class, java.util.Map.class,
                com.ext.portlet.plans.model.PlanType.class,
                com.ext.portlet.contests.model.ContestPhase.class, int.class,
                int.class, java.lang.String.class, java.lang.String.class);

        _getPlansMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlans", java.util.Map.class, java.util.Map.class,
                com.ext.portlet.plans.model.PlanType.class,
                com.ext.portlet.contests.model.ContestPhase.class, int.class,
                int.class, java.lang.String.class, java.lang.String.class,
                boolean.class);

        _getPlansMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlans", java.util.Map.class, java.util.Map.class,
                com.ext.portlet.plans.model.PlanType.class,
                java.util.List.class, int.class, int.class,
                java.lang.String.class, java.lang.String.class);

        _getPlansMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlans", java.util.Map.class, java.util.Map.class,
                com.ext.portlet.plans.model.PlanType.class,
                java.util.List.class, int.class, int.class,
                java.lang.String.class, java.lang.String.class, boolean.class);

        _isNameAvailableMethodKey27 = new MethodKey(_classLoaderProxy.getClassName(),
                "isNameAvailable", java.lang.String.class,
                com.ext.portlet.contests.model.Contest.class);

        _applyFiltersMethodKey28 = new MethodKey(_classLoaderProxy.getClassName(),
                "applyFilters", java.util.Map.class, java.util.Map.class,
                com.ext.portlet.plans.model.PlanType.class, java.util.List.class);

        _removePlanWithEntireHistoryMethodKey29 = new MethodKey(_classLoaderProxy.getClassName(),
                "removePlanWithEntireHistory", java.lang.Long.class);

        _getAllVersionsMethodKey30 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAllVersions", com.ext.portlet.plans.model.PlanItem.class);

        _getPlanAttributesMethodKey31 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanAttributes", com.ext.portlet.plans.model.PlanItem.class);

        _getPlanAttributeMethodKey32 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanAttribute", com.ext.portlet.plans.model.PlanItem.class,
                java.lang.String.class);

        _reIndexMethodKey33 = new MethodKey(_classLoaderProxy.getClassName(),
                "reIndex");

        _reIndexMethodKey34 = new MethodKey(_classLoaderProxy.getClassName(),
                "reIndex", long.class);

        _findPlansForFocusAreaMethodKey35 = new MethodKey(_classLoaderProxy.getClassName(),
                "findPlansForFocusArea",
                com.ext.portlet.ontology.model.FocusArea.class);

        _findPlansForOntologyTermsMethodKey36 = new MethodKey(_classLoaderProxy.getClassName(),
                "findPlansForOntologyTerms",
                com.ext.portlet.ontology.model.OntologyTerm.class);

        _findPlansForOntologyTermsMethodKey37 = new MethodKey(_classLoaderProxy.getClassName(),
                "findPlansForOntologyTerms", java.util.List.class);

        _countPlansByContestMethodKey38 = new MethodKey(_classLoaderProxy.getClassName(),
                "countPlansByContest", java.lang.Long.class);

        _getPlansByContestMethodKey39 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlansByContest", java.lang.Long.class);

        _planDeletedMethodKey40 = new MethodKey(_classLoaderProxy.getClassName(),
                "planDeleted", com.ext.portlet.plans.model.PlanItem.class);
    }

    public com.ext.portlet.plans.model.PlanItem addPlanItem(
        com.ext.portlet.plans.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addPlanItemMethodKey0,
                ClpSerializer.translateInput(planItem));

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

        return (com.ext.portlet.plans.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.plans.model.PlanItem createPlanItem(
        java.lang.Long id) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createPlanItemMethodKey1,
                ClpSerializer.translateInput(id));

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

        return (com.ext.portlet.plans.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public void deletePlanItem(java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deletePlanItemMethodKey2,
                ClpSerializer.translateInput(id));

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

    public void deletePlanItem(com.ext.portlet.plans.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deletePlanItemMethodKey3,
                ClpSerializer.translateInput(planItem));

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

    public com.ext.portlet.plans.model.PlanItem fetchPlanItem(java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchPlanItemMethodKey8,
                ClpSerializer.translateInput(id));

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

        return (com.ext.portlet.plans.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.plans.model.PlanItem getPlanItem(java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanItemMethodKey9,
                ClpSerializer.translateInput(id));

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

        return (com.ext.portlet.plans.model.PlanItem) ClpSerializer.translateOutput(returnObj);
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

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlanItems(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanItemsMethodKey11,
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

        return (java.util.List<com.ext.portlet.plans.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public int getPlanItemsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanItemsCountMethodKey12);

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

    public com.ext.portlet.plans.model.PlanItem updatePlanItem(
        com.ext.portlet.plans.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updatePlanItemMethodKey13,
                ClpSerializer.translateInput(planItem));

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

        return (com.ext.portlet.plans.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.plans.model.PlanItem updatePlanItem(
        com.ext.portlet.plans.model.PlanItem planItem, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updatePlanItemMethodKey14,
                ClpSerializer.translateInput(planItem), merge);

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

        return (com.ext.portlet.plans.model.PlanItem) ClpSerializer.translateOutput(returnObj);
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

    public com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.contests.model.ContestPhase phase,
        java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createPlanMethodKey17,
                ClpSerializer.translateInput(phase),
                ClpSerializer.translateInput(authorId));

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

        return (com.ext.portlet.plans.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.plans.model.PlanItem basePlan, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createPlanMethodKey18,
                ClpSerializer.translateInput(basePlan),
                ClpSerializer.translateInput(authorId));

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

        return (com.ext.portlet.plans.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.plans.model.PlanItem basePlan,
        com.ext.portlet.contests.model.ContestPhase contestPhase,
        java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createPlanMethodKey19,
                ClpSerializer.translateInput(basePlan),
                ClpSerializer.translateInput(contestPhase),
                ClpSerializer.translateInput(authorId));

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

        return (com.ext.portlet.plans.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansMethodKey20);

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

        return (java.util.List<com.ext.portlet.plans.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlansInContestPhase(
        com.ext.portlet.contests.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansInContestPhaseMethodKey21,
                ClpSerializer.translateInput(contestPhase));

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

        return (java.util.List<com.ext.portlet.plans.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.plans.model.PlanItem getPlan(java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanMethodKey22,
                ClpSerializer.translateInput(planId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.plans.NoSuchPlanItemException) {
                throw (com.ext.portlet.plans.NoSuchPlanItemException) t;
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

        return (com.ext.portlet.plans.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        com.ext.portlet.contests.model.ContestPhase phase, int start, int end,
        java.lang.String sortColumn, java.lang.String sortDirection)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansMethodKey23,
                ClpSerializer.translateInput(sessionMap),
                ClpSerializer.translateInput(requestMap),
                ClpSerializer.translateInput(planType),
                ClpSerializer.translateInput(phase), start, end,
                ClpSerializer.translateInput(sortColumn),
                ClpSerializer.translateInput(sortDirection));

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

        return (java.util.List<com.ext.portlet.plans.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        com.ext.portlet.contests.model.ContestPhase phase, int start, int end,
        java.lang.String sortColumn, java.lang.String sortDirection,
        boolean applyFilters)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansMethodKey24,
                ClpSerializer.translateInput(sessionMap),
                ClpSerializer.translateInput(requestMap),
                ClpSerializer.translateInput(planType),
                ClpSerializer.translateInput(phase), start, end,
                ClpSerializer.translateInput(sortColumn),
                ClpSerializer.translateInput(sortDirection), applyFilters);

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

        return (java.util.List<com.ext.portlet.plans.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        java.util.List<com.ext.portlet.contests.model.ContestPhase> phases,
        int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansMethodKey25,
                ClpSerializer.translateInput(sessionMap),
                ClpSerializer.translateInput(requestMap),
                ClpSerializer.translateInput(planType),
                ClpSerializer.translateInput(phases), start, end,
                ClpSerializer.translateInput(sortColumn),
                ClpSerializer.translateInput(sortDirection));

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

        return (java.util.List<com.ext.portlet.plans.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        java.util.List<com.ext.portlet.contests.model.ContestPhase> phases,
        int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection, boolean applyFilters)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansMethodKey26,
                ClpSerializer.translateInput(sessionMap),
                ClpSerializer.translateInput(requestMap),
                ClpSerializer.translateInput(planType),
                ClpSerializer.translateInput(phases), start, end,
                ClpSerializer.translateInput(sortColumn),
                ClpSerializer.translateInput(sortDirection), applyFilters);

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

        return (java.util.List<com.ext.portlet.plans.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public boolean isNameAvailable(java.lang.String planName,
        com.ext.portlet.contests.model.Contest c)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_isNameAvailableMethodKey27,
                ClpSerializer.translateInput(planName),
                ClpSerializer.translateInput(c));

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

        return ((Boolean) returnObj).booleanValue();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> applyFilters(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        java.util.List<com.ext.portlet.plans.model.PlanItem> plans)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_applyFiltersMethodKey28,
                ClpSerializer.translateInput(sessionMap),
                ClpSerializer.translateInput(requestMap),
                ClpSerializer.translateInput(planType),
                ClpSerializer.translateInput(plans));

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

        return (java.util.List<com.ext.portlet.plans.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public void removePlanWithEntireHistory(java.lang.Long planId) {
        MethodHandler methodHandler = new MethodHandler(_removePlanWithEntireHistoryMethodKey29,
                ClpSerializer.translateInput(planId));

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

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getAllVersions(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getAllVersionsMethodKey30,
                ClpSerializer.translateInput(plan));

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

        return (java.util.List<com.ext.portlet.plans.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanAttributesMethodKey31,
                ClpSerializer.translateInput(plan));

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

        return (java.util.List<com.ext.portlet.plans.model.PlanAttribute>) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.plans.model.PlanAttribute getPlanAttribute(
        com.ext.portlet.plans.model.PlanItem plan, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanAttributeMethodKey32,
                ClpSerializer.translateInput(plan),
                ClpSerializer.translateInput(name));

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

        return (com.ext.portlet.plans.model.PlanAttribute) ClpSerializer.translateOutput(returnObj);
    }

    public void reIndex()
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_reIndexMethodKey33);

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

    public void reIndex(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_reIndexMethodKey34,
                planId);

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

    public java.util.List<com.ext.portlet.plans.model.PlanItem> findPlansForFocusArea(
        com.ext.portlet.ontology.model.FocusArea fa)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findPlansForFocusAreaMethodKey35,
                ClpSerializer.translateInput(fa));

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

        return (java.util.List<com.ext.portlet.plans.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> findPlansForOntologyTerms(
        com.ext.portlet.ontology.model.OntologyTerm terms)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findPlansForOntologyTermsMethodKey36,
                ClpSerializer.translateInput(terms));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.plans.NoSuchPlanItemException) {
                throw (com.ext.portlet.plans.NoSuchPlanItemException) t;
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

        return (java.util.List<com.ext.portlet.plans.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> findPlansForOntologyTerms(
        java.util.List<com.ext.portlet.ontology.model.OntologyTerm> terms)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findPlansForOntologyTermsMethodKey37,
                ClpSerializer.translateInput(terms));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.plans.NoSuchPlanItemException) {
                throw (com.ext.portlet.plans.NoSuchPlanItemException) t;
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

        return (java.util.List<com.ext.portlet.plans.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public long countPlansByContest(java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_countPlansByContestMethodKey38,
                ClpSerializer.translateInput(contestId));

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

        return ((Long) returnObj).longValue();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlansByContest(
        java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansByContestMethodKey39,
                ClpSerializer.translateInput(contestId));

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

        return (java.util.List<com.ext.portlet.plans.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public void planDeleted(com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_planDeletedMethodKey40,
                ClpSerializer.translateInput(plan));

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
