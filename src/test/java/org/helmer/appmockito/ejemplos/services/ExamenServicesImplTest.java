package org.helmer.appmockito.ejemplos.services;

import org.helmer.appmockito.ejemplos.dao.ExamenRepository;
import org.helmer.appmockito.ejemplos.dao.PreguntaRepository;
import org.helmer.appmockito.ejemplos.models.Examen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//ExtendWith nos permite extender la funcionalidad de JUnit5 con Mockito para poder utilizar las anotaciones de Mockito
//ExtendWith recibe como parametro la clase MockitoExtension.class
@ExtendWith(MockitoExtension.class)
class ExamenServicesImplTest {
    @Mock
    ExamenRepository examenRepository;

    @Mock
    PreguntaRepository preguntaRepository;

    //La inyeccion de dependencias se realiza mediante la anotacion @InjectMocks,
    //con esto se inyectan los mocks en la clase que se va a probar

    @InjectMocks
    ExamenServicesImpl examenServices;

    @BeforeEach
    void setUp() {
        //FORMA 1: Inicializa los mocks mediante la anotacion @Mock
        //MockitoAnnotations.openMocks(this);

        /* NO SE USA:  examenRepository = mock(ExamenRepositoryImpl.class);
        preguntaRepository = mock(PreguntaRepository.class);
        examenServices = new ExamenServicesImpl(examenRepository, preguntaRepository);*/


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

    @Test
    void testGuardarExamen() {
        //GIVEN - Configuracion de los mocks
        Examen newExamen = Datos.EXAMEN;
        newExamen.setPreguntas(Datos.PREGUNTAS);

        when(examenRepository.guardar(newExamen)).then(new Answer<Examen>() {
            Long secuencia = 8L;

            @Override
            public Examen answer(InvocationOnMock invocation) {
                Examen examen = invocation.getArgument(0);
                examen.setId(secuencia++);
                return examen;
            }
        });

        //WHEN - Ejecucion del metodo a probar
        Examen examen = examenServices.guardar(newExamen);

        //THEN - Verificacion del resultado
        assertNotNull(examen.getId());
        assertEquals(8L, examen.getId());
        assertEquals("Fisica", examen.getNombre());
        verify(examenRepository).guardar(any(Examen.class));
        verify(preguntaRepository).guardarVarias(anyList());

    }

}
