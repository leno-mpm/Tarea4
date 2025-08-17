/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espol;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mile
 */
public class AtencionClienteIT {
    @Test
    void testAtenderProblema(){
        AtencionCliente atencioncliente= new AtencionCliente(null);
        Usuario usuario = new Usuario("Francis Loayza", "faloayza@espol.edu.ec", "0955461934", new SMS());
        boolean solucion =atencioncliente.atenderProblema("Problema", usuario);
        assertTrue(solucion);
    }
    @Test
    void testAtenderProblema_UsuarioNull(){
        AtencionCliente atencioncliente= new AtencionCliente(null);
        Exception e= assertThrows(IllegalArgumentException.class,()->{atencioncliente.atenderProblema("Problema", null);});
        assertEquals("Los paramatros no pueden ser nulos.",e.getMessage());

    }
     
    @Test
    void testAtenderProblema_DescripcionNull(){
        AtencionCliente atencioncliente= new AtencionCliente(null);
        Usuario usuario = new Usuario("Francis Loayza", "faloayza@espol.edu.ec", "0955461934", new SMS());
        Exception e= assertThrows(IllegalArgumentException.class,()->{atencioncliente.atenderProblema(null, usuario);});
        assertEquals("Los paramatros no pueden ser nulos.",e.getMessage());
    }
    
    @Test
    void testAtenderProblema_DescripcionVacia(){
        AtencionCliente atencioncliente= new AtencionCliente(null);
        Usuario usuario = new Usuario("Francis Loayza", "faloayza@espol.edu.ec", "0955461934", new SMS());
        Exception e= assertThrows(IllegalArgumentException.class,()->{atencioncliente.atenderProblema("", usuario);});
        assertEquals("La descripcion no puede estar vacia.",e.getMessage());
    }

    @Test
    void testEscalarAGerencia(){
        GerenciaCrucero gerencia = new GerenciaCrucero("Pepe Granda","RPDise@gmail.com","0982201301",1);
        AtencionCliente atencionCliente= new AtencionCliente(gerencia);
        Usuario usuario = new Usuario("Francis Loayza", "faloayza@espol.edu.ec", "0955461934", new SMS());
        atencionCliente.escalarAGerencia("Problema", usuario);
    }
    
    @Test
    void testEscalarAGerencia_UsuarioNull(){
        GerenciaCrucero gerencia = new GerenciaCrucero("Pepe Granda","RPDise@gmail.com","0982201301",1);
        AtencionCliente atencionCliente= new AtencionCliente(gerencia);
         Exception e= assertThrows(IllegalArgumentException.class,()->{atencionCliente.escalarAGerencia("Problema", null);});
        assertEquals("Los parámetros no pueden ser nulos",e.getMessage());
    }

    @Test
    void testEscalarAGerencia_DescripcionNull(){
        GerenciaCrucero gerencia = new GerenciaCrucero("Pepe Granda","RPDise@gmail.com","0982201301",1);
        Usuario usuario = new Usuario("Francis Loayza", "faloayza@espol.edu.ec", "0955461934", new SMS());
        AtencionCliente atencionCliente= new AtencionCliente(gerencia);
         Exception e= assertThrows(IllegalArgumentException.class,()->{atencionCliente.escalarAGerencia(null, usuario);});
        assertEquals("Los parámetros no pueden ser nulos",e.getMessage());
    }
    
}
