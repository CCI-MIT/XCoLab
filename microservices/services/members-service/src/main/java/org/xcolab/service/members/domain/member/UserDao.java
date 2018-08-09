package org.xcolab.service.members.domain.member;

import org.xcolab.model.tables.pojos.User;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findByGiven(PaginationHelper paginationHelper, String partialName,
            String partialEmail, String roleName, String email, String screenName, Long facebookId,
            String googleId, String colabSsoId, String climateXId, List<Long> roleIds);
    int countByGiven(String partialName, String partialEmail, String roleName);

    Optional<User> getUser(long userId);
    boolean updatePassword(long userId, String hashedPassword);

    Integer getUserMaterializedPoints(Long userId);
    Integer getUserHypotheticalPoints(Long userId);

    Optional<User> findOneByScreenName(String screenName);
    Optional<User> findOneByEmail(String email);
    Optional<User> findOneByLoginTokenId(String loginTokenId);

    boolean isScreenNameTaken(String screenName);

    boolean isEmailUsed(String email);

    Optional<User> findOneByForgotPasswordHash(String newPasswordToken);

    boolean updateUser(User member);
    User createUser(User pojo);

    List<User> findByIp(String ip) ;
    List<User> findByScreenNameName(String name);
}
