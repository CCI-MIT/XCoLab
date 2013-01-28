package org.xcolab.portlets.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

public class ModelsPreferencesBean {

    private String screennames;
    private String layout;
    private String debatetopics="";




    private final static String SCREENNAMES_PREF = "SCREENNAMES";
    private final static String LAYOUT_TYPE_PREF = "LAYOUT_TYPE";
    private final static String RESTRICTED_CATEGORIES_PREF = "RESTRICTED_CATEGORIES";
    private final static String DEFAULT_SCREENNAMES = "jintrone";
    private final static String[] LAYOUTS = {"COMMENTS_BELOW_ITEM_DETAILS", "COMMENTS_BELOW_DEBATE_MAP"};
    
    
    
    
    public ModelsPreferencesBean() {
        screennames = Helper.getPortletPrefs().getValue(SCREENNAMES_PREF, DEFAULT_SCREENNAMES);
        layout = Helper.getPortletPrefs().getValue(LAYOUT_TYPE_PREF, LAYOUTS[0]);
        debatetopics = Helper.getPortletPrefs().getValue(RESTRICTED_CATEGORIES_PREF,"");
        validateTopics();
    }

    private void validateTopics()  {
        String[] topics = debatetopics.split(",");
        String ntopics = "";
        String sep = "";
        boolean fixprefs = false;

        for (String topic:topics) {
            try {
                Long tid = Long.parseLong(topic);
                ntopics+= (sep+topic);
                sep = ",";
            } catch (Exception e) {
                fixprefs = true;
            }

        }
        if (fixprefs) {
            debatetopics = ntopics;
            PortletPreferences prefs = Helper.getPortletPrefs();
            try {
                prefs.setValue(RESTRICTED_CATEGORIES_PREF, debatetopics);
            } catch(Exception e) {
                //fail
                e.printStackTrace();

            }
        }
    }

    
    
    public String getLayout() {
        return layout;
    }

    public List<SelectItem> getAvailableTopics() throws SystemException {
        List<SelectItem> result = new ArrayList<SelectItem>();
        return result;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public void setTopics(String[] topics) throws SystemException, PortalException {
        debatetopics = "";
        String sep = "";
        for (String topic:topics) {
            debatetopics+=(sep+topic);
            sep = ",";
        }

    }

    public String[] getTopics() {
      return debatetopics.split(",");
    }


    public String getScreennames() {
        return screennames;
    }

    public void setScreennames(String screennames) {
        this.screennames = screennames;
    }

    public List<SelectItem> getSupportedLayouts() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        for (String layout: LAYOUTS) {
            items.add(new SelectItem(layout, layout));
        }
        return items;
    }
  
    
    public String submit() throws ReadOnlyException, ValidatorException, IOException, PortalException, SystemException {
        ThemeDisplay td = Helper.getThemeDisplay();
        boolean isValid = true;
        FacesMessage fm = new FacesMessage();
        
        String[] screennameArr = screennames.split(",");
        for (String screenname: screennameArr) {
            screenname = screenname.trim();
            if (screenname.equals("")) {
                continue;
            }
            try {
                UserLocalServiceUtil.getUserByScreenName(td.getCompanyId(), screenname.trim());
            } catch (NoSuchUserException e) {
                fm.setSummary("Can't find user with screen name: " + screenname);
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                isValid = false;
            }
            
        }
        
        if (isValid) {
            PortletPreferences prefs = Helper.getPortletPrefs();
            prefs.setValue(SCREENNAMES_PREF, screennames);
            prefs.setValue(LAYOUT_TYPE_PREF, layout);
            prefs.setValue(RESTRICTED_CATEGORIES_PREF, debatetopics);
            
            prefs.store();
            
            
            fm.setSummary("Settings saved successfully");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
        }

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);
        
        return "";
    }
    
    public List<User> getSuggestionRecipients() throws PortalException, SystemException {
        PortletPreferences pref = Helper.getPortletPrefs();
        String val = pref.getValue(SCREENNAMES_PREF, DEFAULT_SCREENNAMES);
        String[] screennames = val.split(",");
        ThemeDisplay td = Helper.getThemeDisplay();
        
        List<User> ret = new ArrayList<User>();
        for (String screenname: screennames) {
            try {
                User user = UserLocalServiceUtil.getUserByScreenName(td.getCompanyId(), screenname.trim());
                ret.add(user);
            } catch (NoSuchUserException e) {
                // FIXME should be logged with logger
                e.printStackTrace();
            }
        }
        return ret;
        
    }

    public boolean getIsManagingTopics() {
            return debatetopics.length()>0;
    }

    
    
}
