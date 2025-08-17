package ec.edu.espol;

import java.util.Date;
import java.util.List;


public class ReservaBase implements Reserva {
    private int id;
    private ViajeCrucero viajeCrucero;
    private EstadoReserva estado;
    private Usuario usuario;
    private Date fechaReserva;
    private PoliticaCancelacion politicaCancelacion;
    private Notificacion notificacion;
    private Cabina cabina; 

    
    public ReservaBase(int id, ViajeCrucero vcrucero, Usuario usuario, Date fechaReserva, PoliticaCancelacion politicaCancelacion, String tipoCabina) {
        this.id=id; 
        this.viajeCrucero = vcrucero;
        this.usuario = usuario;
        this.fechaReserva = fechaReserva;
        this.politicaCancelacion = politicaCancelacion;
        this.notificacion = usuario.getMedio();
        //comprobar que la cabina es del crucero
        if (!viajeCrucero.verificarCabina(tipoCabina)) {
            throw new IllegalArgumentException("La cabina no pertenece a este crucero");
        }
        List<Cabina> cabinasDisponibles = viajeCrucero.buscarCabinasDisponibles(tipoCabina);
        if (cabinasDisponibles.isEmpty()) {
            throw new IllegalArgumentException("No hay cabinas disponibles del tipo " + tipoCabina);
        }else{
            this.cabina=cabinasDisponibles.get(0);
        }
        // usuario.getReservas().add(this);
        this.estado= EstadoReserva.RESERVADA;
    }

    public int getId() {
        return id;
    }
    
    @Override
    public Cabina getCabina() {
        return cabina;
    }

    @Override
    public void setCabina(Cabina cabina) {
        this.cabina = cabina;
    }

    public void setViajeCrucero(ViajeCrucero viajeCrucero) {
        this.viajeCrucero = viajeCrucero;
    }

    @Override
    public PoliticaCancelacion getPoliticaCancelacion() {
        return politicaCancelacion;
    }


    public EstadoReserva getEstado() {
        return this.estado;
    }

    @Override
    public Usuario getUsuario() {
        return this.usuario;
    }

    public Date getFechaReserva() {
        return this.fechaReserva;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }   

    public void actualizacionesImportantes(String mensaje) {
        if (mensaje == null) throw new IllegalArgumentException("El mensaje no puede ser nulo");
        notificacion.enviarNotificacion(mensaje);
    }

    @Override
    public Double calcularCostoTotal() {
        return 250.00;
    }

    public ViajeCrucero getViajeCrucero() {
        return viajeCrucero;
    }

    


    
    
}
