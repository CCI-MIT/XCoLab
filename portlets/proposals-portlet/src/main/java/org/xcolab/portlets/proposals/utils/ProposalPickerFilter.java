package org.xcolab.portlets.proposals.utils;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;

import com.ext.portlet.model.Proposal;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 04/12/13
 * Time: 10:43
 */
public class ProposalPickerFilter {
    /**
     * Filters the passed list of proposals
     * @param proposals the list of proposals to be filtered. Will be modified and contains the result!
     * @return A list of all proposals that were removed
     */
    public Set<Long> filter(List<Pair<Proposal,Date>> proposals) {
        return filter(proposals, null);
    }
    /**
     * Filters the passed list of proposals
     * @param proposals the list of proposals to be filtered. Will be modified and contains the result!
     * @param additionalFilterCriterion The additional filter criterion - if any - used by this filter
     * @return A list of all proposals that were removed
     */
    public Set<Long> filter(List<Pair<Proposal,Date>> proposals, Object additionalFilterCriterion) {
        return new HashSet<>();
    }
    /**
     * Filters the passed list of contests. Convenience method without an additional parameter
     * @param contests the list of contests to be filtered. Will be modified and contains the result!
     * @return A list of all contests that were removed
     */
    public Set<Long> filterContests(List<Pair<ContestWrapper,Date>> contests) {
        return filterContests(contests, null);
    }

    /**
     * Filters the passed list of contests
     * @param contests the list of contests to be filtered. Will be modified and contains the result!
     * @param additionalFilterCriterion The additional filter criterion - if any - used by this filter
     * @return A list of all contests that were removed
     */
    public Set<Long> filterContests(List<Pair<ContestWrapper,Date>> contests, Object additionalFilterCriterion){
        return new HashSet<>();
    }
}
