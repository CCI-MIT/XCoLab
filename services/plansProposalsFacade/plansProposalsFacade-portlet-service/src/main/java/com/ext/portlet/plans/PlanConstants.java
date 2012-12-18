/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.plans.PlanFilterFactory.LessThanFilter;
import com.ext.portlet.plans.PlanFilterFactory.LikeFilter;
import com.ext.portlet.plans.PlanFilterFactory.MinMaxFilter;
import com.ext.portlet.plans.PlanFilterFactory.MoreThanFilter;
import com.ext.portlet.plans.PlanValueFactory.AttributeGetter;
import com.ext.portlet.plans.PlanValueFactory.EmptyFactory;
import com.ext.portlet.plans.PlanValueFactory.MinMaxAttributeGetter;
import com.ext.portlet.plans.PlanValueFactory.PojoGetter;
import com.ext.portlet.plans.attributes.AttributeFunction;
import com.ext.portlet.plans.attributes.AttributeFunctionFactory;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanAttributeFilter;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanPropertyFilter;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanTypeAttribute;
import com.ext.portlet.plans.model.PlanTypeColumn;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.service.PlanAttributeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Helper class with constant values that are used across Plans Portlet.
 *
 * version 1.1: added constants for integration plans and models
 * version 1.2: added constants required for new "View plans" page
 *
 * @author janusz.p, janusz.p
 *
 * @version 1.2
 * @since 1.0
 */
public class PlanConstants {
	
    private static AttributeFunctionFactory attributeFunctionFactory = new AttributeFunctionFactory();

    private static Log _log = LogFactoryUtil.getLog(PlanConstants.class);

    static {

    }

        public static enum Attribute {
		CO2(Double.class,"%.0f", attributeFunctionFactory.getLastValueFunction("AtmosphericCO2Concentration", Double.class), false, PlanFilterOperatorType.MIN_MAX, new MinMaxFilter() {

			@Override
			public String getMax(PlanAttributeFilter filter) {
                return String.valueOf(filter.getMax() != null ? filter.getMax() : Integer.MAX_VALUE);
			}
			@Override
			public String getMin(PlanAttributeFilter filter) {
				return String.valueOf(filter.getMin() != null ? filter.getMin() : Integer.MIN_VALUE);
			}

		}),
		TEMP(Double.class,"%.1f", attributeFunctionFactory.getLastValueFunction("GlobalTempChange", Double.class), true, null, null),
		MIN_MITIGATION_COST(Double.class,"%.1f%%", attributeFunctionFactory.getMinFromLastValuesFunction(new String[] {"_Change_in_GDP_vs__baseline_minicam_output", "_Change_in_GDP_vs__baseline_igsm_output", "_Change_in_GDP_vs__baseline_merge_output"}, Double.class),
		        true, PlanFilterOperatorType.GREATER_THAN_OR_NULL, new MoreThanFilter() {
			@Override
			public String getValue(PlanAttributeFilter filter) {
                return String.valueOf(filter.getStringVal() != null ? filter.getStringVal() : Double.MIN_VALUE);
			}

		}),


		MAX_MITIGATION_COST(Double.class,"%.1f%%", attributeFunctionFactory.getMaxFromLastValuesFunction(new String[] {"_Change_in_GDP_vs__baseline_minicam_output", "_Change_in_GDP_vs__baseline_igsm_output", "_Change_in_GDP_vs__baseline_merge_output"
                }, Double.class),
		        true, PlanFilterOperatorType.LESS_THAN_OR_NULL, new LessThanFilter() {
			public String getValue(PlanAttributeFilter filter) {
                return String.valueOf(filter.getStringVal() != null ? filter.getStringVal() : Double.MAX_VALUE);
			}
		}),
        MIN_MITIGATION_COST_EMF(Double.class,"%.1f%%", attributeFunctionFactory.getMinFromLastValuesFunction(new String[] {
                "Change_in_GDP_vs_baseline_witch_emf22_output1",
                "Change_in_GDP_vs_baseline_minicam_emf22_output2",
                "Change_in_GDP_vs_baseline_merge_emf22_output3",
                "Change_in_GDP_vs_baseline_fund_emf23_output4",
                "Change_in_GDP_vs_baseline_gcm_emf24_output5",
                "Change_in_GDP_vs_baseline_merge_optimistic_emf25_output6",
                "Change_in_GDP_vs_baseline_merge_pessimistic_emf26_output7"}, Double.class),
		        true, PlanFilterOperatorType.GREATER_THAN_OR_NULL, new MoreThanFilter() {
			@Override
			public String getValue(PlanAttributeFilter filter) {
                return String.valueOf(filter.getStringVal() != null ? filter.getStringVal() : Double.MIN_VALUE);
			}

		}),
		MAX_MITIGATION_COST_EMF(Double.class,"%.1f%%", attributeFunctionFactory.getMaxFromLastValuesFunction(new String[] {
                "Change_in_GDP_vs_baseline_witch_emf22_output1",
                "Change_in_GDP_vs_baseline_minicam_emf22_output2",
                "Change_in_GDP_vs_baseline_merge_emf22_output3",
                "Change_in_GDP_vs_baseline_fund_emf23_output4",
                "Change_in_GDP_vs_baseline_gcm_emf24_output5",
                "Change_in_GDP_vs_baseline_merge_optimistic_emf25_output6",
                "Change_in_GDP_vs_baseline_merge_pessimistic_emf26_output7"}, Double.class),
		        true, PlanFilterOperatorType.LESS_THAN_OR_NULL, new LessThanFilter() {
			public String getValue(PlanAttributeFilter filter) {
                return String.valueOf(filter.getStringVal() != null ? filter.getStringVal() : Double.MAX_VALUE);
			}
		}),
		EMISSIONS_DEVELOPED(Double.class,"%.2f%%", attributeFunctionFactory.getLastValueFunction("DevelopedFossilFuelEmissions", Double.class),true, null, null),
		EMISSIONS_DEVELOPING_A(Double.class,"%.2f%%", attributeFunctionFactory.getLastValueFunction("DevelopingAFossilFuelEmissions", Double.class), true, null, null),
		EMISSIONS_DEVELOPING_B(Double.class,"%.2f%%", attributeFunctionFactory.getLastValueFunction("DevelopingBFossilFuelEmissions", Double.class), true, null, null),
		SEA_LEVEL(Integer.class,"%d", attributeFunctionFactory.getLastValueFunction("Sea_Level_Rise_output", Double.class), true, null, null),
		MAX_DAMAGE_COST(Double.class,"%.1f%%", attributeFunctionFactory.getLastValueFunction("change_in_GDP_vs_baseline_page_95__output3", Double.class), true, PlanFilterOperatorType.LESS_THAN, new LessThanFilter() {
			public String getValue(PlanAttributeFilter filter) {
                return String.valueOf(filter.getStringVal() != null ? filter.getStringVal() : Double.MAX_VALUE);
			}
		}),
		
