package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.Member;
import org.xcolab.service.members.domain.member.MemberDao;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.members.service.member.MemberService;
import org.xcolab.service.members.util.SecureRandomUtil;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@RestController
public class LoginTokenController {

    private final MemberDao memberDao;
    private final MemberService memberService;

    @Autowired
    public LoginTokenController(MemberDao memberDao, MemberService memberService) {
        this.memberDao = memberDao;
        this.memberService = memberService;
    }

    @GetMapping("/loginTokens/{tokenId}/validate")
    public boolean validateToken(@PathVariable String tokenId, @RequestParam String tokenKey)
            throws NotFoundException {
        Member member = memberDao.findOneByLoginTokenId(tokenId)
                .orElseThrow(NotFoundException::new);
        return memberService.validatePassword(tokenKey, member.getLoginTokenKey());
    }

    @PostMapping("/loginTokens/{tokenId}/invalidate")
    public void invalidateToken(@PathVariable String tokenId, @RequestParam String tokenKey) {
        final Optional<Member> optionalMember = memberDao.findOneByLoginTokenId(tokenId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setLoginTokenId(null);
            member.setLoginTokenKey(null);
            member.setLoginTokenExpirationDate(null);
            memberDao.updateMember(member);
        }
    }

    @GetMapping("/loginTokens/{tokenId}/member")
    public Member getMemberForToken(@PathVariable String tokenId)
            throws NotFoundException {
        return memberDao.findOneByLoginTokenId(tokenId).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/members/{memberId}/loginToken")
    public LoginToken generateToken(@PathVariable long memberId) throws NotFoundException {
        String tokenId = Long.toHexString(SecureRandomUtil.nextLong());
        String tokenKey = Long.toHexString(SecureRandomUtil.nextLong());
        Instant tokenExpirationDate = Instant.now().plus(3, ChronoUnit.DAYS);

        final Member member = memberDao.getMember(memberId).orElseThrow(NotFoundException::new);
        member.setLoginTokenId(tokenId);
        member.setLoginTokenKey(memberService.hashPassword(tokenKey));
        member.setLoginTokenExpirationDate(Timestamp.from(tokenExpirationDate));
        memberDao.updateMember(member);

        return new LoginToken(tokenId, tokenKey, tokenExpirationDate);
    }

    public static class LoginToken {
        private final String tokenId;
        private final String tokenKey;
        private final Instant tokenExpirationDate;

        public LoginToken(String tokenId, String tokenKey, Instant tokenExpirationDate) {
            this.tokenId = tokenId;
            this.tokenKey = tokenKey;
            this.tokenExpirationDate = tokenExpirationDate;
        }

        public String getTokenId() {
            return tokenId;
        }

        public String getTokenKey() {
            return tokenKey;
        }

        public Instant getTokenExpirationDate() {
            return tokenExpirationDate;
        }
    }
}
