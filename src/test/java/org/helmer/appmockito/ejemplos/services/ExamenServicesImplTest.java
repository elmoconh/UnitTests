package org.helmer.appmockito.ejemplos.services;

import org.helmer.appmockito.ejemplos.dao.ExamenRepository;
import org.helmer.appmockito.ejemplos.dao.ExamenRepositoryImpl;
import org.helmer.appmockito.ejemplos.models.Examen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExamenServicesImplTest {
//este es un test reaizado con JUnit 5
    @Test
    void buscarExamenPorNombreTest() {
        //Primero creamos una instancia de la interfaz ExamenRepository
        ExamenRepository examenRepository = new ExamenRepositoryImpl();
        //Creamos una instancia de la clase ExamenServicesImpl
        ExamenServicesImpl examenServices = new ExamenServicesImpl(examenRepository);
        //Creamos una instancia de la clase Examen
        Examen examen = examenServices.buscarExamenPorNombre("Matematicas");
        //Comprobamos que el nombre del examen sea igual a "Matematicas"
        assertEquals("Matematicas", examen.getNombre());
    }
}