package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestSchedule;
import com.ext.portlet.service.*;
import com.liferay.counter.service.CounterLocalServiceUtil;
import org.xcolab.portlets.contestmanagement.beans.ContestPhaseBean;
import org.xcolab.portlets.contestmanagement.entities.StartDateEndDate;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2/20/2015.
 */
public class ContestScheduleWrapper {


    public static void insertSeedDataToContestScheduleTableIfNotAvailable() throws Exception{
        if(!isContestSchedulesAvailable()){
            insertSeedDataToContestScheduleTable("Tier 1 (Basic)", createSeedDataForBasicLevelSchedule());
            insertSeedDataToContestScheduleTable("Tier 2 (Regional)", createSeedDataForRegionalLevelSchedule());
        }
    }

    private static boolean isContestSchedulesAvailable() throws Exception{
        return ContestScheduleLocalServiceUtil.getContestSchedulesCount() > 0;
    }

    private static void insertSeedDataToContestScheduleTable(String scheduleName, List<ContestPhaseBean> contestPhaseBeanList) throws Exception{

        ContestSchedule contestSchedule = ContestScheduleLocalServiceUtil.
                createContestSchedule(CounterLocalServiceUtil.increment(ContestSchedule.class.getName()));
        contestSchedule.setName(scheduleName);
        contestSchedule.persist();
        ContestScheduleLocalServiceUtil.updateContestSchedule(contestSchedule);

        for(ContestPhaseBean contestPhaseBean : contestPhaseBeanList){
            ContestPhase contestPhase = ContestPhaseLocalServiceUtil.
                    createContestPhase(CounterLocalServiceUtil.increment(ContestPhase.class.getName()));

            contestPhase.setContestPK(0L);
            contestPhase.setContestPhaseType(contestPhaseBean.getContestPhaseType());
            contestPhase.setPhaseStartDate(contestPhaseBean.getPhaseStartDate());
            contestPhase.setPhaseEndDate(contestPhaseBean.getPhaseEndDate());
            contestPhase.setContestPhaseAutopromote(contestPhaseBean.getContestPhaseAutopromote());
            contestPhase.setFellowScreeningActive(contestPhaseBean.getFellowScreeningActive());
            contestPhase.persist();
            ContestPhaseLocalServiceUtil.updateContestPhase(contestPhase);
        }

    }

    public static void createContestPhaseAccordingToContestSchedule(Contest contest) throws Exception{
        removeExistingContestPhases(contest);
        List<ContestPhase> contestSchedulePhases = ContestPhaseLocalServiceUtil.getPhasesForContestSchedule(contest.getContestScheduleId(), 0L);
        for(ContestPhase contestPhase : contestSchedulePhases){
            contestPhase.setContestPK(contest.getContestPK());
            ContestPhaseLocalServiceUtil.addContestPhase(contestPhase);
        }
    }

    private static void removeExistingContestPhases(Contest contest) throws Exception{
        List<ContestPhase> contestPhases = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());
        for(ContestPhase contestPhase : contestPhases){
            ContestPhaseLocalServiceUtil.deleteContestPhase(contestPhase);
        }
    }

    private static List<ContestPhaseBean> createSeedDataForBasicLevelSchedule(){
        List<StartDateEndDate> phaseStartEndDatesBasicLevelSchedule  = new ArrayList<>();
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date(2015, 3, 6), new Date(2015, 5, 16)));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date(2015, 5, 18), new Date(2015, 5, 29)));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date(2015, 6, 1), new Date(2015, 6, 13)));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date(2015, 6, 15), new Date(2015, 6, 26)));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date(2015, 7, 1), new Date(2015, 7, 31)));
        return createPhasesFromStartEndDates(phaseStartEndDatesBasicLevelSchedule);
    }

    private static List<ContestPhaseBean> createSeedDataForRegionalLevelSchedule(){
        List<StartDateEndDate> phaseStartEndDatesBasicLevelSchedule  = new ArrayList<>();
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date(2015, 3, 6), new Date(2015, 6, 6)));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date(2015, 6, 8), new Date(2015, 7, 2)));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date(2015, 7, 3), new Date(2015, 7, 15)));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date(2015, 7, 16), new Date(2015, 8, 6)));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date(2015, 8, 7), new Date(2015, 9, 1)));
        return createPhasesFromStartEndDates(phaseStartEndDatesBasicLevelSchedule);
    }

    private static List<ContestPhaseBean> createPhasesFromStartEndDates(List<StartDateEndDate> startDateEndDates){
        List<ContestPhaseBean> contestPhaseBeans = new ArrayList<>();
        contestPhaseBeans.add(new ContestPhaseBean(1L, startDateEndDates.get(0).getStartDate(), startDateEndDates.get(0).getEndDate(), "PROMOTE", false));
        contestPhaseBeans.add(new ContestPhaseBean(16L, startDateEndDates.get(1).getStartDate(), startDateEndDates.get(0).getEndDate(), "PROMOTE_DONE", true));
        contestPhaseBeans.add(new ContestPhaseBean(18L, startDateEndDates.get(2).getStartDate(), startDateEndDates.get(0).getEndDate(), "PROMOTE", false));
        contestPhaseBeans.add(new ContestPhaseBean(19L, startDateEndDates.get(3).getStartDate(), startDateEndDates.get(0).getEndDate(), "PROMOTE_JUDGED", true));
        contestPhaseBeans.add(new ContestPhaseBean(20L, startDateEndDates.get(4).getStartDate(), startDateEndDates.get(0).getEndDate(), "", false));
        contestPhaseBeans.add(new ContestPhaseBean(14L, startDateEndDates.get(4).getEndDate()));
        return  contestPhaseBeans;
    }

}
