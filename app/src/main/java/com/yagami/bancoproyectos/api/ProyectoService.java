package com.yagami.bancoproyectos.api;

import retrofit2.Call;
import retrofit2.http.GET;

import com.yagami.bancoproyectos.ProyectosRespuesta;


public interface ProyectoService {
    @GET("Proyectos")


    Call<ProyectosRespuesta> obtenerListaProyecto();
}
