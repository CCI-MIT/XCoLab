package com.ext.portlet.service.base;

import com.ext.portlet.service.FocusAreaOntologyTermLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FocusAreaOntologyTermLocalServiceClpInvoker {
    private String _methodName0;
    private String[] _methodParameterTypes0;
    private String _methodName1;
    private String[] _methodParameterTypes1;
    private String _methodName2;
    private String[] _methodParameterTypes2;
    private String _methodName3;
    private String[] _methodParameterTypes3;
    private String _methodName4;
    private String[] _methodParameterTypes4;
    private String _methodName5;
    private String[] _methodParameterTypes5;
    private String _methodName6;
    private String[] _methodParameterTypes6;
    private String _methodName7;
    private String[] _methodParameterTypes7;
    private String _methodName8;
    private String[] _methodParameterTypes8;
    private String _methodName9;
    private String[] _methodParameterTypes9;
    private String _methodName10;
    private String[] _methodParameterTypes10;
    private String _methodName11;
    private String[] _methodParameterTypes11;
    private String _methodName12;
    private String[] _methodParameterTypes12;
    private String _methodName13;
    private String[] _methodParameterTypes13;
    private String _methodName14;
    private String[] _methodParameterTypes14;
    private String _methodName15;
    private String[] _methodParameterTypes15;
    private String _methodName632;
    private String[] _methodParameterTypes632;
    private String _methodName633;
    private String[] _methodParameterTypes633;
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

    public FocusAreaOntologyTermLocalServiceClpInvoker() {
        _methodName0 = "addFocusAreaOntologyTerm";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.FocusAreaOntologyTerm"
            };

        _methodName1 = "createFocusAreaOntologyTerm";

        _methodParameterTypes1 = new String[] {
                "com.ext.portlet.service.persistence.FocusAreaOntologyTermPK"
            };

        _methodName2 = "deleteFocusAreaOntologyTerm";

        _methodParameterTypes2 = new String[] {
                "com.ext.portlet.service.persistence.FocusAreaOntologyTermPK"
            };

        _methodName3 = "deleteFocusAreaOntologyTerm";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.FocusAreaOntologyTerm"
            };

        _methodName4 = "dynamicQuery";

        _methodParameterTypes4 = new String[] {  };

        _methodName5 = "dynamicQuery";

        _methodParameterTypes5 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery"
            };

        _methodName6 = "dynamicQuery";

        _methodParameterTypes6 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
            };

        _methodName7 = "dynamicQuery";

        _methodParameterTypes7 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
                "com.liferay.portal.kernel.util.OrderByComparator"
            };

        _methodName8 = "dynamicQueryCount";

        _methodParameterTypes8 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery"
            };

        _methodName9 = "dynamicQueryCount";

        _methodParameterTypes9 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery",
                "com.liferay.portal.kernel.dao.orm.Projection"
            };

        _methodName10 = "fetchFocusAreaOntologyTerm";

        _methodParameterTypes10 = new String[] {
                "com.ext.portlet.service.persistence.FocusAreaOntologyTermPK"
            };

        _methodName11 = "getFocusAreaOntologyTerm";

        _methodParameterTypes11 = new String[] {
                "com.ext.portlet.service.persistence.FocusAreaOntologyTermPK"
            };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getFocusAreaOntologyTerms";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getFocusAreaOntologyTermsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateFocusAreaOntologyTerm";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.FocusAreaOntologyTerm"
            };

        _methodName632 = "getBeanIdentifier";

        _methodParameterTypes632 = new String[] {  };

        _methodName633 = "setBeanIdentifier";

        _methodParameterTypes633 = new String[] { "java.lang.String" };

        _methodName638 = "findTermsByFocusArea";

        _methodParameterTypes638 = new String[] { "java.lang.Long" };

        _methodName639 = "addAreaTerm";

        _methodParameterTypes639 = new String[] {
                "java.lang.Long", "java.lang.Long"
            };

        _methodName640 = "removeAreaTerm";

        _methodParameterTypes640 = new String[] {
                "java.lang.Long", "java.lang.Long"
            };

        _methodName641 = "store";

        _methodParameterTypes641 = new String[] {
                "com.ext.portlet.model.FocusAreaOntologyTerm"
            };

        _methodName642 = "getTerm";

        _methodParameterTypes642 = new String[] {
                "com.ext.portlet.model.FocusAreaOntologyTerm"
            };

        _methodName643 = "getArea";

        _methodParameterTypes643 = new String[] {
                "com.ext.portlet.model.FocusAreaOntologyTerm"
            };

        _methodName644 = "getFocusAreaOntologyTermIdsByFocusAreaAndSpaceId";

        _methodParameterTypes644 = new String[] { "long", "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.addFocusAreaOntologyTerm((com.ext.portlet.model.FocusAreaOntologyTerm) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.createFocusAreaOntologyTerm((com.ext.portlet.service.persistence.FocusAreaOntologyTermPK) arguments[0]);
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.deleteFocusAreaOntologyTerm((com.ext.portlet.service.persistence.FocusAreaOntologyTermPK) arguments[0]);
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.deleteFocusAreaOntologyTerm((com.ext.portlet.model.FocusAreaOntologyTerm) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.fetchFocusAreaOntologyTerm((com.ext.portlet.service.persistence.FocusAreaOntologyTermPK) arguments[0]);
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.getFocusAreaOntologyTerm((com.ext.portlet.service.persistence.FocusAreaOntologyTermPK) arguments[0]);
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.getFocusAreaOntologyTerms(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.getFocusAreaOntologyTermsCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.updateFocusAreaOntologyTerm((com.ext.portlet.model.FocusAreaOntologyTerm) arguments[0]);
        }

        if (_methodName632.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes632, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName633.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes633, parameterTypes)) {
            FocusAreaOntologyTermLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName638.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes638, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.findTermsByFocusArea((java.lang.Long) arguments[0]);
        }

        if (_methodName639.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes639, parameterTypes)) {
            FocusAreaOntologyTermLocalServiceUtil.addAreaTerm((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1]);

            return null;
        }

        if (_methodName640.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes640, parameterTypes)) {
            FocusAreaOntologyTermLocalServiceUtil.removeAreaTerm((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1]);

            return null;
        }

        if (_methodName641.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes641, parameterTypes)) {
            FocusAreaOntologyTermLocalServiceUtil.store((com.ext.portlet.model.FocusAreaOntologyTerm) arguments[0]);

            return null;
        }

        if (_methodName642.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes642, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.getTerm((com.ext.portlet.model.FocusAreaOntologyTerm) arguments[0]);
        }

        if (_methodName643.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes643, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.getArea((com.ext.portlet.model.FocusAreaOntologyTerm) arguments[0]);
        }

        if (_methodName644.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes644, parameterTypes)) {
            return FocusAreaOntologyTermLocalServiceUtil.getFocusAreaOntologyTermIdsByFocusAreaAndSpaceId(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
