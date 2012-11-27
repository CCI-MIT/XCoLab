package com.ext.portlet.ontology.service;

import com.ext.portlet.ontology.model.FocusAreaClp;
import com.ext.portlet.ontology.model.FocusAreaOntologyTermClp;
import com.ext.portlet.ontology.model.OntologySpaceClp;
import com.ext.portlet.ontology.model.OntologyTermClp;
import com.ext.portlet.ontology.model.OntologyTermEntityClp;

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

        if (oldModelClassName.equals(FocusAreaClp.class.getName())) {
            return translateInputFocusArea(oldModel);
        }

        if (oldModelClassName.equals(FocusAreaOntologyTermClp.class.getName())) {
            return translateInputFocusAreaOntologyTerm(oldModel);
        }

        if (oldModelClassName.equals(OntologySpaceClp.class.getName())) {
            return translateInputOntologySpace(oldModel);
        }

        if (oldModelClassName.equals(OntologyTermClp.class.getName())) {
            return translateInputOntologyTerm(oldModel);
        }

        if (oldModelClassName.equals(OntologyTermEntityClp.class.getName())) {
            return translateInputOntologyTermEntity(oldModel);
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

    public static Object translateInputFocusArea(BaseModel<?> oldModel) {
        FocusAreaClp oldCplModel = (FocusAreaClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.ontology.model.impl.FocusAreaImpl",
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

    public static Object translateInputFocusAreaOntologyTerm(
        BaseModel<?> oldModel) {
        FocusAreaOntologyTermClp oldCplModel = (FocusAreaOntologyTermClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.ontology.model.impl.FocusAreaOntologyTermImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setFocusAreaId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getFocusAreaId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setOntologyTermId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getOntologyTermId());

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

    public static Object translateInputOntologySpace(BaseModel<?> oldModel) {
        OntologySpaceClp oldCplModel = (OntologySpaceClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.ontology.model.impl.OntologySpaceImpl",
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

                Method method2 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value2 = oldCplModel.getDescription();

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

    public static Object translateInputOntologyTerm(BaseModel<?> oldModel) {
        OntologyTermClp oldCplModel = (OntologyTermClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.ontology.model.impl.OntologyTermImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setParentId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getParentId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setOntologySpaceId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getOntologySpaceId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value3 = oldCplModel.getName();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setDescriptionUrl",
                        new Class[] { String.class });

                String value4 = oldCplModel.getDescriptionUrl();

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

    public static Object translateInputOntologyTermEntity(BaseModel<?> oldModel) {
        OntologyTermEntityClp oldCplModel = (OntologyTermEntityClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.ontology.model.impl.OntologyTermEntityImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setTermId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getTermId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setClassNameId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getClassNameId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setClassPK",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getClassPK());

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
                    "com.ext.portlet.ontology.model.impl.FocusAreaImpl")) {
            return translateOutputFocusArea(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.ontology.model.impl.FocusAreaOntologyTermImpl")) {
            return translateOutputFocusAreaOntologyTerm(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.ontology.model.impl.OntologySpaceImpl")) {
            return translateOutputOntologySpace(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.ontology.model.impl.OntologyTermImpl")) {
            return translateOutputOntologyTerm(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.ontology.model.impl.OntologyTermEntityImpl")) {
            return translateOutputOntologyTermEntity(oldModel);
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

    public static Object translateOutputFocusArea(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                FocusAreaClp newModel = new FocusAreaClp();

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

    public static Object translateOutputFocusAreaOntologyTerm(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                FocusAreaOntologyTermClp newModel = new FocusAreaOntologyTermClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getFocusAreaId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setFocusAreaId(value0);

                Method method1 = oldModelClass.getMethod("getOntologyTermId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setOntologyTermId(value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputOntologySpace(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                OntologySpaceClp newModel = new OntologySpaceClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value1);

                Method method2 = oldModelClass.getMethod("getDescription");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputOntologyTerm(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                OntologyTermClp newModel = new OntologyTermClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getParentId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setParentId(value1);

                Method method2 = oldModelClass.getMethod("getOntologySpaceId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setOntologySpaceId(value2);

                Method method3 = oldModelClass.getMethod("getName");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value3);

                Method method4 = oldModelClass.getMethod("getDescriptionUrl");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescriptionUrl(value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputOntologyTermEntity(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                OntologyTermEntityClp newModel = new OntologyTermEntityClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getTermId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setTermId(value1);

                Method method2 = oldModelClass.getMethod("getClassNameId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setClassNameId(value2);

                Method method3 = oldModelClass.getMethod("getClassPK");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setClassPK(value3);

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
