package org.helmer.appmockito.ejemplos.dao;

import java.util.List;

public interface PreguntaRepository {
    List<String> findPreguntasPorExamenId(Long id);
}