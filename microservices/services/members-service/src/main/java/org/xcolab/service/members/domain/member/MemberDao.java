package org.xcolab.service.members.domain.member;

import org.xcolab.model.tables.pojos.Member;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface MemberDao {

    List<Member> findByGiven(PaginationHelper paginationHelper, String partialName,
            String roleName, String email, String screenName, Long facebookId, String openId);

    Member getMember(long memberId) throws NotFoundException;

    Integer countMembersFilteredByCategory(String filter, String roleName);

    Integer countMembers(String filter);

    Integer getMemberMaterializedPoints(Long memberId);

    Integer getMemberActivityCount(Long memberId);

    Member findOneByScreenName(String screenName);
    Member findOneByEmail(String email);

    Member findOneByForgotPasswordHash(String newPasswordToken);

    boolean updateMember(Member member);
    void createMember(String screenName, String password, String email, String firstName, String lastName,
            String shortBio, String country, Long facebookId, String openId, Long liferayUserId);

    boolean isScreenNameTaken(String screenName);
    boolean isEmailUsed(String email);
}
