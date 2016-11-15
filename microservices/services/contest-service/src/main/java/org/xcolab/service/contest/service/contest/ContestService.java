package org.xcolab.service.contest.service.contest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.model.tables.pojos.ContestPhaseType;
import org.xcolab.service.contest.domain.contest.ContestDao;
import org.xcolab.service.contest.domain.contestphase.ContestPhaseDao;
import org.xcolab.service.contest.domain.contestphasetype.ContestPhaseTypeDao;
import org.xcolab.service.contest.domain.ontologyterm.OntologyTermDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.ontology.OntologyService;
import org.xcolab.service.contest.utils.promotion.enums.ContestPhaseTypeValue;
import org.xcolab.service.utils.PaginationHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
public class ContestService {

    private static final Logger _log = LoggerFactory.getLogger(ContestService.class);

    private final ContestDao contestDao;

    private final ContestPhaseDao contestPhaseDao;

    private final ContestPhaseTypeDao contestPhaseTypeDao;

    private final OntologyService ontologyService;
	
	private final OntologyTermDao ontologyTermDao;

    @Autowired
    public ContestService(ContestDao contestDao, ContestPhaseDao contestPhaseDao, ContestPhaseTypeDao contestPhaseTypeDao, OntologyService ontologyService, OntologyTermDao ontologyTermDao) {

        this.contestDao = contestDao;
        this.contestPhaseDao = contestPhaseDao;
        this.contestPhaseTypeDao = contestPhaseTypeDao;
        this.ontologyService = ontologyService;
        this.ontologyTermDao = ontologyTermDao;
    }

    public List<ContestPhase> getAllContestPhases(Long contestId) {
        return this.contestPhaseDao.findByGiven(contestId, null);
    }

    public ContestPhase getActiveOrLastPhase(Long contestId) {
        ContestPhase lastPhase = null;
        for (ContestPhase phase : getAllContestPhases(contestId)) {
            if (lastPhase == null || lastPhase.getPhaseStartDate().before(phase.getPhaseStartDate())) {
                lastPhase = phase;
            }
            if (contestPhaseDao.isPhaseActive(phase)) {
                return phase;
            }
        }
        return lastPhase;
    }

    public List<Contest> findContestsByName(String contestName, List<Long> ontologyTermIds, List<Long> contestTypeIds) {
        List<Long> focusAreaOntologyTermsIds = new ArrayList<>();
        if(ontologyTermIds != null && !ontologyTermIds.isEmpty()) {
            List<Long> allChildTerms = new ArrayList<>();
            for (Long otId : ontologyTermIds) {
                allChildTerms.addAll(ontologyService.getAllOntologyTermDescendantTermsIds(otId));
            }
            focusAreaOntologyTermsIds = ontologyService.getFocusAreasIdForOntologyTermIds(allChildTerms);
        }
        return contestDao.findByGiven(contestName, focusAreaOntologyTermsIds, contestTypeIds);
    }

    public List<ContestPhase> getVisiblePhases(Long contestId) {
        return getAllContestPhases(contestId).stream()
                .filter(contestPhase -> {
                    final Optional<ContestPhaseType> contestPhaseType = contestPhaseTypeDao
                            .get(contestPhase.getContestPhaseType());
                    return contestPhaseType.isPresent() && !contestPhaseType.get().getInvisible();
                })
                .collect(Collectors.toList());
    }

    public List<Contest> getSubContestsByOntologySpaceId(Long contestId, Long ontologySpaceId) {
        try {
            Contest contest = contestDao.get(contestId);
            long focusAreaId = contest.getFocusAreaId();
            long contestTier = contest.getContestTier();
            long lowerContestTier = contestTier - 1;
            if(lowerContestTier < 1) {
                return new ArrayList<>();
            }
            List<Long> focusAreaOntologyTerms = ontologyService.getFocusAreaOntologyTermIdsByFocusAreaAndSpaceId(focusAreaId,ontologySpaceId );
            PaginationHelper ph = new PaginationHelper(0,Integer.MAX_VALUE,null);
            return contestDao.findByGiven(ph, null, null, null, null, contestTier, focusAreaOntologyTerms, null, null, null, null);


        } catch (NotFoundException ignored) {
            return new ArrayList<>();
        }
    }


