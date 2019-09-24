package org.xcolab.service.members.domain.member;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<UserWrapper> findByGiven(PaginationHelper paginationHelper, String partialName,
            String partialEmail, String roleName, String email, String screenName, Long facebookId,
            String googleId, String colabSsoId, String climateXId, List<Long> roleIds);
    int countByGiven(String partialName, String partialEmail, String roleName);

    Optional<UserWrapper> getUser(long userId);
    boolean updatePassword(long userId, String hashedPassword);

    Integer getUserMaterializedPoints(Long userId);
    Integer getUserHypotheticalPoints(Long userId);

    Optional<UserWrapper> findOneByScreenName(String screenName);
    Optional<UserWrapper> findOneByEmail(String email);
    Optional<UserWrapper> findOneByLoginTokenId(String loginTokenId);

    boolean isScreenNameTaken(String screenName);

    boolean isEmailUsed(String email);

    Optional<UserWrapper> findOneByForgotPasswordHash(String newPasswordToken);

    boolean updateUser(UserWrapper member);
    UserWrapper createUser(UserWrapper pojo);

    List<UserWrapper> findByIp(String ip) ;
    List<UserWrapper> findByScreenNameName(String name);
}
