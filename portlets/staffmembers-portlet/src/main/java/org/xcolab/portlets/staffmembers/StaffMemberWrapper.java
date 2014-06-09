package org.xcolab.portlets.staffmembers;

import com.ext.portlet.model.StaffMember;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.Serializable;

public class StaffMemberWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private StaffMember staffMember;

    public StaffMemberWrapper(StaffMember staffMember) {
        this.staffMember = staffMember;
    }

    private User getUser() {
        long userId = this.staffMember.getUserId();
        if (userId == 0L) {
            return null;
        }

        try {
            return UserLocalServiceUtil.getUser(this.staffMember.getUserId());
        } catch (PortalException e) {
            return null;
        } catch (SystemException e) {
            return null;
        }

    }
    private boolean hasCoLabUser() {
        return this.getUser() != null;
    }

    public String getName() {
        if (this.hasCoLabUser()) {
            return this.getUser().getFullName();
        } else {
            return this.staffMember.getFirstNames()+" "+this.staffMember.getLastName();
        }
    }

    public String getPhoto() {
        try {
            if (this.hasCoLabUser()) {
                return Helper.getThemeDisplay().getPathImage() + "/user_"
                        + (this.getUser().getFemale() ? "female" : "male") + "_portrait?img_id="
                        + this.getUser().getPortraitId();

            } else {
                return this.staffMember.getFirstNames()+" "+this.staffMember.getLastName();
            }
        } catch (Exception e) {
            return "";
        }

    }



    
}
