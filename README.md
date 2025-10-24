Liste des Animaux – Application Android Kotlin

📱 Aperçu de l’application

L’application "Liste des Animaux" affiche une collection d’animaux sous deux formats différents :

Affichage linéaire (liste verticale)

Affichage en grille (Grid)

L’utilisateur peut basculer entre ces deux modes grâce à un sélecteur (RadioButton) en haut de l’écran.
Chaque carte d’animal contient :

Une image

Le nom de l’animal

Son espèce (par ex. Mammifère, Oiseau)

Un bouton de suppression

Un bouton d’ajout ou d’informations (icône « + »)

🎨 Interface utilisateur (UI)

L’interface est conçue avec RecyclerView et un CardView personnalisé pour chaque animal.

Mode Linéaire :

Chaque animal est affiché dans une seule ligne avec :

Son image à gauche

Son nom et son espèce à droite

Une icône d’action (supprimer ou info) à droite de la carte.

Mode Grille :

Les animaux sont disposés en colonnes de 2, sous forme de cartes compactes avec :

L’image centrée

Les boutons d’action (supprimer, ajouter) superposés ou en bas de la carte.

⚙️ Fonctionnalités principales
Fonction	Description
🧭 Changement d’affichage	L’utilisateur peut choisir entre une liste linéaire ou une grille à l’aide des boutons radio.
🐕 Affichage dynamique	Les animaux sont gérés dans une RecyclerView avec un Adapter Kotlin qui s’adapte selon le mode sélectionné.
➕ Ajout d’un animal	Un bouton permet d’ajouter un nouvel animal à la liste (ou d’afficher des détails).
🗑️ Suppression d’un animal	Le bouton corbeille supprime la carte correspondante de la RecyclerView.
💡 Design moderne	Interface pastel avec coins arrondis et ombrages doux (CardView + ConstraintLayout).
🧩 Structure du projet
app/

 ├── java/
 
 │   └── com.example.animaux/
 
 │       ├── MainActivity.kt
 
 │       ├── Animal.kt
 
 │       ├── AnimalAdapter.kt
 
 │       ├── AnimalViewHolder.kt
 
 │       └── DataProvider.kt
 
 ├── res/
 
 │   ├── layout/
 
 │   │   ├── activity_main.xml
 
 │   │   ├── item_animal_linear.xml
 
 │   │   └── item_animal_grid.xml
 
 │   ├── drawable/
 
 │   │   └── (images des animaux)
 
 │   └── values/
 
 │       └── colors.xml, styles.xml, strings.xml
 
 └── AndroidManifest.xml

🧠 Code principal
MainActivity.kt

Initialise la RecyclerView

Gère le RadioGroup pour changer le LayoutManager :

recyclerView.layoutManager = LinearLayoutManager(this)
recyclerView.adapter = AnimalAdapter(listeAnimaux)


ou :

recyclerView.layoutManager = GridLayoutManager(this, 2)

AnimalAdapter.kt

Gère l’affichage des éléments (cartes)

Associe les données (nom, espèce, image)

Contient les listeners pour les boutons (ajout/suppression)

Animal.kt
data class Animal(
    val nom: String,
    val espece: String,
    val imageResId: Int
)

🧰 Technologies utilisées

Langage : Kotlin

Interface : XML

Composants Android :

RecyclerView

CardView

RadioButton / RadioGroup

ImageView & TextView

Architecture : MVC simplifiée (MainActivity → Adapter → Layout)

🪄 Exemple de rendu visuel
Mode Linéaire	Mode Grille

	
