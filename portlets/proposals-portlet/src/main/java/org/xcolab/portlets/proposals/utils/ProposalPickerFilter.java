package org.xcolab.portlets.proposals.utils;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;

import com.ext.portlet.model.Proposal;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 04/12/13
 * Time: 10:43
 * To change this template use File | Settings | File Templates.
 */
public interface ProposalPickerFilter {
    public void filter(List<Pair<Proposal,Date>> proposals, Object additionalFilterCriterion);
    public void filterContests(List<Pair<ContestWrapper,Date>> proposals, Object additionalFilterCriterion);
}
