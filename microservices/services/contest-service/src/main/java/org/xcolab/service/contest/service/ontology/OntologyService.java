package org.xcolab.service.contest.service.ontology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.FocusAreaOntologyTerm;
import org.xcolab.model.tables.pojos.OntologyTerm;
import org.xcolab.service.contest.domain.focusareaontologyterm.FocusAreaOntologyTermDao;
import org.xcolab.service.contest.domain.ontologyterm.OntologyTermDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.commons.exceptions.InternalException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OntologyService {

    private final OntologyTermDao ontologyTermDao;

    private final FocusAreaOntologyTermDao focusAreaOntologyTermDao;

    @Autowired
    public OntologyService(OntologyTermDao ontologyTermDao,
            FocusAreaOntologyTermDao focusAreaOntologyTermDao) {
        this.ontologyTermDao = ontologyTermDao;
        this.focusAreaOntologyTermDao = focusAreaOntologyTermDao;
    }


    public List<Long> getFocusAreaOntologyTermIdsByFocusAreaAndSpaceId(Long focusAreaId, Long ontologySpaceId) {
        long ontologyTermId = getOntologyTermIdByFocusAreaAndSpaceId(focusAreaId, ontologySpaceId);
        List<Long> focusAreasIdsByOntologyTermId = new ArrayList<>();
        for (FocusAreaOntologyTerm faot : focusAreaOntologyTermDao.findByGiven(null, ontologyTermId)) {
            focusAreasIdsByOntologyTermId.add(faot.getFocusAreaId());
        }

        return focusAreasIdsByOntologyTermId;
    }

    private long getOntologyTermIdByFocusAreaAndSpaceId(Long focusAreaId, long ontologySpaceId) {
        List<FocusAreaOntologyTerm> ontologyTermsForFocusArea =
            focusAreaOntologyTermDao.findByGiven(focusAreaId, null);
        for (FocusAreaOntologyTerm focusAreaOntologyTerm : ontologyTermsForFocusArea) {
            long focusAreaOntologyTermOntologyTermId = focusAreaOntologyTerm.getOntologyTermId();
            try {
                OntologyTerm ontologyTerm = ontologyTermDao.get(focusAreaOntologyTermOntologyTermId);
                if (ontologyTerm.getOntologySpaceId() == ontologySpaceId) {
                    return ontologyTerm.getId();
                }
            } catch (NotFoundException ignored) {

            }
        }
        throw new InternalException("Could not find ontology terms for for focusAreaId "
            + focusAreaId + " and spaceId " + ontologySpaceId);
    }

    public List<Long> getFocusAreaIdsForDescendantTerms(List<Long> ontologyTermIds) {
        if (ontologyTermIds != null && !ontologyTermIds.isEmpty()) {
            final List<Long> allChildTerms = ontologyTermIds.stream()
                .flatMap(otId -> getAllOntologyTermDescendantTermsIds(otId).stream())
                .collect(Collectors.toList());
            return getFocusAreasIdForOntologyTermIds(allChildTerms);
        }
        return Collections.emptyList();
    }

    public List<Long> getAllOntologyTermDescendantTermsIds(Long ontologyTermId) {

        List<Long> terms = new ArrayList<>();
        try {
            OntologyTerm ontologyTerm = ontologyTermDao.get(ontologyTermId);

            for (OntologyTerm ot : getAllOntologyTermDescendantTerms(ontologyTerm)) {
                terms.add(ot.getId());
            }
        } catch (NotFoundException ignored) {
        }
        return terms;
    }

    public List<OntologyTerm> getAllOntologyTermDescendantTerms(OntologyTerm ontologyTerm) {
        List<OntologyTerm> terms = new ArrayList<>();
        if (ontologyTerm.getId() != 0) {
            for (OntologyTerm ot : ontologyTermDao.findByGiven(null, ontologyTerm.getId(),null)) {
                terms.add(ot);
                terms.addAll(getAllOntologyTermDescendantTerms(ot));
            }
            return terms;

        } else {
            return terms;
        }
    }


    public List<Long> getFocusAreasIdForOntologyTermIds(List<Long> ontTermId) {

        List<FocusAreaOntologyTerm> list = focusAreaOntologyTermDao.findByOntologyTermIds(ontTermId);
        List<Long> focusAreaIds = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (FocusAreaOntologyTerm faot : list) {
                focusAreaIds.add(faot.getFocusAreaId());
            }
        }
        return focusAreaIds;
    }


}
