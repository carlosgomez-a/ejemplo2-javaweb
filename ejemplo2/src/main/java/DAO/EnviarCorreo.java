package DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;


@WebServlet("/EnviarCorreo")
public class EnviarCorreo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Configuración del correo remitente
    private final String remitente = "caligomez294@gmail.com";
    private final String clave = ""; // contraseña de aplicación

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recibir datos desde el formulario
        String destinatario = request.getParameter("destinatario");
        String asunto = request.getParameter("asunto");
        String mensaje = request.getParameter("mensaje");

        // Configuración de propiedades SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Crear sesión autenticada
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        try {
            // Crear el mensaje
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(remitente));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            msg.setSubject(asunto);
            msg.setText(mensaje);

            // Enviar el correo
            Transport.send(msg);

            response.getWriter().println("Correo enviado exitosamente a " + destinatario);

        } catch (MessagingException e) {
            e.printStackTrace();
            response.getWriter().println(" Error al enviar el correo: " + e.getMessage());
        }
    }
}
