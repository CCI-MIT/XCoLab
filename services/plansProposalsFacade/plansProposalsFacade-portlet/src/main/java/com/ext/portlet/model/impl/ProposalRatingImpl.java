package com.ext.portlet.model.impl;

/**
 * The extended model implementation for the ProposalRating service. Represents a row in the &quot;xcolab_ProposalRating&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.model.ProposalRating} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class ProposalRatingImpl extends ProposalRatingBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a proposal rating model instance should use the {@link com.ext.portlet.model.ProposalRating} interface instead.
     */
    public ProposalRatingImpl() {
    }

    public boolean isRatingComplete() {
        return (this.getRatingValueId() > 0 && this.getComment() != null && this.getComment() != "");
    }

}
