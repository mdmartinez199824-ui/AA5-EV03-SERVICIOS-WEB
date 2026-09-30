package com.sena.api.controller;

import com.sena.api.model.Usuario;
import com.sena.api.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/** Controlador REST que publica los endpoints de registro e inicio de sesión. */
@RestController
@RequestMapping("/api")
public class AutenticacionController {
    private final UsuarioService usuarioService;
    public AutenticacionController(UsuarioService usuarioService) { this.usuarioService = usuarioService; }

    /** POST /api/registro: recibe usuario y contraseña y crea el registro. */
    @PostMapping("/registro")
    public ResponseEntity<Map<String, String>> registrar(@RequestBody Usuario usuario) {
        String mensaje = usuarioService.registrar(usuario);
        HttpStatus estado = mensaje.equals("Usuario registrado satisfactoriamente.") ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(estado).body(Map.of("mensaje", mensaje));
    }

    /** POST /api/login: devuelve éxito cuando las credenciales son correctas. */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Usuario usuario) {
        if (usuarioService.autenticar(usuario)) return ResponseEntity.ok(Map.of("mensaje", "Autenticación satisfactoria."));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Error en la autenticación."));
    }
}
