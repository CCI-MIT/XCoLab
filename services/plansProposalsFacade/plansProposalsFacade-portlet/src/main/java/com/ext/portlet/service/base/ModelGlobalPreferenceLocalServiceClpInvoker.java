package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelGlobalPreferenceLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelGlobalPreferenceLocalServiceClpInvoker {
    private String _methodName0;
    private String[] _methodParameterTypes0;
    private String _methodName1;
    private String[] _methodParameterTypes1;
    private String _methodName2;
    private String[] _methodParameterTypes2;
    private String _methodName3;
    private String[] _methodParameterTypes3;
    private String _methodName4;
    private String[] _methodParameterTypes4;
    private String _methodName5;
    private String[] _methodParameterTypes5;
    private String _methodName6;
    private String[] _methodParameterTypes6;
    private String _methodName7;
    private String[] _methodParameterTypes7;
    private String _methodName8;
    private String[] _methodParameterTypes8;
    private String _methodName9;
    private String[] _methodParameterTypes9;
    private String _methodName10;
    private String[] _methodParameterTypes10;
    private String _methodName11;
    private String[] _methodParameterTypes11;
    private String _methodName12;
    private String[] _methodParameterTypes12;
    private String _methodName13;
    private String[] _methodParameterTypes13;
    private String _methodName14;
    private String[] _methodParameterTypes14;
    private String _methodName15;
    private String[] _methodParameterTypes15;
    private String _methodName350;
    private String[] _methodParameterTypes350;
    private String _methodName351;
    private String[] _methodParameterTypes351;
    private String _methodName356;
    private String[] _methodParameterTypes356;
    private String _methodName357;
    private String[] _methodParameterTypes357;
    private String _methodName358;
    private String[] _methodParameterTypes358;
    private String _methodName359;
    private String[] _methodParameterTypes359;
    private String _methodName360;
    private String[] _methodParameterTypes360;
    private String _methodName361;
    private String[] _methodParameterTypes361;
    private String _methodName362;
    private String[] _methodParameterTypes362;
    private String _methodName363;
    private String[] _methodParameterTypes363;
    private String _methodName364;
    private String[] _methodParameterTypes364;
    private String _methodName366;
    private String[] _methodParameterTypes366;

    public ModelGlobalPreferenceLocalServiceClpInvoker() {
        _methodName0 = "addModelGlobalPreference";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.ModelGlobalPreference"
            };

        _methodName1 = "createModelGlobalPreference";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteModelGlobalPreference";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteModelGlobalPreference";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.ModelGlobalPreference"
            };

        _methodName4 = "dynamicQuery";

        _methodParameterTypes4 = new String[] {  };

        _methodName5 = "dynamicQuery";

        _methodParameterTypes5 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery"
            };

        _methodName6 = "dynamicQuery";

        _methodParameterTypes6 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
            };

        _methodName7 = "dynamicQuery";

        _methodParameterTypes7 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
                "com.liferay.portal.kernel.util.OrderByComparator"
            };

        _methodName8 = "dynamicQueryCount";

        _methodParameterTypes8 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery"
            };

        _methodName9 = "dynamicQueryCount";

        _methodParameterTypes9 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery",
                "com.liferay.portal.kernel.dao.orm.Projection"
            };

        _methodName10 = "fetchModelGlobalPreference";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getModelGlobalPreference";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getModelGlobalPreferences";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getModelGlobalPreferencesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateModelGlobalPreference";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.ModelGlobalPreference"
            };

        _methodName350 = "getBeanIdentifier";

        _methodParameterTypes350 = new String[] {  };

        _methodName351 = "setBeanIdentifier";

        _methodParameterTypes351 = new String[] { "java.lang.String" };

        _methodName356 = "isVisible";

        _methodParameterTypes356 = new String[] {
                "edu.mit.cci.roma.client.Simulation"
            };

        _methodName357 = "getByModelId";

        _methodParameterTypes357 = new String[] { "long" };

        _methodName358 = "setVisible";

        _methodParameterTypes358 = new String[] {
                "edu.mit.cci.roma.client.Simulation", "boolean"
            };

        _methodName359 = "getWeight";

        _methodParameterTypes359 = new String[] {
                "edu.mit.cci.roma.client.Simulation"
            };

        _methodName360 = "setWeight";

        _methodParameterTypes360 = new String[] {
                "edu.mit.cci.roma.client.Simulation", "int"
            };

        _methodName361 = "getExpertEvaluationPageId";

        _methodParameterTypes361 = new String[] {
                "edu.mit.cci.roma.client.Simulation"
            };

        _methodName362 = "setExpertEvaluationPageId";

        _methodParameterTypes362 = new String[] {
                "edu.mit.cci.roma.client.Simulation", "java.lang.Long"
            };

        _methodName363 = "findByCategory";

        _methodParameterTypes363 = new String[] {
                "com.ext.portlet.model.ModelCategory"
            };

        _methodName364 = "getCategory";

        _methodParameterTypes364 = new String[] {
                "edu.mit.cci.roma.client.Simulation"
            };

        _methodName366 = "updateModelCategory";

        _methodParameterTypes366 = new String[] {
                "com.ext.portlet.model.ModelCategory",
                "edu.mit.cci.roma.client.Simulation"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.addModelGlobalPreference((com.ext.portlet.model.ModelGlobalPreference) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.createModelGlobalPreference(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.deleteModelGlobalPreference(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.deleteModelGlobalPreference((com.ext.portlet.model.ModelGlobalPreference) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.fetchModelGlobalPreference(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.getModelGlobalPreference(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.getModelGlobalPreferences(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.getModelGlobalPreferencesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.updateModelGlobalPreference((com.ext.portlet.model.ModelGlobalPreference) arguments[0]);
        }

        if (_methodName350.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes350, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName351.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes351, parameterTypes)) {
            ModelGlobalPreferenceLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName356.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes356, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.isVisible((edu.mit.cci.roma.client.Simulation) arguments[0]);
        }

        if (_methodName357.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes357, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.getByModelId(((Long) arguments[0]).longValue());
        }

        if (_methodName358.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes358, parameterTypes)) {
            ModelGlobalPreferenceLocalServiceUtil.setVisible((edu.mit.cci.roma.client.Simulation) arguments[0],
                ((Boolean) arguments[1]).booleanValue());

            return null;
        }

        if (_methodName359.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes359, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.getWeight((edu.mit.cci.roma.client.Simulation) arguments[0]);
        }

        if (_methodName360.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes360, parameterTypes)) {
            ModelGlobalPreferenceLocalServiceUtil.setWeight((edu.mit.cci.roma.client.Simulation) arguments[0],
                ((Integer) arguments[1]).intValue());

            return null;
        }

        if (_methodName361.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes361, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.getExpertEvaluationPageId((edu.mit.cci.roma.client.Simulation) arguments[0]);
        }

        if (_methodName362.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes362, parameterTypes)) {
            ModelGlobalPreferenceLocalServiceUtil.setExpertEvaluationPageId((edu.mit.cci.roma.client.Simulation) arguments[0],
                (java.lang.Long) arguments[1]);

            return null;
        }

        if (_methodName363.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes363, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.findByCategory((com.ext.portlet.model.ModelCategory) arguments[0]);
        }

        if (_methodName364.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes364, parameterTypes)) {
            return ModelGlobalPreferenceLocalServiceUtil.getCategory((edu.mit.cci.roma.client.Simulation) arguments[0]);
        }

        if (_methodName366.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes366, parameterTypes)) {
            ModelGlobalPreferenceLocalServiceUtil.updateModelCategory((com.ext.portlet.model.ModelCategory) arguments[0],
                (edu.mit.cci.roma.client.Simulation) arguments[1]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
