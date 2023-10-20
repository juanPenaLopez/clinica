package co.edu.uniquindio.clinica.servicios.impl;

import co.edu.uniquindio.clinica.dto.EmailDTO;
import co.edu.uniquindio.clinica.servicios.interfaces.EmailServicio;
import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class EmailServicioImpl implements EmailServicio {

    private final JavaMailSender javaMailSender;

    @Override
    public void enviarEmail(EmailDTO emailDTO) throws Exception {
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);
        helper.setSubject(emailDTO.getAsunto());
        helper.setText(emailDTO.getCuerpo(), true);
        helper.setTo(emailDTO.getDestinatario());
        helper.setFrom("doctorfast99@gmail.com");
        javaMailSender.send(mensaje);
    }
}
