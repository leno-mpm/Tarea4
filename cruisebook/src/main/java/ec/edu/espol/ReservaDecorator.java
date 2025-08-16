package ec.edu.espol;

public abstract class ReservaDecorator implements Reserva {
    protected Reserva reserva;

    public ReservaDecorator(Reserva reserva) {
        this.reserva = reserva;
    }

    public abstract Double calcularCostoTotal();
}
