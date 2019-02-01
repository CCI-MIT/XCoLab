package org.xcolab.service.contest.service.promotion;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import org.xcolab.service.contest.utils.promotion.PromotionService;
import org.xcolab.util.http.ServiceRequestUtils;

import java.util.Date;

@RunWith(PowerMockRunner.class)
@org.powermock.modules.junit4.PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@OverrideAutoConfiguration(enabled = false)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@PrepareForTest({
        ServiceRequestUtils.class,
        ProposalWrapper.class,
        ContestWrapper.class
})
@ComponentScan("org.xcolab.service.contest")
@ComponentScan("org.xcolab.client")
@ActiveProfiles("test")
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
