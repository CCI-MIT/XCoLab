package org.xcolab.client.contest;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.contest.pojo.IFocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.IImpactDefaultSeries;
import org.xcolab.client.contest.pojo.IImpactDefaultSeriesData;
import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologySpaceWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class OntologyClientMockImpl implements IOntologyClient {

    @Override
    public List<OntologySpaceWrapper> getAllOntologySpaces() {
        return Collections.emptyList();
    }

    @Override
    public List<OntologyTermWrapper> getOntologyTerms(String name, Long parentId,
            Long ontologySpaceId) {
        return Collections.emptyList();
    }

    @Override
    public List<FocusAreaWrapper> getAllFocusAreas() {
        return Collections.emptyList();
    }

    @Override
    public FocusAreaWrapper createFocusArea(FocusAreaWrapper focusArea) {
        return null;
    }

    @Override
    public IFocusAreaOntologyTerm createFocusAreaOntologyTerm(
            IFocusAreaOntologyTerm focusAreaOntologyTerm) {
        return null;
    }

    @Override
    public List<IFocusAreaOntologyTerm> getFocusAreaOntologyTerms(Long focusAreaId,
            Long ontologyTermId) {
        return Collections.emptyList();
    }

    @Override
    public OntologyTermWrapper getOntologyTerm(Long ontologyTermId) {
        return null;
    }

    @Override
    public OntologyTermWrapper createOntologyTerm(OntologyTermWrapper ontologyTerm) {
        return null;
    }

    @Override
    public boolean updateOntologyTerm(OntologyTermWrapper ontologyTerm) {
        return false;
    }

    @Override
    public boolean deleteOntologyTerm(Long ontologyTermId) {
        return false;
    }

    @Override
    public List<OntologyTermWrapper> getAllOntologyTermDescendant(Long ontologyTermId) {
        return Collections.emptyList();
    }

    @Override
    public List<OntologyTermWrapper> getOntologyTermsByFocusAreaOntologySpaceName(Long focusAreaId,
            String ontologySpaceName) {
        return Collections.emptyList();
    }

    @Override
    public boolean updateFocusArea(FocusAreaWrapper focusArea) {
        return false;
    }

    @Override
    public boolean deleteFocusArea(Long focusAreaId) {
        return false;
    }

    @Override
    public boolean deleteFocusAreaOntologyTerm(Long focusAreaId, Long ontologyTermId) {
        return false;
    }

    @Override
    public FocusAreaWrapper getFocusArea(Long focusAreaId) {
        return null;
    }

    @Override
    public OntologySpaceWrapper getOntologySpace(Long ontologySpaceId) {
        return null;
    }

    @Override
    public List<IImpactDefaultSeries> getImpactDefaultSeries(Long focusAreaId, String name) {
        return Collections.emptyList();
    }

    @Override
    public List<IImpactDefaultSeriesData> getImpactDefaultSeriesData(Long seriesId, Integer year) {
        return Collections.emptyList();
    }
}
