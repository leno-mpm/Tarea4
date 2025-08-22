package ec.edu.espol;

import java.util.ArrayList;
import java.util.List;

public class Usuario implements Observador {
    private String nombre;
    private String email;
    private String telefono;
    private Notificacion medio;
    private List<Reserva> reservas = new ArrayList<>();

    public Usuario(String nombre, String email, String telefono, Notificacion medio) {
        if (nombre == null || email == null || telefono == null || medio == null) {
            throw new IllegalArgumentException("Los parámetros no pueden ser nulos");
        }
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.medio = medio;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public Notificacion getMedio() { return medio; }
    public List<Reserva> getReservas() { return reservas; }

    public void agregarReserva(Reserva reserva) {
        if (reserva == null) throw new IllegalArgumentException("La reserva no puede ser nula");
        if (reservas.contains(reserva)) throw new IllegalArgumentException("La reserva ya existe");
        reservas.add(reserva);
        reserva.setEstado(EstadoReserva.RESERVADA);
    }

    @Override
    public boolean notificar(String mensaje) {
        return medio.enviarNotificacion(mensaje);
    }

    @Override
    public void accionNotificar(Reserva reserva) {
        ReservaConsoleUI ui = new ReservaConsoleUI();
        ui.eleccionUsuario(this, reserva);
    }
}
