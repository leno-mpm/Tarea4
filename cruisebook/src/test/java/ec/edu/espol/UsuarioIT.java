/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espol;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Gabriel
 */
public class UsuarioIT {
    static ViajeCrucero viajeCrucero;
    static Usuario usuario;
    static Reserva reservaBase;
    static Crucero crucero;
    static PoliticaCancelacion politicaCancelacion;
    static Cabina cabina;

    @BeforeAll
    static void antesDeTodo(){
        politicaCancelacion = new PoliticaCancelacion("Flexible", 10);
        List<Cabina> cabinas = List.of(new CabinaBalcon(3, EstadoCabina.DISPONIBLE, politicaCancelacion), new CabinaSuite(4, EstadoCabina.DISPONIBLE, politicaCancelacion),new CabinaSuite(4, EstadoCabina.DISPONIBLE, politicaCancelacion));
        crucero = new Crucero("Crucero Caribe", "Caribe", "Un viaje por el Caribe", politicaCancelacion);
        viajeCrucero = new ViajeCrucero(new Date(), cabinas, "Itinerario", crucero);
        usuario = new Usuario("Gabriel", "ejemplo@gmail.com", "0935267281", new Email());
        reservaBase = new ReservaBase(0, viajeCrucero, usuario, new Date(), politicaCancelacion, "Suite");
        usuario.agregarReserva(reservaBase);
        viajeCrucero.agregarReserva(reservaBase);

    }

    @Test
    @DisplayName("Se deberia de crear todo correctamente")
    void testConstructor(){
        Notificacion notificacion = new Mensajeria();
        Usuario usuario2 = new Usuario("Gabriel", "ejemplo3@gmail.com", "0983527856", notificacion);
        assertNotNull(usuario2);
        assertEquals(notificacion, usuario2.getMedio());
    }

    @Test
    @DisplayName("Deberia lanzar un IllegalArgumentException con un mensaje que dice que los parametros no pueden ser nulo")
    void testConstructorArgumentosNulo(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario(null, null, null, null);
        });
        assertEquals("Los parametros no pueden ser nulos", e.getMessage());
    }

    @Test
    @DisplayName("Deberia lanzar un IllegalArgumentException con un mensaje que dice que los parametros no pueden ser nulo")
    void testConstructorNombreNulo(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario(null, "ejemplo3@gmail.com", "0983527856", new Mensajeria());
        });
        assertEquals("Los parametros no pueden ser nulos", e.getMessage());
    }

    @Test
    @DisplayName("Deberia lanzar un IllegalArgumentException con un mensaje que dice que los parametros no pueden ser nulo")
    void testConstructorEmailNulo(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("Gabriel", null, "0983527856", new Mensajeria());
        });
        assertEquals("Los parametros no pueden ser nulos", e.getMessage());
    }

    @Test
    @DisplayName("Deberia lanzar un IllegalArgumentException con un mensaje que dice que los parametros no pueden ser nulo")
    void testConstructorTelefonoNulo(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("Gabriel", "ejemplo3@gmail.com", null, new Mensajeria());
        });
        assertEquals("Los parametros no pueden ser nulos", e.getMessage());
    }

    @Test
    @DisplayName("Deberia lanzar un IllegalArgumentException con un mensaje que dice que los parametros no pueden ser nulo")
    void testConstructorNotificacionNulo(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("Gabriel", "ejemplo3@gmail.com", "0983527856", null);
        });
        assertEquals("Los parametros no pueden ser nulos", e.getMessage());
    }


    @Test
    @DisplayName("Deberia lanzar un IllegalArgumentException con un mensaje de que no se puede enviar un mensaje nulo")
    void testNotificarMensajeNulo(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            usuario.notificar(null);
        });
        assertEquals("No se puede enviar un mensaje Nulo", e.getMessage());
    }

    @Test
    @DisplayName("Deberia retornar True ya que es un mensaje que si se puede enviar")
    void testNotificar(){
        assertTrue(usuario.notificar("Haz sacado 100 en la tarea de diseño."));
    }
}