		MIN_DAMAGE_COST(Double.class,"%.1f%%", attributeFunctionFactory.getLastValueFunction("change_in_GDP_vs_baseline_page_5__output2", Double.class), true, PlanFilterOperatorType.GREATER_THAN, new MoreThanFilter() {
			public String getValue(PlanAttributeFilter filter) {
                return String.valueOf(filter.getStringVal() != null ? filter.getStringVal() : Double.MIN_VALUE);
			}
		}),
		
		MAX_DAMAGE_COST_2010(Double.class,"%.1f%%", attributeFunctionFactory.getLastValueFunction("PercentChange95_output", Double.class), true, PlanFilterOperatorType.LESS_THAN, new LessThanFilter() {
			public String getValue(PlanAttributeFilter filter) {
                return String.valueOf(filter.getStringVal() != null ? filter.getStringVal() : Double.MAX_VALUE);
			}
		}),
		
		MIN_DAMAGE_COST_2010(Double.class,"%.1f%%", attributeFunctionFactory.getLastValueFunction("PercentChange5_output", Double.class), true, PlanFilterOperatorType.GREATER_THAN, new MoreThanFilter() {
			public String getValue(PlanAttributeFilter filter) {
                return String.valueOf(filter.getStringVal() != null ? filter.getStringVal() : Double.MIN_VALUE);
			}
		}),
		
		NAME(String.class, "%s", attributeFunctionFactory.getPlanPropertyFunction("name"), true, PlanFilterOperatorType.LIKE, new LikeFilter() {
            public String getValue(PlanAttributeFilter filter) {
                return filter.getStringVal();
            }
		    
		}),
        VOTES(Double.class, "%.0f", attributeFunctionFactory.getPlanPropertyFunction("votes"), true, PlanFilterOperatorType.MIN_MAX,  null),
        CREATE_DATE(Date.class, "%1$tm/%1$te/%1$ty", attributeFunctionFactory.getPlanPropertyFunction("createDate"), true, PlanFilterOperatorType.DATE_FROM_TO, null), 
        PUBLISH_DATE(Date.class, "%1$tm/%1$te/%1$ty", attributeFunctionFactory.getPlanPropertyFunction("publishDate"), true, PlanFilterOperatorType.DATE_FROM_TO, null),
        CREATOR(String.class, "%s", attributeFunctionFactory.getPlanPropertyFunction("creator"), true, PlanFilterOperatorType.LIKE, null),
	    MITIGATION_COST_ERROR(String.class, "%s", attributeFunctionFactory.getIndexedOutputErrors("Mitigation Cost"), true, null, null), 
	    DESCRIPTION(String.class, "%s", attributeFunctionFactory.getPlanPropertyFunction("description"), true, PlanFilterOperatorType.LIKE, null),
        POSITIONS(Long[].class, "%s", attributeFunctionFactory.getPlanPropertyFunction("positionsIdsArray"), true, PlanFilterOperatorType.POSITIONS_FILTER, null),
        STATUS(String.class, "%s", attributeFunctionFactory.getPlanPropertyFunction("status"), true, PlanFilterOperatorType.LIKE, null),
        SEEKING_ASSISTANCE(String.class, "%s", attributeFunctionFactory.getAttributeValue("SEEKING_ASSISTANCE"), true, null, null),
        SUPPORTERS(Integer.class, "%d", attributeFunctionFactory.getAttributeValue("SUPPORTERS"), true, null, null),
        IS_PLAN_OPEN(String.class, "%s", attributeFunctionFactory.getPlanPropertyFunction("open"), true, null, null),
        COMMENTS(Integer.class, "%d", attributeFunctionFactory.getPlanPropertyFunction("commentsCount"), true, null, null),
        PLAN_PLACE(Integer.class, "%d", attributeFunctionFactory.getAttributeValue("PLAN_PLACE"), true, null, null),
        PLAN_RIBBON(Integer.class, "%d", attributeFunctionFactory.getAttributeValue("PLAN_RIBBON"), true, null, null),
        PLAN_RIBBON_TEXT(String.class, "%s", attributeFunctionFactory.getAttributeValue("PLAN_RIBBON_TEXT"), true, null, null),
		REGION(String.class, "%s", attributeFunctionFactory.getAttributeValue("REGION"), true, null, null),
		SUBREGION(String.class, "%s", attributeFunctionFactory.getAttributeValue("SUBREGION"), true, null, null),
        ABSTRACT(String.class, "%s", attributeFunctionFactory.getAttributeValue("ABSTRACT"), true, null, null),
        SCRAPBOOK(String.class, "%s", attributeFunctionFactory.getAttributeValue("SCRAPBOOK"), true, null, null),
        LAST_MOD_DATE(Date.class, "%1$tm/%1$te/%1$ty", attributeFunctionFactory.getPlanPropertyFunction("Updated"), true, PlanFilterOperatorType.DATE_FROM_TO, null),
        SCRAPBOOK_HOVER(String.class, "%s", attributeFunctionFactory.getAttributeValue("SCRAPBOOK_HOVER"), true, null, null),
        IMAGE(Long.class, "%s", attributeFunctionFactory.getPlanPropertyFunction("image"), true, PlanFilterOperatorType.DUMMY, null),
        TEAM(String.class, "%s", attributeFunctionFactory.getAttributeValue("TEAM"), true, null, null),
        TAGS(String.class, "%s", attributeFunctionFactory.getAttributeValue("TAGS"), true, null, null),
        TAGS_ORDER(Integer.class, "%s", attributeFunctionFactory.getAttributeValue("TAGS_ORDER"), true, null, null),
        TAGS_HOVER(String.class, "%s", attributeFunctionFactory.getAttributeValue("TAGS_HOVER"), true, null, null);
		
		private Class<?> clasz;
		private String format;
		private PlanFilterFactory factory;
		private boolean filterSingleValue;
		private String variableId;
		private AttributeFunction<?> attributeFunction;
        private PlanFilterOperatorType planFilterOperatorType;
		
		Attribute(Class<?> c, String format, AttributeFunction<?> attributeFunction,
		        boolean filterSingleValue, PlanFilterOperatorType operatorType, PlanFilterFactory factory) {
			this.clasz = c;
			this.format = format;
			this.factory = factory;
			this.filterSingleValue = filterSingleValue;
			this.attributeFunction = attributeFunction;
			this.planFilterOperatorType = operatorType;
		}
		
