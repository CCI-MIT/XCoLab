package org.xcolab.view.pages.redballoon.utils;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.xcolab.util.http.exceptions.ExceptionUtils.getOptional;

@Service
public class BalloonService {

    private static final Logger _log = LoggerFactory.getLogger(BalloonService.class);

    private static final String URL_PLACEHOLDER = "URLPLACEHOLDER";

    public static final String SNP_LINK_URL = "/snp/socialnetworkprize/link/{linkUuid}";
    private static final String NEW_BALLOON_USER_TRACKING_ATTRIBUTE =
            "org.xcolab.snp.newBalloonUserTracking";

    private final AuthenticationService authenticationService;

    @Autowired
    public BalloonService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public BalloonLink createBalloonLink(String email, BalloonUserTracking but)
            throws BalloonTextNotFoundException {

        BalloonLink link = new BalloonLink();
        link.setUuid_(UUID.randomUUID().toString());
        link.setBalloonUserUuid(but.getUuid_());
        link.setCreateDate(new Timestamp(new Date().getTime()));
        link.setTargetUrl(getSnpLinkUrl(link.getUuid_()));
        link = BalloonsClient.createBalloonLink(link);

        BalloonText text = BalloonsClient.getBalloonText(but.getBalloonTextId());
        String messageSubject = text.getEmailSubjectTemplate();
        String messageBody = text.getEmailTemplate()
                .replaceAll(URL_PLACEHOLDER, LinkUtils.getAbsoluteUrl(link.getTargetUrl()));

        final String fromEmail = ConfigurationAttributeKey.ADMIN_FROM_EMAIL.get();
        final String fromName = ConfigurationAttributeKey.COLAB_NAME.get();
        EmailClient.sendEmail(fromEmail, fromName, email, messageSubject, messageBody, true,
                fromEmail, fromName, but.getBalloonTextId());
        return link;
    }

    private String getSnpLinkUrl(String linkUuid) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("linkUuid", linkUuid);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(SNP_LINK_URL);
        return uriBuilder.buildAndExpand(uriVariables).toUriString();
    }

    public Optional<BalloonUserTracking> getBalloonUserTracking(HttpServletRequest request,
            HttpServletResponse response) {

        // a new tracking created earlier in this request won't show up in the cookies
        // it's saved in an attribute so we can use that instead
        final BalloonUserTracking createdBut =
                (BalloonUserTracking) request.getAttribute(NEW_BALLOON_USER_TRACKING_ATTRIBUTE);
        if (createdBut != null) {
            return Optional.of(createdBut);
        }

        //make sure we always track the correct user, even when impersonating
        Member member = authenticationService.getRealMemberOrNull();

        Optional<BalloonCookie> cookieOpt = BalloonCookie.from(request.getCookies());
        if (cookieOpt.isPresent()) {
            try {
                BalloonUserTracking but = getBalloonUserTrackingFromCookie(cookieOpt.get());

                if (member == null) {
                    return Optional.of(but);
                }

                final boolean butLinkedToOtherMember =
                        but.getUserId() != null && but.getUserId() != member.getId_();
                if (!butLinkedToOtherMember) {
                    but.updateUserIdAndEmailIfEmpty(member.getId_(), member.getEmailAddress());
                    return Optional.of(but);
                }
            } catch (ReferenceResolutionException rre) {
                return Optional.empty();
            }
        }

        if (member != null) {
            BalloonUserTracking but = getBalloonUserTrackingForMember(member);
            if (but != null) {
                but.updateUserIdAndEmailIfEmpty(member.getId_(), member.getEmailAddress());
                BalloonCookie cookie = BalloonCookie.of(but.getUuid_());
                response.addCookie(cookie.getHttpCookie());
                return Optional.of(but);
            }
        }

        return Optional.empty();
    }

    public BalloonUserTracking getOrCreateBalloonUserTracking(HttpServletRequest request,
            HttpServletResponse response, String parent, String linkUuid) {

        final Optional<BalloonUserTracking> butOpt = getBalloonUserTracking(request, response);
        if (butOpt.isPresent()) {
            return butOpt.get();
        }

        //make sure we always track the correct user, even when impersonating
        Member member = authenticationService.getRealMemberOrNull();

        String uuid;
        if (member != null) {
            uuid = member.getUuid();
        } else {
            uuid = UUID.randomUUID().toString();
        }
        response.addCookie(BalloonCookie.of(uuid).getHttpCookie());

        final BalloonUserTracking but =
                createBalloonUserTracking(uuid, parent, linkUuid, member, request.getRemoteAddr(),
                        request.getHeader(HttpHeaders.REFERER),
                        request.getHeader(HttpHeaders.USER_AGENT));
        request.setAttribute(NEW_BALLOON_USER_TRACKING_ATTRIBUTE, but);
        return but;
    }

    public void associateBalloonTrackingWithUser(HttpServletRequest request, Member member) {
        Optional<BalloonCookie> balloonCookieOpt = BalloonCookie.from(request.getCookies());
        if (balloonCookieOpt.isPresent()) {
            final BalloonCookie balloonCookie = balloonCookieOpt.get();
            try {
                BalloonUserTracking but =
                        BalloonsClient.getBalloonUserTracking(balloonCookie.getUuid());
                if (but != null) {
                    but.updateUserIdAndEmailIfEmpty(member.getId_(), member.getEmailAddress());
                }
            } catch (BalloonUserTrackingNotFoundException e) {
                _log.error("Invalid UUID: {}", balloonCookie);
            }
        }
    }

    private BalloonUserTracking getBalloonUserTrackingFromCookie(BalloonCookie cookie) {
        return getOptional(() -> BalloonsClient.getBalloonUserTracking(cookie.getUuid()))
                .orElseThrow(() -> ReferenceResolutionException
                        .toObject(BalloonUserTracking.class, cookie.getUuid())
                        .fromObject(BalloonCookie.class, ""));
    }

    private BalloonUserTracking createBalloonUserTracking(String uuid, String parent,
            String linkUuid, Member member, String remoteIp, String referrer, String userAgent) {
        BalloonUserTracking but = new BalloonUserTracking();
        but.setUuid_(uuid);
        but.setIp(remoteIp);
        but.setParent(parent);
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

    private static BalloonUserTracking getBalloonUserTrackingForMember(Member member) {
        try {
            return BalloonsClient.getBalloonUserTracking(member.getUuid());
        } catch (BalloonUserTrackingNotFoundException ignored) {
            List<BalloonUserTracking> buts =
                    BalloonsClient.getBalloonUserTrackingByEmail(member.getEmailAddress());

            return buts.stream().findFirst().orElse(null);
        }
    }

}
