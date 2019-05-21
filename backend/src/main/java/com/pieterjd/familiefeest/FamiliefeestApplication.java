package com.pieterjd.familiefeest;

import org.h2.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class FamiliefeestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamiliefeestApplication.class, args);
	}
	@Bean
	@Autowired
	CommandLineRunner demo(JavaMailSender mailSender){
		return (args)->{
			SimpleMailMessage smm = new SimpleMailMessage();
			smm.setTo("drouillon@hotmail.com");
			smm.setSubject("test mail from springboot");
			smm.setText("Some text to send");
			mailSender.send(smm);
		};
	}

}
