package com.example.recyclerviewstest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/* La classe RecyclerAdapter permet d'afficher des vues composées d'un titre, d'une description et d'une image */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.HerissonViewHolder> {

    private ArrayList<String> herissonTitle;
    private ArrayList<String> herissonDetails;
    private ArrayList<Integer> imageList;
    private Context context;

    /* Constructeur pour instancier l'adaptateur. Il prend en paramètres les 3 ArrayLists qui contiennent les données à afficher
     * ainsi qu'un objet Context pour obtenir le contexte de l'activité parente */
    public RecyclerAdapter(ArrayList<String> herissonTitle, ArrayList<String> herissonDetails, ArrayList<Integer> imageList, Context context) {
        this.herissonTitle = herissonTitle;
        this.herissonDetails = herissonDetails;
        this.imageList = imageList;
        this.context = context;
    }

    /* Lorsque l'adapteur a besoin de créer un nouvel objet ViewHolder dans la liste, cette méthode sera appelée.
     * On y spécifie le Layout utilisé pour chaque élément de la RecyclerView.
     * Ici, on gonfle (inflate) le layout "card_design" en utilisant le LayoutInflater et on le retourne entant que ViewHolder*/
    @NonNull
    @Override
    public HerissonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design, parent, false);

        return new HerissonViewHolder(view);
    }

    /* Lorsque la RecyclerView doit afficher des données dans un ViewHolder spécifique à une certaine position, cette méthode est appelée.
     * Cette méthode permet de lier les données (TextView, ImageView) aux différentes vues du ViewHolder. */
    @Override
    public void onBindViewHolder(@NonNull HerissonViewHolder holder, int position) {

        holder.textViewHerissonTitle.setText(herissonTitle.get(position));
        holder.textViewHerissonDetails.setText(herissonDetails.get(position));
        holder.imageView.setImageResource(imageList.get(position));
        holder.cardView.setOnClickListener(view -> {
            if (position == 0) {
                Toast.makeText(context, "Le premier hérisson", Toast.LENGTH_SHORT).show();
            }
            if (position == 1) {
                Toast.makeText(context, "Le deuxième hérisson", Toast.LENGTH_SHORT).show();
            }
            if (position == 2) {
                Toast.makeText(context, "Le troisième hérisson", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* Méthode à surcharger afin qu'elle renvoie le nombre total d'éléments à afficher dans la RecyclerView */
    @Override
    public int getItemCount() {
        return herissonTitle.size();
    }

    public class HerissonViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewHerissonTitle, textViewHerissonDetails;
        private ImageView imageView;
        private CardView cardView;

        // https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.ViewHolder
        public HerissonViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewHerissonTitle = itemView.findViewById(R.id.textViewHerisson);
            textViewHerissonDetails = itemView.findViewById(R.id.textViewHerissonDetails);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
