package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.service.ContestDebateLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model implementation for the ContestDebate service. Represents a row in the &quot;Contests_ContestDebate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.contests.model.ContestDebate} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class ContestDebateImpl extends ContestDebateBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a contest debate model instance should use the {@link com.ext.portlet.contests.model.ContestDebate} interface instead.
     */
    public ContestDebateImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            ContestDebateLocalServiceUtil.addContestDebate(this);
        }
        else {
            ContestDebateLocalServiceUtil.updateContestDebate(this);
        }
    }
    
    public void delete() throws SystemException {
        if (isNew()) {
            // ignore
        }
        else {
            ContestDebateLocalServiceUtil.deleteContestDebate(this);
        }
    }
}
