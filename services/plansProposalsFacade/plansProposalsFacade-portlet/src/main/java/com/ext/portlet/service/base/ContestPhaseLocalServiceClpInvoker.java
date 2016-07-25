package com.ext.portlet.service.base;

import com.ext.portlet.service.ContestPhaseLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContestPhaseLocalServiceClpInvoker {
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
    private String _methodName502;
    private String[] _methodParameterTypes502;
    private String _methodName503;
    private String[] _methodParameterTypes503;
    private String _methodName504;
    private String[] _methodParameterTypes504;
    private String _methodName505;
    private String[] _methodParameterTypes505;
    private String _methodName506;
    private String[] _methodParameterTypes506;
    private String _methodName507;
    private String[] _methodParameterTypes507;
    private String _methodName508;
    private String[] _methodParameterTypes508;
    private String _methodName509;
    private String[] _methodParameterTypes509;
    private String _methodName510;
    private String[] _methodParameterTypes510;
    private String _methodName511;
    private String[] _methodParameterTypes511;
    private String _methodName512;
    private String[] _methodParameterTypes512;
    private String _methodName513;
    private String[] _methodParameterTypes513;
    private String _methodName514;
    private String[] _methodParameterTypes514;
    private String _methodName515;
    private String[] _methodParameterTypes515;

    public ContestPhaseLocalServiceClpInvoker() {
        _methodName0 = "addContestPhase";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName1 = "createContestPhase";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteContestPhase";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteContestPhase";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.ContestPhase"
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

        _methodName10 = "fetchContestPhase";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getContestPhase";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getContestPhases";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getContestPhasesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateContestPhase";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName488 = "getBeanIdentifier";

        _methodParameterTypes488 = new String[] {  };

        _methodName489 = "setBeanIdentifier";

        _methodParameterTypes489 = new String[] { "java.lang.String" };

        _methodName494 = "overrideClock";

        _methodParameterTypes494 = new String[] { "org.xcolab.utils.Clock" };

        _methodName495 = "getContestStatus";

        _methodParameterTypes495 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName496 = "getContestStatusStr";

        _methodParameterTypes496 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName497 = "getPhaseColumns";

        _methodParameterTypes497 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName498 = "getPhaseColumnsRaw";

        _methodParameterTypes498 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName499 = "getPreviousPhases";

        _methodParameterTypes499 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName500 = "getNextContestPhase";

        _methodParameterTypes500 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName501 = "getPhaseActive";

        _methodParameterTypes501 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName502 = "getPhasesForContest";

        _methodParameterTypes502 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName503 = "getPhasesForContest";

        _methodParameterTypes503 = new String[] { "long" };

        _methodName504 = "getPhasesForContestScheduleId";

        _methodParameterTypes504 = new String[] { "long" };

        _methodName505 = "getPhasesForContestScheduleIdAndContest";

        _methodParameterTypes505 = new String[] { "long", "long" };

        _methodName506 = "getPhasesForContestScheduleIdAndPhaseType";

        _methodParameterTypes506 = new String[] { "long", "long" };

        _methodName507 = "getActivePhaseForContest";

        _methodParameterTypes507 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName508 = "getContest";

        _methodParameterTypes508 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName509 = "getName";

        _methodParameterTypes509 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName510 = "promoteProposal";

        _methodParameterTypes510 = new String[] { "long", "long", "long" };

        _methodName511 = "autoPromoteProposals";

        _methodParameterTypes511 = new String[] {  };

        _methodName512 = "createFromContestPhase";

        _methodParameterTypes512 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName513 = "forcePromotionOfProposalInPhase";

        _methodParameterTypes513 = new String[] {
                "com.ext.portlet.model.Proposal",
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName514 = "getNumberOfProposalsForJudge";

        _methodParameterTypes514 = new String[] {
                "com.liferay.portal.model.User",
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName515 = "getContestPhaseLinkUrl";

        _methodParameterTypes515 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.addContestPhase((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.createContestPhase(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.deleteContestPhase(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.deleteContestPhase((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.fetchContestPhase(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestPhase(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestPhases(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestPhasesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.updateContestPhase((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName488.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes488, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName489.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes489, parameterTypes)) {
            ContestPhaseLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName494.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes494, parameterTypes)) {
            ContestPhaseLocalServiceUtil.overrideClock((org.xcolab.utils.Clock) arguments[0]);

            return null;
        }

        if (_methodName495.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes495, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestStatus((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName496.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes496, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestStatusStr((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName497.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes497, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseColumns((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName498.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes498, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseColumnsRaw((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName499.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes499, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPreviousPhases((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName500.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes500, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getNextContestPhase((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName501.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes501, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseActive((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName502.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes502, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContest((com.ext.portlet.model.Contest) arguments[0]);
        }

        if (_methodName503.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes503, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContest(((Long) arguments[0]).longValue());
        }

        if (_methodName504.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes504, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(((Long) arguments[0]).longValue());
        }

        if (_methodName505.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes505, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName506.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes506, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndPhaseType(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName507.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes507, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getActivePhaseForContest((com.ext.portlet.model.Contest) arguments[0]);
        }

        if (_methodName508.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes508, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContest((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName509.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes509, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getName((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName510.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes510, parameterTypes)) {
            ContestPhaseLocalServiceUtil.promoteProposal(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue());

            return null;
        }

        if (_methodName511.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes511, parameterTypes)) {
            ContestPhaseLocalServiceUtil.autoPromoteProposals();

            return null;
        }

        if (_methodName512.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes512, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.createFromContestPhase((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName513.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes513, parameterTypes)) {
            ContestPhaseLocalServiceUtil.forcePromotionOfProposalInPhase((com.ext.portlet.model.Proposal) arguments[0],
                (com.ext.portlet.model.ContestPhase) arguments[1]);

            return null;
        }

        if (_methodName514.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes514, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getNumberOfProposalsForJudge((com.liferay.portal.model.User) arguments[0],
                (com.ext.portlet.model.ContestPhase) arguments[1]);
        }

        if (_methodName515.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes515, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestPhaseLinkUrl((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
