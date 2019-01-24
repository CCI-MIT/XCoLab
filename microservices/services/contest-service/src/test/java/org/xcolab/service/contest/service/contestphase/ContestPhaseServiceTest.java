package org.xcolab.service.contest.service.contestphase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.ProposalPhaseClient;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.util.http.ServiceRequestUtils;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;

@RunWith(PowerMockRunner.class)
@org.powermock.modules.junit4.PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@OverrideAutoConfiguration(enabled = false)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@PrepareForTest({
        ProposalWrapper.class,
        ContestWrapper.class
})
@ComponentScan("org.xcolab.service.contest")
@ComponentScan("org.xcolab.client")
@ActiveProfiles("test")
public class ContestPhaseServiceTest {

    @Autowired
    ContestPhaseService contestPhaseService;

    @Autowired
    ProposalPhaseClient proposalPhaseClient;

    @Autowired
    IProposalClient proposalClient;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() throws Exception {

        ServiceRequestUtils.setInitialized(true);

        Mockito.when(proposalPhaseClient
                .isProposalContestPhaseAttributeSetAndTrue(anyLong(),anyLong(),anyString()))
                .thenAnswer(invocation -> false);

        Mockito.when(proposalClient.getProposal(anyLong()))
                .thenAnswer(invocation -> {
                    ProposalWrapper proposal = Mockito.mock(ProposalWrapper.class);
                    proposal.setId(1333850L);
                    return proposal;
                });
    }

    @Test
    public void shouldForcePromotionOfProposalInPhase() throws Exception {

        contestPhaseService.forcePromotionOfProposalInPhase(1333850L, 1318613L);
    }

    @Test
    public void shouldFailPromotionOfProposalInPhaseOnPhaseNotFound() throws Exception {

        exception.expect(NotFoundException.class);
        contestPhaseService.forcePromotionOfProposalInPhase(1333851L, 1318614L);

    }
}
