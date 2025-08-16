package ec.edu.espol;

public class FBalcon extends FabricaCabina {

    @Override
    public Cabina crearCabina(double precio, EstadoCabina estado, PoliticaCancelacion politicaCancelacion) {
        return new CabinaBalcon(precio, estado, politicaCancelacion);
    }

}
