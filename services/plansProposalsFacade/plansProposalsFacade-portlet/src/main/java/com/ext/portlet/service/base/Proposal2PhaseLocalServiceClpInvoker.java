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
    private String _methodName518;
    private String[] _methodParameterTypes518;
    private String _methodName519;
    private String[] _methodParameterTypes519;
    private String _methodName524;
    private String[] _methodParameterTypes524;
    private String _methodName525;
    private String[] _methodParameterTypes525;
    private String _methodName526;
    private String[] _methodParameterTypes526;
    private String _methodName527;
    private String[] _methodParameterTypes527;
    private String _methodName528;
    private String[] _methodParameterTypes528;
    private String _methodName529;
    private String[] _methodParameterTypes529;
    private String _methodName530;
    private String[] _methodParameterTypes530;
    private String _methodName531;
    private String[] _methodParameterTypes531;
    private String _methodName532;
    private String[] _methodParameterTypes532;
    private String _methodName533;
    private String[] _methodParameterTypes533;
    private String _methodName534;
    private String[] _methodParameterTypes534;
    private String _methodName535;
    private String[] _methodParameterTypes535;
    private String _methodName536;
    private String[] _methodParameterTypes536;
    private String _methodName537;
    private String[] _methodParameterTypes537;

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

        _methodName518 = "getBeanIdentifier";

        _methodParameterTypes518 = new String[] {  };

        _methodName519 = "setBeanIdentifier";

        _methodParameterTypes519 = new String[] { "java.lang.String" };

        _methodName524 = "create";

        _methodParameterTypes524 = new String[] { "long", "long" };

        _methodName525 = "create";

        _methodParameterTypes525 = new String[] { "long", "long", "int", "int" };

        _methodName526 = "getByProposalIdContestPhaseId";

        _methodParameterTypes526 = new String[] { "long", "long" };

        _methodName527 = "getByProposalId";

        _methodParameterTypes527 = new String[] { "long" };

        _methodName528 = "getLatestProposalVersionInActiveContest";

        _methodParameterTypes528 = new String[] { "java.lang.Long" };

        _methodName529 = "getLatestContestPhaseInContest";

        _methodParameterTypes529 = new String[] { "java.lang.Long" };

        _methodName530 = "getCurrentContestForProposal";

        _methodParameterTypes530 = new String[] { "long" };

        _methodName531 = "isContestPhaseOfProposal2PhaseValidInContest";

        _methodParameterTypes531 = new String[] {
                "com.ext.portlet.model.Proposal2Phase"
            };

        _methodName532 = "isContestPhaseValidInContest";

        _methodParameterTypes532 = new String[] { "long" };

        _methodName533 = "getForVersion";

        _methodParameterTypes533 = new String[] { "java.lang.Long", "int" };

        _methodName534 = "getForVersion";

        _methodParameterTypes534 = new String[] {
                "com.ext.portlet.model.ProposalVersion"
            };

        _methodName535 = "getContestPhasesForProposal";

        _methodParameterTypes535 = new String[] { "long" };

        _methodName536 = "getActiveContestPhasesForProposal";

        _methodParameterTypes536 = new String[] { "long" };

        _methodName537 = "getByContestPhaseId";

        _methodParameterTypes537 = new String[] { "long" };
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

        if (_methodName518.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes518, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName519.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes519, parameterTypes)) {
            Proposal2PhaseLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName524.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes524, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.create(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName525.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes525, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.create(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Integer) arguments[2]).intValue(),
                ((Integer) arguments[3]).intValue());
        }

        if (_methodName526.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes526, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName527.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes527, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getByProposalId(((Long) arguments[0]).longValue());
        }

        if (_methodName528.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes528, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getLatestProposalVersionInActiveContest((java.lang.Long) arguments[0]);
        }

        if (_methodName529.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes529, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getLatestContestPhaseInContest((java.lang.Long) arguments[0]);
        }

        if (_methodName530.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes530, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(((Long) arguments[0]).longValue());
        }

        if (_methodName531.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes531, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.isContestPhaseOfProposal2PhaseValidInContest((com.ext.portlet.model.Proposal2Phase) arguments[0]);
        }

        if (_methodName532.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes532, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.isContestPhaseValidInContest(((Long) arguments[0]).longValue());
        }

        if (_methodName533.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes533, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getForVersion((java.lang.Long) arguments[0],
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName534.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes534, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getForVersion((com.ext.portlet.model.ProposalVersion) arguments[0]);
        }

        if (_methodName535.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes535, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(((Long) arguments[0]).longValue());
        }

        if (_methodName536.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes536, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getActiveContestPhasesForProposal(((Long) arguments[0]).longValue());
        }

        if (_methodName537.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes537, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getByContestPhaseId(((Long) arguments[0]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
