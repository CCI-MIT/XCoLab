package com.ext.utils;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.ext.portlet.NoSuchEmailListException;
import com.ext.portlet.model.EmailList;
import com.ext.portlet.service.EmailListLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class EmailListServlet extends HttpServlet {

    private final static String PARAM_EMAIL_ADDRESS = "emailAddress";
    private final static String PARAM_LIST_NAME = "listName";
    private final static Log _log = LogFactoryUtil.getLog(EmailListServlet.class);


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl = req.getRequestURL().toString();
        
        if (requestUrl.endsWith("/api/addEmailToList")) {
            addEmailAddressToList(req, resp);
        }
        else if (requestUrl.endsWith("/api/getEmailsInList")) {
            getEmailsInList(req, resp);
        }
        else if (requestUrl.endsWith("/api/getListNames")) {
            getListNames(req, resp);
        }
        else {
            _log.error("Unknown api request: " + requestUrl);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        return;
    }
    
    
    private void getListNames(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Set<String> listNames = new TreeSet<>();
        try {
            for (EmailList email: EmailListLocalServiceUtil.getEmailLists(0, Integer.MAX_VALUE)) {
                listNames.add(email.getName());
            }
        }
        catch (SystemException e) {
            _log.error(e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        for (String name: listNames) {
            resp.getWriter().append(name);
            resp.getWriter().append("\n");
        }
        resp.setStatus(HttpServletResponse.SC_OK);
    }


    private void getEmailsInList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String listName = req.getParameter(PARAM_LIST_NAME);

        try {
            for (EmailList email: EmailListLocalServiceUtil.findByListName(listName)) {
                resp.getWriter().append(email.getEmail());
                resp.getWriter().append("\n");
            }
        }
        catch (SystemException e) {
            _log.error(e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        } catch (NoSuchEmailListException e) {
            _log.error(e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        
    }


    private void addEmailAddressToList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String listName = req.getParameter(PARAM_LIST_NAME);
        String emailAddress = req.getParameter(PARAM_EMAIL_ADDRESS);
        
        if (StringUtils.isBlank(listName)) {
            _log.error("List name can't be null/empty");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        if (StringUtils.isBlank(emailAddress)) {
            _log.error("Email address can't be null/empty");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        
        try {
            EmailListLocalServiceUtil.findByListNameEmailAddress(listName, emailAddress);
        }
        catch (NoSuchEmailListException e) {
            try {
                EmailList list = EmailListLocalServiceUtil.createEmailList(CounterLocalServiceUtil.increment(EmailList.class.getName()));
                list.setEmail(emailAddress);
                list.setName(listName);
                EmailListLocalServiceUtil.addEmailList(list);
            } catch (SystemException e1) {
                _log.error(e1);
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        } catch (SystemException e) {
            _log.error(e);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        resp.setStatus(HttpServletResponse.SC_OK);
        
    }

}
