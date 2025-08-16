package ec.edu.espol;

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
    

    // Cancelar reserva
    public void cancelarReserva(Reserva reserva) {
        if (reserva == null || !reservas.contains(reserva)) {
            System.out.println("Reserva no encontrada o no pertenece al usuario.");
            return;
        }
        PoliticaCancelacion politica = reserva.getPoliticaCancelacion();
        if (politica.verificarCancelacion(reserva)) {
            reserva.getViajeCrucero().eliminarReserva(reserva);
            procesarReembolso();
            reservas.remove(reserva);
        } else {
            System.out.println("No se puede cancelar según la política de cancelación.");
        }
    }

    @Override
    public void notificar(String mensaje) {
        medio.enviarNotificacion(mensaje);
    }
   
    @Override
    public void accionNotificar(Reserva reserva) {
        System.out.println("Notificación importante sobre su reserva: "+reserva.getId());
        System.out.println("¿Desea cancelar y recibir reembolso (R), modificar la fecha sin cargos (M) ? (R/M)");

        Scanner sc = new Scanner(System.in);
        String respuesta = sc.nextLine();

        switch (respuesta.toUpperCase()) {
            case "R":
                cancelarReservaConReembolso(reserva);
                break;
            case "M":
                modificarReservaSinCargo(reserva);
                break;
            default:
                System.out.println("Opción no válida, reserva mantenida.");
        }
        // No cerramos Scanner para evitar errores posteriores
    }


    private void cancelarReservaConReembolso(Reserva reserva) {
        System.out.println("Procesando cancelación y reembolso para la reserva: " + reserva.getId());
        procesarReembolso();
        reserva.getViajeCrucero().eliminarReserva(reserva);
        reserva.setEstado(EstadoReserva.CANCELADA);
        System.out.println("Reserva cancelada y reembolso procesado.");
    }

    
    private void modificarReservaSinCargo(Reserva reserva) {
        //CREAR UNA NUEVA RESERVA  EN OTRO VIAJE CRUCERO
        System.out.println("Modificando reserva sin cargos para la reserva: " + reserva.getId());      
        reserva.getViajeCrucero().getCrucero().mostrarViajesProgramados();
        System.out.println("Selecione la fecha del nuevo viaje: ");
        Scanner sc = new Scanner(System.in);
        String fechaStr = sc.nextLine();
        Date nuevaFecha = new Date(fechaStr);
        ViajeCrucero nuevoViaje = reserva.getViajeCrucero().getCrucero().buscarViajePorFecha(nuevaFecha);
        if (nuevoViaje != null) {
            

            System.out.println("Ingrese el tipo de cabina para la nueva reserva: ");
            String tipoCabina = sc.nextLine();
            if (!nuevoViaje.verificarCabina(tipoCabina)) {
                System.out.println("La cabina no pertenece a este crucero. Reserva original mantenida.");
                return;
            }
            List<Cabina> cabinasDisponibles = nuevoViaje.buscarCabinasDisponibles(tipoCabina);
            if (cabinasDisponibles.isEmpty()) {
                System.out.println("No hay cabinas disponibles del tipo " + tipoCabina + ". Reserva original mantenida.");
                return;
            }
           
            Cabina cabinaAsignada = cabinasDisponibles.get(0);
            reserva.setViajeCrucero(nuevoViaje);
            reserva.setCabina(cabinaAsignada);
            reserva.setEstado(EstadoReserva.CONFIRMADA);
            System.out.println("Reserva modificada al nuevo viaje en fecha: " + nuevaFecha);
        } else {
            System.out.println("No hay viaje disponible en la fecha seleccionada. Reserva original mantenida.");
        }
        
    }        

    private void procesarReembolso() {
        System.out.println("Procesando reembolso para el usuario: " + nombre);
    }

    public void reportarIncidente(String detalle) {
        System.out.println("Reporte de incidente del usuario " + nombre + ": " + detalle);
    }
}
