package com.github.chat;

import com.github.chat.config.ServerConfig;
import com.github.chat.utils.ServiceHandler;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;

public class BackendRun {

    private static final Logger log = LoggerFactory.getLogger(ServiceHandler.class);

    public static void main(String[] args) {
        try {
          Tomcat t = ServerConfig.tomcat();
          t.start();
          t.getServer().await();
        } catch (ServletException  | LifecycleException e) {
            log.error("Enter: %s", e.getMessage());
        }
    }
}