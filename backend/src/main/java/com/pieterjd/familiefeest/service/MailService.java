package com.pieterjd.familiefeest.service;

import com.pieterjd.familiefeest.domain.EventRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.stream.Collectors;

@Service
public class MailService {

    private JavaMailSender mailSender;
    private NumberFormat currencyFormatter;
    @Autowired
    public MailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
        this.currencyFormatter = NumberFormat.getInstance();
        this.currencyFormatter.setCurrency(Currency.getInstance("EUR"));

    }


    private static final String confirmationMail = "Beste %s,\n\n"+
            "Bedankt voor je inschrijving. Je hebt volgende reservaties:\n "+
            "%s\n\n"+
            "Tot nu toe betaalde je al %s euro. Graag het openstaand bedrag %s over te schrijven op rekening <TODO> " +
            "met vermelding'Familiefeest Van Ryckeghem %s'.\n\n"+
            "Tot 22 september!\n"+
            "Pieter-Jan & Margriet";

    public void sendPurchaseConfirmationMail(EventRegistration er){
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(er.getUser().getEmail());
        smm.setSubject(er.getEvent().getTitle());
        String text = String.format(confirmationMail,
                er.getUser().getName(),
                purchaseToString(er),
                currencyFormatter.format(er.amountAlreadyPaid()),
                currencyFormatter.format(er.openAmount()),
                er.getCode()
        );
        smm.setText(text);
        mailSender.send(smm);
        
    }

    private String purchaseToString(EventRegistration er) {
        return er.getPurchasedItems().stream()
                .map(pi ->  String.format("* %s voor %s\n",pi.getEventItem().getTitle(),pi.getBeneficiary()))
                .collect(Collectors.joining("\n"));

    }
}
