package org.xcolab.view.pages.staffmemberswidget;

import org.json.JSONObject;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.members.legacy.enums.CategoryRole;
import org.xcolab.entity.utils.WidgetPreference;
import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.i18n.I18nUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class StaffMembersPreferences extends WidgetPreference implements Serializable {

    private static final long serialVersionUID = 1L;

    private final static String PORTLET_TITLE = "PORTLET_TITLE";
    private final static String COLUMN_AMOUNT = "COLUMN_AMOUNT";
    private final static String DISPLAY_PHOTO = "DISPLAY_PHOTO";
    private final static String DISPLAY_URL = "DISPLAY_URL";
    private final static String CATEGORY_ID = "CATEGORY_ID";


    private final static int defaultColumnAmount = 3;
    private final static boolean defaultDisplayPhoto = true;
    private final static boolean defaultDisplayUrl = true;
    private final static int defaultCategoryId = 1;
    private final static String defaultPortletTitle = "";

    private static final Map<Long, String> categories;

    static {
        categories = new HashMap<>();

        categories.put(CategoryRole.ADVISOR.getCategoryId(), "Advisors");
        categories.put(CategoryRole.JUDGE.getCategoryId(), "Judges");
        categories.put(CategoryRole.FELLOW.getCategoryId(), "Fellows");
        categories.put(CategoryRole.IMPACT_FELLOW.getCategoryId(), "Impact Fellows");
        categories.put(CategoryRole.CATALYST.getCategoryId(), "Catalyst");
        categories.put(CategoryRole.EXPERT_ADVISORY.getCategoryId(), "Expert Advisors");
        categories.put(CategoryRole.EXPERT_COUNCIL.getCategoryId(), "Expert Council");
        categories.put(CategoryRole.TEAM.getCategoryId(), "Project Staff: Team");
        categories.put(CategoryRole.VENDORS.getCategoryId(), "Project Staff: Vendors");
        categories.put(CategoryRole.ADVISORS_STAFF.getCategoryId(), "Project Staff: Advisors");
        categories.put(CategoryRole.ALUMNI.getCategoryId(), "Project Staff: Alumni");
        categories.put(CategoryRole.RESEARCH_COLAB.getCategoryId(), "Research Collaborator");
    }

    private String portletTitle;
    private int columnAmount;
    private boolean displayPhoto;
    private boolean displayUrl;
    private int categoryId;
    public StaffMembersPreferences() {
        this(null, I18nUtils.DEFAULT_LANGUAGE);
    }


    public StaffMembersPreferences(String preferenceId, String language) {
        super(preferenceId, language);


        columnAmount = defaultColumnAmount;
        try {
            columnAmount = Integer.parseInt(
                    (prefs.has(COLUMN_AMOUNT)) ? (prefs.getString(COLUMN_AMOUNT))
                            : (String.valueOf(defaultColumnAmount)));
        } catch (NumberFormatException e) {
            // ignore
        }

        categoryId = defaultCategoryId;
        try {
            categoryId = Integer.parseInt((prefs.has(CATEGORY_ID)) ? (prefs.getString(CATEGORY_ID))
                    : (String.valueOf(defaultCategoryId)));
        } catch (Exception e) {
            // ignore
        }

        try {
            portletTitle = (prefs.has(PORTLET_TITLE)) ? (prefs.getString(PORTLET_TITLE))
                    : (defaultPortletTitle);
        } catch (Exception e) {
            // ignore
        }

        displayPhoto = Boolean.parseBoolean(
                (prefs.has(DISPLAY_PHOTO)) ? (prefs.getString(DISPLAY_PHOTO))
                        : (String.valueOf(defaultDisplayPhoto)));
        displayUrl = Boolean.parseBoolean((prefs.has(DISPLAY_URL)) ? (prefs.getString(DISPLAY_URL))
                : (String.valueOf(defaultDisplayUrl)));
    }

    public static Map<Long, String> getCategories() {
        return categories;
    }

    @Override
    public AttributeGetter<String> getConfigurationAttribute() {
        return ConfigurationAttributeKey.PORTLET_STAFF_MEMBERS_PREFERENCES;
    }

    public String store() throws IOException {
        JSONObject prefs = new JSONObject();

        prefs.put(COLUMN_AMOUNT, String.valueOf(columnAmount));
        prefs.put(CATEGORY_ID, String.valueOf(categoryId));
        prefs.put(DISPLAY_PHOTO, String.valueOf(displayPhoto));
        prefs.put(DISPLAY_URL, String.valueOf(displayUrl));
        prefs.put(PORTLET_TITLE, String.valueOf(portletTitle));

        savePreferences(prefs, preferenceId);

        return null;
    }

    public String getPortletTitle() {
        return portletTitle;
    }

    public void setPortletTitle(String portletTitle) {
        this.portletTitle = portletTitle;
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
