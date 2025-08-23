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
    @Override
    public EstadoReserva getEstado() {
        return reserva.getEstado();
    }
     @Override
    public Crucero getCrucero() {
        return reserva.getCrucero();
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
