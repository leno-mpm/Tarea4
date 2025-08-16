package ec.edu.espol;

public class Mensajeria implements Notificacion{

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Enviando notificación por mensajería: " + mensaje);
    }
}
