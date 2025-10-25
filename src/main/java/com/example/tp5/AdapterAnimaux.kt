package com.example.selection

import android.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class AnimalAdapter(
    private val animals: MutableList<Animal>,
    private val listener: OnSelectionChangeListener
) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    enum class LayoutMode { LINEAR, GRID }
    var layoutMode = LayoutMode.LINEAR

    val selectedItems = mutableSetOf<Int>()

    interface OnSelectionChangeListener {
        fun onSelectionChanged(selectedPositions: List<Int>)
    }

    inner class AnimalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemContainer: LinearLayout = view.findViewById(R.id.itemContainer) // LinearLayout root
        val imageAnimal: ImageView = view.findViewById(R.id.imageAnimal)
        val textContainer: LinearLayout = view.findViewById(R.id.textContainer)
        val textName: TextView = view.findViewById(R.id.textName)
        val textType: TextView = view.findViewById(R.id.textType)
        val buttonInfo: ImageButton = view.findViewById(R.id.buttonInfo)
        val buttonDelete: ImageButton = view.findViewById(R.id.buttonDelete)
        val checkBox: CheckBox = view.findViewById(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.animal_item, parent, false)
        return AnimalViewHolder(view)
    }

    override fun getItemCount(): Int = animals.size

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animals[position]

        holder.imageAnimal.setImageResource(animal.imageResId)
        holder.textName.text = animal.name
        holder.textType.text = animal.type

        // Ajustement orientation selon mode
        if (layoutMode == LayoutMode.GRID) {
            holder.itemContainer.orientation = LinearLayout.VERTICAL
            holder.textContainer.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
            holder.textContainer.gravity = Gravity.CENTER_HORIZONTAL
        } else {
            holder.itemContainer.orientation = LinearLayout.HORIZONTAL
            holder.textContainer.layoutParams.width = 0
            (holder.textContainer.layoutParams as LinearLayout.LayoutParams).weight = 1f
            holder.textContainer.gravity = Gravity.START
        }

        // Checkbox
        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = selectedItems.contains(position)
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) selectedItems.add(position) else selectedItems.remove(position)
            listener.onSelectionChanged(selectedItems.toList())
        }

        // Info
        holder.buttonInfo.setOnClickListener {
            Toast.makeText(holder.itemView.context, "${animal.name} est un(e) ${animal.type}", Toast.LENGTH_SHORT).show()
        }

        // Supprimer individuel
        holder.buttonDelete.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Confirmer la suppression")
                .setMessage("Voulez-vous supprimer ${animal.name} ?")
                .setPositiveButton("Oui") { _, _ ->
                    animals.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, animals.size)
                    selectedItems.remove(position)
                    listener.onSelectionChanged(selectedItems.toList())
                }
                .setNegativeButton("Annuler", null)
                .show()
        }
    }

    fun deleteSelected() {
        val sorted = selectedItems.sortedDescending()
        for (pos in sorted) {
            if (pos in animals.indices) animals.removeAt(pos)
        }
        selectedItems.clear()
        notifyDataSetChanged()
        listener.onSelectionChanged(emptyList())
    }
}
