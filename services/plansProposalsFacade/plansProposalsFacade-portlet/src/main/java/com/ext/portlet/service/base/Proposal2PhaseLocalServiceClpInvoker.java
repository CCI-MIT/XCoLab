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

        _methodName588 = "getBeanIdentifier";

        _methodParameterTypes588 = new String[] {  };

        _methodName589 = "setBeanIdentifier";

        _methodParameterTypes589 = new String[] { "java.lang.String" };

        _methodName594 = "create";

        _methodParameterTypes594 = new String[] { "long", "long" };

        _methodName595 = "create";

        _methodParameterTypes595 = new String[] { "long", "long", "int", "int" };

        _methodName596 = "getByProposalIdContestPhaseId";

        _methodParameterTypes596 = new String[] { "long", "long" };

        _methodName597 = "getByProposalId";

        _methodParameterTypes597 = new String[] { "long" };

        _methodName598 = "getCurrentContestForProposal";

        _methodParameterTypes598 = new String[] { "long" };

        _methodName599 = "getForVersion";

        _methodParameterTypes599 = new String[] {
                "com.ext.portlet.model.ProposalVersion"
            };

        _methodName600 = "getContestPhasesForProposal";

        _methodParameterTypes600 = new String[] { "long" };

        _methodName601 = "getActiveContestPhasesForProposal";

        _methodParameterTypes601 = new String[] { "long" };
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

        if (_methodName588.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes588, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName589.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes589, parameterTypes)) {
            Proposal2PhaseLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName594.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes594, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.create(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName595.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes595, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.create(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Integer) arguments[2]).intValue(),
                ((Integer) arguments[3]).intValue());
        }

        if (_methodName596.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes596, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName597.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes597, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getByProposalId(((Long) arguments[0]).longValue());
        }

        if (_methodName598.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes598, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(((Long) arguments[0]).longValue());
        }

        if (_methodName599.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes599, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getForVersion((com.ext.portlet.model.ProposalVersion) arguments[0]);
        }

        if (_methodName600.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes600, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(((Long) arguments[0]).longValue());
        }

        if (_methodName601.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes601, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getActiveContestPhasesForProposal(((Long) arguments[0]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
