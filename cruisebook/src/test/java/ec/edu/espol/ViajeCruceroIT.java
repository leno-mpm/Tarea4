/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mile
 */
public class ViajeCruceroIT {
    
    @Test
    @DisplayName("Test Constructor lleno ViajeCrucero")
    public void testConstructor() {
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
        assertNotNull(viaje);
        assertEquals(fechaSalida, viaje.getFechaSalida());
        assertEquals(cabinas, viaje.getCabinas());
        assertEquals(itinerario, viaje.getItinerario());
        assertEquals(crucero, viaje.getCrucero());
    }

    @Test
    @DisplayName("Test Constructor vacio ViajeCrucero")
    public void testConstructorVacio() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new ViajeCrucero(null, null, null, null);
        });
        assertEquals("Los parámetros no pueden ser nulos", e.getMessage());
    }

   
    @Test
    @DisplayName("Constructor lanza excepción si cabinas es null")
    public void testConstructorCabinasNulas() {
        
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new ViajeCrucero(new Date(), null, "itinerario", new Crucero("Nombre", "Destino", "Desc", new PoliticaCancelacion("Flexible", 1)));
        });
        assertEquals("Los parámetros no pueden ser nulos", e.getMessage());
    }

    @Test
    @DisplayName("Constructor lanza excepción si itinerario es null")
    public void testConstructorItinerarioNulo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new ViajeCrucero(new Date(), new ArrayList<>(), null, new Crucero("Nombre", "Destino", "Desc",new PoliticaCancelacion("Flexible", 1)));
        });
        assertEquals("Los parámetros no pueden ser nulos", e.getMessage());
    }

    @Test
    @DisplayName("Constructor lanza excepción si crucero es null")
    public void testConstructorCruceroNulo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new ViajeCrucero(new Date(), new ArrayList<>(), "Itinerario", null);
        });
        assertEquals("Los parámetros no pueden ser nulos", e.getMessage());
    }

    @Test
    @DisplayName("Constructor lanza excepción si fechaSalida es null")
    public void testConstructorFechaSalidaNula() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new ViajeCrucero(null, new ArrayList<>(), "itinerario", new Crucero("Nombre", "Destino", "Desc", new PoliticaCancelacion("Flexible", 1)));
        });
        assertEquals("Los parámetros no pueden ser nulos", e.getMessage());
    }


    @Test
    @DisplayName("Test agregarReserva agrega la reserva, registra al usuario y cambia el estado de la cabina")
    public void testAgregarReserva() {
        Date fechaSalida = new Date();
        FabricaCabina fabricaCabina = new FBalcon();
        Cabina cabina1 = fabricaCabina.crearCabina(100.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Flexible", 1));
        List<Cabina> cabinas = new ArrayList<>();
        cabinas.add(cabina1);

        Crucero crucero = new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1));
        ViajeCrucero viaje = new ViajeCrucero(fechaSalida, cabinas, "Itinerario", crucero);
        crucero.agregarViaje(viaje);

        Notificacion n= new SMS();
        Usuario usuario = new Usuario("Juan", "juan@mail.com", "0992313206", n);
        Reserva reserva = new ReservaBase(1, viaje, usuario, fechaSalida, new PoliticaCancelacion("Flexible", 1), "Balcón");
        viaje.agregarReserva(reserva);
        usuario.agregarReserva(reserva);
        assertTrue(viaje.getReservas().contains(reserva));
        assertTrue(viaje.getObservadores().contains(usuario));
        assertEquals(EstadoCabina.OCUPADA, cabina1.getEstado());
        assertTrue(usuario.getReservas().contains(reserva));

    }

    @Test
    @DisplayName("Test agregarReserva agrega la reserva null")
    public void testAgregarReservaNull() {
        Date fechaSalida = new Date();
        FabricaCabina fabricaCabina = new FBalcon();
        Cabina cabina1 = fabricaCabina.crearCabina(100.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Flexible", 1));
        List<Cabina> cabinas = new ArrayList<>();
        cabinas.add(cabina1);
        Crucero crucero = new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1));
        ViajeCrucero viaje = new ViajeCrucero(fechaSalida, cabinas, "Itinerario", crucero);
        
        Exception e= assertThrows(IllegalArgumentException.class, ()-> {
            viaje.agregarReserva(null);
        });

        assertEquals(e.getMessage(), "No puede ser nula la reserva");
    }


    @Test
    @DisplayName("Test eliminarReserva con reserva null lanza excepción")
    public void testEliminarReservaNull() {
        Date fechaSalida = new Date();
        FabricaCabina fabricaCabina = new FBalcon();
        Cabina cabina1 = fabricaCabina.crearCabina(100.0, EstadoCabina.DISPONIBLE,
                new PoliticaCancelacion("Flexible", 1));
        List<Cabina> cabinas = new ArrayList<>();
        cabinas.add(cabina1);

        Crucero crucero = new Crucero("Crucero Caribe", "Caribe", "Desc",
                new PoliticaCancelacion("Flexible", 1));
        ViajeCrucero viaje = new ViajeCrucero(fechaSalida, cabinas, "Itinerario", crucero);
        crucero.agregarViaje(viaje);

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            viaje.eliminarReserva(null);
        });
        assertEquals("No puede ser nula la reserva", e.getMessage());
    }


    @Test
    @DisplayName("Test eliminarReserva libera la cabina y elimina observador")
    public void testEliminarReservaCorrecta() {
        Date fechaSalida = new Date();
        FabricaCabina fabricaCabina = new FBalcon();
        Cabina cabina1 = fabricaCabina.crearCabina(100.0, EstadoCabina.DISPONIBLE,
                new PoliticaCancelacion("Flexible", 1));
        List<Cabina> cabinas = new ArrayList<>();
        cabinas.add(cabina1);

        Crucero crucero = new Crucero("Crucero Caribe", "Caribe", "Desc",
                new PoliticaCancelacion("Flexible", 1));
        ViajeCrucero viaje = new ViajeCrucero(fechaSalida, cabinas, "Itinerario", crucero);
        crucero.agregarViaje(viaje);

        Notificacion n = new SMS();
        Usuario usuario = new Usuario("Juan", "juan@mail.com", "0992313206", n);
        Reserva reserva = new ReservaBase(1, viaje, usuario, fechaSalida,
                new PoliticaCancelacion("Flexible", 1), "Balcón");

        viaje.agregarReserva(reserva);
        assertEquals(EstadoCabina.OCUPADA, cabina1.getEstado());
        viaje.eliminarReserva(reserva);
        assertFalse(viaje.getReservas().contains(reserva));
        assertEquals(EstadoCabina.DISPONIBLE, cabina1.getEstado());
        assertFalse(viaje.getObservadores().contains(usuario));
    }

    @Test
    @DisplayName("Test agregarObservador con null lanza excepción")
    public void testAgregarObservadorNull() {
        Date fechaSalida = new Date();
        ViajeCrucero viaje = new ViajeCrucero(
                fechaSalida,
                new ArrayList<>(), "Itinerario", new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1))
        );

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            viaje.agregarObservador(null);
        });
        assertEquals("Debe existir el usuario en la reserva", e.getMessage());
    }

    @Test
    @DisplayName("Test agregarObservador agrega a la lista")
    public void testAgregarObservadorCorrecto() {
        Date fechaSalida = new Date();
        ViajeCrucero viaje = new ViajeCrucero(fechaSalida, new ArrayList<>(), "Itinerario", new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1)));
        Notificacion notificacion = new SMS();
        Usuario usuario = new Usuario("Juan", "juan@mail.com", "0992313206", notificacion);
        viaje.agregarObservador(usuario);
        assertTrue(viaje.getObservadores().contains(usuario));
    }

    @Test
    @DisplayName("Test eliminarObservador con null lanza excepción")
    public void testEliminarObservadorNull() {
        Date fechaSalida = new Date();
        ViajeCrucero viaje = new ViajeCrucero(fechaSalida,new ArrayList<>(),"Itinerario",new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1)));

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            viaje.eliminarObservador(null);
        });
        assertEquals("No existe usuario", e.getMessage());
    }

    @Test
    @DisplayName("Test eliminarObservador elimina de la lista")
    public void testEliminarObservadorCorrecto() {
        Date fechaSalida = new Date();
        ViajeCrucero viaje = new ViajeCrucero(fechaSalida, new ArrayList<>(), "Itinerario", new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1)));
        Notificacion notificacion = new SMS();
        Usuario usuario = new Usuario("Juan", "juan@mail.com", "0992313206", notificacion);
        viaje.agregarObservador(usuario);
        viaje.eliminarObservador(usuario);
        assertFalse(viaje.getObservadores().contains(usuario));
    }

    @Test
    @DisplayName("Asigna cabina cuando hay una disponible")
    void testAsignarCabinaConUnaDisponible() {
        FabricaCabina fabricaCabina = new FBalcon();
        Cabina cabinaOcupada = fabricaCabina.crearCabina(100.0, EstadoCabina.OCUPADA, new PoliticaCancelacion("Flexible", 1));
        FabricaCabina fabricaCabina2 = new FSuite();
        Cabina cabinaDisponible = fabricaCabina2.crearCabina(150.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Flexible", 2));
        List<Cabina> cabinas = new ArrayList<>();
        cabinas.add(cabinaOcupada);
        cabinas.add(cabinaDisponible);
        ViajeCrucero viaje = new ViajeCrucero(new Date(), cabinas, "Itinerario", new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1)));
        Cabina resultado = viaje.asignarCabinaDisponible();
        assertNotNull(resultado);
        assertEquals("Suite", resultado.getTipo());
        assertEquals(EstadoCabina.OCUPADA, resultado.getEstado());
    }

    @Test
    @DisplayName("Asigna la primera cabina disponible cuando hay múltiples")
    void testAsignarPrimeraCabinaDisponible() {
        
        FabricaCabina fabricaCabina = new FBalcon();
        Cabina primeraDisponible = fabricaCabina.crearCabina(100.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Flexible", 1));
        FabricaCabina fabricaCabina2 = new FSuite();
        Cabina segundaDisponible= fabricaCabina2.crearCabina(150.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Flexible", 2));
        List<Cabina> cabinas = new ArrayList<>();
        cabinas.add(primeraDisponible);
        cabinas.add(segundaDisponible);
        ViajeCrucero viaje = new ViajeCrucero(new Date(), cabinas, "Itinerario", new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1)));
        Cabina resultado = viaje.asignarCabinaDisponible();
        assertNotNull(resultado);
        assertEquals("Balcón", resultado.getTipo());
        assertEquals(EstadoCabina.OCUPADA, resultado.getEstado());
        assertEquals(EstadoCabina.DISPONIBLE, segundaDisponible.getEstado()); // La segunda sigue disponible
    }

    
    @Test
    @DisplayName("No asigna cabina cuando todas están ocupadas")
    void testNoAsignaCuandoTodasOcupadas() {
        FabricaCabina fabricaCabina = new FBalcon();
        Cabina primeraDisponible = fabricaCabina.crearCabina(100.0, EstadoCabina.OCUPADA, new PoliticaCancelacion("Flexible", 1));
        FabricaCabina fabricaCabina2 = new FSuite();
        Cabina segundaDisponible= fabricaCabina2.crearCabina(150.0, EstadoCabina.OCUPADA, new PoliticaCancelacion("Flexible", 2));
        List<Cabina> cabinas = new ArrayList<>();
        cabinas.add(primeraDisponible);
        cabinas.add(segundaDisponible);
        
        ViajeCrucero viaje = new ViajeCrucero(new Date(), cabinas, "Itinerario", new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1)));
        Cabina resultado = viaje.asignarCabinaDisponible();
        assertNull(resultado);
    }

    @Test
    @DisplayName("No asigna cabina cuando la lista está vacía")
    void testNoAsignaCuandoListaVacia() {
        List<Cabina> cabinas = new ArrayList<>();
        ViajeCrucero viaje = new ViajeCrucero(new Date(), cabinas, "Itinerario", new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1)));
        Cabina resultado = viaje.asignarCabinaDisponible();
        assertNull(resultado);
    }

    @Test
    @DisplayName("Buscar cabinas disponibles sin filtro de tipo")
    void testBuscarCabinasDisponibles_SinTipo() {
        FabricaCabina fabricaBalcon = new FBalcon();
        FabricaCabina fabricaSuite = new FSuite();
        
        Cabina balconOcupada = fabricaBalcon.crearCabina(100.0, EstadoCabina.OCUPADA, new PoliticaCancelacion("Flexible", 1));
        Cabina suiteDisponible = fabricaSuite.crearCabina(150.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Flexible", 2));
        Cabina balconDisponible = fabricaBalcon.crearCabina(100.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Estricta", 3));
        
        List<Cabina> cabinas = new ArrayList<>();
        cabinas.add(balconOcupada);
        cabinas.add(suiteDisponible);
        cabinas.add(balconDisponible);
        
        ViajeCrucero viaje = new ViajeCrucero(new Date(), cabinas, "Itinerario", 
            new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1)));
        List<Cabina> disponibles = viaje.buscarCabinasDisponibles(null);
        assertEquals(2, disponibles.size());
        assertTrue(disponibles.contains(suiteDisponible));
        assertTrue(disponibles.contains(balconDisponible));
    }

    @Test
    @DisplayName("Buscar cabinas disponibles filtrando por tipo")
    void testBuscarCabinasDisponibles_ConTipo() {
        FabricaCabina fabricaBalcon = new FBalcon();
        FabricaCabina fabricaSuite = new FSuite();
        Cabina balconDisponible = fabricaBalcon.crearCabina(100.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Flexible", 1));
        Cabina suiteDisponible = fabricaSuite.crearCabina(150.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Flexible", 2));
        Cabina balconOcupada = fabricaBalcon.crearCabina(100.0, EstadoCabina.OCUPADA, new PoliticaCancelacion("Estricta", 3));
        
        List<Cabina> cabinas = new ArrayList<>();
        cabinas.add(balconDisponible);
        cabinas.add(suiteDisponible);
        cabinas.add(balconOcupada);
        
        ViajeCrucero viaje = new ViajeCrucero(new Date(), cabinas, "Itinerario", 
            new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1)));
        List<Cabina> disponibles = viaje.buscarCabinasDisponibles("balcón");
        assertEquals(1, disponibles.size());
        assertEquals("Balcón", disponibles.get(0).getTipo());
        assertTrue(disponibles.get(0).isDisponible());
    }

    @Test
    @DisplayName("Buscar cabinas disponibles cuando no hay coincidencias")
    void testBuscarCabinasDisponibles_SinCoincidencias() {
        FabricaCabina fabrica = new FBalcon();
        Cabina balconOcupada = fabrica.crearCabina(100.0, EstadoCabina.OCUPADA, new PoliticaCancelacion("Flexible", 1));
        Cabina balconOcupada2 = fabrica.crearCabina(100.0, EstadoCabina.OCUPADA, new PoliticaCancelacion("Estricta", 2));
        List<Cabina> cabinas = new ArrayList<>();
        cabinas.add(balconOcupada);
        cabinas.add(balconOcupada2);
        
        ViajeCrucero viaje = new ViajeCrucero(new Date(), cabinas, "Itinerario", 
            new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1)));
        List<Cabina> disponibles = viaje.buscarCabinasDisponibles(null);
        assertTrue(disponibles.isEmpty());
    }


    @Test
    @DisplayName("Verificar cabina cuando existe el tipo")
    void testVerificarCabina_Existe() {
        FabricaCabina fabricaBalcon = new FBalcon();
        FabricaCabina fabricaSuite = new FSuite();
        Cabina balcon = fabricaBalcon.crearCabina(100.0, EstadoCabina.OCUPADA, new PoliticaCancelacion("Flexible", 1));
        Cabina suite = fabricaSuite.crearCabina(150.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Flexible", 2));
        List<Cabina> cabinas = new ArrayList<>();
        cabinas.add(balcon);
        cabinas.add(suite);
        ViajeCrucero viaje = new ViajeCrucero(new Date(), cabinas, "Itinerario", 
            new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1)));

        assertTrue(viaje.verificarCabina("balcón"));
        assertTrue(viaje.verificarCabina("SUITE")); // Prueba case insensitive
    }

    @Test
    @DisplayName("Verificar cabina cuando no existe el tipo")
    void testVerificarCabina_NoExiste() {
        FabricaCabina fabrica = new FBalcon();
        Cabina balcon = fabrica.crearCabina(100.0, EstadoCabina.OCUPADA, new PoliticaCancelacion("Flexible", 1));
        List<Cabina> cabinas = new ArrayList<>();
        cabinas.add(balcon);
        ViajeCrucero viaje = new ViajeCrucero(new Date(), cabinas, "Itinerario", 
            new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1)));
        assertFalse(viaje.verificarCabina("Suite"));
        assertFalse(viaje.verificarCabina("Económica"));
    }


    @Test
    @DisplayName("Verificar cabina con lista vacía")
    void testVerificarCabina_ListaVacia() {
        ViajeCrucero viaje = new ViajeCrucero(new Date(), new ArrayList<>(), "Itinerario", 
            new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1)));
        assertFalse(viaje.verificarCabina("Balcón"));
        assertFalse(viaje.verificarCabina(null));
    }

    @Test
    @DisplayName("Agregar nueva cabina válida")
    void testAddNuevaCabina_Valida() {
        ViajeCrucero viaje = new ViajeCrucero(
            new Date(), 
            new ArrayList<>(), 
            "Itinerario", 
            new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1))
        );
        FabricaCabina fabrica = new FBalcon();
        Cabina nuevaCabina = fabrica.crearCabina(100.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Flexible", 1));
        viaje.addNuevaCabina(nuevaCabina);
        assertEquals(1, viaje.getCabinas().size());
        assertTrue(viaje.getCabinas().contains(nuevaCabina));
    }

    @Test
    @DisplayName("Intentar agregar cabina nula")
    void testAddNuevaCabina_Nula() {
        ViajeCrucero viaje = new ViajeCrucero(
            new Date(), 
            new ArrayList<>(), 
            "Itinerario", 
            new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1))
        );
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            viaje.addNuevaCabina(null);
        });
        assertEquals("No debería ser nula", exception.getMessage());
        assertTrue(viaje.getCabinas().isEmpty());
    }

    @Test
    @DisplayName("Agregar múltiples cabinas")
    void testAddNuevaCabina_Multiples() {
        ViajeCrucero viaje = new ViajeCrucero(
            new Date(), 
            new ArrayList<>(), 
            "Itinerario", 
            new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1))
        );
        FabricaCabina fabricaBalcon = new FBalcon();
        FabricaCabina fabricaSuite = new FSuite();
        Cabina cabina1 = fabricaBalcon.crearCabina(100.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Flexible", 1));
        Cabina cabina2 = fabricaSuite.crearCabina(150.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Flexible", 2));
        viaje.addNuevaCabina(cabina1);
        viaje.addNuevaCabina(cabina2);
        assertEquals(2, viaje.getCabinas().size());
        assertTrue(viaje.getCabinas().contains(cabina1));
        assertTrue(viaje.getCabinas().contains(cabina2));
    }

    @Test
    @DisplayName("Cambiar itinerario válido")
    void testSetItinerario_Valido() {
        ViajeCrucero viaje = new ViajeCrucero(new Date(), new ArrayList<>(), "Itinerario Original", new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1)));
        String nuevoItinerario = "Nuevo Itinerario";
        viaje.setItinerario(nuevoItinerario);
        assertEquals(nuevoItinerario, viaje.getItinerario());
    }

    @Test
    @DisplayName("Intentar cambiar a itinerario nulo")
    void testSetItinerario_Nulo() {
        ViajeCrucero viaje = new ViajeCrucero(new Date(), new ArrayList<>(), "Itinerario Original", new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1)));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            viaje.setItinerario(null);
        });
        assertEquals("No se puede cambiar a un itinerario vacío o nulo", exception.getMessage());
        assertEquals("Itinerario Original", viaje.getItinerario()); // Verifica que no cambió
    }

    @Test
    @DisplayName("Intentar cambiar a itinerario vacío")
    void testSetItinerario_Vacio() {
        ViajeCrucero viaje = new ViajeCrucero(new Date(), new ArrayList<>(), "Itinerario Original", new Crucero("Crucero Caribe", "Caribe", "Desc", new PoliticaCancelacion("Flexible", 1)));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            viaje.setItinerario("");
        });
        assertEquals("No se puede cambiar a un itinerario vacío o nulo", exception.getMessage());
        assertEquals("Itinerario Original", viaje.getItinerario());
    }

}