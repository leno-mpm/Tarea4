package ec.edu.espol;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
@SuppressWarnings("all")
public class ReservaConsoleUI {
    public void eleccionUsuario(Usuario usuario, Reserva reserva) {
        System.out.println("Notificación importante sobre su reserva: " + reserva.getId());
        System.out.println("¿Desea cancelar y recibir reembolso (R), modificar la fecha sin cargos (M)? (R/M)");

        Scanner sc = new Scanner(System.in);
        String respuesta = sc.nextLine();

        if (respuesta.equalsIgnoreCase("M")) {
            try {
                prepararModificacion(usuario, reserva);
            } catch (Exception e) {
                System.out.println("Error al modificar reserva: " + e.getMessage());
            }
        } else {
            usuario.procesarAccionNotificacion(reserva, respuesta);
        }
    }

    private void prepararModificacion(Usuario usuario, Reserva reserva) throws Exception {
        System.out.println("Modificando reserva sin cargos para la reserva: " + reserva.getId());

        if (!reserva.getViajeCrucero().getCrucero().mostrarViajesProgramados()) {
            System.out.println("No hay viajes programados. Reserva original mantenida.");
            return;
        }

        ViajeCrucero nuevoViaje = seleccionarNuevaFecha(reserva);
        if (nuevoViaje == null) return;

        Cabina nuevaCabina = seleccionarCabinaDisponible(nuevoViaje);
        if (nuevaCabina == null) return;

        reasignarReserva(reserva, nuevoViaje, nuevaCabina);

        // Usuario termina el proceso de notificación
        usuario.procesarAccionNotificacion(reserva, "M");
    }

    private ViajeCrucero seleccionarNuevaFecha(Reserva reserva) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione la fecha del nuevo viaje (dd/MM/yyyy): ");
        String fechaStr = sc.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date nuevaFecha = sdf.parse(fechaStr);

        ViajeCrucero nuevoViaje = reserva.getViajeCrucero().getCrucero().buscarViajePorFecha(nuevaFecha);
        if (nuevoViaje == null) {
            System.out.println("No hay viaje disponible en la fecha seleccionada. Reserva original mantenida.");
            return null;
        }
        return nuevoViaje;
    }

    private Cabina seleccionarCabinaDisponible(ViajeCrucero nuevoViaje) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el tipo de cabina para la nueva reserva: ");
        String tipoCabina = sc.nextLine();

        if (!nuevoViaje.verificarCabina(tipoCabina)) {
            System.out.println("La cabina no pertenece a este crucero. Reserva original mantenida.");
            return null;
        }

        List<Cabina> cabinasDisponibles = nuevoViaje.buscarCabinasDisponibles(tipoCabina);
        if (cabinasDisponibles.isEmpty()) {
            System.out.println("No hay cabinas disponibles del tipo " + tipoCabina + ". Reserva original mantenida.");
            return null;
        }

        return cabinasDisponibles.get(0);
    }

    private void reasignarReserva(Reserva reserva, ViajeCrucero nuevoViaje, Cabina nuevaCabina) {
        reserva.setViajeCrucero(nuevoViaje);
        reserva.setCabina(nuevaCabina);
        System.out.println("Reserva modificada al nuevo viaje en fecha: " + nuevoViaje.getFecha());
    }

}
