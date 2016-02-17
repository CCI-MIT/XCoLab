package com.ext.utils;

import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author pdeboer
 *         First created on 30/10/13 at 22:25
 */
public class SocialActivityMessageLimitationHelper {
    Set<Long> limitedClassesIds = new HashSet<>();

    public SocialActivityMessageLimitationHelper(Class... limitedClasses) {
        for (Class c : limitedClasses) {
            long id = ClassNameLocalServiceUtil.getClassNameId(c);
            limitedClassesIds.add(id);
        }
    }

    public List<SocialActivity> process(List data) {
        Map<String,SocialActivity> ret = new HashMap<>(data.size()*2);
        for (Object o : data) {
            SocialActivity sa  = (SocialActivity) o;
            ret.put(getKey(sa),sa);
        }
        return new LinkedList<>(ret.values());
    }

    private String getKey(SocialActivity sa) {
        if (limitedClassesIds.contains(sa.getClassNameId())) {
            return sa.getClassNameId()+"_"+sa.getClassPK();
        }
        else{
            return "0_" + sa.getActivityId();
        }
    }
}
