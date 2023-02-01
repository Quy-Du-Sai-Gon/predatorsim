package org.quydusaigon;

public class Output {
    public static Output instance;

    private Output() {
    }

    public double nutritionGained = 0;
    public double nutritionConsumed = 0;

    public int predatorCount = 0;
    public int smallPreyCount = 0;
    public int mediumPreyCount = 0;
    public int largePreyCount = 0;

    public int predatorDeadCount = 0;
    public int smallPreyDeadCount = 0;
    public int mediumPreyDeadCount = 0;
    public int largePreyDeadCount = 0;

    public static Output getInstance() {
        if (instance == null) {
            return new Output();
        } else
            return instance;
    }

}
