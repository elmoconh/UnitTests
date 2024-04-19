package org.helmer.appmockito.ejemplos.services;

import org.helmer.appmockito.ejemplos.models.Examen;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Datos {
    public final static List<Examen> EXAMENES = Arrays.asList(
            new Examen(5L, "Matematicas"),
            new Examen(6L, "Lenguaje")
    );
    public final static List<String> PREGUNTAS = Arrays.asList(
            "aritmetica",
            "integrales",
            "derivadas",
            "trigonometria",
            "geometria"
    );

    public final static List<Examen> VACIO = Collections.emptyList();

}
