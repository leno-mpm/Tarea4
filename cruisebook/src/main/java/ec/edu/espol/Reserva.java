package ec.edu.espol;

import java.util.Date;

public interface Reserva {
    public Double calcularCostoTotal();
    
    public EstadoReserva getEstado();

    public void setEstado(EstadoReserva estado);

    public PoliticaCancelacion getPoliticaCancelacion();

    public Usuario getUsuario();

    public Cabina getCabina();

    public void setCabina(Cabina cabina);

    public ViajeCrucero getViajeCrucero();

    public int getId();

    public void setFechaReserva(Date fechaReserva);

    public void setViajeCrucero(ViajeCrucero viajeCrucero);

    public void reservada();

    public void confirmada();

    public void cancelada();

    public void finalizada();

    public void pendiente();
}
