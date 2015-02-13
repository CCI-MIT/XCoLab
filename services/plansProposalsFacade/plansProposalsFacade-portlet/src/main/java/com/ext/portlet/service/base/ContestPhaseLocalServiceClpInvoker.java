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
    private String _methodName588;
    private String[] _methodParameterTypes588;
    private String _methodName589;
    private String[] _methodParameterTypes589;
    private String _methodName594;
    private String[] _methodParameterTypes594;
    private String _methodName595;
    private String[] _methodParameterTypes595;
    private String _methodName596;
    private String[] _methodParameterTypes596;
    private String _methodName597;
    private String[] _methodParameterTypes597;
    private String _methodName598;
    private String[] _methodParameterTypes598;
    private String _methodName599;
    private String[] _methodParameterTypes599;
    private String _methodName600;
    private String[] _methodParameterTypes600;
    private String _methodName601;
    private String[] _methodParameterTypes601;
    private String _methodName602;
    private String[] _methodParameterTypes602;
    private String _methodName603;
    private String[] _methodParameterTypes603;
    private String _methodName604;
    private String[] _methodParameterTypes604;
    private String _methodName605;
    private String[] _methodParameterTypes605;
    private String _methodName606;
    private String[] _methodParameterTypes606;
    private String _methodName607;
    private String[] _methodParameterTypes607;
    private String _methodName608;
    private String[] _methodParameterTypes608;
    private String _methodName609;
    private String[] _methodParameterTypes609;
    private String _methodName610;
    private String[] _methodParameterTypes610;
    private String _methodName614;
    private String[] _methodParameterTypes614;
    private String _methodName615;
    private String[] _methodParameterTypes615;

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

        _methodName588 = "getBeanIdentifier";

        _methodParameterTypes588 = new String[] {  };

        _methodName589 = "setBeanIdentifier";

        _methodParameterTypes589 = new String[] { "java.lang.String" };

        _methodName594 = "overrideClock";

        _methodParameterTypes594 = new String[] { "org.xcolab.utils.Clock" };

        _methodName595 = "getPlans";

        _methodParameterTypes595 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName596 = "getContestStatus";

        _methodParameterTypes596 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName597 = "getContestStatusStr";

        _methodParameterTypes597 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName598 = "getPhaseColumns";

        _methodParameterTypes598 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName599 = "getPhaseColumnsRaw";

        _methodParameterTypes599 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName600 = "getPreviousPhases";

        _methodParameterTypes600 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName601 = "getNextContestPhase";

        _methodParameterTypes601 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName602 = "getPhaseActive";

        _methodParameterTypes602 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName603 = "getPhasesForContest";

        _methodParameterTypes603 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName604 = "getPhasesForContest";

        _methodParameterTypes604 = new String[] { "long" };

        _methodName605 = "getActivePhaseForContest";

        _methodParameterTypes605 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName606 = "getContest";

        _methodParameterTypes606 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName607 = "getName";

        _methodParameterTypes607 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName608 = "promoteProposal";

        _methodParameterTypes608 = new String[] { "long", "long" };

        _methodName609 = "promoteProposal";

        _methodParameterTypes609 = new String[] { "long", "long", "long" };

        _methodName610 = "autoPromoteProposals";

        _methodParameterTypes610 = new String[] {  };

        _methodName614 = "forcePromotionOfProposalInPhase";

        _methodParameterTypes614 = new String[] {
                "com.ext.portlet.model.Proposal",
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName615 = "getNumberOfProposalsForJudge";

        _methodParameterTypes615 = new String[] {
                "com.liferay.portal.model.User",
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

        if (_methodName588.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes588, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName589.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes589, parameterTypes)) {
            ContestPhaseLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName594.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes594, parameterTypes)) {
            ContestPhaseLocalServiceUtil.overrideClock((org.xcolab.utils.Clock) arguments[0]);

            return null;
        }

        if (_methodName595.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes595, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPlans((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName596.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes596, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestStatus((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName597.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes597, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestStatusStr((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName598.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes598, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseColumns((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName599.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes599, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseColumnsRaw((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName600.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes600, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPreviousPhases((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName601.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes601, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getNextContestPhase((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName602.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes602, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseActive((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName603.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes603, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContest((com.ext.portlet.model.Contest) arguments[0]);
        }

        if (_methodName604.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes604, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContest(((Long) arguments[0]).longValue());
        }

        if (_methodName605.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes605, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getActivePhaseForContest((com.ext.portlet.model.Contest) arguments[0]);
        }

        if (_methodName606.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes606, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContest((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName607.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes607, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getName((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName608.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes608, parameterTypes)) {
            ContestPhaseLocalServiceUtil.promoteProposal(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());

            return null;
        }

        if (_methodName609.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes609, parameterTypes)) {
            ContestPhaseLocalServiceUtil.promoteProposal(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue());

            return null;
        }

        if (_methodName610.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes610, parameterTypes)) {
            ContestPhaseLocalServiceUtil.autoPromoteProposals();

            return null;
        }

        if (_methodName614.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes614, parameterTypes)) {
            ContestPhaseLocalServiceUtil.forcePromotionOfProposalInPhase((com.ext.portlet.model.Proposal) arguments[0],
                (com.ext.portlet.model.ContestPhase) arguments[1]);

            return null;
        }

        if (_methodName615.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes615, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getNumberOfProposalsForJudge((com.liferay.portal.model.User) arguments[0],
                (com.ext.portlet.model.ContestPhase) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
