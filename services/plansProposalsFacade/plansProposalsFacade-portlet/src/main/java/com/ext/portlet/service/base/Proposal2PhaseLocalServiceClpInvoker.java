package com.ext.portlet.service.base;

import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Proposal2PhaseLocalServiceClpInvoker {
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
    private String _methodName504;
    private String[] _methodParameterTypes504;
    private String _methodName505;
    private String[] _methodParameterTypes505;
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
    private String _methodName516;
    private String[] _methodParameterTypes516;
    private String _methodName517;
    private String[] _methodParameterTypes517;
    private String _methodName518;
    private String[] _methodParameterTypes518;
    private String _methodName519;
    private String[] _methodParameterTypes519;
    private String _methodName520;
    private String[] _methodParameterTypes520;
    private String _methodName521;
    private String[] _methodParameterTypes521;
    private String _methodName522;
    private String[] _methodParameterTypes522;
    private String _methodName523;
    private String[] _methodParameterTypes523;

    public Proposal2PhaseLocalServiceClpInvoker() {
        _methodName0 = "addProposal2Phase";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.Proposal2Phase"
            };

        _methodName1 = "createProposal2Phase";

        _methodParameterTypes1 = new String[] {
                "com.ext.portlet.service.persistence.Proposal2PhasePK"
            };

        _methodName2 = "deleteProposal2Phase";

        _methodParameterTypes2 = new String[] {
                "com.ext.portlet.service.persistence.Proposal2PhasePK"
            };

        _methodName3 = "deleteProposal2Phase";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.Proposal2Phase"
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

        _methodName10 = "fetchProposal2Phase";

        _methodParameterTypes10 = new String[] {
                "com.ext.portlet.service.persistence.Proposal2PhasePK"
            };

        _methodName11 = "getProposal2Phase";

        _methodParameterTypes11 = new String[] {
                "com.ext.portlet.service.persistence.Proposal2PhasePK"
            };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getProposal2Phases";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getProposal2PhasesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateProposal2Phase";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.Proposal2Phase"
            };

        _methodName504 = "getBeanIdentifier";

        _methodParameterTypes504 = new String[] {  };

        _methodName505 = "setBeanIdentifier";

        _methodParameterTypes505 = new String[] { "java.lang.String" };

        _methodName510 = "create";

        _methodParameterTypes510 = new String[] { "long", "long" };

        _methodName511 = "create";

        _methodParameterTypes511 = new String[] { "long", "long", "int", "int" };

        _methodName512 = "getByProposalIdContestPhaseId";

        _methodParameterTypes512 = new String[] { "long", "long" };

        _methodName513 = "getByProposalId";

        _methodParameterTypes513 = new String[] { "long" };

        _methodName514 = "getLatestProposalVersionInActiveContest";

        _methodParameterTypes514 = new String[] { "java.lang.Long" };

        _methodName515 = "getLatestContestPhaseInContest";

        _methodParameterTypes515 = new String[] { "java.lang.Long" };

        _methodName516 = "getCurrentContestForProposal";

        _methodParameterTypes516 = new String[] { "long" };

        _methodName517 = "isContestPhaseOfProposal2PhaseValidInContest";

        _methodParameterTypes517 = new String[] {
                "com.ext.portlet.model.Proposal2Phase"
            };

        _methodName518 = "isContestPhaseValidInContest";

        _methodParameterTypes518 = new String[] { "long" };

        _methodName519 = "getForVersion";

        _methodParameterTypes519 = new String[] { "java.lang.Long", "int" };

        _methodName520 = "getForVersion";

        _methodParameterTypes520 = new String[] {
                "com.ext.portlet.model.ProposalVersion"
            };

        _methodName521 = "getContestPhasesForProposal";

        _methodParameterTypes521 = new String[] { "long" };

        _methodName522 = "getActiveContestPhasesForProposal";

        _methodParameterTypes522 = new String[] { "long" };

        _methodName523 = "getByContestPhaseId";

        _methodParameterTypes523 = new String[] { "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.addProposal2Phase((com.ext.portlet.model.Proposal2Phase) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.createProposal2Phase((com.ext.portlet.service.persistence.Proposal2PhasePK) arguments[0]);
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.deleteProposal2Phase((com.ext.portlet.service.persistence.Proposal2PhasePK) arguments[0]);
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.deleteProposal2Phase((com.ext.portlet.model.Proposal2Phase) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.fetchProposal2Phase((com.ext.portlet.service.persistence.Proposal2PhasePK) arguments[0]);
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getProposal2Phase((com.ext.portlet.service.persistence.Proposal2PhasePK) arguments[0]);
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getProposal2Phases(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getProposal2PhasesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.updateProposal2Phase((com.ext.portlet.model.Proposal2Phase) arguments[0]);
        }

        if (_methodName504.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes504, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName505.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes505, parameterTypes)) {
            Proposal2PhaseLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName510.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes510, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.create(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName511.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes511, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.create(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Integer) arguments[2]).intValue(),
                ((Integer) arguments[3]).intValue());
        }

        if (_methodName512.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes512, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName513.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes513, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getByProposalId(((Long) arguments[0]).longValue());
        }

        if (_methodName514.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes514, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getLatestProposalVersionInActiveContest((java.lang.Long) arguments[0]);
        }

        if (_methodName515.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes515, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getLatestContestPhaseInContest((java.lang.Long) arguments[0]);
        }

        if (_methodName516.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes516, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(((Long) arguments[0]).longValue());
        }

        if (_methodName517.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes517, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.isContestPhaseOfProposal2PhaseValidInContest((com.ext.portlet.model.Proposal2Phase) arguments[0]);
        }

        if (_methodName518.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes518, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.isContestPhaseValidInContest(((Long) arguments[0]).longValue());
        }

        if (_methodName519.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes519, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getForVersion((java.lang.Long) arguments[0],
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName520.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes520, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getForVersion((com.ext.portlet.model.ProposalVersion) arguments[0]);
        }

        if (_methodName521.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes521, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(((Long) arguments[0]).longValue());
        }

        if (_methodName522.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes522, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getActiveContestPhasesForProposal(((Long) arguments[0]).longValue());
        }

        if (_methodName523.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes523, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getByContestPhaseId(((Long) arguments[0]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
