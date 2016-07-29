package com.ext.portlet.service.base;

import com.ext.portlet.service.ContestTypeLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContestTypeLocalServiceClpInvoker {
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
    private String _methodName488;
    private String[] _methodParameterTypes488;
    private String _methodName489;
    private String[] _methodParameterTypes489;
    private String _methodName494;
    private String[] _methodParameterTypes494;
    private String _methodName495;
    private String[] _methodParameterTypes495;
    private String _methodName496;
    private String[] _methodParameterTypes496;
    private String _methodName497;
    private String[] _methodParameterTypes497;
    private String _methodName498;
    private String[] _methodParameterTypes498;
    private String _methodName499;
    private String[] _methodParameterTypes499;
    private String _methodName500;
    private String[] _methodParameterTypes500;
    private String _methodName501;
    private String[] _methodParameterTypes501;

    public ContestTypeLocalServiceClpInvoker() {
        _methodName0 = "addContestType";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.ContestType"
            };

        _methodName1 = "createContestType";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteContestType";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteContestType";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.ContestType"
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

        _methodName10 = "fetchContestType";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getContestType";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getContestTypes";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getContestTypesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateContestType";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.ContestType"
            };

        _methodName488 = "getBeanIdentifier";

        _methodParameterTypes488 = new String[] {  };

        _methodName489 = "setBeanIdentifier";

        _methodParameterTypes489 = new String[] { "java.lang.String" };

        _methodName494 = "getContestTypeFromProposalId";

        _methodParameterTypes494 = new String[] { "long" };

        _methodName495 = "getContestTypeFromContestId";

        _methodParameterTypes495 = new String[] { "long" };

        _methodName496 = "getContestType";

        _methodParameterTypes496 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName497 = "getAllContestTypes";

        _methodParameterTypes497 = new String[] {  };

        _methodName498 = "getActiveContestTypes";

        _methodParameterTypes498 = new String[] {  };

        _methodName499 = "getLabelName";

        _methodParameterTypes499 = new String[] {
                "com.ext.portlet.model.ContestType"
            };

        _methodName500 = "getProposalNames";

        _methodParameterTypes500 = new String[] {
                "java.util.List", "java.lang.String", "java.lang.String"
            };

        _methodName501 = "getContestNames";

        _methodParameterTypes501 = new String[] {
                "java.util.List", "java.lang.String", "java.lang.String"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ContestTypeLocalServiceUtil.addContestType((com.ext.portlet.model.ContestType) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ContestTypeLocalServiceUtil.createContestType(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ContestTypeLocalServiceUtil.deleteContestType(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ContestTypeLocalServiceUtil.deleteContestType((com.ext.portlet.model.ContestType) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ContestTypeLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ContestTypeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ContestTypeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ContestTypeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ContestTypeLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ContestTypeLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ContestTypeLocalServiceUtil.fetchContestType(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ContestTypeLocalServiceUtil.getContestType(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ContestTypeLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ContestTypeLocalServiceUtil.getContestTypes(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ContestTypeLocalServiceUtil.getContestTypesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ContestTypeLocalServiceUtil.updateContestType((com.ext.portlet.model.ContestType) arguments[0]);
        }

        if (_methodName488.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes488, parameterTypes)) {
            return ContestTypeLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName489.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes489, parameterTypes)) {
            ContestTypeLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName494.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes494, parameterTypes)) {
            return ContestTypeLocalServiceUtil.getContestTypeFromProposalId(((Long) arguments[0]).longValue());
        }

        if (_methodName495.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes495, parameterTypes)) {
            return ContestTypeLocalServiceUtil.getContestTypeFromContestId(((Long) arguments[0]).longValue());
        }

        if (_methodName496.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes496, parameterTypes)) {
            return ContestTypeLocalServiceUtil.getContestType((com.ext.portlet.model.Contest) arguments[0]);
        }

        if (_methodName497.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes497, parameterTypes)) {
            return ContestTypeLocalServiceUtil.getAllContestTypes();
        }

        if (_methodName498.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes498, parameterTypes)) {
            return ContestTypeLocalServiceUtil.getActiveContestTypes();
        }

        if (_methodName499.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes499, parameterTypes)) {
            return ContestTypeLocalServiceUtil.getLabelName((com.ext.portlet.model.ContestType) arguments[0]);
        }

        if (_methodName500.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes500, parameterTypes)) {
            return ContestTypeLocalServiceUtil.getProposalNames((java.util.List<java.lang.Long>) arguments[0],
                (java.lang.String) arguments[1], (java.lang.String) arguments[2]);
        }

        if (_methodName501.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes501, parameterTypes)) {
            return ContestTypeLocalServiceUtil.getContestNames((java.util.List<java.lang.Long>) arguments[0],
                (java.lang.String) arguments[1], (java.lang.String) arguments[2]);
        }

        throw new UnsupportedOperationException();
    }
}
