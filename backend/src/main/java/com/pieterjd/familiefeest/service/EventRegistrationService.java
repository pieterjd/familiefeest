package com.pieterjd.familiefeest.service;


import com.pieterjd.familiefeest.domain.Event;
import com.pieterjd.familiefeest.domain.EventRegistration;
import com.pieterjd.familiefeest.domain.User;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EventRegistrationService {
    private final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int CODE_LENGTH = 6;
    private Random random = new Random();

    private String generateCode(int length){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<length;i++){
            char next = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
            sb.append(next);
        }
        return sb.toString();
    }

    public EventRegistration generateCodeForUser(Event e, User u){
        String code = generateCode(CODE_LENGTH);
        return EventRegistration.builder()
                .code(code)
                .event(e)
                .user(u)
                .build();

    }
}
