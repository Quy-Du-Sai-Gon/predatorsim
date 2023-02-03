package org.quydusaigon;

public final class Output {
    private static Output instance;

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
            instance = new Output();
        }

        return instance;
    }

    public void resetData() {
        nutritionGained = 0;
        nutritionConsumed = 0;

        predatorCount = 0;
        smallPreyCount = 0;
        mediumPreyCount = 0;
        largePreyCount = 0;

        predatorDeadCount = 0;
        smallPreyDeadCount = 0;
        mediumPreyDeadCount = 0;
        largePreyDeadCount = 0;
    }

}
