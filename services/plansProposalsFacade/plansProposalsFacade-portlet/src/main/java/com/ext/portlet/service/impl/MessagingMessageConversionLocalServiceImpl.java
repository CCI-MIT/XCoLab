package com.ext.portlet.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.model.MessagingMessage;
import com.ext.portlet.model.MessagingMessageConversion;
import com.ext.portlet.model.MessagingMessageConversionType;
import com.ext.portlet.service.MessagingMessageConversionLocalServiceUtil;
import com.ext.portlet.service.MessagingMessageConversionTypeLocalServiceUtil;
import com.ext.portlet.service.MessagingMessageLocalServiceUtil;
import com.ext.portlet.service.base.MessagingMessageConversionLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the messaging message conversion local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.MessagingMessageConversionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.MessagingMessageConversionLocalServiceBaseImpl
 * @see com.ext.portlet.service.MessagingMessageConversionLocalServiceUtil
 */
public class MessagingMessageConversionLocalServiceImpl
    extends MessagingMessageConversionLocalServiceBaseImpl {
    private static final String PROPERTY_IP_ADDRESS = "ipAddress";
    private static final String PROPERTY_MESSAGE_ID = "messageId";
    
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.MessagingMessageConversionLocalServiceUtil} to access the messaging message conversion local service.
     */
    public int countByType(Long messageId, MessagingMessageConversionType type) throws SystemException {
        return this.getMessagingMessageConversionPersistence().countByfindByType(messageId, type.getTypeId());
    }
    
    public int countByType(Long messageId, String typeName) throws SystemException {
        MessagingMessageConversionType type = MessagingMessageConversionTypeLocalServiceUtil.getByName(typeName);
        return this.getMessagingMessageConversionPersistence().countByfindByType(messageId, type.getTypeId());
    }
    

    public void addConversion(Long messageId, String typeName, String clientIP, 
            Object extraData, Object extraData2)
            throws SystemException, PortalException {

        Date now = new Date();

        MessagingMessage message = MessagingMessageLocalServiceUtil.getMessagingMessage(messageId);

        MessagingMessageConversionType type = MessagingMessageConversionTypeLocalServiceUtil.getByName(typeName);

        Long conversionId = CounterLocalServiceUtil.increment(MessagingMessageConversion.class.getName());

        MessagingMessageConversion conversion = MessagingMessageConversionLocalServiceUtil
                .createMessagingMessageConversion(conversionId);

        conversion.setConversionTypeId(type.getTypeId());
        conversion.setMessageId(message.getMessageId());
        conversion.setIpAddress(clientIP);
        conversion.setCreateDate(now);

        if (extraData != null) {
            conversion.setExtraData(extraData.toString());
        }
        if (extraData2 != null) {
            conversion.setExtraData2(extraData2.toString());
        }
        MessagingMessageConversionLocalServiceUtil.addMessagingMessageConversion(conversion);

        message.setConversionCount(message.getConversionCount() + 1);
        message.setModifiedDate(now);
        MessagingMessageLocalServiceUtil.updateMessagingMessage(message);

    }
    
    public int countConversionsByIP(Long messageId, String typeName) throws SystemException {
        
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MessagingMessageConversion.class);
        Criterion criterionMessageId = RestrictionsFactoryUtil.eq(PROPERTY_MESSAGE_ID, messageId);
        Projection groupByIPAddress = ProjectionFactoryUtil.groupProperty(PROPERTY_IP_ADDRESS);
        Projection count = ProjectionFactoryUtil.count(PROPERTY_IP_ADDRESS);
        
        MessagingMessageConversionType type = MessagingMessageConversionTypeLocalServiceUtil.getByName(typeName);
        Criterion criterionConversionType = RestrictionsFactoryUtil.eq("conversionTypeId", type.getTypeId());
        
        
        ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(groupByIPAddress);
        projectionList.add(count);
        
        dynamicQuery.add(criterionMessageId);
        dynamicQuery.add(criterionConversionType);
        dynamicQuery.setProjection(projectionList);
        
        List<Object> result = MessagingMessageConversionLocalServiceUtil.dynamicQuery(dynamicQuery);
        return result.size();
        
    }
    
    public int countConversionsByRecipient(Long messageId, String typeName) throws SystemException {
        
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MessagingMessageConversion.class);
        Criterion criterionMessageId = RestrictionsFactoryUtil.eq(PROPERTY_MESSAGE_ID, messageId);
        
        MessagingMessageConversionType type = MessagingMessageConversionTypeLocalServiceUtil.getByName(typeName);
        Criterion criterionConversionType = RestrictionsFactoryUtil.eq("conversionTypeId", type.getTypeId());
        Projection groupByIPAddress = ProjectionFactoryUtil.groupProperty("extraData2");
        
        ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(groupByIPAddress);
        
        dynamicQuery.add(criterionMessageId);
        dynamicQuery.add(criterionConversionType);
        dynamicQuery.setProjection(projectionList);
        
        List<Object> result = MessagingMessageConversionLocalServiceUtil.dynamicQuery(dynamicQuery);
        return result.size();
    }
    

}
