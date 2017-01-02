package org.xcolab.view.pages.loginregister;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;

public final class SharedColabUtil {

    private SharedColabUtil() {
    }

    public static void checkTriggerForAutoUserCreationInContest(Long contestId, Long userId) {
        try {
            Contest contestMicro = ContestClientUtil.getContest(contestId);
            if (contestMicro!=null) {
                if (contestMicro.getIsSharedContest()) {
                    LoginRegisterUtil.registerMemberInSharedColab(userId);
                }
            }
        } catch(ContestNotFoundException ignored) {
        }
    }
}
