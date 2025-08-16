package ec.edu.espol;

public class PoliticaCancelacion {
    private int id;
    private String descripcion;

    public PoliticaCancelacion(String descripcion, int id) {
        this.descripcion = descripcion;
        this.id = id;
    }
    public boolean verificarCancelacion(Reserva reserva) {
        if (reserva.getEstado() == EstadoReserva.CONFIRMADA) {
            return true;
        }
        return false;
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
