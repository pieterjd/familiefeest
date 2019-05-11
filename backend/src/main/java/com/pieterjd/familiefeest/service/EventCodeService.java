package com.pieterjd.familiefeest.service;


import com.pieterjd.familiefeest.domain.Event;
import com.pieterjd.familiefeest.domain.EventCode;
import com.pieterjd.familiefeest.domain.User;
import com.pieterjd.familiefeest.repository.EventCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EventCodeService {
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

    public EventCode generateCodeForUser(Event e, User u){
        String code = generateCode(CODE_LENGTH);
        return EventCode.builder()
                .code(code)
                .event(e)
                .user(u)
                .build();

    }
}
