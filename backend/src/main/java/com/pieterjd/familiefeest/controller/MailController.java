package com.pieterjd.familiefeest.controller;

import com.pieterjd.familiefeest.domain.EventRegistration;
import com.pieterjd.familiefeest.repository.EventRegistrationRepository;
import com.pieterjd.familiefeest.service.EventRegistrationService;
import com.pieterjd.familiefeest.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    @PostMapping("/purchasemail/{eventCode}")
    public void sendPurchaseConfirmation(@PathVariable String eventCode) {
        EventRegistration er = eventRegistrationRepository.findByCodeEquals(eventCode).orElseThrow(() -> new RuntimeException("invalid eventcode"));
        mailService.sendPurchaseConfirmationMail(er);
    }

    @PostMapping("/invitationmail/{eventCode}")
    public void sendInvitationConfirmation(@PathVariable String eventCode) {
        EventRegistration er = eventRegistrationRepository.findByCodeEquals(eventCode).orElseThrow(() -> new RuntimeException("invalid eventcode"));
        mailService.sendInvitationMail(er);
    }
}
