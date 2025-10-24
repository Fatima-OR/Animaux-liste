package com.example.animaux

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tp5.R


class AdapterAnimaux(private val listeAnimaux: MutableList<Animal>) :
    RecyclerView.Adapter<AdapterAnimaux.AnimalViewHolder>() {

    // ViewHolder pour contenir les vues de chaque élément
    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAnimalImage: ImageView = itemView.findViewById(R.id.ivAnimalImage)
        val tvNom: TextView = itemView.findViewById(R.id.tvNom)
        val tvEspece: TextView = itemView.findViewById(R.id.tvEspece)
        val btnDetails: ImageButton = itemView.findViewById(R.id.btnDetails)
        val btnSupprimer: ImageButton = itemView.findViewById(R.id.btnSupprimer)
    }

    // Créer un nouveau ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.animal_item, parent, false)
        return AnimalViewHolder(view)
    }

    // Lier les données à chaque ViewHolder
    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = listeAnimaux[position]

        // Afficher les informations de l'animal
        holder.ivAnimalImage.setImageResource(animal.imageResId)
        holder.tvNom.text = animal.nom
        holder.tvEspece.text = animal.espece

        // Bouton Détails - afficher les informations
        holder.btnDetails.setOnClickListener {
            val message = "Nom: ${animal.nom}\nEspèce: ${animal.espece}"
            Toast.makeText(holder.itemView.context, message, Toast.LENGTH_LONG).show()
        }

        // Bouton Supprimer - retirer l'animal de la liste
        holder.btnSupprimer.setOnClickListener {
            val currentPosition = holder.adapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                listeAnimaux.removeAt(currentPosition)
                notifyItemRemoved(currentPosition)
                notifyItemRangeChanged(currentPosition, listeAnimaux.size)
                Toast.makeText(
                    holder.itemView.context, "${animal.nom} supprimé", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // Retourner le nombre d'éléments dans la liste
    override fun getItemCount(): Int = listeAnimaux.size
}