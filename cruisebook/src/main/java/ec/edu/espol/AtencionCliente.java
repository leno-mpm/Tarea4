package ec.edu.espol;

public class AtencionCliente {
    private GerenciaCrucero gerencia;

    public AtencionCliente(GerenciaCrucero gerencia) {
        if(gerencia== null){throw new IllegalArgumentException("Los parametros no pueden ser nulos.");}
        this.gerencia = gerencia;
    }

    public boolean atenderProblema(String descripcion, Usuario usuario) {
        if(descripcion==null || usuario==null){
            throw new IllegalArgumentException("Los paramatros no pueden ser nulos.");
        }
        if(descripcion==""){
            throw new IllegalArgumentException("La descripcion no puede estar vacia.");
        }
        System.out.println("Atención al Cliente atiende problema: " + descripcion);
        // Simula intento de resolución (puede ser aleatorio, o con alguna regla)
        boolean solucionado = intentarResolver(descripcion);

        if (solucionado) {
            System.out.println("Problema resuelto en Atención al Cliente para usuario: " + usuario.getNombre());
            return true;
        }
        System.out.println("No se pudo resolver en Atención al Cliente.");
        return false;
    }

    private boolean intentarResolver(String descripcion) {
        // Lógica simple para simular resolución
        return true; // Si el problema es pago, no resuelven
    }

    public void escalarAGerencia(String descripcion, Usuario usuario) {
        gerencia.resolverProblema(descripcion, usuario);
    }
}
