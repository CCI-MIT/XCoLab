package org.xcolab.view.filters.filtering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.filtering.FilteringClient;
import org.xcolab.client.filtering.enums.FilteringSource;
import org.xcolab.client.filtering.enums.FilteringStatus;
import org.xcolab.client.filtering.pojo.FilteredEntry;
import org.xcolab.view.auth.MemberAuthUtil;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfanityFilteringFilter implements Filter {

    private static final Logger _log = LoggerFactory.getLogger(ProfanityFilteringFilter.class);

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

        long memberId = MemberAuthUtil.getMemberId(request);
        String fullText = request.getParameter("fullText");
        String source = request.getParameter("source");


        FilteredEntry filteredEntry = new FilteredEntry();
        filteredEntry.setAuthorId(memberId);
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
