package com.ext.utils.userInput;

import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.owasp.validator.html.Policy;

public class PolicyFct {
    private Policy policy;
    private String policyFile;
    
    
    public Policy getPolicy() {
        return policy;
    }

    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isBlank(policyFile)) {
            throw new RuntimeException("Policy file can't be null");
        }
        URL policyFileUrl = PolicyFct.class.getClassLoader().getResource(policyFile);
        
        if (policyFileUrl == null) {
            throw new RuntimeException("Can't find policy file: " + policyFile);
        }
        
        policy = Policy.getInstance(policyFileUrl.getFile());

    }

    public void setPolicyFile(String policyFile) {
        this.policyFile = policyFile;
    }

    public String getPolicyFile() {
        return policyFile;
    }

}                                                                                       