package com.ext.portlet.contests.service;

import com.ext.portlet.contests.model.ContestClp;
import com.ext.portlet.contests.model.ContestDebateClp;
import com.ext.portlet.contests.model.ContestPhaseClp;
import com.ext.portlet.contests.model.ContestPhaseColumnClp;
import com.ext.portlet.contests.model.ContestTeamMemberClp;

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

        if (oldModelClassName.equals(ContestClp.class.getName())) {
            return translateInputContest(oldModel);
        }

        if (oldModelClassName.equals(ContestDebateClp.class.getName())) {
            return translateInputContestDebate(oldModel);
        }

        if (oldModelClassName.equals(ContestPhaseClp.class.getName())) {
            return translateInputContestPhase(oldModel);
        }

        if (oldModelClassName.equals(ContestPhaseColumnClp.class.getName())) {
            return translateInputContestPhaseColumn(oldModel);
        }

        if (oldModelClassName.equals(ContestTeamMemberClp.class.getName())) {
            return translateInputContestTeamMember(oldModel);
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

    public static Object translateInputContest(BaseModel<?> oldModel) {
        ContestClp oldCplModel = (ContestClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.contests.model.impl.ContestImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setContestPK",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getContestPK());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setContestName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getContestName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setContestShortName",
                        new Class[] { String.class });

                String value2 = oldCplModel.getContestShortName();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setContestDescription",
                        new Class[] { String.class });

                String value3 = oldCplModel.getContestDescription();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setContestModelDescription",
                        new Class[] { String.class });

                String value4 = oldCplModel.getContestModelDescription();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setContestPositionsDescription",
                        new Class[] { String.class });

                String value5 = oldCplModel.getContestPositionsDescription();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setDefaultPlanDescription",
                        new Class[] { String.class });

                String value6 = oldCplModel.getDefaultPlanDescription();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setPlanTypeId",
                        new Class[] { Long.TYPE });

                Long value7 = new Long(oldCplModel.getPlanTypeId());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value8 = oldCplModel.getCreated();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setUpdated",
                        new Class[] { Date.class });

                Date value9 = oldCplModel.getUpdated();

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setAuthorId",
                        new Class[] { Long.TYPE });

                Long value10 = new Long(oldCplModel.getAuthorId());

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setContestActive",
                        new Class[] { Boolean.TYPE });

                Boolean value11 = new Boolean(oldCplModel.getContestActive());

                method11.invoke(newModel, value11);

                Method method12 = newModelClass.getMethod("setPlanTemplateId",
                        new Class[] { Long.TYPE });

                Long value12 = new Long(oldCplModel.getPlanTemplateId());

                method12.invoke(newModel, value12);

                Method method13 = newModelClass.getMethod("setFocusAreaId",
                        new Class[] { Long.TYPE });

                Long value13 = new Long(oldCplModel.getFocusAreaId());

                method13.invoke(newModel, value13);

                Method method14 = newModelClass.getMethod("setContestLogoId",
                        new Class[] { Long.TYPE });

                Long value14 = new Long(oldCplModel.getContestLogoId());

                method14.invoke(newModel, value14);

                Method method15 = newModelClass.getMethod("setFeatured",
                        new Class[] { Boolean.TYPE });

                Boolean value15 = new Boolean(oldCplModel.getFeatured());

                method15.invoke(newModel, value15);

                Method method16 = newModelClass.getMethod("setPlansOpenByDefault",
                        new Class[] { Boolean.TYPE });

                Boolean value16 = new Boolean(oldCplModel.getPlansOpenByDefault());

                method16.invoke(newModel, value16);

                Method method17 = newModelClass.getMethod("setFlag",
                        new Class[] { Integer.TYPE });

                Integer value17 = new Integer(oldCplModel.getFlag());

                method17.invoke(newModel, value17);

                Method method18 = newModelClass.getMethod("setFlagText",
                        new Class[] { String.class });

                String value18 = oldCplModel.getFlagText();

                method18.invoke(newModel, value18);

                Method method19 = newModelClass.getMethod("setGroupId",
                        new Class[] { Long.TYPE });

                Long value19 = new Long(oldCplModel.getGroupId());

                method19.invoke(newModel, value19);

                Method method20 = newModelClass.getMethod("setDiscussionGroupId",
                        new Class[] { Long.TYPE });

                Long value20 = new Long(oldCplModel.getDiscussionGroupId());

                method20.invoke(newModel, value20);

                Method method21 = newModelClass.getMethod("setWeight",
                        new Class[] { Integer.TYPE });

                Integer value21 = new Integer(oldCplModel.getWeight());

                method21.invoke(newModel, value21);

                Method method22 = newModelClass.getMethod("setResourcesUrl",
                        new Class[] { String.class });

                String value22 = oldCplModel.getResourcesUrl();

                method22.invoke(newModel, value22);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputContestDebate(BaseModel<?> oldModel) {
        ContestDebateClp oldCplModel = (ContestDebateClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.contests.model.impl.ContestDebateImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setDebateId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getDebateId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setContestPK",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getContestPK());

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

    public static Object translateInputContestPhase(BaseModel<?> oldModel) {
        ContestPhaseClp oldCplModel = (ContestPhaseClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.contests.model.impl.ContestPhaseImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setContestPhasePK",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getContestPhasePK());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setContestPK",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getContestPK());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setContestPhaseName",
                        new Class[] { String.class });

                String value2 = oldCplModel.getContestPhaseName();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setContestPhaseDescription",
                        new Class[] { String.class });

                String value3 = oldCplModel.getContestPhaseDescription();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setContestPhaseStatus",
                        new Class[] { String.class });

                String value4 = oldCplModel.getContestPhaseStatus();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setPhaseStartDate",
                        new Class[] { Date.class });

                Date value5 = oldCplModel.getPhaseStartDate();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setPhaseEndDate",
                        new Class[] { Date.class });

                Date value6 = oldCplModel.getPhaseEndDate();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setNextStatus",
                        new Class[] { String.class });

                String value7 = oldCplModel.getNextStatus();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value8 = oldCplModel.getCreated();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setUpdated",
                        new Class[] { Date.class });

                Date value9 = oldCplModel.getUpdated();

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setAuthorId",
                        new Class[] { Long.TYPE });

                Long value10 = new Long(oldCplModel.getAuthorId());

                method10.invoke(newModel, value10);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputContestPhaseColumn(BaseModel<?> oldModel) {
        ContestPhaseColumnClp oldCplModel = (ContestPhaseColumnClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.contests.model.impl.ContestPhaseColumnImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setContestPhasePK",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getContestPhasePK());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setColumnName",
                        new Class[] { String.class });

                String value2 = oldCplModel.getColumnName();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setColumnWeight",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getColumnWeight());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setDefaultSort",
                        new Class[] { Boolean.TYPE });

                Boolean value4 = new Boolean(oldCplModel.getDefaultSort());

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

    public static Object translateInputContestTeamMember(BaseModel<?> oldModel) {
        ContestTeamMemberClp oldCplModel = (ContestTeamMemberClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.contests.model.impl.ContestTeamMemberImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setContestId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getContestId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getUserId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setRole",
                        new Class[] { String.class });

                String value3 = oldCplModel.getRole();

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
                    "com.ext.portlet.contests.model.impl.ContestImpl")) {
            return translateOutputContest(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.contests.model.impl.ContestDebateImpl")) {
            return translateOutputContestDebate(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.contests.model.impl.ContestPhaseImpl")) {
            return translateOutputContestPhase(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.contests.model.impl.ContestPhaseColumnImpl")) {
            return translateOutputContestPhaseColumn(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.contests.model.impl.ContestTeamMemberImpl")) {
            return translateOutputContestTeamMember(oldModel);
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

    public static Object translateOutputContest(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ContestClp newModel = new ContestClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getContestPK");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setContestPK(value0);

                Method method1 = oldModelClass.getMethod("getContestName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestName(value1);

                Method method2 = oldModelClass.getMethod("getContestShortName");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestShortName(value2);

                Method method3 = oldModelClass.getMethod(
                        "getContestDescription");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestDescription(value3);

                Method method4 = oldModelClass.getMethod(
                        "getContestModelDescription");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestModelDescription(value4);

                Method method5 = oldModelClass.getMethod(
                        "getContestPositionsDescription");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestPositionsDescription(value5);

                Method method6 = oldModelClass.getMethod(
                        "getDefaultPlanDescription");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setDefaultPlanDescription(value6);

                Method method7 = oldModelClass.getMethod("getPlanTypeId");

                Long value7 = (Long) method7.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeId(value7);

                Method method8 = oldModelClass.getMethod("getCreated");

                Date value8 = (Date) method8.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value8);

                Method method9 = oldModelClass.getMethod("getUpdated");

                Date value9 = (Date) method9.invoke(oldModel, (Object[]) null);

                newModel.setUpdated(value9);

                Method method10 = oldModelClass.getMethod("getAuthorId");

                Long value10 = (Long) method10.invoke(oldModel, (Object[]) null);

                newModel.setAuthorId(value10);

                Method method11 = oldModelClass.getMethod("getContestActive");

                Boolean value11 = (Boolean) method11.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestActive(value11);

                Method method12 = oldModelClass.getMethod("getPlanTemplateId");

                Long value12 = (Long) method12.invoke(oldModel, (Object[]) null);

                newModel.setPlanTemplateId(value12);

                Method method13 = oldModelClass.getMethod("getFocusAreaId");

                Long value13 = (Long) method13.invoke(oldModel, (Object[]) null);

                newModel.setFocusAreaId(value13);

                Method method14 = oldModelClass.getMethod("getContestLogoId");

                Long value14 = (Long) method14.invoke(oldModel, (Object[]) null);

                newModel.setContestLogoId(value14);

                Method method15 = oldModelClass.getMethod("getFeatured");

                Boolean value15 = (Boolean) method15.invoke(oldModel,
                        (Object[]) null);

                newModel.setFeatured(value15);

                Method method16 = oldModelClass.getMethod(
                        "getPlansOpenByDefault");

                Boolean value16 = (Boolean) method16.invoke(oldModel,
                        (Object[]) null);

                newModel.setPlansOpenByDefault(value16);

                Method method17 = oldModelClass.getMethod("getFlag");

                Integer value17 = (Integer) method17.invoke(oldModel,
                        (Object[]) null);

                newModel.setFlag(value17);

                Method method18 = oldModelClass.getMethod("getFlagText");

                String value18 = (String) method18.invoke(oldModel,
                        (Object[]) null);

                newModel.setFlagText(value18);

                Method method19 = oldModelClass.getMethod("getGroupId");

                Long value19 = (Long) method19.invoke(oldModel, (Object[]) null);

                newModel.setGroupId(value19);

                Method method20 = oldModelClass.getMethod(
                        "getDiscussionGroupId");

                Long value20 = (Long) method20.invoke(oldModel, (Object[]) null);

                newModel.setDiscussionGroupId(value20);

                Method method21 = oldModelClass.getMethod("getWeight");

                Integer value21 = (Integer) method21.invoke(oldModel,
                        (Object[]) null);

                newModel.setWeight(value21);

                Method method22 = oldModelClass.getMethod("getResourcesUrl");

                String value22 = (String) method22.invoke(oldModel,
                        (Object[]) null);

                newModel.setResourcesUrl(value22);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputContestDebate(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ContestDebateClp newModel = new ContestDebateClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getDebateId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setDebateId(value1);

                Method method2 = oldModelClass.getMethod("getContestPK");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setContestPK(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputContestPhase(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ContestPhaseClp newModel = new ContestPhaseClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getContestPhasePK");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setContestPhasePK(value0);

                Method method1 = oldModelClass.getMethod("getContestPK");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setContestPK(value1);

                Method method2 = oldModelClass.getMethod("getContestPhaseName");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestPhaseName(value2);

                Method method3 = oldModelClass.getMethod(
                        "getContestPhaseDescription");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestPhaseDescription(value3);

                Method method4 = oldModelClass.getMethod(
                        "getContestPhaseStatus");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestPhaseStatus(value4);

                Method method5 = oldModelClass.getMethod("getPhaseStartDate");

                Date value5 = (Date) method5.invoke(oldModel, (Object[]) null);

                newModel.setPhaseStartDate(value5);

                Method method6 = oldModelClass.getMethod("getPhaseEndDate");

                Date value6 = (Date) method6.invoke(oldModel, (Object[]) null);

                newModel.setPhaseEndDate(value6);

                Method method7 = oldModelClass.getMethod("getNextStatus");

                String value7 = (String) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setNextStatus(value7);

                Method method8 = oldModelClass.getMethod("getCreated");

                Date value8 = (Date) method8.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value8);

                Method method9 = oldModelClass.getMethod("getUpdated");

                Date value9 = (Date) method9.invoke(oldModel, (Object[]) null);

                newModel.setUpdated(value9);

                Method method10 = oldModelClass.getMethod("getAuthorId");

                Long value10 = (Long) method10.invoke(oldModel, (Object[]) null);

                newModel.setAuthorId(value10);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputContestPhaseColumn(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ContestPhaseColumnClp newModel = new ContestPhaseColumnClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getContestPhasePK");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setContestPhasePK(value1);

                Method method2 = oldModelClass.getMethod("getColumnName");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setColumnName(value2);

                Method method3 = oldModelClass.getMethod("getColumnWeight");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setColumnWeight(value3);

                Method method4 = oldModelClass.getMethod("getDefaultSort");

                Boolean value4 = (Boolean) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setDefaultSort(value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputContestTeamMember(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ContestTeamMemberClp newModel = new ContestTeamMemberClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getContestId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setContestId(value1);

                Method method2 = oldModelClass.getMethod("getUserId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value2);

                Method method3 = oldModelClass.getMethod("getRole");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setRole(value3);

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
