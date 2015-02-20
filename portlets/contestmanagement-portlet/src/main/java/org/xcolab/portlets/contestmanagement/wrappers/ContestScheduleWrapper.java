package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseWrapper;
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
            insertSeedDataToContestScheduleTable("2015 Schedule: sector", createSeedDataForBasicLevelSchedule());
            insertSeedDataToContestScheduleTable("2015 Schedule: regional & global", createSeedDataForRegionalLevelSchedule());
        }
    }

    private static boolean isContestSchedulesAvailable() throws Exception{
        return ContestScheduleLocalServiceUtil.getContestSchedulesCount() > 1;
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
            contestPhase.setContestScheduleId(contestSchedule.getId());
            contestPhase.setContestPhaseType(contestPhaseBean.getContestPhaseType());
            contestPhase.setPhaseStartDate(contestPhaseBean.getPhaseStartDate());

            if(contestPhaseBean.getPhaseEndDate() != null) {
                contestPhase.setPhaseEndDate(contestPhaseBean.getPhaseEndDate());
            }

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

            ContestPhase contestPhaseNew = ContestPhaseLocalServiceUtil.
                    createContestPhase(CounterLocalServiceUtil.increment(ContestPhase.class.getName()));
            contestPhase.setContestPK(contest.getContestPK());
            contestPhase.setPrimaryKey(contestPhaseNew.getPrimaryKey());
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
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("3/6/15 8:00:00 PM"), new Date("5/16/15 7:59:59 PM")));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("5/18/15 8:00:00 PM"), new Date("5/29/15 7:59:59 PM")));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("6/1/15 8:00:00 PM"), new Date("6/13/15 7:59:59 PM")));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("6/15/15 8:00:00 PM"), new Date("6/26/15 7:59:59 PM")));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("7/1/15 8:00:00 PM"), new Date("7/31/15 7:59:59 PM")));
        return createPhasesFromStartEndDates(phaseStartEndDatesBasicLevelSchedule);
    }

    private static List<ContestPhaseBean> createSeedDataForRegionalLevelSchedule(){
        List<StartDateEndDate> phaseStartEndDatesBasicLevelSchedule  = new ArrayList<>();
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("3/6/15 8:00:00 PM"), new Date("6/6/15 7:59:59 PM")));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("6/6/15 8:00:00 PM"), new Date("2/7/15 7:59:59 PM")));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("7/3/15 8:00:00 PM"), new Date("7/15/15 7:59:59 PM")));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("7/16/15 8:00:00 PM"), new Date("8/6/15 7:59:59 PM")));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("8/3/15 8:00:00 PM"), new Date("9/1/15 7:59:59 PM")));
        return createPhasesFromStartEndDates(phaseStartEndDatesBasicLevelSchedule);
    }

    private static List<ContestPhaseBean> createPhasesFromStartEndDates(List<StartDateEndDate> startDateEndDates){
        List<ContestPhaseBean> contestPhaseBeans = new ArrayList<>();
        contestPhaseBeans.add(new ContestPhaseBean(1L, startDateEndDates.get(0).getStartDate(), startDateEndDates.get(0).getEndDate(), "PROMOTE", false));
        contestPhaseBeans.add(new ContestPhaseBean(16L, startDateEndDates.get(1).getStartDate(), startDateEndDates.get(1).getEndDate(), "PROMOTE_DONE", true));
        contestPhaseBeans.add(new ContestPhaseBean(18L, startDateEndDates.get(2).getStartDate(), startDateEndDates.get(2).getEndDate(), "PROMOTE", false));
        contestPhaseBeans.add(new ContestPhaseBean(19L, startDateEndDates.get(3).getStartDate(), startDateEndDates.get(3).getEndDate(), "PROMOTE_JUDGED", true));
        contestPhaseBeans.add(new ContestPhaseBean(20L, startDateEndDates.get(4).getStartDate(), startDateEndDates.get(4).getEndDate(), "", false));
        contestPhaseBeans.add(new ContestPhaseBean(14L, startDateEndDates.get(4).getEndDate()));
        return  contestPhaseBeans;
    }

}
