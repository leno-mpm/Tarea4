package ec.edu.espol;

public class SMS implements Notificacion {
    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Enviando notificación por SMS: " + mensaje);
    }
}
