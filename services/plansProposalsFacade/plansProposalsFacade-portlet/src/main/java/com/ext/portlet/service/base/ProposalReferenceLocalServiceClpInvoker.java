package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalReferenceLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalReferenceLocalServiceClpInvoker {
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

    public ProposalReferenceLocalServiceClpInvoker() {
        _methodName0 = "addProposalReference";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.ProposalReference"
            };

        _methodName1 = "createProposalReference";

        _methodParameterTypes1 = new String[] {
                "com.ext.portlet.service.persistence.ProposalReferencePK"
            };

        _methodName2 = "deleteProposalReference";

        _methodParameterTypes2 = new String[] {
                "com.ext.portlet.service.persistence.ProposalReferencePK"
            };

        _methodName3 = "deleteProposalReference";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.ProposalReference"
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

        _methodName10 = "fetchProposalReference";

        _methodParameterTypes10 = new String[] {
                "com.ext.portlet.service.persistence.ProposalReferencePK"
            };

        _methodName11 = "getProposalReference";

        _methodParameterTypes11 = new String[] {
                "com.ext.portlet.service.persistence.ProposalReferencePK"
            };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getProposalReferences";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getProposalReferencesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateProposalReference";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.ProposalReference"
            };

        _methodName398 = "getBeanIdentifier";

        _methodParameterTypes398 = new String[] {  };

        _methodName399 = "setBeanIdentifier";

        _methodParameterTypes399 = new String[] { "java.lang.String" };

        _methodName404 = "getByProposalId";

        _methodParameterTypes404 = new String[] { "long" };

        _methodName405 = "getBySubProposalId";

        _methodParameterTypes405 = new String[] { "long" };

        _methodName406 = "getByProposalIdSubProposalId";

        _methodParameterTypes406 = new String[] { "long", "long" };

        _methodName407 = "populateTable";

        _methodParameterTypes407 = new String[] {  };

        _methodName408 = "populateTableWithProposal";

        _methodParameterTypes408 = new String[] { "com.ext.portlet.model.Proposal" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.addProposalReference((com.ext.portlet.model.ProposalReference) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.createProposalReference((com.ext.portlet.service.persistence.ProposalReferencePK) arguments[0]);
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.deleteProposalReference((com.ext.portlet.service.persistence.ProposalReferencePK) arguments[0]);
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.deleteProposalReference((com.ext.portlet.model.ProposalReference) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.fetchProposalReference((com.ext.portlet.service.persistence.ProposalReferencePK) arguments[0]);
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.getProposalReference((com.ext.portlet.service.persistence.ProposalReferencePK) arguments[0]);
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.getProposalReferences(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.getProposalReferencesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.updateProposalReference((com.ext.portlet.model.ProposalReference) arguments[0]);
        }

        if (_methodName398.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes398, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName399.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes399, parameterTypes)) {
            ProposalReferenceLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName404.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes404, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.getByProposalId(((Long) arguments[0]).longValue());
        }

        if (_methodName405.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes405, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.getBySubProposalId(((Long) arguments[0]).longValue());
        }

        if (_methodName406.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes406, parameterTypes)) {
            return ProposalReferenceLocalServiceUtil.getByProposalIdSubProposalId(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName407.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes407, parameterTypes)) {
            ProposalReferenceLocalServiceUtil.populateTable();

            return null;
        }

        if (_methodName408.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes408, parameterTypes)) {
            ProposalReferenceLocalServiceUtil.populateTableWithProposal((com.ext.portlet.model.Proposal) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
