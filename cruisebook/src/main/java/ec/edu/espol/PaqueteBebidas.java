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
