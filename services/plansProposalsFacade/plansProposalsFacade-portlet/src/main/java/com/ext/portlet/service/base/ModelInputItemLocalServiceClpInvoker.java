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
    private String _methodName484;
    private String[] _methodParameterTypes484;
    private String _methodName485;
    private String[] _methodParameterTypes485;
    private String _methodName490;
    private String[] _methodParameterTypes490;
    private String _methodName491;
    private String[] _methodParameterTypes491;
    private String _methodName492;
    private String[] _methodParameterTypes492;
    private String _methodName493;
    private String[] _methodParameterTypes493;
    private String _methodName494;
    private String[] _methodParameterTypes494;
    private String _methodName495;
    private String[] _methodParameterTypes495;
    private String _methodName497;
    private String[] _methodParameterTypes497;
    private String _methodName498;
    private String[] _methodParameterTypes498;

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

        _methodName484 = "getBeanIdentifier";

        _methodParameterTypes484 = new String[] {  };

        _methodName485 = "setBeanIdentifier";

        _methodParameterTypes485 = new String[] { "java.lang.String" };

        _methodName490 = "getItemsForModel";

        _methodParameterTypes490 = new String[] {
                "edu.mit.cci.roma.client.Simulation"
            };

        _methodName491 = "getItemForMetaData";

        _methodParameterTypes491 = new String[] {
                "java.lang.Long", "edu.mit.cci.roma.client.MetaData"
            };

        _methodName492 = "getItemForGroupId";

        _methodParameterTypes492 = new String[] { "java.lang.Long" };

        _methodName493 = "getMetaData";

        _methodParameterTypes493 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
            };

        _methodName494 = "getModel";

        _methodParameterTypes494 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
            };

        _methodName495 = "getPropertyMap";

        _methodParameterTypes495 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
            };

        _methodName497 = "saveProperties";

        _methodParameterTypes497 = new String[] {
                "com.ext.portlet.model.ModelInputItem", "java.util.Map"
            };

        _methodName498 = "store";

        _methodParameterTypes498 = new String[] {
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

        if (_methodName484.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes484, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName485.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes485, parameterTypes)) {
            ModelInputItemLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName490.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes490, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getItemsForModel((edu.mit.cci.roma.client.Simulation) arguments[0]);
        }

        if (_methodName491.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes491, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getItemForMetaData((java.lang.Long) arguments[0],
                (edu.mit.cci.roma.client.MetaData) arguments[1]);
        }

        if (_methodName492.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes492, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getItemForGroupId((java.lang.Long) arguments[0]);
        }

        if (_methodName493.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes493, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getMetaData((com.ext.portlet.model.ModelInputItem) arguments[0]);
        }

        if (_methodName494.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes494, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getModel((com.ext.portlet.model.ModelInputItem) arguments[0]);
        }

        if (_methodName495.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes495, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getPropertyMap((com.ext.portlet.model.ModelInputItem) arguments[0]);
        }

        if (_methodName497.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes497, parameterTypes)) {
            ModelInputItemLocalServiceUtil.saveProperties((com.ext.portlet.model.ModelInputItem) arguments[0],
                (java.util.Map<java.lang.String, java.lang.String>) arguments[1]);

            return null;
        }

        if (_methodName498.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes498, parameterTypes)) {
            ModelInputItemLocalServiceUtil.store((com.ext.portlet.model.ModelInputItem) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
