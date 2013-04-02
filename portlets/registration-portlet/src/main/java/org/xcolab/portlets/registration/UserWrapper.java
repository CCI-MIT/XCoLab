package org.xcolab.portlets.registration;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;

import com.ext.portlet.community.CommunityConstants;
import com.ext.utils.userInput.UserInputException;
import com.ext.utils.userInput.service.UserInputFilterUtil;
import com.icesoft.faces.component.inputfile.InputFile;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class UserWrapper {

    private User user; 
    private String about;
    private String realName;
    private String firstName;
    private String lastName;
    private String email;
    private String screenName;
    private File newUserProfile;
    private String filteredAbout;
    private String fileErrorMessage;
    private String newPassword;
    private String currentPassword;
    private String newPasswordRetype;
    
    private final static Log _log = LogFactoryUtil.getLog(UserWrapper.class);
    
    public UserWrapper(User user) throws PortalException, SystemException {
        init(user);
    }
    
    private void init(User user) throws PortalException, SystemException {
        this.user = user;
        about = ExpandoValueLocalServiceUtil.getData(User.class.getName(), CommunityConstants.EXPANDO, 
                CommunityConstants.BIO, user.getUserId(), StringPool.BLANK);
        //filteredAbout = Helper.filterLinkifyUrls(Helper.filterLineBreaks(about));
        filteredAbout = about;
        
        realName = getName(user.getFullName(), user.getScreenName());
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmailAddress();
        screenName = user.getScreenName();
        
        String firstPart = realName.substring(0, realName.length() / 2).trim();
        String secondPart = realName.substring(realName.length() / 2).trim();
        if (firstPart.equals(secondPart)) {
            realName = firstName;
        }
        
    }
    
    public String getAbout() {
        return about;
    }
    
    public void setAbout(String about) throws UserInputException {
        this.about = UserInputFilterUtil.filterHtml(about);
    }
    
    public String getScreenName() {
        return screenName;
    }
    
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
    
    public String getPortrait() throws PortalException, SystemException {
    	/*
        return Helper.getThemeDisplay().getPathImage() + 
            "/user_" + 
            (user.getFemale() ? "female" : "male") + 
            "_portrait?img_id=" + 
            user.getPortraitId();
            */
    	return null;
    }
    
    public String getRealName() {
        return realName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public Date getJoinDate() {
        return user.getCreateDate();
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    
    private String getName(String name, String defaultName) {
        if (name == null || name.trim().equals("")) {
            return defaultName;
        }
        return name;
    }
    
    public void uploadPortrait(ActionEvent e) throws IOException {
        InputFile inputFile = (InputFile) e.getSource();
        if (inputFile.getStatus() == InputFile.INVALID) {
            fileErrorMessage = "Provided file isn't a valid image.";
            _log.error("There was an error when uploading file", inputFile.getFileInfo().getException());
            return;
        }
        
        if (! inputFile.getFileInfo().getContentType().startsWith("image")) {
            fileErrorMessage = "Provided file isn't a valid image.";
            _log.error("There was an error when uploading file", inputFile.getFileInfo().getException());
            return;
        }
        fileErrorMessage = null;
        resizeAndCropImage(inputFile.getFile());
        newUserProfile = inputFile.getFile();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void persistChanges() throws Exception {
        boolean changed = false;
        if (! user.getScreenName().equals(screenName)) {
            user.setScreenName(screenName);
            changed = true;
        }
        if (! user.getEmailAddress().equals(email)) {
            user.setEmailAddress(email);
            changed = true;
        }
        if (! user.getFirstName().equals(firstName)) {
            user.setFirstName(firstName);
            changed = true;
        }
        if (! user.getLastName().equals(lastName)) {
            user.setLastName(lastName);
            changed = true;
        }
        
        if (newPassword.trim().length() > 0) {
            //user.setPassword(PwdEncryptor.encrypt(newPassword));
            changed = true;
        }

        long companyId = CompanyThreadLocal.getCompanyId(); 
        if (companyId == 0) {
           //CompanyThreadLocal.setCompanyId(Helper.getThemeDisplay().getCompanyId());
        }
        

        String existingBio = ExpandoValueLocalServiceUtil.getData(
                User.class.getName(), CommunityConstants.EXPANDO,
                CommunityConstants.BIO, user.getUserId(),
                StringPool.BLANK);
        if (! existingBio.equals(about)) {
            ExpandoValueLocalServiceUtil.addValue(User.class.getName(), CommunityConstants.EXPANDO, 
                    CommunityConstants.BIO, user.getUserId(), about);
        }
        
        if (changed) {
            UserLocalServiceUtil.updateUser(user);
        }
        
        if (newUserProfile != null) {
            byte[] bytes = FileUtil.getBytes(newUserProfile);

            if ((bytes != null) && (bytes.length > 0)) {
                /* we need to set permission checker for liferay */
                PermissionChecker permissionChecker =
                    PermissionCheckerFactoryUtil.create(user, true);

                PermissionThreadLocal.setPermissionChecker(permissionChecker);
                UserServiceUtil.updatePortrait(user.getUserId(), bytes);
                user.setPortraitId(0L);
                UserLocalServiceUtil.updateUser(user);
                UserServiceUtil.updatePortrait(user.getUserId(), bytes);
                user = UserLocalServiceUtil.getUser(user.getUserId());
                //user.se
            }
        }
        
        init(user);
    }
    
    public void cancelChanges() throws PortalException, SystemException {
        init(user);
    }
    
    public String getFilteredAbout() {
        return filteredAbout;
    }
    
    public Long getUserId() {
        return user.getUserId();
    }
    
    public String getUploadedFileName() throws UnsupportedEncodingException {
        if (newUserProfile != null) {
            return URLEncoder.encode(newUserProfile.getName(), "UTF-8");
        }
        return null;
    }
    
    public File getUploadedFile() {
        return newUserProfile;
    }
    
    public void signalPictureUploaded(ActionEvent e) {
        // do nothing, this method will be called thanks to javascript and will
        // cause parent page to detect a file upload in an iframe
    }
    private void resizeAndCropImage(File file) throws IOException {
        int newW = 150;
        int newH = 150;
        
        BufferedImage img = ImageIO.read(file);
        // crop image
        
        int w = img.getWidth();  
        int h = img.getHeight();
        
        int cropSize = 0;
        int cropX = 0;
        int cropY = 0;
        
        
        if (h < w) {
            cropSize = h;
            cropX = (w - h) / 2;
        }
        else {
            cropSize = w;
            cropY = (h-w) / 2;
        }
        
        BufferedImage cropedImage = img.getSubimage(cropX, cropY, cropSize, cropSize);
        
        int imgType = BufferedImage.TYPE_3BYTE_BGR;
        if (img.getType() != BufferedImage.TYPE_CUSTOM) {
            imgType = img.getType();
        }
          
        BufferedImage dimg = new BufferedImage(newW, newH, imgType);  
        Graphics2D g = dimg.createGraphics();  
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
        g.drawImage(cropedImage, 0, 0, newW, newH, 0, 0, cropSize, cropSize, null);  
        g.dispose(); 
        
        String imageType = file.getName();
        imageType = imageType.substring(imageType.lastIndexOf(".") + 1);
        
        ImageIO.write(dimg, imageType, file);
        
    }

    public void setFileErrorMessage(String fileErrorMessage) {
        this.fileErrorMessage = fileErrorMessage;
    }

    public String getFileErrorMessage() {
        return fileErrorMessage;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPasswordRetype() {
        return newPasswordRetype;
    }

    public void setNewPasswordRetype(String newPasswordRetype) {
        this.newPasswordRetype = newPasswordRetype;
    }

    public User getWrapped() {
        return user;
    }
}
