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
    private String _methodName416;
    private String[] _methodParameterTypes416;
    private String _methodName417;
    private String[] _methodParameterTypes417;
    private String _methodName418;
    private String[] _methodParameterTypes418;
    private String _methodName419;
    private String[] _methodParameterTypes419;
    private String _methodName420;
    private String[] _methodParameterTypes420;
    private String _methodName421;
    private String[] _methodParameterTypes421;
    private String _methodName422;
    private String[] _methodParameterTypes422;
    private String _methodName423;
    private String[] _methodParameterTypes423;
    private String _methodName424;
    private String[] _methodParameterTypes424;
    private String _methodName425;
    private String[] _methodParameterTypes425;

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

        _methodName398 = "getBeanIdentifier";

        _methodParameterTypes398 = new String[] {  };

        _methodName399 = "setBeanIdentifier";

        _methodParameterTypes399 = new String[] { "java.lang.String" };

        _methodName404 = "overrideClock";

        _methodParameterTypes404 = new String[] { "org.xcolab.utils.Clock" };

        _methodName405 = "getContestStatus";

        _methodParameterTypes405 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName406 = "getContestStatusStr";

        _methodParameterTypes406 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName407 = "getPhaseColumns";

        _methodParameterTypes407 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName408 = "getPhaseColumnsRaw";

        _methodParameterTypes408 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName409 = "getPreviousPhases";

        _methodParameterTypes409 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName410 = "getNextContestPhase";

        _methodParameterTypes410 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName411 = "getPhaseActive";

        _methodParameterTypes411 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName412 = "getPhasesForContest";

        _methodParameterTypes412 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName413 = "getPhasesForContest";

        _methodParameterTypes413 = new String[] { "long" };

        _methodName414 = "getPhasesForContestScheduleId";

        _methodParameterTypes414 = new String[] { "long" };

        _methodName415 = "getPhasesForContestScheduleIdAndContest";

        _methodParameterTypes415 = new String[] { "long", "long" };

        _methodName416 = "getPhasesForContestScheduleIdAndPhaseType";

        _methodParameterTypes416 = new String[] { "long", "long" };

        _methodName417 = "getActivePhaseForContest";

        _methodParameterTypes417 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName418 = "getContest";

        _methodParameterTypes418 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName419 = "getName";

        _methodParameterTypes419 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName420 = "promoteProposal";

        _methodParameterTypes420 = new String[] { "long", "long", "long" };

        _methodName421 = "autoPromoteProposals";

        _methodParameterTypes421 = new String[] {  };

        _methodName422 = "createFromContestPhase";

        _methodParameterTypes422 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName423 = "forcePromotionOfProposalInPhase";

        _methodParameterTypes423 = new String[] {
                "com.ext.portlet.model.Proposal",
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName424 = "getNumberOfProposalsForJudge";

        _methodParameterTypes424 = new String[] {
                "com.liferay.portal.model.User",
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName425 = "getContestPhaseLinkUrl";

        _methodParameterTypes425 = new String[] {
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

        if (_methodName398.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes398, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName399.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes399, parameterTypes)) {
            ContestPhaseLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName404.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes404, parameterTypes)) {
            ContestPhaseLocalServiceUtil.overrideClock((org.xcolab.utils.Clock) arguments[0]);

            return null;
        }

        if (_methodName405.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes405, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestStatus((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName406.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes406, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestStatusStr((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName407.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes407, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseColumns((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName408.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes408, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseColumnsRaw((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName409.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes409, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPreviousPhases((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName410.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes410, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getNextContestPhase((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName411.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes411, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseActive((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName412.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes412, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContest((com.ext.portlet.model.Contest) arguments[0]);
        }

        if (_methodName413.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes413, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContest(((Long) arguments[0]).longValue());
        }

        if (_methodName414.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes414, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(((Long) arguments[0]).longValue());
        }

        if (_methodName415.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes415, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName416.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes416, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndPhaseType(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName417.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes417, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getActivePhaseForContest((com.ext.portlet.model.Contest) arguments[0]);
        }

        if (_methodName418.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes418, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContest((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName419.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes419, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getName((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName420.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes420, parameterTypes)) {
            ContestPhaseLocalServiceUtil.promoteProposal(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue());

            return null;
        }

        if (_methodName421.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes421, parameterTypes)) {
            ContestPhaseLocalServiceUtil.autoPromoteProposals();

            return null;
        }

        if (_methodName422.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes422, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.createFromContestPhase((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName423.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes423, parameterTypes)) {
            ContestPhaseLocalServiceUtil.forcePromotionOfProposalInPhase((com.ext.portlet.model.Proposal) arguments[0],
                (com.ext.portlet.model.ContestPhase) arguments[1]);

            return null;
        }

        if (_methodName424.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes424, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getNumberOfProposalsForJudge((com.liferay.portal.model.User) arguments[0],
                (com.ext.portlet.model.ContestPhase) arguments[1]);
        }

        if (_methodName425.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes425, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestPhaseLinkUrl((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
