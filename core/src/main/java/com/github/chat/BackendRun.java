package com.github.chat;

import com.github.chat.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackendRun {

    private static final Logger log = LoggerFactory.getLogger(BackendRun.class);

    public static void main(String[] args) {
        AppConfig.tomcatStart();
    }
}