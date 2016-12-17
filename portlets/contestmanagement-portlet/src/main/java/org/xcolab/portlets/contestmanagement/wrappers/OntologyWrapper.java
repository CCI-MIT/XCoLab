package org.xcolab.portlets.contestmanagement.wrappers;


import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ontology.FocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.ontology.OntologySpace;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.wrapper.OntologySpaceWrapper;
import org.xcolab.wrapper.OntologyTermWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OntologyWrapper {

    private final Map<Long, OntologySpaceWrapper> ontologySpaces;
    private final Map<Long, OntologyTermWrapper> ontologyTerms;

    public OntologyWrapper() {
        ontologySpaces = new HashMap<>();
        ontologyTerms = new TreeMap<>();
        initOntologySpacesAndTerms();
    }

    public Collection<OntologyTermWrapper> getOntologyTerms() {
        return ontologyTerms.values();
    }

    public Collection<OntologySpaceWrapper> getOntologySpaces() {
        return ontologySpaces.values();
    }

    public List<OntologySpaceWrapper> getSortedOntologySpaces() {
        List<OntologySpaceWrapper> sortedSpaces = new ArrayList<>(ontologySpaces.values());
        Collections.sort(sortedSpaces, new Comparator<OntologySpaceWrapper>() {
            @Override
            public int compare(OntologySpaceWrapper o1, OntologySpaceWrapper o2) {
                return o1.getOrder() - o2.getOrder();
            }

        });
        return sortedSpaces;
    }

    public List<Long> getOntologyTermIdsForFocusAreaOfContest(Contest contest) {
            List<Long> ontologyTermIds = new ArrayList<>();
            Long focusAreaId = contest.getFocusAreaId();
            for (FocusAreaOntologyTerm focusAreaOntologyTerm : OntologyClientUtil.getFocusAreaOntologyTermsByFocusArea(focusAreaId)) {
                Long ontologyTermId = focusAreaOntologyTerm.getOntologyTermId();
                ontologyTermIds.add(ontologyTermId);
            }
            return ontologyTermIds;

    }

    private void initOntologySpacesAndTerms() {

            List<OntologySpace> ontologySpacesRaw = OntologyClientUtil.getAllOntologySpaces();
            List<OntologyTerm> ontologyTermsRaw = OntologyClientUtil.getAllOntologyTerms();

            for (OntologySpace space : ontologySpacesRaw) {
                ontologySpaces.put(space.getId(), new OntologySpaceWrapper(space));
            }

            for (OntologyTerm term : ontologyTermsRaw) {
                OntologyTermWrapper termWrapped = new OntologyTermWrapper(term);
                ontologySpaces.get(term.getOntologySpaceId()).addTerm(termWrapped);
                ontologyTerms.put(term.getId(), termWrapped);
            }

            for (OntologyTerm term : ontologyTermsRaw) {
                if (term.getParentId() > 0) {
                    ontologyTerms.get(term.getId())
                            .setParent(ontologyTerms.get(term.getParentId()));
                }
            }

    }

}
