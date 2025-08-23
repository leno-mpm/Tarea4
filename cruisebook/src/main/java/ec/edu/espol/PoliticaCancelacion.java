package ec.edu.espol;

public class PoliticaCancelacion {
    private int id;
    private String descripcion;

    public PoliticaCancelacion(String descripcion, int id) {
        if(descripcion==null){
            throw new IllegalArgumentException("Los paramatros no pueden ser nulos.");
        }
        this.descripcion = descripcion;
        this.id = id;
    }
    public boolean verificarCancelacion(Reserva reserva, Crucero crucero) {
        if(reserva==null) throw new IllegalArgumentException("Los paramatros no pueden ser nulos.");
        System.out.println(reserva.getEstado());
        boolean reservaEstadoReservada = reserva.getEstado() == EstadoReserva.RESERVADA;
        boolean politicasCongruentes = crucero.getPoliticaCancelacion().equals(reserva.getPoliticaCancelacion());
        
        return reservaEstadoReservada && politicasCongruentes;
    }

    public int getId() {
        return this.id;
    }   
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    

}
