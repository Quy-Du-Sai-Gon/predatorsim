package org.quydusaigon.predatorsim.util;

public class Parameter {

    public static String getWindowWidthString() {
        return String.valueOf(windowWidth);
    }

    public static double getWindowWidth() {
        return windowWidth;
    }

    public static void setWindowWidth(String windowWidth) {
        Parameter.windowWidth = Double.parseDouble(windowWidth);
    }

    public static String getWindowHeightString() {
        return String.valueOf(windowHeight);
    }

    public static double getWindowHeight() {
        return windowHeight;
    }

    public static void setWindowHeight(String windowHeight) {
        Parameter.windowHeight = Double.parseDouble(windowHeight);
    }

    private static double windowWidth = 700;
    private static double windowHeight = 700;

    private static int predatorCount = 12;
    private static int smallPreyCount = 0;
    private static int mediumPreyCount = 10;
    private static int largePreyCount = 0;

    public static void setPredatorCount(String predatorCount) {
        Parameter.predatorCount = Integer.parseInt(predatorCount);
    }

    public static void setSmallPreyCount(String smallPreyCount) {
        Parameter.smallPreyCount = Integer.parseInt(smallPreyCount);
    }

    public static void setMediumPreyCount(String mediumPreyCount) {
        Parameter.mediumPreyCount = Integer.parseInt(mediumPreyCount);
    }

    public static void setLargePreyCount(String largePreyCount) {
        Parameter.largePreyCount = Integer.parseInt(largePreyCount);
    }

    public static void setPredatorSpeedMinimumRange(String predatorSpeedMinimumRange) {
        Parameter.predatorSpeedMinimumRange = Double.parseDouble(predatorSpeedMinimumRange);
    }

    public static void setPredatorSpeedMaximumRange(String predatorSpeedMaximumRange) {
        Parameter.predatorSpeedMaximumRange = Double.parseDouble(predatorSpeedMaximumRange);
    }

    public static void setSmallPreySpeedMinimumRange(String smallPreySpeedMinimumRange) {
        Parameter.smallPreySpeedMinimumRange = Double.parseDouble(smallPreySpeedMinimumRange);
    }

    public static void setSmallPreySpeedMaximumRange(String smallPreySpeedMaximumRange) {
        Parameter.smallPreySpeedMaximumRange = Double.parseDouble(smallPreySpeedMaximumRange);
    }

    public static void setMediumPreySpeedMinimumRange(String mediumPreySpeedMinimumRange) {
        Parameter.mediumPreySpeedMinimumRange = Double.parseDouble(mediumPreySpeedMinimumRange);
    }

    public static void setMediumPreySpeedMaximumRange(String mediumPreySpeedMaximumRange) {
        Parameter.mediumPreySpeedMaximumRange = Double.parseDouble(mediumPreySpeedMaximumRange);
    }

    public static void setLargePreySpeedMinimumRange(String largePreySpeedMinimumRange) {
        Parameter.largePreySpeedMinimumRange = Double.parseDouble(largePreySpeedMinimumRange);
    }

    public static void setLargePreySpeedMaximumRange(String largePreySpeedMaximumRange) {
        Parameter.largePreySpeedMaximumRange = Double.parseDouble(largePreySpeedMaximumRange);
    }

    public static void setPredatorVisionMinimumRange(String predatorVisionMinimumRange) {
        Parameter.predatorVisionMinimumRange = Double.parseDouble(predatorVisionMinimumRange);
    }

    public static void setPredatorVisionMaximumRange(String predatorVisionMaximumRange) {
        Parameter.predatorVisionMaximumRange = Double.parseDouble(predatorVisionMaximumRange);
    }

    public static void setSmallPreyVisionMinimumRange(String smallPreyVisionMinimumRange) {
        Parameter.smallPreyVisionMinimumRange = Double.parseDouble(smallPreyVisionMinimumRange);
    }

    public static void setSmallPreyVisionMaximumRange(String smallPreyVisionMaximumRange) {
        Parameter.smallPreyVisionMaximumRange = Double.parseDouble(smallPreyVisionMaximumRange);
    }

    public static void setMediumPreyVisionMinimumRange(String mediumPreyVisionMinimumRange) {
        Parameter.mediumPreyVisionMinimumRange = Double.parseDouble(mediumPreyVisionMinimumRange);
    }

    public static void setMediumPreyVisionMaximumRange(String mediumPreyVisionMaximumRange) {
        Parameter.mediumPreyVisionMaximumRange = Double.parseDouble(mediumPreyVisionMaximumRange);
    }

    public static void setLargePreyVisionMinimumRange(String largePreyVisionMinimumRange) {
        Parameter.largePreyVisionMinimumRange = Double.parseDouble(largePreyVisionMinimumRange);
    }

