package com.rx.service.queuerx;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Autowired
    ThreadPoolManager threadPoolManager;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
    	System.out.println("启动成功......");
        if (event instanceof ContextClosedEvent) {
            XmlWebApplicationContext x = (XmlWebApplicationContext) event.getSource();
            //防止执行两次。root application context 没有parent，他就是老大
            if (x.getDisplayName().equals("Root WebApplicationContext")) {
                threadPoolManager.shutdown();
                Queue q = threadPoolManager.getMsgQueue();
                System.out.println("关闭了服务器，还有未处理的信息条数：" + q.size());
            }


        } else if (event instanceof ContextRefreshedEvent) {
        	//System.out.println(event.getClass().getSimpleName()+" 事件已发生！");
        } else if (event instanceof ContextStartedEvent) {
        	//System.out.println(event.getClass().getSimpleName()+" 事件已发生！");
        } else if (event instanceof ContextStoppedEvent) {
        	//System.out.println(event.getClass().getSimpleName()+" 事件已发生！");
        } else {
        	//System.out.println("有其它事件发生:"+event.getClass().getName());
        }
    }
}
