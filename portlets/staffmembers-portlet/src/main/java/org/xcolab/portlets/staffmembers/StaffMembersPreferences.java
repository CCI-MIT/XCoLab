package org.xcolab.portlets.staffmembers;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class StaffMembersPreferences implements Serializable {

	private static final long serialVersionUID = 1L;

    private final static String PORTLET_TITLE = "PORTLET_TITLE";
    private final static String COLUMN_AMOUNT = "COLUMN_AMOUNT";
    private final static String DISPLAY_PHOTO = "DISPLAY_PHOTO";
    private final static String DISPLAY_URL = "DISPLAY_URL";
    private final static String CATEGORY_ID = "CATEGORY_ID";


    private final static int defaultColumnAmount = 4;
    private final static boolean defaultDisplayPhoto = true;
    private final static boolean defaultDisplayUrl = true;
    private final static int defaultCategoryId = 1;
    private final static String defaultPortletTitle = "";

    private static final Map<Integer, String> categories;
    static
    {
        categories = new HashMap<Integer, String>();
        categories.put(1, "Modeling Steering Committee: External Members");
        categories.put(2, "Modeling Steering Committee: Climate CoLab Staff");
        categories.put(3, "Expert Advisory Board");
        categories.put(4, "Expert Council");
        categories.put(5, "Project Staff: Team");
        categories.put(6, "Project Staff: Vendors");
        categories.put(7, "Project Staff: Advisors");
        categories.put(8, "Project Staff: Alumni");
        categories.put(9, "Advisors");
        categories.put(10, "Judges");
        categories.put(11, "Fellows 2014");
        categories.put(12, "Fellows 2012 & 2013");
        categories.put(13, "Catalysts");
        categories.put(14, "Fellows 2015");
        categories.put(15, "Impact Assessment Fellows 2015");

    }

    public static Map<Integer, String> getCategories() {
        return categories;
    }

    private String portletTitle;
    private int columnAmount;
    private boolean displayPhoto;
    private boolean displayUrl;
    private int categoryId;

    public StaffMembersPreferences() {
    	
    }

    public StaffMembersPreferences(PortletRequest request) {
        PortletPreferences prefs = request.getPreferences();

        columnAmount = defaultColumnAmount;
        try {
            columnAmount = Integer.parseInt(prefs.getValue(COLUMN_AMOUNT, String.valueOf(defaultColumnAmount)));
        }
        catch (NumberFormatException e) {
            // ignore
        }
        
        categoryId = defaultCategoryId;
        try {
            categoryId = Integer.parseInt(prefs.getValue(CATEGORY_ID, String.valueOf(defaultCategoryId)));
        }
        catch (Exception e) {
            // ignore
        }

        try {
            portletTitle = prefs.getValue(PORTLET_TITLE, defaultPortletTitle);
        }
        catch (Exception e) {
            // ignore
        }

        displayPhoto = Boolean.parseBoolean(prefs.getValue(DISPLAY_PHOTO,String.valueOf(defaultDisplayPhoto)));
        displayUrl = Boolean.parseBoolean(prefs.getValue(DISPLAY_URL, String.valueOf(defaultDisplayUrl)));
    }

    
    public String store(PortletRequest request) throws ReadOnlyException, ValidatorException, IOException {

        PortletPreferences prefs = request.getPreferences();
        prefs.setValue(COLUMN_AMOUNT, String.valueOf(columnAmount));
        prefs.setValue(CATEGORY_ID, String.valueOf(categoryId));
        prefs.setValue(DISPLAY_PHOTO, String.valueOf(displayPhoto));
        prefs.setValue(DISPLAY_URL,String.valueOf(displayUrl));
		prefs.setValue(PORTLET_TITLE, String.valueOf(portletTitle));

        prefs.store();
        
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
