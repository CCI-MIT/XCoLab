package com.ext.portlet.service.base;

import com.ext.portlet.service.User_LocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class User_LocalServiceClpInvoker {
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
    private String _methodName16;
    private String[] _methodParameterTypes16;
    private String _methodName17;
    private String[] _methodParameterTypes17;
    private String _methodName18;
    private String[] _methodParameterTypes18;
    private String _methodName19;
    private String[] _methodParameterTypes19;
    private String _methodName20;
    private String[] _methodParameterTypes20;
    private String _methodName21;
    private String[] _methodParameterTypes21;
    private String _methodName22;
    private String[] _methodParameterTypes22;
    private String _methodName23;
    private String[] _methodParameterTypes23;
    private String _methodName24;
    private String[] _methodParameterTypes24;
    private String _methodName25;
    private String[] _methodParameterTypes25;
    private String _methodName26;
    private String[] _methodParameterTypes26;
    private String _methodName27;
    private String[] _methodParameterTypes27;
    private String _methodName28;
    private String[] _methodParameterTypes28;
    private String _methodName29;
    private String[] _methodParameterTypes29;
    private String _methodName30;
    private String[] _methodParameterTypes30;
    private String _methodName31;
    private String[] _methodParameterTypes31;
    private String _methodName618;
    private String[] _methodParameterTypes618;
    private String _methodName619;
    private String[] _methodParameterTypes619;
    private String _methodName624;
    private String[] _methodParameterTypes624;
    private String _methodName625;
    private String[] _methodParameterTypes625;
    private String _methodName626;
    private String[] _methodParameterTypes626;
    private String _methodName627;
    private String[] _methodParameterTypes627;
    private String _methodName628;
    private String[] _methodParameterTypes628;
    private String _methodName629;
    private String[] _methodParameterTypes629;
    private String _methodName630;
    private String[] _methodParameterTypes630;
    private String _methodName631;
    private String[] _methodParameterTypes631;
    private String _methodName632;
    private String[] _methodParameterTypes632;
    private String _methodName633;
    private String[] _methodParameterTypes633;

    public User_LocalServiceClpInvoker() {
        _methodName0 = "addUser_";

        _methodParameterTypes0 = new String[] { "com.ext.portlet.model.User_" };

        _methodName1 = "createUser_";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteUser_";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteUser_";

        _methodParameterTypes3 = new String[] { "com.ext.portlet.model.User_" };

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

        _methodName10 = "fetchUser_";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getUser_";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getUser_s";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getUser_sCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateUser_";

        _methodParameterTypes15 = new String[] { "com.ext.portlet.model.User_" };

        _methodName16 = "addRole_User_";

        _methodParameterTypes16 = new String[] { "long", "long" };

        _methodName17 = "addRole_User_";

        _methodParameterTypes17 = new String[] {
                "long", "com.ext.portlet.model.User_"
            };

        _methodName18 = "addRole_User_s";

        _methodParameterTypes18 = new String[] { "long", "long[][]" };

        _methodName19 = "addRole_User_s";

        _methodParameterTypes19 = new String[] { "long", "java.util.List" };

        _methodName20 = "clearRole_User_s";

        _methodParameterTypes20 = new String[] { "long" };

        _methodName21 = "deleteRole_User_";

        _methodParameterTypes21 = new String[] { "long", "long" };

        _methodName22 = "deleteRole_User_";

        _methodParameterTypes22 = new String[] {
                "long", "com.ext.portlet.model.User_"
            };

        _methodName23 = "deleteRole_User_s";

        _methodParameterTypes23 = new String[] { "long", "long[][]" };

        _methodName24 = "deleteRole_User_s";

        _methodParameterTypes24 = new String[] { "long", "java.util.List" };

        _methodName25 = "getRole_User_s";

        _methodParameterTypes25 = new String[] { "long" };

        _methodName26 = "getRole_User_s";

        _methodParameterTypes26 = new String[] { "long", "int", "int" };

        _methodName27 = "getRole_User_s";

        _methodParameterTypes27 = new String[] {
                "long", "int", "int",
                "com.liferay.portal.kernel.util.OrderByComparator"
            };

        _methodName28 = "getRole_User_sCount";

        _methodParameterTypes28 = new String[] { "long" };

        _methodName29 = "hasRole_User_";

        _methodParameterTypes29 = new String[] { "long", "long" };

        _methodName30 = "hasRole_User_s";

        _methodParameterTypes30 = new String[] { "long" };

        _methodName31 = "setRole_User_s";

        _methodParameterTypes31 = new String[] { "long", "long[][]" };

        _methodName618 = "getBeanIdentifier";

        _methodParameterTypes618 = new String[] {  };

        _methodName619 = "setBeanIdentifier";

        _methodParameterTypes619 = new String[] { "java.lang.String" };

        _methodName624 = "getUsersSortedByScreenNameAsc";

        _methodParameterTypes624 = new String[] { "int", "int", "java.lang.String" };

        _methodName625 = "getUsersSortedByScreenNameAscFilteredByCategory";

        _methodParameterTypes625 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName626 = "getUsersSortedByScreenNameDesc";

        _methodParameterTypes626 = new String[] { "int", "int", "java.lang.String" };

        _methodName627 = "getUsersSortedByScreenNameDescFilteredByCategory";

        _methodParameterTypes627 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName628 = "getUsersSortedByRoleNameAsc";

        _methodParameterTypes628 = new String[] { "int", "int", "java.lang.String" };

        _methodName629 = "getUsersSortedByRoleNameDesc";

        _methodParameterTypes629 = new String[] { "int", "int", "java.lang.String" };

        _methodName630 = "getUsersSortedByMemberSinceAsc";

        _methodParameterTypes630 = new String[] { "int", "int", "java.lang.String" };

        _methodName631 = "getUsersSortedByMemberSinceAscFilteredByCategory";

        _methodParameterTypes631 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName632 = "getUsersSortedByMemberSinceDesc";

        _methodParameterTypes632 = new String[] { "int", "int", "java.lang.String" };

        _methodName633 = "getUsersSortedByMemberSinceDescFilteredByCategory";

        _methodParameterTypes633 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return User_LocalServiceUtil.addUser_((com.ext.portlet.model.User_) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return User_LocalServiceUtil.createUser_(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return User_LocalServiceUtil.deleteUser_(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return User_LocalServiceUtil.deleteUser_((com.ext.portlet.model.User_) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return User_LocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return User_LocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return User_LocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return User_LocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return User_LocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return User_LocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return User_LocalServiceUtil.fetchUser_(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return User_LocalServiceUtil.getUser_(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return User_LocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return User_LocalServiceUtil.getUser_s(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return User_LocalServiceUtil.getUser_sCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return User_LocalServiceUtil.updateUser_((com.ext.portlet.model.User_) arguments[0]);
        }

        if (_methodName16.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
            User_LocalServiceUtil.addRole_User_(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());

            return null;
        }

        if (_methodName17.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
            User_LocalServiceUtil.addRole_User_(((Long) arguments[0]).longValue(),
                (com.ext.portlet.model.User_) arguments[1]);

            return null;
        }

        if (_methodName18.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
            User_LocalServiceUtil.addRole_User_s(((Long) arguments[0]).longValue(),
                (long[]) arguments[1]);

            return null;
        }

        if (_methodName19.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
            User_LocalServiceUtil.addRole_User_s(((Long) arguments[0]).longValue(),
                (java.util.List<com.ext.portlet.model.User_>) arguments[1]);

            return null;
        }

        if (_methodName20.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
            User_LocalServiceUtil.clearRole_User_s(((Long) arguments[0]).longValue());

            return null;
        }

        if (_methodName21.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
            User_LocalServiceUtil.deleteRole_User_(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());

            return null;
        }

        if (_methodName22.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
            User_LocalServiceUtil.deleteRole_User_(((Long) arguments[0]).longValue(),
                (com.ext.portlet.model.User_) arguments[1]);

            return null;
        }

        if (_methodName23.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
            User_LocalServiceUtil.deleteRole_User_s(((Long) arguments[0]).longValue(),
                (long[]) arguments[1]);

            return null;
        }

        if (_methodName24.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
            User_LocalServiceUtil.deleteRole_User_s(((Long) arguments[0]).longValue(),
                (java.util.List<com.ext.portlet.model.User_>) arguments[1]);

            return null;
        }

        if (_methodName25.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
            return User_LocalServiceUtil.getRole_User_s(((Long) arguments[0]).longValue());
        }

        if (_methodName26.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
            return User_LocalServiceUtil.getRole_User_s(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName27.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
            return User_LocalServiceUtil.getRole_User_s(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName28.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
            return User_LocalServiceUtil.getRole_User_sCount(((Long) arguments[0]).longValue());
        }

        if (_methodName29.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
            return User_LocalServiceUtil.hasRole_User_(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName30.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
            return User_LocalServiceUtil.hasRole_User_s(((Long) arguments[0]).longValue());
        }

        if (_methodName31.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
            User_LocalServiceUtil.setRole_User_s(((Long) arguments[0]).longValue(),
                (long[]) arguments[1]);

            return null;
        }

        if (_methodName618.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes618, parameterTypes)) {
            return User_LocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName619.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes619, parameterTypes)) {
            User_LocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName624.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes624, parameterTypes)) {
            return User_LocalServiceUtil.getUsersSortedByScreenNameAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName625.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes625, parameterTypes)) {
            return User_LocalServiceUtil.getUsersSortedByScreenNameAscFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName626.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes626, parameterTypes)) {
            return User_LocalServiceUtil.getUsersSortedByScreenNameDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName627.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes627, parameterTypes)) {
            return User_LocalServiceUtil.getUsersSortedByScreenNameDescFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName628.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes628, parameterTypes)) {
            return User_LocalServiceUtil.getUsersSortedByRoleNameAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName629.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes629, parameterTypes)) {
            return User_LocalServiceUtil.getUsersSortedByRoleNameDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName630.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes630, parameterTypes)) {
            return User_LocalServiceUtil.getUsersSortedByMemberSinceAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName631.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes631, parameterTypes)) {
            return User_LocalServiceUtil.getUsersSortedByMemberSinceAscFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName632.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes632, parameterTypes)) {
            return User_LocalServiceUtil.getUsersSortedByMemberSinceDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName633.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes633, parameterTypes)) {
            return User_LocalServiceUtil.getUsersSortedByMemberSinceDescFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        throw new UnsupportedOperationException();
    }
}
