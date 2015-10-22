package com.ext.portlet.service.base;

import com.ext.portlet.service.ImpactTemplateMaxFocusAreaLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ImpactTemplateMaxFocusAreaLocalServiceClpInvoker {
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

    public ImpactTemplateMaxFocusAreaLocalServiceClpInvoker() {
        _methodName0 = "addImpactTemplateMaxFocusArea";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.ImpactTemplateMaxFocusArea"
            };

        _methodName1 = "createImpactTemplateMaxFocusArea";

        _methodParameterTypes1 = new String[] {
                "com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK"
            };

        _methodName2 = "deleteImpactTemplateMaxFocusArea";

        _methodParameterTypes2 = new String[] {
                "com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK"
            };

        _methodName3 = "deleteImpactTemplateMaxFocusArea";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.ImpactTemplateMaxFocusArea"
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

        _methodName10 = "fetchImpactTemplateMaxFocusArea";

        _methodParameterTypes10 = new String[] {
                "com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK"
            };

        _methodName11 = "getImpactTemplateMaxFocusArea";

        _methodParameterTypes11 = new String[] {
                "com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK"
            };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getImpactTemplateMaxFocusAreas";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getImpactTemplateMaxFocusAreasCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateImpactTemplateMaxFocusArea";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.ImpactTemplateMaxFocusArea"
            };

        _methodName492 = "getBeanIdentifier";

        _methodParameterTypes492 = new String[] {  };

        _methodName493 = "setBeanIdentifier";

        _methodParameterTypes493 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.addImpactTemplateMaxFocusArea((com.ext.portlet.model.ImpactTemplateMaxFocusArea) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.createImpactTemplateMaxFocusArea((com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK) arguments[0]);
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.deleteImpactTemplateMaxFocusArea((com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK) arguments[0]);
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.deleteImpactTemplateMaxFocusArea((com.ext.portlet.model.ImpactTemplateMaxFocusArea) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.fetchImpactTemplateMaxFocusArea((com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK) arguments[0]);
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.getImpactTemplateMaxFocusArea((com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK) arguments[0]);
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.getImpactTemplateMaxFocusAreas(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.getImpactTemplateMaxFocusAreasCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.updateImpactTemplateMaxFocusArea((com.ext.portlet.model.ImpactTemplateMaxFocusArea) arguments[0]);
        }

        if (_methodName492.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes492, parameterTypes)) {
            return ImpactTemplateMaxFocusAreaLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName493.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes493, parameterTypes)) {
            ImpactTemplateMaxFocusAreaLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
