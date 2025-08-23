package ec.edu.espol;

import java.util.Date;

public class PaqueteBebidas extends ReservaDecorator {

    public PaqueteBebidas(Reserva reserva) {
        super(reserva);
    }

    @Override
    public Double calcularCostoTotal() {
        return reserva.calcularCostoTotal() + 100.0; // Costo fijo del paquete de bebidas
    }
}