		public boolean isFiltered() {
			return factory!=null;
		}
		/*
		public Object getValue(Plan plan) throws SystemException {
			PlanAttribute attribute =  PlanAttributeLocalServiceUtil.findPlanAttribute(plan.getPlanId(), this.name()); 
			String s =(attribute==null || attribute.getAttributeValue() == null) ? "" : attribute.getAttributeValue();
			if (clasz == Double.class) {
	            if (s == null || s.trim().length() ==0) return null;
				return Double.parseDouble(s);
			} else if (clasz == Integer.class) {
	            if (s == null || s.trim().length() ==0) return null;
				return new Double(Double.parseDouble(s)).intValue();
			}
			else if (clasz == Date.class) {
			    return TypedValueConverter.getValue(Date.class, s);
			} else if (clasz == String.class) {
                return s;
            }
			return null;
		}*/
		
	      public Object getValue(PlanItem plan) throws SystemException {
	            PlanAttribute attribute =  PlanAttributeLocalServiceUtil.findPlanAttribute(plan.getPlanId(), this.name()); 
	            String s =(attribute==null || attribute.getAttributeValue() == null) ? "" : attribute.getAttributeValue();
	            if (clasz == Double.class) {
	                if (s == null || s.trim().length() ==0) return null;
	                return Double.parseDouble(s);
	            } else if (clasz == Integer.class) {
	                if (s == null || s.trim().length() ==0) s ="0";
	                return new Double(Double.parseDouble(s)).intValue();
	            } else if (clasz == Date.class) {
	                    return TypedValueConverter.getValue(Date.class, s);
	            } else if (clasz == String.class) {
	                return s;
	            }
	            else if (clasz.isArray()) {
	                return TypedValueConverter.getValues(clasz.getComponentType(), s);
	            }
	            return null;
	        }
			
	      public String format(PlanItem plan) throws SystemException {        
	          if (getValue(plan) == null) {
	              return "N/A";
	          }
	          return String.format(format, getValue(plan));
	        }
		
		public StringBuilder getFilterString(StringBuilder builder, PlansUserSettings planUserSettings) 
		throws NoSuchPlanAttributeFilterException, SystemException {
			if (isFiltered()) {
			    PlanAttributeFilter attributeFilter = planUserSettings.getAttributeFilter(this.toString());
			    if (attributeFilter == null) {
			        //attributeFilter = PlanAttributeFilterLocalServiceUtil.createPlanAttributeFilter(null);
			    }
				return factory.getFilterString(this.name(), attributeFilter, builder);
			} else {
				return null;
			}
		}

        public PlanAttributeFilter getAttributeFilter(PlansUserSettings planUserSettings) throws NoSuchPlanAttributeFilterException, SystemException {
            PlanAttributeFilter attributeFilter = planUserSettings.getAttributeFilter(this.toString());
            if (attributeFilter == null) {
                // attributeFilter = PlanAttributeFilterLocalServiceUtil.createPlanAttributeFilter(null);
            }
            return attributeFilter;
        }
        
        public boolean isFilterDefined(PlansUserSettings planUserSettings) throws NoSuchPlanAttributeFilterException, SystemException {
            return planUserSettings.getAttributeFilter(this.toString()) != null;
        }
        
        public boolean isFilterSingleValue() {
            return filterSingleValue;
        }
		
		public static int filteredCount() {
			int count = 0;
			for (Attribute att:values()) {
				if (att.isFiltered()) count++;
			}
			return count;
		}
		
		public Object calculateValue(String scenarioId) throws SystemException {
		    Object val = attributeFunction.process(scenarioId);
		    return val != null ? val.toString() : "";
		}
		
		public Object calculateValue(PlanItem plan) throws SystemException {
	        Object val = attributeFunction.process(plan);
	        if (clasz.isArray()) {
	            return TypedValueConverter.getStringForMultipleValues(val);
	        }
	        
	        return TypedValueConverter.getString(val);
	    }
		

        public Object calculateTypedValue(PlanItem plan) throws SystemException {
            Object val = attributeFunction.process(plan);
            
            return val != null ? TypedValueConverter.getValue(clasz, String.valueOf(val)) : null;   
        }
	    
	    
		
		public static List<Attribute> getPlanTypeAttributes(PlanType planType) throws SystemException {
            List<PlanTypeAttribute> planTypeAttributes = PlanTypeLocalServiceUtil.getAttributes(planType);
            
            List<Attribute> attributes = new ArrayList<Attribute>();
            for (PlanTypeAttribute planTypeAttribute: planTypeAttributes) {
                attributes.add(Attribute.valueOf(planTypeAttribute.getAttributeName()));
            }
            
            return attributes;
        }
		
		public Class<?> getAttributeClass() {
		    return clasz;
		}
		
		public PlanFilterOperatorType getOperatorType() {
		    return planFilterOperatorType;
		}
		
		public boolean isInFilteredSet(PlansUserSettings userSettings, PlanItem plan) throws NoSuchPlanAttributeFilterException, SystemException {
		    _log.debug("Checking attribute "+this.name()+"in plan "+PlanItemLocalServiceUtil.getName(plan));
            if (planFilterOperatorType == null) {
		        return true;
		    }
		    try {
		        PlanAttributeFilter planAttributeFilter = userSettings.getAttributeFilter(this.name());
		        PlanAttribute planAttribute = PlanItemLocalServiceUtil.getPlanAttribute(plan, this.name());
		        if (planAttributeFilter == null) {
		            return true;
		        }
		        else if (planAttribute == null) {
		            return false;
		        }
		        return planFilterOperatorType.isInFilteredSet(userSettings, planAttributeFilter, planAttribute);
		    } 
		    catch (NoSuchPlanAttributeFilterException e) {
		        // ignore
		        return true;
		    }
		    
		    
		}
	}
	
	
	
	
	public static enum Columns {
		
