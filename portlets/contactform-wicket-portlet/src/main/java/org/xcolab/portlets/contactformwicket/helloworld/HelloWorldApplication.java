/**
 * 
 */
package org.xcolab.portlets.contactformwicket.helloworld;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * @author pdeboer
 *
 */
public class HelloWorldApplication extends WebApplication {

	/**
	 * 
	 */
	public HelloWorldApplication() {
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends Page> getHomePage() { 
		return HelloWorld.class;
	}

}
