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

    @Override
    public EstadoReserva getEstado() {
        return reserva.getEstado();
    }

    @Override
    public PoliticaCancelacion getPoliticaCancelacion() {
        return reserva.getPoliticaCancelacion();
    }
    
    @Override
    public void setEstado(EstadoReserva estado) {
        reserva.setEstado(estado);
    }

    @Override
    public Cabina getCabina() {
        return reserva.getCabina(); 
    }

    @Override
    public void setCabina(Cabina cabina) {
       reserva.setCabina(cabina);
    }

    @Override
    public Usuario getUsuario() {
        return reserva.getUsuario();
    }

    @Override
    public ViajeCrucero getViajeCrucero() {
        return reserva.getViajeCrucero();
    }

    @Override
    public int getId() {
        return reserva.getId();
    }

    @Override
    public void setFechaReserva(Date fechaReserva) {
        reserva.setFechaReserva(fechaReserva);
    }

    @Override
    public void setViajeCrucero(ViajeCrucero viajeCrucero) {
        reserva.setViajeCrucero(viajeCrucero);
    }
}
