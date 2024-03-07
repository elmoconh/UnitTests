package org.helmer.appmockito.ejemplos.services;

import org.helmer.appmockito.ejemplos.dao.ExamenRepository;
import org.helmer.appmockito.ejemplos.dao.ExamenRepositoryImpl;
import org.helmer.appmockito.ejemplos.dao.ExamenRepositoryOtro;
import org.helmer.appmockito.ejemplos.models.Examen;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
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

    //test con mockito

    /*
     * Método de prueba 'testConMockito' explicado paso a paso:
     *
     * 1. Se crea un mock de la interfaz 'ExamenRepository' usando Mockito. Un mock es un objeto falso que se puede usar en lugar del objeto real durante las pruebas. Esto se hace para aislar el código que se está probando.
     *    ExamenRepository examenRepository = Mockito.mock(ExamenRepository.class);
     *
     * 2. Se crea una instancia de 'ExamenServicesImpl', pasando el mock de 'ExamenRepository' al constructor. Esto significa que cuando 'ExamenServicesImpl' llame a los métodos de 'ExamenRepository', en realidad estará llamando a los métodos del mock.
     *    ExamenServicios examenServices = new ExamenServicesImpl(examenRepository);
     *
     * 3. Se crea una lista de objetos 'Examen' que se utilizará para simular los datos devueltos por el método 'findAll()' de 'ExamenRepository'.
     *    List<Examen> datos = Arrays.asList(new Examen(5L, "Matematicas"), new Examen(6L, "Lenguaje"));
     *
     * 4. Se configura el mock para que cuando se llame al método 'findAll()' de 'ExamenRepository', devuelva la lista de datos creada en el paso anterior. Esto se hace usando el método 'when()' de Mockito, que toma una llamada al método y luego se encadena con 'thenReturn()' para especificar qué devolver cuando se llame al método.
     *    Mockito.when(examenRepository.findAll()).thenReturn(datos);
     *
     * 5. Se llama al método 'buscarExamenPorNombre()' de 'ExamenServicesImpl', pasando el nombre del examen que se desea buscar. Como 'ExamenServicesImpl' está utilizando el mock de 'ExamenRepository', cuando llama a 'findAll()', obtendrá la lista de datos que se configuró para devolver en el paso 4.
     *    Examen examen = examenServices.buscarExamenPorNombre("Matematicas");
     *
     * 6. Finalmente, se verifica que el nombre del examen devuelto por 'buscarExamenPorNombre()' sea el esperado. Esto se hace usando el método 'assertEquals()' de JUnit, que toma dos argumentos y verifica que sean iguales. Si no son iguales, la prueba fallará.
     *    assertEquals("Matematicas", examen.getNombre());
     *
     * En resumen, este método de prueba está utilizando un mock de 'ExamenRepository' para simular su comportamiento y aislar la funcionalidad de 'ExamenServicesImpl' que se está probando.
     */
    @Test
    void testConMockito() {
        //Creamos una instancia de la interfaz ExamenRepository con mockito
        ExamenRepository examenRepository = Mockito.mock(ExamenRepository.class);
        ExamenServicios examenServices = new ExamenServicesImpl(examenRepository);

        List<Examen> datos = Arrays.asList(
                        new Examen(5L, "Matematicas"),
                        new Examen(6L, "Lenguaje")
                );
        //Cuando se llame al metodo findAll() de la interfaz ExamenRepository, se devolvera la lista datos
        Mockito.when(examenRepository.findAll()).thenReturn(datos);
        //cuando se llame devuelva vacio
        //Mockito.when(examenRepository.findAll()).thenReturn(Arrays.asList());

        Examen examen = examenServices.buscarExamenPorNombre("Matematicas");
        assertEquals("Matematicas", examen.getNombre());


    }

    @Test
    void testConMockito2() {
        //Creamos una instancia de la interfaz ExamenRepository con mockito
        ExamenRepository examenRepository = Mockito.mock(ExamenRepositoryOtro.class);
        ExamenServicios services = new ExamenServicesImpl(examenRepository);

        List<Examen> datos = Arrays.asList(
                new Examen(5L, "Matematicas"),
                new Examen(6L, "Lenguaje")
        );
        //Cuando se llame al metodo findAll() de la interfaz ExamenRepository, se devolvera la lista datos
        Mockito.when(examenRepository.findAll()).thenReturn(datos);

        Examen examen = services.buscarExamenPorNombre("Matematicas");
        assertNotNull(examen);
        assertEquals("Matematicas", examen.getNombre());

    }
    @Test
    void testConMockitoVacio() {
        //Creamos una instancia de la interfaz ExamenRepository con mockito
        ExamenRepository examenRepository = Mockito.mock(ExamenRepository.class);
        ExamenServicios services = new ExamenServicesImpl(examenRepository);

        List<Examen> datos = List.of();
        //Cuando se llame al metodo findAll() de la interfaz ExamenRepository, se devolvera la lista datos
        Mockito.when(examenRepository.findAll()).thenReturn(datos);

        Examen examen = services.buscarExamenPorNombre("Matematicas");
        try {
            assertNull(examen);
            System.out.println("el examen es null con mockito");
        } catch (NullPointerException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }


    }
}
