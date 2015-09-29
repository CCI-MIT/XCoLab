package com.ext.portlet.service.base;

import com.ext.portlet.service.Xcolab_UserLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Xcolab_UserLocalServiceClpInvoker {
    private String _methodName616;
    private String[] _methodParameterTypes616;
    private String _methodName617;
    private String[] _methodParameterTypes617;
    private String _methodName620;
    private String[] _methodParameterTypes620;
    private String _methodName621;
    private String[] _methodParameterTypes621;
    private String _methodName622;
    private String[] _methodParameterTypes622;
    private String _methodName623;
    private String[] _methodParameterTypes623;
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
    private String _methodName634;
    private String[] _methodParameterTypes634;
    private String _methodName635;
    private String[] _methodParameterTypes635;
    private String _methodName636;
    private String[] _methodParameterTypes636;
    private String _methodName637;
    private String[] _methodParameterTypes637;
    private String _methodName638;
    private String[] _methodParameterTypes638;

    public Xcolab_UserLocalServiceClpInvoker() {
        _methodName616 = "getBeanIdentifier";

        _methodParameterTypes616 = new String[] {  };

        _methodName617 = "setBeanIdentifier";

        _methodParameterTypes617 = new String[] { "java.lang.String" };

        _methodName620 = "getUsersSortedByScreenNameAsc";

        _methodParameterTypes620 = new String[] { "int", "int", "java.lang.String" };

        _methodName621 = "getUsersSortedByScreenNameAscFilteredByCategory";

        _methodParameterTypes621 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName622 = "getUsersSortedByScreenNameDesc";

        _methodParameterTypes622 = new String[] { "int", "int", "java.lang.String" };

        _methodName623 = "getUsersSortedByScreenNameDescFilteredByCategory";

        _methodParameterTypes623 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName624 = "getUsersSortedByRoleNameAsc";

        _methodParameterTypes624 = new String[] { "int", "int", "java.lang.String" };

        _methodName625 = "getUsersSortedByRoleNameDesc";

        _methodParameterTypes625 = new String[] { "int", "int", "java.lang.String" };

        _methodName626 = "getUsersSortedByMemberSinceAsc";

        _methodParameterTypes626 = new String[] { "int", "int", "java.lang.String" };

        _methodName627 = "getUsersSortedByMemberSinceAscFilteredByCategory";

        _methodParameterTypes627 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName628 = "getUsersSortedByMemberSinceDesc";

        _methodParameterTypes628 = new String[] { "int", "int", "java.lang.String" };

        _methodName629 = "getUsersSortedByMemberSinceDescFilteredByCategory";

        _methodParameterTypes629 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName630 = "getUsersSortedByActivityCountAsc";

        _methodParameterTypes630 = new String[] { "int", "int", "java.lang.String" };

        _methodName631 = "getUsersSortedByActivityCountAscFilteredByCategory";

        _methodParameterTypes631 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName632 = "getUsersSortedByActivityCountDesc";

        _methodParameterTypes632 = new String[] { "int", "int", "java.lang.String" };

        _methodName633 = "getUsersSortedByActivityCountDescFilteredByCategory";

        _methodParameterTypes633 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName634 = "getUsersSortedByPointsAsc";

        _methodParameterTypes634 = new String[] { "int", "int", "java.lang.String" };

        _methodName635 = "getUsersSortedByPointsDesc";

        _methodParameterTypes635 = new String[] { "int", "int", "java.lang.String" };

        _methodName636 = "getUsersSortedByPointsAscFilteredByCategory";

        _methodParameterTypes636 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName637 = "getUsersSortedByPointsDescFilteredByCategory";

        _methodParameterTypes637 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName638 = "getUserActivityCount";

        _methodParameterTypes638 = new String[] { "java.lang.Long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName616.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes616, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName617.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes617, parameterTypes)) {
            Xcolab_UserLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName620.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes620, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName621.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes621, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameAscFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName622.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes622, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName623.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes623, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameDescFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName624.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes624, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByRoleNameAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName625.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes625, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByRoleNameDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName626.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes626, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName627.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes627, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceAscFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName628.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes628, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName629.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes629, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceDescFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName630.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes630, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName631.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes631, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountAscFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName632.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes632, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName633.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes633, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountDescFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName634.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes634, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName635.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes635, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName636.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes636, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsAscFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName637.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes637, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsDescFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName638.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes638, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUserActivityCount((java.lang.Long) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
