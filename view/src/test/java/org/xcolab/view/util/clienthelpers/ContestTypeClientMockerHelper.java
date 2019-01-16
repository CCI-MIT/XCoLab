package org.xcolab.view.util.clienthelpers;

import org.mockito.Mockito;

import org.xcolab.client.admin.IContestTypeClient;
import org.xcolab.client.admin.pojo.MockContestType;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Matchers.anyString;

public class ContestTypeClientMockerHelper {

    public static IContestTypeClient mockContestTypeClient() {
        IContestTypeClient contestTypeClient = Mockito.mock(IContestTypeClient.class);
        Mockito.when(contestTypeClient.getAllContestTypes())
                .thenReturn(new ArrayList<>());
        Mockito.when(contestTypeClient.getContestType(anyLong()))
                .thenReturn(new MockContestType(0));
        Mockito.when(contestTypeClient.getContestType(anyLong(), anyString()))
                .thenReturn(new MockContestType(0, "en"));

        return contestTypeClient;
    }
}
