package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanPositionsLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanPositionsLocalServiceClpInvoker {
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
    private String _methodName576;
    private String[] _methodParameterTypes576;
    private String _methodName577;
    private String[] _methodParameterTypes577;
    private String _methodName582;
    private String[] _methodParameterTypes582;
    private String _methodName583;
    private String[] _methodParameterTypes583;
    private String _methodName584;
    private String[] _methodParameterTypes584;
    private String _methodName585;
    private String[] _methodParameterTypes585;
    private String _methodName586;
    private String[] _methodParameterTypes586;
    private String _methodName587;
    private String[] _methodParameterTypes587;
    private String _methodName588;
    private String[] _methodParameterTypes588;
    private String _methodName589;
    private String[] _methodParameterTypes589;
    private String _methodName590;
    private String[] _methodParameterTypes590;

    public PlanPositionsLocalServiceClpInvoker() {
        _methodName0 = "addPlanPositions";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.PlanPositions"
            };

        _methodName1 = "createPlanPositions";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deletePlanPositions";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deletePlanPositions";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.PlanPositions"
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

        _methodName10 = "fetchPlanPositions";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getPlanPositions";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getPlanPositionses";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getPlanPositionsesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updatePlanPositions";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.PlanPositions"
            };

        _methodName576 = "getBeanIdentifier";

        _methodParameterTypes576 = new String[] {  };

        _methodName577 = "setBeanIdentifier";

        _methodParameterTypes577 = new String[] { "java.lang.String" };

        _methodName582 = "getCurrentForPlan";

        _methodParameterTypes582 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName583 = "createPlanPositions";

        _methodParameterTypes583 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName584 = "getAllForPlan";

        _methodParameterTypes584 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName585 = "createNewVersionForPlan";

        _methodParameterTypes585 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName586 = "createNewVersionForPlan";

        _methodParameterTypes586 = new String[] {
                "com.ext.portlet.model.PlanItem", "boolean"
            };

        _methodName587 = "getPositionsIds";

        _methodParameterTypes587 = new String[] {
                "com.ext.portlet.model.PlanPositions"
            };

        _methodName588 = "store";

        _methodParameterTypes588 = new String[] {
                "com.ext.portlet.model.PlanPositions"
            };

        _methodName589 = "setPositionsIds";

        _methodParameterTypes589 = new String[] {
                "com.ext.portlet.model.PlanPositions", "java.util.List"
            };

        _methodName590 = "getUpdateAuthor";

        _methodParameterTypes590 = new String[] {
                "com.ext.portlet.model.PlanPositions"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.addPlanPositions((com.ext.portlet.model.PlanPositions) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.createPlanPositions(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.deletePlanPositions(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.deletePlanPositions((com.ext.portlet.model.PlanPositions) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.fetchPlanPositions(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.getPlanPositions(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.getPlanPositionses(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.getPlanPositionsesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.updatePlanPositions((com.ext.portlet.model.PlanPositions) arguments[0]);
        }

        if (_methodName576.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes576, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName577.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes577, parameterTypes)) {
            PlanPositionsLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName582.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes582, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.getCurrentForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName583.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes583, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.createPlanPositions((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName584.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes584, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.getAllForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName585.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes585, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.createNewVersionForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName586.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes586, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.createNewVersionForPlan((com.ext.portlet.model.PlanItem) arguments[0],
                ((Boolean) arguments[1]).booleanValue());
        }

        if (_methodName587.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes587, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.getPositionsIds((com.ext.portlet.model.PlanPositions) arguments[0]);
        }

        if (_methodName588.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes588, parameterTypes)) {
            PlanPositionsLocalServiceUtil.store((com.ext.portlet.model.PlanPositions) arguments[0]);

            return null;
        }

        if (_methodName589.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes589, parameterTypes)) {
            PlanPositionsLocalServiceUtil.setPositionsIds((com.ext.portlet.model.PlanPositions) arguments[0],
                (java.util.List<java.lang.Long>) arguments[1]);

            return null;
        }

        if (_methodName590.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes590, parameterTypes)) {
            return PlanPositionsLocalServiceUtil.getUpdateAuthor((com.ext.portlet.model.PlanPositions) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
