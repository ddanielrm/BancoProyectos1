package com.yagami.bancoproyectos;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yagami.bancoproyectos.api.ProyectoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;
    RecyclerView recyclerView;
    ListaProyectosAdapter listaProyectosAdapter;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.card_recycler_view);
        listaProyectosAdapter=new ListaProyectosAdapter(this);
        recyclerView.setAdapter(listaProyectosAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,true);
        recyclerView.setLayoutManager(linearLayoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://lexa2334.pythonanywhere.com/api")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        imageView = findViewById(R.id.imagenGlide);
        setImageView();
        obtenerDatos();
    }

    private void obtenerDatos() {
        ProyectoService service=retrofit.create(ProyectoService.class);
        Call<ProyectosRespuesta> proyectosRespuestaCall=service.obtenerListaProyecto();
        proyectosRespuestaCall.enqueue(new Callback<ProyectosRespuesta>() {
            @Override
            public void onResponse(Call<ProyectosRespuesta> call, Response<ProyectosRespuesta> response) {
                if (response.isSuccessful()) {
                    ProyectosRespuesta proyectosRespuesta = response.body();
                    List<Proyectos> listaproyectos = proyectosRespuesta.getResults();
                    for (int i = 0; i < listaproyectos.size(); i++) {
                        Proyectos p = listaproyectos.get(i);
                        Log.e(TAG, "pokemon: " + p.getNombre_proyecto());
                    }

                    listaProyectosAdapter.add((ArrayList<Proyectos>) listaproyectos);
                }
                else{
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ProyectosRespuesta> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());

            }
        });

    }

    private void setImageView() {
        String url = "https://assets.stickpng.com/images/58f3773fa4fa116215a92413.png";
        Glide.with(this)
                .load(url)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .into(imageView);

    }
}