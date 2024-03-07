package org.helmer.appmockito.ejemplos.services;

import org.helmer.appmockito.ejemplos.models.Examen;

import java.util.Optional;

public interface ExamenServicios {
    Optional<Examen> buscarExamenPorNombre(String nombre);


}
