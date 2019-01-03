package org.xcolab.view.config.sentry;

import io.sentry.event.interfaces.HttpInterface;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

public class CustomSentryHttpInterface extends HttpInterface {

    // These entries will be shortened to 7 characters
    public static final List<String> COOKIES_TO_CROP = Arrays.asList("SESSION", "XSRF-TOKEN", "remember-me");

    public static final List<String> COOKIES_TO_REMOVE = Arrays.asList();

    public static final List<String> HEADERS_TO_REMOVE = Arrays.asList("cookies");

    public CustomSentryHttpInterface(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Map<String, Collection<String>> getHeaders() {
        final Map<String, Collection<String>> headers = new HashMap<>(super.getHeaders());
        headers.entrySet().removeIf(header -> HEADERS_TO_REMOVE.contains(header.getKey()));
        return Collections.unmodifiableMap(headers);
    }

    @Override
    public Map<String, String> getCookies() {
        Map<String, String> cookies = new HashMap<>(super.getCookies());
        //Remove cookies that start with "_" - these are set by someone else (e.g. GA)
        cookies.entrySet().removeIf(header -> header.getKey().startsWith("_"));
        cookies.entrySet().removeIf(header -> COOKIES_TO_REMOVE.contains(header.getKey()));

        for (Entry<String, String> entry : cookies.entrySet()) {
            if (COOKIES_TO_CROP.contains(entry.getKey())) {
                entry.setValue(entry.getValue().substring(0, 7) + "...");
            }
        }
        return Collections.unmodifiableMap(cookies);
    }
}
