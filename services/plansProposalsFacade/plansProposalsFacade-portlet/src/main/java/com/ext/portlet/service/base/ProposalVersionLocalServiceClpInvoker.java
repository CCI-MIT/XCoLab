package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalVersionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalVersionLocalServiceClpInvoker {
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

    public ProposalVersionLocalServiceClpInvoker() {
        _methodName0 = "addProposalVersion";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.ProposalVersion"
            };

        _methodName1 = "createProposalVersion";

        _methodParameterTypes1 = new String[] {
                "com.ext.portlet.service.persistence.ProposalVersionPK"
            };

        _methodName2 = "deleteProposalVersion";

        _methodParameterTypes2 = new String[] {
                "com.ext.portlet.service.persistence.ProposalVersionPK"
            };

        _methodName3 = "deleteProposalVersion";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.ProposalVersion"
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

        _methodName10 = "fetchProposalVersion";

        _methodParameterTypes10 = new String[] {
                "com.ext.portlet.service.persistence.ProposalVersionPK"
            };

        _methodName11 = "getProposalVersion";

        _methodParameterTypes11 = new String[] {
                "com.ext.portlet.service.persistence.ProposalVersionPK"
            };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getProposalVersions";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getProposalVersionsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateProposalVersion";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.ProposalVersion"
            };

        _methodName464 = "getBeanIdentifier";

        _methodParameterTypes464 = new String[] {  };

        _methodName465 = "setBeanIdentifier";

        _methodParameterTypes465 = new String[] { "java.lang.String" };

        _methodName470 = "countByProposalId";

        _methodParameterTypes470 = new String[] { "long" };

        _methodName471 = "getByProposalId";

        _methodParameterTypes471 = new String[] { "long", "int", "int" };

        _methodName472 = "getByProposalIdVersion";

        _methodParameterTypes472 = new String[] { "long", "int" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.addProposalVersion((com.ext.portlet.model.ProposalVersion) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.createProposalVersion((com.ext.portlet.service.persistence.ProposalVersionPK) arguments[0]);
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.deleteProposalVersion((com.ext.portlet.service.persistence.ProposalVersionPK) arguments[0]);
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.deleteProposalVersion((com.ext.portlet.model.ProposalVersion) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.fetchProposalVersion((com.ext.portlet.service.persistence.ProposalVersionPK) arguments[0]);
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.getProposalVersion((com.ext.portlet.service.persistence.ProposalVersionPK) arguments[0]);
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.getProposalVersions(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.getProposalVersionsCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.updateProposalVersion((com.ext.portlet.model.ProposalVersion) arguments[0]);
        }

        if (_methodName464.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes464, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName465.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes465, parameterTypes)) {
            ProposalVersionLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName470.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes470, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.countByProposalId(((Long) arguments[0]).longValue());
        }

        if (_methodName471.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes471, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.getByProposalId(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName472.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes472, parameterTypes)) {
            return ProposalVersionLocalServiceUtil.getByProposalIdVersion(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue());
        }

        throw new UnsupportedOperationException();
    }
}
