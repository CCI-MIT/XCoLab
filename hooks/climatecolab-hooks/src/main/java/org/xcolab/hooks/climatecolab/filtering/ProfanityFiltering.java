package org.xcolab.hooks.climatecolab.filtering;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

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

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Long memberId = themeDisplay.getUserId();
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
            response.getWriter().append(String.format("{\"valid\": \""+filteredEntry.getStatus().equals(FilteringStatus.APPROVED.getId())+"\",\"uuid\":\""+filteredEntry+"\""));
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
