package ec.edu.espol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViajeCrucero {
    private Date fechaSalida;
    private List<Cabina> cabinas;// {Balcon, Suite, Estándar, Económica};
    private List<Reserva> reservas = new ArrayList<>();
    private String itinerario;
    private List<Observador> observadores = new ArrayList<>();
    private Crucero crucero;

    public ViajeCrucero(Date fechaSalida, List<Cabina> cabinas, String itinerario, Crucero crucero) {//Asumir que cuando se crea un viaje, ya se tienen las cabinas disponibles
        if(fechaSalida == null || cabinas == null || itinerario == null || crucero == null) {
            throw new IllegalArgumentException("Los parámetros no pueden ser nulos");
        }
        this.fechaSalida = fechaSalida;
        this.cabinas=cabinas;
        this.itinerario = itinerario;
        this.crucero = crucero;
    }
    
    public void agregarReserva(Reserva reserva) {
        if(reserva==null){
            throw new IllegalArgumentException("No puede ser nula la reserva");
        }
        reservas.add(reserva);
        agregarObservador(reserva.getUsuario());
        for(Cabina cabina : cabinas) {
            if (cabina.equals(reserva.getCabina())) {
                cabina.setEstado(EstadoCabina.OCUPADA);
                break;
            }
        }
    }

    public Date getFecha(){
        return fechaSalida;
    }

    public void eliminarReserva(Reserva reserva) {
        if(reserva==null){
            throw new IllegalArgumentException("No puede ser nula la reserva");
        }
        for (Cabina cabina : cabinas) {
            if (cabina.equals(reserva.getCabina())) {
                cabina.setEstado(EstadoCabina.DISPONIBLE);
                break;
            }   
        }
        reservas.remove(reserva);
        eliminarObservador(reserva.getUsuario());
    }
        

    // Métodos para observadores
    public void agregarObservador(Observador observador) {
        if(observador==null){
            throw new IllegalArgumentException("Debe existir el usuario en la reserva");
        }
        observadores.add(observador);
    }

    public void eliminarObservador(Observador observador) {
        if(observador==null){
            throw new IllegalArgumentException("No existe usuario");
        }
        observadores.remove(observador);
    }

    private void notificarObservadores(String mensaje) {
       List<Observador> copiaObservadores = new ArrayList<>(observadores);
        for (Observador observador : copiaObservadores) {
            observador.notificar(mensaje);
            NotificadorReserva.gestionarNotificacion(observador, this, mensaje);
        }
    }

    public List<Observador> getObservadores(){
        return observadores;
    }

    // Reprogramar fecha de salida
    public void reprogramarFecha(Date nuevaFecha) {
        if(nuevaFecha==null){
            throw new IllegalArgumentException("No se puede cambiar a una fecha nula");
        }
        this.fechaSalida = nuevaFecha;
        notificarObservadores("El viaje del crucero: ha sido reprogramado para el " + nuevaFecha);
    }

    // Cancelar viaje
    public void cancelarViaje() {
        notificarObservadores("El viaje del crucero para la fecha " + fechaSalida + " ha sido cancelado. Se procesarán reembolsos automáticos o podrá modificar su reserva sin cargos.");
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public List<Cabina> getCabinas() {
        return cabinas;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void addNuevaCabina(Cabina cabina) {
        if(cabina==null){
            throw new IllegalArgumentException("No debería ser nula");
        }
        cabinas.add(cabina);
    }

    public String getItinerario() {
        return itinerario;
    }

    public void setItinerario(String itinerario) {
        if(itinerario==null || itinerario.isEmpty()){
            throw new IllegalArgumentException("No se puede cambiar a un itinerario vacío o nulo");
        }
        this.itinerario = itinerario;
        notificarObservadores("El itinerario del viaje ha cambiado: " + itinerario);
    }

    public List<Cabina> buscarCabinasDisponibles(String tipo) {
        List<Cabina> disponibles = new ArrayList<>();
        for (Cabina c : cabinas) {
            if (c.isDisponible() && (tipo == null || c.getTipo().equalsIgnoreCase(tipo))) {
                disponibles.add(c);
            }
        }
        return disponibles;
    }

    public boolean verificarCabina(String tipo) {
        for (Cabina c : cabinas) {
            if (c.getTipo().equalsIgnoreCase(tipo)) {
                return true;
            }
        }
        return false;
    }

    public Crucero getCrucero() {
        return crucero;
    }

    public Cabina asignarCabinaDisponible() {
        for (Cabina c : cabinas) {
            if (c.getEstado()==EstadoCabina.DISPONIBLE) {
                c.setEstado(EstadoCabina.OCUPADA);
                return c;
            }
        }
        return null; 
    }
        
}
