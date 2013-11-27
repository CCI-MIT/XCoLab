package org.xcolab.hooks.climatecolab.wiki;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class GetWIkiImageFixRequestWrapper extends HttpServletRequestWrapper {
	private final static String FILE_NAME_PARAM = "fileName";

	public GetWIkiImageFixRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		if (name.equals(FILE_NAME_PARAM)) {
			String param = super.getParameter(FILE_NAME_PARAM);
			int pos = param.indexOf("/");
			if (pos >= 0) {
				return param.substring(pos+1);
			}
		}
		
		return super.getParameter(name);
	}
	

}
