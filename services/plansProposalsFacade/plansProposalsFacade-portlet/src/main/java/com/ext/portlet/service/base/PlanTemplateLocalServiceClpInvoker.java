package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanTemplateLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanTemplateLocalServiceClpInvoker {
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
    private String _methodName540;
    private String[] _methodParameterTypes540;
    private String _methodName541;
    private String[] _methodParameterTypes541;
    private String _methodName546;
    private String[] _methodParameterTypes546;
    private String _methodName547;
    private String[] _methodParameterTypes547;
    private String _methodName548;
    private String[] _methodParameterTypes548;
    private String _methodName549;
    private String[] _methodParameterTypes549;
    private String _methodName550;
    private String[] _methodParameterTypes550;

    public PlanTemplateLocalServiceClpInvoker() {
        _methodName0 = "addPlanTemplate";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.PlanTemplate"
            };

        _methodName1 = "createPlanTemplate";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deletePlanTemplate";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deletePlanTemplate";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.PlanTemplate"
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

        _methodName10 = "fetchPlanTemplate";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getPlanTemplate";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getPlanTemplates";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getPlanTemplatesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updatePlanTemplate";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.PlanTemplate"
            };

        _methodName540 = "getBeanIdentifier";

        _methodParameterTypes540 = new String[] {  };

        _methodName541 = "setBeanIdentifier";

        _methodParameterTypes541 = new String[] { "java.lang.String" };

        _methodName546 = "store";

        _methodParameterTypes546 = new String[] {
                "com.ext.portlet.model.PlanTemplate"
            };

        _methodName547 = "getSections";

        _methodParameterTypes547 = new String[] {
                "com.ext.portlet.model.PlanTemplate"
            };

        _methodName548 = "addSection";

        _methodParameterTypes548 = new String[] {
                "com.ext.portlet.model.PlanTemplate",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName549 = "removeSection";

        _methodParameterTypes549 = new String[] {
                "com.ext.portlet.model.PlanTemplate",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName550 = "updateSectionWeight";

        _methodParameterTypes550 = new String[] {
                "com.ext.portlet.model.PlanTemplate",
                "com.ext.portlet.model.PlanSectionDefinition", "int"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.addPlanTemplate((com.ext.portlet.model.PlanTemplate) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.createPlanTemplate(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.deletePlanTemplate(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.deletePlanTemplate((com.ext.portlet.model.PlanTemplate) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.fetchPlanTemplate(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.getPlanTemplate(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.getPlanTemplates(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.getPlanTemplatesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.updatePlanTemplate((com.ext.portlet.model.PlanTemplate) arguments[0]);
        }

        if (_methodName540.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes540, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName541.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes541, parameterTypes)) {
            PlanTemplateLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName546.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes546, parameterTypes)) {
            PlanTemplateLocalServiceUtil.store((com.ext.portlet.model.PlanTemplate) arguments[0]);

            return null;
        }

        if (_methodName547.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes547, parameterTypes)) {
            return PlanTemplateLocalServiceUtil.getSections((com.ext.portlet.model.PlanTemplate) arguments[0]);
        }

        if (_methodName548.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes548, parameterTypes)) {
            PlanTemplateLocalServiceUtil.addSection((com.ext.portlet.model.PlanTemplate) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);

            return null;
        }

        if (_methodName549.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes549, parameterTypes)) {
            PlanTemplateLocalServiceUtil.removeSection((com.ext.portlet.model.PlanTemplate) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);

            return null;
        }

        if (_methodName550.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes550, parameterTypes)) {
            PlanTemplateLocalServiceUtil.updateSectionWeight((com.ext.portlet.model.PlanTemplate) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1],
                ((Integer) arguments[2]).intValue());

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
