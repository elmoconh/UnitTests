package org.helmer.appmockito.ejemplos.dao;

import org.helmer.appmockito.ejemplos.models.Examen;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExamenRepositoryImpl implements ExamenRepository {
    @Override
    public Examen guardar(Examen examen) {
        return null;
    }

    @Override
    public List<Examen> findAll() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;

    }
}
