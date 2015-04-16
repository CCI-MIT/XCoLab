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
    private String _methodName620;
    private String[] _methodParameterTypes620;
    private String _methodName621;
    private String[] _methodParameterTypes621;
    private String _methodName626;
    private String[] _methodParameterTypes626;
    private String _methodName627;
    private String[] _methodParameterTypes627;
    private String _methodName628;
    private String[] _methodParameterTypes628;
    private String _methodName629;
    private String[] _methodParameterTypes629;
    private String _methodName630;
    private String[] _methodParameterTypes630;
    private String _methodName631;
    private String[] _methodParameterTypes631;
    private String _methodName632;
    private String[] _methodParameterTypes632;
    private String _methodName633;
    private String[] _methodParameterTypes633;
    private String _methodName634;
    private String[] _methodParameterTypes634;
    private String _methodName635;
    private String[] _methodParameterTypes635;
    private String _methodName636;
    private String[] _methodParameterTypes636;
    private String _methodName637;
    private String[] _methodParameterTypes637;

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

        _methodName620 = "getBeanIdentifier";

        _methodParameterTypes620 = new String[] {  };

        _methodName621 = "setBeanIdentifier";

        _methodParameterTypes621 = new String[] { "java.lang.String" };

        _methodName626 = "getCurrentForPlanSectionDef";

        _methodParameterTypes626 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName627 = "getCurrentForPlanSectionDef";

        _methodParameterTypes627 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition", "boolean"
            };

        _methodName628 = "getForPlanSectionDef";

        _methodParameterTypes628 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName629 = "getForPlanSectionDef";

        _methodParameterTypes629 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition", "boolean",
                "boolean"
            };

        _methodName630 = "createForPlanFrom";

        _methodParameterTypes630 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSection", "boolean"
            };

        _methodName631 = "createNewVersionForPlanSectionDefinition";

        _methodParameterTypes631 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName632 = "createNewVersionForPlanSectionDefinition";

        _methodParameterTypes632 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition", "boolean"
            };

        _methodName633 = "getAllForPlanDefinition";

        _methodParameterTypes633 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName634 = "store";

        _methodParameterTypes634 = new String[] {
                "com.ext.portlet.model.PlanSection"
            };

        _methodName635 = "getDefinition";

        _methodParameterTypes635 = new String[] {
                "com.ext.portlet.model.PlanSection"
            };

        _methodName636 = "addPlanReference";

        _methodParameterTypes636 = new String[] {
                "com.ext.portlet.model.PlanSection", "java.lang.Long"
            };

        _methodName637 = "getReferencedPlans";

        _methodParameterTypes637 = new String[] {
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

        if (_methodName620.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes620, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName621.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes621, parameterTypes)) {
            PlanSectionLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName626.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes626, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getCurrentForPlanSectionDef((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);
        }

        if (_methodName627.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes627, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getCurrentForPlanSectionDef((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName628.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes628, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getForPlanSectionDef((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);
        }

        if (_methodName629.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes629, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getForPlanSectionDef((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1],
                ((Boolean) arguments[2]).booleanValue(),
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName630.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes630, parameterTypes)) {
            return PlanSectionLocalServiceUtil.createForPlanFrom((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSection) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName631.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes631, parameterTypes)) {
            return PlanSectionLocalServiceUtil.createNewVersionForPlanSectionDefinition((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);
        }

        if (_methodName632.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes632, parameterTypes)) {
            return PlanSectionLocalServiceUtil.createNewVersionForPlanSectionDefinition((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName633.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes633, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getAllForPlanDefinition((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);
        }

        if (_methodName634.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes634, parameterTypes)) {
            PlanSectionLocalServiceUtil.store((com.ext.portlet.model.PlanSection) arguments[0]);

            return null;
        }

        if (_methodName635.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes635, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getDefinition((com.ext.portlet.model.PlanSection) arguments[0]);
        }

        if (_methodName636.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes636, parameterTypes)) {
            PlanSectionLocalServiceUtil.addPlanReference((com.ext.portlet.model.PlanSection) arguments[0],
                (java.lang.Long) arguments[1]);

            return null;
        }

        if (_methodName637.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes637, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getReferencedPlans((com.ext.portlet.model.PlanSection) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
