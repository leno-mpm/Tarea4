package ec.edu.espol;

public class CabinaSuite extends Cabina {

    public CabinaSuite(double precio, EstadoCabina estado, PoliticaCancelacion politicaCancelacion) {
        super(precio, estado, politicaCancelacion);
        setTipo("Suite");
    }

}
