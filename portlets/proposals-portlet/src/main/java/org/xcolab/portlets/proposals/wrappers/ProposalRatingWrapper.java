package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.model.ProposalRatingType;
import com.ext.portlet.model.ProposalRatingValue;
import com.ext.portlet.service.ProposalRatingTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingValueLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.text.DecimalFormat;

/**
 * Created by Manuel Thurner
 */
public class ProposalRatingWrapper {
	private ProposalRating proposalRating;
	private ProposalRatingType ratingType;
	private ProposalRatingValue ratingValue;
	private Long roundFactor;

	public ProposalRatingWrapper(ProposalRating proposalRating) {
		this.proposalRating = proposalRating;
	}
	public ProposalRatingWrapper(ProposalRating proposalRating, Long roundFactor) {
		this.proposalRating = proposalRating;
		this.roundFactor = roundFactor;
	}

	public ProposalRatingWrapper() {

	}

	public String getRatingValueName() {
		ProposalRatingValue ratingValue = this.getRatingValue();
		if (ratingValue != null) {
			return ratingValue.getName();
		} else {
			return "";
		}
	}

	public String getRatingTypeLabel() {
		ProposalRatingType ratingType = this.getRatingType();
		if (ratingType != null) {
			return ratingType.getLabel();
		} else {
			return "";
		}
	}

	public boolean getIsActive() {
		ProposalRatingType ratingType = this.getRatingType();
		if (ratingType != null) {
			return ratingType.getIsActive();
		} else {
			return true;
		}
	}

	public Long getRatingTypeId() {
		ProposalRatingType ratingType = this.getRatingType();
		if (ratingType != null) {
			return ratingType.getId();
		} else {
			return null;
		}
	}

	public ProposalRatingType getRatingType() {
		ProposalRatingValue ratingValue = this.getRatingValue();
		try {
			if (ratingValue != null) {
				if (ratingType == null)
					ratingType = ProposalRatingTypeLocalServiceUtil.fetchProposalRatingType(ratingValue.getRatingTypeId());
				return ratingType;
			}
		} catch (SystemException e) {
		}
		return null;
	}

	public ProposalRatingValue getRatingValue() {
		try {
			if (ratingValue == null)
				ratingValue = ProposalRatingValueLocalServiceUtil.fetchProposalRatingValue(this.proposalRating.getRatingValueId());
			return ratingValue;
		} catch (SystemException e) {
			return null;
		}
	}

	public double getNotRoundedRatingValue() {
		double ratingValueNotRounded = 0.;
		try {
			ratingValueNotRounded = (double) this.proposalRating.getRatingValueId() / (double) roundFactor;
			ratingValueNotRounded = ratingValueNotRounded / getRatingTypeId();
		} catch (Exception e){
		}
		return ratingValueNotRounded;
	}

	public String getNotRoundedRatingValueFormatted(){
		DecimalFormat f = new DecimalFormat("#0.0");
		return f.format(getNotRoundedRatingValue());
	}

	public double getRatingValueInPercent(){
		double ratingValueInPercent = 0;
		Double proposalRatingValue = getNotRoundedRatingValue();
		if(proposalRatingValue != null){
			ratingValueInPercent = proposalRatingValue / 5.0 * 100.0;
		}
		return ratingValueInPercent;
	}

	public ProposalRating unwrap() {
		return proposalRating;
	}

	public void setRatingValueId(Long id) {
		this.proposalRating.setRatingValueId(id);
	}
}
