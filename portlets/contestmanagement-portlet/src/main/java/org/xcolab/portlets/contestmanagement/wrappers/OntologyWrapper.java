package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import org.xcolab.wrapper.OntologySpaceWrapper;
import org.xcolab.wrapper.OntologyTermWrapper;

import java.util.*;

/**
 * Created by Thomas on 2/16/2015.
 */
public class OntologyWrapper {

    private Map<Long, OntologySpaceWrapper> ontologySpaces;
    private Map<Long, OntologyTermWrapper> ontologyTerms;

    public OntologyWrapper() throws Exception{

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

    public List<Long> getOntologyTermIdsForFocusAreaOfContest(Contest contest) throws Exception{
        List<Long> ontologyTermIds = new ArrayList<>();
        Long focusAreaId = contest.getFocusAreaId();
        for(FocusAreaOntologyTerm focusAreaOntologyTerm : FocusAreaOntologyTermLocalServiceUtil.findTermsByFocusArea(focusAreaId)){
            Long ontologyTermId = focusAreaOntologyTerm.getOntologyTermId();
            ontologyTermIds.add(ontologyTermId);
        }
        return ontologyTermIds;
    }

    private void initOntologySpacesAndTerms() throws Exception{
        List<OntologySpace> ontologySpacesRaw = OntologySpaceLocalServiceUtil.getOntologySpaces(0, Integer.MAX_VALUE);
        List<OntologyTerm> ontologyTermsRaw = OntologyTermLocalServiceUtil.getOntologyTerms(0, Integer.MAX_VALUE);

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
                ontologyTerms.get(term.getId()).setParent(ontologyTerms.get(term.getParentId()));
            }
        }

    }


}
