package com.eduardo.spoilerappnetwork.user.utils;

import com.eduardo.spoilerappnetwork.user.dto.ResponseMessageDTO;

public class ResposeMessageHandler {


    public static ResponseMessageDTO creationMessage(Long id){
        return buildMessage(id, "created");
    }

    public static ResponseMessageDTO updationMessage(Long id){
        return buildMessage(id, "updated");
    }


    private static ResponseMessageDTO buildMessage(Long id, String action){
        return ResponseMessageDTO.builder()
                .message(String.format("User with id %d %s!", id, action))
                .build();
    }

}
