package ec.edu.espol;

public class ServicioIncidentes {

    public void reportarIncidente(Usuario usuario, String detalle) {
        if (detalle == null) throw new IllegalArgumentException("No se puede mandar un detalle nulo");
        System.out.println("Reporte de incidente del usuario " + usuario.getNombre() + ": " + detalle);
    }
}
