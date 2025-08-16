package ec.edu.espol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FabricaCabina fabricaCabina = new FBalcon();
        Cabina cabinaOcupada = fabricaCabina.crearCabina(100.0, EstadoCabina.OCUPADA, new PoliticaCancelacion("Flexible", 1));
        FabricaCabina fabricaCabina2 = new FSuite();
        Cabina cabinaDisponible = fabricaCabina2.crearCabina(150.0, EstadoCabina.DISPONIBLE, new PoliticaCancelacion("Flexible", 2));
        List<Cabina> cabinas = new ArrayList<>();
        cabinas.add(cabinaOcupada);
        cabinas.add(cabinaDisponible);
       
        System.out.println("Tipo de cabina ocupada: " + cabinaOcupada.getTipo()+ cabinaOcupada.getEstado());
        System.out.println("Tipo de cabina disponible: " + cabinaDisponible.getTipo());

        Crucero crucero = new Crucero("Crucero del Caribe", "Caribe", 
                                     "Ruta por las islas", new PoliticaCancelacion("Flexible", 3));
        ViajeCrucero viaje = new ViajeCrucero(new Date(), cabinas, "Itinerario prueba", crucero);
        
        List<Cabina> cabinasprueba= viaje.getCabinas();
        if(cabinasprueba.isEmpty()){
            System.out.println("YES");
        }
        // 6. Verificar estado inicial
        System.out.println("\nEstado inicial de las cabinas:");
        for (Cabina c : cabinasprueba) {
            System.out.println("Cabina " + c.getTipo() + " - Estado: " + c.getEstado());
        }
        
    }
    
}
