package ec.edu.espol;

public class Email implements Notificacion {

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Enviando notificación por Email: " + mensaje);
    }

}
