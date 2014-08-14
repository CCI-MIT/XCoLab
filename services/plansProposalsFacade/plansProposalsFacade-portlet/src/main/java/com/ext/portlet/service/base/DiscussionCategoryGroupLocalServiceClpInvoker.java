package com.ext.portlet.service.base;

import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DiscussionCategoryGroupLocalServiceClpInvoker {
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
    private String _methodName552;
    private String[] _methodParameterTypes552;
    private String _methodName553;
    private String[] _methodParameterTypes553;
    private String _methodName558;
    private String[] _methodParameterTypes558;
    private String _methodName559;
    private String[] _methodParameterTypes559;
    private String _methodName560;
    private String[] _methodParameterTypes560;
    private String _methodName561;
    private String[] _methodParameterTypes561;
    private String _methodName562;
    private String[] _methodParameterTypes562;
    private String _methodName563;
    private String[] _methodParameterTypes563;
    private String _methodName564;
    private String[] _methodParameterTypes564;
    private String _methodName565;
    private String[] _methodParameterTypes565;
    private String _methodName566;
    private String[] _methodParameterTypes566;
    private String _methodName567;
    private String[] _methodParameterTypes567;
    private String _methodName568;
    private String[] _methodParameterTypes568;
    private String _methodName569;
    private String[] _methodParameterTypes569;

    public DiscussionCategoryGroupLocalServiceClpInvoker() {
        _methodName0 = "addDiscussionCategoryGroup";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.DiscussionCategoryGroup"
            };

        _methodName1 = "createDiscussionCategoryGroup";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteDiscussionCategoryGroup";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteDiscussionCategoryGroup";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.DiscussionCategoryGroup"
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

        _methodName10 = "fetchDiscussionCategoryGroup";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getDiscussionCategoryGroup";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getDiscussionCategoryGroups";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getDiscussionCategoryGroupsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateDiscussionCategoryGroup";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.DiscussionCategoryGroup"
            };

        _methodName552 = "getBeanIdentifier";

        _methodParameterTypes552 = new String[] {  };

        _methodName553 = "setBeanIdentifier";

        _methodParameterTypes553 = new String[] { "java.lang.String" };

        _methodName558 = "createDiscussionCategoryGroup";

        _methodParameterTypes558 = new String[] { "java.lang.String" };

        _methodName559 = "getCategoryById";

        _methodParameterTypes559 = new String[] { "long" };

        _methodName560 = "getThreadById";

        _methodParameterTypes560 = new String[] { "long" };

        _methodName561 = "getCategories";

        _methodParameterTypes561 = new String[] {
                "com.ext.portlet.model.DiscussionCategoryGroup"
            };

        _methodName562 = "addCategory";

        _methodParameterTypes562 = new String[] {
                "com.ext.portlet.model.DiscussionCategoryGroup",
                "java.lang.String", "java.lang.String",
                "com.liferay.portal.model.User"
            };

        _methodName563 = "store";

        _methodParameterTypes563 = new String[] {
                "com.ext.portlet.model.DiscussionCategoryGroup"
            };

        _methodName564 = "getCommentThread";

        _methodParameterTypes564 = new String[] {
                "com.ext.portlet.model.DiscussionCategoryGroup"
            };

        _methodName565 = "addComment";

        _methodParameterTypes565 = new String[] {
                "com.ext.portlet.model.DiscussionCategoryGroup",
                "java.lang.String", "java.lang.String",
                "com.liferay.portal.model.User"
            };

        _methodName566 = "getCommentsCount";

        _methodParameterTypes566 = new String[] { "long" };

        _methodName567 = "getCommentsCount";

        _methodParameterTypes567 = new String[] {
                "com.ext.portlet.model.DiscussionCategoryGroup"
            };

        _methodName568 = "copyEverything";

        _methodParameterTypes568 = new String[] {
                "com.ext.portlet.model.DiscussionCategoryGroup",
                "com.ext.portlet.model.DiscussionCategoryGroup"
            };

        _methodName569 = "getUserMessages";

        _methodParameterTypes569 = new String[] { "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.addDiscussionCategoryGroup((com.ext.portlet.model.DiscussionCategoryGroup) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.createDiscussionCategoryGroup(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.deleteDiscussionCategoryGroup(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.deleteDiscussionCategoryGroup((com.ext.portlet.model.DiscussionCategoryGroup) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.fetchDiscussionCategoryGroup(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroups(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroupsCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.updateDiscussionCategoryGroup((com.ext.portlet.model.DiscussionCategoryGroup) arguments[0]);
        }

        if (_methodName552.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes552, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName553.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes553, parameterTypes)) {
            DiscussionCategoryGroupLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName558.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes558, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.createDiscussionCategoryGroup((java.lang.String) arguments[0]);
        }

        if (_methodName559.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes559, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.getCategoryById(((Long) arguments[0]).longValue());
        }

        if (_methodName560.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes560, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.getThreadById(((Long) arguments[0]).longValue());
        }

        if (_methodName561.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes561, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.getCategories((com.ext.portlet.model.DiscussionCategoryGroup) arguments[0]);
        }

        if (_methodName562.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes562, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.addCategory((com.ext.portlet.model.DiscussionCategoryGroup) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (com.liferay.portal.model.User) arguments[3]);
        }

        if (_methodName563.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes563, parameterTypes)) {
            DiscussionCategoryGroupLocalServiceUtil.store((com.ext.portlet.model.DiscussionCategoryGroup) arguments[0]);

            return null;
        }

        if (_methodName564.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes564, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.getCommentThread((com.ext.portlet.model.DiscussionCategoryGroup) arguments[0]);
        }

        if (_methodName565.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes565, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.addComment((com.ext.portlet.model.DiscussionCategoryGroup) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (com.liferay.portal.model.User) arguments[3]);
        }

        if (_methodName566.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes566, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.getCommentsCount(((Long) arguments[0]).longValue());
        }

        if (_methodName567.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes567, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.getCommentsCount((com.ext.portlet.model.DiscussionCategoryGroup) arguments[0]);
        }

        if (_methodName568.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes568, parameterTypes)) {
            DiscussionCategoryGroupLocalServiceUtil.copyEverything((com.ext.portlet.model.DiscussionCategoryGroup) arguments[0],
                (com.ext.portlet.model.DiscussionCategoryGroup) arguments[1]);

            return null;
        }

        if (_methodName569.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes569, parameterTypes)) {
            return DiscussionCategoryGroupLocalServiceUtil.getUserMessages(((Long) arguments[0]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
