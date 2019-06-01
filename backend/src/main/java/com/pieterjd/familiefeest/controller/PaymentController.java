package com.pieterjd.familiefeest.controller;

import com.pieterjd.familiefeest.domain.EventRegistration;
import com.pieterjd.familiefeest.domain.Payment;
import com.pieterjd.familiefeest.repository.EventRegistrationRepository;
import com.pieterjd.familiefeest.repository.PaymentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/payment")
@Log4j2
public class PaymentController {
    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Value("${secret.token}")
    private String expectedSecretToken;

    @PostMapping(value = "/{eventCode}")
    public void addPayment(@PathVariable String eventCode, @RequestHeader(name = "secret-token") String secretToken,@RequestBody Payment payment){
        log.info("received postman token: "+secretToken);
        if(!expectedSecretToken.equals(secretToken)){
            throw new RuntimeException("Access denied");
        }
        EventRegistration er = eventRegistrationRepository.findByCodeEquals(eventCode).orElseThrow(() -> new RuntimeException("invalid eventcode"));
        log.info("found er with code "+eventCode);
        log.info("amiunt: "+ payment.getAmount() );
        payment.setEventRegistration(er);
        paymentRepository.save(payment);
        er.getPayments().add(payment);
        eventRegistrationRepository.save(er);
    }
}
