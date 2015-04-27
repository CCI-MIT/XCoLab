package com.ext.portlet.service.base;

import com.ext.portlet.service.ContestTeamMemberLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContestTeamMemberLocalServiceClpInvoker {
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
    private String _methodName626;
    private String[] _methodParameterTypes626;
    private String _methodName627;
    private String[] _methodParameterTypes627;
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

    public ContestTeamMemberLocalServiceClpInvoker() {
        _methodName0 = "addContestTeamMember";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.ContestTeamMember"
            };

        _methodName1 = "createContestTeamMember";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteContestTeamMember";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteContestTeamMember";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.ContestTeamMember"
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

        _methodName10 = "fetchContestTeamMember";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getContestTeamMember";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getContestTeamMembers";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getContestTeamMembersCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateContestTeamMember";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.ContestTeamMember"
            };

        _methodName626 = "getBeanIdentifier";

        _methodParameterTypes626 = new String[] {  };

        _methodName627 = "setBeanIdentifier";

        _methodParameterTypes627 = new String[] { "java.lang.String" };

        _methodName632 = "addContestTeamMember";

        _methodParameterTypes632 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.String"
            };

        _methodName633 = "findForContest";

        _methodParameterTypes633 = new String[] { "java.lang.Long" };

        _methodName634 = "store";

        _methodParameterTypes634 = new String[] {
                "com.ext.portlet.model.ContestTeamMember"
            };

        _methodName635 = "delete";

        _methodParameterTypes635 = new String[] {
                "com.ext.portlet.model.ContestTeamMember"
            };

        _methodName636 = "getUser";

        _methodParameterTypes636 = new String[] {
                "com.ext.portlet.model.ContestTeamMember"
            };

        _methodName637 = "getContest";

        _methodParameterTypes637 = new String[] {
                "com.ext.portlet.model.ContestTeamMember"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.addContestTeamMember((com.ext.portlet.model.ContestTeamMember) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.createContestTeamMember(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.deleteContestTeamMember(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.deleteContestTeamMember((com.ext.portlet.model.ContestTeamMember) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.fetchContestTeamMember(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.getContestTeamMember(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.getContestTeamMembers(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.getContestTeamMembersCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.updateContestTeamMember((com.ext.portlet.model.ContestTeamMember) arguments[0]);
        }

        if (_methodName626.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes626, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName627.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes627, parameterTypes)) {
            ContestTeamMemberLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName632.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes632, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.addContestTeamMember((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1], (java.lang.String) arguments[2]);
        }

        if (_methodName633.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes633, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.findForContest((java.lang.Long) arguments[0]);
        }

        if (_methodName634.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes634, parameterTypes)) {
            ContestTeamMemberLocalServiceUtil.store((com.ext.portlet.model.ContestTeamMember) arguments[0]);

            return null;
        }

        if (_methodName635.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes635, parameterTypes)) {
            ContestTeamMemberLocalServiceUtil.delete((com.ext.portlet.model.ContestTeamMember) arguments[0]);

            return null;
        }

        if (_methodName636.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes636, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.getUser((com.ext.portlet.model.ContestTeamMember) arguments[0]);
        }

        if (_methodName637.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes637, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.getContest((com.ext.portlet.model.ContestTeamMember) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
