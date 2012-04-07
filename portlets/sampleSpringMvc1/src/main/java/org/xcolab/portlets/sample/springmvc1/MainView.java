package org.xcolab.portlets.sample.springmvc1;

import java.util.Collections;
import java.util.Date;
import java.util.regex.Pattern;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.services.sample.model.SampleEntity;
import org.xcolab.services.sample.service.SampleEntityLocalServiceUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("VIEW")
public class MainView {

	@RequestMapping
	public String test(Model model, PortletRequest request, @RequestParam(value="entryId", required=false) String jspPage) throws SystemException {
		/*
    	SampleEntity entity = SampleEntityLocalServiceUtil.createSampleEntity(CounterLocalServiceUtil.increment(SampleEntity.class.getName()));
    	entity.setContent("This is content!");
    	SampleEntityLocalServiceUtil.addSampleEntity(entity);
    	*/
		SampleEntity e = SampleEntityLocalServiceUtil.createSampleEntity(CounterLocalServiceUtil.increment(SampleEntity.class.getName()));
		e.setContent("this is content!");
		e.setCreated(new Date());
		
		e.persist();
		
		
		System.out.println("fasdfasdf".matches("[^/\\.]+"));
		
		
		
		System.out.println(request.getParameterMap());
		System.out.println("Jsp page: " + jspPage);
		
		
		
		
    	
    	System.out.println("ok ... jest nieźle");
    	
		return "view";
	}
	

}
