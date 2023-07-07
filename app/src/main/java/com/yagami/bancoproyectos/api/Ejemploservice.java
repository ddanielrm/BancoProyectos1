package com.yagami.bancoproyectos.api;

import com.yagami.bancoproyectos.ProyectosRespuesta;
import com.yagami.bancoproyectos.Proyectosbanco;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Ejemploservice {

    @GET("products")
    List<Proyectosbanco> proyectosbanco();
}
