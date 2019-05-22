package com.pieterjd.familiefeest.service;

import com.pieterjd.familiefeest.domain.EventRegistration;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.stream.Collectors;

@Service
@Log4j2
public class MailService {

    private JavaMailSender mailSender;
    private NumberFormat currencyFormatter;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        this.currencyFormatter = NumberFormat.getInstance();
        this.currencyFormatter.setCurrency(Currency.getInstance("EUR"));

    }


    private static final String confirmationMail = "Beste %s,\n\n" +
            "Bedankt voor je inschrijving. Je hebt volgende reservaties:\n " +
            "%s\n\n" +
            "Tot nu toe betaalde je al %s euro. Graag het openstaand bedrag %s over te schrijven op rekening <TODO> " +
            "met vermelding'Familiefeest Van Ryckeghem %s'.\n\n" +
            "Tot 22 september!\n" +
            "Pieter-Jan & Margriet";

    private static final String invitationMail = "Beste %s,\n\n" +
            "Graag nodigen we je uit op het familiefeest van de familie Van Ryckeghem. Om alles vlot te laten verlopen, " +
            "kan je de menukeuzes van je gezin online doorgeven op https://shielded-ridge-64229.herokuapp.com <TO UPDATE>. " +
            "Je unieke reservatiecode is %s.\n\nOp de website kan je ook alle informatie nog eens rustig doorlezen.\n\n" +
            "Tot 22 september!\n" +
            "Pieter-Jan & Margriet";

    public void sendInvitationMail(EventRegistration er) {
        SimpleMailMessage smm = getSimpleMailMessage(er);
        String text = String.format(invitationMail,
                er.getUser().getName(),
                er.getCode()
        );
        smm.setText(text);
        mailSender.send(smm);
        log.info(String.format("Invitation mail sent to %s",smm.getTo()));

    }

    public void sendPurchaseConfirmationMail(EventRegistration er) {
        SimpleMailMessage smm = getSimpleMailMessage(er);
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

    private SimpleMailMessage getSimpleMailMessage(EventRegistration er) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(er.getUser().getEmail());
        smm.setSubject(er.getEvent().getTitle());
        return smm;
    }

    private String purchaseToString(EventRegistration er) {
        return er.getPurchasedItems().stream()
                .map(pi -> String.format("* %s voor %s\n", pi.getEventItem().getTitle(), pi.getBeneficiary()))
                .collect(Collectors.joining("\n"));

    }
}
