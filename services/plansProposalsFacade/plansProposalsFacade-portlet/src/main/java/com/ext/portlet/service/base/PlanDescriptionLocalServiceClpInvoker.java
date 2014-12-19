package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanDescriptionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanDescriptionLocalServiceClpInvoker {
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
    private String _methodName591;
    private String[] _methodParameterTypes591;

    public PlanDescriptionLocalServiceClpInvoker() {
        _methodName0 = "addPlanDescription";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.PlanDescription"
            };

        _methodName1 = "createPlanDescription";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deletePlanDescription";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deletePlanDescription";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.PlanDescription"
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

        _methodName10 = "fetchPlanDescription";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getPlanDescription";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getPlanDescriptions";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getPlanDescriptionsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updatePlanDescription";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.PlanDescription"
            };

        _methodName576 = "getBeanIdentifier";

        _methodParameterTypes576 = new String[] {  };

        _methodName577 = "setBeanIdentifier";

        _methodParameterTypes577 = new String[] { "java.lang.String" };

        _methodName582 = "createPlanDescription";

        _methodParameterTypes582 = new String[] {
                "com.ext.portlet.model.PlanItem", "java.lang.String"
            };

        _methodName583 = "createPlanDescription";

        _methodParameterTypes583 = new String[] {
                "com.ext.portlet.model.PlanItem", "java.lang.String", "boolean"
            };

        _methodName584 = "getCurrentForPlan";

        _methodParameterTypes584 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName585 = "getForPlan";

        _methodParameterTypes585 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName586 = "getAllForPlan";

        _methodParameterTypes586 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName587 = "createNewVersionForPlan";

        _methodParameterTypes587 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName588 = "createNewVersionForPlan";

        _methodParameterTypes588 = new String[] {
                "com.ext.portlet.model.PlanItem", "boolean"
            };

        _methodName589 = "createNewVersionForPlanFrom";

        _methodParameterTypes589 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanDescription", "boolean"
            };

        _methodName590 = "store";

        _methodParameterTypes590 = new String[] {
                "com.ext.portlet.model.PlanDescription"
            };

        _methodName591 = "getUpdateAuthor";

        _methodParameterTypes591 = new String[] {
                "com.ext.portlet.model.PlanDescription"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.addPlanDescription((com.ext.portlet.model.PlanDescription) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.createPlanDescription(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.deletePlanDescription(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.deletePlanDescription((com.ext.portlet.model.PlanDescription) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.fetchPlanDescription(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getPlanDescription(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getPlanDescriptions(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getPlanDescriptionsCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.updatePlanDescription((com.ext.portlet.model.PlanDescription) arguments[0]);
        }

        if (_methodName576.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes576, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName577.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes577, parameterTypes)) {
            PlanDescriptionLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName582.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes582, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.createPlanDescription((com.ext.portlet.model.PlanItem) arguments[0],
                (java.lang.String) arguments[1]);
        }

        if (_methodName583.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes583, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.createPlanDescription((com.ext.portlet.model.PlanItem) arguments[0],
                (java.lang.String) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName584.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes584, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getCurrentForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName585.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes585, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName586.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes586, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getAllForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName587.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes587, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.createNewVersionForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName588.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes588, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.createNewVersionForPlan((com.ext.portlet.model.PlanItem) arguments[0],
                ((Boolean) arguments[1]).booleanValue());
        }

        if (_methodName589.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes589, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.createNewVersionForPlanFrom((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanDescription) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName590.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes590, parameterTypes)) {
            PlanDescriptionLocalServiceUtil.store((com.ext.portlet.model.PlanDescription) arguments[0]);

            return null;
        }

        if (_methodName591.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes591, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getUpdateAuthor((com.ext.portlet.model.PlanDescription) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
