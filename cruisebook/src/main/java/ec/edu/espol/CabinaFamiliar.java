package ec.edu.espol;

public class CabinaFamiliar extends Cabina {

    public CabinaFamiliar(double precio, EstadoCabina estado, PoliticaCancelacion politicaCancelacion) {
        super(precio, estado, politicaCancelacion);
        setTipo("Familiar");
    }

}
