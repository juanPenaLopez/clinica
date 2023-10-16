package co.edu.uniquindio.clinica.servicios.impl;

import co.edu.uniquindio.clinica.dto.LoginDTO;
import co.edu.uniquindio.clinica.dto.TokenDTO;
import co.edu.uniquindio.clinica.modelo.Cuenta;
import co.edu.uniquindio.clinica.modelo.Medico;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.repositorios.CuentaRepo;
import co.edu.uniquindio.clinica.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.clinica.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {

    private final CuentaRepo cuentaRepo;
    private final JWTUtils jwtUtils;
    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<Cuenta> cuentaOptional = Optional.ofNullable(cuentaRepo.findByCorreo(loginDTO.getCorreo()));
        if(cuentaOptional.isEmpty()){
            throw new Exception("No existe el correo ingresado");
        }
        Cuenta cuenta = cuentaOptional.get();
        if( !passwordEncoder.matches(loginDTO.getContrasena(), cuenta.getContrasena()) ){
            throw new Exception("La contraseña ingresada es incorrecta");
        }
        return new TokenDTO( crearToken(cuenta) );
    }
    private String crearToken(Cuenta cuenta){
        String rol;
        String nombre;
        if( cuenta instanceof Paciente){
            rol = "paciente";
            nombre = ((Paciente) cuenta).getNombre();
        }else if( cuenta instanceof Medico){
            rol = "medico";
            nombre = ((Medico) cuenta).getNombre();
        }else{
            rol = "admin";
            nombre = "Administrador";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", rol);
        map.put("nombre", nombre);
        map.put("id", cuenta.getIdCuenta());

        return jwtUtils.generarToken(cuenta.getEmail(), map);
    }
}
