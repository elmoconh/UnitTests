package org.helmer.appmockito.ejemplos.services;

import org.helmer.appmockito.ejemplos.dao.ExamenRepository;
import org.helmer.appmockito.ejemplos.dao.ExamenRepositoryImpl;
import org.helmer.appmockito.ejemplos.dao.PreguntaRepository;
import org.helmer.appmockito.ejemplos.models.Examen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ExamenServicesImplTest {
    ExamenRepository examenRepository;
    ExamenServicesImpl examenServices;
    PreguntaRepository preguntaRepository;

    @BeforeEach
    void setUp() {
        examenRepository = mock(ExamenRepositoryImpl.class);
        preguntaRepository = mock(PreguntaRepository.class);
        examenServices = new ExamenServicesImpl(examenRepository, preguntaRepository);

    }

    @Test
    void testConMockito() {
        Mockito.when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
        Optional<Examen> examen = examenServices.buscarExamenPorNombre("Matematicas");
        assertEquals("Matematicas", examen.orElseThrow().getNombre());


    }

    @Test
    void testConMockito2() {
        List<Examen> datos = Arrays.asList(
                new Examen(5L, "Matematicas"),
                new Examen(6L, "Lenguaje")
        );
        Mockito.when(examenRepository.findAll()).thenReturn(datos);

        Optional<Examen> examen = examenServices.buscarExamenPorNombre("Matematicas");
        assertNotNull(examen);
        assertEquals("Matematicas", examen.orElseThrow().getNombre());

    }

    @Test
    void testConMockitoVacio() {

        List<Examen> datos = List.of();
        Mockito.when(examenRepository.findAll()).thenReturn(datos);

        Optional<Examen> examen = examenServices.buscarExamenPorNombre("Matematicas");
        try {
            assertEquals(Optional.empty(), examen);
            System.out.println("el examen es null con mockito");
        } catch (NullPointerException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }

    @Test
    void testConListaVacia() {
        List<Examen> datos = Collections.emptyList();
        Mockito.when(examenRepository.findAll()).thenReturn(datos);

        Optional<Examen> examen = examenServices.buscarExamenPorNombre("Matematicas");
        try {
            assertEquals(Optional.empty(), examen);
            System.out.println("el examen es null con mockito");
        } catch (NullPointerException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }

    @Test
    void testPreguntasExamen() {
        Mockito.when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
        Mockito.when(preguntaRepository.findPreguntasPorExamenId(5L)).thenReturn(Datos.PREGUNTAS);
        Examen examen = examenServices.findExamenPorNombreConPreguntas("Matematicas");
        System.out.println("Pasa correctamente");
        assertEquals(5, examen.getPreguntas().size());
    }

}
