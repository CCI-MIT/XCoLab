package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.user.ILoginTokenClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.LoginTokenWrapper;
import org.xcolab.client.user.pojo.wrapper.TokenValidityWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.service.members.domain.member.UserDao;
import org.xcolab.service.members.service.member.UserService;
import org.xcolab.service.members.util.SecureRandomUtil;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@RestController
public class LoginTokenController implements ILoginTokenClient {

    private final UserDao memberDao;
    private final UserService memberService;

    @Autowired
    public LoginTokenController(UserDao memberDao, UserService memberService) {
        this.memberDao = memberDao;
        this.memberService = memberService;
    }

    @GetMapping("/loginTokens/{tokenId}/validate")
    public TokenValidityWrapper validateToken(@PathVariable String tokenId, @RequestParam String tokenKey)
            throws MemberNotFoundException {
        UserWrapper member = memberDao.findOneByLoginTokenId(tokenId)
                .orElseThrow(MemberNotFoundException::new);
        final boolean isValid = memberService.validatePassword(tokenKey, member.getLoginTokenKey());
        if (!isValid) {
            return TokenValidityWrapper.INVALID;
        }
        if (Instant.now().isAfter(member.getLoginTokenExpirationDate().toInstant())) {
            return TokenValidityWrapper.EXPIRED;
        }
        return TokenValidityWrapper.VALID;
    }

    @PostMapping("/loginTokens/{tokenId}/invalidate")
    public void invalidateToken(@PathVariable String tokenId) {
        final Optional<UserWrapper> optionalUser = memberDao.findOneByLoginTokenId(tokenId);
        if (optionalUser.isPresent()) {
            UserWrapper member = optionalUser.get();
            member.setLoginTokenExpirationDate(Timestamp.from(Instant.now()));
            memberDao.updateUser(member);
        }
    }

    @GetMapping("/loginTokens/{tokenId}/member")
    public UserWrapper getUserForToken(@PathVariable String tokenId)
            throws MemberNotFoundException {
        return memberDao.findOneByLoginTokenId(tokenId).orElseThrow(MemberNotFoundException::new);
    }

    @PostMapping("/members/{userId}/loginToken")
    public LoginTokenWrapper generateToken(@PathVariable long userId)
            throws MemberNotFoundException {
        String tokenId = Long.toHexString(SecureRandomUtil.nextLong());
        String tokenKey = Long.toHexString(SecureRandomUtil.nextLong());
        final long expirationInDays = ConfigurationAttributeKey.LOGIN_LINK_EXPIRATION_IN_DAYS.get();
        Instant tokenExpirationDate = Instant.now().plus(expirationInDays, ChronoUnit.DAYS);

        final UserWrapper member = memberDao.getUser(userId).orElseThrow(MemberNotFoundException::new);
        member.setLoginTokenId(tokenId);
        member.setLoginTokenKey(memberService.hashPassword(tokenKey));
        member.setLoginTokenExpirationDate(Timestamp.from(tokenExpirationDate));
        memberDao.updateUser(member);

        return new LoginTokenWrapper(tokenId, tokenKey, tokenExpirationDate);
    }

}