		NAME("Name","Name of the plan","ShowName",true, Attribute.NAME, new PojoGetter("Name","%s")),
		VOTES("Votes","% of votes for this plan","ShowVotes",true, Attribute.VOTES,new PlanValueFactory() {


            @Override
            public String getValue(PlanItem plan) throws SystemException, PortalException {
                int votesCount = PlanVoteLocalServiceUtil.countPlanVotes(PlanItemLocalServiceUtil.getContest(plan));
                int planVotes = PlanItemLocalServiceUtil.getVotes(plan);
                
                return String.format("%d %%", votesCount == 0 ? 0 : Math.round(((double) 
                        PlanItemLocalServiceUtil.getVotes(plan) * 100) / votesCount));
            }
			
		}),
		CREATOR("Creator","Creator of this plan","ShowCreator",false, Attribute.CREATOR,new PlanValueFactory() {

			@Override
			public String getValue(PlanItem plan) throws SystemException, PortalException {
			    PlanValueFactory pvf = new AttributeGetter("%s",Attribute.CREATOR);
			    return pvf.getValue(plan);
			}


		}),
		CREATE_DATE("Date created","Date this plan was created","ShowDate",false, Attribute.CREATE_DATE, new PlanValueFactory() {


            @Override
            public String getValue(PlanItem plan) throws SystemException, PortalException {
                PlanValueFactory pvf = new AttributeGetter("%s",Attribute.CREATE_DATE);
                return pvf.getValue(plan);
            }
			
		}),
		PUBLISH_DATE("Date published","Date this plan was published","Published",false, Attribute.PUBLISH_DATE,new PlanValueFactory() {
	

            @Override
            public String getValue(PlanItem plan) throws SystemException, PortalException {
                PlanValueFactory pvf = new AttributeGetter("%s",Attribute.PUBLISH_DATE);
                return pvf.getValue(plan);
            }
		}),
		UPDATE_DATE("Date updated","Date this plan was updated","Updated",false, Attribute.LAST_MOD_DATE,new PlanValueFactory() {
       

            @Override
            public String getValue(PlanItem plan) throws SystemException, PortalException {
                PlanValueFactory pvf = new AttributeGetter("%s",Attribute.LAST_MOD_DATE);
                return pvf.getValue(plan);
            }
        }),
		POSITIONS("Positions","Positions on key issues that are emboded by this plan","ShowPositions",false, null, new EmptyFactory()),		
		
		COLUMN_DEVELOPED_EMISSIONS("Emissions change for developed countries<br/>(10^9 tons Carbon in 2050))","% change in emissions from 2005 to [2050?] in " +
				"the developed countries.	Developed countries includes many of the most developed nations: US, EU (27 countries), " +
				"Norway and Sweden, Russia and the former Soviet States, Japan, Canada, South Korea, New Zealand, and Australia",
				"ShowDevelopedEmissions",false, Attribute.EMISSIONS_DEVELOPED, new AttributeGetter("%s",Attribute.EMISSIONS_DEVELOPED)),
				
		COLUMN_DEVELOPING_A_EMISSIONS("Emissions change for rapidly developing countries<br/>(10^9 tons Carbon in 2050)"," % change in emissions from 2005 to [2050?] in " +
				"the rapidly developing countries.Rapidly developing countries include many of the fastest developing and larger nations: China, " +
				"India, South Africa, Mexico, Brazil, Indonesia, and other large developing Asian countries.","ShowDevelopingAEmissions",
				false, Attribute.EMISSIONS_DEVELOPING_A, new AttributeGetter("%s",Attribute.EMISSIONS_DEVELOPING_A)),
				
		COLUMN_DEVELOPING_B_EMISSIONS("Emissions change for other developing countries<br/>(10^9 tons Carbon in 2050)","%  change in emissions from 2005 to [2050?] in the " +
				"rapidly developing countries. Other developing countries includes smaller developing nations in the Middle East, Latin America, Africa, " +
				"and Asia.","ShowDevelopingBEmissions",false, Attribute.EMISSIONS_DEVELOPING_B, new AttributeGetter("%s",Attribute.EMISSIONS_DEVELOPING_B)),
		
		CO2_CONCENTRATION("CO2","Atmospheric CO2 concentration in parts per million (ppm) in 2100","ShowCO2", true, Attribute.CO2, new AttributeGetter("%s",Attribute.CO2)),
				
		TEMP_CHANGE("Temperature Change<br/>(&#176;C in 2100)","Global average temperature change in degrees Celsius (C) from pre-industrial " +
				"values to 2100","ShowTemperatureChange",true, Attribute.TEMP ,new AttributeGetter("%s",Attribute.TEMP)),
				
		SEA_LEVEL_CHANGE("Sea level change<br/>(mm in 2100)","Sea level change in millimeters (mm) from 2000 to 2100","ShowSeaLevelRise",false, Attribute.SEA_LEVEL, new AttributeGetter("%s",Attribute.SEA_LEVEL)),
		
		MITIGATION_COST("Mitigation cost<br/>(%GDP in 2100)","Cost of efforts to prevent climate change (e.g., by reducing emissions). " +
				"Costs are shown as a % of World GDP (Gross Domestic Product).	Values shown are the lowest and highest of the estimates " +
				"produced by three models of these costs.","ShowMitigationCost",true, Attribute.MIN_MITIGATION_COST,
                new PlanValueFactory.CustomizedAttributeGetter(
                        new PlanValueFactory.SelectingFormatFunction(
                                "%s to %s <div class='errors popup-info-box' style='display: none;" +
                                        " position: absolute; width: 150px;'>%s</div>",
                                "N/A <div class='errors popup-info-box' style='display: none;" +
                                        " position: absolute; width: 150px;'>%3$s</div>") {
                            @Override
                            public int messageIndex(Object[] params) {
                                return "N/A".equals(params[0]) && "N/A".equals(params[1])?1:0;
                            }
                        },Attribute.MIN_MITIGATION_COST, Attribute.MAX_MITIGATION_COST, Attribute.MITIGATION_COST_ERROR)),
        MITIGATION_COST_EMF("Mitigation cost<br/>(%GDP in 2100)","Cost of efforts to prevent climate change (e.g., by reducing emissions). " +
				"Costs are shown as a % of World GDP (Gross Domestic Product).	Values shown are the lowest and highest of the estimates " +
				"produced by three models of these costs.","ShowMitigationCost",true, Attribute.MIN_MITIGATION_COST_EMF,
                new PlanValueFactory.CustomizedAttributeGetter(
                        new PlanValueFactory.SelectingFormatFunction(
                                "%s to %s <div class='errors popup-info-box' style='display: none;" +
                                        " position: absolute; width: 150px;'>%s</div>",
                                "N/A <div class='errors popup-info-box' style='display: none;" +
                                        " position: absolute; width: 150px;'>%3$s</div>") {
                            @Override
                            public int messageIndex(Object[] params) {
                                return "N/A".equals(params[0]) && "N/A".equals(params[1])?1:0;
                            }
                        },Attribute.MIN_MITIGATION_COST_EMF, Attribute.MAX_MITIGATION_COST_EMF, Attribute.MITIGATION_COST_ERROR)),

