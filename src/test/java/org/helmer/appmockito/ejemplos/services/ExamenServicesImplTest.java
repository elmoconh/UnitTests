package org.helmer.appmockito.ejemplos.services;

import org.helmer.appmockito.ejemplos.dao.ExamenRepository;
import org.helmer.appmockito.ejemplos.dao.ExamenRepositoryImpl;
import org.helmer.appmockito.ejemplos.dao.PreguntaRepository;
import org.helmer.appmockito.ejemplos.models.Examen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        //when se utiliza para simular el comportamiento de un metodo de una clase que no se ha implementado aun
        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
        Optional<Examen> examen = examenServices.buscarExamenPorNombre("Matematicas");
        assertEquals("Matematicas", examen.orElseThrow().getNombre());
    }

    @Test
    void testConMockito2() {
        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
        Optional<Examen> examen = examenServices.buscarExamenPorNombre("Matematicas");
        assertNotNull(examen);
        assertEquals("Matematicas", examen.orElseThrow().getNombre());

    }

    @Test
    void testConMockitoVacio() {
        when(examenRepository.findAll()).thenReturn(Datos.VACIO);
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
        when(examenRepository.findAll()).thenReturn(Datos.VACIO);
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
        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
        when(preguntaRepository.findPreguntasPorExamenId(5L)).thenReturn(Datos.PREGUNTAS);
        Examen examen = examenServices.findExamenPorNombreConPreguntas("Matematicas");
        assertEquals(5, examen.getPreguntas().size());
    }

    @Test
    void testPreguntasExamenVerify() {
        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
        when(preguntaRepository.findPreguntasPorExamenId(5L)).thenReturn(Datos.PREGUNTAS);
        Examen examen = examenServices.findExamenPorNombreConPreguntas("Matematicas");
        assertEquals(5, examen.getPreguntas().size());
        //Verify nos permite verificar que un metodo se haya llamado o no
        verify(examenRepository).findAll();
        verify(preguntaRepository).findPreguntasPorExamenId(5L);
    }

    @Test
    void testNoExisteExamenVerify() {
        when(examenRepository.findAll()).thenReturn(Datos.VACIO);
        when(preguntaRepository.findPreguntasPorExamenId(5L)).thenReturn(Datos.PREGUNTAS);
        Examen examen = examenServices.findExamenPorNombreConPreguntas("Matematicas");
        assertNull(examen);

        //Verify nos permite verificar que un metodo se haya llamado o no
        verify(examenRepository).findAll();

        //Estamos simulando una falla ya que el metodo findAll devuelve una lista vacia, por lo que no se deberia llamar al metodo findPreguntasPorExamenId
        verify(preguntaRepository).findPreguntasPorExamenId(5L);
    }

}
