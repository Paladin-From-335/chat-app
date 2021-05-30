package com.github.chat.config;

import com.github.chat.service.IMessageService;
import com.github.chat.service.IRoomMemberService;
import com.github.chat.service.IRoomService;
import com.github.chat.service.IUsersService;
import com.github.chat.service.impl.MessageService;
import com.github.chat.service.impl.RoomMemberService;
import com.github.chat.service.impl.RoomService;
import com.github.chat.service.impl.UserService;

public class ServiceConfig {

    private static final IUsersService userService = new UserService(RepositoryConfig.getUsersRepository());
    private static final IMessageService messageService = new MessageService(RepositoryConfig.getMessageRepository());
    private static final IRoomService roomService = new RoomService(RepositoryConfig.getRoomRepository());
    private static final IRoomMemberService roomMemberService = new RoomMemberService(RepositoryConfig.getRoomMemberRepository());

    public static IUsersService getUserService() {
        return userService;
    }

    public static IMessageService getMessageService() {
        return messageService;
    }

    public static IRoomService getRoomService() {
        return roomService;
    }

    public static IRoomMemberService getRoomMemberService() {
        return roomMemberService;
    }
}
