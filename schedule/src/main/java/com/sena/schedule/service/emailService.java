package com.sena.schedule.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class emailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void basicMail() {
        try {
            // destinatario
            String addressMail = "correo@gmail.com";
            // Asunto
            String subject = "Este es un correo de prueba";
            // Cuerpo Correo
            String bodyMail = "Correo prueba";



            emailSender(addressMail, subject, bodyMail);
        } catch (Exception e) {

        }
    }


   public void advancedEmail(String addressMail) {
        try {
            // destinatario
            // String addressMail = "cjcs.cadenasarasty8@gmail.com";
            // Asunto
            String subject = "Convocatoria Nocturna: El Convite de los Caídos";
            // Cuerpo Correo
            String bodyMail = ""
            + "<!DOCTYPE html>\n"
                + "<html lang=\"es\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Actualización de Políticas</title>\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: 'Segoe UI', sans-serif;\n"
                + "            background-color: #f5f6fa;\n"
                + "            margin: 0;\n"
                + "            padding: 0;\n"
                + "        }\n"
                + "        .container {\n"
                + "            background-color: #ffffff;\n"
                + "            max-width: 600px;\n"
                + "            margin: 40px auto;\n"
                + "            padding: 30px;\n"
                + "            border-radius: 10px;\n"
                + "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n"
                + "        }\n"
                + "        .header {\n"
                + "            text-align: center;\n"
                + "            color: #2f3640;\n"
                + "        }\n"
                + "        .content {\n"
                + "            color: #353b48;\n"
                + "            line-height: 1.6;\n"
                + "            font-size: 16px;\n"
                + "        }\n"
                + "        .footer {\n"
                + "            margin-top: 30px;\n"
                + "            font-size: 13px;\n"
                + "            color: #888;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "        .button {\n"
                + "            display: inline-block;\n"
                + "            margin-top: 20px;\n"
                + "            padding: 12px 20px;\n"
                + "            background-color: #273c75;\n"
                + "            color: white;\n"
                + "            text-decoration: none;\n"
                + "            border-radius: 5px;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container\">\n"
                + "        <h2 class=\"header\">Actualización de Políticas Corporativas</h2>\n"
                + "        <div class=\"content\">\n"
                + "            <p>Estimado/a usuario,</p>\n"
                + "            <p>Le informamos que hemos actualizado nuestras políticas corporativas para ofrecerle un mejor servicio y garantizar la transparencia en todas nuestras operaciones.</p>\n"
                + "            <p>Puede consultar las nuevas políticas haciendo clic en el siguiente enlace:</p>\n"
                + "            <a href=\"https://www.tuempresa.com/politicas\" class=\"button\">Ver Políticas</a>\n"
                + "            <p>Gracias por su atención.</p>\n"
                + "            <p>Que tenga buen resto de día</p>\n"
                + "        </div>\n"
                + "        <div class=\"footer\">\n"
                + "            Este correo es solo informativo. Por favor, no responda a este mensaje.\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";


            emailSender(addressMail, subject, bodyMail);
        } catch (Exception e) {

        }
    }
    

    public boolean emailSender(String addressMail, String subject, String bodyMail) throws MessagingException {
        try {
            // creación del correo
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(addressMail);
            helper.setSubject(subject);
            helper.setText(bodyMail,true);
            javaMailSender.send(message);
            ;
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}