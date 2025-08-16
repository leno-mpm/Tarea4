package ec.edu.espol;

public class CabinaInterior extends Cabina {

    public CabinaInterior(double precio, EstadoCabina estado, PoliticaCancelacion politicaCancelacion) {
        super(precio, estado, politicaCancelacion);
        setTipo("Interior");
    }

}
