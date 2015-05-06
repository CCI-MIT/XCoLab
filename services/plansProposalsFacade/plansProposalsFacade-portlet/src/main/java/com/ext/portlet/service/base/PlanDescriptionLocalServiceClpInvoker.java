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
    private String _methodName658;
    private String[] _methodParameterTypes658;
    private String _methodName659;
    private String[] _methodParameterTypes659;
    private String _methodName664;
    private String[] _methodParameterTypes664;
    private String _methodName665;
    private String[] _methodParameterTypes665;
    private String _methodName666;
    private String[] _methodParameterTypes666;
    private String _methodName667;
    private String[] _methodParameterTypes667;
    private String _methodName668;
    private String[] _methodParameterTypes668;
    private String _methodName669;
    private String[] _methodParameterTypes669;
    private String _methodName670;
    private String[] _methodParameterTypes670;
    private String _methodName671;
    private String[] _methodParameterTypes671;
    private String _methodName672;
    private String[] _methodParameterTypes672;
    private String _methodName673;
    private String[] _methodParameterTypes673;

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

        _methodName658 = "getBeanIdentifier";

        _methodParameterTypes658 = new String[] {  };

        _methodName659 = "setBeanIdentifier";

        _methodParameterTypes659 = new String[] { "java.lang.String" };

        _methodName664 = "createPlanDescription";

        _methodParameterTypes664 = new String[] {
                "com.ext.portlet.model.PlanItem", "java.lang.String"
            };

        _methodName665 = "createPlanDescription";

        _methodParameterTypes665 = new String[] {
                "com.ext.portlet.model.PlanItem", "java.lang.String", "boolean"
            };

        _methodName666 = "getCurrentForPlan";

        _methodParameterTypes666 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName667 = "getForPlan";

        _methodParameterTypes667 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName668 = "getAllForPlan";

        _methodParameterTypes668 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName669 = "createNewVersionForPlan";

        _methodParameterTypes669 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName670 = "createNewVersionForPlan";

        _methodParameterTypes670 = new String[] {
                "com.ext.portlet.model.PlanItem", "boolean"
            };

        _methodName671 = "createNewVersionForPlanFrom";

        _methodParameterTypes671 = new String[] {
                "com.ext.portlet.model.PlanItem",
                "com.ext.portlet.model.PlanDescription", "boolean"
            };

        _methodName672 = "store";

        _methodParameterTypes672 = new String[] {
                "com.ext.portlet.model.PlanDescription"
            };

        _methodName673 = "getUpdateAuthor";

        _methodParameterTypes673 = new String[] {
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

        if (_methodName658.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes658, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName659.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes659, parameterTypes)) {
            PlanDescriptionLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName664.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes664, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.createPlanDescription((com.ext.portlet.model.PlanItem) arguments[0],
                (java.lang.String) arguments[1]);
        }

        if (_methodName665.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes665, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.createPlanDescription((com.ext.portlet.model.PlanItem) arguments[0],
                (java.lang.String) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName666.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes666, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getCurrentForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName667.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes667, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName668.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes668, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getAllForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName669.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes669, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.createNewVersionForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName670.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes670, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.createNewVersionForPlan((com.ext.portlet.model.PlanItem) arguments[0],
                ((Boolean) arguments[1]).booleanValue());
        }

        if (_methodName671.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes671, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.createNewVersionForPlanFrom((com.ext.portlet.model.PlanItem) arguments[0],
                (com.ext.portlet.model.PlanDescription) arguments[1],
                ((Boolean) arguments[2]).booleanValue());
        }

        if (_methodName672.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes672, parameterTypes)) {
            PlanDescriptionLocalServiceUtil.store((com.ext.portlet.model.PlanDescription) arguments[0]);

            return null;
        }

        if (_methodName673.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes673, parameterTypes)) {
            return PlanDescriptionLocalServiceUtil.getUpdateAuthor((com.ext.portlet.model.PlanDescription) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
