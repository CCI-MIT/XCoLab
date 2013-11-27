package org.xcolab.portlets.loginregister.singlesignon;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.InMemoryConsumerAssociationStore;
import org.openid4java.consumer.InMemoryNonceVerifier;

/**
 * @author Jorge Ferrer
 */
public class OpenIdUtil {

    public static ConsumerManager getConsumerManager() {
        _instance._initialize();

        return _instance._manager;
    }

    public static String getScreenName(String openId) {
        String result = normalize(openId);

        if (result.startsWith(Http.HTTP_WITH_SLASH)) {
            result = result.substring(Http.HTTP_WITH_SLASH.length());
        }

        if (result.startsWith(Http.HTTPS_WITH_SLASH)) {
            result = result.substring(Http.HTTPS_WITH_SLASH.length());
        }

        result = StringUtil.replace(
                result,
                new String[]{StringPool.SLASH, StringPool.UNDERLINE},
                new String[]{StringPool.PERIOD, StringPool.PERIOD});

        return result;
    }

    public static String normalize(String identity) {
        String result = identity;

        if (result.endsWith(StringPool.SLASH)) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    private void _initialize() {
        try {
            if (_manager == null) {
                _manager = new ConsumerManager();

                _manager.setAssociations(
                        new InMemoryConsumerAssociationStore());
                _manager.setNonceVerifier(new InMemoryNonceVerifier(5000));
            }
        }
        catch (Exception ce) {
            _log.error(ce.getMessage());
        }
    }

    private static Log _log = LogFactoryUtil.getLog(OpenIdUtil.class);

    private static OpenIdUtil _instance = new OpenIdUtil();

    private ConsumerManager _manager;

}