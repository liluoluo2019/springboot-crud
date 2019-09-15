package com.lll.springboot.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("servletContextListener容器启动....");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("servletContextListener容器销毁....");
    }
}
