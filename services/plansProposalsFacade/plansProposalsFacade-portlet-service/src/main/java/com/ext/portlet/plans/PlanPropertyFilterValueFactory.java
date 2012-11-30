package com.ext.portlet.plans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public interface PlanPropertyFilterValueFactory {
    Object getValue(String val) throws Exception;
    Class<?> getValueType();
    
    public class PlanPropertyFilterSearchStringValue implements PlanPropertyFilterValueFactory {
        @Override
        public Object getValue(String val) {
            StringBuilder searchString = new StringBuilder();
            searchString.append("%");
            searchString.append(val.replaceAll("\\ ", "%"));
            searchString.append("%");
            return searchString.toString();
        }

        @Override
        public Class<?> getValueType() {
            return String.class;
        }
    }
    
    public class PlanPropertyFilterStringValue implements PlanPropertyFilterValueFactory {
        @Override
        public Object getValue(String val) throws ParseException {
            return val;
        }
        @Override
        public Class<?> getValueType() {
            return String.class;
        }
    }

    
    public class PlanPropertyFilterDateValue implements PlanPropertyFilterValueFactory {
        DateFormat filterDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        DateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        @Override
        public Object getValue(String val) throws ParseException {
            return sqlDateFormat.format(filterDateFormat.parse(val));
        }
        @Override
        public Class<?> getValueType() {
            return String.class;
        }
    }
    public class PlanPropertyFilterDoubleValue implements PlanPropertyFilterValueFactory {
        
        @Override
        public Object getValue(String val) throws ParseException {
            return Double.parseDouble(val != null && !val.trim().equals("") ? val : "0" );
        }

        @Override
        public Class<?> getValueType() {
            return Double.class;
        }
    }
    
    

}
