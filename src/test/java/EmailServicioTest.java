import co.edu.uniquindio.clinica.dto.EmailDTO;
import co.edu.uniquindio.clinica.servicios.impl.EmailServicioImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.MimeMessageHelper;

@SpringBootTest
@Transactional
public class EmailServicioTest {

    private EmailServicioImpl emailServicioImpl;

    @Mock
    private JavaMailSender javaMailSender;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        emailServicioImpl = new EmailServicioImpl(javaMailSender);
    }

    @Test
    public void enviarEmail() throws Exception {
        // Se preparan los datos de prueba
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setAsunto("Asunto del correo");
        emailDTO.setCuerpo("Cuerpo del correo");
        emailDTO.setDestinatario("ericap.ruedau@uqvirtual.edu.co");

        try {
            // Simular el comportamiento de JavaMailSender
            MimeMessage mensaje = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje);
            Mockito.when(javaMailSender.createMimeMessage()).thenReturn(mensaje);
            Mockito.when(new MimeMessageHelper(mensaje)).thenReturn(helper);

            // Realizar la prueba
            emailServicioImpl.enviarEmail(emailDTO);

            // Verificar que se llamaron los métodos necesarios
            Mockito.verify(javaMailSender).send(mensaje);
            Mockito.verify(helper).setSubject("Asunto del correo");
            Mockito.verify(helper).setText("Cuerpo del correo", true);
            Mockito.verify(helper).setTo("destinatario@example.com");
            Mockito.verify(helper).setFrom("doctorfast99@gmail.com");
        } catch (MessagingException e) {
            // Manejar la excepción si ocurre un error al enviar el correo
            throw new Exception("Error al enviar el correo", e);
        }
    }
}
