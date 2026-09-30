# Documentación de servicios web – CitaSalud

**Evidencia:** GA7-220501096-AA5-EV03  
**Aprendiz:** Michel Dayana Martínez Fajardo  
**Tecnología:** Java 17, Spring Boot y Maven  
**URL base local:** `http://localhost:8080`

## 1. Registrar usuario
- Método: `POST`
- Endpoint: `/api/registro`
- JSON: `{"usuario":"michel","contrasena":"12345"}`
- Respuesta exitosa: HTTP 201.

## 2. Iniciar sesión
- Método: `POST`
- Endpoint: `/api/login`
- JSON: `{"usuario":"michel","contrasena":"12345"}`
- Respuesta exitosa: HTTP 200. Credenciales incorrectas: HTTP 401.

## 3. Crear cita médica
- Método: `POST`
- Endpoint: `/api/citas`
- JSON de ejemplo:
```json
{
  "paciente": "Michel Martinez",
  "documento": "123456789",
  "especialidad": "Medicina general",
  "medico": "Dra. Ana Perez",
  "fecha": "2026-10-05",
  "hora": "10:30",
  "estado": "PROGRAMADA"
}
```
- Respuesta exitosa: HTTP 201 y la cita con su `id`.

## 4. Listar citas
- Método: `GET`
- Endpoint: `/api/citas`
- Respuesta exitosa: HTTP 200 con la lista de citas.

## 5. Consultar cita por ID
- Método: `GET`
- Endpoint: `/api/citas/{id}`
- Ejemplo: `/api/citas/1`
- Respuesta: HTTP 200 si existe o HTTP 404 si no existe.

## 6. Actualizar cita
- Método: `PUT`
- Endpoint: `/api/citas/{id}`
- Se envía el mismo formato JSON utilizado al crear una cita.
- Respuesta: HTTP 200 si se actualiza o HTTP 404 si no existe.

## 7. Eliminar cita
- Método: `DELETE`
- Endpoint: `/api/citas/{id}`
- Respuesta: HTTP 200 si se elimina o HTTP 404 si no existe.

## Ejecución
Desde la raíz del proyecto ejecutar `mvn spring-boot:run`. Los datos se almacenan en memoria para esta evidencia académica y se reinician al detener la aplicación.
