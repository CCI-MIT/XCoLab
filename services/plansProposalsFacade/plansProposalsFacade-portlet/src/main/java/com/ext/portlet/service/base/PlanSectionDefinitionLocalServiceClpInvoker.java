package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanSectionDefinitionLocalServiceClpInvoker {
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
    private String _methodName422;
    private String[] _methodParameterTypes422;
    private String _methodName423;
    private String[] _methodParameterTypes423;
    private String _methodName428;
    private String[] _methodParameterTypes428;
    private String _methodName429;
    private String[] _methodParameterTypes429;
    private String _methodName430;
    private String[] _methodParameterTypes430;
    private String _methodName431;
    private String[] _methodParameterTypes431;

    public PlanSectionDefinitionLocalServiceClpInvoker() {
        _methodName0 = "addPlanSectionDefinition";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName1 = "createPlanSectionDefinition";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deletePlanSectionDefinition";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deletePlanSectionDefinition";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.PlanSectionDefinition"
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

        _methodName10 = "fetchPlanSectionDefinition";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getPlanSectionDefinition";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getPlanSectionDefinitions";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getPlanSectionDefinitionsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updatePlanSectionDefinition";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName422 = "getBeanIdentifier";

        _methodParameterTypes422 = new String[] {  };

        _methodName423 = "setBeanIdentifier";

        _methodParameterTypes423 = new String[] { "java.lang.String" };

        _methodName428 = "store";

        _methodParameterTypes428 = new String[] {
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName429 = "getFocusArea";

        _methodParameterTypes429 = new String[] {
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName430 = "getPlanSectionDefinition";

        _methodParameterTypes430 = new String[] {
                "com.ext.portlet.model.FocusArea", "java.lang.String", "long"
            };

        _methodName431 = "getAdditionalIds";

        _methodParameterTypes431 = new String[] {
                "com.ext.portlet.model.PlanSectionDefinition"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.addPlanSectionDefinition((com.ext.portlet.model.PlanSectionDefinition) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.createPlanSectionDefinition(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.deletePlanSectionDefinition(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.deletePlanSectionDefinition((com.ext.portlet.model.PlanSectionDefinition) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.fetchPlanSectionDefinition(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinitions(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinitionsCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.updatePlanSectionDefinition((com.ext.portlet.model.PlanSectionDefinition) arguments[0]);
        }

        if (_methodName422.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes422, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName423.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes423, parameterTypes)) {
            PlanSectionDefinitionLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName428.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes428, parameterTypes)) {
            PlanSectionDefinitionLocalServiceUtil.store((com.ext.portlet.model.PlanSectionDefinition) arguments[0]);

            return null;
        }

        if (_methodName429.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes429, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.getFocusArea((com.ext.portlet.model.PlanSectionDefinition) arguments[0]);
        }

        if (_methodName430.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes430, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition((com.ext.portlet.model.FocusArea) arguments[0],
                (java.lang.String) arguments[1],
                ((Long) arguments[2]).longValue());
        }

        if (_methodName431.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes431, parameterTypes)) {
            return PlanSectionDefinitionLocalServiceUtil.getAdditionalIds((com.ext.portlet.model.PlanSectionDefinition) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
