package com.example.TP5

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animaux.AdapterAnimaux
import com.example.animaux.Animal
import com.example.tp5.R

class MainActivity : AppCompatActivity() {

    private lateinit var rvAnimaux: RecyclerView
    private lateinit var rgAffichage: RadioGroup
    private lateinit var adapter: AdapterAnimaux
    private lateinit var listeAnimaux: MutableList<Animal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialiser les vues
        rvAnimaux = findViewById(R.id.rvAnimaux)
        rgAffichage = findViewById(R.id.rgAffichage)

        // Créer la liste des animaux
        listeAnimaux = creerListeAnimaux()

        // Configurer l'adapter
        adapter = AdapterAnimaux(listeAnimaux)
        rvAnimaux.adapter = adapter

        // Définir le layout manager initial (linéaire)
        rvAnimaux.layoutManager = LinearLayoutManager(this)

        // Gérer le changement de type d'affichage
        rgAffichage.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbLineaire -> {
                    rvAnimaux.layoutManager = LinearLayoutManager(this)
                }
                R.id.rbGrille -> {
                    rvAnimaux.layoutManager = GridLayoutManager(this, 2)
                }
            }
        }
    }

    // Méthode pour créer une liste d'animaux
    private fun creerListeAnimaux(): MutableList<Animal> {
        return mutableListOf(
            Animal("Chat", "Mammifère", R.drawable.chat),
            Animal("Chien", "Mammifère", R.drawable.chien),
            Animal("Lion", "Mammifère", R.drawable.lion),
            Animal("Aigle", "Oiseau", R.drawable.aigle),
            Animal("Perroquet", "Oiseau", R.drawable.perroquet),
            Animal("Crocodile", "Reptile", R.drawable.crocodile),
            Animal("Serpent", "Reptile", R.drawable.serpent),
            Animal("Éléphant", "Mammifère", R.drawable.elephant),
            Animal("Tigre", "Mammifère", R.drawable.tigre),
            Animal("Dauphin", "Mammifère", R.drawable.dauphin)
        )
    }
}