package org.xcolab.view.crons;

import org.ocpsoft.common.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;

import java.io.IOException;

@Component
@ConditionalOnProperty(prefix = "xcolab.crons", matchIfMissing = true,
        name = {"enabled", "promotion.enabled"})
public class PromotionCron {

    private static final Logger _log = LoggerFactory.getLogger(PromotionCron.class);

    private static final long RATE = 60_000;
    private static final long DELAY = 60_000;

    public PromotionCron() {
        _log.info("Initializing promotion cron with rate = {}s", RATE / 1000);
    }

    @Scheduled(fixedRate = RATE, initialDelay = DELAY)
    public int doPromotion() {
        _log.debug("Executing promotions...");
        int promotedProposals = StaticContestContext.getContestClient().autoPromoteProposals();
        if (promotedProposals > 0) {
            _log.info("Promoted {} proposals.", promotedProposals);
            ServiceRequestUtils.clearCache(CacheName.PROPOSAL_LIST);
            ServiceRequestUtils.clearCache(CacheName.PROPOSAL_LIST_CLOSED);
        }
        return promotedProposals;
    }

    @Controller
    @ConditionalOnProperty(prefix = "xcolab.crons", matchIfMissing = true,
            name = "promotion.http-endpoint.enabled")
    public static class TriggerController {

        private final PromotionCron promotionCron;

        @Autowired
        public TriggerController(PromotionCron promotionCron) {
            _log.info("Initializing http endpoint to trigger promotions.");
            Assert.notNull(promotionCron, "PromotionCron bean is required");
            this.promotionCron = promotionCron;
        }

        @RequestMapping("/crons/promotions/run")
        public ResponseEntity<String> triggerPromotion() throws IOException {
            _log.debug("Cron triggered via http endpoint.");
            final int promotedProposals = promotionCron.doPromotion();
            return ResponseEntity.ok("Promoted " + promotedProposals + " proposals.");
        }
    }
}