         MITIGATION_COST_NO_ERRORS("Mitigation cost<br/>(%GDP in 2100)","Cost of efforts to prevent climate change (e.g., by reducing emissions). " +
                                "Costs are shown as a % of World GDP (Gross Domestic Product).  Values shown are the lowest and highest of the estimates " +
                                "produced by three models of these costs.","ShowMitigationCost",true, Attribute.MIN_MITIGATION_COST_EMF,
                                new PlanValueFactory.CustomizedAttributeGetter(
                                        new PlanValueFactory.SelectingFormatFunction(
                                                "%s to %s",
                                                "N/A") {
                                            @Override
                                            public int messageIndex(Object[] params) {
                                                return "N/A".equals(params[0]) && "N/A".equals(params[1])?1:0;
                                            }
                                        },Attribute.MIN_MITIGATION_COST_EMF, Attribute.MAX_MITIGATION_COST_EMF, Attribute.MITIGATION_COST_ERROR)),                        

				
		DAMAGE_COST("Damage cost<br/>(%GDP in 2100)","Cost of damages caused by climate change (e.g., damages from rising sea level, hurricanes, " +
				"droughts, etc.). Costs are shown as a % of World GDP (Gross Domestic Product). Values shown are estimates of the " +
				"90% confidence interval for these costs. That is, the models predict there is only a 5% chance that the costs would be l" +
				"ess than the lower number and a 5% chance that the costs would be greater than the higher number.","ShowDamageCost",true, 
				Attribute.MIN_DAMAGE_COST, new MinMaxAttributeGetter("%s to %s",Attribute.MIN_DAMAGE_COST,Attribute.MAX_DAMAGE_COST)),
		
		DAMAGE_COST_2010("Damage cost<br/>(%GDP in 2100)","Cost of damages caused by climate change (e.g., damages from rising sea level, hurricanes, " +
				"droughts, etc.). Costs are shown as a % of World GDP (Gross Domestic Product). Values shown are estimates of the " +
				"90% confidence interval for these costs. That is, the models predict there is only a 5% chance that the costs would be l" +
				"ess than the lower number and a 5% chance that the costs would be greater than the higher number.","ShowDamageCost",true, 
				Attribute.MIN_DAMAGE_COST_2010, new MinMaxAttributeGetter("%s to %s",Attribute.MIN_DAMAGE_COST_2010,Attribute.MAX_DAMAGE_COST_2010)),
		
		
        SEEKING_ASSISTANCE("Seeking<br />help","Does this plan team seeks for new members?","ShowSeekingAssistance",true, Attribute.SEEKING_ASSISTANCE, new AttributeGetter("%s",Attribute.SEEKING_ASSISTANCE)),

        SUPPORTERS("Supporters","Number of users supporting this plan","ShowSupporters",true, Attribute.SUPPORTERS, new AttributeGetter("%s",Attribute.SUPPORTERS)),
		
		IS_PLAN_OPEN("Who can<br /> edit", "Who can edit the plan", "ShowWhoCanEdit", true, Attribute.IS_PLAN_OPEN, new AttributeGetter("%s", Attribute.IS_PLAN_OPEN)),
		
		COMMENTS("Comments", "Number of comments", "ShowComments", true, Attribute.COMMENTS, new PlanValueFactory() {


            @Override
            public String getValue(PlanItem plan) throws SystemException, PortalException {
                return String.format("%d", DiscussionCategoryGroupLocalServiceUtil.getCommentsCount(PlanItemLocalServiceUtil.getDiscussionCategoryGroup(plan)));
            }
            
        }),
        REGION("Transnational group/<br />large country","Transnational group/large country described by proposal","ShowSupporters",true, Attribute.REGION, new AttributeGetter("%s",Attribute.REGION)),
        SUBREGION("Country within<br /> transnational group","Country within transnational group described by proposal","ShowSupporters",true, Attribute.SUBREGION, new AttributeGetter("%s",Attribute.SUBREGION)),
        ABSTRACT("Pitch","The pitch is a tweet-length (140 character) description of your proposal","ShowAbstract",true, Attribute.ABSTRACT, new AttributeGetter("%s",Attribute.ABSTRACT));
		
        
		private String name;
		private String description;
		
		private  boolean defaultDisplay;
		private static boolean initialized = false;
		private String setter;
		private String getter;
		private PlanConstants.Attribute sortAttribute;
		
		private PlanValueFactory value;
		
		private static List<Columns> defaults = new ArrayList<Columns> ();
		
		Columns(String name, String description, String methodroot, boolean defaultDisplay, PlanConstants.Attribute sortAttribute, PlanValueFactory factory) {
			this.name = name;
			this.description =description;
			this.getter = "get"+methodroot;
			this.setter = "set"+methodroot;
            this.defaultDisplay = defaultDisplay;
			this.value = factory;
			this.sortAttribute = sortAttribute;
		}
		
		
		public String getName() {
			return name;
		}
		
		public String getDescription() {
			return description;
		}
		
		public boolean isDefault() {
			return defaultDisplay;
		}
		
		
		public String getValue(PlanItem plan) throws SystemException, PortalException {
            return value.getValue(plan);
        }
		
		public static List<Columns> defaults() {
			if (!initialized) {
				
			synchronized(Columns.class) {
				for (Columns c:Columns.values()) {
					if (c.isDefault()) defaults.add(c);
				}
			}
			}
			return Collections.unmodifiableList(defaults);
		}
		
		public static List<Columns> getPlanTypeColumns(PlanType planType) throws SystemException {
		    List<PlanTypeColumn> planTypeColumns = PlanTypeLocalServiceUtil.getColumns(planType);
		    
		    List<Columns> columns = new ArrayList<Columns>();
		    for (PlanTypeColumn planTypeColumn: planTypeColumns) {
		        columns.add(Columns.valueOf(planTypeColumn.getColumnName()));
		    }
		    
		    return columns;
		}
		
		public boolean isSortable() {
		    return sortAttribute != null;
		}
		
		public Attribute getSortAttribute() {
		    return sortAttribute;
		}
		
		
	
	}
	
	
	public static enum Property {
	    NAME(new PlanPropertyFilterValueFactory.PlanPropertyFilterSearchStringValue()),
	    DESCRIPTION(new PlanPropertyFilterValueFactory.PlanPropertyFilterSearchStringValue()),
	    CREATOR(new PlanPropertyFilterValueFactory.PlanPropertyFilterSearchStringValue()),
	    VOTES_FROM(new PlanPropertyFilterValueFactory.PlanPropertyFilterDoubleValue(), true, false),
	    VOTES_TO(new PlanPropertyFilterValueFactory.PlanPropertyFilterDoubleValue(), true, false),
        DATE_FROM(new PlanPropertyFilterValueFactory.PlanPropertyFilterDateValue()),
        DATE_TO(new PlanPropertyFilterValueFactory.PlanPropertyFilterDateValue());
        
	    
	    
