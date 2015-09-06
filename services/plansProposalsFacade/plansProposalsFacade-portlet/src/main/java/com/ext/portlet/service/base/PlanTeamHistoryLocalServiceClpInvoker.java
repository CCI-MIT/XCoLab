package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanTeamHistoryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanTeamHistoryLocalServiceClpInvoker {
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
    private String _methodName632;
    private String[] _methodParameterTypes632;
    private String _methodName633;
    private String[] _methodParameterTypes633;
    private String _methodName638;
    private String[] _methodParameterTypes638;
    private String _methodName639;
    private String[] _methodParameterTypes639;
    private String _methodName640;
    private String[] _methodParameterTypes640;
    private String _methodName641;
    private String[] _methodParameterTypes641;
    private String _methodName642;
    private String[] _methodParameterTypes642;
    private String _methodName643;
    private String[] _methodParameterTypes643;

    public PlanTeamHistoryLocalServiceClpInvoker() {
        _methodName0 = "addPlanTeamHistory";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.PlanTeamHistory"
            };

        _methodName1 = "createPlanTeamHistory";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deletePlanTeamHistory";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deletePlanTeamHistory";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.PlanTeamHistory"
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

        _methodName10 = "fetchPlanTeamHistory";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getPlanTeamHistory";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getPlanTeamHistories";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getPlanTeamHistoriesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updatePlanTeamHistory";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.PlanTeamHistory"
            };

        _methodName632 = "getBeanIdentifier";

        _methodParameterTypes632 = new String[] {  };

        _methodName633 = "setBeanIdentifier";

        _methodParameterTypes633 = new String[] { "java.lang.String" };

        _methodName638 = "newHistoryItem";

        _methodParameterTypes638 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.String",
                "java.lang.Long"
            };

        _methodName639 = "newHistoryItem";

        _methodParameterTypes639 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.String",
                "java.lang.String", "java.lang.Long"
            };

        _methodName640 = "getLastUserActionInPlan";

        _methodParameterTypes640 = new String[] {
                "java.lang.Long", "java.lang.Long"
            };

        _methodName641 = "store";

        _methodParameterTypes641 = new String[] {
                "com.ext.portlet.model.PlanTeamHistory"
            };

        _methodName642 = "getUser";

        _methodParameterTypes642 = new String[] {
                "com.ext.portlet.model.PlanTeamHistory"
            };

        _methodName643 = "getPlan";

        _methodParameterTypes643 = new String[] {
                "com.ext.portlet.model.PlanTeamHistory"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.addPlanTeamHistory((com.ext.portlet.model.PlanTeamHistory) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.createPlanTeamHistory(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.deletePlanTeamHistory(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.deletePlanTeamHistory((com.ext.portlet.model.PlanTeamHistory) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.fetchPlanTeamHistory(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.getPlanTeamHistory(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.getPlanTeamHistories(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.getPlanTeamHistoriesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.updatePlanTeamHistory((com.ext.portlet.model.PlanTeamHistory) arguments[0]);
        }

        if (_methodName632.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes632, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName633.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes633, parameterTypes)) {
            PlanTeamHistoryLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName638.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes638, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.newHistoryItem((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1], (java.lang.String) arguments[2],
                (java.lang.Long) arguments[3]);
        }

        if (_methodName639.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes639, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.newHistoryItem((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1], (java.lang.String) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4]);
        }

        if (_methodName640.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes640, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.getLastUserActionInPlan((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1]);
        }

        if (_methodName641.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes641, parameterTypes)) {
            PlanTeamHistoryLocalServiceUtil.store((com.ext.portlet.model.PlanTeamHistory) arguments[0]);

            return null;
        }

        if (_methodName642.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes642, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.getUser((com.ext.portlet.model.PlanTeamHistory) arguments[0]);
        }

        if (_methodName643.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes643, parameterTypes)) {
            return PlanTeamHistoryLocalServiceUtil.getPlan((com.ext.portlet.model.PlanTeamHistory) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
