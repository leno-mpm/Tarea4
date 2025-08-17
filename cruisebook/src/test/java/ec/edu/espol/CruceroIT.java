/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Mile
 */
public class CruceroIT {
    private Crucero crearCruceroValido() {
        PoliticaCancelacion politica = new PoliticaCancelacion("Cancelación flexible", 1);
        return new Crucero("Crucero1", "Caribe", "Viaje de lujo", politica);
    }

    private List<Cabina> crearListaCabinas() {
        FabricaCabina fabricaCabina = new FBalcon();
        Cabina cabina1 = fabricaCabina.crearCabina(100.0, EstadoCabina.DISPONIBLE,
                new PoliticaCancelacion("Flexible", 1));
        List<Cabina> cabinas = new ArrayList<>();
        cabinas.add(cabina1);
        return cabinas;
    }

    @Test
    void testAgregarViaje_valido() {
        Crucero crucero = crearCruceroValido();
        List<Cabina> cabinas = crearListaCabinas();
        ViajeCrucero viaje = new ViajeCrucero(new Date(2025 - 1900, 7, 15), cabinas, "Itinerario1", crucero);

        crucero.agregarViaje(viaje);

        assertEquals(1, crucero.getViajesProgramados().size());
    }

    @Test
    void testAgregarViaje_null() {
        Crucero crucero = crearCruceroValido();

        assertThrows(IllegalArgumentException.class, () -> crucero.agregarViaje(null));
    }

    @Test
    void testEliminarViaje_validoExistente() {
        Crucero crucero = crearCruceroValido();
        List<Cabina> cabinas = crearListaCabinas();
        ViajeCrucero viaje = new ViajeCrucero(new Date(2025 - 1900, 7, 15), cabinas, "Itinerario1", crucero);

        crucero.agregarViaje(viaje);
        assertEquals(1, crucero.getViajesProgramados().size());

        crucero.eliminarViaje(viaje);
        assertEquals(0, crucero.getViajesProgramados().size());
    }

    @Test
    void testEliminarViaje_null() {
        Crucero crucero = crearCruceroValido();
        List<Cabina> cabinas = crearListaCabinas();
        ViajeCrucero viaje = new ViajeCrucero(new Date(2025 - 1900, 7, 15), cabinas, "Itinerario1", crucero);

        crucero.agregarViaje(viaje);
        int sizeAntes = crucero.getViajesProgramados().size();

        crucero.eliminarViaje(null);

        assertEquals(sizeAntes, crucero.getViajesProgramados().size());
    }

    @Test
    void testEliminarViaje_validoNoExistente() {
        Crucero crucero = crearCruceroValido();
        List<Cabina> cabinas1 = crearListaCabinas();
        ViajeCrucero viaje1 = new ViajeCrucero(new Date(2025 - 1900, 7, 15), cabinas1, "Itinerario1", crucero);

        List<Cabina> cabinas2 = crearListaCabinas();
        ViajeCrucero viaje2 = new ViajeCrucero(new Date(2025 - 1900, 7, 16), cabinas2, "Itinerario2", crucero);

        crucero.agregarViaje(viaje1);
        int sizeAntes = crucero.getViajesProgramados().size();

        crucero.eliminarViaje(viaje2);

        assertEquals(sizeAntes, crucero.getViajesProgramados().size());
    }

    @Test
    void testBuscarViajePorFecha_existente() {
        Crucero crucero = crearCruceroValido();
        Date fecha = new Date(2025 - 1900, 7, 15);
        List<Cabina> cabinas = crearListaCabinas();
        ViajeCrucero viaje = new ViajeCrucero(fecha, cabinas, "Itinerario1", crucero);

        crucero.agregarViaje(viaje);

        ViajeCrucero resultado = crucero.buscarViajePorFecha(fecha);

        assertNotNull(resultado);
        assertEquals(fecha, resultado.getFechaSalida());
    }

    @Test
    void testBuscarViajePorFecha_noExistente() {
        Crucero crucero = crearCruceroValido();
        Date fechaExistente = new Date(2025 - 1900, 7, 15);
        List<Cabina> cabinas = crearListaCabinas();
        ViajeCrucero viaje = new ViajeCrucero(fechaExistente, cabinas, "Itinerario1", crucero);

        crucero.agregarViaje(viaje);

        Date fechaBusqueda = new Date(2025 - 1900, 7, 16);

        ViajeCrucero resultado = crucero.buscarViajePorFecha(fechaBusqueda);

        assertNull(resultado);
    }

    @Test
    void testBuscarViajePorFecha_null() {
        Crucero crucero = crearCruceroValido();

        assertThrows(IllegalArgumentException.class, () -> crucero.buscarViajePorFecha(null));
    }

    @Test
    void testMostrarViajesProgramados() {
        Crucero crucero = crearCruceroValido();
        List<Cabina> cabinas = crearListaCabinas();
        ViajeCrucero viaje = new ViajeCrucero(new Date(2025 - 1900, 7, 15), cabinas, "Itinerario1", crucero);

        crucero.agregarViaje(viaje);

        assertDoesNotThrow(() -> crucero.mostrarViajesProgramados());
    }
    
}
