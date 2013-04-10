/**
 * 
 */
package org.xcolab.portlets.contactformwicket.helloworld;

import org.apache.wicket.util.io.IClusterable;

/**
 * @author pdeboer
 *
 */
public class FormModel implements IClusterable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 47524184094708215L;
	
	private String name;
	private String email;
	private String message;

	/**
	 * 
	 */
	public FormModel() {
	}

	/**
	 * @return the name
	 */
	public String getName() {
		System.out.println("getter called: "+name);
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		System.out.println("setter called: "+name);
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
