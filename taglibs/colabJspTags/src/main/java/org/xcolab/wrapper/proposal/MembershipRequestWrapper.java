package org.xcolab.wrapper.proposal;

import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 10/7/13
 * Time: 4:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class MembershipRequestWrapper {
    private User requestUser;
    private MembershipRequest membershipRequest;

    public MembershipRequestWrapper(MembershipRequest membershipRequest){
        this.membershipRequest = membershipRequest;

        try{
            this.requestUser = UserLocalServiceUtil.getUser(membershipRequest.getUserId());
        } catch (Exception e) { e.printStackTrace();}
    }

    public User getRequestUser(){
        return requestUser;
    }

    public MembershipRequest getMembershipRequest(){
        return membershipRequest;
    }



}
