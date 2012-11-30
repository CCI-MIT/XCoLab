package com.ext.utils.userInput;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;

public class UserInputFilterImpl implements IUserInputFilter {
    
    
    private AntiSamy as;
    
    public UserInputFilterImpl(com.ext.utils.userInput.PolicyFct policyFactoryBean) throws Exception {
        as = new AntiSamy(policyFactoryBean.getPolicy());
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

}
