package org.xcolab.hooks.climatecolab.flagging;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.flagging.FlaggingClient;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.util.enums.flagging.TargetType;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FlaggingReportFilter implements Filter {

    private static final Logger _log = LoggerFactory.getLogger(FlaggingReportFilter.class);

    private void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JSONObject json = new JSONObject();

        try {
            request.setCharacterEncoding("UTF-8");
            long userId = MemberAuthUtil.getMemberId(request);

            TargetType targetType = TargetType.valueOf(request.getParameter("targetType"));
            long targetId = Long.valueOf(request.getParameter("targetId"));
            long targetAdditionalId = Long.valueOf(request.getParameter("targetAdditionalId"));
            String reason = request.getParameter("reason");
            String comment = request.getParameter("comment");
            FlaggingClient.report(MembersClient.getMember(userId), targetId,
                    targetAdditionalId, targetType, reason, comment);
            json.put("success", true);
        } catch (MemberNotFoundException | NumberFormatException e) {
            _log.warn("", e);
            json.put("success", false);
        }

        response.setContentType("application/json");
        response.getWriter().write(json.toString());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        doPost((HttpServletRequest) request, (HttpServletResponse) response);
    }

    @Override
    public void destroy() {

    }
}
