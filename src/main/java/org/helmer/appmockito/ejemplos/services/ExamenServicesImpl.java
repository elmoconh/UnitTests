package org.helmer.appmockito.ejemplos.services;

import org.helmer.appmockito.ejemplos.dao.ExamenRepository;
import org.helmer.appmockito.ejemplos.models.Examen;

import java.util.Optional;


public class ExamenServicesImpl implements ExamenServicios {
    private final ExamenRepository examenRepository;

    public ExamenServicesImpl(ExamenRepository examenRepository) {
        this.examenRepository = examenRepository;
    }

    /*
        @Override
        public Examen buscarExamenPorNombre(String nombre) {
            System.out.println("nombre = " + nombre);
    //Optional es una clase que nos permite trabajar con objetos que pueden ser nulos
            Optional<Examen> examenOptional=
                    examenRepository.findAll()
                            .stream()
                            .filter(e -> e.getNombre().contains(nombre))
                            .findFirst();

            Examen examen = null;
            if(examenOptional.isPresent()){
               /* El método get() y orElseThrow() son métodos de la clase Optional en Java. Ambos se utilizan para obtener el valor presente en el objeto Optional, pero se comportan de manera diferente cuando el objeto Optional está vacío (es decir, no contiene ningún valor).
                        *get(): Este método devolverá el valor presente en el objeto Optional si existe. Sin embargo, si el objeto Optional está vacío, lanzará una excepción NoSuchElementException.
                        *orElseThrow(): Este método también devolverá el valor presente en el objeto Optional si existe. Pero a diferencia de get(), permite personalizar la excepción que se lanza cuando el objeto Optional está vacío. Si no se proporciona una excepción personalizada, también lanzará una NoSuchElementException.

                examen = examenOptional.orElseThrow();
            }
            return examen;
        }
        */
    @Override
    public Optional<Examen> buscarExamenPorNombre(String nombre) {
        return examenRepository.findAll()
                .stream()
                .filter(e -> e.getNombre().contains(nombre)).
                findFirst();
    }
}
