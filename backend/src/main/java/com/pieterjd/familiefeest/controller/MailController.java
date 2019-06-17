package com.pieterjd.familiefeest.controller;

import com.pieterjd.familiefeest.domain.EventRegistration;
import com.pieterjd.familiefeest.repository.EventRegistrationRepository;
import com.pieterjd.familiefeest.service.EventRegistrationService;
import com.pieterjd.familiefeest.service.MailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/mail")

@Log4j2
public class MailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    @Value("${secret.token}")
    private String expectedSecretToken;

    @PostMapping("/purchasemail/{eventCode}")
    public void sendPurchaseConfirmation(@PathVariable String eventCode,
                                         @RequestHeader(name = "secret-token", required = false) String secretToken) throws MessagingException {
        log.info("received postman token: " + secretToken);
        if (secretToken != null && !expectedSecretToken.equals(secretToken)) {
            throw new RuntimeException("Access denied");
        }
        EventRegistration er = eventRegistrationRepository.findByCodeEquals(eventCode).orElseThrow(() -> new RuntimeException("invalid eventcode"));
        mailService.sendPurchaseConfirmationMail(er);
    }

    @PostMapping("/invitationmail/{eventCode}")
    public void sendInvitationConfirmation(@PathVariable String eventCode,
                                           @RequestHeader(name = "secret-token", required = false) String secretToken) throws MessagingException {

        log.info("received postman token: " + secretToken);
        if (secretToken != null && !expectedSecretToken.equals(secretToken)) {
            throw new RuntimeException("Access denied");
        }
        EventRegistration er = eventRegistrationRepository.findByCodeEquals(eventCode).orElseThrow(() -> new RuntimeException("invalid eventcode"));
        mailService.sendInvitationMail(er);
    }
}
