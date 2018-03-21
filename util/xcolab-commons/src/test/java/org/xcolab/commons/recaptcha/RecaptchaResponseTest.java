package org.xcolab.commons.recaptcha;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

public class RecaptchaResponseTest {

    @Test
    public void testJsonDeserialization() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final RecaptchaResponse val = objectMapper.readerFor(RecaptchaResponse.class).readValue(
                "{\n" + "  \"success\": false,\n"
                        + "  \"challenge_ts\": \"2018-03-20T23:14:06Z\",\n"
                        + "  \"hostname\": \"localhost\",\n" + "  \"error-codes\": [\n"
                        + "    \"timeout-or-duplicate\"\n" + "  ]\n" + "}");
        Assert.assertEquals(val.isSuccess(), false);
        Assert.assertEquals(val.getErrorCodes(), Collections.singletonList("timeout-or-duplicate"));
    }

}
