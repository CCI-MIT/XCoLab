package com.ext.portlet.service.base;

import com.ext.portlet.service.Xcolab_UserLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Xcolab_UserLocalServiceClpInvoker {
    private String _methodName628;
    private String[] _methodParameterTypes628;
    private String _methodName629;
    private String[] _methodParameterTypes629;
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
    private String _methodName639;
    private String[] _methodParameterTypes639;
    private String _methodName640;
    private String[] _methodParameterTypes640;
    private String _methodName641;
    private String[] _methodParameterTypes641;
    private String _methodName642;
    private String[] _methodParameterTypes642;
    private String _methodName643;
    private String[] _methodParameterTypes643;
    private String _methodName644;
    private String[] _methodParameterTypes644;
    private String _methodName645;
    private String[] _methodParameterTypes645;
    private String _methodName646;
    private String[] _methodParameterTypes646;
    private String _methodName647;
    private String[] _methodParameterTypes647;
    private String _methodName648;
    private String[] _methodParameterTypes648;
    private String _methodName649;
    private String[] _methodParameterTypes649;
    private String _methodName650;
    private String[] _methodParameterTypes650;

    public Xcolab_UserLocalServiceClpInvoker() {
        _methodName628 = "getBeanIdentifier";

        _methodParameterTypes628 = new String[] {  };

        _methodName629 = "setBeanIdentifier";

        _methodParameterTypes629 = new String[] { "java.lang.String" };

        _methodName632 = "getUsersSortedByScreenNameAsc";

        _methodParameterTypes632 = new String[] { "int", "int", "java.lang.String" };

        _methodName633 = "getUsersSortedByScreenNameAscFilteredByCategory";

        _methodParameterTypes633 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName634 = "getUsersSortedByScreenNameDesc";

        _methodParameterTypes634 = new String[] { "int", "int", "java.lang.String" };

        _methodName635 = "getUsersSortedByScreenNameDescFilteredByCategory";

        _methodParameterTypes635 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName636 = "getUsersSortedByRoleNameAsc";

        _methodParameterTypes636 = new String[] { "int", "int", "java.lang.String" };

        _methodName637 = "getUsersSortedByRoleNameDesc";

        _methodParameterTypes637 = new String[] { "int", "int", "java.lang.String" };

        _methodName638 = "getUsersSortedByMemberSinceAsc";

        _methodParameterTypes638 = new String[] { "int", "int", "java.lang.String" };

        _methodName639 = "getUsersSortedByMemberSinceAscFilteredByCategory";

        _methodParameterTypes639 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName640 = "getUsersSortedByMemberSinceDesc";

        _methodParameterTypes640 = new String[] { "int", "int", "java.lang.String" };

        _methodName641 = "getUsersSortedByMemberSinceDescFilteredByCategory";

        _methodParameterTypes641 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName642 = "getUsersSortedByActivityCountAsc";

        _methodParameterTypes642 = new String[] { "int", "int", "java.lang.String" };

        _methodName643 = "getUsersSortedByActivityCountAscFilteredByCategory";

        _methodParameterTypes643 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName644 = "getUsersSortedByActivityCountDesc";

        _methodParameterTypes644 = new String[] { "int", "int", "java.lang.String" };

        _methodName645 = "getUsersSortedByActivityCountDescFilteredByCategory";

        _methodParameterTypes645 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName646 = "getUsersSortedByPointsAsc";

        _methodParameterTypes646 = new String[] { "int", "int", "java.lang.String" };

        _methodName647 = "getUsersSortedByPointsDesc";

        _methodParameterTypes647 = new String[] { "int", "int", "java.lang.String" };

        _methodName648 = "getUsersSortedByPointsAscFilteredByCategory";

        _methodParameterTypes648 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName649 = "getUsersSortedByPointsDescFilteredByCategory";

        _methodParameterTypes649 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String"
            };

        _methodName650 = "getUserActivityCount";

        _methodParameterTypes650 = new String[] { "java.lang.Long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName628.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes628, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName629.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes629, parameterTypes)) {
            Xcolab_UserLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName632.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes632, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName633.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes633, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameAscFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName634.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes634, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName635.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes635, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameDescFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName636.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes636, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByRoleNameAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName637.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes637, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByRoleNameDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName638.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes638, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName639.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes639, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceAscFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName640.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes640, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName641.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes641, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceDescFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName642.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes642, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName643.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes643, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountAscFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName644.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes644, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName645.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes645, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountDescFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName646.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes646, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsAsc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName647.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes647, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsDesc(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName648.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes648, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsAscFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName649.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes649, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUsersSortedByPointsDescFilteredByCategory(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);
        }

        if (_methodName650.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes650, parameterTypes)) {
            return Xcolab_UserLocalServiceUtil.getUserActivityCount((java.lang.Long) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
