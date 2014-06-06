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
    private String _methodName514;
    private String[] _methodParameterTypes514;
    private String _methodName515;
    private String[] _methodParameterTypes515;
    private String _methodName520;
    private String[] _methodParameterTypes520;
    private String _methodName521;
    private String[] _methodParameterTypes521;
    private String _methodName522;
    private String[] _methodParameterTypes522;
    private String _methodName523;
    private String[] _methodParameterTypes523;
    private String _methodName524;
    private String[] _methodParameterTypes524;
    private String _methodName525;
    private String[] _methodParameterTypes525;
    private String _methodName526;
    private String[] _methodParameterTypes526;
    private String _methodName527;
    private String[] _methodParameterTypes527;
    private String _methodName528;
    private String[] _methodParameterTypes528;
    private String _methodName529;
    private String[] _methodParameterTypes529;
    private String _methodName530;
    private String[] _methodParameterTypes530;
    private String _methodName531;
    private String[] _methodParameterTypes531;

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

        _methodName514 = "getBeanIdentifier";

        _methodParameterTypes514 = new String[] {  };

        _methodName515 = "setBeanIdentifier";

        _methodParameterTypes515 = new String[] { "java.lang.String" };

        _methodName520 = "getCurrentForPlanSectionDef";

        _methodParameterTypes520 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName521 = "getCurrentForPlanSectionDef";

        _methodParameterTypes521 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition", "boolean"
            };

        _methodName522 = "getForPlanSectionDef";

        _methodParameterTypes522 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName523 = "getForPlanSectionDef";

        _methodParameterTypes523 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition", "boolean",
                "boolean"
            };

        _methodName524 = "createForPlanFrom";

        _methodParameterTypes524 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSection", "boolean"
            };

        _methodName525 = "createNewVersionForPlanSectionDefinition";

        _methodParameterTypes525 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName526 = "createNewVersionForPlanSectionDefinition";

        _methodParameterTypes526 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition", "boolean"
            };

        _methodName527 = "getAllForPlanDefinition";

        _methodParameterTypes527 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName528 = "store";

        _methodParameterTypes528 = new String[] {
                "com.ext.portlet.model.PlanSection"
            };

        _methodName529 = "getDefinition";

        _methodParameterTypes529 = new String[] {
                "com.ext.portlet.model.PlanSection"
            };

        _methodName530 = "addPlanReference";

        _methodParameterTypes530 = new String[] {
                "com.ext.portlet.model.PlanSection", "java.lang.Long"
            };

        _methodName531 = "getReferencedPlans";

        _methodParameterTypes531 = new String[] {
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

        if (_methodName514.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes514, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName515.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes515, parameterTypes)) {
            PlanSectionLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName520.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes520, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getCurrentForPlanSectionDef((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);
        }

        if (_methodName521.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes521, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getCurrentForPlanSectionDef((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName522.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes522, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getForPlanSectionDef((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);
        }

        if (_methodName523.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes523, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getForPlanSectionDef((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1],
                ((Boolean) arguments[2]).booleanValue(),
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName524.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes524, parameterTypes)) {
            return PlanSectionLocalServiceUtil.createForPlanFrom((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSection) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName525.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes525, parameterTypes)) {
            return PlanSectionLocalServiceUtil.createNewVersionForPlanSectionDefinition((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);
        }

        if (_methodName526.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes526, parameterTypes)) {
            return PlanSectionLocalServiceUtil.createNewVersionForPlanSectionDefinition((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName527.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes527, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getAllForPlanDefinition((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);
        }

        if (_methodName528.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes528, parameterTypes)) {
            PlanSectionLocalServiceUtil.store((com.ext.portlet.model.PlanSection) arguments[0]);

            return null;
        }

        if (_methodName529.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes529, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getDefinition((com.ext.portlet.model.PlanSection) arguments[0]);
        }

        if (_methodName530.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes530, parameterTypes)) {
            PlanSectionLocalServiceUtil.addPlanReference((com.ext.portlet.model.PlanSection) arguments[0],
                (java.lang.Long) arguments[1]);

            return null;
        }

        if (_methodName531.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes531, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getReferencedPlans((com.ext.portlet.model.PlanSection) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
