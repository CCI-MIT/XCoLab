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
    private String _methodName644;
    private String[] _methodParameterTypes644;
    private String _methodName645;
    private String[] _methodParameterTypes645;
    private String _methodName650;
    private String[] _methodParameterTypes650;
    private String _methodName651;
    private String[] _methodParameterTypes651;
    private String _methodName652;
    private String[] _methodParameterTypes652;
    private String _methodName653;
    private String[] _methodParameterTypes653;
    private String _methodName654;
    private String[] _methodParameterTypes654;
    private String _methodName655;
    private String[] _methodParameterTypes655;
    private String _methodName656;
    private String[] _methodParameterTypes656;
    private String _methodName657;
    private String[] _methodParameterTypes657;
    private String _methodName658;
    private String[] _methodParameterTypes658;
    private String _methodName659;
    private String[] _methodParameterTypes659;
    private String _methodName660;
    private String[] _methodParameterTypes660;
    private String _methodName661;
    private String[] _methodParameterTypes661;
    private String _methodName662;
    private String[] _methodParameterTypes662;
    private String _methodName663;
    private String[] _methodParameterTypes663;

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

        _methodName644 = "getBeanIdentifier";

        _methodParameterTypes644 = new String[] {  };

        _methodName645 = "setBeanIdentifier";

        _methodParameterTypes645 = new String[] { "java.lang.String" };

        _methodName650 = "create";

        _methodParameterTypes650 = new String[] { "long", "long" };

        _methodName651 = "create";

        _methodParameterTypes651 = new String[] { "long", "long", "int", "int" };

        _methodName652 = "getByProposalIdContestPhaseId";

        _methodParameterTypes652 = new String[] { "long", "long" };

        _methodName653 = "getByProposalId";

        _methodParameterTypes653 = new String[] { "long" };

        _methodName654 = "getLatestProposalVersionInActiveContest";

        _methodParameterTypes654 = new String[] { "java.lang.Long" };

        _methodName655 = "getLatestContestPhaseInContest";

        _methodParameterTypes655 = new String[] { "java.lang.Long" };

        _methodName656 = "getCurrentContestForProposal";

        _methodParameterTypes656 = new String[] { "long" };

        _methodName657 = "isContestPhaseOfProposal2PhaseValidInContest";

        _methodParameterTypes657 = new String[] {
                "com.ext.portlet.model.Proposal2Phase"
            };

        _methodName658 = "isContestPhaseValidInContest";

        _methodParameterTypes658 = new String[] { "long" };

        _methodName659 = "getForVersion";

        _methodParameterTypes659 = new String[] { "java.lang.Long", "int" };

        _methodName660 = "getForVersion";

        _methodParameterTypes660 = new String[] {
                "com.ext.portlet.model.ProposalVersion"
            };

        _methodName661 = "getContestPhasesForProposal";

        _methodParameterTypes661 = new String[] { "long" };

        _methodName662 = "getActiveContestPhasesForProposal";

        _methodParameterTypes662 = new String[] { "long" };

        _methodName663 = "getByContestPhaseId";

        _methodParameterTypes663 = new String[] { "long" };
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

        if (_methodName644.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes644, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName645.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes645, parameterTypes)) {
            Proposal2PhaseLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName650.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes650, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.create(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName651.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes651, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.create(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Integer) arguments[2]).intValue(),
                ((Integer) arguments[3]).intValue());
        }

        if (_methodName652.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes652, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName653.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes653, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getByProposalId(((Long) arguments[0]).longValue());
        }

        if (_methodName654.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes654, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getLatestProposalVersionInActiveContest((java.lang.Long) arguments[0]);
        }

        if (_methodName655.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes655, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getLatestContestPhaseInContest((java.lang.Long) arguments[0]);
        }

        if (_methodName656.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes656, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(((Long) arguments[0]).longValue());
        }

        if (_methodName657.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes657, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.isContestPhaseOfProposal2PhaseValidInContest((com.ext.portlet.model.Proposal2Phase) arguments[0]);
        }

        if (_methodName658.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes658, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.isContestPhaseValidInContest(((Long) arguments[0]).longValue());
        }

        if (_methodName659.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes659, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getForVersion((java.lang.Long) arguments[0],
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName660.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes660, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getForVersion((com.ext.portlet.model.ProposalVersion) arguments[0]);
        }

        if (_methodName661.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes661, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(((Long) arguments[0]).longValue());
        }

        if (_methodName662.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes662, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getActiveContestPhasesForProposal(((Long) arguments[0]).longValue());
        }

        if (_methodName663.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes663, parameterTypes)) {
            return Proposal2PhaseLocalServiceUtil.getByContestPhaseId(((Long) arguments[0]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
