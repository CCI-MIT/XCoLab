package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanTypeLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanTypeLocalServiceClpInvoker {
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

    public PlanTypeLocalServiceClpInvoker() {
        _methodName0 = "addPlanType";

        _methodParameterTypes0 = new String[] { "com.ext.portlet.model.PlanType" };

        _methodName1 = "createPlanType";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deletePlanType";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deletePlanType";

        _methodParameterTypes3 = new String[] { "com.ext.portlet.model.PlanType" };

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

        _methodName10 = "fetchPlanType";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getPlanType";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getPlanTypes";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getPlanTypesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updatePlanType";

        _methodParameterTypes15 = new String[] { "com.ext.portlet.model.PlanType" };

        _methodName658 = "getBeanIdentifier";

        _methodParameterTypes658 = new String[] {  };

        _methodName659 = "setBeanIdentifier";

        _methodParameterTypes659 = new String[] { "java.lang.String" };

        _methodName664 = "getDefaultPlanType";

        _methodParameterTypes664 = new String[] {  };

        _methodName665 = "getColumnsByPlanTypeId";

        _methodParameterTypes665 = new String[] { "long" };

        _methodName666 = "getAttributesByPlanTypeId";

        _methodParameterTypes666 = new String[] { "long" };

        _methodName667 = "isRegionalType";

        _methodParameterTypes667 = new String[] { "long" };

        _methodName668 = "getAvailableModels";

        _methodParameterTypes668 = new String[] { "com.ext.portlet.model.PlanType" };

        _methodName669 = "getDefaultModel";

        _methodParameterTypes669 = new String[] { "com.ext.portlet.model.PlanType" };

        _methodName670 = "getColumns";

        _methodParameterTypes670 = new String[] { "com.ext.portlet.model.PlanType" };

        _methodName671 = "getAttributes";

        _methodParameterTypes671 = new String[] { "com.ext.portlet.model.PlanType" };

        _methodName672 = "isRegional";

        _methodParameterTypes672 = new String[] { "com.ext.portlet.model.PlanType" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return PlanTypeLocalServiceUtil.addPlanType((com.ext.portlet.model.PlanType) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return PlanTypeLocalServiceUtil.createPlanType(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return PlanTypeLocalServiceUtil.deletePlanType(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return PlanTypeLocalServiceUtil.deletePlanType((com.ext.portlet.model.PlanType) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return PlanTypeLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return PlanTypeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return PlanTypeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return PlanTypeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return PlanTypeLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return PlanTypeLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return PlanTypeLocalServiceUtil.fetchPlanType(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return PlanTypeLocalServiceUtil.getPlanType(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return PlanTypeLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return PlanTypeLocalServiceUtil.getPlanTypes(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return PlanTypeLocalServiceUtil.getPlanTypesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return PlanTypeLocalServiceUtil.updatePlanType((com.ext.portlet.model.PlanType) arguments[0]);
        }

        if (_methodName658.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes658, parameterTypes)) {
            return PlanTypeLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName659.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes659, parameterTypes)) {
            PlanTypeLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName664.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes664, parameterTypes)) {
            return PlanTypeLocalServiceUtil.getDefaultPlanType();
        }

        if (_methodName665.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes665, parameterTypes)) {
            return PlanTypeLocalServiceUtil.getColumnsByPlanTypeId(((Long) arguments[0]).longValue());
        }

        if (_methodName666.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes666, parameterTypes)) {
            return PlanTypeLocalServiceUtil.getAttributesByPlanTypeId(((Long) arguments[0]).longValue());
        }

        if (_methodName667.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes667, parameterTypes)) {
            return PlanTypeLocalServiceUtil.isRegionalType(((Long) arguments[0]).longValue());
        }

        if (_methodName668.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes668, parameterTypes)) {
            return PlanTypeLocalServiceUtil.getAvailableModels((com.ext.portlet.model.PlanType) arguments[0]);
        }

        if (_methodName669.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes669, parameterTypes)) {
            return PlanTypeLocalServiceUtil.getDefaultModel((com.ext.portlet.model.PlanType) arguments[0]);
        }

        if (_methodName670.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes670, parameterTypes)) {
            return PlanTypeLocalServiceUtil.getColumns((com.ext.portlet.model.PlanType) arguments[0]);
        }

        if (_methodName671.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes671, parameterTypes)) {
            return PlanTypeLocalServiceUtil.getAttributes((com.ext.portlet.model.PlanType) arguments[0]);
        }

        if (_methodName672.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes672, parameterTypes)) {
            return PlanTypeLocalServiceUtil.isRegional((com.ext.portlet.model.PlanType) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
