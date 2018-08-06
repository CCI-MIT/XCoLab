package org.xcolab.service.contest.service.contest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.model.tables.pojos.ContestPhaseType;
import org.xcolab.model.tables.pojos.ContestTranslation;
import org.xcolab.service.contest.domain.contest.ContestDao;
import org.xcolab.service.contest.domain.contest.ContestDaoQuery;
import org.xcolab.service.contest.domain.contestphase.ContestPhaseDao;
import org.xcolab.service.contest.domain.contestphasetype.ContestPhaseTypeDao;
import org.xcolab.service.contest.domain.contesttranslation.ContestTranslationDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.ontology.OntologyService;
import org.xcolab.service.utils.PaginationHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
    private final ContestTranslationDao contestTranslationDao;
    private final ContestPhaseDao contestPhaseDao;
    private final ContestPhaseTypeDao contestPhaseTypeDao;
    private final OntologyService ontologyService;

    @Autowired
    public ContestService(ContestDao contestDao, ContestTranslationDao contestTranslationDao,
            ContestPhaseDao contestPhaseDao, ContestPhaseTypeDao contestPhaseTypeDao,
            OntologyService ontologyService) {

        this.contestDao = contestDao;
        this.contestTranslationDao = contestTranslationDao;
        this.contestPhaseDao = contestPhaseDao;
        this.contestPhaseTypeDao = contestPhaseTypeDao;
        this.ontologyService = ontologyService;
    }

    public List<ContestPhase> getAllContestPhases(Long contestId) {
        return this.contestPhaseDao.findByGiven(contestId, null, null);
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

    public List<ContestPhase> getVisiblePhases(Long contestId) {
        return getAllContestPhases(contestId).stream()
                .filter(contestPhase -> {
                    final Optional<ContestPhaseType> contestPhaseType = contestPhaseTypeDao
                            .get(contestPhase.getContestPhaseTypeId());
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
            if (lowerContestTier < 1) {
                return new ArrayList<>();
            }
            List<Long> focusAreaOntologyTerms = ontologyService
                .getFocusAreaOntologyTermIdsByFocusAreaAndSpaceId(focusAreaId, ontologySpaceId);
            return ContestDaoQuery.find(contestDao)
                .withPaginationHelper(PaginationHelper.EVERYTHING)
                .withContestTier(contestTier)
                .withFocusAreaIds(focusAreaOntologyTerms)
                .execute();

        } catch (NotFoundException ignored) {
            return new ArrayList<>();
        }
    }

    public boolean isContestTitleYearUnique(String contestShortName, Long year, Long currentContestId){
        return contestDao.isContestTitleYearUnique(contestShortName, year,currentContestId);
    }

    public List<Contest> getContestsByOntologyTerm(Long ontologyTerm, Boolean active, Boolean onlyPrivate) {

        if (ontologyTerm == null) {
            return ContestDaoQuery.find(contestDao)
                .withPaginationHelper(PaginationHelper.EVERYTHING)
                .withContestPrivate(onlyPrivate)
                .execute();
        }

        List<Long> focusAreaOntologyTermsIds = ontologyService.getFocusAreasIdForOntologyTermIds(
            Collections.singletonList(ontologyTerm));

        if (!focusAreaOntologyTermsIds.isEmpty()) {
            return ContestDaoQuery.find(contestDao)
                .withPaginationHelper(PaginationHelper.EVERYTHING)
                .withActive(active)
                .withContestPrivate(onlyPrivate)
                .withFocusAreaIds(focusAreaOntologyTermsIds)
                .execute();
        }
        return Collections.emptyList();
    }


    public List<Contest> getContestsMatchingOntologyTerms(List<Long> ontologyTerms) {

        if (ontologyTerms == null || ontologyTerms.isEmpty()) {
            PaginationHelper ph = new PaginationHelper(0,Integer.MAX_VALUE,null);
            return ContestDaoQuery.find(contestDao)
                .withPaginationHelper(ph)
                .execute();
        }

        List<Long> allChildTerms = new ArrayList<>();
        for (Long otId : ontologyTerms) {
            allChildTerms.addAll(ontologyService.getAllOntologyTermDescendantTermsIds(otId));
        }

        List<Long> focusAreaOntologyTermsIds = ontologyService.getFocusAreasIdForOntologyTermIds(allChildTerms);
        PaginationHelper ph = new PaginationHelper(0,Integer.MAX_VALUE,null);
        return ContestDaoQuery.find(contestDao)
            .withPaginationHelper(ph)
            .withFocusAreaIds(focusAreaOntologyTermsIds)
            .execute();

    }

    public int getNumberOfContestsByOntologyTerm(Long ontologyTerm) {
        int count = 0;
        if (ontologyTerm != null) {
            List<Long> focusAreaOntologyTermsIds = ontologyService
                .getFocusAreasIdForOntologyTermIds(Collections.singletonList(ontologyTerm));
            count += contestDao.countByGiven(null, null, null, null, null, focusAreaOntologyTermsIds, null, null, null, false, null);
        } else {
            count += contestDao.countByGiven(null, null, null, null, null, null, null, null, null, false, null);
        }
        return count;
    }

    private Integer getYearFromDate(Date date) {
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("US/Eastern"));
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public List<Contest> resolveTranslations(List<Contest> contests, String lang) {
        return contests.stream()
                .map(contest -> resolveTranslation(contest, lang))
                .collect(Collectors.toList());
    }

    public Contest resolveTranslation(Contest contest, String lang) {
        final Optional<ContestTranslation> contestTranslation =
                contestTranslationDao.get(contest.getId(), lang);
        return contestTranslation
                .map(translation -> {
                    Contest ret = new Contest(contest);
                    if (StringUtils.isNotBlank(translation.getTitle())) {
                        ret.setTitle(translation.getTitle());
                    }
                    if (StringUtils.isNotBlank(translation.getQuestion())) {
                        ret.setQuestion(translation.getQuestion());
                    }
                    if (StringUtils.isNotBlank(translation.getDescription())) {
                        ret.setDescription(translation.getDescription());
                    }
                    return ret;
                })
                .orElse(contest);
    }



}
