package org.xcolab.hooks.climatecolab.filtering;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.filtering.FilteringClient;
import org.xcolab.client.filtering.enums.FilteringSource;
import org.xcolab.client.filtering.enums.FilteringStatus;
import org.xcolab.client.filtering.pojo.FilteredEntry;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfanityFiltering implements Filter {

    private static final Log _log = LogFactoryUtil.getLog(ProfanityFiltering.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        doGet((HttpServletRequest) request, (HttpServletResponse) response);

    }

    @Override
    public void destroy() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        //ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        //Long memberId = themeDisplay.getUserId();
        String fullText = request.getParameter("fullText");
        String source = request.getParameter("source");


        FilteredEntry filteredEntry = new FilteredEntry();
        //filteredEntry.setAuthorId(memberId);
        filteredEntry.setFullText(fullText);
        if (source.equals(FilteringSource.DISCUSSION.getSource())){
            filteredEntry.setSource(FilteringSource.DISCUSSION.getId());
        }else{
            filteredEntry.setSource(FilteringSource.PROPOSAL.getId());
        }

        filteredEntry = FilteringClient.createFilteredEntry(filteredEntry);

        try {
            response.getWriter().append("{\"valid\": \"")
                    .append(Boolean.toString(
                            filteredEntry.getStatus().equals(FilteringStatus.APPROVED.getId())))
                    .append("\",\"uuid\":\"").append(filteredEntry.getUuid()).append("\"}");
            response.getWriter().close();
        } catch (IOException e) {
            _log.error("Could not write to response in profanity filter", e);
        }
    }
}
