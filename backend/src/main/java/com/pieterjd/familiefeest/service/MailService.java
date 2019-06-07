package com.pieterjd.familiefeest.service;

import com.pieterjd.familiefeest.domain.EventRegistration;
import lombok.extern.log4j.Log4j2;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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


    private void sendMail(EventRegistration er, String templatePath) throws MessagingException {
        JtwigTemplate templ = JtwigTemplate.classpathTemplate(templatePath);
        JtwigModel model = JtwigModel.newModel().with("eventRegistration",er);
        String text = templ.render(model);
        MimeMessageHelper smm = getMimeMessageHelper(er);
        smm.setText(text,true);
        mailSender.send(smm.getMimeMessage());
    }



    public void sendInvitationMail(EventRegistration er) throws MessagingException {
        sendMail(er,String.format("templates/%s.invitation.twig",er.getEvent().getId().toString()));
        log.info(String.format("Invitation mail sent to %s",er.getUser().getEmail()));

    }

    public void sendPurchaseConfirmationMail(EventRegistration er) throws MessagingException {
        sendMail(er, String.format("templates/%s.confirmation.twig",er.getEvent().getId().toString()));
        log.info(String.format("Confirmation mail sent to %s",er.getUser().getEmail()));
    }

    private MimeMessageHelper getMimeMessageHelper(EventRegistration er) throws MessagingException {
        MimeMessage smm = mailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(smm);
        mmh.setTo(er.getUser().getEmail());
        mmh.setSubject(er.getEvent().getTitle());
        return mmh;
    }

    private String purchaseToString(EventRegistration er) {
        return er.getPurchasedItems().stream()
                .map(pi -> String.format("* %s voor %s\n", pi.getEventItem().getTitle(), pi.getBeneficiary()))
                .collect(Collectors.joining("\n"));

    }
}
