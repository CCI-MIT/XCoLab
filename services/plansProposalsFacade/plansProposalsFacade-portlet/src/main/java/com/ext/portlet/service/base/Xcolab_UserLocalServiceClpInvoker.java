package com.ext.portlet.service.base;

import com.ext.portlet.service.Xcolab_UserLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Xcolab_UserLocalServiceClpInvoker {
    private String _methodName482;
    private String[] _methodParameterTypes482;
    private String _methodName483;
    private String[] _methodParameterTypes483;
    private String _methodName486;
    private String[] _methodParameterTypes486;
    private String _methodName487;
    private String[] _methodParameterTypes487;
    private String _methodName488;
    private String[] _methodParameterTypes488;
    private String _methodName489;
    private String[] _methodParameterTypes489;
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
    private String _methodName496;
    private String[] _methodParameterTypes496;

    public Xcolab_UserLocalServiceClpInvoker() {
        _methodName482 = "getBeanIdentifier";

        _methodParameterTypes482 = new String[] {  };

        _methodName483 = "setBeanIdentifier";

        _methodParameterTypes483 = new String[] { "java.lang.String" };

        _methodName486 = "getUsersSortedByScreenName";

        _methodParameterTypes486 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName487 = "getUsersSortedByScreenNameFilteredByCategory";

        _methodParameterTypes487 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName488 = "getUsersSortedByRoleName";

        _methodParameterTypes488 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName489 = "getUsersSortedByMemberSince";

        _methodParameterTypes489 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName490 = "getUsersSortedByMemberSinceFilteredByCategory";

        _methodParameterTypes490 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName491 = "getUsersSortedByActivityCount";

        _methodParameterTypes491 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName492 = "getUsersSortedByActivityCountFilteredByCategory";

        _methodParameterTypes492 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName493 = "getUsersSortedByPoints";

        _methodParameterTypes493 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName494 = "getUsersSortedByPointsFilteredByCategory";

        _methodParameterTypes494 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName495 = "getUserActivityCount";

        _methodParameterTypes495 = new String[] { "java.lang.Long" };

        _methodName496 = "findUsersByLoginIP";

        _methodParameterTypes496 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName482.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes482, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName483.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes483, parameterTypes)) {
            Xcolab_UserLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName486.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes486, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenName(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName487.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes487, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName488.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes488, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByRoleName(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName489.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes489, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSince(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName490.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes490, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName491.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes491, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCount(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName492.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes492, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName493.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes493, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPoints(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName494.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes494, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName495.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes495, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUserActivityCount((java.lang.Long) arguments[0]);
        }

        if (_methodName496.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes496, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.findUsersByLoginIP((java.lang.String) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
