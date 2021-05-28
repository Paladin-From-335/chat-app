package com.github.chat;

import com.github.chat.config.ServerConfig;
import org.apache.catalina.LifecycleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public class BackendRun {

    private static final Logger log = LoggerFactory.getLogger(BackendRun.class);

    public static void main(String[] args) throws ServletException, LifecycleException, IOException {

        ServerConfig.start().run();
    }
}