    public static void setLargePreyVisionMaximumRange(String largePreyVisionMaximumRange) {
        Parameter.largePreyVisionMaximumRange = Double.parseDouble(largePreyVisionMaximumRange);
    }

    public static void setSmallPreyNutritionMinimumRange(String smallPreyNutritionMinimumRange) {
        Parameter.smallPreyNutritionMinimumRange = Integer.parseInt(smallPreyNutritionMinimumRange);
    }

    public static void setSmallPreyNutritionMaximumRange(String smallPreyNutritionMaximumRange) {
        Parameter.smallPreyNutritionMaximumRange = Integer.parseInt(smallPreyNutritionMaximumRange);
    }

    public static void setMediumPreyNutritionMinimumRange(String mediumPreyNutritionMinimumRange) {
        Parameter.mediumPreyNutritionMinimumRange = Integer.parseInt(mediumPreyNutritionMinimumRange);
    }

    public static void setMediumPreyNutritionMaximumRange(String mediumPreyNutritionMaximumRange) {
        Parameter.mediumPreyNutritionMaximumRange = Integer.parseInt(mediumPreyNutritionMaximumRange);
    }

    public static void setLargePreyNutritionMinimumRange(String largePreyNutritionMinimumRange) {
        Parameter.largePreyNutritionMinimumRange = Integer.parseInt(largePreyNutritionMinimumRange);
    }

    public static void setLargePreyNutritionMaximumRange(String largePreyNutritionMaximumRange) {
        Parameter.largePreyNutritionMaximumRange = Integer.parseInt(largePreyNutritionMaximumRange);
    }

    public static void setSmallPreyDefenseMinimumRange(String smallPreyDefenseMinimumRange) {
        Parameter.smallPreyDefenseMinimumRange = Double.parseDouble(smallPreyDefenseMinimumRange);
    }

    public static void setSmallPreyDefenseMaximumRange(String smallPreyDefenseMaximumRange) {
        Parameter.smallPreyDefenseMaximumRange = Double.parseDouble(smallPreyDefenseMaximumRange);
    }

    public static void setMediumPreyDefenseMinimumRange(String mediumPreyDefenseMinimumRange) {
        Parameter.mediumPreyDefenseMinimumRange = Double.parseDouble(mediumPreyDefenseMinimumRange);
    }

    public static void setMediumPreyDefenseMaximumRange(String mediumPreyDefenseMaximumRange) {
        Parameter.mediumPreyDefenseMaximumRange = Double.parseDouble(mediumPreyDefenseMaximumRange);
    }

    public static void setLargePreyDefenseMinimumRange(String largePreyDefenseMinimumRange) {
        Parameter.largePreyDefenseMinimumRange = Double.parseDouble(largePreyDefenseMinimumRange);
    }

    public static void setLargePreyDefenseMaximumRange(String largePreyDefenseMaximumRange) {
        Parameter.largePreyDefenseMaximumRange = Double.parseDouble(largePreyDefenseMaximumRange);
    }

    public static void setPredatorStarvationResillienceMinimumRange(String predatorStarvationResillienceMinimumRange) {
        Parameter.predatorStarvationResillienceMinimumRange = Integer
                .parseInt(predatorStarvationResillienceMinimumRange);
    }

    public static void setPredatorStarvationResillienceMaximumRange(String predatorStarvationResillienceMaximumRange) {
        Parameter.predatorStarvationResillienceMaximumRange = Integer
                .parseInt(predatorStarvationResillienceMaximumRange);
    }

    public static void setPredatorGroupRadius(String predatorGroupRadius) {
        Parameter.predatorGroupRadius = Double.parseDouble(predatorGroupRadius);
    }

    public static void setRelativeSimulationSpeed(String relativeSimulationSpeed) {
        Parameter.relativeSimulationSpeed = Double.parseDouble(relativeSimulationSpeed);
    }
    private static double predatorSpeedMinimumRange = 5;
    private static double predatorSpeedMaximumRange = 6;
    private static double smallPreySpeedMinimumRange = 4;
    private static double smallPreySpeedMaximumRange = 5;
    private static double mediumPreySpeedMinimumRange = 3;
    private static double mediumPreySpeedMaximumRange = 4;
    private static double largePreySpeedMinimumRange = 2;
    private static double largePreySpeedMaximumRange = 3;

    private static double predatorVisionMinimumRange = 80;
    private static double predatorVisionMaximumRange = 110;
    private static double smallPreyVisionMinimumRange = 70;
    private static double smallPreyVisionMaximumRange = 90;
    private static double mediumPreyVisionMinimumRange = 70;
    private static double mediumPreyVisionMaximumRange = 90;
    private static double largePreyVisionMinimumRange = 70;
    private static double largePreyVisionMaximumRange = 90;