	    private PlanPropertyFilterValueFactory filterValueFactory;
	    private boolean forPublished = true;
        private boolean forUnpublished = true;
	    
	    private Property(PlanPropertyFilterValueFactory filterValueFactory) {
	        this.filterValueFactory = filterValueFactory;
	    }
	    
	    private Property(PlanPropertyFilterValueFactory filterValueFactory, boolean forPublished, boolean forUnpublished) {
            this.filterValueFactory = filterValueFactory;
            this.forPublished = forPublished;
            this.forUnpublished = forUnpublished;
        }
	    
	    
	    public Object getPropertyFilterValue(PlansUserSettings planUserSettings) throws Exception {
            // get plan property filter for passed plan user settings and return it's value
	        PlanPropertyFilter filter =  planUserSettings.getPropertyFilter(this.toString());
	        return filterValueFactory.getValue(filter != null ? filter.getValue() : "");
	    }
	    
	    public Object getValueType() {
	        return filterValueFactory.getValueType();
	    }
	    
	    public Object getValue(PlansUserSettings planUserSettings) throws SystemException, NoSuchPlanPropertyFilterException {
            PlanPropertyFilter filter = planUserSettings.getPropertyFilter(this.toString());
            return filter != null ? filter.getValue() : null;
	    }
	    
	    public boolean isForPublished(boolean published) {
	        return published ? forPublished : forUnpublished;
	    }

        public boolean isFilterDefined(PlansUserSettings planUserSettings) throws NoSuchPlanPropertyFilterException, SystemException {
            return planUserSettings.getPropertyFilter(this.toString()) != null;
        }
        
	}
	
    /**
     * Name of actions tab in view plan tab panel.
     */
    public static final String ACTIONS_TAB = "actions";

    /**
     * Add plan action for resource permissions management.
     */
    public static final String ADD_PLAN = "ADD_PLAN";

    /**
     * Value for Positions filter operator denoting that all positions should be
     * referenced by a plan.
     */
    public static final String ALL = "all";

    /**
     * Value for Positions filter operator denoting that any position should be
     * referenced by a plan.
     */
    public static final String ANY = "any";

    /**
     * Name of request parameter with CO2.
     */
    public static final String CO2 = "AtmosphericCO2Concentration";

    /**
     * Name of request parameter with CO2 max.
     */
    public static final String CO2_MAX = "CO2max";

    /**
     * Name of request parameter with CO2 min.
     */
    public static final String CO2_MIN = "CO2min";

    /**
     * Path to jsp file containing portlet configuration.
     */
    public static final String CONFIGURATION_JSP = "/html/portlet/ext/plans/configuration.jsp";

    /**
     * Forward path for configuring columns.
     */
    public static final String CONFIGURE_COLUMNS_FORWARD = "portlet.ext.plans.configure_columns";

    /**
     * Creator request parameter.
     */
    public static final String CREATOR = "creator";

    /**
     * Name of request parameter with damage cost average.
     */
    public static final String DAMAGE_COST_AVG = "damageCostAvg";

    /**
     * Name of request parameter with damage cost max.
     */
    public static final String DAMAGE_COST_MAX = "damageCostMax";

    /**
     * Name of request parameter with damage cost min.
     */
    public static final String DAMAGE_COST_MIN = "damageCostMin";

    /**
     * Name of request parameter with damage cost standard deviation.
     */
    public static final String DAMAGE_COST_STD_DEV = "damageCostStdDev";

    /**
     * Date request parameter.
     */
    public static final String DATE = "date";

    /**
     * Date max request parameter.
     */
    public static final String DATE_MAX = "dateMax";

    /**
     * Date min request parameter.
     */
    public static final String DATE_MIN = "dateMin";

    /**
     * A placeholder string that is used in pattern URLs. It is used to denote at which place debate
     * category id should be inserted.
     */
    public static final String DEBATE_CATEGORY_ID = "DEBATE_CATEGORY_ID";

    /**
     * Name of default model id portlet preference parameter.
     */
    public static final String DEFAULT_MODEL_ID = "defaultModelId";

    /**
     * Name of default topic request parameter.
     */
    public static final String DEFAULT_TOPIC = "defaultTopicId";

    /**
     * Name of default topic id request parameter.
     */
    public static final String DEFAULT_TOPIC_ID = "defaultTopicId";

    /**
     * Name of description tab in view plan tab panel.
     */
    public static final String DESCRIPTION = "description";

    /**
     * Name of description tab in view plan tab panel.
     */
    public static final String DESCRIPTION_TAB = "description";

    /**
     * Name of request parameter with developed emissions.
     */
    public static final String DEVELOPED_EMISSIONS = "DevelopedFossilFuelEmissions";

    /**
     * Name of request parameter with developing A emissions.
     */
    public static final String DEVELOPING_A_EMISSIONS = "DevelopingAFossilFuelEmissions";

    /**
     * Name of request parameter with developing B emissions.
     */
    public static final String DEVELOPING_B_EMISSIONS = "DevelopingBFossilFuelEmissions";

    /**
     * Name of discussion tab in view plan tab panel.
     */
    public static final String DISCUSSION_TAB = "discussion";

    /**
     * Forward path for editing plan.
     */
    public static final String EDIT_PLAN_FORWARD = "portlet.ext.plans.edit_plan";
    /**
     * Enable filters request parameter.
     */
    public static final String ENABLE_FILTERS = "enableFilters";

    /**
     * Name of session parameter with filter for published plans.
     */
    public static final String FILTER_PUBLISHED = "filterPublished";

    /**
     * Name of session parameter with filter for unpublished plans.
     */
    public static final String FILTER_UNPUBLISHED = "filterUnpublished";

    /**
     * Global emissions request parameter.
     */
    public static final String GLOBAL_EMISSIONS = "globalEmissions";

    /**
     * A placeholder string that is used in pattern URLs. It is used to denote at which place group
     * name should be inserted (group friendly url).
     */
    public static final String GROUP_NAME_PLACEHOLDER = "GROUP_NAME_PLACEHOLDER";

    /**
     * Name of request paremeter which denotes if user vote should be highlighted.
     */
    public static final String HIGHLIGHT_VOTE = "highlightVote";

    /**
     * A placeholder string that is used in pattern URLs. It is used to denote at which place certain
     * id should be inserted (for example planId, group id).
     */
    public static final String ID_PLACEHOLDER = "ID_PLACEHOLDER";

    /**
     * Name of impacts tab in view plan tab panel.
     */
    public static final String IMPACTS_TAB = "impacts";

