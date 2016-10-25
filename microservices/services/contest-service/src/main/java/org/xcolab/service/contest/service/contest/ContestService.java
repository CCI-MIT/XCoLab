package org.xcolab.service.contest.service.contest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.model.tables.pojos.ContestPhaseType;
import org.xcolab.model.tables.pojos.OntologyTerm;
import org.xcolab.service.contest.domain.contest.ContestDao;
import org.xcolab.service.contest.domain.contestphase.ContestPhaseDao;
import org.xcolab.service.contest.domain.contestphasetype.ContestPhaseTypeDao;
import org.xcolab.service.contest.domain.ontologyterm.OntologyTermDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.ontology.OntologyService;
import org.xcolab.service.utils.PaginationHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContestService {

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

    public List<Contest> getContestsMatchingOntologyTerms(List<Long> ontologyTerms) {

        if (ontologyTerms.isEmpty()) {
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

}
