package org.xcolab.view.util.clienthelpers;

import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import org.xcolab.client.admin.IAdminClient;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Matchers.anyString;

public class AdminClientMockerHelper {
    public static IAdminClient mockAdminClient() {
        IAdminClient adminClient = Mockito.mock(IAdminClient.class);
        //generic mock for ConfigAttribute
        final Answer<Object> answer = invocation -> {
            Object[] arguments = invocation.getArguments();
            String key = (String) arguments[0];

            // return empty Optional so the default value is used
            return Optional.empty();
        };
        Mockito.when(adminClient.getConfigurationAttribute(anyString(), anyString()))
                .thenAnswer(answer);
        Mockito.when(adminClient.getConfigurationAttribute(anyString(), isNull()))
                .thenAnswer(answer);

        return adminClient;
    }
}
