package com.yagami.bancoproyectos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListaProyectosAdapter extends RecyclerView.Adapter<ListaProyectosAdapter.ViewHolder> {

    private RecyclerView recyclerView;
    private ListaProyectosAdapter listaProyectosAdapter;
    private ArrayList<Proyectos> dataset;
    private Context context;



    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView fotoImageView;
        private TextView name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            fotoImageView = itemView.findViewById(R.id.fotoImageView);
        }
    }

    public ListaProyectosAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

        @Override
        public ViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
            return new ViewHolder(view);
        }
        public int getItemCount () {
            return dataset.size();
        }

        @Override
        public void onBindViewHolder (ViewHolder holder,int position){
            Proyectos proyect = dataset.get(position);
            holder.name.setText(proyect.getNombre_proyecto());

            String url = "https://lexa2334.pythonanywhere.com/api/proyecto/"  +proyect.getNombre_proyecto() ;

            Glide.with(context)
                    .load(url)
                    .error(R.drawable.error)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.fotoImageView);

        }

    public void add(ArrayList<Proyectos> listaproyectos) {
        dataset.addAll(listaproyectos);
        notifyDataSetChanged();
    }
}





