# Android_RecyclerView
RecyclerView + AutoScroll sur TextViews

## Concernant le défilement du texte dans les TextViews : 

La classe *AutoScrollTextView* hérite de *AppCompatTextView* : 
https://developer.android.com/reference/androidx/appcompat/widget/AppCompatTextView

Elle permet de faire défiler le texte dans les TextViews quand ce dernier est trop large pour la TextView.

Il suffit dans un fichier XML de créer une TextView comme suit : 
>  <com.example.votrepackage.AutoScrollTextView 
>  [attributs]
>  />

On peut modifier la vitesse du défilement avec les constantes *SCROLL_DELAY* et *SCROLL_SPEED*.
