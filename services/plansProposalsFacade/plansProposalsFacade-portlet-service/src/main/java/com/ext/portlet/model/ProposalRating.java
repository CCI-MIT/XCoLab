package com.ext.portlet.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the ProposalRating service. Represents a row in the &quot;xcolab_ProposalRating&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingModel
 * @see com.ext.portlet.model.impl.ProposalRatingImpl
 * @see com.ext.portlet.model.impl.ProposalRatingModelImpl
 * @generated
 */
public interface ProposalRating extends ProposalRatingModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.model.impl.ProposalRatingImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public boolean isRatingComplete();
}
