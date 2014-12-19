package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanFanLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanFanLocalServiceClpInvoker {
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
    private String _methodName602;
    private String[] _methodParameterTypes602;
    private String _methodName603;
    private String[] _methodParameterTypes603;
    private String _methodName608;
    private String[] _methodParameterTypes608;
    private String _methodName609;
    private String[] _methodParameterTypes609;
    private String _methodName610;
    private String[] _methodParameterTypes610;
    private String _methodName611;
    private String[] _methodParameterTypes611;
    private String _methodName612;
    private String[] _methodParameterTypes612;
    private String _methodName613;
    private String[] _methodParameterTypes613;
    private String _methodName614;
    private String[] _methodParameterTypes614;
    private String _methodName615;
    private String[] _methodParameterTypes615;
    private String _methodName616;
    private String[] _methodParameterTypes616;
    private String _methodName617;
    private String[] _methodParameterTypes617;
    private String _methodName618;
    private String[] _methodParameterTypes618;

    public PlanFanLocalServiceClpInvoker() {
        _methodName0 = "addPlanFan";

        _methodParameterTypes0 = new String[] { "com.ext.portlet.model.PlanFan" };

        _methodName1 = "createPlanFan";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deletePlanFan";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deletePlanFan";

        _methodParameterTypes3 = new String[] { "com.ext.portlet.model.PlanFan" };

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

        _methodName10 = "fetchPlanFan";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getPlanFan";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getPlanFans";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getPlanFansCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updatePlanFan";

        _methodParameterTypes15 = new String[] { "com.ext.portlet.model.PlanFan" };

        _methodName602 = "getBeanIdentifier";

        _methodParameterTypes602 = new String[] {  };

        _methodName603 = "setBeanIdentifier";

        _methodParameterTypes603 = new String[] { "java.lang.String" };

        _methodName608 = "getPlanFansForPlan";

        _methodParameterTypes608 = new String[] { "java.lang.Long" };

        _methodName609 = "countPlanFansForPlan";

        _methodParameterTypes609 = new String[] { "java.lang.Long" };

        _methodName610 = "getPlanFansForUser";

        _methodParameterTypes610 = new String[] { "java.lang.Long" };

        _methodName611 = "addFan";

        _methodParameterTypes611 = new String[] {
                "java.lang.Long", "java.lang.Long"
            };

        _methodName612 = "removePlanFan";

        _methodParameterTypes612 = new String[] {
                "java.lang.Long", "java.lang.Long"
            };

        _methodName613 = "getPlanFanByPlanIdUserId";

        _methodParameterTypes613 = new String[] {
                "java.lang.Long", "java.lang.Long"
            };

        _methodName614 = "countByUserId";

        _methodParameterTypes614 = new String[] { "java.lang.Long" };

        _methodName615 = "getByUserId";

        _methodParameterTypes615 = new String[] { "java.lang.Long", "int", "int" };

        _methodName616 = "store";

        _methodParameterTypes616 = new String[] { "com.ext.portlet.model.PlanFan" };

        _methodName617 = "getUser";

        _methodParameterTypes617 = new String[] { "com.ext.portlet.model.PlanFan" };

        _methodName618 = "getPlan";

        _methodParameterTypes618 = new String[] { "com.ext.portlet.model.PlanFan" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return PlanFanLocalServiceUtil.addPlanFan((com.ext.portlet.model.PlanFan) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return PlanFanLocalServiceUtil.createPlanFan(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return PlanFanLocalServiceUtil.deletePlanFan(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return PlanFanLocalServiceUtil.deletePlanFan((com.ext.portlet.model.PlanFan) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return PlanFanLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return PlanFanLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return PlanFanLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return PlanFanLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return PlanFanLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return PlanFanLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return PlanFanLocalServiceUtil.fetchPlanFan(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return PlanFanLocalServiceUtil.getPlanFan(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return PlanFanLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return PlanFanLocalServiceUtil.getPlanFans(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return PlanFanLocalServiceUtil.getPlanFansCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return PlanFanLocalServiceUtil.updatePlanFan((com.ext.portlet.model.PlanFan) arguments[0]);
        }

        if (_methodName602.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes602, parameterTypes)) {
            return PlanFanLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName603.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes603, parameterTypes)) {
            PlanFanLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName608.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes608, parameterTypes)) {
            return PlanFanLocalServiceUtil.getPlanFansForPlan((java.lang.Long) arguments[0]);
        }

        if (_methodName609.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes609, parameterTypes)) {
            return PlanFanLocalServiceUtil.countPlanFansForPlan((java.lang.Long) arguments[0]);
        }

        if (_methodName610.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes610, parameterTypes)) {
            return PlanFanLocalServiceUtil.getPlanFansForUser((java.lang.Long) arguments[0]);
        }

        if (_methodName611.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes611, parameterTypes)) {
            return PlanFanLocalServiceUtil.addFan((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1]);
        }

        if (_methodName612.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes612, parameterTypes)) {
            PlanFanLocalServiceUtil.removePlanFan((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1]);

            return null;
        }

        if (_methodName613.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes613, parameterTypes)) {
            return PlanFanLocalServiceUtil.getPlanFanByPlanIdUserId((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1]);
        }

        if (_methodName614.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes614, parameterTypes)) {
            return PlanFanLocalServiceUtil.countByUserId((java.lang.Long) arguments[0]);
        }

        if (_methodName615.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes615, parameterTypes)) {
            return PlanFanLocalServiceUtil.getByUserId((java.lang.Long) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName616.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes616, parameterTypes)) {
            PlanFanLocalServiceUtil.store((com.ext.portlet.model.PlanFan) arguments[0]);

            return null;
        }

        if (_methodName617.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes617, parameterTypes)) {
            return PlanFanLocalServiceUtil.getUser((com.ext.portlet.model.PlanFan) arguments[0]);
        }

        if (_methodName618.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes618, parameterTypes)) {
            return PlanFanLocalServiceUtil.getPlan((com.ext.portlet.model.PlanFan) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
