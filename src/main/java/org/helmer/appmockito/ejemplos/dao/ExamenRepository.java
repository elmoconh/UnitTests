package org.helmer.appmockito.ejemplos.dao;

import org.helmer.appmockito.ejemplos.models.Examen;

import java.util.List;

public interface ExamenRepository {

    List<Examen> findAll();
}
