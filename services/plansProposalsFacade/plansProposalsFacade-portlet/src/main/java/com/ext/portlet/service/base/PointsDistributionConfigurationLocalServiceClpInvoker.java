package com.ext.portlet.service.base;

import com.ext.portlet.service.PointsDistributionConfigurationLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PointsDistributionConfigurationLocalServiceClpInvoker {
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
    private String _methodName492;
    private String[] _methodParameterTypes492;
    private String _methodName493;
    private String[] _methodParameterTypes493;
    private String _methodName498;
    private String[] _methodParameterTypes498;
    private String _methodName499;
    private String[] _methodParameterTypes499;
    private String _methodName500;
    private String[] _methodParameterTypes500;

    public PointsDistributionConfigurationLocalServiceClpInvoker() {
        _methodName0 = "addPointsDistributionConfiguration";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.PointsDistributionConfiguration"
            };

        _methodName1 = "createPointsDistributionConfiguration";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deletePointsDistributionConfiguration";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deletePointsDistributionConfiguration";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.PointsDistributionConfiguration"
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

        _methodName10 = "fetchPointsDistributionConfiguration";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getPointsDistributionConfiguration";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getPointsDistributionConfigurations";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getPointsDistributionConfigurationsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updatePointsDistributionConfiguration";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.PointsDistributionConfiguration"
            };

        _methodName492 = "getBeanIdentifier";

        _methodParameterTypes492 = new String[] {  };

        _methodName493 = "setBeanIdentifier";

        _methodParameterTypes493 = new String[] { "java.lang.String" };

        _methodName498 = "findByProposalPointType";

        _methodParameterTypes498 = new String[] {
                "com.ext.portlet.model.Proposal",
                "com.ext.portlet.model.PointType"
            };

        _methodName499 = "removeByProposalId";

        _methodParameterTypes499 = new String[] { "long" };

        _methodName500 = "addDistributionConfiguration";

        _methodParameterTypes500 = new String[] {
                "long", "long", "java.lang.Long", "java.lang.Long", "double",
                "long"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.addPointsDistributionConfiguration((com.ext.portlet.model.PointsDistributionConfiguration) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.createPointsDistributionConfiguration(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.deletePointsDistributionConfiguration(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.deletePointsDistributionConfiguration((com.ext.portlet.model.PointsDistributionConfiguration) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.fetchPointsDistributionConfiguration(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.getPointsDistributionConfiguration(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.getPointsDistributionConfigurations(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.getPointsDistributionConfigurationsCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.updatePointsDistributionConfiguration((com.ext.portlet.model.PointsDistributionConfiguration) arguments[0]);
        }

        if (_methodName492.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes492, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName493.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes493, parameterTypes)) {
            PointsDistributionConfigurationLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName498.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes498, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.findByProposalPointType((com.ext.portlet.model.Proposal) arguments[0],
                (com.ext.portlet.model.PointType) arguments[1]);
        }

        if (_methodName499.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes499, parameterTypes)) {
            PointsDistributionConfigurationLocalServiceUtil.removeByProposalId(((Long) arguments[0]).longValue());

            return null;
        }

        if (_methodName500.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes500, parameterTypes)) {
            return PointsDistributionConfigurationLocalServiceUtil.addDistributionConfiguration(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.Long) arguments[2], (java.lang.Long) arguments[3],
                ((Double) arguments[4]).doubleValue(),
                ((Long) arguments[5]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
