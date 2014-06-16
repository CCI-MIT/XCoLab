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

        _methodName514 = "getBeanIdentifier";

        _methodParameterTypes514 = new String[] {  };

        _methodName515 = "setBeanIdentifier";

        _methodParameterTypes515 = new String[] { "java.lang.String" };

        _methodName520 = "createPlanDescription";

        _methodParameterTypes520 = new String[] {
                "com.ext.portlet.model.PlanItem", "java.lang.String"
            };

        _methodName521 = "createPlanDescription";

        _methodParameterTypes521 = new String[] {
                "com.ext.portlet.model.PlanItem", "java.lang.String", "boolean"
            };

        _methodName522 = "getCurrentForPlan";

        _methodParameterTypes522 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName523 = "getForPlan";

        _methodParameterTypes523 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName524 = "getAllForPlan";

        _methodParameterTypes524 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName525 = "createNewVersionForPlan";

        _methodParameterTypes525 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName526 = "createNewVersionForPlan";

        _methodParameterTypes526 = new String[] {
                "com.ext.portlet.model.PlanItem", "boolean"
            };

        _methodName527 = "createNewVersionForPlanFrom";

        _methodParameterTypes527 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanDescription", "boolean"
            };

        _methodName528 = "store";

        _methodParameterTypes528 = new String[] {
                "com.ext.portlet.model.PlanDescription"
            };

        _methodName529 = "getUpdateAuthor";

        _methodParameterTypes529 = new String[] {
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

        if (_methodName514.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes514, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName515.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes515, parameterTypes)) {
            PlanDescriptionLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName520.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes520, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.createPlanDescription((com.ext.portlet.model.PlanItem) arguments[0],
                (java.lang.String) arguments[1]);
        }

        if (_methodName521.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes521, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.createPlanDescription((com.ext.portlet.model.PlanItem) arguments[0],
                (java.lang.String) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName522.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes522, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getCurrentForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName523.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes523, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName524.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes524, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getAllForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName525.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes525, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.createNewVersionForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName526.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes526, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.createNewVersionForPlan((com.ext.portlet.model.PlanItem) arguments[0],
                ((Boolean) arguments[1]).booleanValue());
        }

        if (_methodName527.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes527, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.createNewVersionForPlanFrom((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanDescription) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName528.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes528, parameterTypes)) {
            PlanDescriptionLocalServiceUtil.store((com.ext.portlet.model.PlanDescription) arguments[0]);

            return null;
        }

        if (_methodName529.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes529, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getUpdateAuthor((com.ext.portlet.model.PlanDescription) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
