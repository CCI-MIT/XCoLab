package org.xcolab.view.pages.redballoon.utils;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.email.IEmailClient;
import org.xcolab.client.tracking.IBalloonClient;
import org.xcolab.client.tracking.ITrackingClient;
import org.xcolab.client.tracking.exceptions.BalloonTextNotFoundException;
import org.xcolab.client.tracking.exceptions.BalloonUserTrackingNotFoundException;
import org.xcolab.client.tracking.pojo.IBalloonLink;
import org.xcolab.client.tracking.pojo.IBalloonText;
import org.xcolab.client.tracking.pojo.IBalloonUserTracking;
import org.xcolab.client.tracking.pojo.ILocation;
import org.xcolab.client.tracking.pojo.tables.pojos.BalloonLink;
import org.xcolab.client.tracking.pojo.tables.pojos.BalloonUserTracking;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.exceptions.ReferenceResolutionException;
import org.xcolab.entity.utils.LinkUtils;
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
    private final ITrackingClient trackingClient;
    private final IBalloonClient balloonClient;
    private final IEmailClient emailClient;

    @Autowired
    public BalloonService(AuthenticationService authenticationService,
            ITrackingClient trackingClient, IBalloonClient balloonClient, IEmailClient emailClient) {
        this.authenticationService = authenticationService;
        this.trackingClient = trackingClient;
        this.balloonClient = balloonClient;
        this.emailClient = emailClient;
    }

    public IBalloonLink createBalloonLink(String email, IBalloonUserTracking but)
            throws BalloonTextNotFoundException {
        IBalloonLink link = new BalloonLink();
        link.setUuid(UUID.randomUUID().toString());
        link.setBalloonUserUuid(but.getUuid());
        link.setCreatedAt(new Timestamp(new Date().getTime()));
        link.setTargetUrl(getSnpLinkUrl(link.getUuid()));
        link = balloonClient.createBalloonLink(link);

        IBalloonText text = balloonClient.getBalloonText(but.getBalloonTextId());
        String messageSubject = text.getEmailSubjectTemplate();
        String messageBody = text.getEmailTemplate()
                .replaceAll(URL_PLACEHOLDER, LinkUtils.getAbsoluteUrl(link.getTargetUrl()));
        final String fromEmail = ConfigurationAttributeKey.ADMIN_FROM_EMAIL.get();
        final String fromName = ConfigurationAttributeKey.COLAB_NAME.get();
        emailClient.sendEmail(fromEmail, fromName, email, messageSubject, messageBody, true,
                fromEmail, fromName, but.getBalloonTextId());

        return link;
    }

    private String getSnpLinkUrl(String linkUuid) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("linkUuid", linkUuid);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(SNP_LINK_URL);
        return uriBuilder.buildAndExpand(uriVariables).toUriString();
    }

    public Optional<IBalloonUserTracking> getBalloonUserTracking(HttpServletRequest request,
            HttpServletResponse response) {

        // a new tracking created earlier in this request won't show up in the cookies
        // it's saved in an attribute so we can use that instead
        final IBalloonUserTracking createdBut =
                (IBalloonUserTracking) request.getAttribute(NEW_BALLOON_USER_TRACKING_ATTRIBUTE);
        if (createdBut != null) {
            return Optional.of(createdBut);
        }

        //make sure we always track the correct user, even when impersonating
        UserWrapper member = authenticationService.getRealMemberOrNull();

        Optional<BalloonCookie> cookieOpt = BalloonCookie.from(request.getCookies());
        if (cookieOpt.isPresent()) {
            try {
                IBalloonUserTracking but = getBalloonUserTrackingFromCookie(cookieOpt.get());

                if (member == null) {
                    return Optional.of(but);
                }

                final boolean butLinkedToOtherMember =
                        but.getUserId() != null && but.getUserId() != member.getId();
                if (!butLinkedToOtherMember) {
                    balloonClient.updateUserIdAndEmailIfEmpty(but, member.getId(),
                            member.getEmailAddress());
                    return Optional.of(but);
                }
            } catch (ReferenceResolutionException rre) {
                return Optional.empty();
            }
        }

        if (member != null) {
            IBalloonUserTracking but = getBalloonUserTrackingForMember(member);
            if (but != null) {
                balloonClient.updateUserIdAndEmailIfEmpty(but, member.getId(),
                        member.getEmailAddress());
                BalloonCookie cookie = BalloonCookie.of(but.getUuid());
                response.addCookie(cookie.getHttpCookie());
                return Optional.of(but);
            }
        }

        return Optional.empty();
    }

    public IBalloonUserTracking getOrCreateBalloonUserTracking(HttpServletRequest request,
            HttpServletResponse response, String parent, String linkUuid) {

        final Optional<IBalloonUserTracking> butOpt = getBalloonUserTracking(request, response);
        if (butOpt.isPresent()) {
            return butOpt.get();
        }

        //make sure we always track the correct user, even when impersonating
        UserWrapper member = authenticationService.getRealMemberOrNull();

        String uuid;
        if (member != null) {
            uuid = member.getOrGenerateUuid();
        } else {
            uuid = UUID.randomUUID().toString();
        }
        response.addCookie(BalloonCookie.of(uuid).getHttpCookie());

        final IBalloonUserTracking but =
                createBalloonUserTracking(uuid, parent, linkUuid, member, request.getRemoteAddr(),
                        request.getHeader(HttpHeaders.REFERER),
                        request.getHeader(HttpHeaders.USER_AGENT));
        request.setAttribute(NEW_BALLOON_USER_TRACKING_ATTRIBUTE, but);
        return but;
    }

    public void associateBalloonTrackingWithUser(HttpServletRequest request, UserWrapper member) {
        Optional<BalloonCookie> balloonCookieOpt = BalloonCookie.from(request.getCookies());
        if (balloonCookieOpt.isPresent()) {
            final BalloonCookie balloonCookie = balloonCookieOpt.get();
            try {
                IBalloonUserTracking but =
                        balloonClient.getBalloonUserTracking(balloonCookie.getUuid());
                if (but != null) {
                    balloonClient.updateUserIdAndEmailIfEmpty(but, member.getId(),
                            member.getEmailAddress());
                }
            } catch (BalloonUserTrackingNotFoundException e) {
                _log.error("Invalid UUID: {}", balloonCookie);
            }
        }
    }

    private IBalloonUserTracking getBalloonUserTrackingFromCookie(BalloonCookie cookie) {
        return getOptional(() -> balloonClient.getBalloonUserTracking(cookie.getUuid()))
                .orElseThrow(() -> ReferenceResolutionException
                        .toObject(IBalloonUserTracking.class, cookie.getUuid())
                        .fromObject(BalloonCookie.class, ""));
    }

    private IBalloonUserTracking createBalloonUserTracking(String uuid, String parent,
            String linkUuid, UserWrapper member, String remoteIp, String referrer, String userAgent) {
        IBalloonUserTracking but = new BalloonUserTracking();
        but.setUuid(uuid);
        but.setIp(remoteIp);
        but.setParent(parent);
        but.setBalloonLinkUuid(linkUuid);
        but.setReferrer(referrer);
        but.setUserAgent(userAgent);

        // populate GeoLocation data
        ILocation location = trackingClient.getLocationForIp(remoteIp);
        if (location != null) {
            but.setCity(location.getCity());
            but.setCountry(location.getCountry());
            but.setLatitude(location.getLatitude());
            but.setLongitude(location.getLongitude());
        }

        if (member != null && member.getId() > 0) {
            but.setUserId(member.getId());
        }

        // pick random balloon text to be displayed
        List<IBalloonText> texts = balloonClient.getAllEnabledBalloonLinks();
        if (!texts.isEmpty()) {
            but.setBalloonTextId(texts.get(RandomUtils.nextInt(0, texts.size())).getId());
        } else {
            but.setBalloonTextId(0L);
        }

        balloonClient.createBalloonUserTracking(but);
        return but;
    }

    private IBalloonUserTracking getBalloonUserTrackingForMember(UserWrapper member) {
        try {
            return balloonClient.getBalloonUserTracking(member.getOrGenerateUuid());
        } catch (BalloonUserTrackingNotFoundException ignored) {
            List<IBalloonUserTracking> buts =
                    balloonClient.listBalloonUserTrackings(member.getEmailAddress(), null);
            return buts.stream().findFirst().orElse(null);
        }
    }
}
