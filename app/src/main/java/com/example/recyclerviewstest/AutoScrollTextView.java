package com.example.recyclerviewstest;
import android.content.Context;
import android.os.Handler;
import android.text.Layout;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/* La classe AutoScrollTextView hérite de AppCompatTextView et permet d'ajouter une fonction de scroll horizontal automatique aux TextViews */
public class AutoScrollTextView extends AppCompatTextView {

    // Ces deux attributs permettent de contrôler le délai et la vitesse de défilement, ce sont les valeurs à modifier pour modifier la vitesse du défilement
    private static final int SCROLL_DELAY = 40; // Délai de défilement (en millisecondes)
    private static final int SCROLL_SPEED = 2; // Vitesse de défilement (en pixels)

    /* On utilise un Handler pour planifier le défilement à intervalles réguliers :
     * Sources : https://developer.android.com/reference/android/os/Handler */
    private Handler handler;
    private Runnable runnable;


    /* Constructeur de la classe, on passe le contexte puis les attributs contenus dans le fichier XML
     * https://developer.android.com/reference/android/util/AttributeSet */
    public AutoScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setSingleLine(); // Permet d'afficher le texte sur une seule ligne
        setEllipsize(null); // On supprime le marquage à la fin du texte
        setHorizontallyScrolling(true); // On active le défilement horizontal

        handler = new Handler();

        /* A chaque exécution, on augmente la position de défilement "ScrollX" par "SCROLL_SPEED"
         * Si elle dépasse la largueur de la vue, elle est réinitialisée à 0 */
        runnable = new Runnable() {
            @Override
            public void run() {
                // On récupère la position actuelle de la vue
                // Source : https://developer.android.com/reference/android/view/View#getScrollX()
                int scrollX = getScrollX();
                int totalWidth = calculateTotalTextWidth();

                // On incrémente la position de défilement
                scrollX += SCROLL_SPEED;

                // On vérifie si le texte dépasse de la largeur de la TextView
                // Source : https://developer.android.com/reference/android/view/View#getWidth()
                if (totalWidth > getWidth()) {
                    // On incrémente la position de défilement
                    scrollX += SCROLL_SPEED;

                    // Si la position dépasse la largeur totale du texte
                    if (scrollX >= totalWidth) {
                        //On soustrait la largeur totale du texte et la largeur et la largeur de la vue pour que le texte redéfile depuis la droite de la textview
                        scrollX -= totalWidth + getWidth();
                    }

                    // Applique la position de défilement à la vue
                    // Source : https://developer.android.com/reference/android/view/View#scrollTo(int,%20int)
                    scrollTo(scrollX, 0);
                }

                // On planifie le prochain défilement
                handler.postDelayed(this, SCROLL_DELAY);
            }
        };
    }

    private int calculateTotalTextWidth() {
        // On récupère le Layout utilisé pour afficher le texte dans la TextView
        Layout layout = getLayout();

        // Si il y a du texte, on recupère la largeur de ce dernier
        if (layout != null) {
            int textWidth = (int) layout.getLineWidth(0);
            return textWidth;
        }

        // Si le Layout est null, cela signifie qu'il n'y a pas de texte, donc la largeur totale est de 0
        return 0;
    }

    /* Pour économiser des ressources, on commence le scroll uniquement lorsque le TextView est affiché et on l'arrête lorsque ce dernier ne l'est plus */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startScroll();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopScroll();
    }

    /* Cette méthode est appelée lorsque la vue est "attachée à la fenêtre" (voir méthode juste au dessus), en fait, lorsque notre TextView sera affiché à l'écran
     * Elle démarre le défilement automatique en appelant le handler */
    public void startScroll() {
        handler.postDelayed(runnable, SCROLL_DELAY);
    }

    /* Cette méthode est appelée lorsque la vue est "détachée de la fenêtre" (voir méthode juste au dessus), en fait, lorsque notre TextView n'est plus affiché à l'écran
     * Elle arrête le défilement automatique en supprimant les callbacks du handler */
    public void stopScroll() {
        handler.removeCallbacks(runnable);
    }
}
