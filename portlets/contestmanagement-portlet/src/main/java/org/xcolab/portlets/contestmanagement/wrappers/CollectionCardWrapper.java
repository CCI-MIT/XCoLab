package org.xcolab.portlets.contestmanagement.wrappers;


import org.xcolab.client.contest.pojo.Contest;
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

public class CollectionCardWrapper {

    private final Map<Long, OntologySpaceWrapper> ontologySpaces;
    private final Map<Long, OntologyTermWrapper> ontologyTerms;

    public CollectionCardWrapper() {
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
            return  new ArrayList<>();
    }

    private void initOntologySpacesAndTerms() {
        System.out.println("lol");
    }

}
