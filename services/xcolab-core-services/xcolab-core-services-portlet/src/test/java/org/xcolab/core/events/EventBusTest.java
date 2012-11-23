package org.xcolab.core.events;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EventBusTest {
    
    
    private EventBus eventBus;
    
    
    @Before
    public void setUp () {
        eventBus = new EventBusImpl();
    }

    @Test
    public void test() throws EventBusException {
        EventHandlerInteger hi1 = new EventHandlerInteger();
        EventHandlerInteger hi2 = new EventHandlerInteger();
        
        EventHandlerInteger hs1 = new EventHandlerInteger();
        EventHandlerInteger hs2 = new EventHandlerInteger();
        
        eventBus.addLinstener(hi1);
        eventBus.publish("hej ho");
        
        Assert.assertEquals(0, hi1.getHandledEvents());
        eventBus.publish(1);
        Assert.assertEquals(1, hi1.getHandledEvents());

        eventBus.addLinstener(hi2);
        eventBus.addLinstener(hs1);
        
        eventBus.publish(31231);
        eventBus.publish("test test test");
        eventBus.publish("test test test");
        

        Assert.assertEquals(2, hi1.getHandledEvents());
        Assert.assertEquals(1, hi2.getHandledEvents());
        Assert.assertEquals(1, hs1.getHandledEvents());

        eventBus.addLinstener(hs2);
        
        eventBus.removeListener(hs1);
        
        for (int i=0; i < 10; i++) {
            eventBus.publish(i);
            eventBus.publish("value " + 1);
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
        
        ListenerRegistration reg1 = eventBus.addLinstener(hi1);
        ListenerRegistration reg2 = eventBus.addLinstener(hi2);
        ListenerRegistration reg3 = eventBus.addLinstener(hi3);
        
        for (int i=0; i < 5; i++) {
            eventBus.publish(i);
        }
        
        Assert.assertEquals(5,  hi1.getHandledEvents());
        Assert.assertEquals(5,  hi2.getHandledEvents());
        Assert.assertEquals(5,  hi3.getHandledEvents());
        
        reg1.unregister();
        
        
        for (int i=0; i < 5; i++) {
            eventBus.publish(i);
        }

        Assert.assertEquals(5,  hi1.getHandledEvents());
        Assert.assertEquals(10,  hi2.getHandledEvents());
        Assert.assertEquals(10,  hi3.getHandledEvents());
        
        eventBus.removeListener(hi2);
        

        for (int i=0; i < 5; i++) {
            eventBus.publish(i);
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
