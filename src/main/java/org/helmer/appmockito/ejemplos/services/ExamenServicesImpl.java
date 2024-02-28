package org.helmer.appmockito.ejemplos.services;

import org.helmer.appmockito.ejemplos.dao.ExamenRepository;
import org.helmer.appmockito.ejemplos.models.Examen;

import java.util.Optional;

public class ExamenServicesImpl implements ExamenServicios{
    private ExamenRepository examenRepository;

    public ExamenServicesImpl(ExamenRepository examenRepository) {
        this.examenRepository = examenRepository;
    }

    @Override
    public Examen buscarExamenPorNombre(String nombre) {
//Optional es una clase que nos permite trabajar con objetos que pueden ser nulos
        Optional<Examen> examenOptional=
                examenRepository.findAll().stream()
                .filter(e -> e.getNombre().contains(nombre))
                .findFirst();
        Examen examen = null;
        if(examenOptional.isPresent()){
            examen = examenOptional.orElseThrow();
        }
        return examen;
    }
}
