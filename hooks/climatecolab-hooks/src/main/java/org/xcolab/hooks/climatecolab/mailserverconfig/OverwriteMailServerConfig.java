package org.xcolab.hooks.climatecolab.mailserverconfig;


import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.PortalPreferences;
import com.liferay.portal.service.PortalPreferencesLocalServiceUtil;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OverwriteMailServerConfig extends SimpleAction {

    @Override
    public void run(String[] ids) {
        String root = PropsUtil.get("liferay.home");

        //check if the send emails file exists
        if (new File(root+"/.xcolab.sendmails").isFile()) {
            //we are in production. do nothing
        } else {
            //overwrite the email configuration in the database

            final DynamicQuery mailPreferences = DynamicQueryFactoryUtil.forClass(PortalPreferences.class);
            mailPreferences.add(
                    PropertyFactoryUtil.forName("ownerId").eq(0L)).add(
                    PropertyFactoryUtil.forName("ownerType").eq(1)
            );

            try {
                List<PortalPreferences> result = PortalPreferencesLocalServiceUtil.dynamicQuery(mailPreferences);
                if (result.size() == 1) {
                    PortalPreferences p = result.get(0);
                    String prefs = p.getPreferences();

                    //replace the smtp mail server entry
                    Pattern smtpServerPattern = Pattern.compile("<preference><name>mail.session.mail.smtp.host</name><value>(.*?)</value></preference>");
                    Matcher m = smtpServerPattern.matcher(prefs);
                    String newPrefs = m.replaceAll("<preference><name>mail.session.mail.smtp.host</name><value>example.com</value></preference>");

                    p.setPreferences(newPrefs);
                    PortalPreferencesLocalServiceUtil.updatePortalPreferences(p);

                    //clear cache
                    MultiVMPoolUtil.clear();
                    CacheRegistryUtil.clear();

                    System.out.println("INFO - OverwriteMailServerConfig overwrote the SMTP mail server configuration because we are not on production.");
                } else {
                    System.out.println("WARNING - OverwriteMailServerConfig found too many matching PortalPreferences (ownerId=0, ownerType=1).");
                }
            } catch (SystemException e) {
                e.printStackTrace();
            }
        }
    }

}