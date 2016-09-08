package org.xcolab.portlets.staffmembers;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.StaffMember;

import java.io.Serializable;

public class StaffMemberWrapper implements Serializable {

    private static final Log _log = LogFactoryUtil.getLog(StaffMemberWrapper.class);

    private static final long serialVersionUID = 1L;
    private final StaffMember staffMember;
    private final Member colabMember;

    public StaffMemberWrapper(StaffMember staffMember) {
        this.staffMember = staffMember;
        this.colabMember = getColabMember();
    }

    private Member getColabMember() {
        long userId = staffMember.getUserId();
        if (userId == 0L) {
            return null;
        }

        try {
            return MembersClient.getMember(staffMember.getUserId());
        } catch (MemberNotFoundException e) {
            _log.warn("Member account " + staffMember.getUserId()
                    + " linked to staff member " + staffMember.getId_() + " does not exist ");
            return null;
        }
    }


    private static String nl2br(String string) {
        if (string != null) {
            //allow line breaks in the string and render them as html
            return string.replaceAll("\n", "<br />\n");
        } else {
            return null;
        }
    }

    public String getName() {
        if (colabMember != null) {
            return colabMember.getFullName();
        } else {
            return this.staffMember.getFirstNames() + " " + this.staffMember.getLastName();
        }
    }

    public Integer getSort() {
        if(this.staffMember.getSort() != null){
            return this.staffMember.getSort();
        } else {
            return 0;
        }
    }

    public String getPhotoUrl() {
        if (this.staffMember.getPhotoUrl() != null && !this.staffMember.getPhotoUrl().isEmpty()) {
            return this.staffMember.getPhotoUrl();
            //if the photoUrl is not directly set, use the one from the climate colab profile
        } else {
            if (colabMember != null && colabMember.getPortraitId() != 0) {

                return "/image/user_"
                        + "male_portrait?img_id="
                        + colabMember.getPortraitId();
            } else {
                return null;
            }
        }
    }

    public Long getPhotoId() {
        if(this.colabMember != null ){
            return this.colabMember.getPortraitFileEntryId();
        } else {
            return null;
        }
    }

    public String getUrl() {
        if (StringUtils.isBlank(staffMember.getUrl()) && colabMember != null) {
            return "/web/guest/member/-/member/userId/" + colabMember.getUserId();
        } else {
            return staffMember.getUrl();
        }
    }

    public String getRole() {
        return nl2br(staffMember.getRole());
    }

    public String getOrganization() {
        return nl2br(staffMember.getOrganization());
    }
}
