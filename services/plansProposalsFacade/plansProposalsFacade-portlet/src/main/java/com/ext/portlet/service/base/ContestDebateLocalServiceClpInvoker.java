package com.ext.portlet.service.base;

import com.ext.portlet.service.ContestDebateLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContestDebateLocalServiceClpInvoker {
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
    private String _methodName552;
    private String[] _methodParameterTypes552;
    private String _methodName553;
    private String[] _methodParameterTypes553;
    private String _methodName558;
    private String[] _methodParameterTypes558;
    private String _methodName559;
    private String[] _methodParameterTypes559;
    private String _methodName560;
    private String[] _methodParameterTypes560;
    private String _methodName561;
    private String[] _methodParameterTypes561;

    public ContestDebateLocalServiceClpInvoker() {
        _methodName0 = "addContestDebate";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.ContestDebate"
            };

        _methodName1 = "createContestDebate";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteContestDebate";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteContestDebate";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.ContestDebate"
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

        _methodName10 = "fetchContestDebate";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getContestDebate";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getContestDebates";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getContestDebatesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateContestDebate";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.ContestDebate"
            };

        _methodName552 = "getBeanIdentifier";

        _methodParameterTypes552 = new String[] {  };

        _methodName553 = "setBeanIdentifier";

        _methodParameterTypes553 = new String[] { "java.lang.String" };

        _methodName558 = "createContestDebate";

        _methodParameterTypes558 = new String[] {
                "java.lang.Long", "java.lang.Long"
            };

        _methodName559 = "getContestDebates";

        _methodParameterTypes559 = new String[] { "java.lang.Long" };

        _methodName560 = "store";

        _methodParameterTypes560 = new String[] {
                "com.ext.portlet.model.ContestDebate"
            };

        _methodName561 = "delete";

        _methodParameterTypes561 = new String[] {
                "com.ext.portlet.model.ContestDebate"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ContestDebateLocalServiceUtil.addContestDebate((com.ext.portlet.model.ContestDebate) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ContestDebateLocalServiceUtil.createContestDebate(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ContestDebateLocalServiceUtil.deleteContestDebate(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ContestDebateLocalServiceUtil.deleteContestDebate((com.ext.portlet.model.ContestDebate) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ContestDebateLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ContestDebateLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ContestDebateLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ContestDebateLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ContestDebateLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ContestDebateLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ContestDebateLocalServiceUtil.fetchContestDebate(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ContestDebateLocalServiceUtil.getContestDebate(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ContestDebateLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ContestDebateLocalServiceUtil.getContestDebates(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ContestDebateLocalServiceUtil.getContestDebatesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ContestDebateLocalServiceUtil.updateContestDebate((com.ext.portlet.model.ContestDebate) arguments[0]);
        }

        if (_methodName552.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes552, parameterTypes)) {
            return ContestDebateLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName553.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes553, parameterTypes)) {
            ContestDebateLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName558.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes558, parameterTypes)) {
            return ContestDebateLocalServiceUtil.createContestDebate((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1]);
        }

        if (_methodName559.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes559, parameterTypes)) {
            return ContestDebateLocalServiceUtil.getContestDebates((java.lang.Long) arguments[0]);
        }

        if (_methodName560.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes560, parameterTypes)) {
            ContestDebateLocalServiceUtil.store((com.ext.portlet.model.ContestDebate) arguments[0]);

            return null;
        }

        if (_methodName561.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes561, parameterTypes)) {
            ContestDebateLocalServiceUtil.delete((com.ext.portlet.model.ContestDebate) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
