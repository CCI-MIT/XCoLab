package org.xcolab.view.pages.proposals.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ProposalPromotionController {

    private static final Logger log = LoggerFactory.getLogger(ProposalPromotionController.class);

    @GetMapping("/triggers/contests/autoPromotion")
    public ResponseEntity<String> autoPromote(HttpServletResponse response) {
        int promotedProposals = ContestClientUtil.autoPromoteProposals();
        if (promotedProposals > 0) {
            log.info("Promoted {} proposals.", promotedProposals);
            ServiceRequestUtils.clearCache(CacheName.PROPOSAL_LIST);
            ServiceRequestUtils.clearCache(CacheName.PROPOSAL_LIST_CLOSED);
        }
        response.setStatus(HttpStatus.NO_CONTENT.value());
        return ResponseEntity.ok("Promoted " + promotedProposals + " proposals.");
    }
}
