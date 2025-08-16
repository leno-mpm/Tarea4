package ec.edu.espol;

public class FInterior extends FabricaCabina {

    @Override
    public Cabina crearCabina(double precio, EstadoCabina estado, PoliticaCancelacion politicaCancelacion) {
        return new CabinaInterior(precio, EstadoCabina.DISPONIBLE, politicaCancelacion);
    }

}
