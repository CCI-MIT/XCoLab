package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ProposalRatingType;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.portlets.proposals.wrappers.ProposalRatingTypeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalRatingWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Manuel Thurner
 */
public class RatingBean {
    protected List<ProposalRatingTypeWrapper> ratingTypes;
    protected Map<Long, String> ratingValues;

    private Long contestPhaseId;

    private String comment;

    private Long screeningUserId;

    public RatingBean(ProposalWrapper wrapper, List<ProposalRatingType> presetRatingTypes) throws PortalException, SystemException {
        this.ratingValues = new HashMap<Long, String>();
        this.ratingTypes = new ArrayList<ProposalRatingTypeWrapper>();

        //initialize ratingValues and types
        for (ProposalRatingType type : presetRatingTypes) {
            ratingValues.put(type.getId(), "");
            ratingTypes.add(new ProposalRatingTypeWrapper(type));
        }

        //get the existing ratings from the wrapper
        for (ProposalRatingWrapper ratingWrapper : wrapper.getRatings()) {
            ratingValues.put(ratingWrapper.getRatingTypeId(), String.valueOf(ratingWrapper.unwrap().getRatingValueId()));
        }
        comment = wrapper.getRatingComment();
    }

    public RatingBean() {
    }

    public Long getContestPhaseId() {
        return contestPhaseId;
    }

    public void setContestPhaseId(Long contestPhaseId) {
        this.contestPhaseId = contestPhaseId;
    }

    public String getComment() {
       return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Map<Long, String> getRatingValues() {
        return ratingValues;
    }

    public void setRatingValues(Map<Long, String> ratingValues) {
        this.ratingValues = ratingValues;
    }

    public List<ProposalRatingTypeWrapper> getRatingTypes() {
        return ratingTypes;
    }

    public void setScreeningUserId (Long screeningUserId){this.screeningUserId=screeningUserId;}

    public Long getScreeningUserId(){return screeningUserId;}
}
