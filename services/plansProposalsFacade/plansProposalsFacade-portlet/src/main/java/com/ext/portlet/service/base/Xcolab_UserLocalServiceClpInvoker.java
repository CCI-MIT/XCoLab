package com.ext.portlet.service.base;

import com.ext.portlet.service.Xcolab_UserLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Xcolab_UserLocalServiceClpInvoker {
    private String _methodName246;
    private String[] _methodParameterTypes246;
    private String _methodName247;
    private String[] _methodParameterTypes247;
    private String _methodName250;
    private String[] _methodParameterTypes250;
    private String _methodName251;
    private String[] _methodParameterTypes251;
    private String _methodName252;
    private String[] _methodParameterTypes252;
    private String _methodName253;
    private String[] _methodParameterTypes253;
    private String _methodName254;
    private String[] _methodParameterTypes254;
    private String _methodName255;
    private String[] _methodParameterTypes255;
    private String _methodName256;
    private String[] _methodParameterTypes256;
    private String _methodName257;
    private String[] _methodParameterTypes257;
    private String _methodName258;
    private String[] _methodParameterTypes258;
    private String _methodName259;
    private String[] _methodParameterTypes259;
    private String _methodName260;
    private String[] _methodParameterTypes260;

    public Xcolab_UserLocalServiceClpInvoker() {
        _methodName246 = "getBeanIdentifier";

        _methodParameterTypes246 = new String[] {  };

        _methodName247 = "setBeanIdentifier";

        _methodParameterTypes247 = new String[] { "java.lang.String" };

        _methodName250 = "getUsersSortedByScreenName";

        _methodParameterTypes250 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName251 = "getUsersSortedByScreenNameFilteredByCategory";

        _methodParameterTypes251 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName252 = "getUsersSortedByRoleName";

        _methodParameterTypes252 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName253 = "getUsersSortedByMemberSince";

        _methodParameterTypes253 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName254 = "getUsersSortedByMemberSinceFilteredByCategory";

        _methodParameterTypes254 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName255 = "getUsersSortedByActivityCount";

        _methodParameterTypes255 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName256 = "getUsersSortedByActivityCountFilteredByCategory";

        _methodParameterTypes256 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName257 = "getUsersSortedByPoints";

        _methodParameterTypes257 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName258 = "getUsersSortedByPointsFilteredByCategory";

        _methodParameterTypes258 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName259 = "getUserActivityCount";

        _methodParameterTypes259 = new String[] { "java.lang.Long" };

        _methodName260 = "findUsersByLoginIP";

        _methodParameterTypes260 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName246.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes246, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName247.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes247, parameterTypes)) {
            Xcolab_UserLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName250.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes250, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenName(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName251.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes251, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName252.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes252, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByRoleName(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName253.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes253, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSince(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName254.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes254, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName255.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes255, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCount(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName256.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes256, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName257.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes257, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPoints(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                ((Boolean) arguments[3]).booleanValue());
        }

        if (_methodName258.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes258, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                ((Boolean) arguments[4]).booleanValue());
        }

        if (_methodName259.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes259, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUserActivityCount((java.lang.Long) arguments[0]);
        }

        if (_methodName260.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes260, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.findUsersByLoginIP((java.lang.String) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
