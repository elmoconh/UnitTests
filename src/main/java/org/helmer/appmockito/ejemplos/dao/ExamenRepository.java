package org.helmer.appmockito.ejemplos.dao;

import org.helmer.appmockito.ejemplos.models.Examen;

import java.util.List;

public interface ExamenRepository {
    Examen guardar(Examen examen);
    //findAll realiza una consulta a la base de datos y devuelve una lista de examenes
    List<Examen> findAll();
}
