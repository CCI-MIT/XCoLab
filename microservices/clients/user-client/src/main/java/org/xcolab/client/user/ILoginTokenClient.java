package org.xcolab.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.LoginToken;
import org.xcolab.client.user.pojo.TokenValidity;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

@FeignClient("xcolab-user-service")
public interface ILoginTokenClient {

    @GetMapping("/loginTokens/{tokenId}/validate")
    TokenValidity validateToken(@PathVariable String tokenId, @RequestParam String tokenKey)
            throws MemberNotFoundException;

    @PostMapping("/loginTokens/{tokenId}/invalidate")
    void invalidateToken(@PathVariable String tokenId);

    @GetMapping("/loginTokens/{tokenId}/member")
    UserWrapper getUserForToken(@PathVariable String tokenId)
            throws MemberNotFoundException;

    @PostMapping("/members/{userId}/loginToken")
    LoginToken generateToken(@PathVariable long userId) throws MemberNotFoundException;


    default TokenValidity validateLoginToken(String tokenId, String tokenKey) {
        try{
            return validateToken(tokenId,tokenKey);
        }catch (MemberNotFoundException e){
            return TokenValidity.INVALID;
        }
    }
    default void invalidateLoginToken(String tokenId) {
        invalidateToken(tokenId);
    }

    default UserWrapper getMemberForLoginToken(String tokenId) {
        try {
            return getUserForToken(tokenId);
        }catch (MemberNotFoundException e){
            return null;
        }
    }
    default LoginToken createLoginToken(long userId) {
        try{
            return generateToken(userId);
        }catch (MemberNotFoundException e){
            return null;
        }
    }
}
