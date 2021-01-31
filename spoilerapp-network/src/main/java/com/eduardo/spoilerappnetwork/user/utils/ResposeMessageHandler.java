package com.eduardo.spoilerappnetwork.user.utils;

import com.eduardo.spoilerappnetwork.user.dto.ResponseMessageDTO;

public class ResposeMessageHandler {


    public static ResponseMessageDTO creationMessage(Long id){
        return buildMessage(id, "created");
    }

    private static ResponseMessageDTO buildMessage(Long id, String action){
        return ResponseMessageDTO.builder()
                .message(String.format("User with id %d %s!", id, action))
                .build();
    }

}
