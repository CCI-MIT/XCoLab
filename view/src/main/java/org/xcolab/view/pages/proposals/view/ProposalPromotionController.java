package org.xcolab.view.pages.proposals.view;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.ContestClientUtil;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ProposalPromotionController {

    @GetMapping("/triggers/contests/autoPromotion")
    public ResponseEntity<String> autoPromote(HttpServletResponse response) {
        ContestClientUtil.autoPromoteProposals();
        response.setStatus(HttpStatus.NO_CONTENT.value());
        return ResponseEntity.ok("Promotion done.");
    }
}
