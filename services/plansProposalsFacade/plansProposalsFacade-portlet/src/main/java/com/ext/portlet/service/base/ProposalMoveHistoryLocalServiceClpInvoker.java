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
    private String _methodName464;
    private String[] _methodParameterTypes464;
    private String _methodName465;
    private String[] _methodParameterTypes465;
    private String _methodName470;
    private String[] _methodParameterTypes470;
    private String _methodName471;
    private String[] _methodParameterTypes471;
    private String _methodName472;
    private String[] _methodParameterTypes472;
    private String _methodName473;
    private String[] _methodParameterTypes473;
    private String _methodName474;
    private String[] _methodParameterTypes474;
    private String _methodName475;
    private String[] _methodParameterTypes475;
    private String _methodName476;
    private String[] _methodParameterTypes476;
    private String _methodName477;
    private String[] _methodParameterTypes477;
    private String _methodName478;
    private String[] _methodParameterTypes478;
    private String _methodName479;
    private String[] _methodParameterTypes479;
    private String _methodName480;
    private String[] _methodParameterTypes480;
    private String _methodName481;
    private String[] _methodParameterTypes481;

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

        _methodName464 = "getBeanIdentifier";

        _methodParameterTypes464 = new String[] {  };

        _methodName465 = "setBeanIdentifier";

        _methodParameterTypes465 = new String[] { "java.lang.String" };

        _methodName470 = "create";

        _methodParameterTypes470 = new String[] {
                "long", "long", "long", "long", "long", "long", "long",
                "java.lang.String"
            };

        _methodName471 = "createMoveHistory";

        _methodParameterTypes471 = new String[] {
                "long", "long", "long", "long", "long", "long"
            };

        _methodName472 = "createCopyHistory";

        _methodParameterTypes472 = new String[] {
                "long", "long", "long", "long", "long", "long"
            };

        _methodName473 = "createForkHistory";

        _methodParameterTypes473 = new String[] {
                "long", "long", "long", "long", "long", "long", "long"
            };

        _methodName474 = "getBySourceProposalId";

        _methodParameterTypes474 = new String[] { "long" };

        _methodName475 = "getBySourceContestId";

        _methodParameterTypes475 = new String[] { "long" };

        _methodName476 = "getBySourcePhaseId";

        _methodParameterTypes476 = new String[] { "long" };

        _methodName477 = "getByTargetProposalId";

        _methodParameterTypes477 = new String[] { "long" };

        _methodName478 = "getByTargetContestId";

        _methodParameterTypes478 = new String[] { "long" };

        _methodName479 = "getByTargetPhaseId";

        _methodParameterTypes479 = new String[] { "long" };

        _methodName480 = "getBySourceProposalIdContestId";

        _methodParameterTypes480 = new String[] { "long", "long" };

        _methodName481 = "getByTargetProposalIdContestId";

        _methodParameterTypes481 = new String[] { "long", "long" };
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

        if (_methodName464.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes464, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName465.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes465, parameterTypes)) {
            ProposalMoveHistoryLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName470.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes470, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.create(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                ((Long) arguments[3]).longValue(),
                ((Long) arguments[4]).longValue(),
                ((Long) arguments[5]).longValue(),
                ((Long) arguments[6]).longValue(),
                (java.lang.String) arguments[7]);
        }

        if (_methodName471.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes471, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.createMoveHistory(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                ((Long) arguments[3]).longValue(),
                ((Long) arguments[4]).longValue(),
                ((Long) arguments[5]).longValue());
        }

        if (_methodName472.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes472, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.createCopyHistory(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                ((Long) arguments[3]).longValue(),
                ((Long) arguments[4]).longValue(),
                ((Long) arguments[5]).longValue());
        }

        if (_methodName473.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes473, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.createForkHistory(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                ((Long) arguments[3]).longValue(),
                ((Long) arguments[4]).longValue(),
                ((Long) arguments[5]).longValue(),
                ((Long) arguments[6]).longValue());
        }

        if (_methodName474.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes474, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getBySourceProposalId(((Long) arguments[0]).longValue());
        }

        if (_methodName475.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes475, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getBySourceContestId(((Long) arguments[0]).longValue());
        }

        if (_methodName476.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes476, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getBySourcePhaseId(((Long) arguments[0]).longValue());
        }

        if (_methodName477.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes477, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getByTargetProposalId(((Long) arguments[0]).longValue());
        }

        if (_methodName478.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes478, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getByTargetContestId(((Long) arguments[0]).longValue());
        }

        if (_methodName479.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes479, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getByTargetPhaseId(((Long) arguments[0]).longValue());
        }

        if (_methodName480.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes480, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getBySourceProposalIdContestId(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName481.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes481, parameterTypes)) {
            return ProposalMoveHistoryLocalServiceUtil.getByTargetProposalIdContestId(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
