package org.xcolab.view.pages.messaging.utils;

import org.xcolab.view.pages.messaging.beans.MessageBean;

import java.util.Comparator;

public class MessageBeanDateComparator implements Comparator<MessageBean> {
    public int compare (MessageBean messageBean1, MessageBean messageBean2) {
        return messageBean2.getCreatedAt().compareTo(messageBean1.getCreatedAt());
    }
}
