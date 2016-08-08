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
    private String _methodName422;
    private String[] _methodParameterTypes422;
    private String _methodName423;
    private String[] _methodParameterTypes423;
    private String _methodName428;
    private String[] _methodParameterTypes428;
    private String _methodName429;
    private String[] _methodParameterTypes429;
    private String _methodName430;
    private String[] _methodParameterTypes430;
    private String _methodName431;
    private String[] _methodParameterTypes431;
    private String _methodName432;
    private String[] _methodParameterTypes432;
    private String _methodName433;
    private String[] _methodParameterTypes433;
    private String _methodName434;
    private String[] _methodParameterTypes434;
    private String _methodName435;
    private String[] _methodParameterTypes435;
    private String _methodName436;
    private String[] _methodParameterTypes436;
    private String _methodName437;
    private String[] _methodParameterTypes437;
    private String _methodName438;
    private String[] _methodParameterTypes438;
    private String _methodName439;
    private String[] _methodParameterTypes439;
    private String _methodName440;
    private String[] _methodParameterTypes440;
    private String _methodName441;
    private String[] _methodParameterTypes441;
    private String _methodName442;
    private String[] _methodParameterTypes442;
    private String _methodName443;
    private String[] _methodParameterTypes443;
    private String _methodName444;
    private String[] _methodParameterTypes444;
    private String _methodName445;
    private String[] _methodParameterTypes445;
    private String _methodName446;
    private String[] _methodParameterTypes446;
    private String _methodName447;
    private String[] _methodParameterTypes447;
    private String _methodName448;
    private String[] _methodParameterTypes448;
    private String _methodName449;
    private String[] _methodParameterTypes449;

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

        _methodName422 = "getBeanIdentifier";

        _methodParameterTypes422 = new String[] {  };

        _methodName423 = "setBeanIdentifier";

        _methodParameterTypes423 = new String[] { "java.lang.String" };

        _methodName428 = "overrideClock";

        _methodParameterTypes428 = new String[] { "org.xcolab.utils.Clock" };

        _methodName429 = "getContestStatus";

        _methodParameterTypes429 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName430 = "getContestStatusStr";

        _methodParameterTypes430 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName431 = "getPhaseColumns";

        _methodParameterTypes431 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName432 = "getPhaseColumnsRaw";

        _methodParameterTypes432 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName433 = "getPreviousPhases";

        _methodParameterTypes433 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName434 = "getNextContestPhase";

        _methodParameterTypes434 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName435 = "getPhaseActive";

        _methodParameterTypes435 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName436 = "getPhasesForContest";

        _methodParameterTypes436 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName437 = "getPhasesForContest";

        _methodParameterTypes437 = new String[] { "long" };

        _methodName438 = "getPhasesForContestScheduleId";

        _methodParameterTypes438 = new String[] { "long" };

        _methodName439 = "getPhasesForContestScheduleIdAndContest";

        _methodParameterTypes439 = new String[] { "long", "long" };

        _methodName440 = "getPhasesForContestScheduleIdAndPhaseType";

        _methodParameterTypes440 = new String[] { "long", "long" };

        _methodName441 = "getActivePhaseForContest";

        _methodParameterTypes441 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName442 = "getContest";

        _methodParameterTypes442 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName443 = "getName";

        _methodParameterTypes443 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName444 = "promoteProposal";

        _methodParameterTypes444 = new String[] { "long", "long", "long" };

        _methodName445 = "autoPromoteProposals";

        _methodParameterTypes445 = new String[] {  };

        _methodName446 = "createFromContestPhase";

        _methodParameterTypes446 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName447 = "forcePromotionOfProposalInPhase";

        _methodParameterTypes447 = new String[] {
                "com.ext.portlet.model.Proposal",
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName448 = "getNumberOfProposalsForJudge";

        _methodParameterTypes448 = new String[] {
                "com.liferay.portal.model.User",
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName449 = "getContestPhaseLinkUrl";

        _methodParameterTypes449 = new String[] {
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

        if (_methodName422.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes422, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName423.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes423, parameterTypes)) {
            ContestPhaseLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName428.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes428, parameterTypes)) {
            ContestPhaseLocalServiceUtil.overrideClock((org.xcolab.utils.Clock) arguments[0]);

            return null;
        }

        if (_methodName429.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes429, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestStatus((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName430.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes430, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestStatusStr((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName431.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes431, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseColumns((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName432.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes432, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseColumnsRaw((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName433.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes433, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPreviousPhases((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName434.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes434, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getNextContestPhase((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName435.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes435, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseActive((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName436.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes436, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContest((com.ext.portlet.model.Contest) arguments[0]);
        }

        if (_methodName437.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes437, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContest(((Long) arguments[0]).longValue());
        }

        if (_methodName438.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes438, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(((Long) arguments[0]).longValue());
        }

        if (_methodName439.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes439, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName440.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes440, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndPhaseType(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName441.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes441, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getActivePhaseForContest((com.ext.portlet.model.Contest) arguments[0]);
        }

        if (_methodName442.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes442, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContest((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName443.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes443, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getName((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName444.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes444, parameterTypes)) {
            ContestPhaseLocalServiceUtil.promoteProposal(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue());

            return null;
        }

        if (_methodName445.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes445, parameterTypes)) {
            ContestPhaseLocalServiceUtil.autoPromoteProposals();

            return null;
        }

        if (_methodName446.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes446, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.createFromContestPhase((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName447.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes447, parameterTypes)) {
            ContestPhaseLocalServiceUtil.forcePromotionOfProposalInPhase((com.ext.portlet.model.Proposal) arguments[0],
                (com.ext.portlet.model.ContestPhase) arguments[1]);

            return null;
        }

        if (_methodName448.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes448, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getNumberOfProposalsForJudge((com.liferay.portal.model.User) arguments[0],
                (com.ext.portlet.model.ContestPhase) arguments[1]);
        }

        if (_methodName449.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes449, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestPhaseLinkUrl((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
