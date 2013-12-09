package org.xcolab.portlets.proposals.utils;

import java.util.List;
import com.ext.portlet.model.Proposal;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 04/12/13
 * Time: 10:43
 * To change this template use File | Settings | File Templates.
 */
public interface ProposalPickerFilter {
    public List<Proposal> filter(List<Proposal> proposals, Object additionalFilterCriterion);
}
