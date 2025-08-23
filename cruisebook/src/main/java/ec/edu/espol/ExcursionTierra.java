package ec.edu.espol;

import java.util.Date;

public class ExcursionTierra extends ReservaDecorator {

    public ExcursionTierra(Reserva reserva) {
        super(reserva);
    }

    @Override
    public Double calcularCostoTotal() {
        return reserva.calcularCostoTotal() + 100.0; // Costo fijo de la excursión en tierra
    }

}
