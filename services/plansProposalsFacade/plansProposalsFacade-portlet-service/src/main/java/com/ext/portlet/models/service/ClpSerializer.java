package com.ext.portlet.models.service;

import com.ext.portlet.models.model.ModelCategoryClp;
import com.ext.portlet.models.model.ModelDiscussionClp;
import com.ext.portlet.models.model.ModelGlobalPreferenceClp;
import com.ext.portlet.models.model.ModelInputGroupClp;
import com.ext.portlet.models.model.ModelInputItemClp;
import com.ext.portlet.models.model.ModelOutputChartOrderClp;
import com.ext.portlet.models.model.ModelOutputItemClp;
import com.ext.portlet.models.model.ModelPositionClp;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import java.lang.reflect.Method;

import java.util.ArrayList;
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

        if (oldModelClassName.equals(ModelCategoryClp.class.getName())) {
            return translateInputModelCategory(oldModel);
        }

        if (oldModelClassName.equals(ModelDiscussionClp.class.getName())) {
            return translateInputModelDiscussion(oldModel);
        }

        if (oldModelClassName.equals(ModelGlobalPreferenceClp.class.getName())) {
            return translateInputModelGlobalPreference(oldModel);
        }

        if (oldModelClassName.equals(ModelInputGroupClp.class.getName())) {
            return translateInputModelInputGroup(oldModel);
        }

        if (oldModelClassName.equals(ModelInputItemClp.class.getName())) {
            return translateInputModelInputItem(oldModel);
        }

        if (oldModelClassName.equals(ModelOutputChartOrderClp.class.getName())) {
            return translateInputModelOutputChartOrder(oldModel);
        }

        if (oldModelClassName.equals(ModelOutputItemClp.class.getName())) {
            return translateInputModelOutputItem(oldModel);
        }

        if (oldModelClassName.equals(ModelPositionClp.class.getName())) {
            return translateInputModelPosition(oldModel);
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

    public static Object translateInputModelCategory(BaseModel<?> oldModel) {
        ModelCategoryClp oldCplModel = (ModelCategoryClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.models.model.impl.ModelCategoryImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setModelCategoryPK",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getModelCategoryPK());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setModelCategoryName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getModelCategoryName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setModelCategoryDescription",
                        new Class[] { String.class });

                String value2 = oldCplModel.getModelCategoryDescription();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setModelCategoryDisplayWeight",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getModelCategoryDisplayWeight());

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

    public static Object translateInputModelDiscussion(BaseModel<?> oldModel) {
        ModelDiscussionClp oldCplModel = (ModelDiscussionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.models.model.impl.ModelDiscussionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setModelDiscussionId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getModelDiscussionId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getModelId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setCategoryId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getCategoryId());

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

    public static Object translateInputModelGlobalPreference(
        BaseModel<?> oldModel) {
        ModelGlobalPreferenceClp oldCplModel = (ModelGlobalPreferenceClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.models.model.impl.ModelGlobalPreferenceImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setModelGlobalPreferencePK",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getModelGlobalPreferencePK());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getModelId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setVisible",
                        new Class[] { Boolean.TYPE });

                Boolean value2 = new Boolean(oldCplModel.getVisible());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setWeight",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getWeight());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setExpertEvaluationPageId",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getExpertEvaluationPageId());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setModelCategoryId",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getModelCategoryId());

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

    public static Object translateInputModelInputGroup(BaseModel<?> oldModel) {
        ModelInputGroupClp oldCplModel = (ModelInputGroupClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.models.model.impl.ModelInputGroupImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setModelInputGroupPK",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getModelInputGroupPK());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getModelId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setNameAndDescriptionMetaDataId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getNameAndDescriptionMetaDataId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value3 = oldCplModel.getName();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value4 = oldCplModel.getDescription();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setDisplayItemOrder",
                        new Class[] { Integer.TYPE });

                Integer value5 = new Integer(oldCplModel.getDisplayItemOrder());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setGroupType",
                        new Class[] { String.class });

                String value6 = oldCplModel.getGroupType();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setParentGroupPK",
                        new Class[] { Long.TYPE });

                Long value7 = new Long(oldCplModel.getParentGroupPK());

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

    public static Object translateInputModelInputItem(BaseModel<?> oldModel) {
        ModelInputItemClp oldCplModel = (ModelInputItemClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.models.model.impl.ModelInputItemImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setModelInputItemPK",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getModelInputItemPK());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getModelId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setModelInputItemID",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getModelInputItemID());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setModelGroupId",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getModelGroupId());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setDisplayItemOrder",
                        new Class[] { Integer.TYPE });

                Integer value4 = new Integer(oldCplModel.getDisplayItemOrder());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setType",
                        new Class[] { String.class });

                String value5 = oldCplModel.getType();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setProperties",
                        new Class[] { String.class });

                String value6 = oldCplModel.getProperties();

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

    public static Object translateInputModelOutputChartOrder(
        BaseModel<?> oldModel) {
        ModelOutputChartOrderClp oldCplModel = (ModelOutputChartOrderClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.models.model.impl.ModelOutputChartOrderImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setModelOutputChartOrderPK",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getModelOutputChartOrderPK());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getModelId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setModelOutputLabel",
                        new Class[] { String.class });

                String value2 = oldCplModel.getModelOutputLabel();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setModelOutputChartOrder",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getModelOutputChartOrder());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setModelIndexRangePolicy",
                        new Class[] { String.class });

                String value4 = oldCplModel.getModelIndexRangePolicy();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setModelIndexRangeMessage",
                        new Class[] { String.class });

                String value5 = oldCplModel.getModelIndexRangeMessage();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setModelIndexErrorPolicy",
                        new Class[] { String.class });

                String value6 = oldCplModel.getModelIndexErrorPolicy();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setModelIndexErrorMessage",
                        new Class[] { String.class });

                String value7 = oldCplModel.getModelIndexErrorMessage();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setModelChartIsVisible",
                        new Class[] { Boolean.TYPE });

                Boolean value8 = new Boolean(oldCplModel.getModelChartIsVisible());

                method8.invoke(newModel, value8);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputModelOutputItem(BaseModel<?> oldModel) {
        ModelOutputItemClp oldCplModel = (ModelOutputItemClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.models.model.impl.ModelOutputItemImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setModelOutputItemModifierPK",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getModelOutputItemModifierPK());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getModelId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setModelOutputItemId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getModelOutputItemId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setModelOutputItemOrder",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getModelOutputItemOrder());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setModelItemRangePolicy",
                        new Class[] { String.class });

                String value4 = oldCplModel.getModelItemRangePolicy();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setModelItemRangeMessage",
                        new Class[] { String.class });

                String value5 = oldCplModel.getModelItemRangeMessage();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setModelItemErrorPolicy",
                        new Class[] { String.class });

                String value6 = oldCplModel.getModelItemErrorPolicy();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setModelItemErrorMessage",
                        new Class[] { String.class });

                String value7 = oldCplModel.getModelItemErrorMessage();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setModelItemLabelFormat",
                        new Class[] { String.class });

                String value8 = oldCplModel.getModelItemLabelFormat();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setModelItemIsVisible",
                        new Class[] { Boolean.TYPE });

                Boolean value9 = new Boolean(oldCplModel.getModelItemIsVisible());

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setItemType",
                        new Class[] { String.class });

                String value10 = oldCplModel.getItemType();

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setRelatedOutputItem",
                        new Class[] { Long.TYPE });

                Long value11 = new Long(oldCplModel.getRelatedOutputItem());

                method11.invoke(newModel, value11);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputModelPosition(BaseModel<?> oldModel) {
        ModelPositionClp oldCplModel = (ModelPositionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.models.model.impl.ModelPositionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPositionId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPositionId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getModelId());

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
                    "com.ext.portlet.models.model.impl.ModelCategoryImpl")) {
            return translateOutputModelCategory(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.models.model.impl.ModelDiscussionImpl")) {
            return translateOutputModelDiscussion(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.models.model.impl.ModelGlobalPreferenceImpl")) {
            return translateOutputModelGlobalPreference(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.models.model.impl.ModelInputGroupImpl")) {
            return translateOutputModelInputGroup(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.models.model.impl.ModelInputItemImpl")) {
            return translateOutputModelInputItem(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.models.model.impl.ModelOutputChartOrderImpl")) {
            return translateOutputModelOutputChartOrder(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.models.model.impl.ModelOutputItemImpl")) {
            return translateOutputModelOutputItem(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.models.model.impl.ModelPositionImpl")) {
            return translateOutputModelPosition(oldModel);
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

    public static Object translateOutputModelCategory(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ModelCategoryClp newModel = new ModelCategoryClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getModelCategoryPK");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setModelCategoryPK(value0);

                Method method1 = oldModelClass.getMethod("getModelCategoryName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelCategoryName(value1);

                Method method2 = oldModelClass.getMethod(
                        "getModelCategoryDescription");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelCategoryDescription(value2);

                Method method3 = oldModelClass.getMethod(
                        "getModelCategoryDisplayWeight");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelCategoryDisplayWeight(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputModelDiscussion(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ModelDiscussionClp newModel = new ModelDiscussionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getModelDiscussionId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setModelDiscussionId(value0);

                Method method1 = oldModelClass.getMethod("getModelId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value1);

                Method method2 = oldModelClass.getMethod("getCategoryId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setCategoryId(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputModelGlobalPreference(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ModelGlobalPreferenceClp newModel = new ModelGlobalPreferenceClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getModelGlobalPreferencePK");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setModelGlobalPreferencePK(value0);

                Method method1 = oldModelClass.getMethod("getModelId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value1);

                Method method2 = oldModelClass.getMethod("getVisible");

                Boolean value2 = (Boolean) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setVisible(value2);

                Method method3 = oldModelClass.getMethod("getWeight");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setWeight(value3);

                Method method4 = oldModelClass.getMethod(
                        "getExpertEvaluationPageId");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setExpertEvaluationPageId(value4);

                Method method5 = oldModelClass.getMethod("getModelCategoryId");

                Long value5 = (Long) method5.invoke(oldModel, (Object[]) null);

                newModel.setModelCategoryId(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputModelInputGroup(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ModelInputGroupClp newModel = new ModelInputGroupClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getModelInputGroupPK");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setModelInputGroupPK(value0);

                Method method1 = oldModelClass.getMethod("getModelId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value1);

                Method method2 = oldModelClass.getMethod(
                        "getNameAndDescriptionMetaDataId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setNameAndDescriptionMetaDataId(value2);

                Method method3 = oldModelClass.getMethod("getName");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value3);

                Method method4 = oldModelClass.getMethod("getDescription");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value4);

                Method method5 = oldModelClass.getMethod("getDisplayItemOrder");

                Integer value5 = (Integer) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setDisplayItemOrder(value5);

                Method method6 = oldModelClass.getMethod("getGroupType");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setGroupType(value6);

                Method method7 = oldModelClass.getMethod("getParentGroupPK");

                Long value7 = (Long) method7.invoke(oldModel, (Object[]) null);

                newModel.setParentGroupPK(value7);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputModelInputItem(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ModelInputItemClp newModel = new ModelInputItemClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getModelInputItemPK");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setModelInputItemPK(value0);

                Method method1 = oldModelClass.getMethod("getModelId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value1);

                Method method2 = oldModelClass.getMethod("getModelInputItemID");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setModelInputItemID(value2);

                Method method3 = oldModelClass.getMethod("getModelGroupId");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setModelGroupId(value3);

                Method method4 = oldModelClass.getMethod("getDisplayItemOrder");

                Integer value4 = (Integer) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setDisplayItemOrder(value4);

                Method method5 = oldModelClass.getMethod("getType");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setType(value5);

                Method method6 = oldModelClass.getMethod("getProperties");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setProperties(value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputModelOutputChartOrder(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ModelOutputChartOrderClp newModel = new ModelOutputChartOrderClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getModelOutputChartOrderPK");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setModelOutputChartOrderPK(value0);

                Method method1 = oldModelClass.getMethod("getModelId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value1);

                Method method2 = oldModelClass.getMethod("getModelOutputLabel");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelOutputLabel(value2);

                Method method3 = oldModelClass.getMethod(
                        "getModelOutputChartOrder");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelOutputChartOrder(value3);

                Method method4 = oldModelClass.getMethod(
                        "getModelIndexRangePolicy");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelIndexRangePolicy(value4);

                Method method5 = oldModelClass.getMethod(
                        "getModelIndexRangeMessage");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelIndexRangeMessage(value5);

                Method method6 = oldModelClass.getMethod(
                        "getModelIndexErrorPolicy");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelIndexErrorPolicy(value6);

                Method method7 = oldModelClass.getMethod(
                        "getModelIndexErrorMessage");

                String value7 = (String) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelIndexErrorMessage(value7);

                Method method8 = oldModelClass.getMethod(
                        "getModelChartIsVisible");

                Boolean value8 = (Boolean) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelChartIsVisible(value8);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputModelOutputItem(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ModelOutputItemClp newModel = new ModelOutputItemClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getModelOutputItemModifierPK");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setModelOutputItemModifierPK(value0);

                Method method1 = oldModelClass.getMethod("getModelId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value1);

                Method method2 = oldModelClass.getMethod("getModelOutputItemId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setModelOutputItemId(value2);

                Method method3 = oldModelClass.getMethod(
                        "getModelOutputItemOrder");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelOutputItemOrder(value3);

                Method method4 = oldModelClass.getMethod(
                        "getModelItemRangePolicy");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelItemRangePolicy(value4);

                Method method5 = oldModelClass.getMethod(
                        "getModelItemRangeMessage");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelItemRangeMessage(value5);

                Method method6 = oldModelClass.getMethod(
                        "getModelItemErrorPolicy");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelItemErrorPolicy(value6);

                Method method7 = oldModelClass.getMethod(
                        "getModelItemErrorMessage");

                String value7 = (String) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelItemErrorMessage(value7);

                Method method8 = oldModelClass.getMethod(
                        "getModelItemLabelFormat");

                String value8 = (String) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelItemLabelFormat(value8);

                Method method9 = oldModelClass.getMethod(
                        "getModelItemIsVisible");

                Boolean value9 = (Boolean) method9.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelItemIsVisible(value9);

                Method method10 = oldModelClass.getMethod("getItemType");

                String value10 = (String) method10.invoke(oldModel,
                        (Object[]) null);

                newModel.setItemType(value10);

                Method method11 = oldModelClass.getMethod(
                        "getRelatedOutputItem");

                Long value11 = (Long) method11.invoke(oldModel, (Object[]) null);

                newModel.setRelatedOutputItem(value11);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputModelPosition(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ModelPositionClp newModel = new ModelPositionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getPositionId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPositionId(value1);

                Method method2 = oldModelClass.getMethod("getModelId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value2);

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
