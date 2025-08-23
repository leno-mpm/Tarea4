/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espol;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Mile
 */
public class GerenciaCruceroIT {
    GerenciaCrucero gerencia;
    Usuario usuario;
    @Test
    void testResolverProblema_descripcionNull() {
        gerencia =  new GerenciaCrucero(
            "Gerencia Principal", 
            "gerencia@crucero.com", 
            "0991234567", 
            101
        );
        usuario = new Usuario("Juan", "juan@email.com", "1234567890", new SMS());

        assertThrows(IllegalArgumentException.class, () -> gerencia.resolverProblema(null, usuario));
    }

    @Test
    void testResolverProblema_usuarioNull() {
        gerencia =  new GerenciaCrucero(
            "Gerencia Principal", 
            "gerencia@crucero.com", 
            "0991234567", 
            101
        );

        assertThrows(IllegalArgumentException.class, () -> gerencia.resolverProblema("Problema grave", null));
    }

    @Test
    void testResolverProblema_valido() {
        gerencia =  new GerenciaCrucero(
            "Gerencia Principal", 
            "gerencia@crucero.com", 
            "0991234567", 
            101
        );
       usuario = new Usuario("Juan", "juan@email.com", "1234567890", new SMS());

        assertDoesNotThrow(() -> gerencia.resolverProblema("Problema", usuario));
    }
}
