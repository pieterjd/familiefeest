package com.pieterjd.familiefeest.controller;

import com.pieterjd.familiefeest.domain.EventRegistration;
import com.pieterjd.familiefeest.domain.Payment;
import com.pieterjd.familiefeest.repository.EventRegistrationRepository;
import com.pieterjd.familiefeest.repository.PaymentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(value = "/{eventCode}")
    public void addPayment(@PathVariable String eventCode, HttpEntity<Double> amount){
        EventRegistration er = eventRegistrationRepository.findByCodeEquals(eventCode).orElseThrow(() -> new RuntimeException("invalid eventcode"));
        log.info("found er with code "+eventCode);
        log.info("amiunt: "+ amount );
        Payment p = Payment.builder()
                .amount(amount.getBody())
                .eventRegistration(er)
                .date(new Date())
                .build();
        paymentRepository.save(p);
        er.getPayments().add(p);
        eventRegistrationRepository.save(er);
    }
}
