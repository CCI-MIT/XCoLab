package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanModelRunLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanModelRunLocalServiceClpInvoker {
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
    private String _methodName624;
    private String[] _methodParameterTypes624;
    private String _methodName625;
    private String[] _methodParameterTypes625;
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
    private String _methodName638;
    private String[] _methodParameterTypes638;

    public PlanModelRunLocalServiceClpInvoker() {
        _methodName0 = "addPlanModelRun";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.PlanModelRun"
            };

        _methodName1 = "createPlanModelRun";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deletePlanModelRun";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deletePlanModelRun";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.PlanModelRun"
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

        _methodName10 = "fetchPlanModelRun";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getPlanModelRun";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getPlanModelRuns";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getPlanModelRunsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updatePlanModelRun";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.PlanModelRun"
            };

        _methodName624 = "getBeanIdentifier";

        _methodParameterTypes624 = new String[] {  };

        _methodName625 = "setBeanIdentifier";

        _methodParameterTypes625 = new String[] { "java.lang.String" };

        _methodName630 = "createPlanModelRun";

        _methodParameterTypes630 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName631 = "getCurrentForPlan";

        _methodParameterTypes631 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName632 = "getAllForPlan";

        _methodParameterTypes632 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName633 = "getForPlan";

        _methodParameterTypes633 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName634 = "createNewVersionForPlan";

        _methodParameterTypes634 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName635 = "createNewVersionForPlan";

        _methodParameterTypes635 = new String[] {
                "com.ext.portlet.model.PlanItem", "boolean"
            };

        _methodName636 = "createNewVersionForPlanFrom";

        _methodParameterTypes636 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanModelRun", "boolean"
            };

        _methodName637 = "store";

        _methodParameterTypes637 = new String[] {
                "com.ext.portlet.model.PlanModelRun"
            };

        _methodName638 = "getUpdateAuthor";

        _methodParameterTypes638 = new String[] {
                "com.ext.portlet.model.PlanModelRun"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.addPlanModelRun((com.ext.portlet.model.PlanModelRun) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.createPlanModelRun(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.deletePlanModelRun(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.deletePlanModelRun((com.ext.portlet.model.PlanModelRun) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.fetchPlanModelRun(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.getPlanModelRun(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.getPlanModelRuns(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.getPlanModelRunsCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.updatePlanModelRun((com.ext.portlet.model.PlanModelRun) arguments[0]);
        }

        if (_methodName624.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes624, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName625.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes625, parameterTypes)) {
            PlanModelRunLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName630.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes630, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.createPlanModelRun((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName631.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes631, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.getCurrentForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName632.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes632, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.getAllForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName633.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes633, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.getForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName634.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes634, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.createNewVersionForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName635.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes635, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.createNewVersionForPlan((com.ext.portlet.model.PlanItem) arguments[0],
                ((Boolean) arguments[1]).booleanValue());
        }

        if (_methodName636.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes636, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.createNewVersionForPlanFrom((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanModelRun) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName637.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes637, parameterTypes)) {
            PlanModelRunLocalServiceUtil.store((com.ext.portlet.model.PlanModelRun) arguments[0]);

            return null;
        }

        if (_methodName638.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes638, parameterTypes)) {
            return PlanModelRunLocalServiceUtil.getUpdateAuthor((com.ext.portlet.model.PlanModelRun) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