    private static int smallPreyNutritionMinimumRange = 150;
    private static int smallPreyNutritionMaximumRange = 200;
    private static int mediumPreyNutritionMinimumRange = 600;
    private static int mediumPreyNutritionMaximumRange = 800;
    private static int largePreyNutritionMinimumRange = 1000;
    private static int largePreyNutritionMaximumRange = 1200;

    private static double smallPreyDefenseMinimumRange = 0;
    private static double smallPreyDefenseMaximumRange = 10;
    private static double mediumPreyDefenseMinimumRange = 30;
    private static double mediumPreyDefenseMaximumRange = 50;
    private static double largePreyDefenseMinimumRange = 75;
    private static double largePreyDefenseMaximumRange = 100;

    private static int predatorStarvationResillienceMinimumRange = 750;
    private static int predatorStarvationResillienceMaximumRange = 1000;
    private static double predatorGroupRadius = 100;
    private static double relativeSimulationSpeed = 50;

    public static String getPredatorCountString() {
        return String.valueOf(predatorCount);
    }

    public static int getPredatorCount() {
        return predatorCount;
    }

    public static String getSmallPreyCountString() {
        return String.valueOf(smallPreyCount);
    }

    public static int getSmallPreyCount() {
        return smallPreyCount;
    }

    public static String getMediumPreyCountString() {
        return String.valueOf(mediumPreyCount);
    }

    public static int getMediumPreyCount() {
        return mediumPreyCount;
    }

    public static String getLargePreyCountString() {
        return String.valueOf(largePreyCount);
    }

    public static int getLargePreyCount() {
        return largePreyCount;
    }

    public static String getPredatorSpeedMinimumRangeString() {
        return String.valueOf(predatorSpeedMinimumRange);
    }

    public static double getPredatorSpeedMinimumRange() {
        return predatorSpeedMinimumRange;
    }

    public static String getPredatorSpeedMaximumRangeString() {
        return String.valueOf(predatorSpeedMaximumRange);
    }

    public static double getPredatorSpeedMaximumRange() {
        return predatorSpeedMaximumRange;
    }

    public static String getSmallPreySpeedMinimumRangeString() {
        return String.valueOf(smallPreySpeedMinimumRange);
    }

    public static double getSmallPreySpeedMinimumRange() {
        return smallPreySpeedMinimumRange;
    }

    public static String getSmallPreySpeedMaximumRangeString() {
        return String.valueOf(smallPreySpeedMaximumRange);
    }

    public static double getSmallPreySpeedMaximumRange() {
        return smallPreySpeedMaximumRange;
    }

    public static String getMediumPreySpeedMinimumRangeString() {
        return String.valueOf(mediumPreySpeedMinimumRange);
    }

    public static double getMediumPreySpeedMinimumRange() {
        return mediumPreySpeedMinimumRange;
    }

    public static String getMediumPreySpeedMaximumRangeString() {
        return String.valueOf(mediumPreySpeedMaximumRange);
    }

    public static double getMediumPreySpeedMaximumRange() {
        return mediumPreySpeedMaximumRange;
    }

    public static String getLargePreySpeedMinimumRangeString() {
        return String.valueOf(largePreySpeedMinimumRange);
    }

    public static double getLargePreySpeedMinimumRange() {
        return largePreySpeedMinimumRange;
    }

    public static String getLargePreySpeedMaximumRangeString() {
        return String.valueOf(largePreySpeedMaximumRange);
    }

    public static double getLargePreySpeedMaximumRange() {
        return largePreySpeedMaximumRange;
    }

    public static String getPredatorVisionMinimumRangeString() {
        return String.valueOf(predatorVisionMinimumRange);
    }

    public static double getPredatorVisionMinimumRange() {
        return predatorVisionMinimumRange;
    }

    public static String getPredatorVisionMaximumRangeString() {
        return String.valueOf(predatorVisionMaximumRange);
    }

    public static double getPredatorVisionMaximumRange() {
        return predatorVisionMaximumRange;
    }

    public static String getSmallPreyVisionMinimumRangeString() {
        return String.valueOf(smallPreyVisionMinimumRange);
    }

    public static double getSmallPreyVisionMinimumRange() {
        return smallPreyVisionMinimumRange;
    }

    public static String getSmallPreyVisionMaximumRangeString() {
        return String.valueOf(smallPreyVisionMaximumRange);
    }

    public static double getSmallPreyVisionMaximumRange() {
        return smallPreyVisionMaximumRange;
    }

    public static String getMediumPreyVisionMinimumRangeString() {
        return String.valueOf(mediumPreyVisionMinimumRange);
    }

    public static double getMediumPreyVisionMinimumRange() {
        return mediumPreyVisionMinimumRange;
    }

