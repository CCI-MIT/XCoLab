package org.climatecollaboratorium.plans.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class StringTruncateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        return truncateStr(arg1, arg2.toString());
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        return arg2 != null ? truncateStr(arg1, arg2.toString()) : "";
    }
    
    private String truncateStr(UIComponent comp, String str) {
        int maxLength = Integer.parseInt(comp.getAttributes().get("maxLength").toString());
        return str.substring(0, Math.min(str.length(), maxLength)) + (str.length() > maxLength ? " ..." : "") ;
    }

}
