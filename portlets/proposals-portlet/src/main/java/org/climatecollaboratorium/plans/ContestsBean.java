package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.navigation.NavigationEvent;
import org.climatecollaboratorium.plans.wrappers.ContestWrapper;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;

public class ContestsBean {
    private List<ContestWrapper> contests = new ArrayList<ContestWrapper>();
    private List<ContestWrapper> contestsPart1;
    private List<ContestWrapper> contestsPart2;
    private List<ContestWrapper> contestsFeatured;
    private List<ContestWrapper> contestsNormal;
    private TreeMap<Tuple, List<ContestWrapper>> contestsSplitted = new TreeMap<Tuple, List<ContestWrapper>>(new Comparator<Tuple>() {
        public int compare(Tuple o1, Tuple o2) {
            return ((Integer) o1.getObject(0)) - ((Integer) (o2.getObject(0)));
        }
    });
    private EventBus eventBus;
    private ViewType viewType = ViewType.GRID;
    private String filterString;
    private String newContestSuggestion;
    private boolean contestSuggestionSent;
    
    
    private SortColumn sortColumn = null;
    
    private boolean sortAsc = true;
    private boolean showActiveOnly = true;
    public final static String PLANS_SOURCE = "plans";
    private static final String CONTEST_FILTER_PARAM = "contestFilter";
    private static final String CONTESTS_TYPE_PARAM = "contestsType";
    private static final String CONTESTS_PRIOR = "prior";
    
    
    public ContestsBean(Map<String, String> params) {
        
        String viewTypeStr = Helper.getCookieValue("cc_contests_viewType");
        if (viewType != null) {
            try {
                viewType = ViewType.valueOf(viewTypeStr);
            }
            catch (Exception e) {
                // ignore
            }
        }
        if (params.containsKey(CONTESTS_TYPE_PARAM) && params.get(CONTESTS_TYPE_PARAM).equals(CONTESTS_PRIOR)) {
            showActiveOnly = false;
        }
        
    }
    
    
    public List<ContestWrapper> getContests() throws SystemException, PortalException {
        //System.out.println(System.currentTimeMillis() + "\t get contests");
        if (contests.size() == 0) {
            for (Contest contest: ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
                boolean add = true;
                if (filterString != null) {
                    if (contest.getContestShortName().toLowerCase().contains(filterString) || 
                            contest.getContestName().toLowerCase().contains(filterString)) {
                        add = true;
                    
                    }
                    else {
                        add = false;
                        
                    }
                }
                add &= !(showActiveOnly ^ contest.getContestActive());
                if (add) {
                    ContestWrapper contestWrapper = new ContestWrapper(contest);
                    if (eventBus != null) {
                        contestWrapper.setEventBus(eventBus);
                    }
                    contests.add(contestWrapper);
                }
            }
            if (sortColumn != null) {
                Collections.sort(contests, sortColumn);
                if (sortAsc) Collections.reverse(contests);
            }
        }
        
        return contests;
    }
    
    public List<ContestWrapper> getContestsPart1() throws SystemException, PortalException {
        //System.out.println(System.currentTimeMillis() + "\t get contests part1 ");
        if (contestsPart1 == null) {
            splitContestsIntoParts();
        }
        return contestsPart1;
    }
    
    public List<ContestWrapper> getContestsPart2() throws SystemException, PortalException {
        //System.out.println(System.currentTimeMillis() + "\t get contests part2 ");
        if (contestsPart2 == null) {
            splitContestsIntoParts();
        }
        return contestsPart2;
    }
    
    public List<ContestWrapper> getContestsFeatured() throws SystemException, PortalException {
        //System.out.println(System.currentTimeMillis() + "\t get contests featured ");
        if (contestsPart2 == null) {
            splitContestsIntoParts();
        }
        return contestsFeatured;
    }


    public List<ContestWrapper> getContestsNormal() throws SystemException, PortalException {
        //System.out.println(System.currentTimeMillis() + "\t get contests normal ");
        if (contestsPart2 == null) {
            splitContestsIntoParts();
        }
        return contestsNormal;
    }


