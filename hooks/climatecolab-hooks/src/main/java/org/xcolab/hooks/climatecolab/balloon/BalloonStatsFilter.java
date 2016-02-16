package org.xcolab.hooks.climatecolab.balloon;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ext.portlet.model.BalloonStatsEntry;
import com.ext.portlet.service.BalloonStatsEntryLocalServiceUtil;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;

public class BalloonStatsFilter implements Filter {

    private static final String STATS_ID = "statsId";
    private static final String CONTEST_ID = "contestId";
    private final static Log _log = LogFactoryUtil.getLog(BalloonStatsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        Long contestId = ParamUtil.getLong(httpReq, CONTEST_ID);
        Long statsId = ParamUtil.getLong(httpReq, STATS_ID);
        try {
            BalloonStatsEntry entry = BalloonStatsEntryLocalServiceUtil.getBalloonStatsEntry(statsId);
            if (contestId.equals(entry.getFirstContestId())) {
                entry.setChoosenContest(entry.getChoosenContest() | 1);
            } else {
                entry.setChoosenContest(entry.getChoosenContest() | 2);
            }
            BalloonStatsEntryLocalServiceUtil.store(entry);
        } catch (SystemException | PortalException e) {
            _log.error(e);
            return;

        }

        HttpServletResponse httpResp = (HttpServletResponse) response;
        httpResp.sendRedirect(ContestLocalServiceUtil.getContestLinkUrl(contestId));

    }

    @Override
    public void destroy() {

    }

}
