package org.xcolab.view.util;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;

import org.xcolab.view.util.entity.flash.AlertMessage;

import static org.mockito.Matchers.anyString;

public class AlertMessageMockHelper {
    public static void mockAlertMessage(){
        PowerMockito.mockStatic(AlertMessage.class);

        Mockito.when(AlertMessage.success(anyString()))
                .thenAnswer(new Answer<AlertMessage>() {
                    @Override
                    public AlertMessage answer(InvocationOnMock invocation)
                            throws Throwable {

                        return AlertMessage.CHANGES_SAVED;
                    }
                });
    }

}
