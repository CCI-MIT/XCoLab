package com.ext.portlet.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.model.DiscussionMessageFlag;
import com.ext.portlet.service.base.DiscussionMessageFlagLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the discussion message flag local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.DiscussionMessageFlagLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.DiscussionMessageFlagLocalServiceBaseImpl
 * @see com.ext.portlet.service.DiscussionMessageFlagLocalServiceUtil
 */
public class DiscussionMessageFlagLocalServiceImpl
    extends DiscussionMessageFlagLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.DiscussionMessageFlagLocalServiceUtil} to access the discussion message flag local service.
     */

    public List<DiscussionMessageFlag> findMessageFlags(Long messageId) throws SystemException {
        return discussionMessageFlagPersistence.findByMessageId(messageId);
    }
    
    public DiscussionMessageFlag createFlag(Long messageId, String flagType, String data, Long userId) throws SystemException {
        Long pk = CounterLocalServiceUtil.increment(DiscussionMessageFlag.class.getName());
        DiscussionMessageFlag flag = createDiscussionMessageFlag(pk);
        
        flag.setMessageId(messageId);
        flag.setData(data);
        flag.setUserId(userId);
        flag.setFlagType(flagType);
        flag.setCreated(new Date());
        
        addDiscussionMessageFlag(flag);
        
        return flag;
    }
}
