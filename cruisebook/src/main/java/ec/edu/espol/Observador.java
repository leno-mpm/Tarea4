package ec.edu.espol;

import java.util.List;

public interface Observador {
    boolean notificar (String mensaje);
    void accionNotificar(Reserva reserva);
    List<Reserva> getReservas();
}
