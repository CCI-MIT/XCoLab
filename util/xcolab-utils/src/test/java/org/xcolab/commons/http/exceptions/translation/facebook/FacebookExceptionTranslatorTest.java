package org.xcolab.commons.http.exceptions.translation.facebook;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import org.xcolab.commons.http.exceptions.translation.facebook.FacebookErrorResponseObject.Error;
import org.xcolab.commons.http.exceptions.translation.facebook.FacebookExceptionTranslator.FacebookException;

public class FacebookExceptionTranslatorTest {

    private static final String FACEBOOK_OAUTH_ERROR_JSON = "{\n"
            + "\"error\": {\n"
            + "\"message\": \"Some message\",\n"
            + "\"type\": \"OAuthException\",\n"
            + "\"code\": 191,\n"
            + "\"fbtrace_id\": \"AbcD123\"\n"
            + "}\n"
            + "}";
    private static final HttpStatusCodeException FACEBOOK_OAUTH_EXCEPTION = new HttpServerErrorException(
            HttpStatus.BAD_REQUEST, "", new ContentTypeJsonHeaders(),
            FACEBOOK_OAUTH_ERROR_JSON.getBytes(),
            null);

    @Test
    public void testGetExceptionObject__oauthError() throws Exception {
        final FacebookExceptionTranslator translator = new FacebookExceptionTranslator();
        final FacebookErrorResponseObject exceptionObject = translator
                .getExceptionObject(FACEBOOK_OAUTH_EXCEPTION, null, null);
        Assert.assertNotNull(exceptionObject);
        final Error error = exceptionObject.getError();
        Assert.assertNotNull("Error object not parsed", error);
        Assert.assertEquals("Message parsed wrong",
                "Some message", error.getMessage());
        Assert.assertEquals("Type parsed wrong",
                "OAuthException", error.getType());
        Assert.assertEquals("Code parsed wrong", 191, error.getCode());
        Assert.assertEquals("fbtrace_id parsed wrong",
                "AbcD123", error.getFbtrace_id());
    }

    @Test(expected = FacebookException.class)
    public void testTranslateException__oauthError() throws Exception {
        final FacebookExceptionTranslator translator = new FacebookExceptionTranslator();
        translator.translateException(FACEBOOK_OAUTH_EXCEPTION, null, null);
    }

    private static class ContentTypeJsonHeaders extends HttpHeaders {
        ContentTypeJsonHeaders() {
            super();
            add("Content-Type", "application/json");
        }
    }
}
