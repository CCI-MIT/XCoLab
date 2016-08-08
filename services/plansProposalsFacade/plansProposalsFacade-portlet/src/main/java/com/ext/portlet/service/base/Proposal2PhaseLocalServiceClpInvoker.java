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

        _methodName422 = "getBeanIdentifier";

        _methodParameterTypes422 = new String[] {  };

        _methodName423 = "setBeanIdentifier";

        _methodParameterTypes423 = new String[] { "java.lang.String" };

        _methodName428 = "create";

        _methodParameterTypes428 = new String[] { "long", "long" };

        _methodName429 = "create";

        _methodParameterTypes429 = new String[] { "long", "long", "int", "int" };

        _methodName430 = "getByProposalIdContestPhaseId";

        _methodParameterTypes430 = new String[] { "long", "long" };

        _methodName431 = "getByProposalId";

        _methodParameterTypes431 = new String[] { "long" };

        _methodName432 = "getLatestProposalVersionInActiveContest";

        _methodParameterTypes432 = new String[] { "java.lang.Long" };

        _methodName433 = "getLatestContestPhaseInContest";

        _methodParameterTypes433 = new String[] { "java.lang.Long" };

        _methodName434 = "getCurrentContestForProposal";

        _methodParameterTypes434 = new String[] { "long" };

        _methodName435 = "isContestPhaseOfProposal2PhaseValidInContest";

        _methodParameterTypes435 = new String[] {
                "com.ext.portlet.model.Proposal2Phase"
            };

        _methodName436 = "isContestPhaseValidInContest";

        _methodParameterTypes436 = new String[] { "long" };

        _methodName437 = "getForVersion";

        _methodParameterTypes437 = new String[] { "java.lang.Long", "int" };

        _methodName438 = "getForVersion";

        _methodParameterTypes438 = new String[] {
                "com.ext.portlet.model.ProposalVersion"
            };

        _methodName439 = "getContestPhasesForProposal";

        _methodParameterTypes439 = new String[] { "long" };

        _methodName440 = "getActiveContestPhasesForProposal";

        _methodParameterTypes440 = new String[] { "long" };

        _methodName441 = "getByContestPhaseId";

        _methodParameterTypes441 = new String[] { "long" };
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

        if (_methodName422.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes422, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName423.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes423, parameterTypes)) {
            Proposal2PhaseLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName428.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes428, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.create(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName429.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes429, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.create(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Integer) arguments[2]).intValue(),
                ((Integer) arguments[3]).intValue());
        }

        if (_methodName430.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes430, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName431.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes431, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getByProposalId(((Long) arguments[0]).longValue());
        }

        if (_methodName432.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes432, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getLatestProposalVersionInActiveContest((java.lang.Long) arguments[0]);
        }

        if (_methodName433.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes433, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getLatestContestPhaseInContest((java.lang.Long) arguments[0]);
        }

        if (_methodName434.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes434, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(((Long) arguments[0]).longValue());
        }

        if (_methodName435.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes435, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.isContestPhaseOfProposal2PhaseValidInContest((com.ext.portlet.model.Proposal2Phase) arguments[0]);
        }

        if (_methodName436.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes436, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.isContestPhaseValidInContest(((Long) arguments[0]).longValue());
        }

        if (_methodName437.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes437, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getForVersion((java.lang.Long) arguments[0],
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName438.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes438, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getForVersion((com.ext.portlet.model.ProposalVersion) arguments[0]);
        }

        if (_methodName439.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes439, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(((Long) arguments[0]).longValue());
        }

        if (_methodName440.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes440, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getActiveContestPhasesForProposal(((Long) arguments[0]).longValue());
        }

        if (_methodName441.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes441, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getByContestPhaseId(((Long) arguments[0]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
