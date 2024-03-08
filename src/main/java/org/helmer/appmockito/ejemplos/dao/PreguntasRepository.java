package org.helmer.appmockito.ejemplos.dao;

import java.util.List;

public interface PreguntasRepository {

    List<String> findPreguntasPorExamenId(Long id);

}
