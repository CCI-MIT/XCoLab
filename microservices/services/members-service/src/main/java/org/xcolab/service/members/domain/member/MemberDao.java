package org.xcolab.service.members.domain.member;

import org.xcolab.model.tables.pojos.Member;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;
import java.util.Optional;

public interface MemberDao {

    List<Member> findByGiven(PaginationHelper paginationHelper, String partialName,
            String partialEmail, String roleName, String email, String screenName, Long facebookId,
            String googleId);
    int countByGiven(String partialName, String partialEmail, String roleName);

    Optional<Member> getMember(long memberId);
    boolean updatePassword(long memberId, String hashedPassword);

    Integer getMemberMaterializedPoints(Long memberId);
    Integer getMemberHypotheticalPoints(Long memberId);

    Optional<Member> findOneByScreenName(String screenName);
    Optional<Member> findOneByEmail(String email);
    Optional<Member> findOneByLoginTokenId(String loginTokenId);

    boolean isScreenNameTaken(String screenName);

    boolean isEmailUsed(String email);

    Optional<Member> findOneByForgotPasswordHash(String newPasswordToken);

    boolean updateMember(Member member);
    void createMember(String screenName, String password, String email,
            String firstName, String lastName, String shortBio, String country,
            Long facebookId, String openId, Long imageid, Long liferayUserId, String googleId, String defaultLocale);

    List<Member> findByIp(String ip) ;
    List<Member> findByScreenNameName(String name);
}
