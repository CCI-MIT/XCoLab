package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.entities.ContestMassAction;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractContestMassAction implements ContestMassAction {

    private final String displayName;

    public AbstractContestMassAction(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    protected List<Contest> getLocalContests(List<Contest> contests) {
        return contests
                .stream()
                .filter(contest -> !contest.getIsSharedContestInForeignColab())
                .collect(Collectors.toList());
    }
}
