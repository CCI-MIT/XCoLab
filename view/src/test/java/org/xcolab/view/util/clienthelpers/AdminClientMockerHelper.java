package org.xcolab.view.util.clienthelpers;

import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.exceptions.ConfigurationAttributeNotFoundException;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Matchers.anyString;

public class AdminClientMockerHelper {

    public static void mockAdminClient() {

        PowerMockito.mockStatic(AdminClient.class);
        //generic mock for ConfigAttribute
        final Answer<Object> answer = invocation -> {
            Object[] arguments = invocation.getArguments();
            String key = (String) arguments[0];

            // throw not found exception so the default value is used
            throw new ConfigurationAttributeNotFoundException(key);
        };
        Mockito.when(AdminClient.getConfigurationAttribute(anyString(), anyString()))
                .thenAnswer(answer);
        Mockito.when(AdminClient.getConfigurationAttribute(anyString(), isNull()))
                .thenAnswer(answer);
    }
}
