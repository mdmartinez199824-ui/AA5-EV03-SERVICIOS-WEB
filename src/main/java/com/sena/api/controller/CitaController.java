package com.sena.api.controller;

import com.sena.api.model.Cita;
import com.sena.api.service.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/** API REST para gestionar las citas médicas de CitaSalud. */
@RestController
@RequestMapping("/api/citas")
public class CitaController {
    private final CitaService citaService;

    public CitaController(CitaService citaService) { this.citaService = citaService; }

    /** GET /api/citas: lista todas las citas. */
    @GetMapping
    public List<Cita> listar() { return citaService.listar(); }

    /** GET /api/citas/{id}: consulta una cita por identificador. */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Cita cita = citaService.buscar(id);
        return cita != null ? ResponseEntity.ok(cita)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Cita no encontrada."));
    }

    /** POST /api/citas: registra una nueva cita. */
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Cita cita) {
        try { return ResponseEntity.status(HttpStatus.CREATED).body(citaService.crear(cita)); }
        catch (IllegalArgumentException e) { return ResponseEntity.badRequest().body(Map.of("error", e.getMessage())); }
    }

    /** PUT /api/citas/{id}: actualiza una cita existente. */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Cita cita) {
        try {
            Cita actualizada = citaService.actualizar(id, cita);
            return actualizada != null ? ResponseEntity.ok(actualizada)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Cita no encontrada."));
        } catch (IllegalArgumentException e) { return ResponseEntity.badRequest().body(Map.of("error", e.getMessage())); }
    }

    /** DELETE /api/citas/{id}: elimina una cita. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        if (citaService.eliminar(id)) return ResponseEntity.ok(Map.of("mensaje", "Cita eliminada satisfactoriamente."));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Cita no encontrada."));
    }
}
