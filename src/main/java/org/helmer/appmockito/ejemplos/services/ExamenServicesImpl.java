package org.helmer.appmockito.ejemplos.services;

import org.helmer.appmockito.ejemplos.dao.ExamenRepository;
import org.helmer.appmockito.ejemplos.dao.PreguntaRepository;
import org.helmer.appmockito.ejemplos.models.Examen;

import java.util.List;
import java.util.Optional;


public class ExamenServicesImpl implements ExamenServicios {
    private ExamenRepository examenRepository;
    private PreguntaRepository preguntaRepository;

    public ExamenServicesImpl(ExamenRepository examenRepository, PreguntaRepository preguntaRepository) {
        this.examenRepository = examenRepository;
        this.preguntaRepository = preguntaRepository;
    }

    @Override
    public Optional<Examen> buscarExamenPorNombre(String nombre) {
        return examenRepository.findAll()
                .stream()
                .filter(e -> e.getNombre().contains(nombre)).
                findFirst();
    }
    @Override
    public Examen findExamenPorNombreConPreguntas(String nombre){
        Optional<Examen> examenOptional = buscarExamenPorNombre(nombre);
        Examen examen = null;
        if(examenOptional.isPresent()){
            List<String> preguntas = preguntaRepository.findPreguntasPorExamenId(examenOptional.get().getId());
            examen = examenOptional.orElseThrow();
            examen.setPreguntas(preguntas);
        }
        return examen;
    }
}
