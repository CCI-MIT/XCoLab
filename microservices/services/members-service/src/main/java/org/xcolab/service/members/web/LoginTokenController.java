package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.model.tables.pojos.User;
import org.xcolab.service.members.domain.member.UserDao;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.members.service.member.UserService;
import org.xcolab.service.members.util.SecureRandomUtil;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@RestController
public class LoginTokenController {

    private final UserDao memberDao;
    private final UserService memberService;

    @Autowired
    public LoginTokenController(UserDao memberDao, UserService memberService) {
        this.memberDao = memberDao;
        this.memberService = memberService;
    }

    @GetMapping("/loginTokens/{tokenId}/validate")
    public TokenValidity validateToken(@PathVariable String tokenId, @RequestParam String tokenKey)
            throws NotFoundException {
        User member = memberDao.findOneByLoginTokenId(tokenId)
                .orElseThrow(NotFoundException::new);
        final boolean isValid = memberService.validatePassword(tokenKey, member.getLoginTokenKey());
        if (!isValid) {
            return TokenValidity.INVALID;
        }
        if (Instant.now().isAfter(member.getLoginTokenExpirationDate().toInstant())) {
            return TokenValidity.EXPIRED;
        }
        return TokenValidity.VALID;
    }

    @PostMapping("/loginTokens/{tokenId}/invalidate")
    public void invalidateToken(@PathVariable String tokenId) {
        final Optional<User> optionalUser = memberDao.findOneByLoginTokenId(tokenId);
        if (optionalUser.isPresent()) {
            User member = optionalUser.get();
            member.setLoginTokenExpirationDate(Timestamp.from(Instant.now()));
            memberDao.updateUser(member);
        }
    }

    @GetMapping("/loginTokens/{tokenId}/member")
    public User getUserForToken(@PathVariable String tokenId)
            throws NotFoundException {
        return memberDao.findOneByLoginTokenId(tokenId).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/members/{userId}/loginToken")
    public LoginToken generateToken(@PathVariable long userId) throws NotFoundException {
        String tokenId = Long.toHexString(SecureRandomUtil.nextLong());
        String tokenKey = Long.toHexString(SecureRandomUtil.nextLong());
        final long expirationInDays = ConfigurationAttributeKey.LOGIN_LINK_EXPIRATION_IN_DAYS.get();
        Instant tokenExpirationDate = Instant.now().plus(expirationInDays, ChronoUnit.DAYS);

        final User member = memberDao.getUser(userId).orElseThrow(NotFoundException::new);
        member.setLoginTokenId(tokenId);
        member.setLoginTokenKey(memberService.hashPassword(tokenKey));
        member.setLoginTokenExpirationDate(Timestamp.from(tokenExpirationDate));
        memberDao.updateUser(member);

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

    public enum TokenValidity {
        VALID, INVALID, EXPIRED
    }
}
