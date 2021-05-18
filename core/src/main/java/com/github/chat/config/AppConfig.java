package com.github.chat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;

public class AppConfig {

    private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

        public static void tomcatStart(){
            try {
                ServerConfig.tomcat().run();
            } catch (ServletException e){
                e.printStackTrace();
            }
        }
    }
