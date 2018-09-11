package org.xcolab.service.contest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.service.contest.domain.tosagreement.TosAgreementDao;

@RestController
public class TosAgreementController {

    @Autowired
    private TosAgreementDao tosAgreementDao;

    @GetMapping("/contests/{contestId}/memberAgreedToTos/{memberId}")
    public boolean hasMemberAgreedToTos(@PathVariable long contestId, @PathVariable long memberId) {
        return tosAgreementDao.hasMemberAgreedToContestTos(contestId, memberId);
    }

    @PostMapping("/contests/{contestId}/memberAgreedToTos")
    public void memberAgreedToTos(@PathVariable long contestId, @RequestParam long memberId,
            @RequestBody boolean agreed) {
        tosAgreementDao.setMemberAgreedToContestTos(contestId, memberId, agreed);
    }
}
