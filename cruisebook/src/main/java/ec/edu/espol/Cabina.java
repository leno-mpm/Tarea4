package ec.edu.espol;

public abstract class Cabina {
    private double precio;
    private String tipo;
    private EstadoCabina estado;
    private PoliticaCancelacion politicaCancelacion;

    public Cabina(double precio, EstadoCabina estado, PoliticaCancelacion politicaCancelacion) {
        this.precio = precio;
        this.estado = estado;
        this.politicaCancelacion = politicaCancelacion;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public EstadoCabina getEstado() {
        return estado;
    }
    public void setEstado(EstadoCabina estado) {
        this.estado = estado;
    }
    public PoliticaCancelacion getPoliticaCancelacion() {
        return politicaCancelacion;
    }
    public void setPoliticaCancelacion(PoliticaCancelacion politicaCancelacion) {
        this.politicaCancelacion = politicaCancelacion;
    }
    public String getTipo() {
        return tipo;
    }       
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public boolean isDisponible() {
        return this.estado == EstadoCabina.DISPONIBLE;
    }
    
}
