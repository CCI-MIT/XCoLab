package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.FocusAreaOntologyTerm;
import com.ext.portlet.model.OntologySpace;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.service.FocusAreaOntologyTermLocalServiceUtil;
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.util.exceptions.DatabaseAccessException;
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
        try {
            List<Long> ontologyTermIds = new ArrayList<>();
            Long focusAreaId = contest.getFocusAreaId();
            for (FocusAreaOntologyTerm focusAreaOntologyTerm : FocusAreaOntologyTermLocalServiceUtil
                    .findTermsByFocusArea(focusAreaId)) {
                Long ontologyTermId = focusAreaOntologyTerm.getOntologyTermId();
                ontologyTermIds.add(ontologyTermId);
            }
            return ontologyTermIds;
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private void initOntologySpacesAndTerms() {
        try {
            List<OntologySpace> ontologySpacesRaw = OntologySpaceLocalServiceUtil
                    .getOntologySpaces(0, Integer.MAX_VALUE);
            List<OntologyTerm> ontologyTermsRaw = OntologyTermLocalServiceUtil
                    .getOntologyTerms(0, Integer.MAX_VALUE);

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
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

}
