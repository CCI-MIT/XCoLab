package com.ext.utils.userInput;

import org.owasp.validator.html.Policy;
import org.springframework.core.io.Resource;

public class PolicyFct {
    Policy policy;

    Resource policyFile;
    
    
    public Policy getPolicy() {
        return policy;
    }

    public void afterPropertiesSet() throws Exception {
        policy = Policy.getInstance(policyFile.getFile());
    }

    public void setPolicyFile(Resource policyFile) {
        this.policyFile = policyFile;
    }

    public Resource getPolicyFile() {
        return policyFile;
    }

}                                                                                       