    /**
     * Requests parameter key that represents user plan membership status.
     */
    public static final String IS_PLAN_MEMBER = "isPlanMember";

    /**
     * Requests parameter key that represents user plan ownership status.
     */
    public static final String IS_PLAN_OWNER = "isPlanOwner";

    /**
     * Name of members tab in view plan tab panel.
     */
    public static final String MEMBERS_TAB = "members";

    /**
     * Name of request parameter with mitigation cost average.
     */
    public static final String MITIGATION_COST_AVG = "mitigationCostAvg";

    /**
     * Name of request parameter with mitigation cost max.
     */
    public static final String MITIGATION_COST_MAX = "mitigationCostMax";

    /**
     * Name of request parameter with mitigation cost min.
     */
    public static final String MITIGATION_COST_MIN = "mitigationCostMin";

    /**
     * Name of request parameter with mitigation cost standard deviation.
     */
    public static final String MITIGATION_COST_STD_DEV = "mitigationCostStdDev";

    /**
     * Requests parameter key that represents model for displaying.
     */
    public static final String MODEL_DISPLAY = "modelDisplay";

    /**
     * Request parameter key with model id.
     */
    public static final String MODEL_ID = "modelId";

    /**
     * A placeholder string that is used in pattern URLs. It is used to denote at which place model
     * id should be inserted.
     */
    public static final String MODEL_ID_PLACEHOLDER = "MODEL_ID_PLACEHOLDER";

    /**
     * Name of model tab in view plan tab panel.
     */
    public static final String MODEL_TAB = "model";

    /**
     * Name request parameter.
     */
    public static final String NAME = "name";

    /**
     * Request parameter with name of column used for ordering.
     */
    public static final String ORDER_COLUMN = "orderColumn";

    /**
     * Denotes ordering direction.
     */
    public static final String ORDER_DIRECTION = "orderDirection";

    /**
     * Pager max page elements request parameter.
     */
    public static final String PAGER_MAX = "pagerMax";

    /**
     * Pager start request parameter (denotes index of first element displayed by pager).
     */
    public static final String PAGER_START = "pagerStart";

    /**
     * Requests parameter key that represents plan object.
     */
    public static final String PLAN = "plan";

    /**
     * Name of portlet preference holding plan manage membership requests URL pattern.
     */
    public static final String PLAN_ACTIONS_URL = "planActionsURL";

    /**
     * Represents action message of successful plan addition/edit.
     */
    public static final String PLAN_ADD_ACTION_MESSAGE = ".doAddPlan";

    /**
     * Name of plan's short content field.
     */
    public static final String PLAN_SHORT_CONTENT_FIELD = "plan_short_content";

    /**
     * Name of plan's content field.
     */
    public static final String PLAN_CONTENT_FIELD = "plan_content";

    /**
     * Name of portlet preference holding plan discussion page URL pattern.
     */
    public static final String PLAN_DISCUSSION_URL = "planDiscussionURL";

    /**
     * Requests parameter key that represents selected plan id.
     */
    public static final String PLAN_ID = "planId";

    /**
     * Name of portlet preference holding plan impacts URL pattern.
     */
    public static final String PLAN_IMPACTS_URL = "planImpactsURL";

    /**
     * Name of portlet preference holding plan members management page URL pattern.
     */
    public static final String PLAN_MANAGE_MEMBERS_URL = "planManageMembersURL";

    /**
     * Name of portlet preference holding plan manage membership requests URL pattern.
     */
    public static final String PLAN_MANAGE_MEMBERSHIP_REQUESTS_URL = "planManageMembershipRequestsURL";

    /**
     * Requests parameter key that represents plan members count.
     */
    public static final String PLAN_MEMBERS_COUNT = "planMembersCount";

    /**
     * Name of portlet preference holding plan members list page URL pattern.
     */
    public static final String PLAN_MEMBERS_URL = "planMembersURL";

    /**
     * Name of plan's model id field.
     */
    public static final String PLAN_MODEL_ID_FIELD = "modelId";

    /**
     * Name of portlet preference holding plan model URL pattern.
     */
    public static final String PLAN_MODEL_URL = "planModelURL";

    /**
     * Name of plan's name field.
     */
    public static final String PLAN_NAME_FIELD = "name";

    /**
     * Requests parameter key that represents plan pending membership requests count.
     */
    public static final String PLAN_PENDING_REQUESTS = "planPendingRequests";

    /**
     * Name of portlet preference holding position URL pattern.
     */
    public static final String PLAN_POSITION_URL = "planPositionURL";

    /**
     * Name of parameter holding list of positions referenced by plan.
     */
    public static final String PLAN_POSITIONS = "planPositions";

    /**
     * Character used to separate plan positions.
     */
    public static final String PLAN_POSITIONS_SEPARATOR = "\\,";

    /**
     * Name of plan's published field.
     */
    public static final String PLAN_PUBLISHED_FIELD = "published";

    /**
     * Name of portlet preference holding question URL pattern.
     */
    public static final String PLAN_QUESTION_URL = "planQuestionURL";

    /**
     * Name of portlet preference holding plan request membership URL pattern.
     */
    public static final String PLAN_REQUEST_MEMBERSHIP_URL = "planRequestMembershipURL";

    /**
     * Requests parameter key that represents plan summary.
     */
    public static final String PLAN_SUMMARY = "planSummary";

    /**
     * Name of portlet resource request parameter.
     */
    public static final String PORTLET_RESOURCE = "portletResource";

    /**
     * A placeholder string that is used in pattern URLs. It is used to denote at which place
     * position id should be inserted.
     */
    public static final String POSITION_ID = "POSITION_ID";

   /**
     * A placeholder string that is used in pattern URLs. It is used to denote at which place
     * child category id should be inserted.
     */
    public static final String CHILD_CATEGORY_ID = "CHILD_CATEGORY_ID";
    

    /**
     * Name of parameter holding list of position ids (separated by positions
     * separator).
     */
    public static final String POSITIONS = "positions";

    /**
     * Name of positions filter operator request parameter.
     */
    public static final String POSITIONS_FILTER_OPERATOR = "positionsFilterOperator";

    /**
     * Published request parameter.
     */
    public static final String PUBLISHED = "published";

    /**
     * Published plans tab name.
     */
    public static final String PUBLISHED_PLANS_TAB = "published-plans-tab";

    /**
     * Name of parameter holding map of questions and their positions.
     */
    public static final String QUESTION_POSITIONS = "questionPositions";

    /**
     * Name of parameter holding list of topic questions.
     */
    public static final String QUESTIONS = "questions";

    /**
     * Requests parameter key that represents URL for redirection.
     */
    public static final String REDIRECT = "redirect";

