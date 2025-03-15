package org.liu.google.eventbus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

/**
 * Created by liuzhsh on 2017/11/21.
 */
public class MultiEventListener {
    @AllowConcurrentEvents
    @Subscribe
    public void listen(OrderEvent event){
        System.out.println(Thread.currentThread().getName() + "，multi event listener receive order event:order id=" + event.getOrderId() + ",amount = " + event.getAmount());
    }

    @AllowConcurrentEvents
    @Subscribe
    public void listen(MessageEvent event){
        System.out.println(Thread.currentThread().getName() + "，multi event listener receive message event:" + event.getTitle() + ",content = " + event.getContent());
    }
}
