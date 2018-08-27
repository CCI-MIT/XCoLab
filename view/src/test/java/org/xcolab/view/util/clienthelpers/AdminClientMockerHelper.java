package org.xcolab.view.util.clienthelpers;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.exceptions.ConfigurationAttributeNotFoundException;

import static org.mockito.Matchers.anyString;

public class AdminClientMockerHelper {

    public static void mockAdminClient() {

        PowerMockito.mockStatic(AdminClient.class);
        //generic mock for ConfigAttribute
        Mockito.when(AdminClient.getConfigurationAttribute(anyString(), anyString()))
                .thenAnswer(invocation -> {
                    Object[] arguments = invocation.getArguments();
                    String key = (String) arguments[0];

                    // throw not found exception so the default value is used
                    throw new ConfigurationAttributeNotFoundException(key);
                });
    }
}
