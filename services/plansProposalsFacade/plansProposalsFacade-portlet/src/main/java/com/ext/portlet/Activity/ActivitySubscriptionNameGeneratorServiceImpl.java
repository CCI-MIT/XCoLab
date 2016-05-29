package com.ext.portlet.Activity;

import com.ext.portlet.model.ActivitySubscription;

import java.util.List;

public class ActivitySubscriptionNameGeneratorServiceImpl implements ActivitySubscriptionNameGeneratorService {
    
    List<ActivitySubscriptionNameGenerator> nameGenerators;
    
    public void setNameGenerators(List<ActivitySubscriptionNameGenerator> nameGenerators) {
        this.nameGenerators = nameGenerators;
    }

    @Override
    public String getName(ActivitySubscription subscription) {
        
        for (ActivitySubscriptionNameGenerator generator: nameGenerators) {
            if (generator.supports(subscription)) {
                return generator.getName(subscription);
            }
        }
        return null;
        
    }

}
