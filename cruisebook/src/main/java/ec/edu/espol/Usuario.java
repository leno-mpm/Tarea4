package ec.edu.espol;

import java.beans.SimpleBeanInfo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
@SuppressWarnings("all")
public class Usuario implements Observador {
    private String nombre;
    private String email;
    private String telefono;
    private Notificacion medio;
    private List<Reserva> reservas= new ArrayList<>();

    public Usuario(String nombre, String email, String telefono, Notificacion medio) {
        if (nombre == null || email == null || telefono == null || medio == null) {
            throw new IllegalArgumentException("Los parametros no pueden ser nulos");
        }
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.medio = medio;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }   
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public Notificacion getMedio() {
        return medio;
    }
    public void setMedio(Notificacion medio) {
        this.medio = medio;
    }
    public List<Reserva> getReservas() {
        return reservas;
    }
    

    public void agregarReserva(Reserva reserva){
        if (reserva == null) {
            throw new IllegalArgumentException("La reserva no puede ser nula");
        }
        if (reservas.contains(reserva)) {
            throw new IllegalArgumentException("La reserva ya existe");
        }
        reservas.add(reserva);
        reserva.setEstado(EstadoReserva.RESERVADA);
    }

    // Cancelar reserva
    public void cancelarReserva(Reserva reserva, Crucero crucero) {
        if (reserva == null || !reservas.contains(reserva)) {
            throw new IllegalArgumentException("Reserva no encontrada o no pertenece al usuario.");
        }
        PoliticaCancelacion politica = reserva.getPoliticaCancelacion();
        if (politica.verificarCancelacion(reserva, crucero)) {
            reserva.setEstado(EstadoReserva.CANCELADA);
            reserva.getViajeCrucero().eliminarReserva(reserva);
            procesarReembolso();
        } else {
            throw new IllegalArgumentException("No se puede cancelar según la política de cancelación.");
        }
    }

    @Override
    public boolean notificar(String mensaje) {
        return medio.enviarNotificacion(mensaje);
    }

    @Override
    public void accionNotificar(Reserva reserva) {
        // Delegación de UI
        ReservaConsoleUI ui = new ReservaConsoleUI();
        ui.eleccionUsuario(this, reserva);
    }

    public void procesarAccionNotificacion(Reserva reserva, String opcion) {
    if (reserva == null) throw new IllegalArgumentException("La reserva no puede ser nula");

    switch (opcion.toUpperCase()) {
        case "R":
            cancelarReservaConReembolso(reserva);
            break;
        case "M":
            modificarReservaSinCargo(reserva); 
            break;
        default:
            System.out.println("Opción no válida, reserva mantenida.");
    }
}

    public void cancelarReservaConReembolso(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("La reserva no puede ser nula");
        }
        System.out.println("Procesando cancelación y reembolso para la reserva: " + reserva.getId());
        procesarReembolso();
        reserva.getViajeCrucero().eliminarReserva(reserva);
        reserva.setEstado(EstadoReserva.CANCELADA);
        System.out.println("Reserva cancelada y reembolso procesado.");
    }

    
    private void modificarReservaSinCargo(Reserva reserva) {
        // La pedida por consola se maneja en ReservaConsoleUI
        reserva.setEstado(EstadoReserva.CONFIRMADA);
        System.out.println("Reserva modificada exitosamente al nuevo viaje: " + reserva.getViajeCrucero().getFecha());
    }       

    private void procesarReembolso() {
        System.out.println("Procesando reembolso para el usuario: " + nombre);
    }

    public void reportarIncidente(String detalle) {
        if (detalle == null) {
            throw new IllegalArgumentException("No se puede mandar un detalle nulo");
        }
        System.out.println("Reporte de incidente del usuario " + nombre + ": " + detalle);
    }
}
