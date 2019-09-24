package org.xcolab.client.contest.util;

import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SchedulePhase extends ContestPhaseWrapper {

    private final List<SchedulePhase> schedulePhases;
    private final int position;

    public SchedulePhase(List<SchedulePhase> container, ContestPhaseWrapper contestPhase, int position) {
        super(contestPhase);
        this.schedulePhases = container;
        this.position = position;
    }

    public static List<SchedulePhase> wrapList(List<ContestPhaseWrapper> phases) {
        final List<ContestPhaseWrapper> sortedPhases = new ArrayList<>(phases);
        sortedPhases.sort(Comparator.comparing(ContestPhaseWrapper::getPhaseStartDate));
        List<SchedulePhase> schedulePhases = new ArrayList<>();
        final List<SchedulePhase> immutablePhases = Collections.unmodifiableList(schedulePhases);
        for (int i = 0; i < phases.size(); ++i) {
            schedulePhases.add(new SchedulePhase(immutablePhases, phases.get(i), i));
        }
        return immutablePhases;
    }

    public int getPosition() {
        return position;
    }

    public List<SchedulePhase> getSchedulePhases() {
        return schedulePhases;
    }
}
