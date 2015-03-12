package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanMetaLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanMetaLocalServiceClpInvoker {
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

    public PlanMetaLocalServiceClpInvoker() {
        _methodName0 = "addPlanMeta";

        _methodParameterTypes0 = new String[] { "com.ext.portlet.model.PlanMeta" };

        _methodName1 = "createPlanMeta";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deletePlanMeta";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deletePlanMeta";

        _methodParameterTypes3 = new String[] { "com.ext.portlet.model.PlanMeta" };

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

        _methodName10 = "fetchPlanMeta";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getPlanMeta";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getPlanMetas";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getPlanMetasCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updatePlanMeta";

        _methodParameterTypes15 = new String[] { "com.ext.portlet.model.PlanMeta" };

        _methodName624 = "getBeanIdentifier";

        _methodParameterTypes624 = new String[] {  };

        _methodName625 = "setBeanIdentifier";

        _methodParameterTypes625 = new String[] { "java.lang.String" };

        _methodName630 = "createPlanMeta";

        _methodParameterTypes630 = new String[] {
                "com.ext.portlet.model.PlanItem", "java.lang.Long"
            };

        _methodName631 = "getCurrentForPlan";

        _methodParameterTypes631 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName632 = "getAllForPlan";

        _methodParameterTypes632 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName633 = "createNewVersionForPlan";

        _methodParameterTypes633 = new String[] { "com.ext.portlet.model.PlanItem" };

        _methodName634 = "createNewVersionForPlan";

        _methodParameterTypes634 = new String[] {
                "com.ext.portlet.model.PlanItem", "boolean"
            };

        _methodName635 = "store";

        _methodParameterTypes635 = new String[] { "com.ext.portlet.model.PlanMeta" };

        _methodName636 = "vote";

        _methodParameterTypes636 = new String[] { "com.ext.portlet.model.PlanMeta" };

        _methodName637 = "unvote";

        _methodParameterTypes637 = new String[] { "com.ext.portlet.model.PlanMeta" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return PlanMetaLocalServiceUtil.addPlanMeta((com.ext.portlet.model.PlanMeta) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return PlanMetaLocalServiceUtil.createPlanMeta(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return PlanMetaLocalServiceUtil.deletePlanMeta(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return PlanMetaLocalServiceUtil.deletePlanMeta((com.ext.portlet.model.PlanMeta) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return PlanMetaLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return PlanMetaLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return PlanMetaLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return PlanMetaLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return PlanMetaLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return PlanMetaLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return PlanMetaLocalServiceUtil.fetchPlanMeta(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return PlanMetaLocalServiceUtil.getPlanMeta(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return PlanMetaLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return PlanMetaLocalServiceUtil.getPlanMetas(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return PlanMetaLocalServiceUtil.getPlanMetasCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return PlanMetaLocalServiceUtil.updatePlanMeta((com.ext.portlet.model.PlanMeta) arguments[0]);
        }

        if (_methodName624.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes624, parameterTypes)) {
            return PlanMetaLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName625.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes625, parameterTypes)) {
            PlanMetaLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName630.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes630, parameterTypes)) {
            return PlanMetaLocalServiceUtil.createPlanMeta((com.ext.portlet.model.PlanItem) arguments[0],
                (java.lang.Long) arguments[1]);
        }

        if (_methodName631.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes631, parameterTypes)) {
            return PlanMetaLocalServiceUtil.getCurrentForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName632.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes632, parameterTypes)) {
            return PlanMetaLocalServiceUtil.getAllForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName633.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes633, parameterTypes)) {
            return PlanMetaLocalServiceUtil.createNewVersionForPlan((com.ext.portlet.model.PlanItem) arguments[0]);
        }

        if (_methodName634.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes634, parameterTypes)) {
            return PlanMetaLocalServiceUtil.createNewVersionForPlan((com.ext.portlet.model.PlanItem) arguments[0],
                ((Boolean) arguments[1]).booleanValue());
        }

        if (_methodName635.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes635, parameterTypes)) {
            PlanMetaLocalServiceUtil.store((com.ext.portlet.model.PlanMeta) arguments[0]);

            return null;
        }

        if (_methodName636.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes636, parameterTypes)) {
            PlanMetaLocalServiceUtil.vote((com.ext.portlet.model.PlanMeta) arguments[0]);

            return null;
        }

        if (_methodName637.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes637, parameterTypes)) {
            PlanMetaLocalServiceUtil.unvote((com.ext.portlet.model.PlanMeta) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
