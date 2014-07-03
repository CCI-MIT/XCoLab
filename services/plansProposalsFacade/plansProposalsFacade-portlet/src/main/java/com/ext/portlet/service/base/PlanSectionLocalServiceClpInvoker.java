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
    private String _methodName551;
    private String[] _methodParameterTypes551;
    private String _methodName552;
    private String[] _methodParameterTypes552;
    private String _methodName553;
    private String[] _methodParameterTypes553;
    private String _methodName554;
    private String[] _methodParameterTypes554;
    private String _methodName555;
    private String[] _methodParameterTypes555;
    private String _methodName556;
    private String[] _methodParameterTypes556;
    private String _methodName557;
    private String[] _methodParameterTypes557;

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

        _methodName540 = "getBeanIdentifier";

        _methodParameterTypes540 = new String[] {  };

        _methodName541 = "setBeanIdentifier";

        _methodParameterTypes541 = new String[] { "java.lang.String" };

        _methodName546 = "getCurrentForPlanSectionDef";

        _methodParameterTypes546 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName547 = "getCurrentForPlanSectionDef";

        _methodParameterTypes547 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition", "boolean"
            };

        _methodName548 = "getForPlanSectionDef";

        _methodParameterTypes548 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName549 = "getForPlanSectionDef";

        _methodParameterTypes549 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition", "boolean",
                "boolean"
            };

        _methodName550 = "createForPlanFrom";

        _methodParameterTypes550 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSection", "boolean"
            };

        _methodName551 = "createNewVersionForPlanSectionDefinition";

        _methodParameterTypes551 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName552 = "createNewVersionForPlanSectionDefinition";

        _methodParameterTypes552 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition", "boolean"
            };

        _methodName553 = "getAllForPlanDefinition";

        _methodParameterTypes553 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanSectionDefinition"
            };

        _methodName554 = "store";

        _methodParameterTypes554 = new String[] {
                "com.ext.portlet.model.PlanSection"
            };

        _methodName555 = "getDefinition";

        _methodParameterTypes555 = new String[] {
                "com.ext.portlet.model.PlanSection"
            };

        _methodName556 = "addPlanReference";

        _methodParameterTypes556 = new String[] {
                "com.ext.portlet.model.PlanSection", "java.lang.Long"
            };

        _methodName557 = "getReferencedPlans";

        _methodParameterTypes557 = new String[] {
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

        if (_methodName540.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes540, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName541.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes541, parameterTypes)) {
            PlanSectionLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName546.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes546, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getCurrentForPlanSectionDef((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);
        }

        if (_methodName547.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes547, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getCurrentForPlanSectionDef((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName548.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes548, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getForPlanSectionDef((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);
        }

        if (_methodName549.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes549, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getForPlanSectionDef((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1],
                ((Boolean) arguments[2]).booleanValue(),
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName550.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes550, parameterTypes)) {
            return PlanSectionLocalServiceUtil.createForPlanFrom((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSection) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName551.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes551, parameterTypes)) {
            return PlanSectionLocalServiceUtil.createNewVersionForPlanSectionDefinition((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);
        }

        if (_methodName552.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes552, parameterTypes)) {
            return PlanSectionLocalServiceUtil.createNewVersionForPlanSectionDefinition((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName553.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes553, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getAllForPlanDefinition((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanSectionDefinition) arguments[1]);
        }

        if (_methodName554.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes554, parameterTypes)) {
            PlanSectionLocalServiceUtil.store((com.ext.portlet.model.PlanSection) arguments[0]);

            return null;
        }

        if (_methodName555.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes555, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getDefinition((com.ext.portlet.model.PlanSection) arguments[0]);
        }

        if (_methodName556.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes556, parameterTypes)) {
            PlanSectionLocalServiceUtil.addPlanReference((com.ext.portlet.model.PlanSection) arguments[0],
                (java.lang.Long) arguments[1]);

            return null;
        }

        if (_methodName557.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes557, parameterTypes)) {
            return PlanSectionLocalServiceUtil.getReferencedPlans((com.ext.portlet.model.PlanSection) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
