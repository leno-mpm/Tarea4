package ec.edu.espol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Configuración inicial
        // Crear políticas de cancelación
        PoliticaCancelacion politicaEstandar = new PoliticaCancelacion("Cancelación estándar 48h antes", 1);
        
        // Crear medios de notificación
        Notificacion notificacionEmail = new Email();
        Notificacion notificacionSMS = new SMS();
        
        // Crear gerencia
        GerenciaCrucero gerencia = new GerenciaCrucero("Gerencia Caribe", "gerencia@cruceros.com", "123456789", 1);
        
        // Crear atención al cliente
        AtencionCliente atencionCliente = new AtencionCliente(gerencia);
        
        // 2. Crear crucero y viajes
        // Crear fábricas de cabinas
        FabricaCabina fabricaBalcon = new FBalcon();
        FabricaCabina fabricaSuite = new FSuite();
        FabricaCabina fabricaFamiliar = new FFamiliar();
        FabricaCabina fabricaInterior = new FInterior();
        
        // Crear lista de cabinas para el viaje
        List<Cabina> cabinasViaje1 = new ArrayList<>();
        cabinasViaje1.add(fabricaBalcon.crearCabina(1200.0, EstadoCabina.DISPONIBLE, politicaEstandar));
        cabinasViaje1.add(fabricaSuite.crearCabina(2000.0, EstadoCabina.DISPONIBLE, politicaEstandar));
        cabinasViaje1.add(fabricaFamiliar.crearCabina(1500.0, EstadoCabina.DISPONIBLE, politicaEstandar));
        cabinasViaje1.add(fabricaInterior.crearCabina(800.0, EstadoCabina.DISPONIBLE, politicaEstandar));
        
        // Crear crucero
        Crucero caribePrincess = new Crucero("Caribe Princess", "Caribe", "Crucero de lujo por el Caribe", politicaEstandar);
        
        // Crear viaje
        Date fechaViaje1 = new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30)); // 30 días después
        ViajeCrucero viaje1 = new ViajeCrucero(fechaViaje1, cabinasViaje1, "Ruta por Bahamas y Cozumel", caribePrincess);
        caribePrincess.agregarViaje(viaje1);
        
        // 3. Crear usuarios
        Usuario usuario1 = new Usuario("Juan Pérez", "juan@email.com", "555-1234", notificacionEmail);
        Usuario usuario2 = new Usuario("María García", "maria@email.com", "555-5678", notificacionSMS);
        
        // 4. Proceso de reserva
        System.out.println("\n=== Simulación de reserva ===");
        
        // Reserva base
        Reserva reservaJuan = new ReservaBase(1, viaje1, usuario1, new Date(), politicaEstandar, "Balcón");
        System.out.println("Reserva creada para Juan - ID: " + reservaJuan.getId());
        System.out.println("Costo base: $" + reservaJuan.calcularCostoTotal());
        
        // Agregar decoradores (servicios adicionales)
        reservaJuan = new ExcursionTierra(reservaJuan);
        reservaJuan = new PaqueteBebidas(reservaJuan);
        System.out.println("Costo total con servicios adicionales: $" + reservaJuan.calcularCostoTotal());
        usuario1.agregarReserva(reservaJuan);
        viaje1.agregarReserva(reservaJuan);
        
        // 5. Simular atención al cliente
        System.out.println("\n=== Atención al cliente ===");
        boolean resuelto = atencionCliente.atenderProblema("Problema con la cabina asignada", usuario1);
        if (!resuelto) {
            atencionCliente.escalarAGerencia("Problema persistente con cabina", usuario1);
        }
        
        // 6. Simular cambio de itinerario
        System.out.println("\n=== Cambio de itinerario ===");
        viaje1.setItinerario("Nueva ruta: Jamaica y Gran Caimán");
        
        // 7. Simular cancelación de viaje
        System.out.println("\n=== Cancelación de viaje ===");
        viaje1.cancelarViaje();
        
        // 8. Otra reserva para demostrar diferentes tipos
        System.out.println("\n=== Segunda reserva ===");
        Reserva reservaMaria = new ReservaBase(2, viaje1, usuario2, new Date(), politicaEstandar, "Suite");
        reservaMaria = new TratamientoSpa(reservaMaria);
        usuario2.agregarReserva(reservaMaria);
        viaje1.agregarReserva(reservaMaria);
        System.out.println("Reserva creada para María - ID: " + reservaMaria.getId());
        System.out.println("Costo total con spa: $" + reservaMaria.calcularCostoTotal());
        
        // 9. Mostrar estado de cabinas
        System.out.println("\n=== Estado de cabinas ===");
        for (Cabina cabina : viaje1.getCabinas()) {
            System.out.println("Cabina " + cabina.getTipo() + ": " + cabina.getEstado());
        }
        
        // 10. Probar cancelación de reserva
        System.out.println("\n=== Cancelación de reserva ===");
        usuario2.cancelarReserva(reservaMaria, viaje1.getCrucero());
        System.out.println("Estado de la reserva después de cancelar: " + reservaMaria.getEstado());
        
    }
    
}
