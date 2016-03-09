package com.ext.portlet.service.base;

import com.ext.portlet.service.ConfigurationAttributeLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ConfigurationAttributeLocalServiceClpInvoker {
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
    private String _methodName524;
    private String[] _methodParameterTypes524;
    private String _methodName525;
    private String[] _methodParameterTypes525;
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
    private String _methodName538;
    private String[] _methodParameterTypes538;

    public ConfigurationAttributeLocalServiceClpInvoker() {
        _methodName0 = "addConfigurationAttribute";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.ConfigurationAttribute"
            };

        _methodName1 = "createConfigurationAttribute";

        _methodParameterTypes1 = new String[] {
                "com.ext.portlet.service.persistence.ConfigurationAttributePK"
            };

        _methodName2 = "deleteConfigurationAttribute";

        _methodParameterTypes2 = new String[] {
                "com.ext.portlet.service.persistence.ConfigurationAttributePK"
            };

        _methodName3 = "deleteConfigurationAttribute";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.ConfigurationAttribute"
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

        _methodName10 = "fetchConfigurationAttribute";

        _methodParameterTypes10 = new String[] {
                "com.ext.portlet.service.persistence.ConfigurationAttributePK"
            };

        _methodName11 = "getConfigurationAttribute";

        _methodParameterTypes11 = new String[] {
                "com.ext.portlet.service.persistence.ConfigurationAttributePK"
            };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getConfigurationAttributes";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getConfigurationAttributesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateConfigurationAttribute";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.ConfigurationAttribute"
            };

        _methodName524 = "getBeanIdentifier";

        _methodParameterTypes524 = new String[] {  };

        _methodName525 = "setBeanIdentifier";

        _methodParameterTypes525 = new String[] { "java.lang.String" };

        _methodName530 = "getByAttributeName";

        _methodParameterTypes530 = new String[] { "java.lang.String" };

        _methodName531 = "getByAttributeNameAdditionalId";

        _methodParameterTypes531 = new String[] { "java.lang.String", "long" };

        _methodName532 = "getAttributeStringValue";

        _methodParameterTypes532 = new String[] {
                "java.lang.String", "long", "java.lang.String"
            };

        _methodName533 = "getAttributeLongValue";

        _methodParameterTypes533 = new String[] {
                "java.lang.String", "long", "long"
            };

        _methodName534 = "getAttributeDoubleValue";

        _methodParameterTypes534 = new String[] {
                "java.lang.String", "long", "double"
            };

        _methodName535 = "getAttributeStringValue";

        _methodParameterTypes535 = new String[] { "java.lang.String", "long" };

        _methodName536 = "getAttributeLongValue";

        _methodParameterTypes536 = new String[] { "java.lang.String", "long" };

        _methodName537 = "getAttributeBooleanValue";

        _methodParameterTypes537 = new String[] { "java.lang.String", "long" };

        _methodName538 = "getAttributeDoubleValue";

        _methodParameterTypes538 = new String[] { "java.lang.String", "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.addConfigurationAttribute((com.ext.portlet.model.ConfigurationAttribute) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.createConfigurationAttribute((com.ext.portlet.service.persistence.ConfigurationAttributePK) arguments[0]);
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.deleteConfigurationAttribute((com.ext.portlet.service.persistence.ConfigurationAttributePK) arguments[0]);
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.deleteConfigurationAttribute((com.ext.portlet.model.ConfigurationAttribute) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.fetchConfigurationAttribute((com.ext.portlet.service.persistence.ConfigurationAttributePK) arguments[0]);
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.getConfigurationAttribute((com.ext.portlet.service.persistence.ConfigurationAttributePK) arguments[0]);
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.getConfigurationAttributes(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.getConfigurationAttributesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.updateConfigurationAttribute((com.ext.portlet.model.ConfigurationAttribute) arguments[0]);
        }

        if (_methodName524.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes524, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName525.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes525, parameterTypes)) {
            ConfigurationAttributeLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName530.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes530, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.getByAttributeName((java.lang.String) arguments[0]);
        }

        if (_methodName531.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes531, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.getByAttributeNameAdditionalId((java.lang.String) arguments[0],
                ((Long) arguments[1]).longValue());
        }

        if (_methodName532.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes532, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.getAttributeStringValue((java.lang.String) arguments[0],
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName533.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes533, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.getAttributeLongValue((java.lang.String) arguments[0],
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue());
        }

        if (_methodName534.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes534, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.getAttributeDoubleValue((java.lang.String) arguments[0],
                ((Long) arguments[1]).longValue(),
                ((Double) arguments[2]).doubleValue());
        }

        if (_methodName535.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes535, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.getAttributeStringValue((java.lang.String) arguments[0],
                ((Long) arguments[1]).longValue());
        }

        if (_methodName536.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes536, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.getAttributeLongValue((java.lang.String) arguments[0],
                ((Long) arguments[1]).longValue());
        }

        if (_methodName537.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes537, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.getAttributeBooleanValue((java.lang.String) arguments[0],
                ((Long) arguments[1]).longValue());
        }

        if (_methodName538.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes538, parameterTypes)) {
            return ConfigurationAttributeLocalServiceUtil.getAttributeDoubleValue((java.lang.String) arguments[0],
                ((Long) arguments[1]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
