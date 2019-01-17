package org.xcolab.view.pages.contestmanagement.wrappers;

import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.ContestWrapper;
import org.xcolab.client.contest.pojo.IFocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.OntologySpaceWrapper;
import org.xcolab.client.contest.pojo.OntologyTermWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OntologyWrapper {

    private final Map<Long, org.xcolab.view.taglibs.xcolab.wrapper.OntologySpaceWrapper> ontologySpaces;
    private final Map<Long, org.xcolab.view.taglibs.xcolab.wrapper.OntologyTermWrapper> ontologyTerms;

    public OntologyWrapper() {
        ontologySpaces = new HashMap<>();
        ontologyTerms = new TreeMap<>();
        initOntologySpacesAndTerms();
    }

    private void initOntologySpacesAndTerms() {

        List<OntologySpaceWrapper> ontologySpacesRaw = OntologyClientUtil.getAllOntologySpaces();
        List<OntologyTermWrapper> ontologyTermsRaw = OntologyClientUtil.getAllOntologyTerms();

        for (OntologySpaceWrapper space : ontologySpacesRaw) {
            ontologySpaces.put(space.getId(), new org.xcolab.view.taglibs.xcolab.wrapper.OntologySpaceWrapper(space));
        }

        for (OntologyTermWrapper term : ontologyTermsRaw) {
            org.xcolab.view.taglibs.xcolab.wrapper.OntologyTermWrapper
                    termWrapped = new org.xcolab.view.taglibs.xcolab.wrapper.OntologyTermWrapper(term);
            ontologySpaces.get(term.getOntologySpaceId()).addTerm(termWrapped);
            ontologyTerms.put(term.getId(), termWrapped);
        }

        for (OntologyTermWrapper term : ontologyTermsRaw) {
            if (term.getParentId() > 0) {
                ontologyTerms.get(term.getId())
                        .setParent(ontologyTerms.get(term.getParentId()));
            }
        }

    }

    public Collection<org.xcolab.view.taglibs.xcolab.wrapper.OntologyTermWrapper> getOntologyTerms() {
        return ontologyTerms.values();
    }

    public Collection<org.xcolab.view.taglibs.xcolab.wrapper.OntologySpaceWrapper> getOntologySpaces() {
        return ontologySpaces.values();
    }

    public List<org.xcolab.view.taglibs.xcolab.wrapper.OntologySpaceWrapper> getSortedOntologySpaces() {
        List<org.xcolab.view.taglibs.xcolab.wrapper.OntologySpaceWrapper> sortedSpaces = new ArrayList<>(ontologySpaces.values());
        sortedSpaces.sort(Comparator.comparingInt(
                org.xcolab.view.taglibs.xcolab.wrapper.OntologySpaceWrapper::getOrder));
        return sortedSpaces;
    }

    public List<Long> getOntologyTermIdsForFocusAreaOfContest(ContestWrapper contest) {
        List<Long> ontologyTermIds = new ArrayList<>();
        Long focusAreaId = contest.getFocusAreaId();
        for (IFocusAreaOntologyTerm focusAreaOntologyTerm : OntologyClientUtil
                .getFocusAreaOntologyTermsByFocusArea(focusAreaId)) {
            Long ontologyTermId = focusAreaOntologyTerm.getOntologyTermId();
            ontologyTermIds.add(ontologyTermId);
        }
        return ontologyTermIds;

    }

}
