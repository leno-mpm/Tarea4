/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espol;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Mile
 */
public class PoliticaCancelacionIT {
    @Test
    void testPoliticaCancelacion(){
        PoliticaCancelacion politicas= new PoliticaCancelacion("Descripcion", 1);
    }

    @Test
    void testPoliticaCancelacion_DescripcionNull(){
        Exception e= assertThrows(IllegalArgumentException.class, ()->{new PoliticaCancelacion(null, 1);});
        assertEquals("Los paramatros no pueden ser nulos.", e.getMessage());
    }

    @Test
    void testVerificarCancelacion(){
        Date fechaSalida = new Date();
        FabricaCabina fabricaCabina = new FBalcon();
        Cabina cabina1 = fabricaCabina.crearCabina(100.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Flexible", 1));
        Cabina cabina2 = fabricaCabina.crearCabina(80.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Estandar", 2));
        Cabina cabina3 = fabricaCabina.crearCabina(60.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Economica", 3));
        FabricaCabina fabricaCabina2= new FFamiliar();
        Cabina cabina4 = fabricaCabina2.crearCabina(120.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Familiar", 4));
        List<Cabina> cabinas = List.of(cabina1, cabina2, cabina3, cabina4);    
        String itinerario = "Puerto Rico - Bahamas";
        Crucero crucero = new Crucero("Crucero Caribe", "Caribe", "Un viaje por el Caribe", new PoliticaCancelacion("Flexible", 1));
        ViajeCrucero viaje = new ViajeCrucero(fechaSalida, cabinas, itinerario, crucero);
        Usuario usuario = new Usuario("Juan", "juan@email.com", "1234567890", new SMS());
        Reserva Reserva=new ReservaBase(0, viaje, usuario, null, null, cabina1.getTipo());
        PoliticaCancelacion politicas= new PoliticaCancelacion("Descripcion", 1);
        politicas.verificarCancelacion(Reserva);
        assertTrue( politicas.verificarCancelacion(Reserva));
    }

        @Test
    void testVerificarCancelacion_ReservaNull(){
        PoliticaCancelacion politicas= new PoliticaCancelacion("Descripcion", 1);
        Exception e= assertThrows(IllegalArgumentException.class, ()->{politicas.verificarCancelacion(null);});
        assertEquals("Los paramatros no pueden ser nulos.", e.getMessage());
        
    }
    
}
