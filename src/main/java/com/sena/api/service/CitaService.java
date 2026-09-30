package com.sena.api.service;

import com.sena.api.model.Cita;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/** Lógica de negocio para administrar las citas médicas. */
@Service
public class CitaService {
    private final ConcurrentHashMap<Long, Cita> citas = new ConcurrentHashMap<>();
    private final AtomicLong secuencia = new AtomicLong(0);

    public List<Cita> listar() { return new ArrayList<>(citas.values()); }

    public Cita buscar(Long id) { return citas.get(id); }

    public Cita crear(Cita cita) {
        validar(cita);
        long id = secuencia.incrementAndGet();
        cita.setId(id);
        if (vacio(cita.getEstado())) cita.setEstado("PROGRAMADA");
        citas.put(id, cita);
        return cita;
    }

    public Cita actualizar(Long id, Cita datos) {
        validar(datos);
        if (!citas.containsKey(id)) return null;
        datos.setId(id);
        if (vacio(datos.getEstado())) datos.setEstado("PROGRAMADA");
        citas.put(id, datos);
        return datos;
    }

    public boolean eliminar(Long id) { return citas.remove(id) != null; }

    private void validar(Cita cita) {
        if (cita == null || vacio(cita.getPaciente()) || vacio(cita.getDocumento()) ||
            vacio(cita.getEspecialidad()) || vacio(cita.getMedico()) ||
            vacio(cita.getFecha()) || vacio(cita.getHora())) {
            throw new IllegalArgumentException("Paciente, documento, especialidad, médico, fecha y hora son obligatorios.");
        }
    }

    private boolean vacio(String valor) { return valor == null || valor.isBlank(); }
}
