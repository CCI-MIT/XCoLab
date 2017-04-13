package org.xcolab.view.util.clienthelpers;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;

import static org.mockito.Matchers.anyString;

public class AdminClientMockerHelper {

    public static void mockAdminClient(){

        PowerMockito.mockStatic(AdminClient.class);
        //generic mock for ConfigAttribute
        Mockito.when(AdminClient.getConfigurationAttribute(anyString()))
                .thenAnswer(new Answer<ConfigurationAttribute>() {
                    @Override
                    public ConfigurationAttribute answer(InvocationOnMock invocation)
                            throws Throwable {
                        Object[] arguments = invocation.getArguments();
                        if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                            String key = (String) arguments[0];
                            if (key.equals("ACTIVE_THEME")) {
                                return new ConfigurationAttribute("ACTIVE_THEME", 0l, 0l,
                                        "CLIMATE_COLAB", 0d);
                            } else {
                                return new ConfigurationAttribute(key, 0l, 0l, "12312312332", 0d);
                            }

                        }

                        return new ConfigurationAttribute("GOOGLE_RECAPTCHA_SITE_KEY", 0l, 0l,
                                "12312312332", 0d);
                    }
                });
    }
}
