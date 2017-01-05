package org.xcolab.view.taglibs.xcolab.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * <p>Formats given time span given in miliseconds to values like "3 days", "4 hours".
 * It can return either a number or an unit.</p>
 *  
 * @author janusz
 *
 */
public class FormatTimeSpanTag extends SimpleTagSupport {
    /**
     * Number of time span miliseconds.
     */
    private long value;
    
    /**
     * Set to true if tag should output unit in human readable format (minute, second, hours) 
     */
    private boolean returnUnit;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public boolean isReturnUnit() {
        return returnUnit;
    }

    public void setReturnUnit(boolean returnUnit) {
        this.returnUnit = returnUnit;
    }

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();
        
        TimeUnits unit = TimeUnits.SECOND;
        long retVal = 0;
        for (TimeUnits timeUnit: TimeUnits.values()) {
            retVal = Math.abs(value / timeUnit.getMiliseconds());
            if (retVal > 0) {
                unit = timeUnit;
                break;
            }
        }
        
        if (returnUnit) {
            getJspContext().getOut().append(unit.format(retVal));
        }
        else {
            getJspContext().getOut().append(String.valueOf(retVal));
        }
        
    }
    
    
    enum TimeUnits {
        DAY(1000 * 60 * 60 * 24, "day", "days"),
        HOUR(1000 * 60 * 60, "hour", "hours"),
        MINUTE(1000 * 60, "minute", "minutes"),
        SECOND(1000, "second", "seconds");
        
        private final long miliseconds;
        private final String nameSingular;
        private final String namePlural;
        
        
        private TimeUnits(long miliseconds, String nameSingular, String namePlural) {
            this.miliseconds = miliseconds;
            this.nameSingular = nameSingular;
            this.namePlural = namePlural;
        }

        public long getMiliseconds() {
            return miliseconds;
        }

        public String getNameSingular() {
            return nameSingular;
        }

        public String getNamePlural() {
            return namePlural;
        }
        
        public String format(long val) {
            if (val == 1) {
                return nameSingular;
            }
            return namePlural;
                
        }
    }
    
    
    

}
