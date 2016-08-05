package com.ext.portlet.service.base;

import com.ext.portlet.service.Xcolab_UserLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Xcolab_UserLocalServiceClpInvoker {
    private String _methodName448;
    private String[] _methodParameterTypes448;
    private String _methodName449;
    private String[] _methodParameterTypes449;
    private String _methodName452;
    private String[] _methodParameterTypes452;
    private String _methodName453;
    private String[] _methodParameterTypes453;
    private String _methodName454;
    private String[] _methodParameterTypes454;
    private String _methodName455;
    private String[] _methodParameterTypes455;
    private String _methodName456;
    private String[] _methodParameterTypes456;
    private String _methodName457;
    private String[] _methodParameterTypes457;
    private String _methodName458;
    private String[] _methodParameterTypes458;
    private String _methodName459;
    private String[] _methodParameterTypes459;
    private String _methodName460;
    private String[] _methodParameterTypes460;
    private String _methodName461;
    private String[] _methodParameterTypes461;
    private String _methodName462;
    private String[] _methodParameterTypes462;

    public Xcolab_UserLocalServiceClpInvoker() {
        _methodName448 = "getBeanIdentifier";

        _methodParameterTypes448 = new String[] {  };

        _methodName449 = "setBeanIdentifier";

        _methodParameterTypes449 = new String[] { "java.lang.String" };

        _methodName452 = "getUsersSortedByScreenName";

        _methodParameterTypes452 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName453 = "getUsersSortedByScreenNameFilteredByCategory";

        _methodParameterTypes453 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName454 = "getUsersSortedByRoleName";

        _methodParameterTypes454 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName455 = "getUsersSortedByMemberSince";

        _methodParameterTypes455 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName456 = "getUsersSortedByMemberSinceFilteredByCategory";

        _methodParameterTypes456 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName457 = "getUsersSortedByActivityCount";

        _methodParameterTypes457 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName458 = "getUsersSortedByActivityCountFilteredByCategory";

        _methodParameterTypes458 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName459 = "getUsersSortedByPoints";

        _methodParameterTypes459 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName460 = "getUsersSortedByPointsFilteredByCategory";

        _methodParameterTypes460 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName461 = "getUserActivityCount";

        _methodParameterTypes461 = new String[] { "java.lang.Long" };

        _methodName462 = "findUsersByLoginIP";

        _methodParameterTypes462 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName448.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes448, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName449.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes449, parameterTypes)) {
            Xcolab_UserLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName452.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes452, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenName(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName453.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes453, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName454.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes454, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByRoleName(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName455.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes455, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSince(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName456.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes456, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName457.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes457, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCount(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName458.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes458, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName459.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes459, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPoints(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName460.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes460, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName461.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes461, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUserActivityCount((java.lang.Long) arguments[0]);
        }

        if (_methodName462.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes462, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.findUsersByLoginIP((java.lang.String) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
