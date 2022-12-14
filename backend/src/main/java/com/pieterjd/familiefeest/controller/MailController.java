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
import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/finalremindermail/{testing}")
    public List<String> sendFinalReminder(@PathVariable(required = false) boolean testing,
                                          @RequestBody List<String> eventCodes,
                                          @RequestHeader(name = "secret-token", required = false) String secretToken) throws MessagingException {

        log.info("received postman token: " + secretToken);
        if (secretToken != null && !expectedSecretToken.equals(secretToken)) {
            throw new RuntimeException("Access denied");
        }
        List<String> sentTo = new ArrayList<>();
        for (String eventCode : eventCodes) {
            EventRegistration er = eventRegistrationRepository.findByCodeEquals(eventCode).orElseThrow(() -> new RuntimeException("invalid eventcode"));
            if (mailService.sendFinalReminderMail(er, testing)) {
                sentTo.add(er.getCode());
            }
        }
        return sentTo;
    }

    @PostMapping("/invitationmail")
    public void sendInvitationConfirmation(@RequestBody List<String> eventCodes,
                                           @RequestHeader(name = "secret-token", required = false) String secretToken) throws MessagingException {

        log.info("received postman token: " + secretToken);
        if (secretToken != null && !expectedSecretToken.equals(secretToken)) {
            throw new RuntimeException("Access denied");
        }
        for (String eventCode : eventCodes) {
            EventRegistration er = eventRegistrationRepository.findByCodeEquals(eventCode).orElseThrow(() -> new RuntimeException("invalid eventcode"));
            mailService.sendInvitationMail(er);
        }
    }
}
