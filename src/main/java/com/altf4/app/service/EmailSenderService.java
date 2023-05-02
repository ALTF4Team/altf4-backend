package com.altf4.app.service;

import com.altf4.app.model.application.LoanApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.altf4.app.util.EmailEnum.EMAIL_ADDRESS;
import static com.altf4.app.util.EmailEnum.EMAIL_TOPIC;

@Service
public class EmailSenderService {

    private final JavaMailSender mailSender;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendConfirmationEmail(LoanApplication application) {

        SimpleMailMessage email = new SimpleMailMessage();
        String customerName = application.getCustomer().getName();

        email.setFrom(EMAIL_ADDRESS);
        email.setTo(application.getCustomer().getEmail());
        email.setSubject(EMAIL_TOPIC);
        email.setText("Dear, " + customerName + ", \n\n" +
                "We wish to inform you that we have received " +
                "your loan application and that it will be reviewed by one of our staff members. " +
                "Once the evaluation is complete, you will be informed of our decision by mail and phone." +
                "\n\nThank you for using our services.");

        mailSender.send(email);
    }

    public void sendDecisionEmail(LoanApplication application) {

        SimpleMailMessage email = new SimpleMailMessage();
        String customerName = application.getCustomer().getName();

        email.setFrom(EMAIL_ADDRESS);
        email.setTo(application.getCustomer().getEmail());
        email.setSubject(EMAIL_TOPIC);

        switch (application.getApplicationStatus()) {
            case APPROVED -> {
                email.setText("Dear, " + customerName + ",\n\n" +
                        "We wish to inform you that your loan " +
                        "application has been approved and one of our employees will contact " +
                        "you shortly by phone to provide further details." +
                        "\n\nThank you for using our services.");
                application.setResponseEmailSent(true);
            }
            case DENIED -> {
                email.setText("Dear, " + customerName + ", \n\n" +
                        "We wish to inform you that your loan " +
                        "application has been denied." +
                        "\n\nThank you for using our services.");
                application.setResponseEmailSent(true);
            }
        }
        mailSender.send(email);
    }

}