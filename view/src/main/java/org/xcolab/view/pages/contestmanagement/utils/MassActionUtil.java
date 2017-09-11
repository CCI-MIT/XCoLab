package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;

import java.util.List;
import java.util.stream.Collectors;

public final class MassActionUtil {

    private MassActionUtil() { }

    public static List<Contest> getContests(List<Long> contestIds) {
        return contestIds
                .stream()
                .map(contestId -> ContestClientUtil.getContest(contestId))
                .collect(Collectors.toList());
    }
}
