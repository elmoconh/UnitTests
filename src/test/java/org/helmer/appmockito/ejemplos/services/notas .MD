# Test con mockito

## Método de prueba 'testConMockito' explicado paso a paso:

1. Se crea un mock de la interfaz 'ExamenRepository' usando Mockito. Un mock es un objeto falso que se puede usar en
   lugar del objeto real durante las pruebas. Esto se hace para aislar el código que se está probando.
   ExamenRepository examenRepository = Mockito.mock(ExamenRepository.class);

2. Se crea una instancia de 'ExamenServicesImpl', pasando el mock de 'ExamenRepository' al constructor. Esto significa
   que cuando 'ExamenServicesImpl' llame a los métodos de 'ExamenRepository', en realidad estará llamando a los métodos
   del mock.
   ExamenServicios examenServices = new ExamenServicesImpl(examenRepository);

3. Se crea una lista de objetos 'Examen' que se utilizará para simular los datos devueltos por el método 'findAll()'
   de 'ExamenRepository'.
   List<Examen> datos = Arrays.asList(new Examen(5L, "Matematicas"), new Examen(6L, "Lenguaje"));

4. Se configura el mock para que cuando se llame al método 'findAll()' de 'ExamenRepository', devuelva la lista de datos
   creada en el paso anterior. Esto se hace usando el método 'when()' de Mockito, que toma una llamada al método y luego
   se encadena con 'thenReturn()' para especificar qué devolver cuando se llame al método.
   Mockito.when(examenRepository.findAll()).thenReturn(datos);

5. Se llama al método 'buscarExamenPorNombre()' de 'ExamenServicesImpl', pasando el nombre del examen que se desea
   buscar. Como 'ExamenServicesImpl' está utilizando el mock de 'ExamenRepository', cuando llama a 'findAll()', obtendrá
   la lista de datos que se configuró para devolver en el paso 4.
   Examen examen = examenServices.buscarExamenPorNombre("Matematicas");

6. Finalmente, se verifica que el nombre del examen devuelto por 'buscarExamenPorNombre()' sea el esperado. Esto se hace
   usando el método 'assertEquals()' de JUnit, que toma dos argumentos y verifica que sean iguales. Si no son iguales,
   la prueba fallará.
   assertEquals("Matematicas", examen.getNombre());

### En resumen, este método de prueba está utilizando un mock de 'ExamenRepository' para simular su comportamiento y aislar la funcionalidad de 'ExamenServicesImpl' que se está probando._~~

```java

@Test
void testConMockito() {
    ExamenRepository examenRepository = Mockito.mock(ExamenRepository.class);
    ExamenServicios examenServices = new ExamenServicesImpl(examenRepository);

    List<Examen> datos = Arrays.asList(
            new Examen(5L, "Matematicas"),
            new Examen(6L, "Lenguaje")
    );
    Mockito.when(examenRepository.findAll()).thenReturn(datos);
    Optional<Examen> examen = examenServices.buscarExamenPorNombre("Matematicas");
    assertEquals("Matematicas", examen.orElseThrow().getNombre());


}

```
