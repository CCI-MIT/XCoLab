/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans;

import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.model.PlanItem;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public interface PlanValueFactory {
	
    public String getValue(PlanItem plan) throws SystemException, PortalException;
	
	public static class PojoGetter implements PlanValueFactory {
		
		private String format;
		private String field;
		
		public PojoGetter(String field, String format) {
			this.field = field;
			this.format = format;
		}
        @Override
        public String getValue(PlanItem plan) throws SystemException, PortalException {
            // THIS IS UGLY HACK
            return (new AttributeGetter(format, Attribute.valueOf(field.toUpperCase()))).getValue(plan);
        }
		
	}
	
	public static class AttributeGetter implements PlanValueFactory {
		
		Attribute[] atts;
		String format;
		
		public AttributeGetter(String format, Attribute... atts) {
			this.format = format;
			this.atts = atts;
		}
		

        @Override
        public String getValue(PlanItem plan) throws SystemException, PortalException {
            String[] params = new String[atts.length];
            int i = 0;
            for (Attribute att:atts) {
                params[i++]=att.format(plan);
            }
            return String.format(format,(Object[])params);
        }
		
	}

    public static class CustomizedAttributeGetter implements PlanValueFactory {

		Attribute[] atts;
		SelectingFormatFunction formatfunction;

		public CustomizedAttributeGetter(SelectingFormatFunction function, Attribute... atts) {
			this.formatfunction = function;
			this.atts = atts;
		}

        @Override
        public String getValue(PlanItem plan) throws SystemException, PortalException {
            String[] params = new String[atts.length];
            int i = 0;
            for (Attribute att:atts) {
                params[i++]=att.format(plan);
            }
           return formatfunction.format((Object[])params);
        }

	}


    public abstract static class SelectingFormatFunction {

        public String[] formats;

        public SelectingFormatFunction(String... formats) {
            this.formats = formats;
        }

        public String format(Object[] params) {
            int i = messageIndex(params);
            if (i>=formats.length) {
                return "N/A";
            } else return String.format(formats[i],params);
        }

        public abstract int messageIndex(Object[] params);

    }

	
	public static class MinMaxAttributeGetter implements PlanValueFactory {
        
        Attribute att1, att2;
        String format;
        
        public MinMaxAttributeGetter(String format, Attribute att1, Attribute att2) {
            this.format = format;
            this.att1 = att1;
            this.att2 = att2;
        }
        

        @Override
        public String getValue(PlanItem plan) throws SystemException, PortalException {
            String[] params = new String[2];
            Object val1 = att1.getValue(plan);
            Object val2 = att2.getValue(plan);
            if (val1 != null && val2 != null && val1.getClass() == Double.class && val2.getClass() == Double.class) {
                if ((Double) val1 > (Double) val2) {
                    Attribute tmp = att1;
                    att1 = att2;
                    att2 = tmp;
                }
            }
            params[0]=att1.format(plan);
            params[1]=att2.format(plan);
            return String.format(format,(Object[])params);
        }
    }
	
	public static class EmptyFactory implements PlanValueFactory {

        @Override
        public String getValue(PlanItem plan) throws SystemException, PortalException {
            // TODO Auto-generated method stub
            return "<empty>";
        }
		
	}
	
	
	
}
