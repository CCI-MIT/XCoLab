package org.xcolab.service.members.service.member;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xcolab.model.tables.pojos.Member;
import org.xcolab.model.tables.pojos.User_;
import org.xcolab.service.members.domain.member.MemberDao;
import org.xcolab.service.members.util.SHA1PasswordEncryptor;
import org.xcolab.service.members.util.UsernameGenerator;
import org.xcolab.service.members.util.email.ConnectorEmmaAPI;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
public class MemberService {

    private final static int MAX_SCREEN_NAME_LENGTH = 26;

    private final MemberDao memberDao;

    private final ConnectorEmmaAPI connectorEmmaAPI;

    @Autowired
    public MemberService(MemberDao memberDao, ConnectorEmmaAPI connectorEmmaAPI) {
        this.memberDao = memberDao;
        this.connectorEmmaAPI = connectorEmmaAPI;
    }

    public Integer getMemberActivityCount(Long memberId) {
        return this.memberDao.getMemberActivityCount(memberId);
    }

    public void updateMember(Member member) {
        this.memberDao.updateMember(member);
    }

    public String generateScreenName(String[] inputData) {
        UsernameGenerator usernameGenerator = new UsernameGenerator(inputData, true, MAX_SCREEN_NAME_LENGTH);

        String username;
        do {
            username = usernameGenerator.getNext();
        } while (memberDao.isScreenNameTaken(username));
        return username;
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        SHA1PasswordEncryptor sha1PasswordEncryptor = new SHA1PasswordEncryptor();
        return "{SHA-1}" + sha1PasswordEncryptor.doEncrypt("SHA-1", password);
    }

    public boolean validatePassword(String password, String hash) throws NoSuchAlgorithmException {
        SHA1PasswordEncryptor sha1PasswordEncryptor = new SHA1PasswordEncryptor();
        if (hash.startsWith("{SHA-1}")) {
            return sha1PasswordEncryptor.doEncrypt("SHA-1", password).equals(hash.substring(7));
        }
        return sha1PasswordEncryptor.doEncrypt("SHA-1", password).equals(hash);
    }

    public Member register(String screenName, String password, String email, String firstName, String lastName,
            String shortBio, String country, String fbIdString, String openId, String imageId, long liferayUserId)
            throws NoSuchAlgorithmException {
        memberDao.createMember(screenName, hashPassword(password), email, firstName, lastName,
                    shortBio, country, Long.parseLong(fbIdString), openId, liferayUserId);
        final Member member = memberDao.findOneByScreenName(screenName);

        subscribeToNewsletter(member.getId_());
        return member;
    }

    public boolean login(User_ member, String password) {
        try {
            if (validatePassword(password, member.getPassword_())) {
                //do login
                return true;
            }
        } catch (NoSuchAlgorithmException ignored) {}
        return false;
    }

    public boolean isSubscribedToNewsletter(long memberId) throws IOException {
        final String email = memberDao.getMember(memberId).getEmailAddress();
        JSONObject memberDetails = connectorEmmaAPI.getMemberJSONfromEmail(email);
        return ConnectorEmmaAPI.hasMemberActiveSubscription(memberDetails, false);
    }

    public boolean subscribeToNewsletter(long memberId) {
        final String email = memberDao.getMember(memberId).getEmailAddress();
        try {
            JSONObject memberDetails = connectorEmmaAPI.subscribeMemberWithEmail(email);
            return ConnectorEmmaAPI.hasMemberActiveSubscription(memberDetails, true);
        } catch (IOException e) {
            return false;
        }
    }

    public boolean unsubscribeFromNewsletter(long memberId) {
        final String email = memberDao.getMember(memberId).getEmailAddress();
        try {
            return connectorEmmaAPI.unSubscribeMemberWithEmail(email);
        } catch (IOException e) {
            return false;
        }
    }
}