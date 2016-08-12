package org.xcolab.util.http.exceptions;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

@SuppressWarnings("ThrowableInstanceNeverThrown")
public class ServiceExceptionTranslatorUtilTest {

    private static final String HTTP_500_RUNTIME_EXCEPTION_STRING = "{\"timestamp\":1471028566513,"
                    + "\"status\":500, \"error\":\"Internal Server Error\""
                    + ",\"exception\":\"java.lang.RuntimeException\""
                    + ",\"message\":\"test\""
                    + ",\"path\":\"/members-service/messages\"}";
    private static final HttpStatusCodeException HTTP_500_SERVER_ERROR_EXCEPTION = new HttpServerErrorException(
            HttpStatus.INTERNAL_SERVER_ERROR, "",
            HTTP_500_RUNTIME_EXCEPTION_STRING.getBytes(),
            null);

    private static final String HTTP_400_BAD_REQUEST_EXCEPTION_STRING = "{\"timestamp\":1471041676535,"
            + "\"status\":400, \"error\":\"Bad Request\","
            + "\"exception\":\"org.springframework.web.method.annotation.MethodArgumentTypeMismatchException\","
            + "\"message\":\"[omitted]\","
            + "\"path\":\"/members-service/messages/test\"}";
    private static final HttpStatusCodeException HTTP_400_BAD_REQUEST_EXCEPTION = new HttpClientErrorException(
            HttpStatus.BAD_REQUEST, "",
            HTTP_400_BAD_REQUEST_EXCEPTION_STRING.getBytes(),
            null);

    private static final String HTTP_404_NOT_FOUND_EXCEPTION_STRING = "{\"timestamp\":1471041738772,"
            + "\"status\":404, \"error\":\"Not Found\","
            + "\"exception\":\"org.xcolab.service.members.exceptions.NotFoundException\","
            + "\"message\":\"Object Not Found\","
            + "\"path\":\"/members-service/messages/112125234645345\"}";
    private static final HttpStatusCodeException HTTP_404_NOT_FOUND_EXCEPTION = new HttpClientErrorException(
            HttpStatus.NOT_FOUND, "",
            HTTP_404_NOT_FOUND_EXCEPTION_STRING.getBytes(),
            null);

    private static final HttpStatusCodeException HTTP_404_EMPTY_CLIENT_ERROR_EXCEPTION = new HttpClientErrorException(
            HttpStatus.NOT_FOUND, "",
            "".getBytes(),
            null);

    @Test(expected = ServiceNotFoundException.class)
    public void testGetExceptionObject__ServiceNotFoundException() throws Exception {
        ServiceExceptionTranslatorUtil.getExceptionObject(HTTP_404_EMPTY_CLIENT_ERROR_EXCEPTION, "");
    }

    @Test
    public void testGetExceptionObject__500InternalServerError() throws Exception {
        final HttpServiceExceptionObject exceptionObject = ServiceExceptionTranslatorUtil
                .getExceptionObject(HTTP_500_SERVER_ERROR_EXCEPTION, "");
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
    public void testTranslateException__500InternalServerError() throws Exception {
        ServiceExceptionTranslatorUtil.translateException(HTTP_500_SERVER_ERROR_EXCEPTION, "");
    }

    @Test(expected = Http400BadRequestException.class)
    public void testTranslateException__400BadRequest() throws Exception {
        ServiceExceptionTranslatorUtil.translateException(HTTP_400_BAD_REQUEST_EXCEPTION, "");
    }

    @Test(expected = UncheckedEntityNotFoundException.class)
    public void testTranslateException__404NotFound() throws Exception {
        ServiceExceptionTranslatorUtil.translateException(HTTP_404_NOT_FOUND_EXCEPTION, "");
    }

}