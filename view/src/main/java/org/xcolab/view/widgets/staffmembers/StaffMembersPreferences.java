package org.xcolab.view.widgets.staffmembers;

import org.json.JSONObject;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.user.legacy.enums.StaffMemberCategoryRole;
import org.xcolab.commons.attributes.AttributeGetter;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.widgets.WidgetPreference;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class StaffMembersPreferences extends WidgetPreference implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String PORTLET_TITLE = "PORTLET_TITLE";
    private static final String COLUMN_AMOUNT = "COLUMN_AMOUNT";
    private static final String DISPLAY_PHOTO = "DISPLAY_PHOTO";
    private static final String DISPLAY_URL = "DISPLAY_URL";
    private static final String CATEGORY_ID = "CATEGORY_ID";


    private static final int defaultColumnAmount = 3;
    private static final boolean defaultDisplayPhoto = true;
    private static final boolean defaultDisplayUrl = true;
    private static final int defaultCategoryId = 1;
    private static final String defaultPortletTitle = "";

    private static final Map<Long, String> categories;

    static {
        categories = new HashMap<>();

        categories.put(StaffMemberCategoryRole.ADVISOR.getCategoryId(), "Advisors");
        categories.put(StaffMemberCategoryRole.JUDGE.getCategoryId(), "Judges");
        categories.put(StaffMemberCategoryRole.FELLOW.getCategoryId(), "Fellows");
        categories.put(StaffMemberCategoryRole.IMPACT_FELLOW.getCategoryId(), "Impact Fellows");
        categories.put(StaffMemberCategoryRole.CATALYST.getCategoryId(), "Catalyst");
        categories.put(StaffMemberCategoryRole.EXPERT_ADVISORY.getCategoryId(), "Expert Advisors");
        categories.put(StaffMemberCategoryRole.EXPERT_COUNCIL.getCategoryId(), "Expert Council");
        categories.put(StaffMemberCategoryRole.TEAM.getCategoryId(), "Project Staff: Team");
        categories.put(StaffMemberCategoryRole.VENDORS.getCategoryId(), "Project Staff: Vendors");
        categories.put(StaffMemberCategoryRole.ADVISORS_STAFF.getCategoryId(), "Project Staff: Advisors");
        categories.put(StaffMemberCategoryRole.ALUMNI.getCategoryId(), "Project Staff: Alumni");
        categories.put(StaffMemberCategoryRole.RESEARCH_COLAB.getCategoryId(), "Research Collaborator");
    }

    private String title;
    private int columnAmount;
    private boolean displayPhoto;
    private boolean displayUrl;
    private int categoryId;

    public StaffMembersPreferences() {
        this(null, I18nUtils.DEFAULT_LANGUAGE);
    }


    public StaffMembersPreferences(String preferenceId, String language) {
        super(preferenceId, language);

        columnAmount = jsonPreferences.optInt(COLUMN_AMOUNT, defaultColumnAmount);
        categoryId = jsonPreferences.optInt(CATEGORY_ID, defaultCategoryId);
        title = jsonPreferences.optString(PORTLET_TITLE, defaultPortletTitle);
        displayPhoto = jsonPreferences.optBoolean(DISPLAY_PHOTO, defaultDisplayPhoto);
        displayUrl = jsonPreferences.optBoolean(DISPLAY_URL, defaultDisplayUrl);
    }

    public static Map<Long, String> getCategories() {
        return categories;
    }

    @Override
    public AttributeGetter<String> getConfigurationAttribute() {
        return ConfigurationAttributeKey.PORTLET_STAFF_MEMBERS_PREFERENCES;
    }

    @Override
    public void savePreferences() {
        JSONObject prefs = new JSONObject();

        prefs.put(COLUMN_AMOUNT, String.valueOf(columnAmount));
        prefs.put(CATEGORY_ID, String.valueOf(categoryId));
        prefs.put(DISPLAY_PHOTO, String.valueOf(displayPhoto));
        prefs.put(DISPLAY_URL, String.valueOf(displayUrl));
        prefs.put(PORTLET_TITLE, String.valueOf(title));

        savePreferencesInternal(prefs, preferenceId);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getColumnAmount() {
        return columnAmount;
    }

    public void setColumnAmount(int columnAmount) {
        this.columnAmount = columnAmount;
    }

    public boolean isDisplayPhoto() {
        return displayPhoto;
    }

    public void setDisplayPhoto(boolean displayPhoto) {
        this.displayPhoto = displayPhoto;
    }

    public boolean isDisplayUrl() {
        return displayUrl;
    }

    public void setDisplayUrl(boolean displayUrl) {
        this.displayUrl = displayUrl;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
