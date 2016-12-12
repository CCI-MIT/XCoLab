package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelCategoryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelCategoryLocalServiceClpInvoker {
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

    public ModelCategoryLocalServiceClpInvoker() {
        _methodName0 = "addModelCategory";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.ModelCategory"
            };

        _methodName1 = "createModelCategory";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteModelCategory";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteModelCategory";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.ModelCategory"
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

        _methodName10 = "fetchModelCategory";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getModelCategory";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getModelCategories";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getModelCategoriesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateModelCategory";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.ModelCategory"
            };

        _methodName350 = "getBeanIdentifier";

        _methodParameterTypes350 = new String[] {  };

        _methodName351 = "setBeanIdentifier";

        _methodParameterTypes351 = new String[] { "java.lang.String" };

        _methodName356 = "getModelPreferences";

        _methodParameterTypes356 = new String[] {
                "com.ext.portlet.model.ModelCategory"
            };

        _methodName357 = "addCategory";

        _methodParameterTypes357 = new String[] {
                "java.lang.String", "java.lang.String"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.addModelCategory((com.ext.portlet.model.ModelCategory) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.createModelCategory(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.deleteModelCategory(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.deleteModelCategory((com.ext.portlet.model.ModelCategory) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.fetchModelCategory(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.getModelCategory(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.getModelCategories(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.getModelCategoriesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.updateModelCategory((com.ext.portlet.model.ModelCategory) arguments[0]);
        }

        if (_methodName350.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes350, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName351.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes351, parameterTypes)) {
            ModelCategoryLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName356.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes356, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.getModelPreferences((com.ext.portlet.model.ModelCategory) arguments[0]);
        }

        if (_methodName357.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes357, parameterTypes)) {
            return ModelCategoryLocalServiceUtil.addCategory((java.lang.String) arguments[0],
                (java.lang.String) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
