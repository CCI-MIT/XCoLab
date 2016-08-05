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
    private String _methodName482;
    private String[] _methodParameterTypes482;
    private String _methodName483;
    private String[] _methodParameterTypes483;
    private String _methodName484;
    private String[] _methodParameterTypes484;
    private String _methodName485;
    private String[] _methodParameterTypes485;
    private String _methodName486;
    private String[] _methodParameterTypes486;
    private String _methodName487;
    private String[] _methodParameterTypes487;
    private String _methodName488;
    private String[] _methodParameterTypes488;
    private String _methodName489;
    private String[] _methodParameterTypes489;
    private String _methodName490;
    private String[] _methodParameterTypes490;
    private String _methodName491;
    private String[] _methodParameterTypes491;

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

        _methodName464 = "getBeanIdentifier";

        _methodParameterTypes464 = new String[] {  };

        _methodName465 = "setBeanIdentifier";

        _methodParameterTypes465 = new String[] { "java.lang.String" };

        _methodName470 = "overrideClock";

        _methodParameterTypes470 = new String[] { "org.xcolab.utils.Clock" };

        _methodName471 = "getContestStatus";

        _methodParameterTypes471 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName472 = "getContestStatusStr";

        _methodParameterTypes472 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName473 = "getPhaseColumns";

        _methodParameterTypes473 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName474 = "getPhaseColumnsRaw";

        _methodParameterTypes474 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName475 = "getPreviousPhases";

        _methodParameterTypes475 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName476 = "getNextContestPhase";

        _methodParameterTypes476 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName477 = "getPhaseActive";

        _methodParameterTypes477 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName478 = "getPhasesForContest";

        _methodParameterTypes478 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName479 = "getPhasesForContest";

        _methodParameterTypes479 = new String[] { "long" };

        _methodName480 = "getPhasesForContestScheduleId";

        _methodParameterTypes480 = new String[] { "long" };

        _methodName481 = "getPhasesForContestScheduleIdAndContest";

        _methodParameterTypes481 = new String[] { "long", "long" };

        _methodName482 = "getPhasesForContestScheduleIdAndPhaseType";

        _methodParameterTypes482 = new String[] { "long", "long" };

        _methodName483 = "getActivePhaseForContest";

        _methodParameterTypes483 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName484 = "getContest";

        _methodParameterTypes484 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName485 = "getName";

        _methodParameterTypes485 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName486 = "promoteProposal";

        _methodParameterTypes486 = new String[] { "long", "long", "long" };

        _methodName487 = "autoPromoteProposals";

        _methodParameterTypes487 = new String[] {  };

        _methodName488 = "createFromContestPhase";

        _methodParameterTypes488 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName489 = "forcePromotionOfProposalInPhase";

        _methodParameterTypes489 = new String[] {
                "com.ext.portlet.model.Proposal",
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName490 = "getNumberOfProposalsForJudge";

        _methodParameterTypes490 = new String[] {
                "com.liferay.portal.model.User",
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName491 = "getContestPhaseLinkUrl";

        _methodParameterTypes491 = new String[] {
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

        if (_methodName464.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes464, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName465.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes465, parameterTypes)) {
            ContestPhaseLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName470.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes470, parameterTypes)) {
            ContestPhaseLocalServiceUtil.overrideClock((org.xcolab.utils.Clock) arguments[0]);

            return null;
        }

        if (_methodName471.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes471, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestStatus((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName472.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes472, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestStatusStr((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName473.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes473, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseColumns((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName474.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes474, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseColumnsRaw((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName475.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes475, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPreviousPhases((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName476.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes476, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getNextContestPhase((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName477.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes477, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseActive((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName478.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes478, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContest((com.ext.portlet.model.Contest) arguments[0]);
        }

        if (_methodName479.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes479, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContest(((Long) arguments[0]).longValue());
        }

        if (_methodName480.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes480, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(((Long) arguments[0]).longValue());
        }

        if (_methodName481.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes481, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName482.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes482, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndPhaseType(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName483.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes483, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getActivePhaseForContest((com.ext.portlet.model.Contest) arguments[0]);
        }

        if (_methodName484.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes484, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContest((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName485.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes485, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getName((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName486.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes486, parameterTypes)) {
            ContestPhaseLocalServiceUtil.promoteProposal(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue());

            return null;
        }

        if (_methodName487.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes487, parameterTypes)) {
            ContestPhaseLocalServiceUtil.autoPromoteProposals();

            return null;
        }

        if (_methodName488.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes488, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.createFromContestPhase((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName489.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes489, parameterTypes)) {
            ContestPhaseLocalServiceUtil.forcePromotionOfProposalInPhase((com.ext.portlet.model.Proposal) arguments[0],
                (com.ext.portlet.model.ContestPhase) arguments[1]);

            return null;
        }

        if (_methodName490.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes490, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getNumberOfProposalsForJudge((com.liferay.portal.model.User) arguments[0],
                (com.ext.portlet.model.ContestPhase) arguments[1]);
        }

        if (_methodName491.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes491, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestPhaseLinkUrl((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
