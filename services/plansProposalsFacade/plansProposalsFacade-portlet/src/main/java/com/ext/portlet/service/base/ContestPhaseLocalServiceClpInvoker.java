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
    private String _methodName658;
    private String[] _methodParameterTypes658;
    private String _methodName659;
    private String[] _methodParameterTypes659;
    private String _methodName664;
    private String[] _methodParameterTypes664;
    private String _methodName665;
    private String[] _methodParameterTypes665;
    private String _methodName666;
    private String[] _methodParameterTypes666;
    private String _methodName667;
    private String[] _methodParameterTypes667;
    private String _methodName668;
    private String[] _methodParameterTypes668;
    private String _methodName669;
    private String[] _methodParameterTypes669;
    private String _methodName670;
    private String[] _methodParameterTypes670;
    private String _methodName671;
    private String[] _methodParameterTypes671;
    private String _methodName672;
    private String[] _methodParameterTypes672;
    private String _methodName673;
    private String[] _methodParameterTypes673;
    private String _methodName674;
    private String[] _methodParameterTypes674;
    private String _methodName675;
    private String[] _methodParameterTypes675;
    private String _methodName676;
    private String[] _methodParameterTypes676;
    private String _methodName677;
    private String[] _methodParameterTypes677;
    private String _methodName678;
    private String[] _methodParameterTypes678;
    private String _methodName679;
    private String[] _methodParameterTypes679;
    private String _methodName680;
    private String[] _methodParameterTypes680;
    private String _methodName681;
    private String[] _methodParameterTypes681;
    private String _methodName682;
    private String[] _methodParameterTypes682;
    private String _methodName683;
    private String[] _methodParameterTypes683;
    private String _methodName684;
    private String[] _methodParameterTypes684;
    private String _methodName685;
    private String[] _methodParameterTypes685;

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

        _methodName658 = "getBeanIdentifier";

        _methodParameterTypes658 = new String[] {  };

        _methodName659 = "setBeanIdentifier";

        _methodParameterTypes659 = new String[] { "java.lang.String" };

        _methodName664 = "overrideClock";

        _methodParameterTypes664 = new String[] { "org.xcolab.utils.Clock" };

        _methodName665 = "getPlans";

        _methodParameterTypes665 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName666 = "getContestStatus";

        _methodParameterTypes666 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName667 = "getContestStatusStr";

        _methodParameterTypes667 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName668 = "getPhaseColumns";

        _methodParameterTypes668 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName669 = "getPhaseColumnsRaw";

        _methodParameterTypes669 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName670 = "getPreviousPhases";

        _methodParameterTypes670 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName671 = "getNextContestPhase";

        _methodParameterTypes671 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName672 = "getPhaseActive";

        _methodParameterTypes672 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName673 = "getPhasesForContest";

        _methodParameterTypes673 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName674 = "getPhasesForContest";

        _methodParameterTypes674 = new String[] { "long" };

        _methodName675 = "getPhasesForContestScheduleId";

        _methodParameterTypes675 = new String[] { "long" };

        _methodName676 = "getPhasesForContestScheduleIdAndContest";

        _methodParameterTypes676 = new String[] { "long", "long" };

        _methodName677 = "getPhasesForContestScheduleIdAndPhaseType";

        _methodParameterTypes677 = new String[] { "long", "long" };

        _methodName678 = "getActivePhaseForContest";

        _methodParameterTypes678 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName679 = "getContest";

        _methodParameterTypes679 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName680 = "getName";

        _methodParameterTypes680 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName681 = "promoteProposal";

        _methodParameterTypes681 = new String[] { "long", "long", "long" };

        _methodName682 = "autoPromoteProposals";

        _methodParameterTypes682 = new String[] {  };

        _methodName683 = "createFromContestPhase";

        _methodParameterTypes683 = new String[] {
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName684 = "forcePromotionOfProposalInPhase";

        _methodParameterTypes684 = new String[] {
                "com.ext.portlet.model.Proposal",
                "com.ext.portlet.model.ContestPhase"
            };

        _methodName685 = "getNumberOfProposalsForJudge";

        _methodParameterTypes685 = new String[] {
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

        if (_methodName658.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes658, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName659.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes659, parameterTypes)) {
            ContestPhaseLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName664.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes664, parameterTypes)) {
            ContestPhaseLocalServiceUtil.overrideClock((org.xcolab.utils.Clock) arguments[0]);

            return null;
        }

        if (_methodName665.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes665, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPlans((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName666.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes666, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestStatus((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName667.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes667, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContestStatusStr((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName668.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes668, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseColumns((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName669.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes669, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseColumnsRaw((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName670.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes670, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPreviousPhases((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName671.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes671, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getNextContestPhase((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName672.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes672, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhaseActive((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName673.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes673, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContest((com.ext.portlet.model.Contest) arguments[0]);
        }

        if (_methodName674.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes674, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContest(((Long) arguments[0]).longValue());
        }

        if (_methodName675.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes675, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(((Long) arguments[0]).longValue());
        }

        if (_methodName676.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes676, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName677.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes677, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndPhaseType(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName678.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes678, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getActivePhaseForContest((com.ext.portlet.model.Contest) arguments[0]);
        }

        if (_methodName679.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes679, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getContest((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName680.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes680, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getName((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName681.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes681, parameterTypes)) {
            ContestPhaseLocalServiceUtil.promoteProposal(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue());

            return null;
        }

        if (_methodName682.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes682, parameterTypes)) {
            ContestPhaseLocalServiceUtil.autoPromoteProposals();

            return null;
        }

        if (_methodName683.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes683, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.createFromContestPhase((com.ext.portlet.model.ContestPhase) arguments[0]);
        }

        if (_methodName684.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes684, parameterTypes)) {
            ContestPhaseLocalServiceUtil.forcePromotionOfProposalInPhase((com.ext.portlet.model.Proposal) arguments[0],
                (com.ext.portlet.model.ContestPhase) arguments[1]);

            return null;
        }

        if (_methodName685.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes685, parameterTypes)) {
            return ContestPhaseLocalServiceUtil.getNumberOfProposalsForJudge((com.liferay.portal.model.User) arguments[0],
                (com.ext.portlet.model.ContestPhase) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
