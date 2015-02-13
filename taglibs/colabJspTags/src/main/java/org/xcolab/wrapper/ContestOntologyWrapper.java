package org.xcolab.wrapper;

import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.util.*;

public class ContestOntologyWrapper {
    private static final String WHERE = "where";
    private static final String WHAT = "what";
    private static final String WHO = "who";
    private static final String HOW = "how";

    private final static Map<Long, FocusArea> faCache = new HashMap<Long, FocusArea>();
    private Map<String, List<OntologyTerm>> ontologySpaceCache = new HashMap<String, List<OntologyTerm>>();
    private Map<String, String> ontologyJoinedNames = new HashMap<String, String>();

    private Contest contest;

    public ContestOntologyWrapper(Contest contest) {
        this.contest = contest;
    }

    public void persist() throws SystemException {
        contest.persist();
    }

    public long getContestPK() {
        return contest.getContestPK();
    }

    public void setContestPK(long ContestPK) {
        contest.setContestPK(ContestPK);
    }

    public long getFocusAreaId() {
        return contest.getFocusAreaId();
    }

    public void setFocusAreaId(long focusAreaId) {
        contest.setFocusAreaId(focusAreaId);
    }

    public List<OntologyTerm> getWho() throws PortalException, SystemException {
        return getTermFromSpace(WHO);
    }

    public String getWhoName() throws PortalException, SystemException {
        return getTermNameFromSpace(WHO);
    }

    public List<OntologyTerm> getWhat() throws PortalException, SystemException {
        return getTermFromSpace(WHAT);
    }

    public String getWhatName() throws PortalException, SystemException {
        return getTermNameFromSpace(WHAT);
    }

    public List<OntologyTerm> getWhere() throws PortalException,
            SystemException {
        return getTermFromSpace(WHERE);
    }

    public String getWhereName() throws PortalException, SystemException {
        return getTermNameFromSpace(WHERE);
    }

    public List<OntologyTerm> getHow() throws PortalException,
            SystemException {
        return getTermFromSpace(HOW);
    }

    public String getHowName() throws PortalException, SystemException {
        return getTermNameFromSpace(HOW);
    }

    public String getTermNameFromSpace(String space) throws PortalException, SystemException {
        String ontologyJoinedName = ontologyJoinedNames.get(space);
        if (ontologyJoinedName == null) {
            getTermFromSpace(space);
            ontologyJoinedName = ontologyJoinedNames.get(space);
        }

        if (ontologyJoinedName == null) {
            return "";
        }
        return ontologyJoinedName;
    }

    private List<OntologyTerm> getTermFromSpace(String space)
            throws PortalException, SystemException {

        if (!ontologySpaceCache.containsKey(space) && getFocusAreaId() > 0) {
            if (!faCache.containsKey(contest.getFocusAreaId())) {
                FocusArea fa = FocusAreaLocalServiceUtil.getFocusArea(contest
                        .getFocusAreaId());
                if (fa == null) {
                    ontologySpaceCache.put(space, null);
                    ontologyJoinedNames.put(space, "");
                    return null;
                }
                faCache.put(fa.getId(), fa);
            }
            List<OntologyTerm> terms = new ArrayList<OntologyTerm>();
            StringBuilder joinedTerms = new StringBuilder();
            for (OntologyTerm t : FocusAreaLocalServiceUtil.getTerms(faCache.get(contest.getFocusAreaId()))) {
                if (OntologyTermLocalServiceUtil.getSpace(t).getName()
                        .equalsIgnoreCase(space)) {
                    terms.add(t);
                    joinedTerms.append(t.getName());
                }
            }
            ontologySpaceCache.put(space, terms.isEmpty() ? null : terms);
            ontologyJoinedNames.put(space, joinedTerms.toString());

        }
        return ontologySpaceCache.get(space);
    }

    public boolean getHasFocusArea() {
        return contest.getFocusAreaId() > 0;
    }

}
