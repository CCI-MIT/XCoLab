package org.xcolab.view.webhooks.sendgrid.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.view.pages.proposals.utils.voting.VoteValidator.ValidationResult;
import org.xcolab.view.webhooks.sendgrid.model.EventType;
import org.xcolab.view.webhooks.sendgrid.model.SendGridEvent;

@Component
public class BounceEventProcessor implements SendGridEventProcessor {

    private static final Logger log = LoggerFactory.getLogger(BounceEventProcessor.class);

    @Override
    public boolean handles(EventType eventType) {
        return eventType == EventType.BOUNCE;
    }

    @Override
    public void process(SendGridEvent event) {
        final String email = event.getEmail();
        try {
            Member member = MembersClient.findMemberByEmailAddress(email);
            if (member.isVerifiedAccount()) {
                // ignore bounces from verified members
                return;
            }

            final boolean isHardBounce = event.getStatus().startsWith("5");
            if (isHardBounce && !member.getIsEmailBounced()) {
                member.setIsEmailBounced(true);
                MembersClient.updateMember(member);
                ProposalMemberRatingClientUtil.invalidateVotesForMember(member.getId(),
                        ValidationResult.INVALID_BOUNCED_EMAIL.name());
                log.debug("Marked {}'s email {} as bounced ({}).", member.getScreenName(), email,
                        event.getReason());
            }
        } catch (MemberNotFoundException e) {
            //we don't care about bounces from non-member emails
            log.debug("Ignoring bounced email {} (no member found).", email);
        }
    }
}
