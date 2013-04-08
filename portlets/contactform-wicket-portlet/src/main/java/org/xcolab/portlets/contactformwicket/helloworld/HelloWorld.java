/**
 * 
 */
package org.xcolab.portlets.contactformwicket.helloworld;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

/**
 * @author pdeboer
 * 
 */
public class HelloWorld extends WebPage {
	Form<FormModel> form;

	/**
	 * 
	 */
	private static final long serialVersionUID = 6169731898602021560L;

	/**
	 * 
	 */
	public HelloWorld() {
		form = new ContactForm("contactForm");
		add(form);
	}

	private class ContactForm extends Form<FormModel> {

		/**
		 * @param id
		 */
		public ContactForm(String id) {
			super(id, new CompoundPropertyModel<>(new FormModel()));

			add(new TextField<String>("name").setRequired(true).setLabel(
					new Model<String>("Name")));
			
			add(new TextField<String>("email").setRequired(true).setLabel(
					new Model<String>("E-Mail")));
			
			add(new TextArea<String>("message").setRequired(true).setLabel(
					new Model<String>("Message")));
			
			add(new SubmitLink("submit"));
		}
		
		/* (non-Javadoc)
		 * @see org.apache.wicket.markup.html.form.Form#onSubmit()
		 */
		@Override
		protected void onSubmit() {
			System.out.println("submitting: "+getModelObject().getName());
			super.onSubmit();
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	}

}
