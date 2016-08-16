package com.ext.portlet.service.base;

import com.ext.portlet.service.Xcolab_UserLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Xcolab_UserLocalServiceClpInvoker {
    private String _methodName382;
    private String[] _methodParameterTypes382;
    private String _methodName383;
    private String[] _methodParameterTypes383;
    private String _methodName386;
    private String[] _methodParameterTypes386;
    private String _methodName387;
    private String[] _methodParameterTypes387;
    private String _methodName388;
    private String[] _methodParameterTypes388;
    private String _methodName389;
    private String[] _methodParameterTypes389;
    private String _methodName390;
    private String[] _methodParameterTypes390;
    private String _methodName391;
    private String[] _methodParameterTypes391;
    private String _methodName392;
    private String[] _methodParameterTypes392;
    private String _methodName393;
    private String[] _methodParameterTypes393;
    private String _methodName394;
    private String[] _methodParameterTypes394;
    private String _methodName395;
    private String[] _methodParameterTypes395;
    private String _methodName396;
    private String[] _methodParameterTypes396;

    public Xcolab_UserLocalServiceClpInvoker() {
        _methodName382 = "getBeanIdentifier";

        _methodParameterTypes382 = new String[] {  };

        _methodName383 = "setBeanIdentifier";

        _methodParameterTypes383 = new String[] { "java.lang.String" };

        _methodName386 = "getUsersSortedByScreenName";

        _methodParameterTypes386 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName387 = "getUsersSortedByScreenNameFilteredByCategory";

        _methodParameterTypes387 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName388 = "getUsersSortedByRoleName";

        _methodParameterTypes388 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName389 = "getUsersSortedByMemberSince";

        _methodParameterTypes389 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName390 = "getUsersSortedByMemberSinceFilteredByCategory";

        _methodParameterTypes390 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName391 = "getUsersSortedByActivityCount";

        _methodParameterTypes391 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName392 = "getUsersSortedByActivityCountFilteredByCategory";

        _methodParameterTypes392 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName393 = "getUsersSortedByPoints";

        _methodParameterTypes393 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName394 = "getUsersSortedByPointsFilteredByCategory";

        _methodParameterTypes394 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName395 = "getUserActivityCount";

        _methodParameterTypes395 = new String[] { "java.lang.Long" };

        _methodName396 = "findUsersByLoginIP";

        _methodParameterTypes396 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName382.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes382, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName383.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes383, parameterTypes)) {
            Xcolab_UserLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName386.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes386, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenName(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName387.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes387, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName388.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes388, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByRoleName(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName389.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes389, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSince(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName390.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes390, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName391.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes391, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCount(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName392.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes392, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName393.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes393, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPoints(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName394.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes394, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName395.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes395, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUserActivityCount((java.lang.Long) arguments[0]);
        }

        if (_methodName396.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes396, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.findUsersByLoginIP((java.lang.String) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
