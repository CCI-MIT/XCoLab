package org.xcolab.portlets.blogsstats;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BlogsEntryServiceHelper implements Serializable {
    
    private final static String F_DISPLAY_DATE = "displayDate";
    private final static String F_GROUP_ID = "groupId";
    
    
    public static List<Object> getGroupEntriesByYearMonth(Long groupId, int year, int month, int start, int max) throws SystemException {
        DynamicQuery dq = getGroupEntriesFilterByYearMonthQuery(groupId, year, month);
        
        return BlogsEntryLocalServiceUtil.dynamicQuery(dq, start, max);
    }
    
    public static Integer countGroupEntriesByYearMonth(Long groupId, int year, int month) throws SystemException {
        DynamicQuery dq = getGroupEntriesFilterByYearMonthQuery(groupId, year, month);
        dq.setProjection(ProjectionFactoryUtil.rowCount());
        
        
        List<Object> result = BlogsEntryLocalServiceUtil.dynamicQuery(dq);
        return result.isEmpty() ? 0 : (Integer) result.get(0);
    }
    
    public static List<BlogsEntryStats> getEntryDates(Long groupId) throws SystemException {
        DynamicQuery dq = getGroupEntriesFilterByYearMonthQuery(groupId, -1, -1);
        dq.setProjection(ProjectionFactoryUtil.property(F_DISPLAY_DATE));
        
        List<Object> result = BlogsEntryLocalServiceUtil.dynamicQuery(dq);
        Calendar c = Calendar.getInstance();
        Calendar prev = Calendar.getInstance();
        prev.set(Calendar.YEAR, 0);
        List<BlogsEntryStats> stats = new ArrayList<BlogsEntryServiceHelper.BlogsEntryStats>();
        BlogsEntryStats monthStats = null;
        
        for (Object obj: result) {
            c.setTime((Timestamp) obj);
            
            if (monthStats == null || c.get(Calendar.YEAR) != prev.get(Calendar.YEAR) || c.get(Calendar.MONTH) != prev.get(Calendar.MONTH)) {
                monthStats = new BlogsEntryStats((Timestamp) obj, 0);
                stats.add(monthStats);
                prev.set(Calendar.YEAR, c.get(Calendar.YEAR));
                prev.set(Calendar.MONTH, c.get(Calendar.MONTH));
                        
            }
            monthStats.count++;
            
        }
        
        return stats;
    }
    
    private static DynamicQuery getGroupEntriesFilterByYearMonthQuery(Long groupId, int year, int month) {
        DynamicQuery dq = DynamicQueryFactoryUtil.forClass(BlogsEntry.class);
        
        Calendar c = Calendar.getInstance();
        if (year > 0) {
            c.set(Calendar.YEAR, year);
            
            if (month >= 0) {
                c.set(Calendar.MONTH, month);
            }
            else {
                c.set(Calendar.MONTH, 0);
            }
            
            c.set(Calendar.DAY_OF_MONTH, 0);
            c.set(Calendar.HOUR, 0);
            c.set(Calendar.MINUTE, 0);
            
            Date fromDate = c.getTime();
            
            if (month >=0) {
                c.add(Calendar.MONTH, 1);
            }
            else {
                c.add(Calendar.YEAR, 1);
            }
            
            Date toDate = c.getTime();
            dq.add(RestrictionsFactoryUtil.ge(F_DISPLAY_DATE, fromDate));
            dq.add(RestrictionsFactoryUtil.lt(F_DISPLAY_DATE, toDate));
        }
            
        dq.add(RestrictionsFactoryUtil.eq(F_GROUP_ID, groupId));
        dq.addOrder(OrderFactoryUtil.desc(F_DISPLAY_DATE));
        
        return dq;
        
    }
    
    public static class BlogsEntryStats {
        public Date date;
        public int count;
        
        public BlogsEntryStats(Date date, int count) {
            this.date = date;
            this.count = count;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "BlogsEntryStats [date=" + date + ", count=" + count + "]";
        }
        
        
    }

}
