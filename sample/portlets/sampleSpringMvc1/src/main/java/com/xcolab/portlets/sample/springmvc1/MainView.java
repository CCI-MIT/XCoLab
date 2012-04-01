package com.xcolab.portlets.sample.springmvc1;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.xcolab.services.sample.model.SampleEntity;
import com.xcolab.services.sample.service.SampleEntityLocalServiceUtil;

@Controller
@RequestMapping("VIEW")
public class MainView {

	@RequestMapping
	public String test(Model model) throws SystemException {
		/*
    	SampleEntity entity = SampleEntityLocalServiceUtil.createSampleEntity(CounterLocalServiceUtil.increment(SampleEntity.class.getName()));
    	entity.setContent("This is content!");
    	SampleEntityLocalServiceUtil.addSampleEntity(entity);
    	*/
		SampleEntity e = SampleEntityLocalServiceUtil.createSampleEntity(-1);
		e.setContent("this is content!");
		e.setCreated(new Date());
		
		e.persist();
		
		
		model.addAttribute("sampleEntities", SampleEntityLocalServiceUtil.getAllEntities());
		SampleEntityLocalServiceUtil.printSomething();
		SampleEntityLocalServiceUtil.printNotSomething();
		
		SampleEntityLocalServiceUtil.printSomethingElse();
		
    	
    	System.out.println("ok ... jest nieźle");
		
		
		return "view";
	}
	

}
