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
    private String _methodName638;
    private String[] _methodParameterTypes638;
    private String _methodName639;
    private String[] _methodParameterTypes639;
    private String _methodName644;
    private String[] _methodParameterTypes644;
    private String _methodName645;
    private String[] _methodParameterTypes645;
    private String _methodName646;
    private String[] _methodParameterTypes646;
    private String _methodName647;
    private String[] _methodParameterTypes647;
    private String _methodName648;
    private String[] _methodParameterTypes648;
    private String _methodName649;
    private String[] _methodParameterTypes649;

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

        _methodName638 = "getBeanIdentifier";

        _methodParameterTypes638 = new String[] {  };

        _methodName639 = "setBeanIdentifier";

        _methodParameterTypes639 = new String[] { "java.lang.String" };

        _methodName644 = "addContestTeamMember";

        _methodParameterTypes644 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.String"
            };

        _methodName645 = "findForContest";

        _methodParameterTypes645 = new String[] { "java.lang.Long" };

        _methodName646 = "store";

        _methodParameterTypes646 = new String[] {
                "com.ext.portlet.model.ContestTeamMember"
            };

        _methodName647 = "delete";

        _methodParameterTypes647 = new String[] {
                "com.ext.portlet.model.ContestTeamMember"
            };

        _methodName648 = "getUser";

        _methodParameterTypes648 = new String[] {
                "com.ext.portlet.model.ContestTeamMember"
            };

        _methodName649 = "getContest";

        _methodParameterTypes649 = new String[] {
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

        if (_methodName638.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes638, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName639.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes639, parameterTypes)) {
            ContestTeamMemberLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName644.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes644, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.addContestTeamMember((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1], (java.lang.String) arguments[2]);
        }

        if (_methodName645.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes645, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.findForContest((java.lang.Long) arguments[0]);
        }

        if (_methodName646.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes646, parameterTypes)) {
            ContestTeamMemberLocalServiceUtil.store((com.ext.portlet.model.ContestTeamMember) arguments[0]);

            return null;
        }

        if (_methodName647.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes647, parameterTypes)) {
            ContestTeamMemberLocalServiceUtil.delete((com.ext.portlet.model.ContestTeamMember) arguments[0]);

            return null;
        }

        if (_methodName648.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes648, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.getUser((com.ext.portlet.model.ContestTeamMember) arguments[0]);
        }

        if (_methodName649.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes649, parameterTypes)) {
            return ContestTeamMemberLocalServiceUtil.getContest((com.ext.portlet.model.ContestTeamMember) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
