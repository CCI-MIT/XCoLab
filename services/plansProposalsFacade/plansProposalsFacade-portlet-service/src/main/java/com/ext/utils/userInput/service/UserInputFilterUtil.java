package com.ext.utils.userInput.service;

import com.ext.utils.userInput.IUserInputFilter;
import com.ext.utils.userInput.UserInputException;

public class UserInputFilterUtil {
    private static IUserInputFilter _service;
    
    
    
    public static IUserInputFilter getService() {
        if (_service == null) {
            throw new RuntimeException("IUserInputFilter is not set");
        }

        return _service;
    }

    public void setService(IUserInputFilter service) {
        _service = service;
    }

    public static String filterHtml(String html) throws UserInputException {
        return _service.filterHtml(html);
    }
}
