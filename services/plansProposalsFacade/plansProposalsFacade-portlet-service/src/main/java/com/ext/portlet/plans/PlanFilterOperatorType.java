package com.ext.portlet.plans;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import com.ext.portlet.model.PlanAttribute;
import com.ext.portlet.model.PlanAttributeFilter;
import com.ext.portlet.model.PlansUserSettings;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.service.PlanAttributeLocalServiceUtil;

public enum PlanFilterOperatorType {
    LIKE(new PlanFilterOperator.LikeOperator()),
    LESS_THAN(new PlanFilterOperator.LessThanOperator()),
    LESS_THAN_OR_NULL(new PlanFilterOperator.LessThanOrNullOperator()),
    GREATER_THAN(new PlanFilterOperator.MoreThanOperator()),
    GREATER_THAN_OR_NULL(new PlanFilterOperator.MoreThanOrNullOperator()),
    MIN_MAX(new PlanFilterOperator.MinMaxOperator()),
    DATE_FROM_TO(new PlanFilterOperator.DateFromToOperator()),
    DUMMY(new PlanFilterOperator.DummyOperator()), 
    POSITIONS_FILTER(new PlanFilterOperator.PositionsFilterOperator());
    
    private PlanFilterOperator operator;

    PlanFilterOperatorType(PlanFilterOperator operator) {
        this.operator = operator;
    }

    public boolean isInFilteredSet(PlansUserSettings userSettings, PlanAttributeFilter planAttributeFilter, PlanAttribute planAttribute) {
        return operator.isInFilteredSet(userSettings, planAttributeFilter, planAttribute);
    }
    
    interface PlanFilterOperator {
        boolean isInFilteredSet(PlansUserSettings userSettings, PlanAttributeFilter planAttributeFilter, PlanAttribute planAttribute);
        
        public class LikeOperator implements PlanFilterOperator {

            @Override
            public boolean isInFilteredSet(PlansUserSettings userSettings, PlanAttributeFilter planAttributeFilter, PlanAttribute planAttribute) {
                String val = planAttribute.getAttributeValue();
                String filterVal = planAttributeFilter.getStringVal();
                if (filterVal == null || filterVal.trim().length() == 0) {
                    return true;
                }
                Pattern pattern = Pattern.compile(".*" + filterVal.replaceAll("\\s", ".*") + ".*", Pattern.CASE_INSENSITIVE);
                System.out.println("Matches: " + pattern.matcher(val).matches());
                return val == null ? false : pattern.matcher(val).matches();
            }
        }
        
        public class LessThanOperator implements PlanFilterOperator {

            @Override
            public boolean isInFilteredSet(PlansUserSettings userSettings, PlanAttributeFilter planAttributeFilter, PlanAttribute planAttribute) {
                //if (planAttributeFilter.getTypedValue() == null) {
                    return true;
                //}
                //Comparable attributeVal = (Comparable) PlanAttributeLocalServiceUtil.getTypedValue(planAttribute);
                //return attributeVal == null ? false : attributeVal.compareTo(planAttributeFilter.getTypedValue()) <= 0;
                //return false;
            }
        }

        public class LessThanOrNullOperator extends LessThanOperator {

            @Override
            public boolean isInFilteredSet(PlansUserSettings userSettings, PlanAttributeFilter planAttributeFilter, PlanAttribute planAttribute) {
                Object[] filterValues = TypedValueConverter.getValues(Attribute.valueOf(planAttribute.getAttributeName()).getAttributeClass(),
                        planAttributeFilter.getStringVal());
                if (filterValues.length > 1) {
                   Comparable filterVal = (Comparable) filterValues[0];
                    Comparable attributeVal = (Comparable) PlanAttributeLocalServiceUtil.getTypedValue(planAttribute);

                   return filterVal == null ? true : attributeVal==null?filterValues[1] == null:attributeVal.compareTo(filterVal) <= 0;
                } else {
                    return super.isInFilteredSet(userSettings, planAttributeFilter,planAttribute);
                }

            }
        }
        
        public class MoreThanOperator implements PlanFilterOperator {

