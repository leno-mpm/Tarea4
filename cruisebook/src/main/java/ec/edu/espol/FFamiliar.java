package ec.edu.espol;

public class FFamiliar extends FabricaCabina {
    @Override
    public Cabina crearCabina(double precio, EstadoCabina estado, PoliticaCancelacion politicaCancelacion) {
        return new CabinaFamiliar(precio, estado, politicaCancelacion);
    }
}
