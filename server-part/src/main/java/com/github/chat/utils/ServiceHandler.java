package com.github.chat.utils;

import com.github.chat.service.IUsersService;
import org.slf4j.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ServiceHandler implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(ServiceHandler.class);

    private final IUsersService usersService;

    public ServiceHandler(IUsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       log.info("Before call to method: {}, with args: {}", method.getName(), args);
       Object result = method.invoke(usersService, args);
       log.info("After call to method: {}", result);
       return result;
    }
}
