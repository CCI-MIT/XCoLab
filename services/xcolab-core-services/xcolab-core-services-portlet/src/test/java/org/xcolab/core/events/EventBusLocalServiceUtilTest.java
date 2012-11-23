package org.xcolab.core.events;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/META-INF/portlet-spring.xml")
public class EventBusLocalServiceUtilTest {
    

    @Test
    public void test() throws EventBusException {
        EventHandlerInteger hi1 = new EventHandlerInteger();
        EventHandlerInteger hi2 = new EventHandlerInteger();
        
        EventHandlerInteger hs1 = new EventHandlerInteger();
        EventHandlerInteger hs2 = new EventHandlerInteger();
        
        EventBusLocalServiceUtil.addLinstener(hi1);
        EventBusLocalServiceUtil.publish("hej ho");
        
        Assert.assertEquals(0, hi1.getHandledEvents());
        EventBusLocalServiceUtil.publish(1);
        Assert.assertEquals(1, hi1.getHandledEvents());

        EventBusLocalServiceUtil.addLinstener(hi2);
        EventBusLocalServiceUtil.addLinstener(hs1);
        
        EventBusLocalServiceUtil.publish(31231);
        EventBusLocalServiceUtil.publish("test test test");
        EventBusLocalServiceUtil.publish("test test test");
        

        Assert.assertEquals(2, hi1.getHandledEvents());
        Assert.assertEquals(1, hi2.getHandledEvents());
        Assert.assertEquals(1, hs1.getHandledEvents());

        EventBusLocalServiceUtil.addLinstener(hs2);
        
        EventBusLocalServiceUtil.removeListener(hs1);
        
        for (int i=0; i < 10; i++) {
            EventBusLocalServiceUtil.publish(i);
            EventBusLocalServiceUtil.publish("value " + 1);
        }

        Assert.assertEquals(12, hi1.getHandledEvents());
        Assert.assertEquals(11, hi2.getHandledEvents());
        Assert.assertEquals(1, hs1.getHandledEvents());
        Assert.assertEquals(10, hs2.getHandledEvents());
    }
    
    @Test
    public void testRemoval() throws EventBusException {
        EventHandlerInteger hi1 = new EventHandlerInteger();
        EventHandlerInteger hi2 = new EventHandlerInteger();
        EventHandlerInteger hi3 = new EventHandlerInteger();
        
        ListenerRegistration reg1 = EventBusLocalServiceUtil.addLinstener(hi1);
        ListenerRegistration reg2 = EventBusLocalServiceUtil.addLinstener(hi2);
        ListenerRegistration reg3 = EventBusLocalServiceUtil.addLinstener(hi3);
        
        for (int i=0; i < 5; i++) {
            EventBusLocalServiceUtil.publish(i);
        }
        
        Assert.assertEquals(5,  hi1.getHandledEvents());
        Assert.assertEquals(5,  hi2.getHandledEvents());
        Assert.assertEquals(5,  hi3.getHandledEvents());
        
        reg1.unregister();
        
        
        for (int i=0; i < 5; i++) {
            EventBusLocalServiceUtil.publish(i);
        }

        Assert.assertEquals(5,  hi1.getHandledEvents());
        Assert.assertEquals(10,  hi2.getHandledEvents());
        Assert.assertEquals(10,  hi3.getHandledEvents());
        
        EventBusLocalServiceUtil.removeListener(hi2);
        

        for (int i=0; i < 5; i++) {
            EventBusLocalServiceUtil.publish(i);
        }
        

        Assert.assertEquals(5,  hi1.getHandledEvents());
        Assert.assertEquals(10,  hi2.getHandledEvents());
        Assert.assertEquals(15,  hi3.getHandledEvents());
    }
    
    

    public class EventHandlerInteger {
        private int handledEvents = 0;
        
        public EventHandlerInteger() {
        }

        @EventListener
        public void handle(Integer event) {
            handledEvents++;
        }
        
        public int getHandledEvents() {
            return handledEvents;
        }
    }
    

    public class EventHandlerString {
        private int handledEvents = 0;
        
        public EventHandlerString() {
        }

        @EventListener
        public void handle(String handleString) {
            handledEvents++;
        }
        
        public int getHandledEvents() {
            return handledEvents;
        }
    }

}
