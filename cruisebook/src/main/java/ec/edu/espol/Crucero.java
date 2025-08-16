package ec.edu.espol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Crucero {
    private String nombre;
    private String destino;
    private String descripcion;
    public List<ViajeCrucero> viajesProgramados = new ArrayList<>();
    PoliticaCancelacion politicaCancelacion;
    

    public Crucero(String nombre, String destino, String descripcion, PoliticaCancelacion politicaCancelacion) {
        this.nombre = nombre;
        this.destino = destino;
        this.descripcion = descripcion;
        this.politicaCancelacion = politicaCancelacion;
    }

    public void agregarViaje(ViajeCrucero viaje) {
        viajesProgramados.add(viaje);
    }

    public void eliminarViaje(ViajeCrucero viaje) {
        viajesProgramados.remove(viaje);
    }

    public List<ViajeCrucero> getViajesProgramados() {
        return viajesProgramados;
    }

    // Getters y setters para nombre, destino, descripcion
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //mostrar las fechas de los viajes programados
    public void mostrarViajesProgramados() {
        System.out.println("Viajes programados para el crucero " + nombre + ":");
        for (ViajeCrucero viaje : viajesProgramados) {
            System.out.println("Fecha de salida: " + viaje.getFechaSalida());
        }
    }

    public ViajeCrucero buscarViajePorFecha(Date nuevaFecha) {
        for (ViajeCrucero viaje : viajesProgramados) {
            if (viaje.getFechaSalida().equals(nuevaFecha)) {
                return viaje;
            }
        }
        return null; // No se encontró un viaje con la fecha especificada
    }
    

}
