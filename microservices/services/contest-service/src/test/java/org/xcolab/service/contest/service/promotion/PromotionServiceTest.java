package org.xcolab.service.contest.service.promotion;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.service.contest.service.contestphase.ContestPhaseService;
import org.xcolab.service.contest.utils.promotion.PromotionService;
import org.xcolab.util.http.ServiceRequestUtils;

import java.util.Date;

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
@Ignore
public class PromotionServiceTest {

    @Autowired
    PromotionService promotionService;

    @Before
    public void setup() throws Exception {
        ServiceRequestUtils.setInitialized(true);
    }

    @Test
    @Ignore
    public void shouldForcePromotionOfProposalInPhase() throws Exception {

        promotionService.doPromotion(new Date());
    }
}
