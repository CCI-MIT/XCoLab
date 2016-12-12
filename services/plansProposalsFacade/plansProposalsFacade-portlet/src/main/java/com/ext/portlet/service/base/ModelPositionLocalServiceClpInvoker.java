package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelPositionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelPositionLocalServiceClpInvoker {
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
    private String _methodName350;
    private String[] _methodParameterTypes350;
    private String _methodName351;
    private String[] _methodParameterTypes351;
    private String _methodName356;
    private String[] _methodParameterTypes356;
    private String _methodName357;
    private String[] _methodParameterTypes357;

    public ModelPositionLocalServiceClpInvoker() {
        _methodName0 = "addModelPosition";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.ModelPosition"
            };

        _methodName1 = "createModelPosition";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteModelPosition";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteModelPosition";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.ModelPosition"
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

        _methodName10 = "fetchModelPosition";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getModelPosition";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getModelPositions";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getModelPositionsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateModelPosition";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.ModelPosition"
            };

        _methodName350 = "getBeanIdentifier";

        _methodParameterTypes350 = new String[] {  };

        _methodName351 = "setBeanIdentifier";

        _methodParameterTypes351 = new String[] { "java.lang.String" };

        _methodName356 = "getModelPositionsByModelId";

        _methodParameterTypes356 = new String[] { "java.lang.Long" };

        _methodName357 = "setModelPositions";

        _methodParameterTypes357 = new String[] {
                "java.lang.Long", "java.util.List"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ModelPositionLocalServiceUtil.addModelPosition((com.ext.portlet.model.ModelPosition) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ModelPositionLocalServiceUtil.createModelPosition(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ModelPositionLocalServiceUtil.deleteModelPosition(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ModelPositionLocalServiceUtil.deleteModelPosition((com.ext.portlet.model.ModelPosition) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ModelPositionLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ModelPositionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ModelPositionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ModelPositionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ModelPositionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ModelPositionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ModelPositionLocalServiceUtil.fetchModelPosition(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ModelPositionLocalServiceUtil.getModelPosition(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ModelPositionLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ModelPositionLocalServiceUtil.getModelPositions(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ModelPositionLocalServiceUtil.getModelPositionsCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ModelPositionLocalServiceUtil.updateModelPosition((com.ext.portlet.model.ModelPosition) arguments[0]);
        }

        if (_methodName350.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes350, parameterTypes)) {
            return ModelPositionLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName351.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes351, parameterTypes)) {
            ModelPositionLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName356.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes356, parameterTypes)) {
            return ModelPositionLocalServiceUtil.getModelPositionsByModelId((java.lang.Long) arguments[0]);
        }

        if (_methodName357.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes357, parameterTypes)) {
            ModelPositionLocalServiceUtil.setModelPositions((java.lang.Long) arguments[0],
                (java.util.List<java.lang.Long>) arguments[1]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
