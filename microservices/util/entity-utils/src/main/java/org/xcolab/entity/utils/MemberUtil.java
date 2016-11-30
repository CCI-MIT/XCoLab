package org.xcolab.entity.utils;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.members.pojo.Member;

import javax.portlet.PortletRequest;

public final class MemberUtil {

    private MemberUtil() {
    }

    public static Member getMember(PortletRequest request) {
        String userIdStr = request.getRemoteUser();
        if (StringUtils.isBlank(userIdStr)) {
            return null;
        }

        return Member.fromId(userIdStr);
    }
}
