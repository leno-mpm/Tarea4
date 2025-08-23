package ec.edu.espol;

import java.util.Date;

public abstract class ReservaDecorator implements Reserva {
    protected Reserva reserva;

    public ReservaDecorator(Reserva reserva) {
        this.reserva = reserva;
    }

    public abstract Double calcularCostoTotal();

    public void reservada(){
        reserva.reservada();
    }

    public void confirmada(){
        reserva.confirmada();
    }

    public void cancelada(){
        reserva.cancelada();
    }

    public void finalizada(){
        reserva.finalizada();
    }

    public void pendiente(){
        reserva.pendiente();
    }

}