    public static String getMediumPreyVisionMaximumRangeString() {
        return String.valueOf(mediumPreyVisionMaximumRange);
    }

    public static double getMediumPreyVisionMaximumRange() {
        return mediumPreyVisionMaximumRange;
    }

    public static String getLargePreyVisionMinimumRangeString() {
        return String.valueOf(largePreyVisionMinimumRange);
    }

    public static double getLargePreyVisionMinimumRange() {
        return largePreyVisionMinimumRange;
    }

    public static String getLargePreyVisionMaximumRangeString() {
        return String.valueOf(largePreyVisionMaximumRange);
    }

    public static double getLargePreyVisionMaximumRange() {
        return largePreyVisionMaximumRange;
    }

    public static String getSmallPreyNutritionMinimumRangeString() {
        return String.valueOf(smallPreyNutritionMinimumRange);
    }

    public static int getSmallPreyNutritionMinimumRange() {
        return smallPreyNutritionMinimumRange;
    }

    public static String getSmallPreyNutritionMaximumRangeString() {
        return String.valueOf(smallPreyNutritionMaximumRange);
    }

    public static int getSmallPreyNutritionMaximumRange() {
        return smallPreyNutritionMaximumRange;
    }

    public static String getMediumPreyNutritionMinimumRangeString() {
        return String.valueOf(mediumPreyNutritionMinimumRange);
    }

    public static int getMediumPreyNutritionMinimumRange() {
        return mediumPreyNutritionMinimumRange;
    }

    public static String getMediumPreyNutritionMaximumRangeString() {
        return String.valueOf(mediumPreyNutritionMaximumRange);
    }

    public static int getMediumPreyNutritionMaximumRange() {
        return mediumPreyNutritionMaximumRange;
    }

    public static String getLargePreyNutritionMinimumRangeString() {
        return String.valueOf(largePreyNutritionMinimumRange);
    }

    public static int getLargePreyNutritionMinimumRange() {
        return largePreyNutritionMinimumRange;
    }

    public static String getLargePreyNutritionMaximumRangeString() {
        return String.valueOf(largePreyNutritionMaximumRange);
    }

    public static int getLargePreyNutritionMaximumRange() {
        return largePreyNutritionMaximumRange;
    }

    public static String getSmallPreyDefenseMinimumRangeString() {
        return String.valueOf(smallPreyDefenseMinimumRange);
    }

    public static double getSmallPreyDefenseMinimumRange() {
        return smallPreyDefenseMinimumRange;
    }

    public static String getSmallPreyDefenseMaximumRangeString() {
        return String.valueOf(smallPreyDefenseMaximumRange);
    }

    public static double getSmallPreyDefenseMaximumRange() {
        return smallPreyDefenseMaximumRange;
    }

    public static String getMediumPreyDefenseMinimumRangeString() {
        return String.valueOf(mediumPreyDefenseMinimumRange);
    }

    public static double getMediumPreyDefenseMinimumRange() {
        return mediumPreyDefenseMinimumRange;
    }

    public static String getMediumPreyDefenseMaximumRangeString() {
        return String.valueOf(mediumPreyDefenseMaximumRange);
    }

    public static double getMediumPreyDefenseMaximumRange() {
        return mediumPreyDefenseMaximumRange;
    }

    public static String getLargePreyDefenseMinimumRangeString() {
        return String.valueOf(largePreyDefenseMinimumRange);
    }

    public static double getLargePreyDefenseMinimumRange() {
        return largePreyDefenseMinimumRange;
    }

    public static String getLargePreyDefenseMaximumRangeString() {
        return String.valueOf(largePreyDefenseMaximumRange);
    }

    public static double getLargePreyDefenseMaximumRange() {
        return largePreyDefenseMaximumRange;
    }

    public static String getPredatorStarvationResillienceMinimumRangeString() {
        return String.valueOf(predatorStarvationResillienceMinimumRange);
    }

    public static int getPredatorStarvationResillienceMinimumRange() {
        return predatorStarvationResillienceMinimumRange;
    }

    public static String getPredatorStarvationResillienceMaximumRangeString() {
        return String.valueOf(predatorStarvationResillienceMaximumRange);
    }

    public static int getPredatorStarvationResillienceMaximumRange() {
        return predatorStarvationResillienceMaximumRange;
    }

    public static String getPredatorGroupRadiusString() {
        return String.valueOf(predatorGroupRadius);
    }

    public static double getPredatorGroupRadius() {
        return predatorGroupRadius;
    }

    public static String getRelativeSimulationSpeedString() {
        return String.valueOf(relativeSimulationSpeed);
    }

    public static double getRelativeSimulationSpeed() {
        return relativeSimulationSpeed;
    }

}

