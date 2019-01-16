package org.liu.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by liuzhsh on 2017/11/21.
 */
public class RegisterAndPublish {

    public static void main(String[] args) throws InterruptedException {
        RegisterAndPublish registerAndPublish = new RegisterAndPublish();
        registerAndPublish.async();
    }

    public void sync(){
        //Creates a new EventBus with the given identifier
        EventBus eventBus = new EventBus("syncEventBus");

        //register all subscriber
        OrderEventListener orderEventListener = new OrderEventListener();
        eventBus.register(orderEventListener);
        MultiEventListener multiEventListener = new MultiEventListener();
        eventBus.register(multiEventListener);

        //publish event
        eventBus.post(new OrderEvent(1L, 145.30));
        eventBus.post(new MessageEvent("liu order", "quick send my product"));

        //unregister
        eventBus.unregister(orderEventListener);
        eventBus.unregister(multiEventListener);
    }

    public void async() throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(5);
        AsyncEventBus asyncEventBus = new AsyncEventBus("asyncEventBus", executor);
        //register all subscriber
        OrderEventListener orderEventListener = new OrderEventListener();
        asyncEventBus.register(orderEventListener);
        MultiEventListener multiEventListener = new MultiEventListener();
        asyncEventBus.register(multiEventListener);

        //publish event
        asyncEventBus.post(new OrderEvent(1L, 145.30));
        asyncEventBus.post(new OrderEvent(2L, 123.00));
        for (int i = 0; i < 100; i++) {
            asyncEventBus.post(new MessageEvent("title:" + i, "msg"));
            if((i % 4) == 0){
                Thread.sleep(2000);
            }
        }

        //unregister
        asyncEventBus.unregister(orderEventListener);
        asyncEventBus.unregister(multiEventListener);

    }

}
