package org.xcolab.service.members.domain.member;

import org.xcolab.model.tables.pojos.Member;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;
import java.util.Optional;

public interface MemberDao {

    List<Member> findByGiven(PaginationHelper paginationHelper, String partialName,
            String roleName, String email, String screenName, Long facebookId, String openId);
    int countByGiven(String partialName, String roleName);

    Optional<Member> getMember(long memberId);
    boolean updatePassword(long memberId, String hashedPassword);

    Integer getMemberMaterializedPoints(Long memberId);
    Integer getMemberHypotheticalPoints(Long memberId);

    Integer getMemberActivityCount(Long memberId);

    Optional<Member> findOneByScreenName(String screenName);
    Optional<Member> findOneByEmail(String email);

    boolean isScreenNameTaken(String screenName);

    boolean isEmailUsed(String email);

    Optional<Member> findOneByForgotPasswordHash(String newPasswordToken);

    boolean updateMember(Member member);
    void createMember(String screenName, String password, String email,
            String firstName, String lastName, String shortBio, String country,
            Long facebookId, String openId, Long imageid, Long liferayUserId);

    List<Member> findByIp(String ip) ;

}
