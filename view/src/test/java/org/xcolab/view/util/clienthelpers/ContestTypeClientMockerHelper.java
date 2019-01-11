package org.xcolab.view.util.clienthelpers;

import org.mockito.Mockito;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.pojo.MockContestType;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Matchers.anyString;

public class ContestTypeClientMockerHelper {

    public static ContestTypeClient mockContestTypeClient() {
        ContestTypeClient contestTypeClient = Mockito.mock(ContestTypeClient.class);
        Mockito.when(contestTypeClient.getAllContestTypes())
                .thenReturn(new ArrayList<>());
        Mockito.when(contestTypeClient.getContestType(anyLong()))
                .thenReturn(new MockContestType(0));
        Mockito.when(contestTypeClient.getContestType(anyLong(), anyString()))
                .thenReturn(new MockContestType(0, "en"));

        return contestTypeClient;
    }
}
