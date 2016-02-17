package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchEmailListException;
import com.ext.portlet.model.EmailList;
import com.ext.portlet.service.base.EmailListLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The implementation of the email list local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.EmailListLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.EmailListLocalServiceBaseImpl
 * @see com.ext.portlet.service.EmailListLocalServiceUtil
 */
public class EmailListLocalServiceImpl extends EmailListLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.EmailListLocalServiceUtil} to access the email list local service.
     */
    
    @Override
    public EmailList findByListNameEmailAddress(String listName, String emailAddress) throws NoSuchEmailListException, SystemException {
        return emailListPersistence.findByfindByNameEmail(listName, emailAddress);
    }
    
    @Override
    public List<EmailList> findByListName(String listName) throws NoSuchEmailListException, SystemException {
        return emailListPersistence.findByfindByName(listName);
    }
}
