package com.ext.portlet.discussions.service;

import com.ext.portlet.discussions.model.DiscussionCategoryClp;
import com.ext.portlet.discussions.model.DiscussionCategoryGroupClp;
import com.ext.portlet.discussions.model.DiscussionMessageClp;
import com.ext.portlet.discussions.model.DiscussionMessageFlagClp;

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

        if (oldModelClassName.equals(DiscussionCategoryClp.class.getName())) {
            return translateInputDiscussionCategory(oldModel);
        }

        if (oldModelClassName.equals(DiscussionCategoryGroupClp.class.getName())) {
            return translateInputDiscussionCategoryGroup(oldModel);
        }

        if (oldModelClassName.equals(DiscussionMessageClp.class.getName())) {
            return translateInputDiscussionMessage(oldModel);
        }

        if (oldModelClassName.equals(DiscussionMessageFlagClp.class.getName())) {
            return translateInputDiscussionMessageFlag(oldModel);
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

    public static Object translateInputDiscussionCategory(BaseModel<?> oldModel) {
        DiscussionCategoryClp oldCplModel = (DiscussionCategoryClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.discussions.model.impl.DiscussionCategoryImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPk",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPk());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setCategoryId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getCategoryId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setCategoryGroupId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getCategoryGroupId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setAuthorId",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getAuthorId());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value4 = oldCplModel.getName();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value5 = oldCplModel.getDescription();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value6 = oldCplModel.getCreateDate();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setDeleted",
                        new Class[] { Date.class });

                Date value7 = oldCplModel.getDeleted();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setThreadsCount",
                        new Class[] { Integer.TYPE });

                Integer value8 = new Integer(oldCplModel.getThreadsCount());

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setLastActivityDate",
                        new Class[] { Date.class });

                Date value9 = oldCplModel.getLastActivityDate();

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setLastActivityAuthorId",
                        new Class[] { Long.TYPE });

                Long value10 = new Long(oldCplModel.getLastActivityAuthorId());

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

    public static Object translateInputDiscussionCategoryGroup(
        BaseModel<?> oldModel) {
        DiscussionCategoryGroupClp oldCplModel = (DiscussionCategoryGroupClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.discussions.model.impl.DiscussionCategoryGroupImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value1 = oldCplModel.getDescription();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setUrl",
                        new Class[] { String.class });

                String value2 = oldCplModel.getUrl();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCommentsThread",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getCommentsThread());

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

    public static Object translateInputDiscussionMessage(BaseModel<?> oldModel) {
        DiscussionMessageClp oldCplModel = (DiscussionMessageClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.discussions.model.impl.DiscussionMessageImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPk",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPk());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setMessageId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getMessageId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setSubject",
                        new Class[] { String.class });

                String value2 = oldCplModel.getSubject();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setBody",
                        new Class[] { String.class });

                String value3 = oldCplModel.getBody();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setThreadId",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getThreadId());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setCategoryId",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getCategoryId());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setCategoryGroupId",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getCategoryGroupId());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setAuthorId",
                        new Class[] { Long.TYPE });

                Long value7 = new Long(oldCplModel.getAuthorId());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value8 = oldCplModel.getCreateDate();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setVersion",
                        new Class[] { Long.TYPE });

                Long value9 = new Long(oldCplModel.getVersion());

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setDeleted",
                        new Class[] { Date.class });

                Date value10 = oldCplModel.getDeleted();

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setResponsesCount",
                        new Class[] { Integer.TYPE });

                Integer value11 = new Integer(oldCplModel.getResponsesCount());

                method11.invoke(newModel, value11);

                Method method12 = newModelClass.getMethod("setLastActivityDate",
                        new Class[] { Date.class });

                Date value12 = oldCplModel.getLastActivityDate();

                method12.invoke(newModel, value12);

                Method method13 = newModelClass.getMethod("setLastActivityAuthorId",
                        new Class[] { Long.TYPE });

                Long value13 = new Long(oldCplModel.getLastActivityAuthorId());

                method13.invoke(newModel, value13);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputDiscussionMessageFlag(
        BaseModel<?> oldModel) {
        DiscussionMessageFlagClp oldCplModel = (DiscussionMessageFlagClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.discussions.model.impl.DiscussionMessageFlagImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPk",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPk());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setMessageId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getMessageId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setFlagType",
                        new Class[] { String.class });

                String value2 = oldCplModel.getFlagType();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setData",
                        new Class[] { String.class });

                String value3 = oldCplModel.getData();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value4 = oldCplModel.getCreated();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getUserId());

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
                    "com.ext.portlet.discussions.model.impl.DiscussionCategoryImpl")) {
            return translateOutputDiscussionCategory(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.discussions.model.impl.DiscussionCategoryGroupImpl")) {
            return translateOutputDiscussionCategoryGroup(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.discussions.model.impl.DiscussionMessageImpl")) {
            return translateOutputDiscussionMessage(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.discussions.model.impl.DiscussionMessageFlagImpl")) {
            return translateOutputDiscussionMessageFlag(oldModel);
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

    public static Object translateOutputDiscussionCategory(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                DiscussionCategoryClp newModel = new DiscussionCategoryClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPk");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPk(value0);

                Method method1 = oldModelClass.getMethod("getCategoryId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setCategoryId(value1);

                Method method2 = oldModelClass.getMethod("getCategoryGroupId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setCategoryGroupId(value2);

                Method method3 = oldModelClass.getMethod("getAuthorId");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setAuthorId(value3);

                Method method4 = oldModelClass.getMethod("getName");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value4);

                Method method5 = oldModelClass.getMethod("getDescription");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value5);

                Method method6 = oldModelClass.getMethod("getCreateDate");

                Date value6 = (Date) method6.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value6);

                Method method7 = oldModelClass.getMethod("getDeleted");

                Date value7 = (Date) method7.invoke(oldModel, (Object[]) null);

                newModel.setDeleted(value7);

                Method method8 = oldModelClass.getMethod("getThreadsCount");

                Integer value8 = (Integer) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setThreadsCount(value8);

                Method method9 = oldModelClass.getMethod("getLastActivityDate");

                Date value9 = (Date) method9.invoke(oldModel, (Object[]) null);

                newModel.setLastActivityDate(value9);

                Method method10 = oldModelClass.getMethod(
                        "getLastActivityAuthorId");

                Long value10 = (Long) method10.invoke(oldModel, (Object[]) null);

                newModel.setLastActivityAuthorId(value10);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputDiscussionCategoryGroup(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                DiscussionCategoryGroupClp newModel = new DiscussionCategoryGroupClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getDescription");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value1);

                Method method2 = oldModelClass.getMethod("getUrl");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setUrl(value2);

                Method method3 = oldModelClass.getMethod("getCommentsThread");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setCommentsThread(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputDiscussionMessage(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                DiscussionMessageClp newModel = new DiscussionMessageClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPk");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPk(value0);

                Method method1 = oldModelClass.getMethod("getMessageId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setMessageId(value1);

                Method method2 = oldModelClass.getMethod("getSubject");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setSubject(value2);

                Method method3 = oldModelClass.getMethod("getBody");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setBody(value3);

                Method method4 = oldModelClass.getMethod("getThreadId");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setThreadId(value4);

                Method method5 = oldModelClass.getMethod("getCategoryId");

                Long value5 = (Long) method5.invoke(oldModel, (Object[]) null);

                newModel.setCategoryId(value5);

                Method method6 = oldModelClass.getMethod("getCategoryGroupId");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setCategoryGroupId(value6);

                Method method7 = oldModelClass.getMethod("getAuthorId");

                Long value7 = (Long) method7.invoke(oldModel, (Object[]) null);

                newModel.setAuthorId(value7);

                Method method8 = oldModelClass.getMethod("getCreateDate");

                Date value8 = (Date) method8.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value8);

                Method method9 = oldModelClass.getMethod("getVersion");

                Long value9 = (Long) method9.invoke(oldModel, (Object[]) null);

                newModel.setVersion(value9);

                Method method10 = oldModelClass.getMethod("getDeleted");

                Date value10 = (Date) method10.invoke(oldModel, (Object[]) null);

                newModel.setDeleted(value10);

                Method method11 = oldModelClass.getMethod("getResponsesCount");

                Integer value11 = (Integer) method11.invoke(oldModel,
                        (Object[]) null);

                newModel.setResponsesCount(value11);

                Method method12 = oldModelClass.getMethod("getLastActivityDate");

                Date value12 = (Date) method12.invoke(oldModel, (Object[]) null);

                newModel.setLastActivityDate(value12);

                Method method13 = oldModelClass.getMethod(
                        "getLastActivityAuthorId");

                Long value13 = (Long) method13.invoke(oldModel, (Object[]) null);

                newModel.setLastActivityAuthorId(value13);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputDiscussionMessageFlag(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                DiscussionMessageFlagClp newModel = new DiscussionMessageFlagClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPk");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPk(value0);

                Method method1 = oldModelClass.getMethod("getMessageId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setMessageId(value1);

                Method method2 = oldModelClass.getMethod("getFlagType");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setFlagType(value2);

                Method method3 = oldModelClass.getMethod("getData");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setData(value3);

                Method method4 = oldModelClass.getMethod("getCreated");

                Date value4 = (Date) method4.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value4);

                Method method5 = oldModelClass.getMethod("getUserId");

                Long value5 = (Long) method5.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value5);

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
