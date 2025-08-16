package ec.edu.espol;

public class CabinaBalcon extends Cabina {

    public CabinaBalcon(double precio, EstadoCabina estado, PoliticaCancelacion politicaCancelacion) {
        super(precio, estado, politicaCancelacion);
        setTipo("Balcón");
    }

}
