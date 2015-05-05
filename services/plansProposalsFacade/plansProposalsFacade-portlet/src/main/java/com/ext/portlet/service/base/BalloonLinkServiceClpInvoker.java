package com.ext.portlet.service.base;

import com.ext.portlet.service.BalloonLinkServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class BalloonLinkServiceClpInvoker {
    private String _methodName642;
    private String[] _methodParameterTypes642;
    private String _methodName643;
    private String[] _methodParameterTypes643;

    public BalloonLinkServiceClpInvoker() {
        _methodName642 = "getBeanIdentifier";

        _methodParameterTypes642 = new String[] {  };

        _methodName643 = "setBeanIdentifier";

        _methodParameterTypes643 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName642.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes642, parameterTypes)) {
            return BalloonLinkServiceUtil.getBeanIdentifier();
        }

        if (_methodName643.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes643, parameterTypes)) {
            BalloonLinkServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
