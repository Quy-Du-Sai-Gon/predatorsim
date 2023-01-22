package org.quydusaigon.predatorsim.util;

import org.quydusaigon.predatorsim.App;

public class Map {
    
    public static double checkBoundX(double x) {
       if (x > App.simulationWindowWidth) {
            x = App.simulationWindowWidth;
       }
       else if (x < 0) {
            x = 0;
       }

       return x;
    }

    public static double checkBoundY(double y) {
        if (y > App.simulationWindowHeight) {
            y = App.simulationWindowHeight;
       }
       else if (y < 0) {
            y = 0;
       }

       return y;
    }
}
