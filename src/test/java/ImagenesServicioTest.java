import co.edu.uniquindio.clinica.servicios.impl.ImagenesServicioImpl;
import com.cloudinary.Cloudinary;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
@Transactional
public class ImagenesServicioTest {

    private ImagenesServicioImpl imagenesServicio;

    @Mock
    private Cloudinary cloudinary;

    @Before("")
    public void setUp() throws IOException {
        initMocks(this);

        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dlhaobtbc");
        config.put("api_key", "935668423153191");
        config.put("api_secret", "KDO89K2ru-N77NAS2MXPljdv4r0");

        when(cloudinary.uploader().upload(Mockito.any(File.class), Mockito.anyMap())).thenReturn(getSampleUploadResult());
        when(cloudinary.uploader().destroy(Mockito.anyString(), Mockito.anyMap())).thenReturn(getSampleDestroyResult());

        imagenesServicio = new ImagenesServicioImpl();
    }

    @Test
    public void subirImagen() throws Exception {
        // Preparar datos de prueba
        MultipartFile mockMultipartFile = Mockito.mock(MultipartFile.class);

        // Realizar la prueba
        Map result = imagenesServicio.subirImagen(mockMultipartFile);

        // Verificar que se subió la imagen con éxito
        assertEquals("OK", result.get("result"));
    }

    @Test
    public void eliminarImagen() throws Exception {
        // Preparar datos de prueba
        String idImagen = "imagen_id";

        // Realizar la prueba
        Map result = imagenesServicio.eliminarImagen(idImagen);

        // Verificar que se eliminó la imagen con éxito
        assertEquals("ok", result.get("result"));
    }

    private Map getSampleUploadResult() {
        Map<String, Object> result = new HashMap<>();
        result.put("result", "OK");
        // Puedes agregar más detalles si es necesario
        return result;
    }

    private Map getSampleDestroyResult() {
        Map<String, Object> result = new HashMap<>();
        result.put("result", "ok");
        // Puedes agregar más detalles si es necesario
        return result;
    }
}

