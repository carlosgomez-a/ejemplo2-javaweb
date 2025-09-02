package DAO;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarCorreoo {

    public static void main(String[] args) {
        
        // Correo y contraseña del remitente
        final String remitente = "caligomez294@gmail.com";
        final String clave = "cqec dtwz unny xiia"; // para Gmail necesitas "Contraseña de aplicación"

        // Propiedades de la conexión
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); 
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // también puede ser 465 con SSL

        // Crear sesión autenticada
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
          });

        try {
            // Crear el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse("caligomez294@gmail.com")
            );
            message.setSubject("Prueba desde Java");
            message.setText("Hola, este es un correo enviado desde Java usando javax.mail.");

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("Correo enviado correctamente ✅");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
