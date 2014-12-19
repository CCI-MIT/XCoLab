package com.ext.utils.userInput;

import java.lang.reflect.Method;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;

import com.liferay.portal.service.InvokableLocalService;

public class UserInputFilterImpl implements UserInputFilter, InvokableLocalService {
    
    
    private AntiSamy as;
    
    public UserInputFilterImpl() {
    }
    
    public void setPolicyFactory(com.ext.utils.userInput.PolicyFct policyFactoryBean) throws Exception {
        as = new AntiSamy(policyFactoryBean.getPolicy());
    }
    
    public void init() {
        
    }

    @Override
    public String filterHtml(String html) throws UserInputException {
        try {CleanResults cr = as.scan(html);
            if (cr != null) {
                return cr.getCleanHTML();
            }
            return "";
        } catch (ScanException e) {
            throw new UserInputException("Can't process user input", e);
        } catch (PolicyException e) {
            throw new UserInputException("Can't process user input", e);
        }
    }

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
			Object[] arguments) throws Throwable {
		Class[] parameterTypesClass = new Class[parameterTypes.length];
		for (int i=0; i < parameterTypes.length; i++) {
			parameterTypesClass[i] = getClass().getClassLoader().loadClass(parameterTypes[i]);
		}
		
		Method m = this.getClass().getMethod(name, parameterTypesClass);
		Object ret = m.invoke(this, arguments);
		return ret;
	}

}
