package org.xcolab.service.members.service.member;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xcolab.model.tables.pojos.Member;
import org.xcolab.service.members.domain.colabsso.ColabSSODao;
import org.xcolab.service.members.domain.member.MemberDao;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.members.util.PBKDF2PasswordEncryptor;
import org.xcolab.service.members.util.SHA1PasswordEncryptor;
import org.xcolab.service.members.util.SecureRandomUtil;
import org.xcolab.service.members.util.UsernameGenerator;
import org.xcolab.service.members.util.email.ConnectorEmmaAPI;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class MemberService {

    private final static int MAX_SCREEN_NAME_LENGTH = 26;

    private final MemberDao memberDao;

    private final ColabSSODao colabSSODao;

    private final ConnectorEmmaAPI connectorEmmaAPI;

    @Autowired
    public MemberService(MemberDao memberDao,ColabSSODao colabSSODao, ConnectorEmmaAPI connectorEmmaAPI) {
        this.memberDao = memberDao;
        this.connectorEmmaAPI = connectorEmmaAPI;
        this.colabSSODao = colabSSODao;
    }

    public Integer getMemberActivityCount(Long memberId) {
        return this.memberDao.getMemberActivityCount(memberId);
    }

    public String generateScreenName(String[] inputData) {
        UsernameGenerator usernameGenerator = new UsernameGenerator(inputData, true, MAX_SCREEN_NAME_LENGTH);

        String username;
        do {
            username = usernameGenerator.getNext();
        } while (colabSSODao.isScreenNameTaken(username));
        return username;
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        return hashPassword(password, false);
    }

    public String hashPassword(String password, boolean liferayCompatible)
            throws NoSuchAlgorithmException {
        if (liferayCompatible) {
            SHA1PasswordEncryptor sha1PasswordEncryptor = new SHA1PasswordEncryptor();
            return "{SHA-1}" + sha1PasswordEncryptor.doEncrypt("SHA-1", password);
        }
        PBKDF2PasswordEncryptor pbkdf2PasswordEncryptor = new PBKDF2PasswordEncryptor();
        return "PBKDF2_" + pbkdf2PasswordEncryptor
                .doEncrypt(PBKDF2PasswordEncryptor.DEFAULT_ALGORITHM, password, "");
    }

    public boolean validatePassword(String password, String hash)
            throws NoSuchAlgorithmException {
        SHA1PasswordEncryptor sha1PasswordEncryptor = new SHA1PasswordEncryptor();
        if (hash.startsWith("{SHA-1}")) {
            return sha1PasswordEncryptor.doEncrypt("SHA-1", password).equals(hash.substring(7));
        }
        if (hash.startsWith("PBKDF2_")) {
            PBKDF2PasswordEncryptor pbkdf2PasswordEncryptor = new PBKDF2PasswordEncryptor();
            final String unprefixedHash = hash.substring(7);
            return pbkdf2PasswordEncryptor.doEncrypt(
                    PBKDF2PasswordEncryptor.DEFAULT_ALGORITHM, password, unprefixedHash)
                    .equals(unprefixedHash);
        }
        return sha1PasswordEncryptor.doEncrypt("SHA-1", password).equals(hash);
    }

    public Member register(String screenName, String password, String email, String firstName, String lastName,
                           String shortBio, String country, Long facebookId, String openId, Long imageId, Long liferayUserId)
            throws NoSuchAlgorithmException {
        memberDao.createMember(screenName, hashPassword(password), email, firstName, lastName,
                shortBio, country, facebookId, openId, imageId, liferayUserId);
        final Member member = memberDao.findOneByScreenName(screenName);

        subscribeToNewsletter(member.getEmailAddress());
        return member;
    }

    public boolean login(Member member, String password) {
        try {
            if (validatePassword(password, member.getHashedPassword())) {
                //TODO: do login
                return true;
            }
        } catch (NoSuchAlgorithmException ignored) {
        }
        return false;
    }

    public boolean validateForgotPasswordToken(String passwordToken) throws NotFoundException {
        Member member = memberDao.findOneByForgotPasswordHash(passwordToken);

        if (member == null) return false;

        if (member.getForgotPasswordTokenExpireTime().getTime() >= Timestamp.valueOf(LocalDateTime.now()).getTime()) {
            return true;
        } else {
            return false;
        }
    }

    public String createNewForgotPasswordToken(Long memberId) throws NotFoundException {
        Member member = memberDao.getMember(memberId);
        String confirmationToken = Long.toHexString(SecureRandomUtil.nextLong());
        member.setForgotPasswordToken(confirmationToken);
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(10l);
        member.setForgotPasswordTokenExpireTime(Timestamp.valueOf(localDateTime));
        memberDao.updateMember(member);
        return confirmationToken;
    }

    public Long updateUserPasswordWithToken(String token, String newPassword) throws NoSuchAlgorithmException, NotFoundException{
        Member member = memberDao.findOneByForgotPasswordHash(token);
        if (member != null) {
            member.setPasswordModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
            member.setHashedPassword(hashPassword(newPassword));
            member.setForgotPasswordToken(null);
            member.setForgotPasswordTokenExpireTime(Timestamp.valueOf(LocalDateTime.now().plusMinutes(-10)));
            memberDao.updateMember(member);
            return member.getId_();
        }else{
            throw new NotFoundException();
        }
    }

    public boolean isSubscribedToNewsletter(long memberId) throws IOException, NotFoundException {
        final String email = memberDao.getMember(memberId).getEmailAddress();
        JSONObject memberDetails = connectorEmmaAPI.getMemberJSONfromEmail(email);
        return ConnectorEmmaAPI.hasMemberActiveSubscription(memberDetails, false);
    }

    public boolean subscribeToNewsletter(long memberId) throws NotFoundException {
        final String email = memberDao.getMember(memberId).getEmailAddress();
        return subscribeToNewsletter(email);
    }

    public boolean subscribeToNewsletter(String email) {
        try {
            JSONObject memberDetails = connectorEmmaAPI.subscribeMemberWithEmail(email);
            return ConnectorEmmaAPI.hasMemberActiveSubscription(memberDetails, true);
        } catch (IOException e) {
            return false;
        }
    }

    public boolean unsubscribeFromNewsletter(long memberId) throws NotFoundException {
        final String email = memberDao.getMember(memberId).getEmailAddress();
        try {
            return connectorEmmaAPI.unSubscribeMemberWithEmail(email);
        } catch (IOException e) {
            return false;
        }
    }
}