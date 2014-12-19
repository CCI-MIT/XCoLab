package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalRatingValue;
import com.ext.portlet.service.ProposalRatingValueLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the ProposalRatingValue service. Represents a row in the &quot;xcolab_ProposalRatingValue&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProposalRatingValueImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingValueImpl
 * @see com.ext.portlet.model.ProposalRatingValue
 * @generated
 */
public abstract class ProposalRatingValueBaseImpl
    extends ProposalRatingValueModelImpl implements ProposalRatingValue {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a proposal rating value model instance should use the {@link ProposalRatingValue} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalRatingValueLocalServiceUtil.addProposalRatingValue(this);
        } else {
            ProposalRatingValueLocalServiceUtil.updateProposalRatingValue(this);
        }
    }
}
