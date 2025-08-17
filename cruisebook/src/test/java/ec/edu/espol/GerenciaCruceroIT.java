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
    @Test
    void testResolverProblema_descripcionNull() {
        GerenciaCrucero gerencia = new GerenciaCrucero();
        Usuario usuario = new Usuario("Juan", "juan@email.com", "1234567890", new SMS());

        assertThrows(IllegalArgumentException.class, () -> gerencia.resolverProblema(null, usuario));
    }

    @Test
    void testResolverProblema_usuarioNull() {
        GerenciaCrucero gerencia = new GerenciaCrucero();

        assertThrows(IllegalArgumentException.class, () -> gerencia.resolverProblema("Problema grave", null));
    }

    @Test
    void testResolverProblema_valido() {
        GerenciaCrucero gerencia = new GerenciaCrucero();
        Usuario usuario = new Usuario("Juan", "juan@email.com", "1234567890", new SMS());

        assertDoesNotThrow(() -> gerencia.resolverProblema("Problema", usuario));
    }
}
