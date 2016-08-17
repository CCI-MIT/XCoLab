package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalMoveHistoryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalMoveHistoryLocalServiceClpInvoker {
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
    private String _methodName398;
    private String[] _methodParameterTypes398;
    private String _methodName399;
    private String[] _methodParameterTypes399;
    private String _methodName404;
    private String[] _methodParameterTypes404;
    private String _methodName405;
    private String[] _methodParameterTypes405;
    private String _methodName406;
    private String[] _methodParameterTypes406;
    private String _methodName407;
    private String[] _methodParameterTypes407;
    private String _methodName408;
    private String[] _methodParameterTypes408;
    private String _methodName409;
    private String[] _methodParameterTypes409;
    private String _methodName410;
    private String[] _methodParameterTypes410;
    private String _methodName411;
    private String[] _methodParameterTypes411;
    private String _methodName412;
    private String[] _methodParameterTypes412;
    private String _methodName413;
    private String[] _methodParameterTypes413;
    private String _methodName414;
    private String[] _methodParameterTypes414;
    private String _methodName415;
    private String[] _methodParameterTypes415;

    public ProposalMoveHistoryLocalServiceClpInvoker() {
        _methodName0 = "addProposalMoveHistory";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.ProposalMoveHistory"
            };

        _methodName1 = "createProposalMoveHistory";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteProposalMoveHistory";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteProposalMoveHistory";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.ProposalMoveHistory"
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

        _methodName10 = "fetchProposalMoveHistory";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getProposalMoveHistory";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getProposalMoveHistories";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getProposalMoveHistoriesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateProposalMoveHistory";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.ProposalMoveHistory"
            };

        _methodName398 = "getBeanIdentifier";

        _methodParameterTypes398 = new String[] {  };

        _methodName399 = "setBeanIdentifier";

        _methodParameterTypes399 = new String[] { "java.lang.String" };

        _methodName404 = "create";

        _methodParameterTypes404 = new String[] {
                "long", "long", "long", "long", "long", "long", "long",
                "java.lang.String"
            };

        _methodName405 = "createMoveHistory";

        _methodParameterTypes405 = new String[] {
                "long", "long", "long", "long", "long", "long"
            };

        _methodName406 = "createCopyHistory";

        _methodParameterTypes406 = new String[] {
                "long", "long", "long", "long", "long", "long"
            };

        _methodName407 = "createForkHistory";

        _methodParameterTypes407 = new String[] {
                "long", "long", "long", "long", "long", "long", "long"
            };

        _methodName408 = "getBySourceProposalId";

        _methodParameterTypes408 = new String[] { "long" };

        _methodName409 = "getBySourceContestId";

        _methodParameterTypes409 = new String[] { "long" };

        _methodName410 = "getBySourcePhaseId";

        _methodParameterTypes410 = new String[] { "long" };

        _methodName411 = "getByTargetProposalId";

        _methodParameterTypes411 = new String[] { "long" };

        _methodName412 = "getByTargetContestId";

        _methodParameterTypes412 = new String[] { "long" };

        _methodName413 = "getByTargetPhaseId";

        _methodParameterTypes413 = new String[] { "long" };

        _methodName414 = "getBySourceProposalIdContestId";

        _methodParameterTypes414 = new String[] { "long", "long" };

        _methodName415 = "getByTargetProposalIdContestId";

        _methodParameterTypes415 = new String[] { "long", "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.addProposalMoveHistory((com.ext.portlet.model.ProposalMoveHistory) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.createProposalMoveHistory(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.deleteProposalMoveHistory(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.deleteProposalMoveHistory((com.ext.portlet.model.ProposalMoveHistory) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.fetchProposalMoveHistory(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getProposalMoveHistory(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getProposalMoveHistories(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getProposalMoveHistoriesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.updateProposalMoveHistory((com.ext.portlet.model.ProposalMoveHistory) arguments[0]);
        }

        if (_methodName398.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes398, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName399.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes399, parameterTypes)) {
            ProposalMoveHistoryLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName404.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes404, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.create(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                ((Long) arguments[3]).longValue(),
                ((Long) arguments[4]).longValue(),
                ((Long) arguments[5]).longValue(),
                ((Long) arguments[6]).longValue(),
                (java.lang.String) arguments[7]);
        }

        if (_methodName405.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes405, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.createMoveHistory(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                ((Long) arguments[3]).longValue(),
                ((Long) arguments[4]).longValue(),
                ((Long) arguments[5]).longValue());
        }

        if (_methodName406.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes406, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.createCopyHistory(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                ((Long) arguments[3]).longValue(),
                ((Long) arguments[4]).longValue(),
                ((Long) arguments[5]).longValue());
        }

        if (_methodName407.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes407, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.createForkHistory(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                ((Long) arguments[3]).longValue(),
                ((Long) arguments[4]).longValue(),
                ((Long) arguments[5]).longValue(),
                ((Long) arguments[6]).longValue());
        }

        if (_methodName408.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes408, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getBySourceProposalId(((Long) arguments[0]).longValue());
        }

        if (_methodName409.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes409, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getBySourceContestId(((Long) arguments[0]).longValue());
        }

        if (_methodName410.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes410, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getBySourcePhaseId(((Long) arguments[0]).longValue());
        }

        if (_methodName411.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes411, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getByTargetProposalId(((Long) arguments[0]).longValue());
        }

        if (_methodName412.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes412, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getByTargetContestId(((Long) arguments[0]).longValue());
        }

        if (_methodName413.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes413, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getByTargetPhaseId(((Long) arguments[0]).longValue());
        }

        if (_methodName414.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes414, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getBySourceProposalIdContestId(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName415.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes415, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getByTargetProposalIdContestId(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
