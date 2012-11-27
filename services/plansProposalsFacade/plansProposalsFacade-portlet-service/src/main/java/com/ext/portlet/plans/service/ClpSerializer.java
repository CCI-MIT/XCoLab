package com.ext.portlet.plans.service;

import com.ext.portlet.plans.model.PlanAttributeClp;
import com.ext.portlet.plans.model.PlanAttributeFilterClp;
import com.ext.portlet.plans.model.PlanColumnSettingsClp;
import com.ext.portlet.plans.model.PlanDescriptionClp;
import com.ext.portlet.plans.model.PlanFanClp;
import com.ext.portlet.plans.model.PlanItemClp;
import com.ext.portlet.plans.model.PlanMetaClp;
import com.ext.portlet.plans.model.PlanModelRunClp;
import com.ext.portlet.plans.model.PlanPositionClp;
import com.ext.portlet.plans.model.PlanPositionItemClp;
import com.ext.portlet.plans.model.PlanPositionsClp;
import com.ext.portlet.plans.model.PlanPropertyFilterClp;
import com.ext.portlet.plans.model.PlanRelatedClp;
import com.ext.portlet.plans.model.PlanSectionClp;
import com.ext.portlet.plans.model.PlanSectionDefinitionClp;
import com.ext.portlet.plans.model.PlanSectionPlanMapClp;
import com.ext.portlet.plans.model.PlanTeamHistoryClp;
import com.ext.portlet.plans.model.PlanTemplateClp;
import com.ext.portlet.plans.model.PlanTemplateSectionClp;
import com.ext.portlet.plans.model.PlanTypeAttributeClp;
import com.ext.portlet.plans.model.PlanTypeClp;
import com.ext.portlet.plans.model.PlanTypeColumnClp;
import com.ext.portlet.plans.model.PlanVoteClp;
import com.ext.portlet.plans.model.PlansFilterClp;
import com.ext.portlet.plans.model.PlansFilterPositionClp;
import com.ext.portlet.plans.model.PlansUserSettingsClp;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ClpSerializer {
    private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
    private static ClassLoader _classLoader;
    private static String _servletContextName;

    public static String getServletContextName() {
        if (Validator.isNotNull(_servletContextName)) {
            return _servletContextName;
        }

        synchronized (ClpSerializer.class) {
            if (Validator.isNotNull(_servletContextName)) {
                return _servletContextName;
            }

            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Class<?> portletPropsClass = classLoader.loadClass(
                        "com.liferay.util.portlet.PortletProps");

                Method getMethod = portletPropsClass.getMethod("get",
                        new Class<?>[] { String.class });

                String portletPropsServletContextName = (String) getMethod.invoke(null,
                        "plansProposalsFacade-portlet-deployment-context");

                if (Validator.isNotNull(portletPropsServletContextName)) {
                    _servletContextName = portletPropsServletContextName;
                }
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info(
                        "Unable to locate deployment context from portlet properties");
                }
            }

            if (Validator.isNull(_servletContextName)) {
                try {
                    String propsUtilServletContextName = PropsUtil.get(
                            "plansProposalsFacade-portlet-deployment-context");

                    if (Validator.isNotNull(propsUtilServletContextName)) {
                        _servletContextName = propsUtilServletContextName;
                    }
                } catch (Throwable t) {
                    if (_log.isInfoEnabled()) {
                        _log.info(
                            "Unable to locate deployment context from portal properties");
                    }
                }
            }

            if (Validator.isNull(_servletContextName)) {
                _servletContextName = "plansProposalsFacade-portlet";
            }

            return _servletContextName;
        }
    }

    public static void setClassLoader(ClassLoader classLoader) {
        _classLoader = classLoader;
    }

    public static Object translateInput(BaseModel<?> oldModel) {
        Class<?> oldModelClass = oldModel.getClass();

        String oldModelClassName = oldModelClass.getName();

        if (oldModelClassName.equals(PlanAttributeClp.class.getName())) {
            return translateInputPlanAttribute(oldModel);
        }

        if (oldModelClassName.equals(PlanAttributeFilterClp.class.getName())) {
            return translateInputPlanAttributeFilter(oldModel);
        }

        if (oldModelClassName.equals(PlanColumnSettingsClp.class.getName())) {
            return translateInputPlanColumnSettings(oldModel);
        }

        if (oldModelClassName.equals(PlanDescriptionClp.class.getName())) {
            return translateInputPlanDescription(oldModel);
        }

        if (oldModelClassName.equals(PlanFanClp.class.getName())) {
            return translateInputPlanFan(oldModel);
        }

        if (oldModelClassName.equals(PlanItemClp.class.getName())) {
            return translateInputPlanItem(oldModel);
        }

        if (oldModelClassName.equals(PlanMetaClp.class.getName())) {
            return translateInputPlanMeta(oldModel);
        }

        if (oldModelClassName.equals(PlanModelRunClp.class.getName())) {
            return translateInputPlanModelRun(oldModel);
        }

        if (oldModelClassName.equals(PlanPositionClp.class.getName())) {
            return translateInputPlanPosition(oldModel);
        }

        if (oldModelClassName.equals(PlanPositionItemClp.class.getName())) {
            return translateInputPlanPositionItem(oldModel);
        }

        if (oldModelClassName.equals(PlanPositionsClp.class.getName())) {
            return translateInputPlanPositions(oldModel);
        }

        if (oldModelClassName.equals(PlanPropertyFilterClp.class.getName())) {
            return translateInputPlanPropertyFilter(oldModel);
        }

        if (oldModelClassName.equals(PlanRelatedClp.class.getName())) {
            return translateInputPlanRelated(oldModel);
        }

        if (oldModelClassName.equals(PlanSectionClp.class.getName())) {
            return translateInputPlanSection(oldModel);
        }

        if (oldModelClassName.equals(PlanSectionDefinitionClp.class.getName())) {
            return translateInputPlanSectionDefinition(oldModel);
        }

        if (oldModelClassName.equals(PlanSectionPlanMapClp.class.getName())) {
            return translateInputPlanSectionPlanMap(oldModel);
        }

        if (oldModelClassName.equals(PlansFilterClp.class.getName())) {
            return translateInputPlansFilter(oldModel);
        }

        if (oldModelClassName.equals(PlansFilterPositionClp.class.getName())) {
            return translateInputPlansFilterPosition(oldModel);
        }

        if (oldModelClassName.equals(PlansUserSettingsClp.class.getName())) {
            return translateInputPlansUserSettings(oldModel);
        }

        if (oldModelClassName.equals(PlanTeamHistoryClp.class.getName())) {
            return translateInputPlanTeamHistory(oldModel);
        }

        if (oldModelClassName.equals(PlanTemplateClp.class.getName())) {
            return translateInputPlanTemplate(oldModel);
        }

        if (oldModelClassName.equals(PlanTemplateSectionClp.class.getName())) {
            return translateInputPlanTemplateSection(oldModel);
        }

        if (oldModelClassName.equals(PlanTypeClp.class.getName())) {
            return translateInputPlanType(oldModel);
        }

        if (oldModelClassName.equals(PlanTypeAttributeClp.class.getName())) {
            return translateInputPlanTypeAttribute(oldModel);
        }

        if (oldModelClassName.equals(PlanTypeColumnClp.class.getName())) {
            return translateInputPlanTypeColumn(oldModel);
        }

        if (oldModelClassName.equals(PlanVoteClp.class.getName())) {
            return translateInputPlanVote(oldModel);
        }

        return oldModel;
    }

    public static Object translateInput(List<Object> oldList) {
        List<Object> newList = new ArrayList<Object>(oldList.size());

        for (int i = 0; i < oldList.size(); i++) {
            Object curObj = oldList.get(i);

            newList.add(translateInput(curObj));
        }

        return newList;
    }

    public static Object translateInputPlanAttribute(BaseModel<?> oldModel) {
        PlanAttributeClp oldCplModel = (PlanAttributeClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanAttributeImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setAttributeId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getAttributeId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setAttributeName",
                        new Class[] { String.class });

                String value2 = oldCplModel.getAttributeName();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setAttributeValue",
                        new Class[] { String.class });

                String value3 = oldCplModel.getAttributeValue();

                method3.invoke(newModel, value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanAttributeFilter(
        BaseModel<?> oldModel) {
        PlanAttributeFilterClp oldCplModel = (PlanAttributeFilterClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanAttributeFilterImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanAttributeFilterId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanAttributeFilterId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setAttributeName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getAttributeName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanUserSettingsId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanUserSettingsId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setMax",
                        new Class[] { Double.TYPE });

                Double value3 = new Double(oldCplModel.getMax());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setMin",
                        new Class[] { Double.TYPE });

                Double value4 = new Double(oldCplModel.getMin());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setStringVal",
                        new Class[] { String.class });

                String value5 = oldCplModel.getStringVal();

                method5.invoke(newModel, value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanColumnSettings(BaseModel<?> oldModel) {
        PlanColumnSettingsClp oldCplModel = (PlanColumnSettingsClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanColumnSettingsImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanColumnSettingsId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanColumnSettingsId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setColumnName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getColumnName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanUserSettingsId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanUserSettingsId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setVisible",
                        new Class[] { Boolean.TYPE });

                Boolean value3 = new Boolean(oldCplModel.getVisible());

                method3.invoke(newModel, value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanDescription(BaseModel<?> oldModel) {
        PlanDescriptionClp oldCplModel = (PlanDescriptionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanDescriptionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value2 = oldCplModel.getName();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value3 = oldCplModel.getDescription();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setVersion",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getVersion());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setPlanVersion",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getPlanVersion());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value6 = oldCplModel.getCreated();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setUpdateAuthorId",
                        new Class[] { Long.TYPE });

                Long value7 = new Long(oldCplModel.getUpdateAuthorId());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setImage",
                        new Class[] { Long.TYPE });

                Long value8 = new Long(oldCplModel.getImage());

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setPitch",
                        new Class[] { String.class });

                String value9 = oldCplModel.getPitch();

                method9.invoke(newModel, value9);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanFan(BaseModel<?> oldModel) {
        PlanFanClp oldCplModel = (PlanFanClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanFanImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getUserId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value3 = oldCplModel.getCreated();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setDeleted",
                        new Class[] { Date.class });

                Date value4 = oldCplModel.getDeleted();

                method4.invoke(newModel, value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanItem(BaseModel<?> oldModel) {
        PlanItemClp oldCplModel = (PlanItemClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanItemImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setState",
                        new Class[] { String.class });

                String value2 = oldCplModel.getState();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setUpdated",
                        new Class[] { Date.class });

                Date value3 = oldCplModel.getUpdated();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setUpdateAuthorId",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getUpdateAuthorId());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setUpdateType",
                        new Class[] { String.class });

                String value5 = oldCplModel.getUpdateType();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setVersion",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getVersion());

                method6.invoke(newModel, value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanMeta(BaseModel<?> oldModel) {
        PlanMetaClp oldCplModel = (PlanMetaClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanMetaImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanTypeId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanTypeId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setPlanCreated",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getPlanCreated());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setAuthorId",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getAuthorId());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setVotes",
                        new Class[] { Integer.TYPE });

                Integer value5 = new Integer(oldCplModel.getVotes());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setPlanGroupId",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getPlanGroupId());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setOpen",
                        new Class[] { Boolean.TYPE });

                Boolean value7 = new Boolean(oldCplModel.getOpen());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setStatus",
                        new Class[] { String.class });

                String value8 = oldCplModel.getStatus();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setMbCategoryId",
                        new Class[] { Long.TYPE });

                Long value9 = new Long(oldCplModel.getMbCategoryId());

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setCategoryGroupId",
                        new Class[] { Long.TYPE });

                Long value10 = new Long(oldCplModel.getCategoryGroupId());

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setVersion",
                        new Class[] { Long.TYPE });

                Long value11 = new Long(oldCplModel.getVersion());

                method11.invoke(newModel, value11);

                Method method12 = newModelClass.getMethod("setPlanVersion",
                        new Class[] { Long.TYPE });

                Long value12 = new Long(oldCplModel.getPlanVersion());

                method12.invoke(newModel, value12);

                Method method13 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value13 = oldCplModel.getCreated();

                method13.invoke(newModel, value13);

                Method method14 = newModelClass.getMethod("setUpdateAuthorId",
                        new Class[] { Long.TYPE });

                Long value14 = new Long(oldCplModel.getUpdateAuthorId());

                method14.invoke(newModel, value14);

                Method method15 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value15 = new Long(oldCplModel.getModelId());

                method15.invoke(newModel, value15);

                Method method16 = newModelClass.getMethod("setPromoted",
                        new Class[] { Boolean.TYPE });

                Boolean value16 = new Boolean(oldCplModel.getPromoted());

                method16.invoke(newModel, value16);

                Method method17 = newModelClass.getMethod("setPreviousContestPhase",
                        new Class[] { Long.TYPE });

                Long value17 = new Long(oldCplModel.getPreviousContestPhase());

                method17.invoke(newModel, value17);

                Method method18 = newModelClass.getMethod("setContestPhase",
                        new Class[] { Long.TYPE });

                Long value18 = new Long(oldCplModel.getContestPhase());

                method18.invoke(newModel, value18);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanModelRun(BaseModel<?> oldModel) {
        PlanModelRunClp oldCplModel = (PlanModelRunClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanModelRunImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setScenarioId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getScenarioId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setPlanVersion",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getPlanVersion());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setVersion",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getVersion());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value5 = oldCplModel.getCreated();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setUpdateAuthorId",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getUpdateAuthorId());

                method6.invoke(newModel, value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanPosition(BaseModel<?> oldModel) {
        PlanPositionClp oldCplModel = (PlanPositionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanPositionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPositionId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPositionId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getUserId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setUserName",
                        new Class[] { String.class });

                String value3 = oldCplModel.getUserName();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value4 = oldCplModel.getCreateDate();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setModifiedDate",
                        new Class[] { Date.class });

                Date value5 = oldCplModel.getModifiedDate();

                method5.invoke(newModel, value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanPositionItem(BaseModel<?> oldModel) {
        PlanPositionItemClp oldCplModel = (PlanPositionItemClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanPositionItemImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanPositionsId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanPositionsId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPositionId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPositionId());

                method1.invoke(newModel, value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanPositions(BaseModel<?> oldModel) {
        PlanPositionsClp oldCplModel = (PlanPositionsClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanPositionsImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanVersion",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanVersion());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setVersion",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getVersion());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value4 = oldCplModel.getCreated();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setUpdateAuthorId",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getUpdateAuthorId());

                method5.invoke(newModel, value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanPropertyFilter(BaseModel<?> oldModel) {
        PlanPropertyFilterClp oldCplModel = (PlanPropertyFilterClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanPropertyFilterImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanPropertyFilterId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanPropertyFilterId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPropertyName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getPropertyName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanUserSettingsId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanUserSettingsId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setValue",
                        new Class[] { String.class });

                String value3 = oldCplModel.getValue();

                method3.invoke(newModel, value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanRelated(BaseModel<?> oldModel) {
        PlanRelatedClp oldCplModel = (PlanRelatedClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanRelatedImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setSectionId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getSectionId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setRelatedPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getRelatedPlanId());

                method1.invoke(newModel, value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanSection(BaseModel<?> oldModel) {
        PlanSectionClp oldCplModel = (PlanSectionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanSectionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanSectionDefinitionId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanSectionDefinitionId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setContent",
                        new Class[] { String.class });

                String value3 = oldCplModel.getContent();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value4 = oldCplModel.getCreated();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setVersion",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getVersion());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setPlanVersion",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getPlanVersion());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setUpdateAuthorId",
                        new Class[] { Long.TYPE });

                Long value7 = new Long(oldCplModel.getUpdateAuthorId());

                method7.invoke(newModel, value7);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanSectionDefinition(
        BaseModel<?> oldModel) {
        PlanSectionDefinitionClp oldCplModel = (PlanSectionDefinitionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanSectionDefinitionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setAdminTitle",
                        new Class[] { String.class });

                String value1 = oldCplModel.getAdminTitle();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setTitle",
                        new Class[] { String.class });

                String value2 = oldCplModel.getTitle();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setDefaultText",
                        new Class[] { String.class });

                String value3 = oldCplModel.getDefaultText();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setHelpText",
                        new Class[] { String.class });

                String value4 = oldCplModel.getHelpText();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setCharacterLimit",
                        new Class[] { Integer.TYPE });

                Integer value5 = new Integer(oldCplModel.getCharacterLimit());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setFocusAreaId",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getFocusAreaId());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setLocked",
                        new Class[] { Boolean.TYPE });

                Boolean value7 = new Boolean(oldCplModel.getLocked());

                method7.invoke(newModel, value7);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanSectionPlanMap(BaseModel<?> oldModel) {
        PlanSectionPlanMapClp oldCplModel = (PlanSectionPlanMapClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanSectionPlanMapImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setSectionId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getSectionId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setRelatedPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getRelatedPlanId());

                method1.invoke(newModel, value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlansFilter(BaseModel<?> oldModel) {
        PlansFilterClp oldCplModel = (PlansFilterClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlansFilterImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getUserId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanTypeId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanTypeId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value2 = oldCplModel.getName();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCreator",
                        new Class[] { String.class });

                String value3 = oldCplModel.getCreator();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value4 = oldCplModel.getDescription();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setCO2From",
                        new Class[] { Double.TYPE });

                Double value5 = new Double(oldCplModel.getCO2From());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setCO2To",
                        new Class[] { Double.TYPE });

                Double value6 = new Double(oldCplModel.getCO2To());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setVotesFrom",
                        new Class[] { Double.TYPE });

                Double value7 = new Double(oldCplModel.getVotesFrom());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setVotesTo",
                        new Class[] { Double.TYPE });

                Double value8 = new Double(oldCplModel.getVotesTo());

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setDamageFrom",
                        new Class[] { Double.TYPE });

                Double value9 = new Double(oldCplModel.getDamageFrom());

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setDamageTo",
                        new Class[] { Double.TYPE });

                Double value10 = new Double(oldCplModel.getDamageTo());

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setMitigationFrom",
                        new Class[] { Double.TYPE });

                Double value11 = new Double(oldCplModel.getMitigationFrom());

                method11.invoke(newModel, value11);

                Method method12 = newModelClass.getMethod("setMitigationTo",
                        new Class[] { Double.TYPE });

                Double value12 = new Double(oldCplModel.getMitigationTo());

                method12.invoke(newModel, value12);

                Method method13 = newModelClass.getMethod("setDateFrom",
                        new Class[] { Date.class });

                Date value13 = oldCplModel.getDateFrom();

                method13.invoke(newModel, value13);

                Method method14 = newModelClass.getMethod("setDateTo",
                        new Class[] { Date.class });

                Date value14 = oldCplModel.getDateTo();

                method14.invoke(newModel, value14);

                Method method15 = newModelClass.getMethod("setFilterPositionsAll",
                        new Class[] { Boolean.TYPE });

                Boolean value15 = new Boolean(oldCplModel.getFilterPositionsAll());

                method15.invoke(newModel, value15);

                Method method16 = newModelClass.getMethod("setEnabled",
                        new Class[] { Boolean.TYPE });

                Boolean value16 = new Boolean(oldCplModel.getEnabled());

                method16.invoke(newModel, value16);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlansFilterPosition(
        BaseModel<?> oldModel) {
        PlansFilterPositionClp oldCplModel = (PlansFilterPositionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlansFilterPositionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getUserId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanTypeId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanTypeId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPositionId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPositionId());

                method2.invoke(newModel, value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlansUserSettings(BaseModel<?> oldModel) {
        PlansUserSettingsClp oldCplModel = (PlansUserSettingsClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlansUserSettingsImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanUserSettingsId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanUserSettingsId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getUserId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanTypeId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanTypeId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setSortColumn",
                        new Class[] { String.class });

                String value3 = oldCplModel.getSortColumn();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setSortDirection",
                        new Class[] { String.class });

                String value4 = oldCplModel.getSortDirection();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setFilterEnabled",
                        new Class[] { Boolean.TYPE });

                Boolean value5 = new Boolean(oldCplModel.getFilterEnabled());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setFilterPositionsAll",
                        new Class[] { Boolean.TYPE });

                Boolean value6 = new Boolean(oldCplModel.getFilterPositionsAll());

                method6.invoke(newModel, value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanTeamHistory(BaseModel<?> oldModel) {
        PlanTeamHistoryClp oldCplModel = (PlanTeamHistoryClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanTeamHistoryImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getUserId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setAction",
                        new Class[] { String.class });

                String value3 = oldCplModel.getAction();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setPayload",
                        new Class[] { String.class });

                String value4 = oldCplModel.getPayload();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value5 = oldCplModel.getCreated();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setUpdateAuthorId",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getUpdateAuthorId());

                method6.invoke(newModel, value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanTemplate(BaseModel<?> oldModel) {
        PlanTemplateClp oldCplModel = (PlanTemplateClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanTemplateImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getName();

                method1.invoke(newModel, value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanTemplateSection(
        BaseModel<?> oldModel) {
        PlanTemplateSectionClp oldCplModel = (PlanTemplateSectionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanTemplateSectionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanTemplateId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanTemplateId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanSectionId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanSectionId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setWeight",
                        new Class[] { Integer.TYPE });

                Integer value2 = new Integer(oldCplModel.getWeight());

                method2.invoke(newModel, value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanType(BaseModel<?> oldModel) {
        PlanTypeClp oldCplModel = (PlanTypeClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanTypeImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanTypeId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanTypeId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value2 = oldCplModel.getDescription();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getModelId());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setModelTypeName",
                        new Class[] { String.class });

                String value4 = oldCplModel.getModelTypeName();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setPublished",
                        new Class[] { Boolean.TYPE });

                Boolean value5 = new Boolean(oldCplModel.getPublished());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setPublishedCounterpartId",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getPublishedCounterpartId());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setIsDefault",
                        new Class[] { Boolean.TYPE });

                Boolean value7 = new Boolean(oldCplModel.getIsDefault());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setDefaultModelId",
                        new Class[] { Long.TYPE });

                Long value8 = new Long(oldCplModel.getDefaultModelId());

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setDefaultScenarioId",
                        new Class[] { Long.TYPE });

                Long value9 = new Long(oldCplModel.getDefaultScenarioId());

                method9.invoke(newModel, value9);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanTypeAttribute(BaseModel<?> oldModel) {
        PlanTypeAttributeClp oldCplModel = (PlanTypeAttributeClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanTypeAttributeImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanTypeAttributeId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanTypeAttributeId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanTypeId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanTypeId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setAttributeName",
                        new Class[] { String.class });

                String value2 = oldCplModel.getAttributeName();

                method2.invoke(newModel, value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanTypeColumn(BaseModel<?> oldModel) {
        PlanTypeColumnClp oldCplModel = (PlanTypeColumnClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanTypeColumnImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanTypeColumnId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanTypeColumnId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanTypeId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanTypeId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setWeight",
                        new Class[] { Integer.TYPE });

                Integer value2 = new Integer(oldCplModel.getWeight());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setColumnName",
                        new Class[] { String.class });

                String value3 = oldCplModel.getColumnName();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setVisibleByDefault",
                        new Class[] { Boolean.TYPE });

                Boolean value4 = new Boolean(oldCplModel.getVisibleByDefault());

                method4.invoke(newModel, value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanVote(BaseModel<?> oldModel) {
        PlanVoteClp oldCplModel = (PlanVoteClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.plans.model.impl.PlanVoteImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getUserId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setContestId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getContestId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value3 = oldCplModel.getCreateDate();

                method3.invoke(newModel, value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInput(Object obj) {
        if (obj instanceof BaseModel<?>) {
            return translateInput((BaseModel<?>) obj);
        } else if (obj instanceof List<?>) {
            return translateInput((List<Object>) obj);
        } else {
            return obj;
        }
    }

    public static Object translateOutput(BaseModel<?> oldModel) {
        Class<?> oldModelClass = oldModel.getClass();

        String oldModelClassName = oldModelClass.getName();

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanAttributeImpl")) {
            return translateOutputPlanAttribute(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanAttributeFilterImpl")) {
            return translateOutputPlanAttributeFilter(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanColumnSettingsImpl")) {
            return translateOutputPlanColumnSettings(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanDescriptionImpl")) {
            return translateOutputPlanDescription(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanFanImpl")) {
            return translateOutputPlanFan(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanItemImpl")) {
            return translateOutputPlanItem(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanMetaImpl")) {
            return translateOutputPlanMeta(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanModelRunImpl")) {
            return translateOutputPlanModelRun(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanPositionImpl")) {
            return translateOutputPlanPosition(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanPositionItemImpl")) {
            return translateOutputPlanPositionItem(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanPositionsImpl")) {
            return translateOutputPlanPositions(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanPropertyFilterImpl")) {
            return translateOutputPlanPropertyFilter(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanRelatedImpl")) {
            return translateOutputPlanRelated(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanSectionImpl")) {
            return translateOutputPlanSection(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanSectionDefinitionImpl")) {
            return translateOutputPlanSectionDefinition(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanSectionPlanMapImpl")) {
            return translateOutputPlanSectionPlanMap(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlansFilterImpl")) {
            return translateOutputPlansFilter(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlansFilterPositionImpl")) {
            return translateOutputPlansFilterPosition(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlansUserSettingsImpl")) {
            return translateOutputPlansUserSettings(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanTeamHistoryImpl")) {
            return translateOutputPlanTeamHistory(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanTemplateImpl")) {
            return translateOutputPlanTemplate(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanTemplateSectionImpl")) {
            return translateOutputPlanTemplateSection(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanTypeImpl")) {
            return translateOutputPlanType(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanTypeAttributeImpl")) {
            return translateOutputPlanTypeAttribute(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanTypeColumnImpl")) {
            return translateOutputPlanTypeColumn(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.plans.model.impl.PlanVoteImpl")) {
            return translateOutputPlanVote(oldModel);
        }

        return oldModel;
    }

    public static Object translateOutput(List<Object> oldList) {
        List<Object> newList = new ArrayList<Object>(oldList.size());

        for (int i = 0; i < oldList.size(); i++) {
            Object curObj = oldList.get(i);

            newList.add(translateOutput(curObj));
        }

        return newList;
    }

    public static Object translateOutput(Object obj) {
        if (obj instanceof BaseModel<?>) {
            return translateOutput((BaseModel<?>) obj);
        } else if (obj instanceof List<?>) {
            return translateOutput((List<Object>) obj);
        } else {
            return obj;
        }
    }

    public static Object translateOutputPlanAttribute(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanAttributeClp newModel = new PlanAttributeClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getAttributeId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setAttributeId(value0);

                Method method1 = oldModelClass.getMethod("getPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value1);

                Method method2 = oldModelClass.getMethod("getAttributeName");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setAttributeName(value2);

                Method method3 = oldModelClass.getMethod("getAttributeValue");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setAttributeValue(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanAttributeFilter(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanAttributeFilterClp newModel = new PlanAttributeFilterClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getPlanAttributeFilterId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanAttributeFilterId(value0);

                Method method1 = oldModelClass.getMethod("getAttributeName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setAttributeName(value1);

                Method method2 = oldModelClass.getMethod(
                        "getPlanUserSettingsId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanUserSettingsId(value2);

                Method method3 = oldModelClass.getMethod("getMax");

                Double value3 = (Double) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setMax(value3);

                Method method4 = oldModelClass.getMethod("getMin");

                Double value4 = (Double) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setMin(value4);

                Method method5 = oldModelClass.getMethod("getStringVal");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setStringVal(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanColumnSettings(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanColumnSettingsClp newModel = new PlanColumnSettingsClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getPlanColumnSettingsId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanColumnSettingsId(value0);

                Method method1 = oldModelClass.getMethod("getColumnName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setColumnName(value1);

                Method method2 = oldModelClass.getMethod(
                        "getPlanUserSettingsId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanUserSettingsId(value2);

                Method method3 = oldModelClass.getMethod("getVisible");

                Boolean value3 = (Boolean) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setVisible(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanDescription(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanDescriptionClp newModel = new PlanDescriptionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value1);

                Method method2 = oldModelClass.getMethod("getName");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value2);

                Method method3 = oldModelClass.getMethod("getDescription");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value3);

                Method method4 = oldModelClass.getMethod("getVersion");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setVersion(value4);

                Method method5 = oldModelClass.getMethod("getPlanVersion");

                Long value5 = (Long) method5.invoke(oldModel, (Object[]) null);

                newModel.setPlanVersion(value5);

                Method method6 = oldModelClass.getMethod("getCreated");

                Date value6 = (Date) method6.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value6);

                Method method7 = oldModelClass.getMethod("getUpdateAuthorId");

                Long value7 = (Long) method7.invoke(oldModel, (Object[]) null);

                newModel.setUpdateAuthorId(value7);

                Method method8 = oldModelClass.getMethod("getImage");

                Long value8 = (Long) method8.invoke(oldModel, (Object[]) null);

                newModel.setImage(value8);

                Method method9 = oldModelClass.getMethod("getPitch");

                String value9 = (String) method9.invoke(oldModel,
                        (Object[]) null);

                newModel.setPitch(value9);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanFan(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanFanClp newModel = new PlanFanClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getUserId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value1);

                Method method2 = oldModelClass.getMethod("getPlanId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value2);

                Method method3 = oldModelClass.getMethod("getCreated");

                Date value3 = (Date) method3.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value3);

                Method method4 = oldModelClass.getMethod("getDeleted");

                Date value4 = (Date) method4.invoke(oldModel, (Object[]) null);

                newModel.setDeleted(value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanItem(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanItemClp newModel = new PlanItemClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value1);

                Method method2 = oldModelClass.getMethod("getState");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setState(value2);

                Method method3 = oldModelClass.getMethod("getUpdated");

                Date value3 = (Date) method3.invoke(oldModel, (Object[]) null);

                newModel.setUpdated(value3);

                Method method4 = oldModelClass.getMethod("getUpdateAuthorId");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setUpdateAuthorId(value4);

                Method method5 = oldModelClass.getMethod("getUpdateType");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setUpdateType(value5);

                Method method6 = oldModelClass.getMethod("getVersion");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setVersion(value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanMeta(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanMetaClp newModel = new PlanMetaClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value1);

                Method method2 = oldModelClass.getMethod("getPlanTypeId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeId(value2);

                Method method3 = oldModelClass.getMethod("getPlanCreated");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setPlanCreated(value3);

                Method method4 = oldModelClass.getMethod("getAuthorId");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setAuthorId(value4);

                Method method5 = oldModelClass.getMethod("getVotes");

                Integer value5 = (Integer) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setVotes(value5);

                Method method6 = oldModelClass.getMethod("getPlanGroupId");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setPlanGroupId(value6);

                Method method7 = oldModelClass.getMethod("getOpen");

                Boolean value7 = (Boolean) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setOpen(value7);

                Method method8 = oldModelClass.getMethod("getStatus");

                String value8 = (String) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setStatus(value8);

                Method method9 = oldModelClass.getMethod("getMbCategoryId");

                Long value9 = (Long) method9.invoke(oldModel, (Object[]) null);

                newModel.setMbCategoryId(value9);

                Method method10 = oldModelClass.getMethod("getCategoryGroupId");

                Long value10 = (Long) method10.invoke(oldModel, (Object[]) null);

                newModel.setCategoryGroupId(value10);

                Method method11 = oldModelClass.getMethod("getVersion");

                Long value11 = (Long) method11.invoke(oldModel, (Object[]) null);

                newModel.setVersion(value11);

                Method method12 = oldModelClass.getMethod("getPlanVersion");

                Long value12 = (Long) method12.invoke(oldModel, (Object[]) null);

                newModel.setPlanVersion(value12);

                Method method13 = oldModelClass.getMethod("getCreated");

                Date value13 = (Date) method13.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value13);

                Method method14 = oldModelClass.getMethod("getUpdateAuthorId");

                Long value14 = (Long) method14.invoke(oldModel, (Object[]) null);

                newModel.setUpdateAuthorId(value14);

                Method method15 = oldModelClass.getMethod("getModelId");

                Long value15 = (Long) method15.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value15);

                Method method16 = oldModelClass.getMethod("getPromoted");

                Boolean value16 = (Boolean) method16.invoke(oldModel,
                        (Object[]) null);

                newModel.setPromoted(value16);

                Method method17 = oldModelClass.getMethod(
                        "getPreviousContestPhase");

                Long value17 = (Long) method17.invoke(oldModel, (Object[]) null);

                newModel.setPreviousContestPhase(value17);

                Method method18 = oldModelClass.getMethod("getContestPhase");

                Long value18 = (Long) method18.invoke(oldModel, (Object[]) null);

                newModel.setContestPhase(value18);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanModelRun(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanModelRunClp newModel = new PlanModelRunClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value1);

                Method method2 = oldModelClass.getMethod("getScenarioId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setScenarioId(value2);

                Method method3 = oldModelClass.getMethod("getPlanVersion");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setPlanVersion(value3);

                Method method4 = oldModelClass.getMethod("getVersion");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setVersion(value4);

                Method method5 = oldModelClass.getMethod("getCreated");

                Date value5 = (Date) method5.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value5);

                Method method6 = oldModelClass.getMethod("getUpdateAuthorId");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setUpdateAuthorId(value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanPosition(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanPositionClp newModel = new PlanPositionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPlanId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value0);

                Method method1 = oldModelClass.getMethod("getPositionId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPositionId(value1);

                Method method2 = oldModelClass.getMethod("getUserId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value2);

                Method method3 = oldModelClass.getMethod("getUserName");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setUserName(value3);

                Method method4 = oldModelClass.getMethod("getCreateDate");

                Date value4 = (Date) method4.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value4);

                Method method5 = oldModelClass.getMethod("getModifiedDate");

                Date value5 = (Date) method5.invoke(oldModel, (Object[]) null);

                newModel.setModifiedDate(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanPositionItem(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanPositionItemClp newModel = new PlanPositionItemClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPlanPositionsId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanPositionsId(value0);

                Method method1 = oldModelClass.getMethod("getPositionId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPositionId(value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanPositions(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanPositionsClp newModel = new PlanPositionsClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value1);

                Method method2 = oldModelClass.getMethod("getPlanVersion");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanVersion(value2);

                Method method3 = oldModelClass.getMethod("getVersion");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setVersion(value3);

                Method method4 = oldModelClass.getMethod("getCreated");

                Date value4 = (Date) method4.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value4);

                Method method5 = oldModelClass.getMethod("getUpdateAuthorId");

                Long value5 = (Long) method5.invoke(oldModel, (Object[]) null);

                newModel.setUpdateAuthorId(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanPropertyFilter(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanPropertyFilterClp newModel = new PlanPropertyFilterClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getPlanPropertyFilterId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanPropertyFilterId(value0);

                Method method1 = oldModelClass.getMethod("getPropertyName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setPropertyName(value1);

                Method method2 = oldModelClass.getMethod(
                        "getPlanUserSettingsId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanUserSettingsId(value2);

                Method method3 = oldModelClass.getMethod("getValue");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setValue(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanRelated(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanRelatedClp newModel = new PlanRelatedClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getSectionId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setSectionId(value0);

                Method method1 = oldModelClass.getMethod("getRelatedPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setRelatedPlanId(value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanSection(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanSectionClp newModel = new PlanSectionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod(
                        "getPlanSectionDefinitionId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanSectionDefinitionId(value1);

                Method method2 = oldModelClass.getMethod("getPlanId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value2);

                Method method3 = oldModelClass.getMethod("getContent");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setContent(value3);

                Method method4 = oldModelClass.getMethod("getCreated");

                Date value4 = (Date) method4.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value4);

                Method method5 = oldModelClass.getMethod("getVersion");

                Long value5 = (Long) method5.invoke(oldModel, (Object[]) null);

                newModel.setVersion(value5);

                Method method6 = oldModelClass.getMethod("getPlanVersion");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setPlanVersion(value6);

                Method method7 = oldModelClass.getMethod("getUpdateAuthorId");

                Long value7 = (Long) method7.invoke(oldModel, (Object[]) null);

                newModel.setUpdateAuthorId(value7);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanSectionDefinition(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanSectionDefinitionClp newModel = new PlanSectionDefinitionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getAdminTitle");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setAdminTitle(value1);

                Method method2 = oldModelClass.getMethod("getTitle");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setTitle(value2);

                Method method3 = oldModelClass.getMethod("getDefaultText");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setDefaultText(value3);

                Method method4 = oldModelClass.getMethod("getHelpText");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setHelpText(value4);

                Method method5 = oldModelClass.getMethod("getCharacterLimit");

                Integer value5 = (Integer) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setCharacterLimit(value5);

                Method method6 = oldModelClass.getMethod("getFocusAreaId");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setFocusAreaId(value6);

                Method method7 = oldModelClass.getMethod("getLocked");

                Boolean value7 = (Boolean) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setLocked(value7);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanSectionPlanMap(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanSectionPlanMapClp newModel = new PlanSectionPlanMapClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getSectionId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setSectionId(value0);

                Method method1 = oldModelClass.getMethod("getRelatedPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setRelatedPlanId(value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlansFilter(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlansFilterClp newModel = new PlansFilterClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getUserId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value0);

                Method method1 = oldModelClass.getMethod("getPlanTypeId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeId(value1);

                Method method2 = oldModelClass.getMethod("getName");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value2);

                Method method3 = oldModelClass.getMethod("getCreator");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setCreator(value3);

                Method method4 = oldModelClass.getMethod("getDescription");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value4);

                Method method5 = oldModelClass.getMethod("getCO2From");

                Double value5 = (Double) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setCO2From(value5);

                Method method6 = oldModelClass.getMethod("getCO2To");

                Double value6 = (Double) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setCO2To(value6);

                Method method7 = oldModelClass.getMethod("getVotesFrom");

                Double value7 = (Double) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setVotesFrom(value7);

                Method method8 = oldModelClass.getMethod("getVotesTo");

                Double value8 = (Double) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setVotesTo(value8);

                Method method9 = oldModelClass.getMethod("getDamageFrom");

                Double value9 = (Double) method9.invoke(oldModel,
                        (Object[]) null);

                newModel.setDamageFrom(value9);

                Method method10 = oldModelClass.getMethod("getDamageTo");

                Double value10 = (Double) method10.invoke(oldModel,
                        (Object[]) null);

                newModel.setDamageTo(value10);

                Method method11 = oldModelClass.getMethod("getMitigationFrom");

                Double value11 = (Double) method11.invoke(oldModel,
                        (Object[]) null);

                newModel.setMitigationFrom(value11);

                Method method12 = oldModelClass.getMethod("getMitigationTo");

                Double value12 = (Double) method12.invoke(oldModel,
                        (Object[]) null);

                newModel.setMitigationTo(value12);

                Method method13 = oldModelClass.getMethod("getDateFrom");

                Date value13 = (Date) method13.invoke(oldModel, (Object[]) null);

                newModel.setDateFrom(value13);

                Method method14 = oldModelClass.getMethod("getDateTo");

                Date value14 = (Date) method14.invoke(oldModel, (Object[]) null);

                newModel.setDateTo(value14);

                Method method15 = oldModelClass.getMethod(
                        "getFilterPositionsAll");

                Boolean value15 = (Boolean) method15.invoke(oldModel,
                        (Object[]) null);

                newModel.setFilterPositionsAll(value15);

                Method method16 = oldModelClass.getMethod("getEnabled");

                Boolean value16 = (Boolean) method16.invoke(oldModel,
                        (Object[]) null);

                newModel.setEnabled(value16);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlansFilterPosition(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlansFilterPositionClp newModel = new PlansFilterPositionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getUserId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value0);

                Method method1 = oldModelClass.getMethod("getPlanTypeId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeId(value1);

                Method method2 = oldModelClass.getMethod("getPositionId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPositionId(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlansUserSettings(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlansUserSettingsClp newModel = new PlansUserSettingsClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getPlanUserSettingsId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanUserSettingsId(value0);

                Method method1 = oldModelClass.getMethod("getUserId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value1);

                Method method2 = oldModelClass.getMethod("getPlanTypeId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeId(value2);

                Method method3 = oldModelClass.getMethod("getSortColumn");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setSortColumn(value3);

                Method method4 = oldModelClass.getMethod("getSortDirection");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setSortDirection(value4);

                Method method5 = oldModelClass.getMethod("getFilterEnabled");

                Boolean value5 = (Boolean) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setFilterEnabled(value5);

                Method method6 = oldModelClass.getMethod(
                        "getFilterPositionsAll");

                Boolean value6 = (Boolean) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setFilterPositionsAll(value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanTeamHistory(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanTeamHistoryClp newModel = new PlanTeamHistoryClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value1);

                Method method2 = oldModelClass.getMethod("getUserId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value2);

                Method method3 = oldModelClass.getMethod("getAction");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setAction(value3);

                Method method4 = oldModelClass.getMethod("getPayload");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setPayload(value4);

                Method method5 = oldModelClass.getMethod("getCreated");

                Date value5 = (Date) method5.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value5);

                Method method6 = oldModelClass.getMethod("getUpdateAuthorId");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setUpdateAuthorId(value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanTemplate(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanTemplateClp newModel = new PlanTemplateClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanTemplateSection(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanTemplateSectionClp newModel = new PlanTemplateSectionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPlanTemplateId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanTemplateId(value0);

                Method method1 = oldModelClass.getMethod("getPlanSectionId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanSectionId(value1);

                Method method2 = oldModelClass.getMethod("getWeight");

                Integer value2 = (Integer) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setWeight(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanType(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanTypeClp newModel = new PlanTypeClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPlanTypeId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeId(value0);

                Method method1 = oldModelClass.getMethod("getName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value1);

                Method method2 = oldModelClass.getMethod("getDescription");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value2);

                Method method3 = oldModelClass.getMethod("getModelId");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value3);

                Method method4 = oldModelClass.getMethod("getModelTypeName");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelTypeName(value4);

                Method method5 = oldModelClass.getMethod("getPublished");

                Boolean value5 = (Boolean) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setPublished(value5);

                Method method6 = oldModelClass.getMethod(
                        "getPublishedCounterpartId");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setPublishedCounterpartId(value6);

                Method method7 = oldModelClass.getMethod("getIsDefault");

                Boolean value7 = (Boolean) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setIsDefault(value7);

                Method method8 = oldModelClass.getMethod("getDefaultModelId");

                Long value8 = (Long) method8.invoke(oldModel, (Object[]) null);

                newModel.setDefaultModelId(value8);

                Method method9 = oldModelClass.getMethod("getDefaultScenarioId");

                Long value9 = (Long) method9.invoke(oldModel, (Object[]) null);

                newModel.setDefaultScenarioId(value9);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanTypeAttribute(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanTypeAttributeClp newModel = new PlanTypeAttributeClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getPlanTypeAttributeId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeAttributeId(value0);

                Method method1 = oldModelClass.getMethod("getPlanTypeId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeId(value1);

                Method method2 = oldModelClass.getMethod("getAttributeName");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setAttributeName(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanTypeColumn(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanTypeColumnClp newModel = new PlanTypeColumnClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPlanTypeColumnId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeColumnId(value0);

                Method method1 = oldModelClass.getMethod("getPlanTypeId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeId(value1);

                Method method2 = oldModelClass.getMethod("getWeight");

                Integer value2 = (Integer) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setWeight(value2);

                Method method3 = oldModelClass.getMethod("getColumnName");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setColumnName(value3);

                Method method4 = oldModelClass.getMethod("getVisibleByDefault");

                Boolean value4 = (Boolean) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setVisibleByDefault(value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanVote(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanVoteClp newModel = new PlanVoteClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getUserId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value0);

                Method method1 = oldModelClass.getMethod("getContestId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setContestId(value1);

                Method method2 = oldModelClass.getMethod("getPlanId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value2);

                Method method3 = oldModelClass.getMethod("getCreateDate");

                Date value3 = (Date) method3.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }
}
