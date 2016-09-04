package org.xcolab.liferay;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;

public class SharedColabUtil {
    public static void checkTriggerForAutoUserCreationInContest(Long contestId, Long userId){
        try {
            org.xcolab.client.contest.pojo.Contest contestMicro = ContestClient.getContest(contestId);
            if(contestMicro!=null) {
                if (contestMicro.getIsSharedContest()) {
                    LoginRegisterUtil.registerMemberInSharedColab(userId);
                }
            }
        }catch(ContestNotFoundException ignored){
        }
    }
}
