package com.ext.portlet.service.base;

import com.ext.portlet.service.Xcolab_UserLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Xcolab_UserLocalServiceClpInvoker {
    private String _methodName406;
    private String[] _methodParameterTypes406;
    private String _methodName407;
    private String[] _methodParameterTypes407;
    private String _methodName410;
    private String[] _methodParameterTypes410;
    private String _methodName411;
    private String[] _methodParameterTypes411;
    private String _methodName412;
    private String[] _methodParameterTypes412;
    private String _methodName413;
    private String[] _methodParameterTypes413;
    private String _methodName414;
    private String[] _methodParameterTypes414;
    private String _methodName415;
    private String[] _methodParameterTypes415;
    private String _methodName416;
    private String[] _methodParameterTypes416;
    private String _methodName417;
    private String[] _methodParameterTypes417;
    private String _methodName418;
    private String[] _methodParameterTypes418;
    private String _methodName419;
    private String[] _methodParameterTypes419;
    private String _methodName420;
    private String[] _methodParameterTypes420;

    public Xcolab_UserLocalServiceClpInvoker() {
        _methodName406 = "getBeanIdentifier";

        _methodParameterTypes406 = new String[] {  };

        _methodName407 = "setBeanIdentifier";

        _methodParameterTypes407 = new String[] { "java.lang.String" };

        _methodName410 = "getUsersSortedByScreenName";

        _methodParameterTypes410 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName411 = "getUsersSortedByScreenNameFilteredByCategory";

        _methodParameterTypes411 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName412 = "getUsersSortedByRoleName";

        _methodParameterTypes412 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName413 = "getUsersSortedByMemberSince";

        _methodParameterTypes413 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName414 = "getUsersSortedByMemberSinceFilteredByCategory";

        _methodParameterTypes414 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName415 = "getUsersSortedByActivityCount";

        _methodParameterTypes415 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName416 = "getUsersSortedByActivityCountFilteredByCategory";

        _methodParameterTypes416 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName417 = "getUsersSortedByPoints";

        _methodParameterTypes417 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName418 = "getUsersSortedByPointsFilteredByCategory";

        _methodParameterTypes418 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName419 = "getUserActivityCount";

        _methodParameterTypes419 = new String[] { "java.lang.Long" };

        _methodName420 = "findUsersByLoginIP";

        _methodParameterTypes420 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName406.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes406, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName407.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes407, parameterTypes)) {
            Xcolab_UserLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName410.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes410, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenName(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName411.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes411, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName412.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes412, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByRoleName(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName413.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes413, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSince(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName414.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes414, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName415.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes415, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCount(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName416.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes416, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName417.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes417, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPoints(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName418.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes418, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName419.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes419, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUserActivityCount((java.lang.Long) arguments[0]);
        }

        if (_methodName420.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes420, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.findUsersByLoginIP((java.lang.String) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
