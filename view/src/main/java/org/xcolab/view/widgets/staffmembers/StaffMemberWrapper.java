package org.xcolab.view.widgets.staffmembers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.StaffUserWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

import java.io.Serializable;

public class StaffMemberWrapper implements Serializable {

    private static final Logger _log = LoggerFactory.getLogger(StaffMemberWrapper.class);

    private static final long serialVersionUID = 1L;
    private final StaffUserWrapper staffMember;
    private final UserWrapper colabMember;

    public StaffMemberWrapper(StaffUserWrapper staffMember) {
        this.staffMember = staffMember;
        this.colabMember = getColabMember();
    }

    private UserWrapper getColabMember() {
        long userId = staffMember.getUserId();
        if (userId == 0L) {
            return null;
        }

        try {
            return StaticUserContext.getUserClient().getMember(staffMember.getUserId());
        } catch (MemberNotFoundException e) {
            _log.warn("Member account {} linked to staff member {} does not exist ",
                    staffMember.getUserId(), staffMember.getId());
            return null;
        }
    }

    public UserWrapper getMember() {
        return this.colabMember;
    }

    public String getName() {
        if (colabMember != null) {
            return colabMember.getFullName();
        } else {
            return this.staffMember.getFirstNames() + " " + this.staffMember.getLastName();
        }
    }

    public String getLastName() {
        if (this.staffMember.getLastName() != null && !staffMember.getLastName().isEmpty()) {
            return this.staffMember.getLastName();
        } else {
            return this.colabMember.getLastName();
        }
    }

    public Integer getSort() {
        if (this.staffMember.getSortOrder() != null) {
            return this.staffMember.getSortOrder();
        } else {
            return 0;
        }
    }

    public String getPhotoUrl() {
        if (this.staffMember.getPhotoUrl() != null && !this.staffMember.getPhotoUrl().isEmpty()) {
            return this.staffMember.getPhotoUrl();
        }
        if (colabMember != null && colabMember.getPortraitId() != 0) {
            return "/image?defaultImage=MEMBER&img_id=" + colabMember.getPortraitId();
        }
        return "/image?defaultImage=MEMBER";
    }

    public String getUrl() {
        if (StringUtils.isBlank(staffMember.getUrl()) && colabMember != null) {
            return "/members/profile/" + colabMember.getId();
        } else {
            return staffMember.getUrl();
        }
    }

    public String getRole() {
        return nl2br(staffMember.getRole());
    }

    private static String nl2br(String string) {
        if (string != null) {
            //allow line breaks in the string and render them as html
            return string.replaceAll("\n", "<br />\n");
        } else {
            return null;
        }
    }

    public String getOrganization() {
        return nl2br(staffMember.getOrganization());
    }

    public Long getStaffuserId() {
        return staffMember.getId();
    }
}
