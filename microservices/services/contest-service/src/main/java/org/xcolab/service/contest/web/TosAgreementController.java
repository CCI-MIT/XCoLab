package org.xcolab.service.contest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.service.contest.domain.tosagreement.TosAgreementDao;

@RestController
public class TosAgreementController {

    @Autowired
    private TosAgreementDao tosAgreementDao;

    @GetMapping("/contests/{contestId}/memberAgreedToToS/{memberId}")
    public long hasMemberAgreedToToS(@PathVariable long contestId, @PathVariable long memberId) {
        return tosAgreementDao.hasMemberAgreedToConestToS(contestId, memberId);
    }

    @PostMapping("/contests/{contestId}/memberAgreedToToS")
    public void memberAgreedToToS(@PathVariable long contestId, @RequestBody long memberId) {
        tosAgreementDao.setMemberAgreedToContestToS(contestId, memberId);
    }
}
