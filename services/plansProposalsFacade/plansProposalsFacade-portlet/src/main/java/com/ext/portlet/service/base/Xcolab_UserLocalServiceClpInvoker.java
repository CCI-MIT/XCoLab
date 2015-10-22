package com.ext.portlet.service.base;

import com.ext.portlet.service.Xcolab_UserLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Xcolab_UserLocalServiceClpInvoker {
    private String _methodName476;
    private String[] _methodParameterTypes476;
    private String _methodName477;
    private String[] _methodParameterTypes477;
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
    private String _methodName497;
    private String[] _methodParameterTypes497;
    private String _methodName498;
    private String[] _methodParameterTypes498;

    public Xcolab_UserLocalServiceClpInvoker() {
        _methodName476 = "getBeanIdentifier";

        _methodParameterTypes476 = new String[] {  };

        _methodName477 = "setBeanIdentifier";

        _methodParameterTypes477 = new String[] { "java.lang.String" };

        _methodName480 = "getUsersSortedByScreenNameAsc";

        _methodParameterTypes480 = new String[] { "int", "int", "java.lang.String" };

        _methodName481 = "getUsersSortedByScreenNameAscFilteredByCategory";

        _methodParameterTypes481 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName482 = "getUsersSortedByScreenNameDesc";

        _methodParameterTypes482 = new String[] { "int", "int", "java.lang.String" };

        _methodName483 = "getUsersSortedByScreenNameDescFilteredByCategory";

        _methodParameterTypes483 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName484 = "getUsersSortedByRoleNameAsc";

        _methodParameterTypes484 = new String[] { "int", "int", "java.lang.String" };

        _methodName485 = "getUsersSortedByRoleNameDesc";

        _methodParameterTypes485 = new String[] { "int", "int", "java.lang.String" };

        _methodName486 = "getUsersSortedByMemberSinceAsc";

        _methodParameterTypes486 = new String[] { "int", "int", "java.lang.String" };

        _methodName487 = "getUsersSortedByMemberSinceAscFilteredByCategory";

        _methodParameterTypes487 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName488 = "getUsersSortedByMemberSinceDesc";

        _methodParameterTypes488 = new String[] { "int", "int", "java.lang.String" };

        _methodName489 = "getUsersSortedByMemberSinceDescFilteredByCategory";

        _methodParameterTypes489 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName490 = "getUsersSortedByActivityCountAsc";

        _methodParameterTypes490 = new String[] { "int", "int", "java.lang.String" };

        _methodName491 = "getUsersSortedByActivityCountAscFilteredByCategory";

        _methodParameterTypes491 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName492 = "getUsersSortedByActivityCountDesc";

        _methodParameterTypes492 = new String[] { "int", "int", "java.lang.String" };

        _methodName493 = "getUsersSortedByActivityCountDescFilteredByCategory";

        _methodParameterTypes493 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName494 = "getUsersSortedByPointsAsc";

        _methodParameterTypes494 = new String[] { "int", "int", "java.lang.String" };

        _methodName495 = "getUsersSortedByPointsDesc";

        _methodParameterTypes495 = new String[] { "int", "int", "java.lang.String" };

        _methodName496 = "getUsersSortedByPointsAscFilteredByCategory";

        _methodParameterTypes496 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName497 = "getUsersSortedByPointsDescFilteredByCategory";

        _methodParameterTypes497 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName498 = "getUserActivityCount";

        _methodParameterTypes498 = new String[] { "java.lang.Long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName476.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes476, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName477.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes477, parameterTypes)) {
            Xcolab_UserLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName480.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes480, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName481.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes481, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameAscFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName482.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes482, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName483.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes483, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameDescFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName484.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes484, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByRoleNameAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName485.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes485, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByRoleNameDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName486.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes486, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName487.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes487, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceAscFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName488.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes488, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName489.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes489, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceDescFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName490.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes490, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName491.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes491, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountAscFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName492.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes492, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName493.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes493, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountDescFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName494.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes494, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName495.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes495, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName496.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes496, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsAscFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName497.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes497, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsDescFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName498.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes498, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUserActivityCount((java.lang.Long) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
