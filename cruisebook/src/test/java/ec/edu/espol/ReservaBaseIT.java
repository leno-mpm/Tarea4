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
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Mile
 */
public class ReservaBaseIT {
   private ViajeCrucero crearViajeCruceroValido() {
        FabricaCabina fabricaCabina = new FBalcon();
        Cabina cabina1 = fabricaCabina.crearCabina(100.0, EstadoCabina.DISPONIBLE,
        new PoliticaCancelacion("Flexible", 1));
        List<Cabina> cabinas = new ArrayList<>();
        cabinas.add(cabina1);

        Crucero crucero = new Crucero("Crucero1", "Caribe", "Descripcion", new PoliticaCancelacion("Flexible", 1));
        ViajeCrucero viaje = new ViajeCrucero(new Date(2025 - 1900, 7, 15), cabinas, "Itinerario1", crucero);

        return viaje;
    }

    private Usuario crearUsuarioConSMS() {
        return new Usuario("Juan", "juan@email.com", "1234567890", new SMS());
    }

    @Test
    void testActualizacionesImportantes_null() {
        ViajeCrucero viaje = crearViajeCruceroValido();
        Usuario usuario = crearUsuarioConSMS();

        ReservaBase reserva = new ReservaBase(1, viaje, usuario, new Date(), new PoliticaCancelacion("", 1), "FBalcon");

        assertThrows(IllegalArgumentException.class, () -> reserva.actualizacionesImportantes(null));
    }

    @Test
    void testActualizacionesImportantes_valido() {
        ViajeCrucero viaje = crearViajeCruceroValido();
        Usuario usuario = crearUsuarioConSMS();

        ReservaBase reserva = new ReservaBase(1, viaje, usuario, new Date(), new PoliticaCancelacion("", 1), "FBalcon");

        assertDoesNotThrow(() -> reserva.actualizacionesImportantes("Mensaje importante"));
    }

    @Test
    void testCalcularCostoTotal() {
        ViajeCrucero viaje = crearViajeCruceroValido();
        Usuario usuario = crearUsuarioConSMS();

        ReservaBase reserva = new ReservaBase(1, viaje, usuario, new Date(), new PoliticaCancelacion("", 1), "FBalcon");

        Double costo = reserva.calcularCostoTotal();
        assertEquals(250.0, costo);
    }
}
