package org.xcolab.hooks.climatecolab;

import java.util.Arrays;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;

public class TestStartupAction extends SimpleAction {

    @Override
    public void run(String[] ids) throws ActionException {
        
        System.out.println("ids?" + Arrays.toString(ids));

    }

}
