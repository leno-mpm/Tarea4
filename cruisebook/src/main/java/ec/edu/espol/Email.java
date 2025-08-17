package ec.edu.espol;

public class Email implements Notificacion {

    @Override
    public boolean enviarNotificacion(String mensaje) {
        if (mensaje== null) {
            throw new IllegalArgumentException("No se puede enviar un mensaje Nulo");
        }
        System.out.println("Enviando notificación por mensajería: " + mensaje);
        return true;
    }

}
