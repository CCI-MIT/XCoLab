package org.xcolab.service.contest.service.ontology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.contest.pojo.IFocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.service.contest.domain.focusareaontologyterm.FocusAreaOntologyTermDao;
import org.xcolab.service.contest.domain.ontologyterm.OntologyTermDao;
import org.xcolab.service.contest.exceptions.NotFoundException;

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
        for (IFocusAreaOntologyTerm faot : focusAreaOntologyTermDao.findByGiven(null, ontologyTermId)) {
            focusAreasIdsByOntologyTermId.add(faot.getFocusAreaId());
        }

        return focusAreasIdsByOntologyTermId;
    }

    private long getOntologyTermIdByFocusAreaAndSpaceId(Long focusAreaId, long ontologySpaceId) {
        List<IFocusAreaOntologyTerm> ontologyTermsForFocusArea =
            focusAreaOntologyTermDao.findByGiven(focusAreaId, null);
        for (IFocusAreaOntologyTerm focusAreaOntologyTerm : ontologyTermsForFocusArea) {
            long focusAreaOntologyTermOntologyTermId = focusAreaOntologyTerm.getOntologyTermId();
            try {
                OntologyTermWrapper ontologyTerm = ontologyTermDao.get(focusAreaOntologyTermOntologyTermId);
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
            OntologyTermWrapper ontologyTerm = ontologyTermDao.get(ontologyTermId);

            for (OntologyTermWrapper ot : getAllOntologyTermDescendantTerms(ontologyTerm)) {
                terms.add(ot.getId());
            }
        } catch (NotFoundException ignored) {
        }
        return terms;
    }

    public List<OntologyTermWrapper> getAllOntologyTermDescendantTerms(OntologyTermWrapper ontologyTerm) {
        List<OntologyTermWrapper> terms = new ArrayList<>();
        if (ontologyTerm.getId() != 0) {
            for (OntologyTermWrapper ot : ontologyTermDao.findByGiven(null, ontologyTerm.getId(),null)) {
                terms.add(ot);
                terms.addAll(getAllOntologyTermDescendantTerms(ot));
            }
            return terms;

        } else {
            return terms;
        }
    }

    public List<Long> getFocusAreasIdForOntologyTermIds(List<Long> ontTermId) {
        List<IFocusAreaOntologyTerm> list = focusAreaOntologyTermDao.findByOntologyTermIds(ontTermId);
        List<Long> focusAreaIds = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (IFocusAreaOntologyTerm faot : list) {
                focusAreaIds.add(faot.getFocusAreaId());
            }
        }
        return focusAreaIds;
    }
}