            @Override
            public boolean isInFilteredSet(PlansUserSettings userSettings, PlanAttributeFilter planAttributeFilter, PlanAttribute planAttribute) {
                /*if (planAttributeFilter.getTypedValue() == null) {
                    return true;
                }
                Comparable attributeVal = (Comparable) PlanAttributeLocalServiceUtil.getTypedValue(planAttribute);
                return attributeVal == null ? false : attributeVal.compareTo(planAttributeFilter.getTypedValue()) >= 0;
                */
                return true;
            }
        }

        public class MoreThanOrNullOperator extends MoreThanOperator {

            @Override
            public boolean isInFilteredSet(PlansUserSettings userSettings, PlanAttributeFilter planAttributeFilter, PlanAttribute planAttribute) {
                Object[] filterValues = TypedValueConverter.getValues(Attribute.valueOf(planAttribute.getAttributeName()).getAttributeClass(),
                        planAttributeFilter.getStringVal());
                if (filterValues.length > 1) {
                   Comparable filterVal = (Comparable) filterValues[0];
                    Comparable attributeVal = (Comparable) PlanAttributeLocalServiceUtil.getTypedValue(planAttribute);

                   return filterVal == null ? true : attributeVal==null?filterValues[1] == null:attributeVal.compareTo(filterVal) >= 0;
                } else {
                    return super.isInFilteredSet(userSettings, planAttributeFilter,planAttribute);
                }

            }
        }

        
        public class MinMaxOperator implements PlanFilterOperator {

            @Override
            public boolean isInFilteredSet(PlansUserSettings userSettings, PlanAttributeFilter planAttributeFilter, PlanAttribute planAttribute) {
                if (planAttributeFilter.getMin() == null || planAttributeFilter.getMax() == null) {
                    return true;
                }
                Comparable attributeVal = (Comparable) PlanAttributeLocalServiceUtil.getTypedValue(planAttribute);
                return attributeVal == null ? false : attributeVal.compareTo(planAttributeFilter.getMax()) <= 0 &&
                    attributeVal.compareTo(planAttributeFilter.getMin()) >= 0;
            }
        }


        
        public class DateFromToOperator implements PlanFilterOperator {

            @Override
            public boolean isInFilteredSet(PlansUserSettings userSettings, PlanAttributeFilter planAttributeFilter, PlanAttribute planAttribute) {
                Date dateFrom = null;
                Date dateTo = null;

                Object[] dates = TypedValueConverter.getValues(Date.class, planAttributeFilter.getStringVal());
                dateFrom = (Date) (dates.length > 0 ? dates[0] : null);
                dateTo = (Date) (dates.length > 1 ? dates[1] : null); 
                Date planDate = (Date) PlanAttributeLocalServiceUtil.getTypedValue(planAttribute);
                
                if ((dateFrom != null || dateTo != null) && planDate == null) {
                    return false;
                }
                
                if (dateFrom != null && dateTo != null) {
                    return planDate.compareTo(dateFrom) >= 0 && planDate.compareTo(dateTo) <= 0;
                    
                }
                else if (dateFrom != null && dateTo == null) {
                    return planDate.compareTo(dateFrom) >= 0;
                }
                else if (dateFrom == null && dateTo != null) {
                    return planDate.compareTo(dateTo) <= 0;
                }
                return true;
            }
        }
        
        public class DummyOperator implements PlanFilterOperator {
            @Override
            public boolean isInFilteredSet(PlansUserSettings userSettings, PlanAttributeFilter planAttributeFilter, PlanAttribute planAttribute) {
                return true;
            }
        }
        
        public class PositionsFilterOperator implements PlanFilterOperator {
            @Override
            public boolean isInFilteredSet(PlansUserSettings userSettings, PlanAttributeFilter planAttributeFilter, PlanAttribute planAttribute) {
                boolean operatorAll = userSettings.getFilterPositionsAll();
                /*if (userSettings.getPositionsIds().size() == 0) {
                    return true;
                }
                Set<Long> positionsIds = new HashSet<Long>(userSettings.getPositionsIds());
                Object typedValue = PlanAttributeLocalServiceUtil.getTypedValue(planAttribute);
                if (typedValue == null) {
                    typedValue = new Long[0];
                }
                Set<Long> planPositions = new HashSet<Long>(Arrays.asList((Long[]) typedValue));
                
                if (operatorAll) {
                    return planPositions.containsAll(positionsIds);
                }
                else {
                    positionsIds.retainAll(planPositions);
                    return positionsIds.size() > 0;
                }*/
                return true;
            }
        }

        
        
        
    }
}
