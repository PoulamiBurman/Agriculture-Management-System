
package Top_Classes;

import java.util.HashMap; //Not sure if we will implement Resources this way

public class Resources {
    /*
     * Fertilizer, Tractor, Pesticides,
     */
    HashMap<String, Integer> Quantity = new HashMap<String, Integer>();
    int irrigationRating;
    int Score;

    Resources() {
        Quantity.put("Machines", 0);
        Quantity.put("Fertilizers", 0);
        irrigationRating = 0;
    }

    Resources(int m, int f, int ir) {
        Quantity.put("Machines", m);
        Quantity.put("Fertilizers", f);
        irrigationRating = ir;
        calcScore();
    }

    void calcScore() {
        Score = 0;
        Score += Quantity.get("Machines") * 5 + Quantity.get("Fertilizers") * 2 + irrigationRating;
    }
}
