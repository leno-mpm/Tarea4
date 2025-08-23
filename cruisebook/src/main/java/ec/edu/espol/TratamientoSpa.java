package ec.edu.espol;

import java.util.Date;

public class TratamientoSpa extends ReservaDecorator {

    public TratamientoSpa(Reserva reserva) {
        super(reserva);
    }

    @Override
    public Double calcularCostoTotal() {
        return reserva.calcularCostoTotal() + 150.0; // Costo fijo del tratamiento de spa
    }
}
