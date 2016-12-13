package com.ext.portlet.service.base;

import com.ext.portlet.service.Xcolab_UserLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Xcolab_UserLocalServiceClpInvoker {
    private String _methodName334;
    private String[] _methodParameterTypes334;
    private String _methodName335;
    private String[] _methodParameterTypes335;
    private String _methodName338;
    private String[] _methodParameterTypes338;
    private String _methodName339;
    private String[] _methodParameterTypes339;
    private String _methodName340;
    private String[] _methodParameterTypes340;
    private String _methodName341;
    private String[] _methodParameterTypes341;
    private String _methodName342;
    private String[] _methodParameterTypes342;
    private String _methodName343;
    private String[] _methodParameterTypes343;
    private String _methodName344;
    private String[] _methodParameterTypes344;
    private String _methodName345;
    private String[] _methodParameterTypes345;
    private String _methodName346;
    private String[] _methodParameterTypes346;
    private String _methodName347;
    private String[] _methodParameterTypes347;
    private String _methodName348;
    private String[] _methodParameterTypes348;

    public Xcolab_UserLocalServiceClpInvoker() {
        _methodName334 = "getBeanIdentifier";

        _methodParameterTypes334 = new String[] {  };

        _methodName335 = "setBeanIdentifier";

        _methodParameterTypes335 = new String[] { "java.lang.String" };

        _methodName338 = "getUsersSortedByScreenName";

        _methodParameterTypes338 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName339 = "getUsersSortedByScreenNameFilteredByCategory";

        _methodParameterTypes339 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName340 = "getUsersSortedByRoleName";

        _methodParameterTypes340 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName341 = "getUsersSortedByMemberSince";

        _methodParameterTypes341 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName342 = "getUsersSortedByMemberSinceFilteredByCategory";

        _methodParameterTypes342 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName343 = "getUsersSortedByActivityCount";

        _methodParameterTypes343 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName344 = "getUsersSortedByActivityCountFilteredByCategory";

        _methodParameterTypes344 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName345 = "getUsersSortedByPoints";

        _methodParameterTypes345 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName346 = "getUsersSortedByPointsFilteredByCategory";

        _methodParameterTypes346 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName347 = "getUserActivityCount";

        _methodParameterTypes347 = new String[] { "java.lang.Long" };

        _methodName348 = "findUsersByLoginIP";

        _methodParameterTypes348 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName334.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes334, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName335.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes335, parameterTypes)) {
            Xcolab_UserLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName338.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes338, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenName(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName339.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes339, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName340.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes340, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByRoleName(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName341.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes341, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSince(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName342.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes342, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName343.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes343, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCount(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName344.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes344, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName345.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes345, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPoints(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName346.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes346, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName347.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes347, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUserActivityCount((java.lang.Long) arguments[0]);
        }

        if (_methodName348.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes348, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.findUsersByLoginIP((java.lang.String) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
