package com.ext.portlet.service.base;

import com.ext.portlet.service.Xcolab_UserLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Xcolab_UserLocalServiceClpInvoker {
    private String _methodName472;
    private String[] _methodParameterTypes472;
    private String _methodName473;
    private String[] _methodParameterTypes473;
    private String _methodName476;
    private String[] _methodParameterTypes476;
    private String _methodName477;
    private String[] _methodParameterTypes477;
    private String _methodName478;
    private String[] _methodParameterTypes478;
    private String _methodName479;
    private String[] _methodParameterTypes479;
    private String _methodName480;
    private String[] _methodParameterTypes480;
    private String _methodName481;
    private String[] _methodParameterTypes481;
    private String _methodName482;
    private String[] _methodParameterTypes482;
    private String _methodName483;
    private String[] _methodParameterTypes483;
    private String _methodName484;
    private String[] _methodParameterTypes484;
    private String _methodName485;
    private String[] _methodParameterTypes485;
    private String _methodName486;
    private String[] _methodParameterTypes486;

    public Xcolab_UserLocalServiceClpInvoker() {
        _methodName472 = "getBeanIdentifier";

        _methodParameterTypes472 = new String[] {  };

        _methodName473 = "setBeanIdentifier";

        _methodParameterTypes473 = new String[] { "java.lang.String" };

        _methodName476 = "getUsersSortedByScreenName";

        _methodParameterTypes476 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName477 = "getUsersSortedByScreenNameFilteredByCategory";

        _methodParameterTypes477 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName478 = "getUsersSortedByRoleName";

        _methodParameterTypes478 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName479 = "getUsersSortedByMemberSince";

        _methodParameterTypes479 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName480 = "getUsersSortedByMemberSinceFilteredByCategory";

        _methodParameterTypes480 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName481 = "getUsersSortedByActivityCount";

        _methodParameterTypes481 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName482 = "getUsersSortedByActivityCountFilteredByCategory";

        _methodParameterTypes482 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName483 = "getUsersSortedByPoints";

        _methodParameterTypes483 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName484 = "getUsersSortedByPointsFilteredByCategory";

        _methodParameterTypes484 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName485 = "getUserActivityCount";

        _methodParameterTypes485 = new String[] { "java.lang.Long" };

        _methodName486 = "findUsersByLoginIP";

        _methodParameterTypes486 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName472.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes472, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName473.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes473, parameterTypes)) {
            Xcolab_UserLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName476.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes476, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenName(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName477.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes477, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName478.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes478, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByRoleName(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName479.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes479, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSince(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName480.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes480, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName481.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes481, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCount(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName482.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes482, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName483.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes483, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPoints(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName484.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes484, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName485.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes485, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUserActivityCount((java.lang.Long) arguments[0]);
        }

        if (_methodName486.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes486, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.findUsersByLoginIP((java.lang.String) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
