package com.ext.portlet.Activity.service;

import com.ext.portlet.Activity.model.ActivitySubscriptionClp;

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

        if (oldModelClassName.equals(ActivitySubscriptionClp.class.getName())) {
            return translateInputActivitySubscription(oldModel);
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

    public static Object translateInputActivitySubscription(
        BaseModel<?> oldModel) {
        ActivitySubscriptionClp oldCplModel = (ActivitySubscriptionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.Activity.model.impl.ActivitySubscriptionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPk",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPk());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setClassNameId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getClassNameId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setClassPK",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getClassPK());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setType",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getType());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setExtraData",
                        new Class[] { String.class });

                String value4 = oldCplModel.getExtraData();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setReceiverId",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getReceiverId());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value6 = oldCplModel.getCreateDate();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setModifiedDate",
                        new Class[] { Date.class });

                Date value7 = oldCplModel.getModifiedDate();

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
                    "com.ext.portlet.Activity.model.impl.ActivitySubscriptionImpl")) {
            return translateOutputActivitySubscription(oldModel);
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

    public static Object translateOutputActivitySubscription(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ActivitySubscriptionClp newModel = new ActivitySubscriptionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPk");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPk(value0);

                Method method1 = oldModelClass.getMethod("getClassNameId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setClassNameId(value1);

                Method method2 = oldModelClass.getMethod("getClassPK");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setClassPK(value2);

                Method method3 = oldModelClass.getMethod("getType");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setType(value3);

                Method method4 = oldModelClass.getMethod("getExtraData");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setExtraData(value4);

                Method method5 = oldModelClass.getMethod("getReceiverId");

                Long value5 = (Long) method5.invoke(oldModel, (Object[]) null);

                newModel.setReceiverId(value5);

                Method method6 = oldModelClass.getMethod("getCreateDate");

                Date value6 = (Date) method6.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value6);

                Method method7 = oldModelClass.getMethod("getModifiedDate");

                Date value7 = (Date) method7.invoke(oldModel, (Object[]) null);

                newModel.setModifiedDate(value7);

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
