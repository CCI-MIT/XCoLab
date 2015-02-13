package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanSectionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanSectionLocalServiceClpInvoker {
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
    private String _methodName588;
    private String[] _methodParameterTypes588;
    private String _methodName589;
    private String[] _methodParameterTypes589;
    private String _methodName594;
    private String[] _methodParameterTypes594;
    private String _methodName595;
    private String[] _methodParameterTypes595;
    private String _methodName596;
    private String[] _methodParameterTypes596;
    private String _methodName597;
    private String[] _methodParameterTypes597;
    private String _methodName598;
    private String[] _methodParameterTypes598;
    private String _methodName599;
    private String[] _methodParameterTypes599;
    private String _methodName600;
    private String[] _methodParameterTypes600;
    private String _methodName601;
    private String[] _methodParameterTypes601;
    private String _methodName602;
    private String[] _methodParameterTypes602;
    private String _methodName603;
    private String[] _methodParameterTypes603;
    private String _methodName604;
    private String[] _methodParameterTypes604;
    private String _methodName605;
    private String[] _methodParameterTypes605;

    public PlanSectionLocalServiceClpInvoker() {
        _methodName0 = "addPlanSection";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.PlanSection"
            };

        _methodName1 = "createPlanSection";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deletePlanSection";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deletePlanSection";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.PlanSection"
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

        _methodName10 = "fetchPlanSection";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getPlanSection";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getPlanSections";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getPlanSectionsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updatePlanSection";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.PlanSection"
            };

        _methodName588 = "getBeanIdentifier";

        _methodParameterTypes588 = new String[] {  };

        _methodName589 = "setBeanIdentifier";

        _methodParameterTypes589 = new String[] { "java.lang.String" };

        _methodName594 = "getCurrentForPlanSectionDef";

        _methodParameterTypes594 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName595 = "getCurrentForPlanSectionDef";

        _methodParameterTypes595 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition", "boolean"
            };

        _methodName596 = "getForPlanSectionDef";

        _methodParameterTypes596 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName597 = "getForPlanSectionDef";

        _methodParameterTypes597 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition", "boolean",
                "boolean"
            };

        _methodName598 = "createForPlanFrom";

        _methodParameterTypes598 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSection", "boolean"
            };

        _methodName599 = "createNewVersionForPlanSectionDefinition";

        _methodParameterTypes599 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName600 = "createNewVersionForPlanSectionDefinition";

        _methodParameterTypes600 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition", "boolean"
            };

        _methodName601 = "getAllForPlanDefinition";

        _methodParameterTypes601 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName602 = "store";

        _methodParameterTypes602 = new String[] {
                "com.ext.portlet.model.PlanSection"
            };

        _methodName603 = "getDefinition";

        _methodParameterTypes603 = new String[] {
                "com.ext.portlet.model.PlanSection"
            };

        _methodName604 = "addPlanReference";

        _methodParameterTypes604 = new String[] {
                "com.ext.portlet.model.PlanSection", "java.lang.Long"
            };

        _methodName605 = "getReferencedPlans";

        _methodParameterTypes605 = new String[] {
                "com.ext.portlet.model.PlanSection"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return PlanSectionLocalServiceUtil.addPlanSection((com.ext.portlet.model.PlanSection) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return PlanSectionLocalServiceUtil.createPlanSection(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return PlanSectionLocalServiceUtil.deletePlanSection(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return PlanSectionLocalServiceUtil.deletePlanSection((com.ext.portlet.model.PlanSection) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return PlanSectionLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return PlanSectionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return PlanSectionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return PlanSectionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return PlanSectionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return PlanSectionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return PlanSectionLocalServiceUtil.fetchPlanSection(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getPlanSection(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getPlanSections(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getPlanSectionsCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return PlanSectionLocalServiceUtil.updatePlanSection((com.ext.portlet.model.PlanSection) arguments[0]);
        }

        if (_methodName588.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes588, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName589.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes589, parameterTypes)) {
            PlanSectionLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName594.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes594, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getCurrentForPlanSectionDef((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);
        }

        if (_methodName595.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes595, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getCurrentForPlanSectionDef((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName596.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes596, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getForPlanSectionDef((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);
        }

        if (_methodName597.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes597, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getForPlanSectionDef((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1],
                ((Boolean) arguments[2]).booleanValue(),
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName598.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes598, parameterTypes)) {
            return PlanSectionLocalServiceUtil.createForPlanFrom((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSection) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName599.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes599, parameterTypes)) {
            return PlanSectionLocalServiceUtil.createNewVersionForPlanSectionDefinition((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);
        }

        if (_methodName600.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes600, parameterTypes)) {
            return PlanSectionLocalServiceUtil.createNewVersionForPlanSectionDefinition((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName601.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes601, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getAllForPlanDefinition((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);
        }

        if (_methodName602.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes602, parameterTypes)) {
            PlanSectionLocalServiceUtil.store((com.ext.portlet.model.PlanSection) arguments[0]);

            return null;
        }

        if (_methodName603.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes603, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getDefinition((com.ext.portlet.model.PlanSection) arguments[0]);
        }

        if (_methodName604.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes604, parameterTypes)) {
            PlanSectionLocalServiceUtil.addPlanReference((com.ext.portlet.model.PlanSection) arguments[0],
                (java.lang.Long) arguments[1]);

            return null;
        }

        if (_methodName605.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes605, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getReferencedPlans((com.ext.portlet.model.PlanSection) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
