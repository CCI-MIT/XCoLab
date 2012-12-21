/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class ActivityUtil {
    
    private static Log _log = LogFactoryUtil.getLog(ActivityUtil.class);

    private final static String encoding = "UTF-8";

	private static SubscriptionProvider provider = new SubscriptionProvider() {

		public void createSubscription(String portlet, long userid, long entityid, int type) throws SystemException {
			ActivityUtil.createSubscription(portlet, userid, entityid, type);
			
		}

		public void deleteSubscription(String portlet, long userid, long entityid, int type) throws SystemException {
			ActivityUtil.deleteSubscription(portlet, userid, entityid, type);
			
		}

        @Override
        public boolean isSubscribed(String portlet, long userid, long entityid, int type) throws SystemException {
            return ActivityUtil.isSubscribed(portlet,userid,entityid,type);
        }

    };
	
	public static SubscriberFactory getAssociatedSubscriptionFactory(ActivitySubscription a) {
		/*
		int activity = Integer.parseInt(a.getActivitytype());
		if ("plans".equals(a.getPortletId())){
			return PlanActivityKeys.fromId(activity);
		} else if ("debates".equals(a.getPortletId())) {
			return DebateActivityKeys.fromId(activity);
		}
		*/
		return null;
		
	}
	
	public static Map<String,CompositeSubscriptionHolder> getCompositeSubscriptions(long userId) throws SystemException {
		List<ActivitySubscription> subscriptions = ActivitySubscriptionLocalServiceUtil.findByUser(userId);
		Map<String,CompositeSubscriptionHolder> subs = new HashMap<String,CompositeSubscriptionHolder>();
		for (ActivitySubscription sub:subscriptions) {
			String hash = CompositeSubscriptionHolder.createHash(sub);
			if (!subs.containsKey(hash)) {
				subs.put(hash, new CompositeSubscriptionHolder(sub));
			} else {
				subs.get(hash).addSubscription(sub);
			}
		}
		return subs;	
	}
	
	public static List<SocialActivity> retrieveAllActivities(int pagestart,int next) throws SystemException {
		
		List<SocialActivity> activities = SocialActivityLocalServiceUtil.getSocialActivities(pagestart, next);
		return activities;
	}
	
	public static int getAllActivitiesCount() throws SystemException {
		return SocialActivityLocalServiceUtil.getSocialActivitiesCount();
	}
	
	public static List<SocialActivity> retrieveActivities(long userId, int pagestart, int count) throws SystemException, PortalException {
        List<SocialActivity> activities = SocialActivityLocalServiceUtil.getUserActivities(userId, pagestart, count);
        return activities;
	/*
		List<ActivitySubscription> subscriptions = ActivitySubscriptionLocalServiceUtil.findByUser(userId);
		Long companyId = UserLocalServiceUtil.getUser(userId).getCompanyId();
		if (subscriptions.size() ==0) return Collections.emptyList();
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(SocialActivity.class);
		Criterion crit = null;
		for (ActivitySubscription sub : subscriptions) {
			Map<String,Number> criterion = new HashMap<String,Number>();
			int type = Integer.parseInt(sub.getActivitytype());
			if (sub.getEntityId() <0) {
				Portlet p = PortletLocalServiceUtil.getPortletById(companyId, sub.getPortletId());
			
				String[] classnames = p.getSocialActivityInterpreterInstance().getClassNames();
				for (String classname:classnames) {
					criterion.put("classNameId", ClassNameLocalServiceUtil.getClassNameId(classname));
					criterion.put("type", type);
				}
			} else {
				criterion.put("classPK",sub.getEntityId());
				criterion.put("type", type);
			}
			if (crit == null) {
				crit = RestrictionsFactoryUtil.allEq(criterion);
			} else {
				crit = RestrictionsFactoryUtil.or(crit,RestrictionsFactoryUtil.allEq(criterion));
			}
		}
		query.add(crit).addOrder(OrderFactoryUtil.desc("createDate"));
		
		
		List<SocialActivity> activities = new ArrayList<SocialActivity>();
		List<Object> queryResults = SocialActivityLocalServiceUtil.dynamicQuery(query,pagestart,pagestart+count-1);
//		for (Object activity : queryResults
//				.subList(pagestart, Math.min(pagestart + count - 1, queryResults.size() - 1))) {
//			activities.add((SocialActivity) activity);
//		}
		
		for(Object activity:queryResults) {
			activities.add((SocialActivity)activity);
		}
		
		return activities;
		*/
	    //return null;
	}
	
	public static void deleteSubscription(String portlet, long userid, long entityid, int type) throws SystemException {
	    /*
		ActivitySubscriptionPK subscriptionPK = new ActivitySubscriptionPK();
		subscriptionPK.setEntityId(entityid);
		subscriptionPK.setReceiverId(userid);
		subscriptionPK.setActivitytype(type+"");
		subscriptionPK.setPortletId(portlet);
        ActivitySubscription subscription = null;
        try {
            subscription = ActivitySubscriptionLocalServiceUtil
                    .getActivitySubscription(subscriptionPK);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            _log.error(e);
        }
        if (subscription!=null) {
            ActivitySubscriptionLocalServiceUtil.deleteActivitySubscription(subscription);
        }
		*/
	}

    public static boolean isSubscribed(String portlet, long userid, long entityid, int type) throws SystemException {
        /*
        ActivitySubscriptionPK subscriptionPK = new ActivitySubscriptionPK();
		subscriptionPK.setPortletId(portlet);
        subscriptionPK.setEntityId(entityid);
		subscriptionPK.setReceiverId(userid);
		subscriptionPK.setActivitytype(type+"");
        try {
            ActivitySubscriptionLocalServiceUtil.getActivitySubscription(subscriptionPK);
        } catch (Exception e) {
           return false;
        }
		return true;
        */
        return false;

	}

	public static int getActivitiesCount(long userId) throws SystemException, PortalException {
		
		return retrieveActivities(userId,0,Integer.MAX_VALUE).size();
	}
	
	
	public static void addSubscription(SubscriberFactory key, long userid, long entityid) throws SystemException {
		key.subcribe(userid, entityid, provider);
	}

    public static boolean isSubscribed(SubscriberFactory key, long userid, long entityid) throws SystemException {
        return key.isSubscribed(userid,entityid,provider);
    }
	
	public static void removeSubscription(SubscriberFactory key, long userid, long entityid) throws SystemException {
		key.unsubcribe(userid, entityid, provider);
	}
	
	public static void createSubscription(String portlet, long userid, long entityid, int type) throws SystemException {
		/*
		
		ActivitySubscriptionPK subscriptionPK= new ActivitySubscriptionPK();
		subscriptionPK.setEntityId(entityid);
		subscriptionPK.setReceiverId(userid);
		subscriptionPK.setActivitytype(type+"");
		subscriptionPK.setPortletId(portlet);
		ActivitySubscription subscription = null;
		try {
			subscription = ActivitySubscriptionLocalServiceUtil.getActivitySubscription(subscriptionPK);
		} catch (PortalException e) {
			//not found, no problem
		}
		if (subscription != null) {
			return;
		}
		subscription= ActivitySubscriptionLocalServiceUtil.createActivitySubscription(subscriptionPK);
		subscription.setPortletId(portlet);
		subscription.setCreateDate(new Date());
		subscription.setModifiedDate(new Date());
		ActivitySubscriptionLocalServiceUtil.addActivitySubscription(subscription);
		*/
	}
	
	
	
	
	public static void addDefaultSubscriptions(long userid) throws SystemException {
		/*for (SubscriberFactory factory:ActivityConstants.SUBS_DEFAULTS) {
			factory.subcribe(userid,-1, provider);
		}
		*/
	}
	
	public static void clearSubscriptions(long userId) throws SystemException {
		List<ActivitySubscription> subs = ActivitySubscriptionLocalServiceUtil.findByUser(userId);
		for (ActivitySubscription sub:subs) {
			ActivitySubscriptionLocalServiceUtil.deleteActivitySubscription(sub);
		}
	}
	
    public static String encodeMap(Map<String, Serializable> map) {
        StringBuilder encodedString = new StringBuilder();
        for (String attribute : map.keySet()) {
            try {
                encodedString.append(URLEncoder.encode(attribute, encoding));
                encodedString.append("=");
                encodedString.append(URLEncoder.encode(map.get(attribute).toString(), encoding));
                encodedString.append("&");
            } catch (UnsupportedEncodingException e) {
                _log.error(e);
            }
        }
        return encodedString.toString();
    }
    
    public static Map<String, String> decodeMap(String encodedMap) {
        Map<String, String> decodedMap = new HashMap<String, String>();
        String[] parameters = encodedMap.split("&");
        for (String parameter: parameters) {
            if (parameter.trim().equals("")) {
                continue;
            }
            String[] parts = parameter.split("=");
            if (parts.length != 2) {
                _log.error("Invalid parameter " + parts);
            }
            try {
                decodedMap.put(URLDecoder.decode(parts[0], encoding), URLDecoder.decode(parts[1], encoding));
            } catch (UnsupportedEncodingException e) {
                _log.error(e);
            }   
        }
        
        return decodedMap;
    }
    

    public static String getExtraDataForIds(Long... ids) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < ids.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(ids[i]);
        }
        return sb.toString();
    }
    
    public static Long[] getIdsFromExtraData(String extraData) {
        if (extraData == null || extraData.trim().length() == 0) {
            return new Long[] {};
        }
        String[] idStrs = extraData.split(",");
        Long[] ret = new Long[idStrs.length];
        
        for (int i=0; i < idStrs.length; i++) {
            try {
                ret[i] = Long.parseLong(idStrs[i]);
            }
            catch (NumberFormatException e) {
                ret[i] = 0L;
            }
        }
        return ret;
    }
	
}