    private void splitContestsIntoParts() throws SystemException, PortalException {
        //System.out.println(System.currentTimeMillis() + "\tspliting contests ");
        if (contestsPart1 == null || contestsPart2 == null) {
            List<ContestWrapper> contests = getContests();
            contestsPart1 = new ArrayList<ContestWrapper>();
            contestsPart2 = new ArrayList<ContestWrapper>();
            contestsFeatured = new ArrayList<ContestWrapper>();
            contestsNormal = new ArrayList<ContestWrapper>();
            
            
            
            for (ContestWrapper c: contests) {
                if (c.isFeatured()) contestsFeatured.add(c);
                else contestsNormal.add(c);
            }
            
            Collections.sort(contestsNormal, new Comparator<ContestWrapper>() {
                @Override
                public int compare(ContestWrapper o1, ContestWrapper o2) {
                    if (o1.getFlag() != null) 
                        if (o2.getFlag() != null) 
                            return o1.getFlag() - o2.getFlag();
                        else 
                            return -1;
                    else 
                        return o2.getFlag() != null ? 1 : 0;
                }  
            });
            
            boolean addToFirst = true;
            for (ContestWrapper c: contestsFeatured) {
                if (addToFirst) contestsPart1.add(c);
                else contestsPart2.add(c);
                addToFirst = !addToFirst;
            }
            for (ContestWrapper c: contestsNormal) {
                if (addToFirst) contestsPart1.add(c);
                else contestsPart2.add(c);
                addToFirst = !addToFirst;
            }
        }
    }


    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        for (ContestWrapper contest: contests) {
            contest.setEventBus(eventBus);
        }
    }
    
    public void setViewType(ActionEvent e) {
        //System.out.println(System.currentTimeMillis() + "\t set view Type");
        Object newViewTypeObj = e.getComponent().getAttributes().get("viewType");
        if (newViewTypeObj != null) {
            try {
                viewType = ViewType.valueOf(newViewTypeObj.toString());
            }
            catch (Exception ex) {
                // ignore
            }
        }
    }
    
    public String getProfileEvent() {
        //System.out.println(System.currentTimeMillis() + "\t get profile event");
        return "";
    }
    
    public ViewType getViewType() {
        return viewType;
    }
    
    private void clearContestsList() {

        contestsPart1 = null;
        contestsPart2 = null;
        contestsFeatured = null;
        contestsNormal = null;
        contests.clear();
    }
    
    
    public void setFilterString(String filter) {
        this.filterString = filter.trim().length() == 0 ? null : filter.trim();
        clearContestsList();
    }


    public String getFilterString() {
        return filterString;
    }
    
    public void changeSortColumn(ActionEvent e) {
        SortColumn nSortColumn = SortColumn.valueOf(e.getComponent().getAttributes().get("sortColumn").toString());
        if (sortColumn == nSortColumn) sortAsc = !sortAsc;
        else sortColumn = nSortColumn;
        
        clearContestsList();
    }
    

    public void setShowActiveOnly(boolean showActiveOnly) {
        this.showActiveOnly = showActiveOnly;
        clearContestsList();
    }
    
    public void toggleActiveInactive(ActionEvent e) {
        showActiveOnly = !showActiveOnly;
        clearContestsList();
    }


    public boolean isShowActiveOnly() {
        return showActiveOnly;
    }
    
    public String getSortColumn() {
        if (sortColumn != null) {
            return sortColumn.name();
        }
        return null;
    }
    
    public boolean isSortAscending() {
        return sortAsc;
    }


    public static enum ViewType {
        GRID,
        LIST;
        
        public String getLowerCase() {
            return name().toLowerCase();
        }
    }
    
    public static enum SortColumn implements Comparator<ContestWrapper> {
        CONTEST_NAME,
        PROPOSALS,
        COMMENTS,
        WHO,
        WHERE,
        WHAT
        ;

        @Override
        public int compare(ContestWrapper o1, ContestWrapper o2) {
            String w1 = ""; 
            String w2 = "";
            try {
                switch (this) {
                    case CONTEST_NAME:
                        return o1.getContest().getContestShortName().compareTo(o2.getContest().getContestShortName());
                    case PROPOSALS:
                        return (int) (o1.getProposalsCount() - o2.getProposalsCount());
                    case COMMENTS:
                        return (int) (o1.getCommentsCount() - o2.getCommentsCount());
                    case WHO:
                        w1 = o1.getWho() == null ? "" : o1.getWho().get(0).getName();
                        w2 = o2.getWho() == null ? "" : o2.getWho().get(0).getName();
                    
                        return w1.compareTo(w2);

                    case WHAT:
                        w1 = o1.getWhat() == null ? "" : o1.getWhat().get(0).getName();
                        w2 = o2.getWhat() == null ? "" : o2.getWhat().get(0).getName();
                    
                        return w1.compareTo(w2);

                    case WHERE:
                        w1 = o1.getWho() == null ? "" : o1.getWho().get(0).getName();
                        w2 = o2.getWho() == null ? "" : o2.getWho().get(0).getName();
                    
                        return w1.compareTo(w2);
                }
            
            }
            catch (Exception e) {
                // ignore
            }
            return o1.hashCode() - o2.hashCode();
                        
            
        }
        
    }

    public void init(NavigationEvent event) {
        Map<String, String> parameters = event.getParameters(PLANS_SOURCE);
        if (parameters == null || ! parameters.containsKey(CONTEST_FILTER_PARAM)) {
            filterString = null;
            clearContestsList();
            return;
        }
        filterString = parameters.get(CONTEST_FILTER_PARAM).trim();
        clearContestsList();
        
    }


    public void setNewContestSuggestion(String newContestSuggestion) {
        this.newContestSuggestion = newContestSuggestion;
        contestSuggestionSent = false;
    }


    public String getNewContestSuggestion() {
        return newContestSuggestion;
    }
    
    public void sendContestSuggestion(ActionEvent e) throws MailEngineException, AddressException, NumberFormatException, PortalException, SystemException {
        String messageSubject = "New contest suggestion";
        String messageBody = newContestSuggestion;
        
        String[] receipients = new String[] {"janusz.parfieniuk@gmail.com", "jintrone@MIT.EDU", "rjl@MIT.EDU"};
        
        InternetAddress[] addressTo = new InternetAddress[receipients.length];
        for (int i=0; i < receipients.length; i++) {
            addressTo[i] = new InternetAddress(receipients[i]);
        }
        
        InternetAddress addressFrom = new InternetAddress("admin@climatecolab.org");

        InternetAddress replyTo[] = {new InternetAddress(Helper.getLiferayUser().getEmailAddress())};
        
        
        MailEngine.send(addressFrom, addressTo, null, null, null, messageSubject, messageBody, false, replyTo, null, null);
        
        FacesMessage fm = new FacesMessage();
        fm.setSummary("Message has been sent.");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, fm);
        
        contestSuggestionSent = true;
        
        newContestSuggestion = null;
    }


    public void setContestSuggestionSent(boolean contestSuggestionSent) {
        this.contestSuggestionSent = contestSuggestionSent;
        
    }


    public boolean isContestSuggestionSent() {
        contestSuggestionSent = !contestSuggestionSent;
        return !contestSuggestionSent;
        
    }

}
