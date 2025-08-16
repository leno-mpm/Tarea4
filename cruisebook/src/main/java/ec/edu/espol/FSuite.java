package ec.edu.espol;

public class FSuite extends FabricaCabina {

    @Override
    public Cabina crearCabina(double precio, EstadoCabina estado, PoliticaCancelacion politicaCancelacion) {
        return new CabinaSuite(precio, estado, politicaCancelacion);
    }

}