    public List<Contest> getContestsByOntologyTerm(Long ontologyTerm, Boolean getOnlyActive) {

        if (ontologyTerm == null) {
            PaginationHelper ph = new PaginationHelper(0,Integer.MAX_VALUE,null);
            return contestDao.findByGiven(ph,null,null,getOnlyActive,null,null,null,null,null,null,null);
        }

        List<Long> focusAreaOntologyTermsIds = ontologyService.getFocusAreasIdForOntologyTermIds(
                Arrays.asList(ontologyTerm));
        List<Contest> contests = new ArrayList<>();

        if(!focusAreaOntologyTermsIds.isEmpty()) {
            PaginationHelper ph = new PaginationHelper(0,Integer.MAX_VALUE,null);
            contests = contestDao.findByGiven(ph,null,null,getOnlyActive,null,null,focusAreaOntologyTermsIds,null,null,null,null);
        }

        return contests;
    }


    public List<Contest> getContestsMatchingOntologyTerms(List<Long> ontologyTerms) {

        if (ontologyTerms == null || ontologyTerms.isEmpty()) {
            PaginationHelper ph = new PaginationHelper(0,Integer.MAX_VALUE,null);
            return contestDao.findByGiven(ph,null,null,null,null,null,null,null,null,null,null);
        }

        List<Long> allChildTerms = new ArrayList<>();
        for (Long otId : ontologyTerms) {
            allChildTerms.addAll(ontologyService.getAllOntologyTermDescendantTermsIds(otId));
        }

        List<Long> focusAreaOntologyTermsIds = ontologyService.getFocusAreasIdForOntologyTermIds(allChildTerms);
        PaginationHelper ph = new PaginationHelper(0,Integer.MAX_VALUE,null);
        return contestDao.findByGiven(ph,null,null,null,null,null,focusAreaOntologyTermsIds,null,null,null,null);

    }

    public int getNumberOfContestsByOntologyTerm(Long ontologyTerm) {
        int count = 0;
        if (ontologyTerm != null) {
            List<Long> focusAreaOntologyTermsIds =
                    ontologyService.getFocusAreasIdForOntologyTermIds(
                            Arrays.asList(ontologyTerm));
            for (Long areaId : focusAreaOntologyTermsIds) {
                count += contestDao
                        .countByGiven(null, null, null, null, null, null, areaId, null, null, null);
            }
        } else {
            count += contestDao
                    .countByGiven(null, null, null, null, null, null, null, null, null, null);
        }
        return count;
    }

    private Integer getYearFromDate(Date date) {
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("US/Eastern"));
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public void addContestYearSuffixToContest(Contest contest, boolean checkForCompleted) {
        ContestPhase latestPhase = getActiveOrLastPhase(contest.getContestPK());
        String[] contestNameParts = contest.getContestShortName().split(" ");
        _log.info("addContestYearSuffixToContest: " + contest.getContestPK());
        // Is in completed phase and inactive? - or is flag set to false?
        boolean isCompleted = ((contestNameParts).length>0 &&
                (latestPhase.getContestPhaseType() == ContestPhaseTypeValue.COMPLETED.getTypeId() ||
                        latestPhase.getContestPhaseType() == ContestPhaseTypeValue.WINNERS_AWARDED.getTypeId()));
        if (!checkForCompleted || isCompleted) {
            _log.info("Contest phase type : " + latestPhase.getContestPhaseType());

            String lastNamePart = contestNameParts[contestNameParts.length - 1];
            Integer phaseEndYear = getYearFromDate(latestPhase.getPhaseStartDate());

            String newContestShortName;
            try {
                final int suffixYear = Integer.parseInt(lastNamePart);

                // Same year suffix detected - skip contest
                if (suffixYear == phaseEndYear) {
                    return;
                }

                // Unlikely event that a suffix has been created but the phase end date has changed - adapt to new suffix
                contestNameParts[contestNameParts.length - 1] = phaseEndYear.toString();
                newContestShortName = StringUtils.join(contestNameParts, " ");
            } catch (NumberFormatException e) {
                // No year suffix detected - add new one
                newContestShortName = contest.getContestShortName() + " " + phaseEndYear;
            }
            contest.setContestShortName(newContestShortName);
            contestDao.update(contest);
        }
    }

}
