package org.xcolab.view.pages.redballon.utils;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonTextNotFoundException;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFoundException;
import org.xcolab.client.balloons.pojo.BalloonLink;
import org.xcolab.client.balloons.pojo.BalloonText;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.tracking.TrackingClient;
import org.xcolab.client.tracking.pojo.Location;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.util.exceptions.ReferenceResolutionException;
import org.xcolab.view.auth.AuthenticationService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class BalloonService {

    private static final String URL_PLACEHOLDER = "URLPLACEHOLDER";

    public static final String SNP_LINK_URL = "/snp/{context}/link/{linkUuid}";

    private final AuthenticationService authenticationService;

    @Autowired
    public BalloonService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public BalloonLink createBalloonLink(String context, String email,
            BalloonUserTracking but)
            throws BalloonTextNotFoundException {

        BalloonLink link = new BalloonLink();
        link.setUuid_(UUID.randomUUID().toString());
        link.setBalloonUserUuid(but.getUuid_());
        link.setCreateDate(new Timestamp(new Date().getTime()));
        link.setTargetUrl(getSnpLinkUrl(context, link.getUuid_()));
        link = BalloonsClient.createBalloonLink(link);

        BalloonText text = BalloonsClient.getBalloonText(but.getBalloonTextId());
        String messageSubject = text.getEmailSubjectTemplate();
        String messageBody = text.getEmailTemplate()
                .replaceAll(URL_PLACEHOLDER, LinkUtils.getAbsoluteUrl(link.getTargetUrl()));

        final String fromEmail = ConfigurationAttributeKey.ADMIN_FROM_EMAIL.get();
        EmailClient.sendEmail(fromEmail, email, messageSubject, messageBody,
                true, fromEmail, but.getBalloonTextId());
        return link;
    }

    private String getSnpLinkUrl(String context, String linkUuid) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("context", context);
        uriVariables.put("linkUuid", linkUuid);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(SNP_LINK_URL);
        return uriBuilder.buildAndExpand(uriVariables).toUriString();
    }

    public BalloonUserTracking getOrCreateBalloonUserTracking(HttpServletRequest request,
            HttpServletResponse response, String parent, String linkUuid, String context) {

        //make sure we always track the correct user, even when impersonating
        Member member = authenticationService.getRealMemberOrNull();

		BalloonCookie cookie = BalloonCookie.from(request.getCookies())
                .orElseGet(BalloonCookie::new);
		if (cookie.getUuid() != null) {
            try {
                final BalloonUserTracking but =
                        BalloonsClient.getBalloonUserTracking(cookie.getUuid());
                if (member != null) {
                    but.updateUserIdIfEmpty(member.getId_());
                }
                return but;
            } catch (BalloonUserTrackingNotFoundException e) {
                throw ReferenceResolutionException
                        .toObject(BalloonUserTracking.class, cookie.getUuid())
                        .fromObject(BalloonCookie.class, "");
            }
        }

        if (member != null) {
            BalloonUserTracking but = getBalloonUserTrackingForMember(member);
            if (but != null) {
                but.updateUserIdIfEmpty(member.getId_());
                return but;
            }
        }

        if (member != null) {
            cookie.setUuid(member.getUuid());
        } else {
            cookie.setUuid(UUID.randomUUID().toString());
        }
        response.addCookie(cookie.getHttpCookie());

        return createBalloonUserTracking(cookie.getUuid(), parent, linkUuid, context, member,
                request.getRemoteAddr(), request.getHeader(HttpHeaders.REFERER),
                request.getHeader(HttpHeaders.USER_AGENT));
	}

    private BalloonUserTracking createBalloonUserTracking(String uuid, String parent,
            String linkUuid, String context, Member member,
            String remoteIp, String referrer, String userAgent) {
        BalloonUserTracking but = new BalloonUserTracking();
        but.setUuid_(uuid);
        but.setIp(remoteIp);
        but.setParent(parent);
        but.setBalloonLinkContext(context);
        but.setBalloonLinkUuid(linkUuid);
        but.setReferrer(referrer);
        but.setUserAgent(userAgent);

        // populate GeoLocation data
        Location location = TrackingClient.getLocationForIp(remoteIp);
        if (location != null) {
            but.setCity(location.getCity());
            but.setCountry(location.getCountry());
            but.setLatitude(location.getLatitude());
            but.setLongitude(location.getLongitude());
        }

        if (member != null && member.getId_() > 0) {
            but.setUserId(member.getUserId());
        }

        // pick random balloon text to be displayed
        List<BalloonText> texts = BalloonsClient.getAllEnabledBalloonTexts();
        if (!texts.isEmpty()) {
            but.setBalloonTextId(texts.get(RandomUtils.nextInt(0, texts.size())).getId_());
        } else {
            but.setBalloonTextId(0L);
        }

        BalloonsClient.createBalloonUserTracking(but);
        return but;
    }

    private BalloonUserTracking getBalloonUserTrackingForMember(Member member) {
        try {
            return BalloonsClient.getBalloonUserTracking(member.getUuid());
        } catch (BalloonUserTrackingNotFoundException ignored) {
            final String context = ConfigurationAttributeKey.SNP_CONTEXT.get();
            List<BalloonUserTracking> buts = BalloonsClient
                    .getBalloonUserTrackingByEmail(member.getEmailAddress(), context);

            return buts.stream().findFirst().orElse(null);
        }
    }

}
