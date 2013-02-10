package com.ext.portlet.service.persistence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ext.portlet.NoSuchPlanAttributeFilterException;
import com.ext.portlet.NoSuchPlanTypeException;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanType;
import com.ext.portlet.model.PlanVote;
import com.ext.portlet.model.PlansUserSettings;
import com.ext.portlet.model.impl.PlanItemImpl;
import com.ext.portlet.model.impl.PlanItemModelImpl;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.plans.PlanConstants.Property;
import com.ext.portlet.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.service.PlanVoteLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class PlanItemFinderImpl extends BasePersistenceImpl implements PlanItemFinder {
    private final String GET_PLAN_ITEMS = PlanItemFinderImpl.class.getName() + ".getPlans";
    private final String REMOVE_PLAN_WITH_HISTORY = PlanItemFinderImpl.class.getName() + ".removePlanWithHistory";
    private final String GET_PLANS_FOR_PHASE = PlanItemFinderImpl.class.getName() + ".getPlansForContestPhase";
    
    
    public static final String DEFAULT_FORUM_CATEGORY_DESCRIPTION = "General discussion about plan %s";
    public static final String FINDER_CLASS_NAME_ENTITY = PlanItemImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY + ".List";
    
    
    private static final FinderPath FINDER_PATH_FETCH_BY_CONTEST_PHASE_ID;
    private static final FinderPath FINDER_PATH_COUNT_BY_CONTEST_PHASE_ID;
    static {
        FINDER_PATH_FETCH_BY_CONTEST_PHASE_ID = new FinderPath(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
                PlanItemModelImpl.FINDER_CACHE_ENABLED, PlanItemImpl.class, FINDER_CLASS_NAME_LIST, 
                "fetchByContestPhaseId", new String[] {Long.class.getName()});
        
        FINDER_PATH_COUNT_BY_CONTEST_PHASE_ID = new FinderPath(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
                PlanItemModelImpl.FINDER_CACHE_ENABLED, PlanItemImpl.class, FINDER_CLASS_NAME_LIST, 
                "countByContestPhaseId", new String[] {Long.class.getName()});
    }
    
    
    
    private static Log _log = LogFactoryUtil.getLog(PlanItemFinderImpl.class);
    /**
     * Name of custom SQL statement that is responsible for counting filtered
     * plans.
     */


    public static final String COUNT_VOTE_BY_TYPE = PlanItemFinderImpl.class.getName()+".countVotesByPlanType";

    public static final String COUNT_PLANS = PlanItemFinderImpl.class.getName() + ".countPlans";

    /**
     * Name of custom SQL statement that is responsible for counting filtered
     * plans.
     */
    public static final String GET_PLANS = PlanItemFinderImpl.class.getName() + ".getPlansForType";

    /**
     * Name of custom SQL statement that is responsible for counting filtered
     * plans.
     */
    public static final String COUNT_FILTERED_PLANS = PlanItemFinderImpl.class.getName() + ".countFilteredPlans3";

    /**
     * Name of custom SQL statement that is responsible for counting filtered
     * plans.
     */
    public static final String GET_FILTERED_PLANS = PlanItemFinderImpl.class.getName() + ".getFilteredPlans";

    /**
     * Name of custom SQL statement that is responsible for retrieval of user vote position.
     */
    public static final String GET_USER_VOTE_POSITION = PlanItemFinderImpl.class.getName() + ".getUserVotePosition2";

    /**
     * Name of custom SQL statement that is responsible for retrieval of user vote position.
     */
    public static final String GET_FILTERED_USER_VOTE_POSITION = PlanItemFinderImpl.class.getName() +
        ".getFilteredUserVotePosition3";

    /**
     * Name of custom SQL statement that is responsible for retrieval of user vote position.
     */
    public static final String GET_PLANS_POSITIONS = PlanItemFinderImpl.class.getName() + ".getPlansPositions";
    
    /**
     * Name of custom SQL statement that is responsible for retrieval of latest plan version for given id;
     */
    public static final String GET_LATEST_VERSION_OF_PLAN = PlanItemFinderImpl.class.getName() + ".getLatestPlanVersion";

    /**
     * Position of published parameter in custom queries.
     */
    public static final int PARAM_PLAN_TYPE_ID = 0;

    /**
     * Position of name parameter in custom queries.
     */
    public static final int PARAM_NAME = 1;

    /**
     * Position of description parameter in custom queries.
     */
    public static final int PARAM_DESCRIPTION = 2;

    /**
     * Position of creator parameter in custom queries.
     */
    public static final int PARAM_CREATOR = 3;

    /**
     * Position of filter positions parameter in custom queries.
     */
    public static final int PARAM_FILTER_POSITIONS = 4;
    
    /**
     * Position of positions count parameter in custom queries.
     */
    public static final int PARAM_POSITIONS_COUNT = 5;
    
    /**
     * Position of positions count parameter in custom queries.
     */
    public static final int PARAM_ATTRIBUTES_COUNT = 6;

    /**
     * Position of votes from parameter in custom queries.
     */
    public static final int PARAM_VOTES_FROM = 6;

    /**
     * Position of votes to parameter in custom queries.
     */
    public static final int PARAM_VOTES_TO = 7;

   
    /**
     * Position of create date from parameter in custom queries.
     */
    public static final int PARAM_CREATE_DATE_FROM = 10;

    /**
     * Position of create date to parameter in custom queries.
     */
    public static final int PARAM_CREATE_DATE_TO = 11;
    
    /**
     * Position of publish date from parameter in custom queries.
     */
    public static final int PARAM_DATE_FROM = 8;

    /**
     * Position of publish date to parameter in custom queries.
     */
    public static final int PARAM_DATE_TO = 9;

    /**
     * Position of publish date from parameter in custom queries.
     */
    public static final int PARAM_PUBLISH_DATE_FROM = 8;

    /**
     * Position of publish date to parameter in custom queries.
     */
    public static final int PARAM_PUBLISH_DATE_TO = 9;

    /**
     * Value for positions count if user wants to filter by position.
     */
    public static final int POSITIONS_FILTER_ANY = 1;

    /**
     * Placeholder string in custom SQL that should be replaced with list
     * of positions.
     */
    public static final String POSITIONS_PLACEHOLDER = "POSITIONS_PLACEHOLDER";
    
    /**
     * Placeholder string in custom SQL that should be replaced with list
     * of positions.
     */
    public static final String ATTRIBUTES_PLACEHOLDER = "ATTRIBUTES_PLACEHOLDER";
    
    /**
     * Placeholder string in custom SQL that should be replaced with list
     * of positions.
     */
    public static final String PLAN_ATTRIBUTE_NAME_PLACEHOLDER = "PLAN_ATTRIBUTE_NAME_PLACEHOLDER";
    
    
    /**
     * Placeholder string in custom SQL that should be replaced with list
     * of positions.
     */
    public static final String PLAN_ATTRIBUTE_VALUE_PLACEHOLDER = "PLAN_ATTRIBUTE_VALUE_PLACEHOLDER";

    /**
     * Placeholder string in custom SQL that should be replaced with user id.
     */
    public static final String USER_ID_PLACEHOLDER = "USER_ID_PLACEHOLDER";

    /**
     * Placeholder string in custom SQL that should be replaced with list
     * of plans ids.
     */
    public static final String PLAN_IDS_PLACEHOLDER = "PLAN_IDS_PLACEHOLDER";

    /**
     * Placeholder string in custom SQL that should be replaced with constraints for
     * filtering out plans that are "before" plan user has voted for (thus counting
     * them will result in user vote position).
     */
    public static final String USER_VOTE_CONSTRAINTS_PLACEHOLDER = "USER_VOTE_CONSTRAINTS_PLACEHOLDER";

    /**
     * Placeholder string in custom SQL that should be replaced with list of
     * columns for sorting.
     */
    public static final String SORT_COLUMNS_PLACEHOLDER = "SORT_COLUMNS_PLACEHOLDER";

    /**
     * String for filtering by position id.
     */
    public static final String POSITION_FILTER_STRING = "PlanPosition.positionId = ";

    /**
     * Common plan table column prefix in custom SQL's.
     */
    public static final String COLUMN_PREFIX = "PlanItem.";

    /**
     * Table prefix for query that returns plan on which user has voted.
     */
    public static final String USER_VOTE_PREFIX = "UserVote.";

    /**
     * Name column name.
     */
    public static final String COLUMN_NAME = "AttributeNAME";
    
   

    /**
     * Create date column name.
     */
    public static final String COLUMN_CREATE_DATE = "AttributeCREATE_DATE";

    /**
     * Publish date column name.
     */
    public static final String COLUMN_PUBLISH_DATE = "AttributePUBLISH_DATE";

    /**
     * Votes column name.
     */
    public static final String COLUMN_VOTES = "AttributeVOTES";

    /**
     * Plan Id column name.
     */
    public static final String COLUMN_ID = "PlanItem.planId";

    /**
     * Description column name.
     */
    public static final String COLUMN_DESCRIPTION = "Plan.content";

    /**
     * Creator column name.
     */
    public static final String COLUMN_CREATOR = "Plan.username";

    /**
     * CO2 column name.
     */
    public static final String COLUMN_CO2 = "AttributeCO2";

    /**
     * Mitigation column name.
     */
    public static final String COLUMN_MITIGATION = "AttributeMIN_MITIGATION_COST";

    /**
     * Damage column name.
     */
    public static final String COLUMN_DAMAGE = "AttributeMIN_DAMAGE_COST";

    /**
     * Developed emissions column name.
     */
    public static final String COLUMN_DEVELOPED = "AttributeEMISSIONS_DEVELOPED";

    /**
     * Developing A column name.
     */
    public static final String COLUMN_DEVELOPING_A = "AttributeEMISSIONS_DEVELOPING_A";

    /**
     * Developing B column name.
     */
    public static final String COLUMN_DEVELOPING_B = "AttributeEMISSIONS_DEVELOPING_B";

    /**
     * Temperature column name.
     */
    public static final String COLUMN_TEMPERATURE = "AttributeTEMP";

    /**
     * Sea level column name.
     */
    public static final String COLUMN_SEA_LEVEL = "AttributeSEA_LEVEL";

    /**
     * Global emissions column name.
     */
    public static final String COLUMN_GLOBAL = "AttributeglobalEmissions";
    
    
    /**
     * needed for sorting
     */
    public static final Map<String, String> columnsMapping = new HashMap<String, String>(); 
    
    static {
        columnsMapping.put(Columns.CO2_CONCENTRATION.name(), COLUMN_CO2);
        columnsMapping.put(Columns.COLUMN_DEVELOPED_EMISSIONS.name(), COLUMN_DEVELOPED);
        columnsMapping.put(Columns.COLUMN_DEVELOPING_A_EMISSIONS.name(), COLUMN_DEVELOPING_A);
        columnsMapping.put(Columns.COLUMN_DEVELOPING_B_EMISSIONS.name(), COLUMN_DEVELOPING_B);
        columnsMapping.put(Columns.CREATE_DATE.name(), COLUMN_CREATE_DATE);
        columnsMapping.put(Columns.CREATOR.name(), COLUMN_CREATOR);
        columnsMapping.put(Columns.DAMAGE_COST.name(), COLUMN_DAMAGE);
        columnsMapping.put(Columns.MITIGATION_COST.name(), COLUMN_MITIGATION);
        columnsMapping.put(Columns.NAME.name(), COLUMN_NAME);
        columnsMapping.put(Columns.PUBLISH_DATE.name(), COLUMN_PUBLISH_DATE);
        columnsMapping.put(Columns.SEA_LEVEL_CHANGE.name(), COLUMN_SEA_LEVEL);
        columnsMapping.put(Columns.TEMP_CHANGE.name(), COLUMN_TEMPERATURE);
        columnsMapping.put(Columns.VOTES.name(), COLUMN_VOTES);
    }
    
    public static final Map<Property, Integer[]> propertyQueryParametersMapping = new HashMap<Property, Integer[]>();
    
    static {
        propertyQueryParametersMapping.put(Property.CREATOR, new Integer[] {PARAM_CREATOR});
        propertyQueryParametersMapping.put(Property.DESCRIPTION, new Integer[] {PARAM_DESCRIPTION});
        propertyQueryParametersMapping.put(Property.NAME, new Integer[] {PARAM_NAME});
        propertyQueryParametersMapping.put(Property.DATE_FROM, new Integer[] {PARAM_CREATE_DATE_FROM, PARAM_PUBLISH_DATE_FROM});
        propertyQueryParametersMapping.put(Property.DATE_TO, new Integer[] {PARAM_CREATE_DATE_TO, PARAM_PUBLISH_DATE_TO});
        propertyQueryParametersMapping.put(Property.VOTES_FROM, new Integer[] {PARAM_VOTES_FROM});
        propertyQueryParametersMapping.put(Property.VOTES_TO, new Integer[] {PARAM_VOTES_TO});
    }

    /**
     * Ascending sort order.
     */
    public static final String ASC = "asc";

    /**
     * Descending sort order.
     */
    public static final String DESC = "desc";

    /**
     * Array of column names that should be used for sorting default for sorting.
     */
    public static final String[] DEFAULT_SORT_COLUMNS = {COLUMN_NAME, COLUMN_ID};


    public List<PlanItem> getPlans() {

        Session session = openSession();
        String sql = CustomSQLUtil.get(GET_PLAN_ITEMS);
        SQLQuery query = session.createSQLQuery(sql);

        query.addEntity("PlanItem", PlanItemImpl.class);
        return (List<PlanItem>) QueryUtil.list(query,getDialect(),0,Integer.MAX_VALUE);
     }
    
    public List<PlanItem> getPlansForPhase(Long phaseId) {
        Object[] args = new Object[] { phaseId };
        
        List<PlanItem> planItems = null;
        //(List<PlanItem>) 
         //        FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CONTEST_PHASE_ID, args, this);

        if (planItems == null) {

            Session session = openSession();
            String sql = CustomSQLUtil.get(GET_PLANS_FOR_PHASE);
            SQLQuery query = session.createSQLQuery(sql);

            query.addEntity("PlanItem", PlanItemImpl.class);
            query.setLong(0, phaseId);
            planItems = (List<PlanItem>) QueryUtil.list(query,getDialect(),0,Integer.MAX_VALUE);
            
            //FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTEST_PHASE_ID, args, planItems);
        }
        return planItems;
     }


  
    
    public void removePlanWithHistory(long planId) {


        Session session = openSession();
        String sql = CustomSQLUtil.get(REMOVE_PLAN_WITH_HISTORY);
        SQLQuery query = session.createSQLQuery(sql);

        query.setLong(0, planId);
        query.executeUpdate();
     }
    
    

    public int countVotesForPlanType(PlanType type) {
        Session session = openSession();

        String sql = CustomSQLUtil.get(COUNT_VOTE_BY_TYPE);

        SQLQuery query = session.createSQLQuery(sql);

        query.addScalar(COUNT_COLUMN_NAME, Type.LONG);
        query.setLong(PARAM_PLAN_TYPE_ID, type.getPlanTypeId());

        Iterator<Long> itr = query.list().iterator();
        if (itr.hasNext()) {
            Long count = itr.next();

            if (count != null) {
                return count.intValue();
            }
        }

        return 0;

    }



    /**
     * Returns count of plans after applying filters.
     *
     * @param filter fliters that should be applied
     * @return count of plans left in data set after application of filters
     * @throws Exception 
     */
    public int countFilteredPlans(PlansUserSettings planUserSettings) throws Exception {
//        Session session = openSession();
//
//        String sql = CustomSQLUtil.get(COUNT_FILTERED_PLANS);
//
//        sql = addFilterPositionsAndSorting(sql, planUserSettings, null, null);
//        sql = addAttributes(sql, planUserSettings);
//        SQLQuery query = session.createSQLQuery(sql);
//
//        query.addScalar(COUNT_COLUMN_NAME, Type.LONG);
//        addFilterParameters(query, planUserSettings);
//
//        Iterator<Long> itr = query.list().iterator();
//        if (itr.hasNext()) {
//            Long count = itr.next();
//
//            if (count != null) {
//                return count.intValue();
//            }
//        }
        return 0;
    }
    
//    public List<Plan> getFilteredPlans2(PlansFilter filter, int start, int end, String sortColumn, String sortDirection) {
//      DynamicQueryFactoryUtil.forClass(Plan.class).add(criterion);
//      RestrictionsFactoryUtil.
//      
//    }

    /**
     * Returns list of plans that are in data set after application of filters.
     * Returned filters are from positions start to end.
     *
     * @param planUserSettings filters that should be applied
     * @param start position of first plan to be retrieved
     * @param end position of last plan to be retrieved
     * @param sortColumn name of column that should be used for sorting
     * @param sortDirection sorting direction
     * @return list of retrieved plans
     * @throws Exception 
     */
    public List<PlanItem> getFilteredPlans(PlansUserSettings planUserSettings, int start, int end, String sortColumn,
            String sortDirection) throws Exception {
        Session session = openSession();

        String sql = CustomSQLUtil.get(GET_FILTERED_PLANS);

        sql = addFilterPositionsAndSorting(sql, planUserSettings, sortColumn, sortDirection);
        sql = addAttributes(sql, planUserSettings);
        _log.info("Query: "+sql);
        //System.out.println("Query "+sql);
        SQLQuery query = session.createSQLQuery(sql);

        query.addEntity("PlanItem", PlanItemImpl.class);
        query.setLong(PARAM_PLAN_TYPE_ID, planUserSettings.getPlanTypeId());
        //addFilterParameters(query, planUserSettings);

        return (List<PlanItem>) QueryUtil.list(query, getDialect(), start, end);
    }
    
    
    /**
     * Returns count of plans, either published or unpublished.
     *
     * @param published type of plans to be counted
     * @return count of plans with given status
     */
    public int countPlans(long planTypeId) {
        Session session = openSession();

        String sql = CustomSQLUtil.get(COUNT_PLANS);

        SQLQuery query = session.createSQLQuery(sql);

        query.addScalar(COUNT_COLUMN_NAME, Type.LONG);
        query.setLong(PARAM_PLAN_TYPE_ID, planTypeId);

        Iterator<Long> itr = query.list().iterator();
        if (itr.hasNext()) {
            Long count = itr.next();

            if (count != null) {
                return count.intValue();
            }
        }

        return 0;
    }

    /**
     * Returns list of plans with given status (published/unpublished).
     *
     * @param published status of a plans that should be retrieved
     * @param start position of first plan to be retrieved
     * @param end position of last plan to be retrieved
     * @param sortColumn name of column that should be used for sorting
     * @param sortDirection sorting direction
     * @return list of retrieved plans
     * @throws SystemException 
     * @throws NoSuchPlanAttributeFilterException 
     */
    public List<PlanItem> getPlans(long planTypeId, int start, int end, String sortColumn, String sortDirection) throws NoSuchPlanAttributeFilterException, SystemException {
        Session session = openSession();

        String sql = CustomSQLUtil.get(GET_PLANS);
        sql = addAttributes(sql, null);
        sql = addFilterPositionsAndSorting(sql, null, sortColumn, sortDirection);
        SQLQuery query = session.createSQLQuery(sql);

        query.addEntity("PlanItem", PlanItemImpl.class);
        query.setLong(PARAM_PLAN_TYPE_ID, planTypeId);

        return (List<PlanItem>) QueryUtil.list(query, getDialect(), start, end);
    }

    /**
     * Returns position of a plan user has voted on in data set containing all
     * published plans.
     *
     * @param userId id of vote owner
     * @param sortColumn name of column that should be used for sorting
     * @param sortDirection sorting direction
     * @return position of a plan user has voted on
     * @throws SystemException 
     * @throws NoSuchPlanTypeException 
     * @throws NoSuchPlanAttributeFilterException 
     */
    public int getUserVotePosition(long userId, String sortColumn, String sortDirection) throws NoSuchPlanTypeException, SystemException, NoSuchPlanAttributeFilterException {
        PlanType planType = PlanTypeLocalServiceUtil.getDefaultPlanType();
        List<PlanItem> plans = getPlans(planType.getPublishedCounterpartId(),0,Integer.MAX_VALUE,sortColumn,sortDirection);
        PlanVote vote = null;
        try {
            vote = PlanVoteLocalServiceUtil.getPlanVote(new PlanVotePK(userId, 1L));
        } catch (Exception e) {
            _log.warn("Error retrieving vote for user "+userId);
            return -1;
        }
        int count = 0;
        for (PlanItem p:plans) {
            if (vote.getPlanId() == p.getPlanId()) {
                return count;
            }
            count++;
        }
        return -1;
//        Session session = openSession();
//
//        String sql = CustomSQLUtil.get(GET_USER_VOTE_POSITION);
//
//        sql = addFilterPositionsAndSorting(sql, null, sortColumn, sortDirection);
//        sql = addAttributes(sql, null);
//        sql = addUserPositionsConstraints(sql, userId, sortColumn, sortDirection);
//        SQLQuery query = session.createSQLQuery(sql);
//
//        query.addScalar(COUNT_COLUMN_NAME, Type.LONG);
//        query.setBoolean(PARAM_PUBLISHED, true);
//
//        Iterator<Long> itr = query.list().iterator();
//        if (itr.hasNext()) {
//            
//            Long count = itr.next();
//
//            if (count != null) {
//                return count.intValue();
//            }
//        }
//
//        return 0;
        
    }

    /**
     * Returns position of a plan user has voted on in data set that is a
     * result of application of passed filters.
     *
     * @param filter filter that should be used to filter data set
     * @param userId id of vote owner
     * @param sortColumn name of column that should be used for sorting
     * @param sortDirection sorting direction
     * @return position of a plan user has voted on
     * @throws Exception 
     */
    public int getFilteredUserVotePosition(PlansUserSettings planUserSettings, long userId, String sortColumn, String sortDirection) throws Exception {
//        List<PlanItem> plans = getFilteredPlans(planUserSettings,0,Integer.MAX_VALUE,sortColumn,sortDirection);
//        PlanVote vote = null;
//        try {
//            vote = PlanVoteLocalServiceUtil.getPlanVote(userId);
//        } catch (Exception e) {
//            _log.warn("Error retrieving vote for user "+userId);
//            return -1;
//        }
//        int count = 0;
//        for (PlanItem p:plans) {
//            if (vote.getPlanId() == p.getPlanId()) {
//                return count;
//            }
//            count++;
//        }
//        return -1;
        
        return 0;
    }

    /**
     * Returns list of PlanPosition objects for passed plans.
     *
     * @param plans list of plans
     * @return list of PlanPositions for given plans
     */
   /* public List<PlanPosition> getPlansPositions(List<Plan> plans) {

//        if (plans.size() == 0) {
//            return new ArrayList<PlanPosition>();
//        }
//        Session session = openSession();
//
//        String sql = CustomSQLUtil.get(GET_PLANS_POSITIONS);
//
//        StringBuilder plansIdsStr = new StringBuilder();
//        boolean addComa = false;
//        for (Plan plan: plans) {
//            if (addComa) {
//                plansIdsStr.append(", ");
//            }
//            plansIdsStr.append(String.valueOf(plan.getPlanId()));
//            addComa = true;
//        }
//
//        sql = sql.replaceAll(PLAN_IDS_PLACEHOLDER, plansIdsStr.toString());
//
//        SQLQuery query = session.createSQLQuery(sql);
//
//        query.addEntity("Plan", PlanPositionImpl.class);
//
//        return (List<PlanPosition>) query.list();
        return Collections.emptyList();
    }*/
    

    public PlanItem findLatestVersion(Long planId) {
        Session session = openSession();

        String sql = CustomSQLUtil.get(GET_LATEST_VERSION_OF_PLAN);

        SQLQuery query = session.createSQLQuery(sql);

        query.addEntity("PlanItem", PlanItemImpl.class);
        query.setLong(0, planId);

        List<PlanItem> ret =  (List<PlanItem>) QueryUtil.list(query, getDialect(), 0, 1);
        
        return ret.isEmpty() ? null : ret.get(0);
        
    }

    /**
     * Method responsible for adding position filtering and sorting
     * statements to SQL query.
     *
     * @param sql SQL query that should be modified
     * @param filter fliters used to filter available plans
     * @param sortColumn name of column that should be used for sorting
     * @param sortDirection sorting direction
     * @return modified SQL query
     */
    private String addFilterPositionsAndSorting(String sql, PlansUserSettings planUserSettings, String sortColumn,
            String sortDirection) {
        
        if (planUserSettings != null) {
            StringBuilder positionsStr = new StringBuilder();
            boolean addOr = false;
            /*
            for (long positionId: planUserSettings.getPositionsIds()) {
                if (addOr) {
                    positionsStr.append(" OR ");
                }
                positionsStr.append(POSITION_FILTER_STRING);
                positionsStr.append(positionId);
                addOr = true;
            }
            if (planUserSettings.getPositionsIds().size() == 0) {
                positionsStr.append(String.valueOf(true));
            }
            */

            sql = sql.replaceAll(POSITIONS_PLACEHOLDER, positionsStr.toString());
        }

        boolean addComa = false;
        StringBuilder sortColumnsStr = new StringBuilder();
        if (sortColumn != null && sortDirection != null && !sortColumn.equals("") && !sortDirection.equals("")) {
            if (columnsMapping.containsKey(sortColumn)) {
                sortColumn = columnsMapping.get(sortColumn);
            }
            //sortColumnsStr.append(getPrefixedColumn(sortColumn, COLUMN_PREFIX));
            sortColumnsStr.append(sortColumn);
            sortColumnsStr.append(" ");
            sortColumnsStr.append(sortDirection);
            addComa = true;
        }
        for(String column: DEFAULT_SORT_COLUMNS) {
            if (column.equals(sortColumn)) {
                continue;
            }
            if (addComa) {
                sortColumnsStr.append(", ");
            }
            //sortColumnsStr.append(getPrefixedColumn(column, COLUMN_PREFIX));
            sortColumnsStr.append(column);
            sortColumnsStr.append(" " + sortDirection);
            addComa = true;
        }
        return sql.replaceAll(SORT_COLUMNS_PLACEHOLDER, sortColumnsStr.toString());
    }
    
    
    /**
     * Method responsible for adding constraints that should be used for
     * retrieval of user vote position in current data set.
     *
     * @param sql SQL query that should be modified
     * @param userId id of a user
     * @param sortColumn name of column that should be used for sorting
     * @param sortDirection sorting direction
     * @return modified SQL query
     */
    private String addUserPositionsConstraints(String sql, long userId, String sortColumn, String sortDirection) {
        sql = sql.replaceAll(USER_ID_PLACEHOLDER, String.valueOf(userId));

        StringBuilder constraintsStr = new StringBuilder();
        constraintsStr.append("(");
        String column = null;
        if (sortColumn != null && sortDirection != null && !sortColumn.equals("") && !sortDirection.equals("")) {
            constraintsStr.append(getPrefixedColumn(sortColumn, COLUMN_PREFIX));
            if (sortDirection.equals(DESC)) {
                constraintsStr.append(" > ");
            } else {
                constraintsStr.append(" < ");
            }
            constraintsStr.append(getPrefixedColumn(sortColumn, USER_VOTE_PREFIX));
            column = sortColumn;
        } else {
            constraintsStr.append(getPrefixedColumn(DEFAULT_SORT_COLUMNS[0], COLUMN_PREFIX));
            constraintsStr.append(" < ");
            constraintsStr.append(getPrefixedColumn(DEFAULT_SORT_COLUMNS[0], USER_VOTE_PREFIX));
            column = DEFAULT_SORT_COLUMNS[0];
        }
        constraintsStr.append(" OR (");
        constraintsStr.append(getPrefixedColumn(column, COLUMN_PREFIX));
        constraintsStr.append(" = ");
        constraintsStr.append(getPrefixedColumn(column, USER_VOTE_PREFIX));
        constraintsStr.append(" AND ");
        constraintsStr.append(getPrefixedColumn(COLUMN_ID, COLUMN_PREFIX));
        constraintsStr.append(" <= ");
        constraintsStr.append(getPrefixedColumn(COLUMN_ID, USER_VOTE_PREFIX));
        constraintsStr.append("))");

        return sql.replaceAll(USER_VOTE_CONSTRAINTS_PLACEHOLDER, constraintsStr.toString());
    }

    private String addAttributes(String sql, PlansUserSettings planUserSettings) throws NoSuchPlanAttributeFilterException, SystemException {

        StringBuilder attNameString = new StringBuilder();
        StringBuilder attValueString = new StringBuilder();
        int count = 0;
        
        
        for (PlanConstants.Attribute att:PlanConstants.Attribute.values()) {
            String atttable = "PA"+count;
            attNameString.append((count>0?", ":"")+atttable+".attributeValue AS Attribute"+att.name()+"");
            attValueString.append((att.isFiltered()&&planUserSettings!=null?"INNER":"LEFT")+" JOIN PlanAttribute as "+atttable+" ON PlanItem.planId="+atttable+".planId ");
            if (planUserSettings==null || !att.isFiltered()) {
                attValueString.append(" AND "+atttable+".attributeName='"+att.name()+"' ");
                
            } else {
                StringBuilder attString = new StringBuilder();
                att.getFilterString(attString,planUserSettings);
                String tmp = attString.toString();
                tmp  = tmp.replaceAll("attributeName",atttable+".attributeName");
                tmp = tmp.replaceAll("attributeValue",atttable+".attributeValue");

                attValueString.append(" AND ");
                attValueString.append(tmp);
                attValueString.append(" ");
            }
            count++;
        }
            
        sql = sql.replace(PLAN_ATTRIBUTE_NAME_PLACEHOLDER,attNameString.toString());
        return sql.replace(PLAN_ATTRIBUTE_VALUE_PLACEHOLDER,attValueString.toString());
        
    }

    
    
    /**
     * Adds filter parameters to passed query.
     *
     * @param query which placeholders should be filled with filter parameters
     * @param filter plans filter
     * @throws Exception 
     */
    private void addFilterParameters(SQLQuery query, PlansUserSettings planUserSettings) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        query.setLong(PARAM_PLAN_TYPE_ID, planUserSettings.getPlanTypeId());

        for (Property property: PlanConstants.Property.values()) {
            if (! propertyQueryParametersMapping.containsKey(property)) {
                continue;
            }

            for (int idx: propertyQueryParametersMapping.get(property)) {
                if (property.getValueType().equals(String.class)) {
                    query.setString(
                            idx, 
                            (String) property.getPropertyFilterValue(planUserSettings));
                }
            
                else if (property.getValueType().equals(Double.class)) {
                    query.setDouble(
                            idx, 
                            (Double) property.getPropertyFilterValue(planUserSettings));
                }
                else {
                    throw new Exception("Unknown property type " + property + ": " + property.getValueType());
                }
                
            }
            /*
            query.setString(PARAM_NAME, prepareSearchString(filter.getName()));
            query.setString(PARAM_DESCRIPTION, prepareSearchString(filter.getDescription()));
            query.setString(PARAM_CREATOR, prepareSearchString(filter.getCreator()));
            query.setDouble(PARAM_VOTES_FROM, filter.getVotesFrom());
            query.setDouble(PARAM_VOTES_TO, filter.getVotesTo());
            query.setString(PARAM_CREATE_DATE_FROM, dateFormat.format(filter.getDateFrom()));
            query.setString(PARAM_CREATE_DATE_TO, dateFormat.format(filter.getDateTo()));
            query.setString(PARAM_PUBLISH_DATE_FROM, dateFormat.format(filter.getDateFrom()));
            query.setString(PARAM_PUBLISH_DATE_TO, dateFormat.format(filter.getDateTo()));
*/
        }

        
        //if (planUserSettings.getPositionsIds().size() > 0) {
        //    query.setBoolean(PARAM_FILTER_POSITIONS, true);
        //} else {
            query.setBoolean(PARAM_FILTER_POSITIONS, false);
        //}
        //if (planUserSettings.getFilterPositionsAll()) {
        //    query.setInteger(PARAM_POSITIONS_COUNT, planUserSettings.getPositionsIds().size());
        //} else {
            query.setInteger(PARAM_POSITIONS_COUNT, POSITIONS_FILTER_ANY);
        //}
    }

    /**
     * Method modifies passed string so it can be used with "LIKE" operator.
     * It replaces all white spaces with "%" signs and adds % to the begining
     * and end of a string.
     *
     * @param base base string
     * @return modified string
     */
    private String prepareSearchString(String base) {
        StringBuilder searchString = new StringBuilder();
        searchString.append("%");
        searchString.append(base.replaceAll("\\ ", "%"));
        searchString.append("%");
        return searchString.toString();
    }

    /**
     * Returns string representing column with given prefix.
     * @param column column to be prefixed
     * @param prefix prefix to be added
     * @return prefixed column
     */
    private String getPrefixedColumn(String column, String prefix) {
        return prefix + column;
    }
    
    
    public void clearPhaseCache(Long contestPhasePk) {
        Object[] args = new Object[] { contestPhasePk };
        
        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTEST_PHASE_ID, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTEST_PHASE_ID, args);
    }
    
}
