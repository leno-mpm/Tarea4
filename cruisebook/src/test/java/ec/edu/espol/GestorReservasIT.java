/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espol;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author danilo
 */
public class GestorReservasIT {
    static ViajeCrucero viajeCrucero;
    static Usuario usuario;
    static Reserva reservaBase;
    static Crucero crucero;
    static PoliticaCancelacion politicaCancelacion;
    static Cabina cabina;
    static GestorReservas gestorReservas;
    
    
    @BeforeAll
    static void antesDeTodo(){
        politicaCancelacion = new PoliticaCancelacion("Flexible", 10);
        List<Cabina> cabinas = List.of(new CabinaBalcon(3, EstadoCabina.DISPONIBLE, politicaCancelacion), new CabinaSuite(4, EstadoCabina.DISPONIBLE, politicaCancelacion),new CabinaSuite(4, EstadoCabina.DISPONIBLE, politicaCancelacion));
        crucero = new Crucero("Crucero Caribe", "Caribe", "Un viaje por el Caribe", politicaCancelacion);
        viajeCrucero = new ViajeCrucero(new Date(), cabinas, "Itinerario", crucero);
        usuario = new Usuario("Danilo", "danilo@gmail.com", "0935267281", new Email());
        reservaBase = new ReservaBase(0, viajeCrucero, usuario, new Date(), politicaCancelacion, "Suite");
        usuario.agregarReserva(reservaBase);
        viajeCrucero.agregarReserva(reservaBase);
        gestorReservas = new GestorReservas();
    }

    @Test
    @DisplayName("Se debe lanzar una excepción que no permita recibir un usuario nulo")
    public void testProcesarAccionUsuarioNulo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            gestorReservas.procesarAccion(null, reservaBase, "");
        });
        assertEquals("El usuario no puede ser nulo", e.getMessage());
    }

    @Test
    @DisplayName("Se debe lanzar una excepción que no permita recibir una reserva nula")
    public void testProcesarAccionReservaNula() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            gestorReservas.procesarAccion(usuario, null, "");
        });
        assertEquals("La reserva no puede ser nula", e.getMessage());
    }

    @Test
    @DisplayName("Se debe lanzar una excepción que no permita recibir una opción nula")
    public void testProcesarAccionOpcionNula() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            gestorReservas.procesarAccion(usuario, reservaBase, null);
        });
        assertEquals("La opción no puede ser nula", e.getMessage());
    }

    @Test
    @DisplayName("El método debe manejar y ejecutar sin problemas un String opción vacía")
    public void testProcesarAccionOpcionVacia() {
        assertDoesNotThrow(() -> {
            gestorReservas.procesarAccion(usuario, reservaBase, "");
        });
    }

    @Test
    @DisplayName("El método debe manejar y ejecutar sin problemas un String cualquiera no vacío")
    public void testProcesarAccionOpcionNoVacia() {
        assertDoesNotThrow(() -> {
            gestorReservas.procesarAccion(usuario, reservaBase, "M");
        });
    }

    @Test
    @DisplayName("Se debe lanzar una excepción que no permita recibir un usuario nulo")
    public void testCancelarReservaConReembolsoUsuarioNulo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            gestorReservas.cancelarReservaConReembolso(null, reservaBase);
        });
        assertEquals("El usuario no puede ser nulo", e.getMessage());
    }

    @Test
    @DisplayName("Se debe lanzar una excepción que no permita recibir una reserva nula")
    public void testCancelarReservaConReembolsoReservaNula() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            gestorReservas.cancelarReservaConReembolso(usuario, null);
        });
        assertEquals("La reserva no puede ser nula", e.getMessage());
    }

    @Test
    @DisplayName("El método debe correr, la lista de reservas en ViajeCrucero debe disminuir en 1, y el estado de la reserva debe ser Cancelada.")
    public void testCancelarReservaConReembolsoValido() {
        int lengthReservasPrevious = viajeCrucero.getReservas().size();
        gestorReservas.cancelarReservaConReembolso(usuario, reservaBase);
        assertEquals(lengthReservasPrevious-1, viajeCrucero.getReservas().size());
        assertEquals(EstadoReserva.CANCELADA, reservaBase.getEstado());
    }

    @Test
    @DisplayName("Se debe lanzar una excepción que no permita recibir una reserva nula")
    public void testModificarReservaSinCargoReservaNula() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            gestorReservas.modificarReservaSinCargo(null);
        });
        assertEquals("La reserva no puede ser nula", e.getMessage());
    }

    @Test
    @DisplayName("El método debe ejecutarse sin problemas y el estado de la reserva debe ser confirmada")
    public void testModificarReservaSinCargoReservaValida() {
        gestorReservas.modificarReservaSinCargo(reservaBase);
        assertEquals(EstadoReserva.CONFIRMADA, reservaBase.getEstado());
    }

}
