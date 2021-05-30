package com.github.chat.config;

import com.github.chat.repository.IMessageRepository;
import com.github.chat.repository.IRoomMemberRepository;
import com.github.chat.repository.IRoomRepository;
import com.github.chat.repository.IUserRepository;
import com.github.chat.repository.impl.MessageRepository;
import com.github.chat.repository.impl.RoomMemberRepository;
import com.github.chat.repository.impl.RoomRepository;
import com.github.chat.repository.impl.UserRepository;

public class RepositoryConfig {

    private static final IUserRepository usersRepository = new UserRepository();
    private static final IMessageRepository messageRepository = new MessageRepository();
    private static final IRoomRepository roomRepository = new RoomRepository();
    private static final IRoomMemberRepository roomMemberRepository = new RoomMemberRepository();

    public static IUserRepository getUsersRepository() {
        return usersRepository;
    }

    public static IMessageRepository getMessageRepository() {
        return messageRepository;
    }

    public static IRoomRepository getRoomRepository() {
        return roomRepository;
    }

    public static IRoomMemberRepository getRoomMemberRepository() {
        return roomMemberRepository;
    }
}
