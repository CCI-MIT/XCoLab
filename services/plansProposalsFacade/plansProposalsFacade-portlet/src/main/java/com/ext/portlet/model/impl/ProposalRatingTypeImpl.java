package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalRatingValue;
import com.ext.portlet.service.ProposalRatingValueLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The extended model implementation for the ProposalRatingType service. Represents a row in the &quot;xcolab_ProposalRatingType&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.model.ProposalRatingType} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class ProposalRatingTypeImpl extends ProposalRatingTypeBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a proposal rating type model instance should use the {@link com.ext.portlet.model.ProposalRatingType} interface instead.
     */
    public ProposalRatingTypeImpl() {
    }

    public List<ProposalRatingValue> getRatingValues() throws SystemException {
        return ProposalRatingValueLocalServiceUtil.getRatingValuesForRatingTypeId(this.getId());
    }
}
