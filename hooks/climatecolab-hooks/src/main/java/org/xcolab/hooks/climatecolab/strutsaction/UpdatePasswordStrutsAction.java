package org.xcolab.hooks.climatecolab.strutsaction;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Ticket;
import com.liferay.portal.model.TicketConstants;
import com.liferay.portal.service.TicketLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by kmang on 18/04/14.
 */
public class UpdatePasswordStrutsAction extends BaseStrutsAction {

	@Override
	public String execute(StrutsAction originalStrutsAction, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Ticket ticket = getTicket(request);

		if ((ticket == null)) {
			SessionErrors.add(request, "errorMessage", "Your ticket has expired. Please try to reset your password again.");
			// Do a response to the login-register portlet here
			response.sendRedirect("/web/guest/loginregister/-/login/forgotPassword/error");
			return "";
		}

		return originalStrutsAction.execute(request, response);
	}

	/**
	 * Copied from {@link com.liferay.portal.action.UpdatePasswordAction}
	 */
	protected Ticket getTicket(HttpServletRequest request) {
		String ticketKey = ParamUtil.getString(request, "ticketKey");

		if (Validator.isNull(ticketKey)) {
			return null;
		}

		try {
			Ticket ticket = TicketLocalServiceUtil.getTicket(ticketKey);

			if (ticket.getType() != TicketConstants.TYPE_PASSWORD) {
				return null;
			}

			if (!ticket.isExpired()) {
				return ticket;
			}

			TicketLocalServiceUtil.deleteTicket(ticket);
		}
		catch (Exception e) {
		}

		return null;
	}
}