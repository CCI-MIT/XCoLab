package org.xcolab.view.pages.loginregister;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.client.sharedcolab.pojo.Member;

public final class SharedColabUtil {

    private SharedColabUtil() {
    }

    public static void checkTriggerForAutoUserCreationInContest(Long contestId, Long memberId) {
        try {
            Contest contest = ContestClientUtil.getContest(contestId);
            if (contest!=null) {
                if (contest.getIsSharedContest()) {
                    registerMemberInSharedColab(memberId);
                }
            }
        } catch(ContestNotFoundException ignored) {
        }
    }

    public static void registerMemberInSharedColab(Long memberId) {
        try {
            org.xcolab.client.members.pojo.Member memberInCurrentColab = MembersClient.getMember(memberId);

            Member member = new Member();
            member.setId_(memberInCurrentColab.getId_());
            member.setScreenName(memberInCurrentColab.getScreenName());
            member.setEmailAddress(memberInCurrentColab.getEmailAddress());
            member.setFirstName(memberInCurrentColab.getFirstName());
            member.setHashedPassword(memberInCurrentColab.getHashedPassword());
            member.setLastName(memberInCurrentColab.getLastName());
            member.setOpenId(memberInCurrentColab.getOpenId());
            member.setFacebookId(memberInCurrentColab.getFacebookId());
            member.setShortBio(memberInCurrentColab.getShortBio());
            member.setCountry(memberInCurrentColab.getCountry());
            SharedColabClient.registerInPartnerColab(member);
        } catch (MemberNotFoundException e) {
            e.printStackTrace();
        }
    }
}