    /**
     * Name of parameter holding scenario id.
     */
    public static final String SCENARIO_ID = "scenarioId";

    /**
     * A placeholder string that is used in pattern URLs. It is used to denote at which place scenario
     * id should be inserted.
     */
    public static final String SCENARIO_ID_PLACEHOLDER = "SCENARIO_ID_PLACEHOLDER";


    /**
     * Name of request parameter with sea level rise.
     */
    public static final String SEA_LEVEL_RISE = "Sea_Level_Rise_output";

    /**
     * Name of summary tab in view plan tab panel.
     */
    public static final String SUMMARY_TAB = "summary";

    /**
     * Name of request parameter with temperature change.
     */
    public static final String TEMPERATURE_CHANGE = "GlobalTempChange";

    /**
     * Request parameter denoting that filters should be turned on/off.
     */
    public static final String TOGGLE_FILTERS = "toggleFilters";

    /**
     * Unpublished plans tab name.
     */
    public static final String UNPUBLISHED_PLANS_TAB = "unpublished-plans-tab";

    /**
     * Unvote request parameter.
     */
    public static final String UNVOTE = "unvote";

    /**
     * Plan update description update type.
     */
    public static final String UPDATE_DESCRIPTION = "description";
    
    /**
     * Plan update description update type.
     */
    public static final String UPDATE_SHORT_DESCRIPTION = "shortdescription";

    /**
     * Plan update plan name update type.
     */
    public static final String UPDATE_PLAN_NAME = "planname";

    /**
     * Plan update scenario update type.
     */
    public static final String UPDATE_POSITIONS = "positions";

    /**
     * Plan update published status update type.
     */
    public static final String UPDATE_PUBLISHED = "published";

    /**
     * Plan update scenario update type.
     */
    public static final String UPDATE_SCENARIO = "updateScenario";

    

    /**
     * Plan update scenario update type.
     */
    public static final String COPY_PLAN = "copyplan";

    
    /**
     * Plan update type request parameter name.
     */
    public static final String UPDATE_TYPE = "updateType";

    /**
     * User settings session parameter.
     */
    public static final String USER_SETTINGS_PUBLISHED = "plansUserSettingsPublished";

    /**
     * User settings session parameter.
     */
    public static final String USER_SETTINGS_UNPUBLISHED = "plansUserSettingsUnpublished";

    /**
     * Forward path for viewing plan actions.
     */
    public static final String VIEW_PLAN_ACTIONS_FORWARD = "portlet.ext.plans.view_plan.actions";

    /**
     * Forward path for viewing plan description.
     */
    public static final String VIEW_PLAN_DESCRIPTION_FORWARD = "portlet.ext.plans.view_plan.description";

    /**
     * Forward path for viewing plan discussion.
     */
    public static final String VIEW_PLAN_DISCUSSION_FORWARD = "portlet.ext.plans.view_plan.discussion";

    /**
     * Forward path for viewing plan impacts.
     */
    public static final String VIEW_PLAN_IMPACTS_FORWARD = "portlet.ext.plans.view_plan.impacts";

    /**
     * Forward path for viewing plan members.
     */
    public static final String VIEW_PLAN_MEMBERS_FORWARD = "portlet.ext.plans.view_plan.members";

    /**
     * Forward path for viewing plan model.
     */
    public static final String VIEW_PLAN_MODEL_FORWARD = "portlet.ext.plans.view_plan.model";

    /**
     * Forward path for viewing plan summary.
     */
    public static final String VIEW_PLAN_SUMMARY_FORWARD = "portlet.ext.plans.view_plan";

    /**
     * Requests parameter key that represents selected tab in view plan tab panel.
     */
    public static final String VIEW_PLAN_TABS = "viewPlanTabs";

    /**
     * Forward path for view plans action.
     */
    public static final String VIEW_PLANS_FORWARD = "portlet.ext.plans.view_plans";

    /**
     * Requests parameter key that represents selected tab in view plans tab panel.
     */
    public static final String VIEW_PLANS_TABS = "viewPlansTabs";

    /**
     * Vote request parameter.
     */
    public static final String VOTE = "planVote";

    /**
     * Votes request parameter.
     */
    public static final String VOTES = "planVotes";

    /**
     * Votes max request parameter.
     */
    public static final String VOTES_MAX = "votesMax";

    /**
     * Votes min request parameter.
     */
    public static final String VOTES_MIN = "votesMin";
    
    /**
     * Plan edit action.
     */
    public static final String PLAN_EDIT = "PLAN_EDIT";
    
    /**
     * Edit plan action.
     */
    public static final String EDIT_PLAN = "EDIT_PLAN";
    

    /**
     * Edit plan action.
     */
    public static final String EDIT_ANY_PLAN = "EDIT_ANY_PLAN";

    /**
     * Vote for plan action.
     */
    public static final String VOTE_FOR_PLAN = "VOTE_FOR_PLAN";

	public static final String MEMBERS_PARAMETER = "planMembersParameter";
	
	public static final String MEMBERSHIP_REQUESTS_PARAMETER = "planMembershipRequests";
	
	public static final String REQUEST_MEMBERSHIP = "planRequestMembership";
	
	public static final String REQUEST_REPLY = "planMembershipRequestReply";
	
	public static final String REQUEST_STATUS = "planMembershipRequestStatus";
	
	public static final String MEMBERSHIP_REQUEST_ID = "planMembershipRequestId";
	
	public static final String PLAN_REPLACEMENT_STRING="PLAN_ID";
	
	public static final String PLAN_URL="/web/guest/plans?p_p_id=plans&amp;p_p_lifecycle=0&amp;p_p_state=normal&amp;p_p_mode=view&amp;p_p_col_id=column-1&amp;p_p_col_count=1&amp;_plans_struts_action=%2Fext%2Fplans%2Fview_plan&amp;_plans_planId=PLAN_ID";
	
    public static final String PLAN_URL_RAW="/web/guest/plans?p_p_id=plans&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_plans_struts_action=%2Fext%2Fplans%2Fview_plan&_plans_planId=PLAN_ID";
	
	public static final String PLAN_MODEL_URL_ACTUAL = "/web/guest/models?p_p_id=models&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_models_struts_action=%2Fext%2Fmodels%2Frun_model&_models_modelId=MODEL_ID_PLACEHOLDER";

    public static final String PLAN_TYPE_ID = "planTypeId";

    public static final String FILTER_NAME = "FILTER_NAME_";

    public static final String USER_SETTINGS = "USER_SETTINGS_";
	
    /**
     * Private constructor to prevent users from instantiating this class.
     */
    private PlanConstants() {
    }
}
