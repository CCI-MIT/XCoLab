package com.ext.portlet.contests;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseColumn;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

public class ContestPhaseHelper {
    
    public static List<Columns> getPhaseColumns(ContestPhase phase) throws SystemException {
        List<Columns> columns = new ArrayList<Columns>();
        for (String column: ContestPhaseLocalServiceUtil.getPhaseColumns(phase)) {
            columns.add(Columns.valueOf(column));
        }
        return columns;
    }
    
    public static Columns getDefaultSortPhaseColumn(ContestPhase phase) throws SystemException {
        String sortColumn = PlanConstants.Columns.UPDATE_DATE.name();
        //String sortColumn = phase.getPhaseColumnsRaw().get(0).getColumnName();
        for (ContestPhaseColumn column: ContestPhaseLocalServiceUtil.getPhaseColumnsRaw(phase)) {
            if (column.getDefaultSort()) {
                sortColumn = column.getColumnName();
            }
        }
        return Columns.valueOf(sortColumn);
    }

}
