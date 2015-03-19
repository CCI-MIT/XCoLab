package org.xcolab.wrapper.proposal;

import com.ext.portlet.model.ProposalRatingType;
import com.ext.portlet.model.ProposalRatingValue;
import com.ext.portlet.service.ProposalRatingValueLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * Created by Manuel Thurner
 */
public class ProposalRatingTypeWrapper {
    private ProposalRatingType proposalRatingType;


    public ProposalRatingTypeWrapper(ProposalRatingType proposalRatingType) {
        this.proposalRatingType = proposalRatingType;
    }

    public List<ProposalRatingValue> getRatingValues() throws SystemException {
        return ProposalRatingValueLocalServiceUtil.getRatingValuesForRatingTypeId(this.proposalRatingType.getId());
    }

    public Long getId() {
        return this.proposalRatingType.getId();
    }

    public String getLabel() {
        return this.proposalRatingType.getLabel();
    }
    public String getDescription() {
        return this.proposalRatingType.getDescription();
    }

}
