package org.xcolab.service.members.domain.member;

import org.xcolab.client.user.pojo.IUser;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<IUser> findByGiven(PaginationHelper paginationHelper, String partialName,
            String partialEmail, String roleName, String email, String screenName, Long facebookId,
            String googleId, String colabSsoId, String climateXId, List<Long> roleIds);
    int countByGiven(String partialName, String partialEmail, String roleName);

    Optional<IUser> getUser(long userId);
    boolean updatePassword(long userId, String hashedPassword);

    Integer getUserMaterializedPoints(Long userId);
    Integer getUserHypotheticalPoints(Long userId);

    Optional<IUser> findOneByScreenName(String screenName);
    Optional<IUser> findOneByEmail(String email);
    Optional<IUser> findOneByLoginTokenId(String loginTokenId);

    boolean isScreenNameTaken(String screenName);

    boolean isEmailUsed(String email);

    Optional<IUser> findOneByForgotPasswordHash(String newPasswordToken);

    boolean updateUser(IUser member);
    IUser createUser(IUser pojo);

    List<IUser> findByIp(String ip) ;
    List<IUser> findByScreenNameName(String name);
}
