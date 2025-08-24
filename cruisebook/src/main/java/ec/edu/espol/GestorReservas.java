package ec.edu.espol;

public class GestorReservas {

    public void procesarAccion(Usuario usuario, Reserva reserva, String opcion) {
        if (usuario == null) throw new IllegalArgumentException("El usuario no puede ser nulo");
        if (reserva == null) throw new IllegalArgumentException("La reserva no puede ser nula");
        if (opcion == null) throw new IllegalArgumentException("La opción no puede ser nula");

        switch (opcion.toUpperCase()) {
            case "R":
                cancelarReservaConReembolso(usuario, reserva);
                break;
            case "M":
                modificarReservaSinCargo(reserva);
                break;
            default:
                System.out.println("Opción no válida, reserva mantenida.");
        }
    }

    public void cancelarReservaConReembolso(Usuario usuario, Reserva reserva) {
        if (usuario == null) throw new IllegalArgumentException("El usuario no puede ser nulo");
        if (reserva == null) throw new IllegalArgumentException("La reserva no puede ser nula");
        System.out.println("Procesando cancelación y reembolso para la reserva: " + reserva.getId());
        procesarReembolso(usuario);
        reserva.getViajeCrucero().eliminarReserva(reserva);
        reserva.cancelada();
        System.out.println("Reserva cancelada y reembolso procesado.");
    }

    public void modificarReservaSinCargo(Reserva reserva) {
        if (reserva == null) throw new IllegalArgumentException("La reserva no puede ser nula");
        reserva.confirmada();
        System.out.println("Reserva modificada exitosamente al nuevo viaje: " + reserva.getViajeCrucero().getFecha());
    }

    private void procesarReembolso(Usuario usuario) {
        System.out.println("Procesando reembolso para el usuario: " + usuario.getNombre());
    }
}
