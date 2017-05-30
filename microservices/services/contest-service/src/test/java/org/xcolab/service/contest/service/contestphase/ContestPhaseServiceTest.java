package org.xcolab.service.contest.service.contestphase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.http.ServiceRequestUtils;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;

@RunWith(PowerMockRunner.class)
@org.powermock.modules.junit4.PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(
        properties = {
                "cache.active=false",
                "eureka.client.enabled=false",
                "spring.datasource.url=jdbc:h2:mem:testdb;MODE=MYSQL"
        }
)

@PrepareForTest({
        org.xcolab.util.http.ServiceRequestUtils.class,
        org.xcolab.client.proposals.ProposalClientUtil.class,
        org.xcolab.client.proposals.ProposalPhaseClientUtil.class,
        org.xcolab.client.proposals.pojo.Proposal.class,
        org.xcolab.client.contest.pojo.Contest.class
})


@ComponentScan("org.xcolab.service.contest")
@ComponentScan("org.xcolab.client")
public class ContestPhaseServiceTest {

    @Autowired
    ContestPhaseService contestPhaseService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() throws Exception {

        PowerMockito.mockStatic(ServiceRequestUtils.class);
        PowerMockito.mockStatic(ProposalClientUtil.class);
        PowerMockito.mockStatic(ProposalPhaseClientUtil.class);
        Mockito.when(ProposalPhaseClientUtil
                .isProposalContestPhaseAttributeSetAndTrue(anyLong(),anyLong(),anyString()))
                .thenAnswer(new Answer<Boolean>() {
                    @Override
                    public Boolean answer(InvocationOnMock invocation)
                            throws Throwable {
                       return false;

                    }
                });

        Mockito.when(ProposalClientUtil.getProposal(anyLong()))
                .thenAnswer(new Answer<Proposal>() {
                    @Override
                    public Proposal answer(InvocationOnMock invocation)
                            throws Throwable {
                        Proposal proposal = Mockito.mock(Proposal.class);
                        proposal.setProposalId(1333850l);
                        return proposal;

                    }
                });
    }

    @Test
    public void shouldForcePromotionOfProposalInPhase() throws Exception {

        contestPhaseService.forcePromotionOfProposalInPhase(1333850l,1318613l);
    }

    @Test
    public void shouldFailPromotionOfProposalInPhaseOnPhaseNotFound() throws Exception {

        exception.expect(NotFoundException.class);
        contestPhaseService.forcePromotionOfProposalInPhase(1333851l,1318614l);

    }
}
