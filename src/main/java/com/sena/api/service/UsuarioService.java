package com.sena.api.service;

import com.sena.api.model.Usuario;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/** Servicio encargado de registrar usuarios y validar sus credenciales. */
@Service
public class UsuarioService {
    // Almacenamiento en memoria para mantener el ejemplo sencillo y enfocado en la API.
    private final Map<String, String> usuarios = new ConcurrentHashMap<>();

    /** Registra un usuario si los datos son válidos y el nombre no existe. */
    public String registrar(Usuario usuario) {
        if (usuario == null || esVacio(usuario.getUsuario()) || esVacio(usuario.getContrasena())) return "El usuario y la contraseña son obligatorios.";
        if (usuarios.containsKey(usuario.getUsuario())) return "El usuario ya se encuentra registrado.";
        usuarios.put(usuario.getUsuario(), usuario.getContrasena());
        return "Usuario registrado satisfactoriamente.";
    }

    /** Comprueba que usuario y contraseña coincidan con un registro existente. */
    public boolean autenticar(Usuario usuario) {
        if (usuario == null || esVacio(usuario.getUsuario()) || esVacio(usuario.getContrasena())) return false;
        return usuario.getContrasena().equals(usuarios.get(usuario.getUsuario()));
    }

    private boolean esVacio(String valor) { return valor == null || valor.isBlank(); }
}
