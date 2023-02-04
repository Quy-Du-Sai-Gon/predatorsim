package org.quydusaigon.predatorsim.util;

import org.quydusaigon.predatorsim.App;

public class Map {
    
     /**
      * Return the appropriate {@code x} position when lower-bounded by 0 and upper-bounded by {@link App.simulationWindowWidth}.
      * @param x position in Cartesian coordinates.
      * @return the approriate {@code x} position after being bounded.
      */
    public static double checkBoundX(double x) {
       if (x > Parameter.getWindowWidth()) {
            x = Parameter.getWindowWidth();
       }
       else if (x < 0) {
            x = 0;
       }

       return x;
    }

    /**
      * Return the appropriate {@code y} position when lower-bounded by 0 and upper-bounded by {@link App.simulationWindowHeight}.
      * @param y position in Cartesian coordinates.
      * @return the approriate {@code y} position after being bounded.
      */
    public static double checkBoundY(double y) {
        if (y > Parameter.getWindowHeight()) {
            y = Parameter.getWindowHeight();
       }
       else if (y < 0) {
            y = 0;
       }

       return y;
    }
}
