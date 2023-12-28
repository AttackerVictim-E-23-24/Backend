package com.ucab.cmcapp.common.util;

import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GmailCodes {

    private static String emailFrom = "apianigdesarrollo@gmail.com";

    private static String passwordFrom = "snynfnwpywrtfzyx";

    private String emailTo;

    private String subject;

    private String content;

    private Properties mProperties;

    private Session mSession;

    private MimeMessage mCorreo;

    private static org.slf4j.Logger _logger = LoggerFactory.getLogger( GmailCodes.class );

    public GmailCodes(){
        mProperties = new Properties();
    }

    public void createEmail(String emailTo){

        //Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

        mSession = Session.getDefaultInstance(mProperties);

        this.emailTo = emailTo;

        try{

            _logger.debug("Entering GmailCodes.createEmail...");

            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(this.emailTo));
            mCorreo.setSubject("Código de recuperación de contraseña");

            content = MailCodesGenerator.generarStringNumerosAleatorios();

            mCorreo.setText("Buenas, el equipo E te facilita el código para puedas establecer una nueva contraseña: " + content, "ISO-8859-1", "html");

        } catch (AddressException e) {
            _logger.error("error {} creating email (Address Exception): {}", e.getMessage(), e.getCause());
        } catch (MessagingException e) {
            _logger.error("error {} creating email (Messaging Exception Uno): {}", e.getMessage(), e.getCause());
        }

        _logger.debug("Leaving GmailCodes.createEmail...");

    }

    public int sendEmail(){

        _logger.debug("Entering GmailCodes.sendEmail...");

        int response = 0;

        try{
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();

            response = Integer.parseInt(content);

        } catch (NoSuchProviderException e) {
            _logger.error("error {} sending email (No Such Provider Exception): {}", e.getMessage(), e.getCause());
        } catch (MessagingException e) {
            _logger.error("error {} sending email (Messaging Exception Dos): {}", e.getMessage(), e.getCause());
        }

        _logger.debug("Leaving GmailCodes.sendEmail...");

        return response;

    }


}
