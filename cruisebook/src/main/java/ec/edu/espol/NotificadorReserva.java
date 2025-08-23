package ec.edu.espol;

public class NotificadorReserva {

     public static void gestionarNotificacion(Observador observador, ViajeCrucero viaje, String mensaje) {
        if (observador == null || viaje == null) return;

        Reserva reservaBuscada = buscarReserva(observador, viaje);
        if (reservaBuscada != null) {
            reservaBuscada.pendiente();
            observador.accionNotificar(reservaBuscada);
        }
    }

    private static Reserva buscarReserva(Observador observador, ViajeCrucero viaje) {
        for (Reserva reserva : observador.getReservas()) {
            if (viaje.getReservas().contains(reserva)) {
                return reserva;
            }
        }
        return null;
    }
    
}
