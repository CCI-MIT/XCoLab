package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelInputItemLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelInputItemLocalServiceClpInvoker {
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
    private String _methodName602;
    private String[] _methodParameterTypes602;
    private String _methodName603;
    private String[] _methodParameterTypes603;
    private String _methodName608;
    private String[] _methodParameterTypes608;
    private String _methodName609;
    private String[] _methodParameterTypes609;
    private String _methodName610;
    private String[] _methodParameterTypes610;
    private String _methodName611;
    private String[] _methodParameterTypes611;
    private String _methodName612;
    private String[] _methodParameterTypes612;
    private String _methodName613;
    private String[] _methodParameterTypes613;
    private String _methodName615;
    private String[] _methodParameterTypes615;
    private String _methodName616;
    private String[] _methodParameterTypes616;

    public ModelInputItemLocalServiceClpInvoker() {
        _methodName0 = "addModelInputItem";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
            };

        _methodName1 = "createModelInputItem";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteModelInputItem";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteModelInputItem";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
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

        _methodName10 = "fetchModelInputItem";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getModelInputItem";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getModelInputItems";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getModelInputItemsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateModelInputItem";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
            };

        _methodName602 = "getBeanIdentifier";

        _methodParameterTypes602 = new String[] {  };

        _methodName603 = "setBeanIdentifier";

        _methodParameterTypes603 = new String[] { "java.lang.String" };

        _methodName608 = "getItemsForModel";

        _methodParameterTypes608 = new String[] {
                "edu.mit.cci.roma.client.Simulation"
            };

        _methodName609 = "getItemForMetaData";

        _methodParameterTypes609 = new String[] {
                "java.lang.Long", "edu.mit.cci.roma.client.MetaData"
            };

        _methodName610 = "getItemForGroupId";

        _methodParameterTypes610 = new String[] { "java.lang.Long" };

        _methodName611 = "getMetaData";

        _methodParameterTypes611 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
            };

        _methodName612 = "getModel";

        _methodParameterTypes612 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
            };

        _methodName613 = "getPropertyMap";

        _methodParameterTypes613 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
            };

        _methodName615 = "saveProperties";

        _methodParameterTypes615 = new String[] {
                "com.ext.portlet.model.ModelInputItem", "java.util.Map"
            };

        _methodName616 = "store";

        _methodParameterTypes616 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.addModelInputItem((com.ext.portlet.model.ModelInputItem) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.createModelInputItem(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.deleteModelInputItem(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.deleteModelInputItem((com.ext.portlet.model.ModelInputItem) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.fetchModelInputItem(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getModelInputItem(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getModelInputItems(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getModelInputItemsCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.updateModelInputItem((com.ext.portlet.model.ModelInputItem) arguments[0]);
        }

        if (_methodName602.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes602, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName603.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes603, parameterTypes)) {
            ModelInputItemLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName608.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes608, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getItemsForModel((edu.mit.cci.roma.client.Simulation) arguments[0]);
        }

        if (_methodName609.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes609, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getItemForMetaData((java.lang.Long) arguments[0],
                (edu.mit.cci.roma.client.MetaData) arguments[1]);
        }

        if (_methodName610.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes610, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getItemForGroupId((java.lang.Long) arguments[0]);
        }

        if (_methodName611.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes611, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getMetaData((com.ext.portlet.model.ModelInputItem) arguments[0]);
        }

        if (_methodName612.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes612, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getModel((com.ext.portlet.model.ModelInputItem) arguments[0]);
        }

        if (_methodName613.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes613, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getPropertyMap((com.ext.portlet.model.ModelInputItem) arguments[0]);
        }

        if (_methodName615.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes615, parameterTypes)) {
            ModelInputItemLocalServiceUtil.saveProperties((com.ext.portlet.model.ModelInputItem) arguments[0],
                (java.util.Map<java.lang.String, java.lang.String>) arguments[1]);

            return null;
        }

        if (_methodName616.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes616, parameterTypes)) {
            ModelInputItemLocalServiceUtil.store((com.ext.portlet.model.ModelInputItem) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
