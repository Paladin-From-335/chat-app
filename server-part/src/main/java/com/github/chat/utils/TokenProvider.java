package com.github.chat.utils;

import com.github.chat.handlers.TokenProviderException;
import com.github.chat.payload.Token;

import java.util.Optional;

public class TokenProvider {

    public static String encode(Token t) {
        return JsonHelper.toJson(t).orElseThrow(TokenProviderException::new);
    }

    public static Token decoding(String str) {
        return JsonHelper.fromJson(str, Token.class).orElseThrow(TokenProviderException::new);
    }
}
