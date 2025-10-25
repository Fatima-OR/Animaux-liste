package com.example.selection

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), AnimalAdapter.OnSelectionChangeListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var radioGroup: RadioGroup
    private lateinit var textSelection: TextView
    private lateinit var buttonDeleteSelected: Button
    private lateinit var adapter: AnimalAdapter

    private val animals = mutableListOf(
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        radioGroup = findViewById(R.id.radioGroup)
        textSelection = findViewById(R.id.textSelection)
        buttonDeleteSelected = findViewById(R.id.buttonDeleteSelected)

        adapter = AnimalAdapter(animals, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Changement layout Linear / Grid
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioLinear -> {
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    adapter.layoutMode = AnimalAdapter.LayoutMode.LINEAR
                    adapter.notifyDataSetChanged()
                }
                R.id.radioGrid -> {
                    recyclerView.layoutManager = GridLayoutManager(this, 2)
                    adapter.layoutMode = AnimalAdapter.LayoutMode.GRID
                    adapter.notifyDataSetChanged()
                }
            }
        }

        // Supprimer les éléments sélectionnés
        buttonDeleteSelected.setOnClickListener {
            if (adapter.selectedItems.isNotEmpty()) {
                AlertDialog.Builder(this)
                    .setTitle("Confirmer la suppression")
                    .setMessage("Voulez-vous supprimer les éléments sélectionnés ?")
                    .setPositiveButton("Oui") { _, _ -> adapter.deleteSelected() }
                    .setNegativeButton("Annuler", null)
                    .show()
            }
        }
    }

    override fun onSelectionChanged(selectedPositions: List<Int>) {
        if (selectedPositions.isEmpty()) {
            textSelection.visibility = View.GONE
            buttonDeleteSelected.visibility = View.GONE
        } else {
            textSelection.visibility = View.VISIBLE
            buttonDeleteSelected.visibility = View.VISIBLE
            textSelection.text = "Vous avez sélectionné l'élément(s) de position : ${selectedPositions.joinToString(", ")}"
        }
    }
}
