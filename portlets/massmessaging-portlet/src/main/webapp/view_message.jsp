<%@ include file="/init.jsp"%>
<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%

Long messageId = ParamUtil.getLong(renderRequest, "messageId");

MessagingMessage message = MessagingMessageLocalServiceUtil.createMessagingMessage(0L);
List < MessagingMessageConversionType > conversionTypes = 
    MessagingMessageConversionTypeLocalServiceUtil.getMessagingMessageConversionTypes(0, 
            MessagingMessageConversionTypeLocalServiceUtil.getMessagingMessageConversionTypesCount());
int recipientsConversion = 0;
int emailsOpenedUniqueIP = 0;
int linksClickUniqueIP = 0;
int registrationCount = MessagingMessageConversionLocalServiceUtil.countByType(messageId, MessagingConversionTypes.USER_REGISTERED.name);

double openClickRate = 0;
double registrationClickRate = 0;
double registrationOpenRate = 0;

if (messageId != null && messageId > 0) {
    message = MessagingMessageLocalServiceUtil.getMessagingMessage(messageId);
    recipientsConversion = MessagingUtils.countConversionsByRecipient(messageId);
    emailsOpenedUniqueIP = MessagingUtils.countConversionsByIP(messageId, MessagingConversionTypes.EMAIL_OPENED);
    linksClickUniqueIP = MessagingUtils.countConversionsByIP(messageId, MessagingConversionTypes.EMAIL_LINK_CLICKED);
    
    if (emailsOpenedUniqueIP > 0) {
        openClickRate = 100 * linksClickUniqueIP / emailsOpenedUniqueIP;
        registrationOpenRate = registrationCount / emailsOpenedUniqueIP;
    }
    if (linksClickUniqueIP > 0) {
        registrationClickRate = registrationCount / linksClickUniqueIP;
    }
}

pageContext.setAttribute("message", message);
pageContext.setAttribute("conversionTypes", conversionTypes);


%>


<h2>Message details</h2>
<table id="message-details">
    <tr class="odd">
        <td class="label">Name:</td>
        <td>${message.name}</td>
    </tr>
    <tr class="even">
        <td class="label">Subject:</td>
        <td>${message.subject}</td>
    </tr>
    <tr class="odd">
        <td class="label">Conversions count:</td>
        <td>${message.conversionCount}</td>
    </tr>
    <tr class="even">
        <td class="label">Conversions details:</td>
        <td>
            <table>
                <c:forEach var="type" items="${conversionTypes}" varStatus="rowCounter">
                    <tr class="<c:if test="${rowCounter.count % 2 == 0 }">even</c:if><c:if test="${rowCounter.count % 2 != 0 }">odd</c:if>">
                        <td class="label">${type.description}</td>
                        <td>
                            <% MessagingMessageConversionType type = (MessagingMessageConversionType) pageContext.getAttribute("type"); %>
                            <%= MessagingMessageConversionLocalServiceUtil.countByType(messageId, type) %>
                        </td>
                            
                    </tr>
                        
                </c:forEach>
                
                <tr class="even">
                    <td class="label">How many recipients have generated at lest one click</td>
                    <td><%= recipientsConversion %></td>z
                </tr>
                <tr class="odd">
                    <td class="label">How many email openings from unique IP's</td>
                    <td><%= emailsOpenedUniqueIP %></td>
                </tr>
                <tr class="even">
                    <td class="label">How many clicks from unique IP's</td>
                    <td><%= linksClickUniqueIP %></td>
                </tr>
                <tr class="odd">
                    <td class="label">Link clicked / Email opened  rate (from unique IP)</td>
                    <td><%= openClickRate %></td>
                </tr>
                <tr class="even">
                    <td class="label">Registration / Link clicked rate (from unique IP)</td>
                    <td><%= NumberFormat.getPercentInstance().format(registrationClickRate) %></td>
                </tr>
                <tr class="odd">
                    <td class="label">Registration / Email opened rate (from unique IP)</td>
                    <td><%= NumberFormat.getPercentInstance().format(registrationOpenRate) %></td>
                </tr>
             </table>
        </td>
    </tr>
    <tr class="odd">
        <td class="label">Modified date:</td>
        <td>${message.modifiedDate}</td>
    </tr>
    <tr class="even">
        <td class="label">Created date:</td>
        <td>${message.createDate}</td>
    </tr>
    <tr  class="odd">
        <td class="label">Message text:</td>
        <td>${message.body}</td>
        
    </tr>

</table>
