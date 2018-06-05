package org.xcolab.util.http.exceptions.translation.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import org.xcolab.util.http.exceptions.Http400BadRequestException;
import org.xcolab.util.http.exceptions.Http500InternalServiceException;
import org.xcolab.util.http.exceptions.ServiceNotFoundException;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;

@SuppressWarnings("ThrowableInstanceNeverThrown")
public class ServiceExceptionTranslatorTest {

    private static final String HTTP_500_RUNTIME_EXCEPTION_STRING = "{\"timestamp\":1471028566513,"
                    + "\"status\":500, \"error\":\"Internal Server Error\""
                    + ",\"exception\":\"java.lang.RuntimeException\""
                    + ",\"message\":\"test\""
                    + ",\"path\":\"/members-service/messages\"}";
    private static final HttpStatusCodeException HTTP_500_SERVER_ERROR_EXCEPTION = new HttpServerErrorException(
            HttpStatus.INTERNAL_SERVER_ERROR, "", new ServiceResponseHeaders(),
            HTTP_500_RUNTIME_EXCEPTION_STRING.getBytes(),
            null);

    private static final String HTTP_400_BAD_REQUEST_EXCEPTION_STRING = "{\"timestamp\":1471041676535,"
            + "\"status\":400, \"error\":\"Bad Request\","
            + "\"exception\":\"org.springframework.web.method.annotation.MethodArgumentTypeMismatchException\","
            + "\"message\":\"[omitted]\","
            + "\"path\":\"/members-service/messages/test\"}";
    private static final HttpStatusCodeException HTTP_400_BAD_REQUEST_EXCEPTION = new HttpClientErrorException(
            HttpStatus.BAD_REQUEST, "", new ServiceResponseHeaders(),
            HTTP_400_BAD_REQUEST_EXCEPTION_STRING.getBytes(),
            null);

    private static final String HTTP_404_NOT_FOUND_EXCEPTION_STRING = "{\"timestamp\":1471041738772,"
            + "\"status\":404, \"error\":\"Not Found\","
            + "\"exception\":\"org.xcolab.service.members.exceptions.NotFoundException\","
            + "\"message\":\"Object Not Found\","
            + "\"path\":\"/members-service/messages/112125234645345\"}";
    private static final HttpStatusCodeException HTTP_404_NOT_FOUND_EXCEPTION = new HttpClientErrorException(
            HttpStatus.NOT_FOUND, "", new ServiceResponseHeaders(),
            HTTP_404_NOT_FOUND_EXCEPTION_STRING.getBytes(),
            null);

    private static final HttpStatusCodeException HTTP_404_EMPTY_CLIENT_ERROR_EXCEPTION = new HttpClientErrorException(
            HttpStatus.NOT_FOUND, "", new ServiceResponseHeaders(),
            "".getBytes(),
            null);

    @Test(expected = ServiceNotFoundException.class)
    public void testGetExceptionObject__ServiceNotFoundException() {
        final ServiceExceptionTranslator translator = new ServiceExceptionTranslator();
        translator.getExceptionObject(
                HTTP_404_EMPTY_CLIENT_ERROR_EXCEPTION, null, null);
    }

    @Test
    public void testGetExceptionObject__500InternalServerError() {
        final ServiceExceptionTranslator translator = new ServiceExceptionTranslator();
        final HttpServiceExceptionObject exceptionObject = translator
                .getExceptionObject(HTTP_500_SERVER_ERROR_EXCEPTION, null, null);
        Assert.assertNotNull(exceptionObject);
        Assert.assertEquals("Status code parsed wrong",
                500, exceptionObject.getStatus());
        Assert.assertEquals("Error field parsed wrong",
                "Internal Server Error", exceptionObject.getError());
        Assert.assertEquals("Exception type parsed wrong",
                "java.lang.RuntimeException", exceptionObject.getException());
        Assert.assertEquals("Message parsed wrong",
                "test", exceptionObject.getMessage());
        Assert.assertEquals("Path parsed wrong",
                "/members-service/messages", exceptionObject.getPath());
    }

    @Test(expected = Http500InternalServiceException.class)
    public void testTranslateException__500InternalServerError() {
        final ServiceExceptionTranslator translator = new ServiceExceptionTranslator();
        translator.translateException(HTTP_500_SERVER_ERROR_EXCEPTION, null, null);
    }

    @Test(expected = Http400BadRequestException.class)
    public void testTranslateException__400BadRequest() {
        final ServiceExceptionTranslator translator = new ServiceExceptionTranslator();
        translator.translateException(HTTP_400_BAD_REQUEST_EXCEPTION, null, null);
    }

    @Test(expected = UncheckedEntityNotFoundException.class)
    public void testTranslateException__404NotFound() {
        final ServiceExceptionTranslator translator = new ServiceExceptionTranslator();
        translator.translateException(HTTP_404_NOT_FOUND_EXCEPTION, null, null);
    }

    @Test
    public void testTranslateException__shouldContainServiceName() {
        final ServiceExceptionTranslator translator = new ServiceExceptionTranslator();
        try {
            translator.translateException(HTTP_500_SERVER_ERROR_EXCEPTION, null, null);
        } catch (Http500InternalServiceException e) {
            Assert.assertEquals("Service name not captures from headers.",
                    "my-service-name", e.getServiceName());
            return;
        }
        Assert.fail("[Test precondition failed] no exception thrown");
    }

    private static class ServiceResponseHeaders extends HttpHeaders {
        ServiceResponseHeaders() {
            super();
            add("Content-Type", "application/json");
            add("X-Application-Context", "my-service-name");
        }
    }
}
