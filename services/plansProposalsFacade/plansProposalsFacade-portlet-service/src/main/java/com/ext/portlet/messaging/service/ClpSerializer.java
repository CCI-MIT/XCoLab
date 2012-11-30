package com.ext.portlet.messaging.service;

import com.ext.portlet.messaging.model.MessageClp;
import com.ext.portlet.messaging.model.MessageRecipientStatusClp;
import com.ext.portlet.messaging.model.MessagingUserPreferencesClp;

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

        if (oldModelClassName.equals(MessageClp.class.getName())) {
            return translateInputMessage(oldModel);
        }

        if (oldModelClassName.equals(MessageRecipientStatusClp.class.getName())) {
            return translateInputMessageRecipientStatus(oldModel);
        }

        if (oldModelClassName.equals(
                    MessagingUserPreferencesClp.class.getName())) {
            return translateInputMessagingUserPreferences(oldModel);
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

    public static Object translateInputMessage(BaseModel<?> oldModel) {
        MessageClp oldCplModel = (MessageClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.messaging.model.impl.MessageImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setMessageId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getMessageId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setFromId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getFromId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setRepliesTo",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getRepliesTo());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value3 = oldCplModel.getCreateDate();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setSubject",
                        new Class[] { String.class });

                String value4 = oldCplModel.getSubject();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setContent",
                        new Class[] { String.class });

                String value5 = oldCplModel.getContent();

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

    public static Object translateInputMessageRecipientStatus(
        BaseModel<?> oldModel) {
        MessageRecipientStatusClp oldCplModel = (MessageRecipientStatusClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.messaging.model.impl.MessageRecipientStatusImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setMessageRecipientId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getMessageRecipientId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setMessageId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getMessageId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getUserId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setOpened",
                        new Class[] { Boolean.TYPE });

                Boolean value3 = new Boolean(oldCplModel.getOpened());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setArchived",
                        new Class[] { Boolean.TYPE });

                Boolean value4 = new Boolean(oldCplModel.getArchived());

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

    public static Object translateInputMessagingUserPreferences(
        BaseModel<?> oldModel) {
        MessagingUserPreferencesClp oldCplModel = (MessagingUserPreferencesClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.messaging.model.impl.MessagingUserPreferencesImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setMessagingPreferencesId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getMessagingPreferencesId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getUserId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setEmailOnSend",
                        new Class[] { Boolean.TYPE });

                Boolean value2 = new Boolean(oldCplModel.getEmailOnSend());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setEmailOnReceipt",
                        new Class[] { Boolean.TYPE });

                Boolean value3 = new Boolean(oldCplModel.getEmailOnReceipt());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setEmailOnActivity",
                        new Class[] { Boolean.TYPE });

                Boolean value4 = new Boolean(oldCplModel.getEmailOnActivity());

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
                    "com.ext.portlet.messaging.model.impl.MessageImpl")) {
            return translateOutputMessage(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.messaging.model.impl.MessageRecipientStatusImpl")) {
            return translateOutputMessageRecipientStatus(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.messaging.model.impl.MessagingUserPreferencesImpl")) {
            return translateOutputMessagingUserPreferences(oldModel);
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

    public static Object translateOutputMessage(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                MessageClp newModel = new MessageClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getMessageId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setMessageId(value0);

                Method method1 = oldModelClass.getMethod("getFromId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setFromId(value1);

                Method method2 = oldModelClass.getMethod("getRepliesTo");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setRepliesTo(value2);

                Method method3 = oldModelClass.getMethod("getCreateDate");

                Date value3 = (Date) method3.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value3);

                Method method4 = oldModelClass.getMethod("getSubject");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setSubject(value4);

                Method method5 = oldModelClass.getMethod("getContent");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setContent(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputMessageRecipientStatus(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                MessageRecipientStatusClp newModel = new MessageRecipientStatusClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getMessageRecipientId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setMessageRecipientId(value0);

                Method method1 = oldModelClass.getMethod("getMessageId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setMessageId(value1);

                Method method2 = oldModelClass.getMethod("getUserId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value2);

                Method method3 = oldModelClass.getMethod("getOpened");

                Boolean value3 = (Boolean) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setOpened(value3);

                Method method4 = oldModelClass.getMethod("getArchived");

                Boolean value4 = (Boolean) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setArchived(value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputMessagingUserPreferences(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                MessagingUserPreferencesClp newModel = new MessagingUserPreferencesClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getMessagingPreferencesId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setMessagingPreferencesId(value0);

                Method method1 = oldModelClass.getMethod("getUserId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value1);

                Method method2 = oldModelClass.getMethod("getEmailOnSend");

                Boolean value2 = (Boolean) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setEmailOnSend(value2);

                Method method3 = oldModelClass.getMethod("getEmailOnReceipt");

                Boolean value3 = (Boolean) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setEmailOnReceipt(value3);

                Method method4 = oldModelClass.getMethod("getEmailOnActivity");

                Boolean value4 = (Boolean) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setEmailOnActivity(value4);

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
